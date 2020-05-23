package com.hnks.wireworld.gui;

import com.hnks.wireworld.automaton.AutomatonCell;
import com.hnks.wireworld.automaton.AutomatonSimulation;
import com.hnks.wireworld.automaton.prefabs.AutomatonPrefab;
import com.hnks.wireworld.automaton.prefabs.gol.BlinkerGoLPrefab;
import com.hnks.wireworld.automaton.prefabs.gol.GliderGoLPrefab;
import com.hnks.wireworld.automaton.prefabs.wwld.DiodeRevWWLDPrefab;
import com.hnks.wireworld.automaton.prefabs.wwld.DiodeWWLDPrefab;
import com.hnks.wireworld.automaton.rules.gol.BaseGoLRule;
import com.hnks.wireworld.automaton.rules.IAutomatonRule;
import com.hnks.wireworld.automaton.rules.WireWorldRule;
import com.hnks.wireworld.automaton.rules.gol.MazeGoLRule;
import com.hnks.wireworld.automaton.rules.gol.TwoByTwoGoLRule;
import com.hnks.wireworld.automaton.rules.gol.WalledCitiesGoLRule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AppFrame extends JFrame {
    private IAutomatonRule[] rules = {
            new WireWorldRule(),
            new BaseGoLRule(),
            new WalledCitiesGoLRule(),
            new MazeGoLRule(),
            new TwoByTwoGoLRule()
    };
    private AutomatonPrefab[] prefabs = {
            new DiodeWWLDPrefab(),
            new DiodeRevWWLDPrefab(),
            new BlinkerGoLPrefab(),
            new GliderGoLPrefab()
    };

    private AppState state;

    private boolean isSimRunning = false;
    private Timer simTimer;
    private JSpinner simCounter;
    private DrawingPanel drawingPanel;

    public AppFrame() {
        super("WireWorld");

        state = new AppState(
                new AutomatonSimulation(80, 50),
                100,
                rules[0]
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

        drawingPanel = new DrawingPanel(state);

        JComboBox<IAutomatonRule> ruleSelector = new JComboBox<>(rules);

        JLabel gen = new JLabel("generacji");
        simCounter = new JSpinner(
                new SpinnerNumberModel(state.getSimCount(), 1, 500, 1)
        );
        simCounter.setBounds(70, 70, 50, 50);

        JButton simulate = new JButton("Symuluj");
        JButton step = new JButton("");

        JButton open = new JButton("");
        JButton save = new JButton("");

        // For styling
        open.setName("SmallOpenButton");
        open.setToolTipText("Otwórz");
        save.setName("SmallSaveButton");
        save.setToolTipText("Zapisz");
        step.setName("SmallStepButton");
        step.setToolTipText("Wykonaj pojedynczy krok symulacji");

        simulate.setIcon(new ImageIcon(getClass().getResource("icons/run.png")));
        step.setIcon(new ImageIcon(getClass().getResource("icons/step.png")));
        open.setIcon(new ImageIcon(getClass().getResource("icons/open.png")));
        save.setIcon(new ImageIcon(getClass().getResource("icons/save.png")));

        panel.add(ruleSelector);
        panel.add(simulate, "East");
        panel.add(step, "East");
        panel.add(simCounter, "Center");
        panel.add(gen, "West");

        // EW EW EW NASTY NASTY NASTY
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(new JSeparator(SwingConstants.VERTICAL));

        panel.add(open);
        panel.add(save);

        JPanel drawing = new JPanel();
        JButton draw = new JButton("");
        JButton erase = new JButton("");
        JButton insertPrefab = new JButton("");
        JButton cable = new JButton("Przewodnik");
        JButton head = new JButton("Głowa");
        JButton tail = new JButton("Ogon");

        JComboBox<AutomatonPrefab> prefabSelector = new JComboBox<>(prefabs);
        prefabSelector.setEnabled(false);

        // For styling
        draw.setName("SmallDrawButton");
        draw.setToolTipText("Rysuj");
        erase.setName("SmallEraseButton");
        erase.setToolTipText("Wymaż");
        insertPrefab.setName("SmallPrefabButton");
        insertPrefab.setToolTipText("Dodaj element");

        draw.setIcon(new ImageIcon(getClass().getResource("icons/draw.png")));
        erase.setIcon(new ImageIcon(getClass().getResource("icons/erase.png")));
        insertPrefab.setIcon(new ImageIcon(getClass().getResource("icons/prefab.png")));

        cable.setIcon(new ImageIcon(getClass().getResource("icons/cable.png")));
        head.setIcon(new ImageIcon(getClass().getResource("icons/head.png")));
        tail.setIcon(new ImageIcon(getClass().getResource("icons/tail.png")));

        draw.setSelected(true);
        cable.setSelected(true);

        drawing.add(draw);
        drawing.add(erase);

        drawing.add(insertPrefab);
        drawing.add(prefabSelector);

        drawing.add(cable);
        drawing.add(head);
        drawing.add(tail);

        add(BorderLayout.NORTH, panel);
        //add(BorderLayout.EAST, pliki);
        add(BorderLayout.SOUTH, drawing);
        add(BorderLayout.CENTER, drawingPanel);

        ruleSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state.setRule(
                        (IAutomatonRule)ruleSelector.getSelectedItem()
                );
            }
        });

        simulate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isSimRunning && state.getSimCount() != 0) {
                    simTimer = new Timer();
                    simTimer.schedule(new AppSimulationTimerTask(), 0, 100);
                } else {
                    simTimer.cancel();
                }
                isSimRunning = !isSimRunning;
            }
        });

        step.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state.generateNext();
                drawingPanel.repaint();
            }
        });

        simCounter.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                state.setSimCount((int)simCounter.getValue());
            }
        });

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
                insertPrefab.setSelected(false);
                draw.setSelected(true);
                erase.setSelected(false);

                prefabSelector.setEnabled(false);

                cable.setEnabled(true);
                head.setEnabled(true);
                tail.setEnabled(true);

                if (cable.isSelected()) {
                    drawingPanel.option = DrawingOption.CABLE;
                } else if (head.isSelected()) {
                    drawingPanel.option = DrawingOption.HEAD;
                } else {
                    drawingPanel.option = DrawingOption.TAIL;
                }
            }
        });

        erase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertPrefab.setSelected(false);
                erase.setSelected(true);
                draw.setSelected(false);

                prefabSelector.setEnabled(false);

                cable.setEnabled(false);
                head.setEnabled(false);
                tail.setEnabled(false);

                drawingPanel.option = DrawingOption.ERASE;
            }
        });

        prefabSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.setPrefab(
                        (AutomatonPrefab)prefabSelector.getSelectedItem()
                );
            }
        });

        insertPrefab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertPrefab.setSelected(true);
                erase.setSelected(false);
                draw.setSelected(false);

                prefabSelector.setEnabled(true);

                cable.setEnabled(false);
                head.setEnabled(false);
                tail.setEnabled(false);

                drawingPanel.option = DrawingOption.PREFAB;
                drawingPanel.setPrefab(
                        (AutomatonPrefab)prefabSelector.getSelectedItem()
                );
            }
        });

        cable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cable.setSelected(true);
                head.setSelected(false);
                tail.setSelected(false);
                drawingPanel.option = DrawingOption.CABLE;
            }
        });

        head.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cable.setSelected(false);
                head.setSelected(true);
                tail.setSelected(false);
                drawingPanel.option = DrawingOption.HEAD;
            }
        });

        tail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cable.setSelected(false);
                head.setSelected(false);
                tail.setSelected(true);
                drawingPanel.option = DrawingOption.TAIL;
            }
        });
    }

    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
    }

    private class AppSimulationTimerTask extends TimerTask {
        @Override
        public void run() {
            int count = state.getSimCount();

            if (count == 0) {
                cancel();
                isSimRunning = false;
            }

            state.generateNext();
            drawingPanel.repaint();

            state.setSimCount(count - 1);
            simCounter.setValue(count);
        }
    }
}
