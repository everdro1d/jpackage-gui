package main.com.everdro1d.jpackage.core;

import static main.com.everdro1d.jpackage.core.CommandSettings.*;
import static main.com.everdro1d.jpackage.core.MainWorker.debug;

public class ButtonAction {

    public static void saveSettingsToFile() {
        // TODO open file chooser to save directory
        getSettingsFromUI();
        if (debug) System.out.println("Saved settings to map.");
        // TODO Save the settings from the commandSettingsMap into a file

        if (debug) System.out.println("Saving settings to file from map.");
    }

    public static void loadSettingsFromFile() {
        // TODO open file chooser to load file
        loadSettingsFromFileToMap();
        if (debug) System.out.println("Loaded settings from file to map.");
        setSettingsFromMap();
        if (debug) System.out.println("Loaded settings from map to ui.");
    }

    public static void assembleAndRunJPackageCommand() {
        // TODO assemble the jpackage command from the commandSettingsMap
        getSettingsFromUI();
        if (debug) System.out.println("Saved settings from ui to map.");

    }

    // worker methods -------------------------------------------------------------------------------------------------|

}
