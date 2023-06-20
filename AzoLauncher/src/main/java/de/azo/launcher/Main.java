package de.azo.launcher;

import fr.arinonia.arilibfx.utils.AriLogger;
import javafx.application.Application;

import javax.swing.*;

public class Main {

     public static AriLogger logger;

    public static void main(String[] args) {
           logger = new AriLogger("Azo Launcher");
           try {
               Class.forName("javafx.application.Application");
               Application.launch(FxApplication.class, args);
           } catch (ClassNotFoundException e){
               logger.warn("JavaFx wurde nicht gefunden");
               JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten! Error Code 101 \n" + e.getMessage() + " Wurde nicht gefunden \nFuer mehr Einzelheiten koennen sie Unserer Website besuchen! ", "Java Error", JOptionPane.ERROR_MESSAGE);
           }
    }
}
