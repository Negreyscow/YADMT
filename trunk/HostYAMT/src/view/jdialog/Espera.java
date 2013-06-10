/*
 * Espera.java
 *
 * Created on 18 de Outubro de 2008, 08:10
 */
package view.jdialog;

import interfaces.util.WaitingDialogInterface;

/**
 *
 * @author  Rafael
 */
public class Espera extends javax.swing.JDialog implements WaitingDialogInterface {

    private static Espera espera;

    /** Creates new form Espera */
    public Espera(java.awt.Frame parent, boolean modal, String mensagem) {
        super(parent, modal);
        initComponents();
        jLabel1.setText(mensagem);
    //jProgressBar1.setMaximum(0);
    //FacadeSwing.setEspera(this);
    }

//    public static Espera getEspera() {
//        return espera;
//    }
//
//    public void setEspera(Espera e) {
//        espera = e;
//    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 12));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 6);
        getContentPane().add(jLabel1, gridBagConstraints);

        jProgressBar1.setFont(new java.awt.Font("Calibri", 0, 12));
        jProgressBar1.setMaximum(0);
        jProgressBar1.setString("0");
        jProgressBar1.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        getContentPane().add(jProgressBar1, gridBagConstraints);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-328)/2, (screenSize.height-90)/2, 328, 90);
    }// </editor-fold>//GEN-END:initComponents
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String mensagem) {
//    //Espera e = ;
//    //java.awt.EventQueue.invokeLater(new Espera(new javax.swing.JFrame(), true, mensagem));
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
    //

    /*
     * process starts
     */
    public void begin(int nMaximo) {
        if (nMaximo > 0) {
            jProgressBar1.setIndeterminate(false);
            jProgressBar1.setMaximum(jProgressBar1.getMaximum() + nMaximo);
            jProgressBar1.setMinimum(0);
        } else {
            jProgressBar1.setIndeterminate(true);
        }
    }

    /**
     * close window
     */
    public void end() {
        this.dispose();
    }

    /**
     * shows the process
     * @param valor
     */
    public void processing(int valor) {
        if (jProgressBar1.isIndeterminate()) {
            jProgressBar1.setString("Colunas adicionadas: " + valor);
        } else {
            jProgressBar1.setValue(valor);
        }
    }

    public void visible() {
        setVisible(true);
    }
}