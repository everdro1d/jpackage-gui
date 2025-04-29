package main.com.everdro1d.jpackage.core;

import com.everdro1d.libs.core.Utils;
import com.everdro1d.libs.io.Files;
import com.everdro1d.libs.io.SyncPipe;
import com.everdro1d.libs.swing.windows.FileChooser;
import com.everdro1d.libs.swing.windows.settings.BasicSettingsWindow;
import main.com.everdro1d.jpackage.ui.MainWindow;

import javax.swing.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static main.com.everdro1d.jpackage.core.CommandSettings.*;
import static main.com.everdro1d.jpackage.core.MainWorker.*;
import static main.com.everdro1d.jpackage.ui.MainWindow.topFrame;

public class ButtonAction {

    private static BasicSettingsWindow settingsWindow;

    private static String fileChooserSaveDialogTitleText = "Save As:";
    private static String fileChooserLoadDialogTitleText = "Load From:";
    private static String setSaveLocationDialogMessageText = "Saved to:";
    private static String setSaveLocationDialogTitleText = "Saved Successfully!:";
    private static String saveLocationOverwriteDialogMessageText = "File already exists. Overwrite?";
    private static String saveLocationOverwriteDialogTitleText = "File Overwrite!";
    private static String cannotLoadDirectoryDialogMessageText = "Cannot load a Directory, please select a valid file.";
    private static String cannotLoadDirectoryDialogTitleText = "Error!";

