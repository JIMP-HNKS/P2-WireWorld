import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;


class WireWorldFrame extends JFrame {
    private WireWorldState state;

    public WireWorldFrame() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setVisible(true);
        setBackground(Color.BLACK);

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