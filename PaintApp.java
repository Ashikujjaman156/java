package paintapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PaintApp extends JFrame {

    private DrawArea drawArea = new DrawArea();
    private JButton clearButton = new JButton("Clear");

    public PaintApp() {
        setTitle("Simple Paint App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel with clear button only
        JPanel topPanel = new JPanel();
        topPanel.add(clearButton);

        add(topPanel, BorderLayout.NORTH);
        add(drawArea, BorderLayout.CENTER);

        clearButton.addActionListener(e -> drawArea.clear());

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PaintApp::new);
    }
}

class DrawArea extends JPanel {
    private ArrayList<Point> points = new ArrayList<>();

    public DrawArea() {
        setBackground(Color.WHITE);
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                points.add(e.getPoint());
                repaint();
            }
        });
    }

    public void clear() {
        points.clear();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (Point p : points) {
            g.fillOval(p.x, p.y, 6, 6);
        }
    }
}
