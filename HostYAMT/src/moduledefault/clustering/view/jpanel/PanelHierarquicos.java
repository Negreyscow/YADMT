/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.view.jpanel;

import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.ComponentUI;
import moduledefault.clustering.hierarquicos.visualization.BinTree;
import moduledefault.clustering.hierarquicos.LigacaoCompleta;
import moduledefault.clustering.hierarquicos.LigacaoMedia;
import moduledefault.clustering.hierarquicos.LigacaoSimples;
import moduledefault.clustering.hierarquicos.Ward;
import moduledefault.clustering.hierarquicos.visualization.TreeView;
import moduledefault.clustering.uteis.AvaliacaoAgrupamento;
import moduledefault.clustering.uteis.Base;
import moduledefault.clustering.uteis.Cluster;
import moduledefault.clustering.uteis.Operações_Mat;
import moduledefault.clustering.uteis.Padrao;
import moduledefault.clustering.view.frames.JFrameHierarquicos;
import moduledefault.clustering.visualization.FramePrincipal.TecnicasDispersao;
import moduledefault.clustering.visualization.panels.JDialogData;

/**
 *
 * @author Mateus
 */
public class PanelHierarquicos extends javax.swing.JPanel {

    /**
     * Creates new form PanelHierarquicos
     */
    int teste_distancia;
    StringBuffer b;
    int[][] matrizGrupos;
    int numeroGrupos;
    ArrayList<Integer> elementoPrincipal;
    ArrayList<StringBuffer> listaText;
    interfaces.Base base;
    Base dados;
    Base dadosOriginal;
    JFrameHierarquicos frameHierarquicos;
    BinTree dendograma = new BinTree();
    ArrayList<Cluster> clusters = null;

    public PanelHierarquicos(interfaces.Base b, JFrameHierarquicos f) {
        initComponents();
        frameHierarquicos = f;
        base = b;
        listaText = new ArrayList<>();
        startMatrizDados();
        buttonVisualizacao.setEnabled(false);
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
    }

    public int[][] getMatrizGrupos() {
        return matrizGrupos;
    }

    public void setMatrizGrupos(int[][] matrizGrupos) {
        this.matrizGrupos = matrizGrupos;
    }

    public int getNumeroGrupos() {
        return numeroGrupos;
    }

    public void setNumeroGrupos(int numeroGrupos) {
        this.numeroGrupos = numeroGrupos;
    }

    public ComponentUI getUi() {
        return ui;
    }

