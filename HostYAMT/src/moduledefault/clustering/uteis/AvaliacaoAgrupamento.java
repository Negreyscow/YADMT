/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.uteis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Thiago
 */
public class AvaliacaoAgrupamento {

    private List<String> classes;
    private List<Cluster> clusters;
    private int tamanhoBase;
    private Base base;
    //
    private double variancia;
    private int[][] mconfusao;
    private float medidaF;
    private float indiceAleatorio;
    private float indiceDunn;
    private double acerto;


    public AvaliacaoAgrupamento(ArrayList<Cluster> clusters, List<String> classes, Base _base) {
        this.classes = classes;
        this.clusters = clusters;
        base = _base;
        this.tamanhoBase = base.getDataSet().size();
         
        matrizConfusao(); //mecher// se a base tem classe entao executar todos as funçoes abaixo
        medidaF();
        indiceAleatorio();
        SQE();///senao executar somente variancia e dunn()
        dunn();
        //ajusteMatrizDeConfusao();
        acerto();
    }

    public final void indiceAleatorio() {
        String[] classepad = new String[tamanhoBase];
        String[] agrupados = new String[tamanhoBase];
        
        for (int j = 0; j < clusters.size(); j++) {
            clusters.get(j).setNomeClasse("-1");
        }

        for (int i = 0; i < mconfusao.length; i++) {
            for (int j = 0; j < mconfusao[0].length; j++) {
                if (i == j) {
                    for (int k = 0; k < clusters.size(); k++) {
                        if (clusters.get(k).getNumClasse(classes.get(i)) == mconfusao[i][j]) {
                            clusters.get(k).setNomeClasse(classes.get(i));
                            break;
                        }
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < clusters.size(); i++) {
            for (int j = 0; j < clusters.get(i).getGrupo().size(); j++) {
                classepad[count] = clusters.get(i).getGrupo().get(j).getClasse();
                agrupados[count] = clusters.get(i).getNomeClasse();
                ++count;
                if (count >= tamanhoBase) { //Parada por causa do watershed - ARRUMAR
                    break;
                }
            }
            if (count >= tamanhoBase) {//Parada por causa do watershed - ARRUMAR
                break;
            }
        }

        float a = 0, b = 0, c = 0, d = 0;

        for (int i = 0; i < tamanhoBase; i++) {
            for (int j = i + 1; j < tamanhoBase; j++) {
                if ((classepad[i].equals(classepad[j])) && (agrupados[i].equals(agrupados[j]))) {
                    ++a;
                }
                if ((classepad[i].equals(classepad[j])) && (!agrupados[i].equals(agrupados[j]))) {
                    ++b;
                }
                if ((!classepad[i].equals(classepad[j])) && (agrupados[i].equals(agrupados[j]))) {
                    ++c;
                }
                if ((!classepad[i].equals(classepad[j])) && (!agrupados[i].equals(agrupados[j]))) {
                    ++d;
                }
            }
        }
        indiceAleatorio = (a + d) / (a + b + c + d);        
    }

    public final void dunn() {
        float centro1 = 0, qdesvio1 = 0, diam1 = 0;
        float centro2 = 0, diam2 = 0;
        float mdistancia = 0, fdistancia = 0, diamfinal = 0;
        int qpadrao1 = 0, qpadrao2 = 0;

        for (int i = 0; i < clusters.size(); i++) {
            ///////////Primeiro grupo
            qpadrao1 = clusters.get(i).getGrupo().size();
            for (int j = 0; j < clusters.get(i).getGrupo().size(); j++) {
                centro1 += clusters.get(i).getGrupo().get(j).getNumero();
            }
            //System.out.println("qpadrao1 = " + qpadrao1);
            centro1 = centro1 / qpadrao1;//calcula o centroide/média
            for (int j = 0; j < clusters.get(i).getGrupo().size(); j++) {
                qdesvio1 = clusters.get(i).getGrupo().get(j).getNumero() - centro1;
                qdesvio1 = (float) Math.pow(qdesvio1, 2);
                if (qdesvio1 > diam1) {
                    diam1 = qdesvio1;
                }
                qdesvio1 = 0;
            }
            ///////////Segundo grupo
            for (int x = i + 1; x < clusters.size(); x++) {
                qpadrao2 = clusters.get(x).getGrupo().size();

                //System.out.println("qpadrao2 = " + qpadrao2);
                for (int j = 0; j < clusters.get(x).getGrupo().size(); j++) {
                    centro1 += clusters.get(x).getGrupo().get(j).getNumero();
                }
                centro2 = centro2 / qpadrao2;//calcula o centroide/média

                for (int j = 0; j < clusters.get(x).getGrupo().size(); j++) {
                    qdesvio1 = clusters.get(x).getGrupo().get(j).getNumero() - centro1;
                    qdesvio1 = (float) Math.pow(qdesvio1, 2);
                    if (qdesvio1 > diam1) {
                        diam1 = qdesvio1;
                    }
                    qdesvio1 = 0;
                }
                ////Distancia entre as médias
                mdistancia = (float) Math.sqrt((Math.pow(centro1, 2)) - (Math.pow(centro2, 2)));
                if (diam1 >= diam2) {
                    diamfinal = diam1;
                } else {
                    diamfinal = diam2;
                }
                fdistancia = mdistancia / (diamfinal);///arrumar pegar o maior diam entre todos os grupos
                if (fdistancia < indiceDunn) {
                    indiceDunn = fdistancia;
                }
                ////Zera as variaveis para prox iteração
                centro2 = 0;
                diam2 = 0;
                qpadrao2 = 0;
                mdistancia = 0;
                fdistancia = 0;
            }
            ////Zera as variaveis para prox iteração
            centro1 = 0;
            qdesvio1 = 0;
            diam1 = 0;
            qpadrao1 = 0;
        }
    }

    public double[] centroide(Cluster cluster) {
        
        double[] centroide = new double[base.getAtributos().size()];//
        System.out.println(base.getAtributos().size());
        //int[] aux = new int[cluster.getGrupo().size()];
        int aux;

        for (int i = 0; i < cluster.getGrupo().size() - 1; i++) { //mecher // retorno do centroide deve ser um vetor, verificar k-means
                aux = cluster.getGrupo().get(i).getNumero();//aux deve ser a posicao do padrao que esta no grupo i
               //add exception
                for (int k = 0; k < base.getAtributos().size() - 1; k++){
                    centroide[k] += base.getDataSet().get(aux).getAtributos().get(k);
                }
            //soma dos padroes
        }
        for (int l = 0; l < base.getAtributos().size() - 1; l++){
            centroide[l] = centroide[l] / cluster.getGrupo().size();
            System.out.println(centroide[l]);
        }
       // centroide = centroide / cluster.getGrupo().size(); //calcula centroide/media

        return centroide;
    }

    
        public final void SQE() { // mecher armazenar os centroides de cada grupo em uma matrix e  
        double qdesvio, sqdesvio = 0, somaqdesvio = 0;
        double[] centro = new double[base.getAtributos().size()];
        int qpadrao, somapadrao = 0;
        //Cluster clusterss = new Cluster();
       // double[] vetor_centroide = centroide();
        for (Cluster cluster : clusters) { //Tipo Cluster recebe o valor de clusters
            qpadrao = cluster.getGrupo().size(); //numero de padroes no grupo
            
            //somapadrao += qpadrao; //todos os padroes da base
            centro = centroide(cluster);
            for (int j = 0; j < qpadrao; j++) {
                for (int k = 0; k < base.getAtributos().size() - 1; k++){
                    sqdesvio += Math.pow(base.getDataSet().get(j).getAtributos().get(k) - centro[k], 2);
                }
            }
            
            somaqdesvio += sqdesvio;//somatorios dos quadrados dos desvios entre todos os padroes de todos os grupos
            sqdesvio = 0;
          ////////////Fazer a variancia para todos os grupos
        }
        variancia = somaqdesvio;//variancia total, de todos os grupos da matriz
    } 
        
        
        
    /*
    public final void variancia() { // mecher armazenar os centroides de cada grupo em uma matrix e  
        float qdesvio, sqdesvio = 0, somaqdesvio = 0;
        double[] centro = new double[base.getAtributos().size()];
        int qpadrao, somapadrao = 0;
        //Cluster clusterss = new Cluster();
       // double[] vetor_centroide = centroide();
        int cont_aux = 0;
        for (Cluster cluster : clusters) { //Tipo Cluster recebe o valor de clusters
            qpadrao = cluster.getGrupo().size(); //numero de padroes no grupo
            for(int i = 0; i < base.getAtributos().size(); i++){
              somapadrao += Math.pow(base.getDataSet().get(cont_aux).getAtributos().get(i) - vetor_centroide[i], 2);
            }
            
            //somapadrao += qpadrao; //todos os padroes da base
            
            centro = centroide(cluster);
            for (int j = 0; j < qpadrao; j++) {
                qdesvio = cluster.getGrupo().get(j).getNumero() - centro;
                qdesvio = (float) Math.pow(qdesvio, 2);
                sqdesvio += qdesvio;
            }
            
            somaqdesvio += sqdesvio;//somatorios dos quadrados dos desvios entre todos os padroes de todos os grupos
            sqdesvio = 0;
            cont_aux++;
            ////////////Fazer a variancia para todos os grupos
        }
        variancia = somaqdesvio / somapadrao;//variancia total, de todos os grupos da matriz
    } */

    public final void matrizConfusao() {
        int gruposDesejados = classes.size();
        int gruposFormados = clusters.size();

        mconfusao = new int[gruposDesejados][gruposFormados];

        clusters =  selectionSort(clusters);

        for (int j = 0; j < gruposFormados; j++) { //Coluna = formados
            for (int i = 0; i < gruposDesejados; i++) {
                mconfusao[i][j] = clusters.get(j).getNumClasse(classes.get(i)); 
            }
        }
        //Ajusta as posições da matriz confusao
        
        for (int i = 0; i < gruposDesejados; i++) { /// mecher Criar uma funçao que receba a matrix de confusao e faça o ajuste com o codigo abaixo depois das chamadas de funçao da avaliaçao
            int aux = 0;
            int grupo = -1;
            for (int j = i; j < gruposFormados; j++) {
                if (mconfusao[i][j] > aux) {
                    boolean maior = true;
                    for (int k = i + 1; k < gruposDesejados; k++) {
                        if (mconfusao[i][j] < mconfusao[k][j]) {
                            maior = false;
                        }
                    }
                    if (maior) {
                        aux = mconfusao[i][j];
                        grupo = j;
                    }
                }
            }
            if (grupo != i && grupo != -1) {
                for (int k = 0; k < gruposDesejados; k++) {
                    int swap = mconfusao[k][grupo];
                    mconfusao[k][grupo] = mconfusao[k][i];
                    mconfusao[k][i] = swap;
                }
            }
        } 
    }

    public final void acerto() {
        for (int i = 0; i < mconfusao.length; i++) {
            for (int j = 0; j < mconfusao[0].length; j++) {
                if (i == j) {
                    acerto += mconfusao[i][i];
                }
            }

        }
        acerto = acerto * 100;
        acerto = acerto / tamanhoBase;
    }

    public final void medidaF() {
        int numpad = classes.size();
        int contgrupo = clusters.size();


        float[][] p = new float[numpad][contgrupo];
        float[][] r = new float[numpad][contgrupo];
        float[][] f = new float[numpad][contgrupo];
        float[] gerado = new float[contgrupo];
        float[] desejados = new float[numpad];
        float b = 1, somapad = 0;

        for (int j = 0; j < contgrupo; j++) {
            gerado[j] = 0;
        }
        for (int i = 0; i < numpad; i++) {
            desejados[i] = 0;
        }
        for (int j = 0; j < contgrupo; j++) {
            for (int i = 0; i < numpad; i++) {
                gerado[j] = gerado[j] + mconfusao[i][j];
            }
        }
        for (int i = 0; i < numpad; i++) {
            for (int j = 0; j < contgrupo; j++) {
                desejados[i] = desejados[i] + mconfusao[i][j];
            }
        }
        for (int i = 0; i < numpad; i++) {
            somapad = somapad + desejados[i];
        }
        for (int i = 0; i < numpad; i++) {
            for (int j = 0; j < contgrupo; j++) {
                p[i][j] = 0;
                r[i][j] = 0;
                f[i][j] = 0;
            }
        }
        ///CALCULO P
        for (int i = 0; i < numpad; i++) {
            for (int j = 0; j < contgrupo; j++) {
                p[i][j] = mconfusao[i][j] / gerado[j];
            }
        }
        ///CALCULO R
        for (int i = 0; i < numpad; i++) {
            for (int j = 0; j < contgrupo; j++) {
                r[i][j] = mconfusao[i][j] / desejados[i];
            }
        }
        ///CALCULO F
        for (int i = 0; i < numpad; i++) {
            for (int j = 0; j < contgrupo; j++) {
                f[i][j] = (((b * b) + 1) * p[i][j] * r[i][j]) / ((b * b) * p[i][j] + r[i][j]);
            }
        }

        float maxj = 0;
        for (int i = 0; i < numpad; i++) {
            for (int j = 0; j < contgrupo; j++) {
                if (f[i][j] > maxj) {
                    maxj = f[i][j];
                }
            }
            medidaF = medidaF + ((desejados[i] / somapad) * maxj);
            maxj = 0;
        }
    }

    //TROCAR POR MERGE SORT!!!!
    public List<Cluster> selectionSort(List<Cluster> clusters) { //ORDENA EM ORDEM CRESCENTE POR PADRÕES CARREGADOS
        int index_min;
        Cluster aux;

        for (int i = 0; i < clusters.size(); i++) {
            index_min = i;
            for (int j = i + 1; j < clusters.size(); j++) {
                if (clusters.get(j).numPadroes() < clusters.get(index_min).numPadroes()) {
                    index_min = j;
                }
            }
            if (index_min != i) {
                aux = clusters.get(index_min);
                clusters.set(index_min, clusters.get(i));
                clusters.set(i, aux);
            }
        }
        return clusters;
    }

    public double getVariancia() {
        return variancia;
    }

    public void setVariancia(double variancia) {
        this.variancia = variancia;
    }

    public int[][] getMconfusao() {
        return mconfusao;
    }

    public void setMconfusao(int[][] mconfusao) {
        this.mconfusao = mconfusao;
    }

    public double getAcerto() {
        return acerto;
    }

    public float getMedidaF() {
        return medidaF;
    }

    public void setAcerto(double acerto) {
        this.acerto = acerto;
    }

    public float getIndiceDunn() {
        return indiceDunn;
    }

    public float getIndiceAleatorio() {
        return indiceAleatorio;
    }
    
    
}
