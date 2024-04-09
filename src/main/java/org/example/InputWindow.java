package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputWindow  extends JFrame {
    int a = 1;
    int b;
    int c;
    double result;
    double sqrtD;

    InputWindow() {

        super("Input a, b, c");
        setLocation(50,100);
        setSize(300,250);
        // Настраиваем первую горизонтальную панель (для ввода a)
        Box box1 = Box.createHorizontalBox();
        JLabel labelA = new JLabel("введите число a:");
        JTextField fieldA = new JTextField(5);
        box1.add(labelA);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(fieldA);
// Настраиваем вторую горизонтальную панель (для ввода b)
        Box box2 = Box.createHorizontalBox();
        JLabel labelB = new JLabel("введите число b:");
        JTextField fieldB = new JTextField(5);
        box2.add(labelB);
        box2.add(Box.createHorizontalStrut(6));
        box2.add(fieldB);
// Настраиваем 3-ю горизонтальную панель (для ввода c)
        Box box3 = Box.createHorizontalBox();
        JLabel labelC = new JLabel("введите число c:");
        JTextField fieldC = new JTextField(5);
        box3.add(labelC);
        box3.add(Box.createHorizontalStrut(6));
        box3.add(fieldC);
// Настраиваем 4-ю горизонтальную панель (с кнопками)
        Box box4 = Box.createHorizontalBox();
        JButton calculate = new JButton("расчет");
        box4.add(Box.createHorizontalGlue());
        box4.add(Box.createHorizontalStrut(12));
        box4.add(calculate);
// Уточняем размеры компонентов
// Размещаем три горизонтальные панели на одной вертикальной
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box3);
        mainBox.add(Box.createVerticalStrut(17));
        mainBox.add(box4);
        setContentPane(mainBox);
        setResizable(false);
        //Обработка ввода после нажатия кнопки "расчет"
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    a = Integer.parseInt(fieldA.getText());
                    b = Integer.parseInt(fieldB.getText());
                    c = Integer.parseInt(fieldC.getText());
                    int d = b * b - 4 * a * c;
                    if (a == 0){
                        JOptionPane.showMessageDialog(fieldA, "число a не может быть = 0", "ERROR (деление на 0)",JOptionPane.WARNING_MESSAGE);
                    }
                    else if (d >= 0) {
                        sqrtD = Math.sqrt(d);
                        result = (b + sqrtD) / (2 * a);
                        JFrame widowReport = new ReportWindow(a,b,c,sqrtD,result);
                        widowReport.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(fieldA,
                                "С таким набором А,В,С вылетаем в комплексные числа",
                                "! попытка извлечь корень квадратный из отрицательного числа !",JOptionPane.WARNING_MESSAGE);
                    }} catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(fieldA, "числа А,В,С должны быть целыми !");
                }
            }
        });
    }
}