    public void setUi(ComponentUI ui) {
        this.ui = ui;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        buttonExecutar = new javax.swing.JButton();
        buttonVisualizacao = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxDistancias = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxMetodos = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        listResultados = new java.awt.List();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(718, 458));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        buttonExecutar.setText("Executar");
        buttonExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExecutarActionPerformed(evt);
            }
        });

        buttonVisualizacao.setText("Visualização");
        buttonVisualizacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVisualizacaoActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Distância"));

        jLabel1.setText("Função de Distância:");

        jComboBoxDistancias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Chebyshev","CityBlock","Correlação de Kendall","Correlação Pearson","Correlação de Spearman" ,"Cosseno", "Euclidiana", "Mahalanobis" }));
        jComboBoxDistancias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDistanciasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jComboBoxDistancias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxDistancias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Método:"));

        jLabel2.setText("Método:");

        jComboBoxMetodos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Completa", "Média", "Simples","Ward" }));
        jComboBoxMetodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMetodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jComboBoxMetodos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jComboBoxMetodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Resultados")));

        listResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listResultadosMouseClicked(evt);
            }
        });
        listResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listResultadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(listResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButton1.setText("Visualizar Dendograma");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Visualizar Dados");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonExecutar, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(buttonVisualizacao, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(buttonExecutar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonVisualizacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonVisualizacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVisualizacaoActionPerformed
        TecnicasDispersao.getInstance().setSetou(false);
        TecnicasDispersao.getInstance().setMatrizDados(dados);
        TecnicasDispersao.getInstance().setCluster(clusters);
        TecnicasDispersao.getInstance().setQntGrupos(clusters.size());
        TecnicasDispersao.getInstance().setCombos();
        TecnicasDispersao.getInstance().setVisible(true);
        TecnicasDispersao.getInstance().inicia();
    }//GEN-LAST:event_buttonVisualizacaoActionPerformed

    private void jComboBoxMetodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMetodosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMetodosActionPerformed

    private void buttonExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExecutarActionPerformed

        buttonVisualizacao.setEnabled(false);
        jButton1.setEnabled(false);

        switch (this.jComboBoxDistancias.getSelectedIndex()) {
            case 0:
                JOptionPane.showMessageDialog(null, "Selecione uma Medida de Distância.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            case 1:
                teste_distancia = 1;
                break;
            case 2:
                teste_distancia = 2;
                break;
            case 3:
                teste_distancia = 3;
                break;
            case 4:
                teste_distancia = 4;
                break;
            case 5:
                teste_distancia = 5;
                break;
            case 6:
                teste_distancia = 6;
                break;
            case 7:
                teste_distancia = 7;
                break;
            case 8:
                teste_distancia = 8;
                break;
            default:
                break;
        }
        System.out.println("indice = " + jComboBoxMetodos.getSelectedIndex());
        switch (this.jComboBoxMetodos.getSelectedIndex()) {
            case 0:
                JOptionPane.showMessageDialog(null, "Selecione um Método.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            case 1:
                LigacaoCompleta LC = new LigacaoCompleta(dados.getDataSet(), teste_distancia);
                LC.ligacaoCompleta();
                LC.clustering(frameHierarquicos.getNumK());
                clusters = LC.getClusters();
                int[][] matrizDendograma = LC.getMatrizDendograma();
                dendograma = new BinTree();
                dendograma.createTree(matrizDendograma);
                break;
            case 2:
                LigacaoMedia LM = new LigacaoMedia(dados.getDataSet(), 5);
                LM.ligacaoMedia();
                LM.clustering(frameHierarquicos.getNumK()); //Definir numero de grupos
                clusters = LM.getClusters();
                matrizDendograma = LM.getMatrizDendograma();
                dendograma = new BinTree();
                dendograma.createTree(matrizDendograma);
                break;
            case 3:
                LigacaoSimples LS = new LigacaoSimples(dados.getDataSet(), 5);
                LS.ligacaoSimples();
                LS.clustering(frameHierarquicos.getNumK()); //Definir numero de grupos
                clusters = LS.getClusters();
                matrizDendograma = LS.getMatrizDendograma();
                dendograma = new BinTree();
                dendograma.createTree(matrizDendograma);
                break;
            case 4:
                Ward ward = new Ward(dados.getDataSet());
                ward.ward();
                ward.clustering(frameHierarquicos.getNumK());
                clusters = ward.getClusters();
                matrizDendograma = ward.getMatrizDendograma();
                dendograma = new BinTree();
                dendograma.createTree(matrizDendograma);
                break;
            default:
                break;
        }

        //imprime em tela o agrupamento realizado
        imprimiAgrupamento();
        imprimiRecuperacao();
        jTextArea1.setText(getBuffer().toString());

        buttonVisualizacao.setEnabled(true);
        jButton1.setEnabled(true);
        jButton2.setEnabled(true);
    }//GEN-LAST:event_buttonExecutarActionPerformed

    private void listResultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listResultadosMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (listResultados.getSelectedIndex() != -1) {
                StringBuffer text = listaText.get(listResultados.getSelectedIndex());
                jTextArea1.setText(text.toString());
            }
        }
    }//GEN-LAST:event_listResultadosMouseClicked

    private void listResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listResultadosActionPerformed
    }//GEN-LAST:event_listResultadosActionPerformed

    private void jComboBoxDistanciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDistanciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxDistanciasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dendograma.treeAnalysis(dendograma.getRoot());
        TreeView.newTreeXML(dendograma.getXml());
        JComponent treeview = TreeView.demo("tree.xml", "name");
