package com.hnks.wireworld;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.*;

import javax.swing.*;

public class WireWorldFrame extends JFrame {
    private WireWorldState state;

    public WireWorldFrame() {
        super("WireWorld");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setVisible(true);

        drawButtons();

        pack();
    }

    public void drawButtons(){
        JPanel panel = new JPanel();

        JPanel pliki = new JPanel();
        JLabel gen = new JLabel("generacji");
        JSpinner num_of_gen = new JSpinner(new SpinnerNumberModel(100, 1, 500, 1));
        num_of_gen.setBounds(70, 70, 50, 50);
        JButton simulate = new JButton("Symuluj");
        simulate.setBackground(Color.MAGENTA);
        simulate.setOpaque(true);
        JButton open = new JButton("Otwórz...");

        JButton save = new JButton("Zapisz...");


        panel.add(simulate, "East");
        panel.add(num_of_gen, "Center");
        panel.add(gen, "West");
        pliki.add(open);
        pliki.add(save);

        JPanel drawing = new JPanel();
        JButton draw = new JButton("Rysuj");
        JButton erase = new JButton("Wymaż");
        JButton cabel = new JButton("Przewodnik");
        JButton head = new JButton("Głowa");
        JButton tail = new JButton("Ogon");

        cabel.setIcon(new ImageIcon("black_dot.png"));
        head.setIcon(new ImageIcon("red_dot.png"));
        tail.setIcon(new ImageIcon("green_dot.png"));

        drawing.add(draw);
        drawing.add(erase);

        drawing.add(new JSeparator(SwingConstants.VERTICAL));
        drawing.add(new JSeparator(SwingConstants.VERTICAL));
        drawing.add(new JSeparator(SwingConstants.VERTICAL));

        drawing.add(cabel);
        drawing.add(head);
        drawing.add(tail);

        getContentPane().add(BorderLayout.WEST, panel);
        getContentPane().add(BorderLayout.EAST, pliki);
        getContentPane().add(BorderLayout.SOUTH, drawing);

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int status = chooser.showOpenDialog(null);
                if (status == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    ;
                    if (file == null) {
                        return;
                    }

                    String fileName = chooser.getSelectedFile().getAbsolutePath();
                    System.out.println("Opening: " + fileName);
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int userSelection = chooser.showSaveDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = chooser.getSelectedFile();
                    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                }
            }
        });

        draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.setEnabled(false);
                erase.setEnabled(true);
            }
        });

        erase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erase.setEnabled(false);
                draw.setEnabled(true);
                cabel.setEnabled(true);
                head.setEnabled(true);
                tail.setEnabled(true);
            }
        });

        cabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(erase.isEnabled()){
                    cabel.setEnabled(false);
                    head.setEnabled(true);
                    tail.setEnabled(true);
                }

            }
        });

        head.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(erase.isEnabled()){
                    cabel.setEnabled(true);
                    head.setEnabled(false);
                    tail.setEnabled(true);
                }
            }
        });

        tail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(erase.isEnabled()){
                    cabel.setEnabled(true);
                    head.setEnabled(true);
                    tail.setEnabled(false);
                }
            }
        });
    }

    public void setPreferredSize(Dimension preferredSize){
        super.setPreferredSize(preferredSize);
    }
}
