/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.kohonen.visualization;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 *
 * @author Thiago
 */
public final class MatrizU2D extends javax.swing.JPanel {

    private int NUM_HEX_CORNERS = 6;
    private int[] mCornersX = new int[NUM_HEX_CORNERS];
    private int[] mCornersY = new int[NUM_HEX_CORNERS];
    private HexGrid mCellMetrics;
    private int gridMUX;
    private int gridMUY;
    double intervalo;

    /**
     * Creates new form Draw2D
     */
    public MatrizU2D() {
        initComponents();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (FrameVisualization.getInstance().getMatrizU() != null) {
            gridMUX = FrameVisualization.getGridMUX();
            gridMUY = FrameVisualization.getGridMUY();
            calcIntervalo();

            ArrayList<Polygon> poligonos = new ArrayList<>();
            mCellMetrics = new HexGrid(FrameVisualization.getValueU2D());

            for (int i = 0; i < gridMUX; i++) {
                for (int j = 0; j < gridMUY; j++) {
                    mCellMetrics.setCellIndex(i, j);
                    mCellMetrics.computeCorners(mCornersX, mCornersY);
                    g.setColor(getColor(i, j));
                    g.drawPolygon(mCornersY, mCornersX, NUM_HEX_CORNERS);
                    g.fillPolygon(mCornersY, mCornersX, NUM_HEX_CORNERS);
                    poligonos.add(new Polygon(mCornersY, mCornersX, NUM_HEX_CORNERS));
                }
            }
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void calcIntervalo() {
        double max = -99999999;
        for (int i = 0; i < gridMUX; i++) {
            for (int j = 0; j < gridMUY; j++) {
                if (FrameVisualization.getMatrizU()[i][j] > max) {
                    max = FrameVisualization.getMatrizU()[i][j];
                }
            }
        }
        double min = 99999999;
        for (int i = 0; i < gridMUX; i++) {
            for (int j = 0; j < gridMUY; j++) {
                if (FrameVisualization.getMatrizU()[i][j] < min) {
                    min = FrameVisualization.getMatrizU()[i][j];
                }
            }
        }
        intervalo = (max - min) / 10;
    }

    public Color getColor(int i, int j) {
        Color cor = null;
        if ((FrameVisualization.getMatrizU()[i][j] > 0) && (FrameVisualization.getMatrizU()[i][j] <= intervalo)) { //Cor 1
            cor = new Color(25, 25, 112);
        } else if ((FrameVisualization.getMatrizU()[i][j] > intervalo) && (FrameVisualization.getMatrizU()[i][j] <= intervalo * 2)) { //Cor 2
            cor = new Color(0, 0, 205);
        } else if ((FrameVisualization.getMatrizU()[i][j] > intervalo * 2) && (FrameVisualization.getMatrizU()[i][j] <= intervalo * 3)) { //Cor 3
            cor = new Color(0, 191, 255);
        } else if ((FrameVisualization.getMatrizU()[i][j] > intervalo * 3) && (FrameVisualization.getMatrizU()[i][j] <= intervalo * 4)) { //Cor 4
            cor = new Color(64, 224, 208);
        } else if ((FrameVisualization.getMatrizU()[i][j] > intervalo * 4) && (FrameVisualization.getMatrizU()[i][j] <= intervalo * 5)) { //Cor 5
            cor = new Color(0, 255, 127);
        } else if ((FrameVisualization.getMatrizU()[i][j] > intervalo * 5) && (FrameVisualization.getMatrizU()[i][j] <= intervalo * 6)) { //Cor 6
            cor = new Color(154, 205, 50);
        } else if ((FrameVisualization.getMatrizU()[i][j] > intervalo * 6) && (FrameVisualization.getMatrizU()[i][j] <= intervalo * 7)) { //Cor 7
            cor = new Color(255, 255, 0);
        } else if ((FrameVisualization.getMatrizU()[i][j] > intervalo * 7) && (FrameVisualization.getMatrizU()[i][j] <= intervalo * 8)) { //Cor 8
            cor = new Color(255, 165, 0);
        } else if ((FrameVisualization.getMatrizU()[i][j] > intervalo * 8) && (FrameVisualization.getMatrizU()[i][j] <= intervalo * 9)) { //Cor 9
            cor = new Color(255, 127, 0);
        } else if ((FrameVisualization.getMatrizU()[i][j] > intervalo * 9) && (FrameVisualization.getMatrizU()[i][j] <= intervalo * 10)) { //Cor 10
            cor = new Color(255, 0, 0);
        }
        return cor;
    }
}