//                frame.setDendograma(dendograma);
//                frame.setVisible(true);
//                frame.repaint();
        JFrame frame1 = new JFrame("Dendograma");
        frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame1.setContentPane(treeview);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String[] atributos = new String[dadosOriginal.getAtributos().size() + 2];
        atributos[0] = "ID";
        for (int j = 0; j < dadosOriginal.getAtributos().size(); j++) {
            atributos[j + 1] = dadosOriginal.getAtributos().get(j);
        }
        atributos[atributos.length - 1] = "Cluster_ID";

        Object[][] data = new Object[dadosOriginal.getDataSet().size()][dadosOriginal.getDataSet().get(0).getAtributos().size() + 3];
        for (int i = 0; i < dadosOriginal.getDataSet().size(); i++) {
            data[i][0] = dadosOriginal.getDataSet().get(i).getNumero();
        }
        for (int i = 0; i < dadosOriginal.getDataSet().size(); i++) {
            for (int j = 0; j < dadosOriginal.getDataSet().get(0).getAtributos().size(); j++) {
                data[i][j + 1] = dadosOriginal.getDataSet().get(i).getAtributos().get(j);
            }
        }
        for (int i = 0; i < dadosOriginal.getDataSet().size(); i++) {
            data[i][dadosOriginal.getDataSet().get(0).getAtributos().size() + 1] = dadosOriginal.getDataSet().get(i).getClasse();
        }
        for (int i = 0; i < clusters.size(); i++) {
            for (int j = 0; j < clusters.get(i).getGrupo().size(); j++) {
                data[clusters.get(i).getGrupo().get(j).getNumero()][data[0].length - 1] = clusters.get(i).getNomeGrupo();
            }
        }
        new JDialogData((Frame) frameHierarquicos, true, data, atributos).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonExecutar;
    private javax.swing.JButton buttonVisualizacao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private static javax.swing.JComboBox jComboBoxDistancias;
    private static javax.swing.JComboBox jComboBoxMetodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private java.awt.List listResultados;
    // End of variables declaration//GEN-END:variables

    public void startMatrizDados() {
        dados = new moduledefault.clustering.uteis.Base();
        double matriz[][] = new double[base.getInput().length][base.getInput()[0].length]; //= arrayListBases.get(arrayListBases.size() - 1).getInput();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] = Double.valueOf(base.getInput()[i][j] + "").doubleValue();
            }
        }


        int grupo = 0;
        for (int i = 0; i < matriz.length; i++) {
            Padrao p = new Padrao();
            p.setNumero(grupo);
            ++grupo;
            for (int j = 0; j < matriz[0].length; j++) {
                p.addAtributos(matriz[i][j]);

            }
            p.setClasse(base.getOutput()[i].toString());
            dados.addDataSet(p);
        }

        for (int i = 0; i < base.getAtributes().length; i++) {
            dados.addAtributos(base.getAtributes()[i]);
        }

        dados.setClasses((List) base.getClasses());
        dados.setNome((String) base.getName());
        dados.setDimensaoMatriz();
        dadosOriginal = dados;
        Operações_Mat m = new Operações_Mat();
        m.Padronização(dados);

    }

    int[][] pmat(int[][] matriz) {
        int i = 0, j = 0, x = 0, y;
        Random random = new Random();
        for (i = 0; i < dados.getDimensaoMatriz(); i++) {
            for (j = 0; j < dados.getDimensaoMatriz(); j++) {
                matriz[i][j] = 0;
            }
        }
        for (y = 1; y <= matriz.length; y++) {//coloca os padroes sem repetir na grade
            do {
                i = random.nextInt((int) dados.getDimensaoMatriz());
                j = random.nextInt((int) dados.getDimensaoMatriz());
            } while (matriz[i][j] != 0);
            x++;
            if (matriz[i][j] == 0) {
                matriz[i][j] = x;
            }
        }
        return matriz;
    }

    synchronized void imprimiAgrupamento() {
        StringBuffer buffer = new StringBuffer();
        setBuffer(buffer);
        getBuffer().append("===================== Informações =====================");
        getBuffer().append("\n\t\t\tYADMT.Clustering.Hierárquicos");
        getBuffer().append("\n\tBase: " + dados.getNome());
//        getBuffer().append("\n\t Número de Instâncias: " + teste.getLinhas());
//        getBuffer().append("\n\t Atributos: " + (teste.getColunas() - 1));
        getBuffer().append("\n\tClasses:");
        for (int i = 0; i < dados.getClasses().size(); i++) {
            getBuffer().append("\n\t\t" + dados.getClasses().get(i));
        }

        switch (jComboBoxMetodos.getSelectedIndex()) {
            case 1:
                getBuffer().append("\n\tMétodo:\n  \tLigação Completa.\n");
                break;
            case 2:
                getBuffer().append("\n\tMétodo:\n  \tLigação Média.\n");
                break;
            case 3:
                getBuffer().append("\n\tMétodo:\n  \tLigação Simples.\n");
                break;
            case 4:
                getBuffer().append("\n\tMétodo:\n  \tWard.\n");
                break;
        }
        switch (teste_distancia) {
            case 1:
                getBuffer().append("\tMedida de Distância:\n  \tChebyshev");
                break;
            case 2:
                getBuffer().append("\tMedida de Distância:\n  \tCityblock");
                break;
            case 3:
                getBuffer().append("\tMedida de Distância:\n  \tCorrelação");
                break;
            case 4:
                getBuffer().append("\tMedida de Distância:\n  \tCosseno");
                break;
            case 5:
                getBuffer().append("\tMedida de Distância:\n  \tEuclidiana");
                break;
            case 6:
                getBuffer().append("\tMedida de Distância:\n  \tMahalanobis");
                break;
            default:
                break;
        }
        getBuffer().append("\n\n");
    }

    synchronized void imprimiRecuperacao() {

        if (clusters != null) {
            AvaliacaoAgrupamento avaliacao = new AvaliacaoAgrupamento(clusters, dados.getClasses(), dados);
            getBuffer().append("\n================ Avaliação do Agrupamento ===============\n");
            getBuffer().append("Grupos Formados:\t" + clusters.size() + "\n");
            getBuffer().append("Medida F:\t\t" + avaliacao.getMedidaF() + "\n");
            getBuffer().append("Medida R:\t\t" + avaliacao.getIndiceAleatorio() + "\n");
            float acertos = avaliacao.getAcerto();
            getBuffer().append("Porcentagem de Acerto:\t" + acertos + "%\n");
            getBuffer().append("Viariância Total:\t" + avaliacao.getVariancia() + "\n");


            getBuffer().append("\nMatriz Confusão:\n");
            int[][] mconfusao = avaliacao.getMconfusao();

            char classe = 'a';
            for (int i = 0; i < mconfusao[0].length; i++) {
                getBuffer().append(classe + "\t");
                ++classe;
            }
            getBuffer().append("\n");
            classe = 'a';
            for (int i = 0; i < mconfusao.length; i++) {
                for (int j = 0; j < mconfusao[0].length; j++) {
                    getBuffer().append(mconfusao[i][j] + "\t");
                    if (j == mconfusao[0].length - 1) {
                        getBuffer().append("\t" + classe + " = " + dados.getClasses().get(i));
                        ++classe;
                    }
                }
                getBuffer().append("\n");
            }
            getBuffer().append("\n\n");
            ArrayList<Integer> grupo;
            String padrao;
            for (int i = 0; i < clusters.size(); i++) {
//                clusters.get(i).setNomeGrupo(dados.getClasses());
                getBuffer().append("Grupo " + (i + 1) + ":");
                grupo = clusters.get(i).getSortGrupo();
                for (int j = 0; j < grupo.size(); j++) {
                    padrao = grupo.get(j) + "";
                    switch (padrao.length()) {
                        case 1:
                            padrao += "   ";
                            break;
                        case 2:
                            padrao += "  ";
                            break;
                        case 3:
                            padrao += " ";
                            break;
                    }
                    if (j % 10 == 0) {
                        getBuffer().append("\n");
                    }
                    getBuffer().append(padrao);
                }
                getBuffer().append("\n\n");
                //jTextArea1.append("Centróide: " + avaliacao.centroide(clusters.get(i)) + "\n\n");
            }

            //Medidas de avaliaxao do agrupamento

        }
    }

    public StringBuffer getBuffer() {
        return b;
    }

    public void setBuffer(StringBuffer b) {
        this.b = b;
    }

    private void setListaResultados() {
        PanelHierarquicos.ClusteringText text = new PanelHierarquicos.ClusteringText();
        String s = text.toString(jComboBoxMetodos.getSelectedIndex());
        text.setJTextArea(jTextArea1);
        listResultados.add(s);
        listaText.add(getBuffer());
    }

    public void addBase(interfaces.Base get) {
        base = get;
        startMatrizDados();
    }

    class ClusteringText {

        final DateFormat formatter;
        private JTextArea text;
        private Date date;

        public ClusteringText() {
            date = new Date();
            formatter = new SimpleDateFormat("HH:mm:ss");
        }

        /**
         * returns the text area
         *
         * @return
         */
        public JTextArea getJTextArea() {
            return text;
        }

        /**
         * sets the text area
         *
         * @param text
         */
        public void setJTextArea(JTextArea text) {
            this.text = text;
        }

        public String toString(int index) {
            switch (jComboBoxMetodos.getSelectedIndex()) {
                case 1:
                    return formatter.format(date) + " - Ligação Completa";
                case 2:
                    return formatter.format(date) + " - Ligação Média";
                case 3:
                    return formatter.format(date) + " - Ligação Simples";
                case 4:
                    return formatter.format(date) + " - Ward";
                default:
                    return "";
            }

        }
    }
}
