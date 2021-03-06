/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.som.visualization;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashMap;

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
    private static boolean grayScale = false;
    public static ArrayList<Polygon> p = new ArrayList<>();
    private double matrizU[][] = null;
    private double max;
    private HashMap<Integer, Integer> paleta = new HashMap<>();

    /**
     * Creates new form Draw2D
     */
    public MatrizU2D() {
        initComponents();
        matrizU = FrameSomVisualization.getMatrizU();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (FrameSomVisualization.getInstance().getMatrizU() != null) {

            matrizU = FrameSomVisualization.getMatrizU();
            calcPaleta();
            gridMUX = FrameSomVisualization.getGridMUX();
            gridMUY = FrameSomVisualization.getGridMUY();
            calcIntervalo();

            mCellMetrics = new HexGrid(FrameSomVisualization.getValueU2D());
            for (int i = 0; i < gridMUX; i++) {
                for (int j = 0; j < gridMUY; j++) {
                    mCellMetrics.setCellIndex(i, j);
                    mCellMetrics.computeCorners(mCornersX, mCornersY);
                    g.setColor(getColor(i, j));
//                    int value = (int) (((matrizU[i][j]) * 255) / max);
//                    System.out.println(value);
//                    value -= 255;
//                    value = Math.abs(value);
//                    double color = hsvToRgb(value+10, 100, 100);
//                    float h = 2;
//                    float s = (float) 10;
//                    float v = (float) 10;
//                    g.setColor(new Color((int)(color)));
                    g.drawPolygon(mCornersX, mCornersY, NUM_HEX_CORNERS);
                    g.fillPolygon(mCornersX, mCornersY, NUM_HEX_CORNERS);
                }
            }

            if (!p.isEmpty()) {
                g.setColor(Color.BLACK);
                for (int i = 0; i < p.size(); i++) {
                    g.fillPolygon(p.get(i));
                    g.drawPolygon(p.get(i));
                }
            }
        }
    }

    public static void setGrayScale(boolean grayScale) {
        MatrizU2D.grayScale = grayScale;
    }

    public static void addP(Polygon p) {
        MatrizU2D.p.add(p);
    }

    public static ArrayList<Polygon> getP() {
        return p;
    }

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

    public void calcPaleta() {
        if (matrizU != null) {
            max = Double.MIN_VALUE;
            for (int i = 0; i < matrizU.length; i++) {
                for (int j = 0; j < matrizU[0].length; j++) {
                    if (matrizU[i][j] > max) {
                        max = matrizU[i][j];
                    }
                }
            }
          
        }
    }

    public void calcIntervalo() {
        double max = -99999999;
        for (int i = 0; i < gridMUX; i++) {
            for (int j = 0; j < gridMUY; j++) {
                if (FrameSomVisualization.getMatrizU()[i][j] > max) {
                    max = FrameSomVisualization.getMatrizU()[i][j];
                }
            }
        }
        double min = 99999999;
        for (int i = 0; i < gridMUX; i++) {
            for (int j = 0; j < gridMUY; j++) {
                if (FrameSomVisualization.getMatrizU()[i][j] < min) {
                    min = FrameSomVisualization.getMatrizU()[i][j];
                }
            }
        }
        intervalo = (max - min) / 10;
    }

    public Color getColor(int i, int j) {
        Color cor = null;
        int rgb;
        if ((FrameSomVisualization.getMatrizU()[i][j] > 0) && (FrameSomVisualization.getMatrizU()[i][j] <= intervalo)) { //Cor 1
            if (grayScale) {
                rgb = (int) ((25 * 0.299) + (25 * 0.587) + (112 * 0.114));
                rgb = getRGB(rgb, rgb, rgb);
                cor = new Color(rgb);
            } else {
                cor = new Color(25, 25, 112);
            }
        } else if ((FrameSomVisualization.getMatrizU()[i][j] > intervalo) && (FrameSomVisualization.getMatrizU()[i][j] <= intervalo * 2)) { //Cor 2
            if (grayScale) {
                rgb = (int) ((0 * 0.299) + (0 * 0.587) + (205 * 0.114));
                rgb = getRGB(rgb, rgb, rgb);
                cor = new Color(rgb);
            } else {
                cor = new Color(0, 0, 205);
            }
        } else if ((FrameSomVisualization.getMatrizU()[i][j] > intervalo * 2) && (FrameSomVisualization.getMatrizU()[i][j] <= intervalo * 3)) { //Cor 3

            if (grayScale) {
                rgb = (int) ((0 * 0.299) + (191 * 0.587) + (255 * 0.114));
                rgb = getRGB(rgb, rgb, rgb);
                cor = new Color(rgb);
            } else {
                cor = new Color(0, 191, 255);
            }
        } else if ((FrameSomVisualization.getMatrizU()[i][j] > intervalo * 3) && (FrameSomVisualization.getMatrizU()[i][j] <= intervalo * 4)) { //Cor 4

            if (grayScale) {
                rgb = (int) ((64 * 0.299) + (224 * 0.587) + (208 * 0.114));
                rgb = getRGB(rgb, rgb, rgb);
                cor = new Color(rgb);
            } else {
                cor = new Color(64, 224, 208);
            }
        } else if ((FrameSomVisualization.getMatrizU()[i][j] > intervalo * 4) && (FrameSomVisualization.getMatrizU()[i][j] <= intervalo * 5)) { //Cor 5

            if (grayScale) {
                rgb = (int) ((0 * 0.299) + (255 * 0.587) + (127 * 0.114));
                rgb = getRGB(rgb, rgb, rgb);
                cor = new Color(rgb);
            } else {
                cor = new Color(0, 255, 127);
            }
        } else if ((FrameSomVisualization.getMatrizU()[i][j] > intervalo * 5) && (FrameSomVisualization.getMatrizU()[i][j] <= intervalo * 6)) { //Cor 6

            if (grayScale) {
                rgb = (int) ((154 * 0.299) + (205 * 0.587) + (50 * 0.114));
                rgb = getRGB(rgb, rgb, rgb);
                cor = new Color(rgb);
            } else {
                cor = new Color(154, 205, 50);
            }
        } else if ((FrameSomVisualization.getMatrizU()[i][j] > intervalo * 6) && (FrameSomVisualization.getMatrizU()[i][j] <= intervalo * 7)) { //Cor 7

            if (grayScale) {
                rgb = (int) ((255 * 0.299) + (255 * 0.587) + (0 * 0.114));
                rgb = getRGB(rgb, rgb, rgb);
                cor = new Color(rgb);
            } else {
                cor = new Color(255, 255, 0);
            }
        } else if ((FrameSomVisualization.getMatrizU()[i][j] > intervalo * 7) && (FrameSomVisualization.getMatrizU()[i][j] <= intervalo * 8)) { //Cor 8

            if (grayScale) {
                rgb = (int) ((255 * 0.299) + (165 * 0.587) + (0 * 0.114));
                rgb = getRGB(rgb, rgb, rgb);
                cor = new Color(rgb);
            } else {
                cor = new Color(255, 165, 0);
            }
        } else if ((FrameSomVisualization.getMatrizU()[i][j] > intervalo * 8) && (FrameSomVisualization.getMatrizU()[i][j] <= intervalo * 9)) { //Cor 9

            if (grayScale) {
                rgb = (int) ((255 * 0.299) + (127 * 0.587) + (0 * 0.114));
                rgb = getRGB(rgb, rgb, rgb);
                cor = new Color(rgb);
            } else {
                cor = new Color(255, 127, 0);
            }
        } else if ((FrameSomVisualization.getMatrizU()[i][j] > intervalo * 9) && (FrameSomVisualization.getMatrizU()[i][j] <= intervalo * 10)) { //Cor 10
            if (grayScale) {
                rgb = (int) ((255 * 0.299) + (0 * 0.587) + (0 * 0.114));
                rgb = getRGB(rgb, rgb, rgb);
                cor = new Color(rgb);
            } else {
                cor = new Color(255, 0, 0);
            }
        }
        return cor;
    }

    public static int getRGB(int r, int g, int b) {
        return 65536 * r + 256 * g + b;
    }

    public double hsvToRgb(float h, float s, float v) {
        double r, g, b;
        int i;
        double f, p, q, t;

        // Make sure our arguments stay in-range
        h = Math.max(0, Math.min(360, h));
        s = Math.max(0, Math.min(100, s));
        v = Math.max(0, Math.min(100, v));

        // We accept saturation and value arguments from 0 to 100 because that's
        // how Photoshop represents those values. Internally, however, the
        // saturation and value are calculated from a range of 0 to 1. We make
        // That conversion here.
        s /= 100;
        v /= 100;

        if (s == 0) {
            // Achromatic (grey)
            r = g = b = v;
            return 65536 * (Math.round(r * 255)) + 256 * (Math.round(g * 255)) + (Math.round(b * 255));
        }

        h /= 60; // sector 0 to 5
        i = (int) Math.floor(h);
        f = h - i; // factorial part of h
        p = v * (1 - s);
        q = v * (1 - s * f);
        t = v * (1 - s * (1 - f));

        switch (i) {
            case 0:
                r = v;
                g = t;
                b = p;
                break;

            case 1:
                r = q;
                g = v;
                b = p;
                break;

            case 2:
                r = p;
                g = v;
                b = t;
                break;

            case 3:
                r = p;
                g = q;
                b = v;
                break;

            case 4:
                r = t;
                g = p;
                b = v;
                break;

            default: // case 5:
                r = v;
                g = p;
                b = q;
        }
        return 65536 * (Math.round(r * 255)) + 256 * (Math.round(g * 255)) + (Math.round(b * 255));
    }
}
