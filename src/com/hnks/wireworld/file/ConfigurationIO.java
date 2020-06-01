package com.hnks.wireworld.file;

import com.hnks.wireworld.automaton.AutomatonCell;
import com.hnks.wireworld.automaton.AutomatonSimulation;
import com.hnks.wireworld.automaton.prefabs.AutomatonPrefab;
import com.hnks.wireworld.automaton.rules.IAutomatonRule;
import com.hnks.wireworld.gui.AppState;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ConfigurationIO {
    public void loadFromFile(
            String filePath, AppState state, IAutomatonRule[] rules, AutomatonPrefab[] prefabs
    ) throws IOException {
        BufferedReader reader = new BufferedReader(
                new FileReader( filePath )
        );

        while ( true ) {
            String line = reader.readLine();
            if ( line == null ) break;

            parseScriptLine( line, state, rules, prefabs );
        }

        reader.close();
    }


    private void parseScriptLine( String line, AppState state, IAutomatonRule[] rules, AutomatonPrefab[] prefabs) {
        if (
                line.startsWith( "//" ) ||
                        line.length() == 0
        ) return;

        String[] args = line.split( "\\s+" );

        if ( args[0].equals( "Cell" ) && args.length > 2 ) {
            // String cell = args[1];
            AutomatonCell cell = AutomatonCell.CABLE;

            if (args[1].equals("Head")) {
                cell = AutomatonCell.HEAD;
            } else if (args[1].equals("Tail")) {
                cell = AutomatonCell.TAIL;
            }

            for ( int i = 2; i < args.length; i += 2 ) {
                if ( i + 1 >= args.length ) break;

                int x = Integer.parseInt( args[i] );
                int y = Integer.parseInt( args[i + 1] );
                
                state.getSim().setCell( cell, x, y );
            }
        } else if ( args[0].equals("Rule") && args.length == 2 ){
            for (IAutomatonRule rule : rules) {
                if (rule.getID().equals(args[1])) {
                    state.setRule(rule);
                    break;
                }
            }
        } else if ( args[0].equals("Prefab") && args.length == 4 ){
            for (AutomatonPrefab prefab : prefabs) {
                if (prefab.getID().equals(args[1])) {
                    prefab.place(
                            state.getSim(),
                            Integer.parseInt(args[2]),
                            Integer.parseInt(args[3])
                    );
                    break;
                }
            }
        }
          
    }

    public void saveToFile(File file, AppState state) throws IOException{
        List<Integer> cableCoords = new ArrayList<Integer>();
        List<Integer> headCoords = new ArrayList<Integer>();
        List<Integer> tailCoords = new ArrayList<Integer>();

        for (int x = 0; x < 80; x++) {
            for (int y = 0; y < 50; y++) {
                AutomatonCell cell = state.getSim().getCell( x, y );

                switch (cell) {
                    case HEAD -> {
                        headCoords.add(x);
                        headCoords.add(y);
                    }
                    case TAIL -> {
                        tailCoords.add(x);
                        tailCoords.add(y);
                    }
                    case CABLE -> {
                        cableCoords.add(x);
                        cableCoords.add(y);
                    }
                }
            }
        }
        StringBuilder test = new StringBuilder();
        test.append( "Cell Head " );
        for(int i : headCoords){
            test.append(i + " ");
        }
        test.append("\n");

        test.append( "Cell Tail " );
        for(int i : tailCoords){
            test.append(i + " ");
        }
        test.append("\n");

        test.append( "Cell Cable " );
        for(int i : cableCoords){
            test.append(i + " ");
        }
        test.append("\n");

        test.append("Rule " + state.getRule().getID());

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.append(test);
        fileWriter.close();


    }
}
