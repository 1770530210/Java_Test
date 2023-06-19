package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class DrawTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new DrawFrame();
            frame.setTitle("DrawTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class DrawFrame extends JFrame {
    public DrawFrame() throws HeadlessException {
        add(new DrawComponent());
        pack();
    }
}

class DrawComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintComponents(g);
    }

    @Override
    public void paintComponents(Graphics g) {
        var g2 = (Graphics2D) g;
//      画矩
        double leftX = 100;
        double topY = 100;
        double width = 200;
        double height = 150;

        var rect = new Rectangle2D.Double(leftX, topY, width, height);
        g2.draw(rect);
//      画椭圆
        var ellipse = new Ellipse2D.Double();
        ellipse.setFrame(rect);
        g2.draw(rect);
//      画对角线
        g2.draw(new Line2D.Double(leftX, topY, leftX + width, topY + height));
//      画圆
        double centerX = rect.getCenterX();
        double centerY = rect.getCenterY();
        double radius = 150;
        var circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY + radius);
        g2.draw(circle);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}