/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.visualization;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Mateus
 */
public class CoordenadasParalelas extends javax.swing.JPanel {

    /**
     * Creates new form CoordenadasParalelas
     */
    private static Color[] coresAux = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.BLACK};
    private static Color[] cores;

    public CoordenadasParalelas() {
        initComponents();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = TecnicasDispersao.getFundoParalelas().getWidth();
        int height = TecnicasDispersao.getFundoParalelas().getHeight();
        iniciaVetorCores();
        int inicioX = 50;
        int inicioY = 50;
        int numEixos = TecnicasDispersao.getMatrizDados().getDataSet().get(0).getAtributos().size();
        int distanciaEixos = TecnicasDispersao.getDistanciaEixos();
        int fimY = height - 50;
        ArrayList<Ponto> pontos = new ArrayList<>();
        int[] coordX = new int[numEixos];
        for (int i = 0; i < numEixos; i++) {
            g.drawLine(inicioX, inicioY, inicioX, fimY);
//            System.out.println(inicioX);
            coordX[i] = inicioX;
            inicioX += distanciaEixos;
        }
        for (int i = 0; i < TecnicasDispersao.getMatrizDados().getDataSet().size(); i++) {
            int[] coordY = new int[TecnicasDispersao.getMatrizDados().getDataSet().get(i).getAtributos().size()];
            for (int j = 0; j < TecnicasDispersao.getMatrizDados().getDataSet().get(i).getAtributos().size(); j++) {
                int y = (height - 70) - (int) (TecnicasDispersao.getMatrizDados().getDataSet().get(i).getAtributos().get((j)) * (height - 120));
                coordY[j] = y;
            }
            for (int l = 0; l < TecnicasDispersao.getMatrizDados().getClasses().size(); l++) {
                String classePadrao = TecnicasDispersao.getMatrizDados().getDataSet().get(i).getClasse();
                String classeMomento = TecnicasDispersao.getMatrizDados().getClasses().get(l);
                if (classeMomento.equals(classePadrao)) {
                    g.setColor(cores[l]);
                }
            }
            Ponto p = new Ponto(TecnicasDispersao.getMatrizDados().getDataSet().get(i).getNumero() + "", coordX, coordY, Color.black);
            pontos.add(p);
            int expessuraLinha = TecnicasDispersao.getExpessuraLinha();
            for (int k = 0; k < expessuraLinha; k++) {
                for (int j = 0; j < coordY.length - 1; j++) {
                    g.drawLine(coordX[j], coordY[j]+k, coordX[j + 1], coordY[j + 1]+k);
                    Color aux = g.getColor();
                    if (TecnicasDispersao.isDesenharPontos()) {
                        g.setColor(Color.black);
                        g.fillRect(coordX[j] - 2, coordY[j] - 2, 4, 4);
                        g.fillRect(coordX[j + 1] - 2, coordY[j + 1] - 2, 4, 4);
                    }
                    g.setColor(aux);
                }

            }
        }
        TecnicasDispersao.setPontosCoordParalelas(pontos);
        if (TecnicasDispersao.isPintarPontoParalela()) {
            int expessuraLinha = TecnicasDispersao.getExpessuraLinha() + 2;
//            System.out.println("entrou aqui no panel");
            g.setColor(Color.black);
            for (int k = 0; k < expessuraLinha; k++) {
                for (int i = 0; i < TecnicasDispersao.getPontosPintarParalela().size(); i++) {
                    for (int j = 0; j < TecnicasDispersao.getPontosPintarParalela().get(i).getYs().length - 1; j++) {
                        g.drawLine(TecnicasDispersao.getPontosPintarParalela().get(i).getXs()[j], TecnicasDispersao.getPontosPintarParalela().get(i).getYs()[j]+k, TecnicasDispersao.getPontosPintarParalela().get(i).getXs()[j + 1], TecnicasDispersao.getPontosPintarParalela().get(i).getYs()[j + 1]+k);
                    }
                }

            }
        }
    }

    private void iniciaVetorCores() {
        cores = new Color[TecnicasDispersao.getMatrizDados().getClasses().size()];
        for (int i = 0; i < cores.length; i++) {
            cores[i] = coresAux[i];
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder("Coordenadas Paralelas"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
