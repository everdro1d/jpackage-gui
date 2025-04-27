package main.com.everdro1d.jpackage.core;

import com.everdro1d.libs.core.Utils;
import com.everdro1d.libs.io.Files;
import com.everdro1d.libs.swing.windows.FileChooser;

import javax.swing.*;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import static main.com.everdro1d.jpackage.core.CommandSettings.*;
import static main.com.everdro1d.jpackage.core.MainWorker.*;
import static main.com.everdro1d.jpackage.ui.MainWindow.topFrame;

public class ButtonAction {

    private static String fileChooserSaveDialogTitleText = "Save As:";
    private static String fileChooserLoadDialogTitleText = "Load From:";
    private static String setSaveLocationDialogMessageText = "Saved to:";
    private static String setSaveLocationDialogTitleText = "Saved Successfully!:";

    public static void saveSettingsToFile() {
        if (debug) System.out.println("Saving settings to file from map.");
        String path = openFileChooser(false);
        if (path == null) return;
        setSettingsMapFromUI();
        String fileName = assembleFileName();
        Files.saveMapToFile(path,
                fileName,
                getCommandSettingsMap(),
                false
        );
        if (debug) System.out.println("Saving settings to file" +
                "\n at: " + path + "\n as: " + fileName + ".txt"
        );

        Files.openInFileManager(path + File.separator + fileName + ".txt");
    }

    public static void loadSettingsFromFile() {
        String path = openFileChooser(true);
        if (path == null) return;
        loadSettingsFromFileToMap(path);
        setUIFromSettingsMap();
    }

    public static void assembleAndRunJPackageCommand() {
        // TODO assemble the jpackage command from the commandSettingsMap
        setSettingsMapFromUI();

    }

    // worker methods -------------------------------------------------------------------------------------------------|

    private static String openFileChooser(boolean load) {
        localeCheck();

        String fileChooserDialogTitleText = load ? fileChooserLoadDialogTitleText : fileChooserSaveDialogTitleText;

        String output = System.getProperty("user.home");

        FileChooser fileChooser = new FileChooser(
                output, fileChooserDialogTitleText,
                load, true, true, "txt",
                false, null, localeManager
        );

        int returnValue = fileChooser.showOpenDialog(topFrame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            // TODO: overwrite warning dialog for save?
            output = f.getAbsolutePath();
            if (!load) JOptionPane.showMessageDialog(
                    topFrame, setSaveLocationDialogMessageText
                    + " " + output, setSaveLocationDialogTitleText,
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            if (debug) System.out.println("FileChooser cancelled by user." + " Load: " + load);
            return null;
        }

        return output;
    }

    private static void localeCheck() {
        if (!localeManager.getClassesInLocaleMap().contains("FileChooser")
                || !localeManager.getComponentsInClassMap("FileChooser").contains("ButtonAction")
        ) {
            addFileChooserComponentToLocale();
        }
        useLocale();
    }

    private static String assembleFileName() {
        String programName = getCommandSettingsMap().get("gen_name");
        if (programName != null) {
            programName = programName.trim().toLowerCase().replaceAll("\\s+", "_");
        }

        return detectedOS + "_"
                + programName + "_"
                + Utils.getSanitizedCurrentTime(
                        true, true, false
                  );
    }

    private static void addFileChooserComponentToLocale() {
        Map<String, String> fileChooserMap = new TreeMap<>();
        fileChooserMap.put("fileChooserSaveDialogTitleText", fileChooserSaveDialogTitleText);
        fileChooserMap.put("fileChooserLoadDialogTitleText", fileChooserLoadDialogTitleText);
        fileChooserMap.put("setSaveLocationDialogMessageText", setSaveLocationDialogMessageText);
        fileChooserMap.put("setSaveLocationDialogTitleText", setSaveLocationDialogTitleText);

        if (!localeManager.getClassesInLocaleMap().contains("FileChooser")) {
            localeManager.addClassSpecificMap("FileChooser", new TreeMap<>());
        }

        localeManager.addComponentSpecificMap("FileChooser", "ButtonAction", fileChooserMap);
    }

    private static void useLocale() {
        Map<String, String> fileChooserMap = localeManager.getComponentSpecificMap("FileChooser", "ButtonAction");
        fileChooserSaveDialogTitleText = fileChooserMap.getOrDefault("fileChooserSaveDialogTitleText", fileChooserSaveDialogTitleText);
        fileChooserLoadDialogTitleText = fileChooserMap.getOrDefault("fileChooserLoadDialogTitleText", fileChooserLoadDialogTitleText);
        setSaveLocationDialogMessageText = fileChooserMap.getOrDefault("setSaveLocationDialogMessageText", setSaveLocationDialogMessageText);
        setSaveLocationDialogTitleText = fileChooserMap.getOrDefault("setSaveLocationDialogTitleText", setSaveLocationDialogTitleText);
    }
}
