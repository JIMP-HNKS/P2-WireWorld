package com.hnks.wireworld.gui;

import com.hnks.wireworld.automaton.AutomatonSimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.*;

import javax.swing.*;

public class AppFrame extends JFrame {
    private AppState state;

    public AppFrame() {
        super("WireWorld");

        state = new AppState(
                new AutomatonSimulation(80, 50),
                100
        );

        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(640, 600));
        setVisible(true);
        setResizable(false);

        createUI();

        pack();
    }

    public void createUI(){
        JPanel panel = new JPanel();

        DrawingPanel drawingPanel = new DrawingPanel(state);

        JPanel pliki = new JPanel();
        JLabel gen = new JLabel("generacji");
        JSpinner num_of_gen = new JSpinner(
                new SpinnerNumberModel(state.getSimCount(), 1, 500, 1)
        );
        num_of_gen.setBounds(70, 70, 50, 50);
        JButton simulate = new JButton("Symuluj");

        // simulate.setBackground(Color.MAGENTA);
        simulate.setOpaque(true);
        JButton open = new JButton("");

        JButton save = new JButton("");

        simulate.setIcon(new ImageIcon(getClass().getResource("icons/run.png")));
        open.setIcon(new ImageIcon(getClass().getResource("icons/open.png")));
        save.setIcon(new ImageIcon(getClass().getResource("icons/save.png")));


        panel.add(simulate, "East");
        panel.add(num_of_gen, "Center");
        panel.add(gen, "West");

        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(new JSeparator(SwingConstants.VERTICAL));



        panel.add(open);
        panel.add(save);

        JPanel drawing = new JPanel();
        JButton draw = new JButton("Rysuj");
        JButton erase = new JButton("Wymaż");
        JButton cable = new JButton("Przewodnik");
        JButton head = new JButton("Głowa");
        JButton tail = new JButton("Ogon");

        draw.setIcon(new ImageIcon(getClass().getResource("icons/draw.png")));
        erase.setIcon(new ImageIcon(getClass().getResource("icons/erase.png")));

        cable.setIcon(new ImageIcon(getClass().getResource("icons/cable.png")));
        head.setIcon(new ImageIcon(getClass().getResource("icons/head.png")));
        tail.setIcon(new ImageIcon(getClass().getResource("icons/tail.png")));

        drawing.add(draw);
        drawing.add(erase);

        drawing.add(new JSeparator(SwingConstants.VERTICAL));
        drawing.add(new JSeparator(SwingConstants.VERTICAL));
        drawing.add(new JSeparator(SwingConstants.VERTICAL));

        drawing.add(cable);
        drawing.add(head);
        drawing.add(tail);

        add(BorderLayout.NORTH, panel);
        //add(BorderLayout.EAST, pliki);
        add(BorderLayout.SOUTH, drawing);
        add(BorderLayout.CENTER, drawingPanel);

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
                draw.setSelected(true);
                erase.setSelected(false);
                cable.setEnabled(true);
                head.setEnabled(true);
                tail.setEnabled(true);
            }
        });

        erase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erase.setSelected(true);
                draw.setSelected(false);
                cable.setEnabled(false);
                head.setEnabled(false);
                tail.setEnabled(false);
            }
        });

        cable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cable.setSelected(true);
                head.setSelected(false);
                tail.setSelected(false);
            }
        });

        head.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cable.setSelected(false);
                head.setSelected(true);
                tail.setSelected(false);
            }
        });

        tail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cable.setSelected(false);
                head.setSelected(false);
                tail.setSelected(true);
            }
        });
    }

    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
    }
}
