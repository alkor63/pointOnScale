    package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.round;

    public class ReportWindow extends JFrame {
        int a;
        int b;
        int c;
        double sqrtD;
        double result;

        public ReportWindow(int a, int b, int c, double sqrtD, double result) throws HeadlessException {
            super("отчёт");
            this.a = a;
            this.b = b;
            this.c = c;
            this.sqrtD = sqrtD;
            this.result = result;

            double resultOut = (Math.round(result * 100)) / 100.0;
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocation(50,350);
            setSize(350,250);
            // Настраиваем первую горизонтальную панель (для ввода a)
            Box box1 = Box.createHorizontalBox();
            JLabel labelA = new JLabel(" a = " + a);
            box1.add(labelA);
            // Настраиваем вторую горизонтальную панель (для ввода b)
            Box box2 = Box.createHorizontalBox();
            JLabel labelB = new JLabel(" b = " + b);
            box2.add(labelB);

            // Настраиваем 3-ю горизонтальную панель (для ввода c)
            Box box3 = Box.createHorizontalBox();
            JLabel labelC = new JLabel(" c = " + c);
            box3.add(labelC);
            // Настраиваем 4-ю горизонтальную панель ()
            Box box4 = Box.createHorizontalBox();

            double d = (Math.round(sqrtD * 10)) / 10.0;
            // округлили до 0.1
            ImageIcon imageIcon = new ImageIcon("sqrt(b2-4ac).png");
            JLabel labelSqrt = new JLabel(imageIcon);
            JLabel labelD = new JLabel(" = "+d);
            box4.add(Box.createHorizontalGlue());
            box4.add(Box.createHorizontalStrut(12));
            box4.add(labelSqrt);
            box4.add(labelD);
            // Настраиваем 5-ю горизонтальную панель (для x)
            Box box5 = Box.createHorizontalBox();
            JLabel labelX = new JLabel(" x = " + resultOut);
            box5.add(labelX);
            // Настраиваем 6-ю горизонтальную панель (для x)

            Box box6 = Box.createHorizontalBox();
            JLabel labelY =  new JLabel("введите число Y:");
            JTextField fieldY = new JTextField(5);
            box6.add(labelY);
            box6.add(Box.createHorizontalStrut(6));
            box6.add(fieldY);
// Настраиваем 4-ю горизонтальную панель (с кнопками)
            JButton drawPoint = new JButton("отобразить точку");
            box6.add(Box.createHorizontalGlue());
            box6.add(Box.createHorizontalStrut(12));
            box6.add(drawPoint);
            // Уточняем размеры компонентов
// Размещаем три горизонтальные панели на одной вертикальной
            Box mainBox = Box.createVerticalBox();
            mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
            mainBox.add(box1);
            mainBox.add(Box.createVerticalStrut(12));
            mainBox.add(box2);
            mainBox.add(Box.createVerticalStrut(12));
            mainBox.add(box3);
            mainBox.add(Box.createVerticalStrut(12));
            mainBox.add(box4);
            mainBox.add(Box.createVerticalStrut(17));
            mainBox.add(box5);
            mainBox.add(Box.createVerticalStrut(17));
            mainBox.add(box6);
            setContentPane(mainBox);

            drawPoint.addActionListener(new ActionListener() {
                double inpY;
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        inpY = Double.parseDouble(fieldY.getText());
                        new PointOnScale(result, inpY);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(fieldY, "ошибка ввода числа Y");
                    }
                }
            });
        }
    }

