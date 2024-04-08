package org.example;

import javax.swing.*;
import java.awt.*;

public class PointOnScale {

    private final int WIDTH = 400;      // ширина окна
    private final int HEIGHT = 400;     // высота окна

    private JPanel panel;
    private JFrame frame;

    private double inputX = -23.4;
    private double inputY = 98.5;

    public static void main(String[] args) {
        new PointOnScale();
    }

    private PointOnScale() {
        initPanel();
        initFrame();
    }

    public PointOnScale(double inputX, double inputY) {
        this.inputX = inputX;
        this.inputY = inputY;
        initPanel();
        initFrame();
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawScreen(g);
            }
        };
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private void initFrame() {
        frame = new JFrame("точка с координатами Х, У");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(390,350);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
    }

    private void drawScreen(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int w = panel.getWidth(), h = panel.getHeight();
        int zeroX = w >> 1;     // центральная точка (w >> n - это бинарный сдвиг или деление на 2 n раз)
        int zeroY = h >> 1;     // центральная точка
// x >> n - побитовый сдвиг вправо на n позиций = деление целого числа на 2 n раз с округлением в меньшую сторону
        final int step = 20; // шаг сетки координат
        for (int x = step; x <= w - step; x += step)
            g2d.drawLine(x, 0, x,  h);
        for (int y = step; y <= h - step; y += step)
            g2d.drawLine(0, y, w, y);
        /* оси координат */
//        g2d.setPaint(Color.blue);
        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(0, zeroY, w , zeroY);
        g2d.drawLine(zeroX, 0, zeroX, h );


        /* пометки */
        g2d.setPaint(Color.gray);

// шкала осей координат
        // Автомасштабирование !
        double pointX = (Math.round(inputX * 10)) / 10.0;        // округлили до 0.1
        double pointY = (Math.round(inputY * 10)) / 10.0;        // округлили до 0.1
        double maxN = Math.abs(pointX);
        if (Math.abs(pointY) > maxN) {
            maxN = Math.abs(pointY);
        }
        double myScale = 1.25 * maxN; //накинул 25%, чтоб отодвинуть точку от границы окна
        // для координатной сетки

        double mashtabeX = myScale/w*2;
        double mashtabeY = myScale/h*2;
        for(int x = 2*step; x < w/2; x += 2*step){
            int scaleX = (int) (x*mashtabeX);// чтоб цифры шкалы осей были целыми
            g2d.drawString(String.valueOf(scaleX), x +w/2- 10, zeroY + 13);
            g2d.drawString(String.valueOf(-scaleX), -x +w/2- 10, zeroY + 13);
        }
        for(int y = 2*step; y < h/2; y += 2*step){
            int scaleY = (int) (y*mashtabeX);// чтоб цифры шкалы осей были целыми
            g2d.drawString(String.valueOf(-scaleY), zeroX + 5, y +h/2 +5);
            g2d.drawString(String.valueOf(scaleY), zeroX + 5,  +h/2 -y +5);
        }
        g2d.drawString("0", zeroX - 10, zeroY + 13);
        g2d.drawString("x", w - 20, zeroY + 13);
        g2d.drawString("y", zeroX - 10, 20);

        /* точки */
        Font baseFont = new Font("Sans Serif", Font.BOLD, 12);
        g2d.setPaint(Color.red);
        g2d.setFont(baseFont);
        int intX = zeroX + (int) (pointX/mashtabeX);    // для позиционирования точки
        int intY = zeroY - (int) (pointY/mashtabeY);    // для позиционирования точки
        g2d.drawString("+("+ pointX+"; "+pointY+")", intX, intY);
    }
}
