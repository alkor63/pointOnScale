package org.example;

import com.aspose.tex.PngMathRenderer;
import com.aspose.tex.PngMathRendererOptions;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FormulaImage extends JFrame {
    FormulaImage() {

        super();
        JTextPane jtp = new JTextPane();
        StyledDocument doc = jtp.getStyledDocument();
        // В этом примере кода показано, как отображать математические формулы и уравнения в формате PNG.
// Создание параметров рендеринга PNG
        PngMathRendererOptions options = new PngMathRendererOptions();

// Укажите разрешение изображения 150 dpi
        options.setResolution(75);


// Укажите коэффициент масштабирования 300% == options.setScale(3000)
        options.setScale(3000);

// Укажите цвет переднего плана.
        options.setTextColor(Color.BLACK);

// Укажите цвет фона.
        options.setBackgroundColor(Color.WHITE);

// Укажите выходной поток для файла журнала.
        options.setLogStream(new ByteArrayOutputStream());

// Укажите, показывать ли вывод терминала на консоли или нет.
        options.showTerminal(true);
        //
// Переменная, в которую будут записаны размеры результирующего изображения.
        com.aspose.tex.Size2D size = new com.aspose.tex.Size2D.Float();
        //
// Создайте выходной поток для изображения формулы.
        final OutputStream stream;
        try {
            stream = new FileOutputStream("sqrt(b2-4ac).png");
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //
//// Рендеринг как PNG
        PngMathRenderer mathRenderer = new PngMathRenderer();
        mathRenderer.render("\\begin{document}"
                + "\\sqrt{ b^2 - 4ac }"
                + "\\end{document}", stream, options, size);
////        mathRenderer.render("\\begin{document}"
//////                + "\\ x = \\frac{b+\\sqrt{b^2 - 4ac}} {2a} "
////                + "\\left[ \\ x = \\frac{{b+\\sqrt{b^2 - 4ac}}} {2a} \\]"
////                + "\\end{document}", stream, options, size);
    }

}
