/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.view.frames;

import java.awt.Cursor;
import java.awt.Point;

/**
 *
 * @author Mateus
 */
public class JFrameSobreACO extends javax.swing.JFrame {

    /**
     * Creates new form SobreACO
     */
    public JFrameSobreACO(Point _p, int l) {
        initComponents();

        setName("Colônia de Formigas");
        setLocation(_p.x + l + 20, _p.y);
        this.setSize(520, this.getHeight());

        jTextArea2.setText("A capacidade mais reconhecida das formigas é a do\n"
                + "trabalho em grupo para realizar uma tarefa que\n"
                + "não poderia ser realizada somente por um indivíduo,\n"
                + "tal que os resultados de uma tarefa realizada por\n"
                + "um grupo são maiores que os resultados de uma\n"
                + "tarefa realizada por um único indivíduo.\n"
                + "\n"
                + "\n"
                + "O Algoritmo  ilustra o processo de Agrupamento baseado\n"
                + "em Colônia de Formigas. Inicialmente são espalhados os\n"
                + "padrões da base de dados na grade, após cada formiga\n"
                + "carrega um destes padrões de forma aleatória. Para o\n"
                + "laço de repetição tem-se que cada formiga irá andar\n"
                + "na grade fazendo as decisões de carregar ou descarregar\n"
                + "um padrão em um determinado local, tudo isto de maneira\n"
                + "isolada a outra formiga. O algoritmo terminará quando\n"
                + "um dado número de iterações for atingido.\n"
                + "\n"
                + "\n"
                + "Parâmetros:\n"
                + "\n"
                + "- Função Vizinhança: determina o raio de percepção da\n"
                + "vizinhança a ser analisada.\n"
                + "\n"
                + "- Vizinhança Máxima/Mínima: determina o raio máximo/mínimo.\n"
                + "\n"
                + "- Controle de Vizinhança:  determina a porcentagem de\n "
                + "padrões na grade classificados como semelhantes.\n"
                + "\n"
                + "- Semelhança Máxima/Mínima: determina a porcentagem\n"
                + "máxima/mínima de padrões na grade classificados como\n"
                + "semelhantes.\n"
                + "\n"
                + "- Fase (%): determina a porcentagem das iterações em que\n"
                + "os parâmetros função vizinhança e controle de vizinhança\n"
                + "irão crescer e posteriormente decrescer.\n");
        jTextArea2.setEditable(false);
        jTextArea2.setCaretPosition(0);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