    public static void saveSettingsToFile() {
        if (debug) System.out.println("Saving settings to file from map.");
        String path = openFileChooser(false);
        if (path == null) return;
        setSettingsMapFromUI();
        String fileName = assembleFileName();
        Files.saveMapToFile(path,
                fileName,
                getCommandSettingsMap(),
                true
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
        setSettingsMapFromUI();
        //todo: add checks for valid jpackage args
        //      (e.x. no spec. chars in name, etc.)
        CommandAssembler.populateCommandMapFromCommandSettingsMap();
        if (debug) System.out.println("Running JPackage Command.");
        ArrayList<String> cmd = CommandAssembler.getCommandList();
        String pwd = getCommandSettingsMap().get("main_jdkBinPath");
        runCommand(cmd, pwd, debug);
    }

    public static void showSettingsWindow() {
        if (debug) System.out.println("Showing settings window.");
        if (settingsWindow == null) {
            settingsWindow = new BasicSettingsWindow(
                    topFrame, MainWindow.fontName, MainWindow.fontSize,
                    prefs, debug, localeManager, new JPanel()
            ) {
                @Override
                public void applySettings() {
                    localeManager.reloadLocaleInProgram(prefs.get("currentLocale", localeManager.getCurrentLocale()));
                    currentLocale = localeManager.getCurrentLocale();
                }
            };
        } else {
            settingsWindow.setVisible(true);
            settingsWindow.requestFocus();
            settingsWindow.toFront();
        }

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
            if (!load) {
                int result = JOptionPane.showConfirmDialog(
                        topFrame,
                        saveLocationOverwriteDialogMessageText + " "
                                + f.getAbsolutePath() + File.separator
                                + assembleFileName(),
                        saveLocationOverwriteDialogTitleText,
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE
                );
                if (result == JOptionPane.CANCEL_OPTION) {
                    if (debug) System.out.println("Save FileChooser cancelled by user.");
                    return null;
                }
            } else if (f.isDirectory()) {
                if (debug) System.out.println("Cannot load a directory: " + f.getAbsolutePath());
                JOptionPane.showMessageDialog(
                        topFrame, cannotLoadDirectoryDialogMessageText,
                        cannotLoadDirectoryDialogTitleText, JOptionPane.ERROR_MESSAGE
                );
                return null;
            }

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
        if (!localeManager.getClassesInLocaleMap().contains("Dialogs")
                || !localeManager.getComponentsInClassMap("Dialogs").contains("ButtonAction")
        ) {
            addDialogComponentsToLocale();
        }
        useLocale();
    }

    private static String assembleFileName() {
        String programName = getCommandSettingsMap().get("gen_name");
        if (programName != null) {
            programName = programName.trim().toLowerCase().replaceAll("\\s+", "_");
        }
        String version = getCommandSettingsMap().get("gen_version");
        if (version != null) {
            version = version.trim().replaceAll("\\s+", "_");
        } else {
            version = "";
        }

        return detectedOS + "_"
                + programName + "_"
                + version + "_"
                + Utils.getSanitizedCurrentTime(
                        true, false, false
                  );
    }

    private static void addDialogComponentsToLocale() {
        Map<String, String> dialogMap = new TreeMap<>();
        dialogMap.put("fileChooserSaveDialogTitleText", fileChooserSaveDialogTitleText);
        dialogMap.put("fileChooserLoadDialogTitleText", fileChooserLoadDialogTitleText);
        dialogMap.put("setSaveLocationDialogMessageText", setSaveLocationDialogMessageText);
        dialogMap.put("setSaveLocationDialogTitleText", setSaveLocationDialogTitleText);

        dialogMap.put("saveLocationOverwriteDialogMessageText", saveLocationOverwriteDialogMessageText);
        dialogMap.put("saveLocationOverwriteDialogTitleText", saveLocationOverwriteDialogTitleText);
        dialogMap.put("cannotLoadDirectoryDialogMessageText", cannotLoadDirectoryDialogMessageText);
        dialogMap.put("cannotLoadDirectoryDialogTitleText", cannotLoadDirectoryDialogTitleText);

        if (!localeManager.getClassesInLocaleMap().contains("Dialogs")) {
            localeManager.addClassSpecificMap("Dialogs", new TreeMap<>());
        }

        localeManager.addComponentSpecificMap("Dialogs", "ButtonAction", dialogMap);
    }

    private static void useLocale() {
        Map<String, String> dialogMap = localeManager.getComponentSpecificMap("Dialogs", "ButtonAction");
        fileChooserSaveDialogTitleText = dialogMap.getOrDefault("fileChooserSaveDialogTitleText", fileChooserSaveDialogTitleText);
        fileChooserLoadDialogTitleText = dialogMap.getOrDefault("fileChooserLoadDialogTitleText", fileChooserLoadDialogTitleText);
        setSaveLocationDialogMessageText = dialogMap.getOrDefault("setSaveLocationDialogMessageText", setSaveLocationDialogMessageText);
        setSaveLocationDialogTitleText = dialogMap.getOrDefault("setSaveLocationDialogTitleText", setSaveLocationDialogTitleText);

        saveLocationOverwriteDialogMessageText = dialogMap.getOrDefault("saveLocationOverwriteDialogMessageText", saveLocationOverwriteDialogMessageText);
        saveLocationOverwriteDialogTitleText = dialogMap.getOrDefault("saveLocationOverwriteDialogTitleText", saveLocationOverwriteDialogTitleText);
        cannotLoadDirectoryDialogMessageText = dialogMap.getOrDefault("cannotLoadDirectoryDialogMessageText", cannotLoadDirectoryDialogMessageText);
        cannotLoadDirectoryDialogTitleText = dialogMap.getOrDefault("cannotLoadDirectoryDialogTitleText", cannotLoadDirectoryDialogTitleText);
    }

    private static void runCommand(ArrayList<String> cmd, String pwd, boolean debug) {
        ProcessBuilder pb = new ProcessBuilder(cmd);
        if (pwd != null && (new File(pwd)).exists()) {
            pb.directory(new File(pwd));
        }

        try {
            Process p = pb.start();
            Scanner errorScanner = new Scanner(p.getErrorStream());
            StringBuilder errorOutput = new StringBuilder();

            while (errorScanner.hasNextLine()) {
                String line = errorScanner.nextLine();
                errorOutput.append(line).append(System.lineSeparator());
            }

            p.waitFor();

            if (p.exitValue() != 0 && errorOutput.length() > 0) {
                if (debug) System.err.println("Jpackage Error: " + errorOutput.toString().trim());
                JOptionPane.showMessageDialog(
                        topFrame,
                        errorOutput.toString().trim(),
                        "JPackage Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else if (p.exitValue() == 0) {
                if (debug) System.out.println("Successfully created installer.");
                String outputPath = getCommandSettingsMap().get("gen_outputPath");
                JOptionPane.showMessageDialog(
                        topFrame,
                        "Successfully created installer at:\n"
                                + outputPath,
                        "Success!",
                        JOptionPane.INFORMATION_MESSAGE
                );

                if (debug) System.out.println("Attempting to open: " + outputPath);
                Files.openInFileManager(outputPath);
            }

            if (debug) {
                System.out.println("JPackage process exited with code: " + p.exitValue());
            }
        } catch (Exception e) {
            if (debug) {
                e.printStackTrace(System.err);
            }
        }
    }

}
