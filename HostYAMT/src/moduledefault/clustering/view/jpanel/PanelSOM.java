/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.view.jpanel;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;
import moduledefault.clustering.uteis.AvaliacaoAgrupamento;
import moduledefault.clustering.uteis.Base;
import moduledefault.clustering.uteis.Cluster;
import moduledefault.clustering.som.ClusteringSOM;
import moduledefault.clustering.som.MarcadoresWaterShed;
import moduledefault.clustering.som.OpMath;
import moduledefault.clustering.som.PanelMarcadores;
import moduledefault.clustering.uteis.Padrao;
import moduledefault.clustering.som.RedeSOM;
import moduledefault.clustering.som.visualization.FrameSomVisualization;
import moduledefault.clustering.view.frames.JFrameHierarquicos;
import moduledefault.clustering.view.frames.JFrameKohonenConfig;
import moduledefault.clustering.view.frames.JFrameKohonenConfigDensidade;

/**
 *
 * @author Thiago Magalhães Faino
 */
public final class PanelSOM extends javax.swing.JPanel {

    //Variaveis Kohonen
    private interfaces.Base Base = new interfaces.Base();
    private Base dados;
    private RedeSOM rede = null;
    private int gridX = 0;
    private int gridY = 0;
    private String distancia;
    private String atualiza;
    private String agrupamento;
    private int raio;
    private int iteracoes;
    private float alfa;
    //
    private double[][] matrizU;
    private static int[][] marcadores = null;
    //
    Thread t;
    private static int sleep = 0;
    //
    JFrameKohonenConfig frameKohonen;
    //
    JFrameKohonenConfigDensidade densidadeConfig = null;
    JFrameHierarquicos hierarquicosConfig = null;
    private static int numGrupos = 3;
    //
    ArrayList<String> resutados = new ArrayList<>();
    DefaultListModel listaResultados = new DefaultListModel();
    private DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    private Date date = new Date();

