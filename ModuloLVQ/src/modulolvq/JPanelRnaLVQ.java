/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JPanelRnaLVQ.java
 *
 * Created on 17/09/2010, 11:32:01
 */

package modulolvq;

/**
 *
 * @author Fernando
 */
public class JPanelRnaLVQ extends javax.swing.JPanel {

    /** Creates new form JPanelRnaLVQ */
    public JPanelRnaLVQ() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabelNroEpocas = new javax.swing.JLabel();
        jLabelTxAprendizado = new javax.swing.JLabel();
        jButtonConcluir = new javax.swing.JButton();
        jFormattedTextFieldEpocas = new javax.swing.JFormattedTextField();
        jFormattedTextFieldTxAprendizado = new javax.swing.JFormattedTextField();
        jLabelInicializacaoPesos = new javax.swing.JLabel();
        jLabelReducao = new javax.swing.JLabel();
        jLabelNroNeuronios = new javax.swing.JLabel();
        jComboBoxInicializacaoPesos = new javax.swing.JComboBox();
        jTextFieldNroNeuronios = new javax.swing.JTextField();
        jFormattedTextFieldTxReducao = new javax.swing.JFormattedTextField();

        setLayout(new java.awt.GridBagLayout());

        jLabelNroEpocas.setText("Nº de Épocas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jLabelNroEpocas, gridBagConstraints);

        jLabelTxAprendizado.setText("Taxa de Aprendizado");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jLabelTxAprendizado, gridBagConstraints);

        jButtonConcluir.setText("Concluir");
        jButtonConcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConcluirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jButtonConcluir, gridBagConstraints);

        jFormattedTextFieldEpocas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jFormattedTextFieldEpocas.setText(Integer.toString(modulolvq.FacadeRnaLVQ.getEpocas()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jFormattedTextFieldEpocas, gridBagConstraints);

        jFormattedTextFieldTxAprendizado.setText(Double.toString(modulolvq.FacadeRnaLVQ.getTxAprendizado()));
        jFormattedTextFieldTxAprendizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldTxAprendizadoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jFormattedTextFieldTxAprendizado, gridBagConstraints);

        jLabelInicializacaoPesos.setText("Inicialização dos pesos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jLabelInicializacaoPesos, gridBagConstraints);

        jLabelReducao.setText("Taxa de Redução Aprendizado");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jLabelReducao, gridBagConstraints);

        jLabelNroNeuronios.setText("Nº Neurônios da Camada Oculta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jLabelNroNeuronios, gridBagConstraints);

        jComboBoxInicializacaoPesos.setModel(new javax.swing.DefaultComboBoxModel(modulolvq.FacadeRnaLVQ.getInicializacoesPeso()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jComboBoxInicializacaoPesos, gridBagConstraints);

        jTextFieldNroNeuronios.setText(Integer.toString(modulolvq.FacadeRnaLVQ.getTamanhoCamadaOculta()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jTextFieldNroNeuronios, gridBagConstraints);

        jFormattedTextFieldTxReducao.setText(Double.toString(modulolvq.FacadeRnaLVQ.getTxReducao()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jFormattedTextFieldTxReducao, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConcluirActionPerformed
        FacadeRnaLVQ.setTamanhoCamadaOculta(Integer.parseInt(jTextFieldNroNeuronios.getText()));
        FacadeRnaLVQ.setInicializacaoPeso((String)jComboBoxInicializacaoPesos.getSelectedItem());
        FacadeRnaLVQ.setEpocas(Integer.parseInt(jFormattedTextFieldEpocas.getText()));
        FacadeRnaLVQ.setTxAprendizado(Double.parseDouble(jFormattedTextFieldTxAprendizado.getText()));
        FacadeRnaLVQ.setTxReducao(Double.parseDouble(jFormattedTextFieldTxReducao.getText()));        
        
        FacadeRnaLVQ.getJDialogConfig().dispose();
    }//GEN-LAST:event_jButtonConcluirActionPerformed

    private void jFormattedTextFieldTxAprendizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTxAprendizadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldTxAprendizadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConcluir;
    private javax.swing.JComboBox jComboBoxInicializacaoPesos;
    private javax.swing.JFormattedTextField jFormattedTextFieldEpocas;
    private javax.swing.JFormattedTextField jFormattedTextFieldTxAprendizado;
    private javax.swing.JFormattedTextField jFormattedTextFieldTxReducao;
    private javax.swing.JLabel jLabelInicializacaoPesos;
    private javax.swing.JLabel jLabelNroEpocas;
    private javax.swing.JLabel jLabelNroNeuronios;
    private javax.swing.JLabel jLabelReducao;
    private javax.swing.JLabel jLabelTxAprendizado;
    private javax.swing.JTextField jTextFieldNroNeuronios;
    // End of variables declaration//GEN-END:variables

}
