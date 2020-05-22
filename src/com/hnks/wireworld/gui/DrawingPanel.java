package com.hnks.wireworld.gui;

import com.hnks.wireworld.automaton.AutomatonCell;

import java.awt.*;
import java.awt.Point;
import javax.swing.*;
import java.awt.event.*;

public class DrawingPanel extends JPanel {
    MouseHandler mouseHandler = new MouseHandler();
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    boolean drawing;

    AppState state;

    public DrawingPanel(AppState state){
        this.setPreferredSize(new Dimension(640, 400));
        this.addMouseListener(mouseHandler);

        this.state = state;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < 80; x++) {
            for (int y = 0; y < 50; y++) {
                AutomatonCell cell = state.getSim().getCell(x, y);

                g.setColor(Color.BLACK);

                g.fillRect(x * 8, y * 8, 8, 8);
            }
        }

    }

    private class MouseHandler extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            drawing = true;
            p1 = e.getPoint();
            p2 = p1;


            repaint();
            // rysuj no
        }

        public void mouseDragged(MouseEvent e) {
            // rysuj no
            repaint();
        }
    }


}
