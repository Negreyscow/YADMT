/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.hierarquicos;

import java.util.ArrayList;
import java.util.List;
import moduledefault.clustering.distancias.Chebyshev;
import moduledefault.clustering.distancias.CityBlock;
import moduledefault.clustering.distancias.CorrelacaoPearson;
import moduledefault.clustering.distancias.Cosseno;
import moduledefault.clustering.distancias.DistanciaEuclidiana;
import moduledefault.clustering.distancias.Mahalanobis;
import moduledefault.clustering.uteis.Cluster;
import moduledefault.clustering.uteis.Padrao;

/**
 *
 * @author Mateus
 */
public class LigacaoSimples {

    private List<Padrao> padroes;
    private int numeroPadroes;
    private int opcaoDistancia;
    private int[][] matrizDendograma;
    private double[][] matrizDistancia;
    private ArrayList<Cluster> clusters;

    public LigacaoSimples(List<Padrao> padroes, int opcaoDistancia) {
        this.padroes = padroes;
        this.numeroPadroes = padroes.size();
        this.opcaoDistancia = opcaoDistancia;
    }

    public void ligacaoSimples() {
        calcMatrizDistancia();
        matrizDendograma = new int[numeroPadroes][numeroPadroes];

        for (int i = 0; i < numeroPadroes; i++) {
            for (int j = 0; j < numeroPadroes; j++) {
                matrizDendograma[i][j] = 0;
            }
        }
        for (int i = 0; i < numeroPadroes; i++) {
            matrizDendograma[0][i] = i + 1;
        }
        /////////////////

        double min = 1000000, d = 0;
        int pad1 = 0, pad2 = 0, q = 1;
        int cont = 0, para = 0;
        /////////////////
        for (int y = 0; y < numeroPadroes - 1; y++){
            cont = 0;
            min = 1000000;
            d = 0;
            pad1 = 0;
            pad2 = 0;
            for (int i = 0; i < numeroPadroes; i++) {
                for (int j = i + 1; j < numeroPadroes; j++) {
                    if ((matrizDendograma[q - 1][i] == matrizDendograma[q - 1][j])) {
                        matrizDistancia[i][j] = 0;
                        matrizDistancia[j][i] = 0;
                    }
                }
            }
            //maior distancia entre um padrão e os padrões de um mesmo grupo
            for (int i = 0; i < numeroPadroes; i++) {
                for (int j = i + 1; j < numeroPadroes; j++) {
                    if ((matrizDendograma[q - 1][i] == matrizDendograma[q - 1][j])) {
                        for (int k = 0; k < numeroPadroes; k++) {
                            if ((k != i) && (k != j)) {
                                if (matrizDistancia[k][j] < matrizDistancia[k][i]) {
                                    d = matrizDistancia[k][j];
                                } else {
                                    d = matrizDistancia[k][i];
                                }
                                matrizDistancia[i][k] = d;
                                matrizDistancia[k][i] = d;
                                matrizDistancia[j][k] = d;
                                matrizDistancia[k][j] = d;
                            }
                        }
                    }
                }
            }
            //menor distancia
            for (int i = 0; i < numeroPadroes; i++) {
                for (int j = 0; j < numeroPadroes; j++) {
                    if ((matrizDistancia[i][j] < min) && (matrizDistancia[i][j] > 0)) {
                        min = matrizDistancia[i][j];
                        pad1 = i + 1;
                        pad2 = j + 1;

                    }
                }
            }
            for (int i = 0; i < numeroPadroes; i++) {
                if ((matrizDendograma[q - 1][i] == pad1) || (matrizDendograma[q - 1][i] == pad2)) {
                    matrizDendograma[q][i] = matrizDendograma[q - 1][pad1 - 1];
                } else {
                    matrizDendograma[q][i] = matrizDendograma[q - 1][i];
                }
            }
            ++q;
        }
       
    }
    
  public void clustering(int grupos) {
        int lineCluster = numeroPadroes - grupos;
        ArrayList<Integer> g = new ArrayList<>();
        for (int i = 0; i < numeroPadroes; i++) {
            if (!g.contains(matrizDendograma[lineCluster][i])) {
                g.add(matrizDendograma[lineCluster][i]);
            }
        }

        clusters = new ArrayList<>();

        for (int i = 0; i < g.size(); i++) {
            Cluster c = new Cluster();
            c.setNomeGrupo("" + (i + 1));
            for (int j = 0; j < numeroPadroes; j++) {
                if (matrizDendograma[lineCluster][j] == g.get(i)) {
                    c.addPadrao(padroes.get(j));
                }
            }
            clusters.add(c);
        }

    }

    public void calcMatrizDistancia() {
        switch (opcaoDistancia) {
            case 1:
                Chebyshev ch = new Chebyshev();
                matrizDistancia = new double[padroes.size()][padroes.size()];
                for (int i = 0; i < padroes.size(); i++) {
                    for (int j = 0; j < padroes.size(); j++) {
                        matrizDistancia[i][j] = ch.distancia(padroes.get(i).getAtributos(), padroes.get(j).getAtributos());
                    }
                }
                break;
            case 2:
                CityBlock ci = new CityBlock();
                matrizDistancia = new double[padroes.size()][padroes.size()];
                for (int i = 0; i < padroes.size(); i++) {
                    for (int j = 0; j < padroes.size(); j++) {
                        matrizDistancia[i][j] = ci.distancia(padroes.get(i).getAtributos(), padroes.get(j).getAtributos());
                    }
                }
                break;
            case 3:
                CorrelacaoPearson pea = new CorrelacaoPearson();
                matrizDistancia = new double[padroes.size()][padroes.size()];
                for (int i = 0; i < padroes.size(); i++) {
                    for (int j = 0; j < padroes.size(); j++) {
                        matrizDistancia[i][j] = pea.distancia(padroes.get(i).getAtributos(), padroes.get(j).getAtributos());
                    }
                }
                break;
            case 4:
                Cosseno cos = new Cosseno();
                matrizDistancia = new double[padroes.size()][padroes.size()];
                for (int i = 0; i < padroes.size(); i++) {
                    for (int j = 0; j < padroes.size(); j++) {
                        matrizDistancia[i][j] = cos.distancia(padroes.get(i).getAtributos(), padroes.get(j).getAtributos());
                    }
                }
                break;
            case 5:
                DistanciaEuclidiana eu = new DistanciaEuclidiana();
                matrizDistancia = new double[padroes.size()][padroes.size()];
                for (int i = 0; i < padroes.size(); i++) {
                    for (int j = 0; j < padroes.size(); j++) {
                        matrizDistancia[i][j] = eu.distancia(padroes.get(i).getAtributos(), padroes.get(j).getAtributos());
                    }
                }
                break;
            case 6:
                Mahalanobis ma = new Mahalanobis();
                matrizDistancia = new double[padroes.size()][padroes.size()];
                for (int i = 0; i < padroes.size(); i++) {
                    for (int j = 0; j < padroes.size(); j++) {
                        matrizDistancia[i][j] = ma.distancia(padroes.get(i).getAtributos(), padroes.get(j).getAtributos());
                    }
                }
                break;
        }
    }

    public int[][] getMatrizDendograma() {
        return matrizDendograma;
    }

    public ArrayList<Cluster> getClusters() {
        return clusters;
    }
}
