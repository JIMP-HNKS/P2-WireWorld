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
        setPreferredSize(new Dimension(640, 500));
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
        JButton open = new JButton("Otwórz...");

        JButton save = new JButton("Zapisz...");


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
                draw.setEnabled(false);
                erase.setEnabled(true);
            }
        });

        erase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erase.setEnabled(false);
                draw.setEnabled(true);
                cable.setEnabled(true);
                head.setEnabled(true);
                tail.setEnabled(true);
                drawingPanel.option = DrawingOption.ERASE;
            }
        });

        cable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(erase.isEnabled()){
                    draw.setEnabled(false);
                    cable.setEnabled(false);
                    head.setEnabled(true);
                    tail.setEnabled(true);
                    drawingPanel.option = DrawingOption.CABLE;
                }

            }
        });

        head.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(erase.isEnabled()){
                    draw.setEnabled(false);
                    cable.setEnabled(true);
                    head.setEnabled(false);
                    tail.setEnabled(true);
                    drawingPanel.option = DrawingOption.HEAD;
                }
            }
        });

        tail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(erase.isEnabled()){
                    draw.setEnabled(false);
                    cable.setEnabled(true);
                    head.setEnabled(true);
                    tail.setEnabled(false);
                    drawingPanel.option = DrawingOption.TAIL;
                }
            }
        });
    }

    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
    }
}