    public PanelSOM(interfaces.Base b, JFrameKohonenConfig fk) {
        initComponents();

        frameKohonen = fk;
        Base = b;
        carregaBase();
        resutados.clear();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoVizinhanca = new javax.swing.ButtonGroup();
        grupoDistancia = new javax.swing.ButtonGroup();
        grupoAprendizagem = new javax.swing.ButtonGroup();
        grupoGrade = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        inicio = new javax.swing.JButton();
        campoIteracoes = new javax.swing.JSpinner();
        jProgressBar1 = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jButtonConfigDensidade = new javax.swing.JButton();
        jButtonAgrupamento = new javax.swing.JButton();
        boxAlgoritmo = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListResultados = new javax.swing.JList();
        jPanel19 = new javax.swing.JPanel();
        bVisualizacao = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Treinamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel6.setText("Número de Iterações:");

        inicio.setText("Treinar");
        inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioActionPerformed(evt);
            }
        });

        campoIteracoes.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(500), Integer.valueOf(1), null, Integer.valueOf(1)));
        campoIteracoes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                campoIteracoesStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jProgressBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createSequentialGroup()
                        .add(jLabel6)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(campoIteracoes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(inicio, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(campoIteracoes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(inicio)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jProgressBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        jTextArea.setEditable(false);
        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        jScrollPane1.setViewportView(jTextArea);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agrupamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jButtonConfigDensidade.setText("Config");
        jButtonConfigDensidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfigDensidadeActionPerformed(evt);
            }
        });

        jButtonAgrupamento.setText("Agrupar");
        jButtonAgrupamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgrupamentoActionPerformed(evt);
            }
        });

        boxAlgoritmo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1D SOM", "SL-SOM", "Matriz de Densidade", "Ligação Simples", "Ligação Média", "Ligação Completa", "Método de Ward", "Componentes Conectados" }));
        boxAlgoritmo.setToolTipText("");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(boxAlgoritmo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 137, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButtonConfigDensidade)
                .addContainerGap())
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jButtonAgrupamento, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(12, 12, 12))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButtonConfigDensidade)
                    .add(boxAlgoritmo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButtonAgrupamento))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Resultados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jListResultados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListResultadosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jListResultados);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 98, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Visualização", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        bVisualizacao.setText("Visualização");
        bVisualizacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bVisualizacaoActionPerformed(evt);
            }
        });

        jLabel1.setText("Métodos de Visualização:");

        org.jdesktop.layout.GroupLayout jPanel19Layout = new org.jdesktop.layout.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel19Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel19Layout.createSequentialGroup()
                        .add(jLabel1)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, bVisualizacao, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel19Layout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(bVisualizacao))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel19, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(1, 1, 1)
                        .add(jPanel19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 115, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioActionPerformed
        //função de distancia
        switch (frameKohonen.getjComboBoxDistancia().getSelectedIndex()) {
            case 0:
                distancia = "euclidiana";
                break;
        }

        //Funcao de Atualização
        switch (frameKohonen.getjComboBoxFuncAtualizacao().getSelectedIndex()) {
            case 0:
                atualiza = "exponencial";
                break;
            case 1:
                atualiza = "reciproca";
                break;
            case 2:
                atualiza = "linear";
                break;
        }

        inicio.setEnabled(false);
        startSOM();

        repaint();
    }//GEN-LAST:event_inicioActionPerformed

    private void campoIteracoesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_campoIteracoesStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_campoIteracoesStateChanged

    private void bVisualizacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bVisualizacaoActionPerformed
        FrameSomVisualization.getInstance().setDados(dados);
        FrameSomVisualization.getInstance().setVisible(true);
        FrameSomVisualization.getInstance().calcBase3D();
    }//GEN-LAST:event_bVisualizacaoActionPerformed

    private void jButtonConfigDensidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfigDensidadeActionPerformed
        switch (boxAlgoritmo.getSelectedIndex()) {
            case 0:
                agrupamento = "1DSOM";
                break;
            case 1:
                MarcadoresWaterShed frame = new MarcadoresWaterShed();
                frame.setVisible(true);
                agrupamento = "slsom";
                break;
            case 2:
                agrupamento = "densidade";
                if (densidadeConfig == null) {
                    densidadeConfig = new JFrameKohonenConfigDensidade();
                }
                densidadeConfig.setVisible(true);
                break;
            case 3:
                agrupamento = "simples";
                if (hierarquicosConfig == null) {
                    hierarquicosConfig = new JFrameHierarquicos();
                }
                hierarquicosConfig.setVisible(true);
                break;
            case 4:
                agrupamento = "media";
                if (hierarquicosConfig == null) {
                    hierarquicosConfig = new JFrameHierarquicos();
                }
                hierarquicosConfig.setVisible(true);
                break;
            case 5:
                agrupamento = "completa";
                if (hierarquicosConfig == null) {
                    hierarquicosConfig = new JFrameHierarquicos();
                }
                hierarquicosConfig.setVisible(true);
                break;
            case 6:
                agrupamento = "ward";
                if (hierarquicosConfig == null) {
                    hierarquicosConfig = new JFrameHierarquicos();
                }
                hierarquicosConfig.setVisible(true);
            case 7:
                agrupamento = "faino";
                break;
        }
    }//GEN-LAST:event_jButtonConfigDensidadeActionPerformed

    private void jButtonAgrupamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgrupamentoActionPerformed
        System.out.println(numGrupos);
        switch (boxAlgoritmo.getSelectedIndex()) {
            case 0:
                agrupamento = "1DSOM";
                break;
            case 1:
                agrupamento = "slsom";
                break;
            case 2:
                agrupamento = "densidade";
                if (densidadeConfig == null) {
                    densidadeConfig = new JFrameKohonenConfigDensidade();
                }
                break;
            case 3:
                agrupamento = "simples";
                break;
            case 4:
                agrupamento = "media";
                break;
            case 5:
                agrupamento = "completa";
                break;
            case 6:
                agrupamento = "ward";
                break;
            case 7:
                agrupamento = "faino";
                break;
        }
        clusterig();
    }//GEN-LAST:event_jButtonAgrupamentoActionPerformed

    private void jListResultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListResultadosMouseClicked
        jTextArea.setText(resutados.get(jListResultados.getSelectedIndex()));
    }//GEN-LAST:event_jListResultadosMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bVisualizacao;
    private javax.swing.JComboBox boxAlgoritmo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JSpinner campoIteracoes;
    private javax.swing.ButtonGroup grupoAprendizagem;
    private javax.swing.ButtonGroup grupoDistancia;
    private javax.swing.ButtonGroup grupoGrade;
    private javax.swing.ButtonGroup grupoVizinhanca;
    private javax.swing.JButton inicio;
    private javax.swing.JButton jButtonAgrupamento;
    private javax.swing.JButton jButtonConfigDensidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList jListResultados;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea;
    // End of variables declaration//GEN-END:variables

    public void startSOM() {
        gridX = Integer.parseInt(frameKohonen.getCampoGridX().getValue().toString());
        gridY = Integer.parseInt(frameKohonen.getCampoGridY().getValue().toString());

        raio = Integer.parseInt(frameKohonen.getCampoRaio().getValue().toString());
        iteracoes = Integer.parseInt(campoIteracoes.getValue().toString());
        alfa = Float.parseFloat(frameKohonen.getCampoAprendizagem().getText());

        rede = new RedeSOM(gridX, gridY, raio, iteracoes, alfa, dados, distancia, atualiza); //cria a Rede
        jProgressBar1.setMaximum(iteracoes);

        FrameSomVisualization.getInstance().setMaxStatus(iteracoes);
        FrameSomVisualization.getInstance().setLabel("Executando");

        FrameSomVisualization.getInstance().setRede(rede);

        PanelMarcadores.getP().clear();
   
        
        t = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < iteracoes; i++) {
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PanelSOM.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    atualizaStatus(i);
                    rede.startRede(i); //inicia o Kohonen
                    FrameSomVisualization.getInstance().setRede(rede);
                    repaint();
                    
                    
                }
                //Calcula o Erro
                rede.calcErro();

                setTextSOM();
                //carrega os padroes...
                rede.carregaPadroes(); //
                //Calcula a matriz U
                OpMath math = new OpMath();
                matrizU = math.matrizU(gridX, gridY, rede);
                //
                FrameSomVisualization.getInstance().setVisualization(rede, matrizU);
                inicio.setEnabled(true);
                //

            }
        };
        t.start();
    }

    public void clusterig() {
        ClusteringSOM cluster = new ClusteringSOM(rede);
        ArrayList<Cluster> clusters = new ArrayList<>();

        setTextSOM();
        //Realiza o Agrupamento
        switch (agrupamento) {
            case "densidade":
                clusters = cluster.clusteringDensidade(Double.parseDouble(densidadeConfig.getCampoErro().getText()));
                jTextArea.append("\n========== Agrupamento por Matriz de Densidade ==========\n");
                break;
            case "1DSOM":
                clusters = cluster.clustering1DSOM();
                jTextArea.append("\n================== Agrupamento 1-D SOM ==================\n");
                break;
            case "simples":
                clusters = cluster.hierarquicos("simples", numGrupos);
                jTextArea.append("\n================== Agrupamento Ligação Simples ==================\n");
                break;
            case "media":
                clusters = cluster.hierarquicos("media", numGrupos);
                jTextArea.append("\n================== Agrupamento Ligação Média ==================\n");
                break;
            case "completa":
                clusters = cluster.hierarquicos("completa", numGrupos);
                jTextArea.append("\n================== Agrupamento Ligação Completa ==================\n");
                break;
            case "ward":
                clusters = cluster.hierarquicos("ward", numGrupos);
                jTextArea.append("\n================== Agrupamento Ligação Ward ==================\n");
                break;
            case "slsom":
                clusters = cluster.clusterungSLSOM(matrizU, marcadores);
                jTextArea.append("\n================== Agrupamento SL-SOM ==================\n");
                break;
            case "faino":
                clusters = cluster.faino();
                jTextArea.append("\n================== Agrupamento Componentes Conectados ==================\n");
                break;
        }
        FrameSomVisualization.getInstance().setClusters(clusters);
        setTextClustering(clusters, agrupamento);
    }

    public void setTextSOM() {
        jTextArea.setText("====================== Informações ======================\n");
        jTextArea.append("Base de Dados:\t" + dados.getNome() + "\n");
        jTextArea.append("Instâncias:\t" + dados.getDataSet().size() + "\n");
        jTextArea.append("Atributos:\t" + dados.getDataSet().get(0).getAtributos().size() + "\n");
        for (int i = 0; i < dados.getAtributos().size(); i++) {
            jTextArea.append("\t\t" + dados.getAtributos().get(i) + "\n");
        }
        jTextArea.append("\n=============== Self-Organizing Map - SOM ===============\n");
        jTextArea.append("Erro de Quantização: " + rede.getErroQuantização() + "\n");
        jTextArea.append("Erro Topológico: " + rede.getErroTopologico() + "\n");
    }

    public void setTextClustering(ArrayList<Cluster> clusters, String agrup) {
        AvaliacaoAgrupamento avaliacao = new AvaliacaoAgrupamento(clusters, dados.getClasses(), dados);
        ArrayList<Integer> grupo;

        //imprime em tela o agrupamento realizado
        String padrao;
        for (int i = 0; i < clusters.size(); i++) {
            
            clusters.get(i).setNomeGrupo(dados.getClasses());
            jTextArea.append("Grupo " + (i + 1) + ":");
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
                    jTextArea.append("\n");
                }
                jTextArea.append(padrao);
            }
            jTextArea.append("\n\n");
            clusters.get(i).centroids();
            jTextArea.append("Centróides: ");
            for (int j = 0; j < clusters.get(i).getCentroides().size(); j++) {
                jTextArea.append(clusters.get(i).getCentroides().get(j)+" ");
            }
            jTextArea.append("\n\n");
            // jTextArea.append("Centróide: " + avaliacao.centroide(clusters.get(i)) + "\n\n");
        }

        //Medidas de avaliaxao do agrupamento
        jTextArea.append("\n================ Avaliação do Agrupamento ===============\n");
        jTextArea.append("Grupos Formados:\t" + clusters.size() + "\n");
        jTextArea.append("Viariância Total:\t" + avaliacao.getVariancia() + "\n");
        jTextArea.append("Medida F:\t\t" + avaliacao.getMedidaF() + "\n");
        jTextArea.append("Medida R:\t\t" + avaliacao.getIndiceAleatorio() + "\n");
        jTextArea.append("Índice Dunn:\t\t" + avaliacao.getIndiceDunn() + "\n");

        double acertos = avaliacao.getAcerto();
        jTextArea.append("Porcentagem de Acerto:\t" + acertos + "%\n");

        jTextArea.append("\nMatriz Confusão:\n");
        int[][] mconfusao = avaliacao.getMconfusao();

        char classe = 'a';
        for (int i = 0; i < mconfusao[0].length; i++) {
            jTextArea.append(classe + "\t");
            ++classe;
        }
        jTextArea.append("\n");
        classe = 'a';
        for (int i = 0; i < mconfusao.length; i++) {
            for (int j = 0; j < mconfusao[0].length; j++) {
                jTextArea.append(mconfusao[i][j] + "\t");
                if (j == mconfusao[0].length - 1) {
                    jTextArea.append("\t" + classe + " = " + dados.getClasses().get(i));
                    ++classe;
                }
            }
            jTextArea.append("\n");
        }
        resutados.add(jTextArea.getText());

        //Adiciona na Lista de Resultados
        String metodo = null;
        switch (agrup) {
            case "densidade":
                metodo = "Densidade";
                break;
        }
        date = new Date();
        listaResultados.addElement(formatter.format(date) + " SOM - " + metodo);
        jListResultados.setModel(listaResultados);

    }

    public void atualizaStatus(final int i) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jProgressBar1.setValue(i);
                FrameSomVisualization.setStatus(i);
            }
        });
    }

    public void carregaBase() {
        dados = new Base();
        double base[][] = new double[Base.getInput().length][Base.getInput()[0].length]; //= arrayListBases.get(arrayListBases.size() - 1).getInput();
        for (int i = 0; i < base.length; i++) {
            for (int j = 0; j < base[0].length; j++) {
                base[i][j] = Double.valueOf(Base.getInput()[i][j] + "").doubleValue();
            }
        }
        padronizacao(base);

        int grupo = 0;
        for (int i = 0; i < base.length; i++) {
            Padrao p = new Padrao();
            p.setNumero(grupo);
            ++grupo;
            for (int j = 0; j < base[0].length; j++) {
                p.addAtributos(base[i][j]);

            }
            p.setClasse(Base.getOutput()[i].toString());
            dados.addDataSet(p);
        }

        for (int i = 0; i < Base.getAtributes().length; i++) {
            dados.addAtributos(Base.getAtributes()[i]);
        }

        dados.setClasses((List) Base.getClasses());
        dados.setNome((String) Base.getName());
        FrameSomVisualization.getInstance().setDados(dados);
        FrameSomVisualization.getInstance().setRede(null);
        FrameSomVisualization.getInstance().setMatrizU(null);
        FrameSomVisualization.getInstance().repaint();
    }

    public void padronizacao(double matriz[][]) {
        double menor = Double.MAX_VALUE;
        double maior = Double.MIN_VALUE;
        int cont = 0;
        while (cont < matriz[0].length) {
            for (int i = 0; i < matriz.length; i++) {
                if (matriz[i][cont] < menor) {
                    menor = matriz[i][cont];
                }

                if (matriz[i][cont] > maior) {
                    maior = matriz[i][cont];
                }
            }
            for (int i = 0; i < matriz.length; i++) {
                matriz[i][cont] -= menor;
            }
            maior -= menor;
            for (int i = 0; i < matriz.length; i++) {
                matriz[i][cont] /= maior;
            }
            cont++;
        }
    }

    public void addBase(interfaces.Base b) {
        Base = b;
        carregaBase();
    }

    public Base getDados() {
        return dados;
    }

    public static void setSleep(int sleep) {
        PanelSOM.sleep = sleep;
    }

    public static int[][] getMarcadores() {
        return marcadores;
    }

    public static void setMarcadores(int[][] marcadores) {
        PanelSOM.marcadores = marcadores;
    }

    public static void setNumGrupos(int numGrupos) {
        PanelSOM.numGrupos = numGrupos;
    }
}
