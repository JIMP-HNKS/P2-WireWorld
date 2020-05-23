package com.hnks.wireworld;

import com.hnks.wireworld.gui.AppFrame;

import javax.swing.*;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        SynthLookAndFeel theme = new SynthLookAndFeel();

        try {
            theme.load(
                    Main.class.getResourceAsStream("gui/theme/theme.xml"),
                    Main.class
            );
            UIManager.setLookAndFeel(theme);
        } catch (ParseException e) {
            // Theme parsing failed - sad.
        } catch (UnsupportedLookAndFeelException e) {
            // Synth theme not supported - sad.
        }

        new AppFrame();
    }
}
