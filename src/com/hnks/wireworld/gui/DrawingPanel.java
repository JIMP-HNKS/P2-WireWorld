package com.hnks.wireworld.gui;

import com.hnks.wireworld.automaton.AutomatonCell;

import java.awt.*;
import java.awt.Point;
import javax.swing.*;
import java.awt.event.*;

public class DrawingPanel extends JPanel {
    MouseHandler mouseHandler = new MouseHandler();
    Point p1 = new Point(0, 0);
    boolean drawing;

    AppState state;
    protected DrawingOption option = DrawingOption.ERASE;


    public DrawingPanel(AppState state){
        this.setPreferredSize(new Dimension(640, 400));
        this.addMouseListener(mouseHandler);

        this.state = state;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < 80; x++) {
            for (int y = 0; y < 50; y++) {
                AutomatonCell cell = state.getSim().getCell( x, y );

                switch(cell){
                    case HEAD:
                        g.setColor(Color.RED);
                        g.fillRect( x * 8, y * 8 , 8, 8 );
                        break;
                    case TAIL:
                        g.setColor(Color.GREEN);
                        g.fillRect( x * 8, y * 8, 8, 8 );
                        break;
                    case CABLE:
                        g.setColor(Color.WHITE);
                        g.fillRect( x * 8 , y * 8, 8, 8 );
                        break;
                    case BLANK:
                        g.setColor(Color.BLACK);
                        g.fillRect( x * 8 , y * 8, 8, 8 );
                        break;
                }
            }
        }

    }

    private class MouseHandler extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            drawing = true;
            p1 = e.getPoint();

            switch( option ){
                case ERASE:
                    state.getSim().setCell( AutomatonCell.BLANK,p1.x/8, p1.y/8 );
                    break;

                case HEAD:
                    state.getSim().setCell( AutomatonCell.HEAD,p1.x/8, p1.y/8 );
                    break;

                case TAIL:
                    state.getSim().setCell( AutomatonCell.TAIL,p1.x/8, p1.y/8 );
                    break;

                case CABLE:
                    state.getSim().setCell( AutomatonCell.CABLE,p1.x/8, p1.y/8 );
                    break;
            }

            repaint();
            // rysuj no
        }

        public void mouseDragged(MouseEvent e) {
            drawing = true;
            p1 = e.getPoint();

            switch( option ){
                case ERASE:
                    state.getSim().setCell( AutomatonCell.BLANK,p1.x/8, p1.y/8 );
                    break;

                case HEAD:
                    state.getSim().setCell( AutomatonCell.HEAD,p1.x/8, p1.y/8 );
                    break;

                case TAIL:
                    state.getSim().setCell( AutomatonCell.TAIL,p1.x/8, p1.y/8 );
                    break;

                case CABLE:
                    state.getSim().setCell( AutomatonCell.CABLE,p1.x/8, p1.y/8 );
                    break;
            }

            repaint();
        }

    }


}
