import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.*;

import javax.swing.*;


class WireWorldFrame extends JFrame {
    private WireWorldState state;

    public WireWorldFrame() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setVisible(true);

        drawButtons();
        
        pack();
    

        //this.state = state;

    }

public void drawButtons(){
    JPanel panel = new JPanel();

    JPanel pliki = new JPanel();
    JLabel label = new JLabel("generacji");
    JSpinner spinner = new JSpinner(new SpinnerNumberModel(100, 1, 500, 1));
    spinner.setBounds(70, 70, 50, 50);
    JButton symuluj = new JButton("Symuluj");
    symuluj.setBackground(Color.MAGENTA);
    symuluj.setOpaque(true);
    JButton otwórz = new JButton("Otwórz...");
    // JFileChooser otwórz = new
    // JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    // otwórz.setDialogTitle("Otwórz...");
    JButton zapisz = new JButton("Zapisz...");
    // JFileChooser zapisz = new
    // JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    // zapisz.setDialogTitle("Zapisz...");

    panel.add(symuluj, "East");
    panel.add(spinner, "Center");
    panel.add(label, "West");
    pliki.add(otwórz);
    pliki.add(zapisz);

    JPanel drawing = new JPanel();
    JButton rysuj = new JButton("Rysuj");
    JButton wymaż = new JButton("Wymaż");
    JButton przewodnik = new JButton("Przewodnik");
    JButton glowa = new JButton("Głowa");
    JButton ogon = new JButton("Ogon");
    przewodnik.setIcon(new ImageIcon("black_dot.png"));
    glowa.setIcon(new ImageIcon("red_dot.png"));
    ogon.setIcon(new ImageIcon("green_dot.png"));

    otwórz.addActionListener(new ActionListener() {
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

    zapisz.addActionListener(new ActionListener() {
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

    rysuj.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            rysuj.setEnabled(false);
            wymaż.setEnabled(true);
        }
    });

    wymaż.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            wymaż.setEnabled(false);
            rysuj.setEnabled(true);
            przewodnik.setEnabled(true);
            glowa.setEnabled(true);
            ogon.setEnabled(true);
        }
    });

    przewodnik.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            przewodnik.setEnabled(false);
            glowa.setEnabled(true);
            ogon.setEnabled(true);
        }
    });

    glowa.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            przewodnik.setEnabled(true);
            glowa.setEnabled(false);
            ogon.setEnabled(true);
        }
    });

    ogon.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            przewodnik.setEnabled(true);
            glowa.setEnabled(true);
            ogon.setEnabled(false);
        }
    });

 

    drawing.add(rysuj);
    drawing.add(wymaż);
    drawing.add(przewodnik);
    drawing.add(glowa);
    drawing.add(ogon);

    
    getContentPane().add(BorderLayout.WEST, panel);
    getContentPane().add(BorderLayout.EAST, pliki);
    getContentPane().add(BorderLayout.SOUTH, drawing);
 


}

public void setPreferredSize(Dimension preferredSize){
    super.setPreferredSize(preferredSize);
}
    
     
}