package main.com.everdro1d.jpackage.core;

import com.everdro1d.libs.core.Utils;
import com.everdro1d.libs.io.Files;
import com.everdro1d.libs.swing.SwingGUI;
import com.everdro1d.libs.swing.windows.FileChooser;
import com.everdro1d.libs.swing.windows.settings.BasicSettingsWindow;
import main.com.everdro1d.jpackage.ui.MainWindow;
import main.com.everdro1d.jpackage.ui.panels.GeneralSettingsPanel;

import javax.swing.*;

import java.io.File;
import java.util.*;

import static main.com.everdro1d.jpackage.core.CommandSettings.*;
import static main.com.everdro1d.jpackage.core.MainWorker.*;
import static main.com.everdro1d.jpackage.ui.MainWindow.getWorkingDialog;
import static main.com.everdro1d.jpackage.ui.MainWindow.topFrame;

public class ButtonAction {

    public static BasicSettingsWindow settingsWindow;
    private static Process currentProcess;

    private static String fileChooserSaveDialogTitleText = "Save As:";
    private static String fileChooserLoadDialogTitleText = "Load From:";
    private static String setSaveLocationDialogMessageText = "Saved to:";
    private static String setSaveLocationDialogTitleText = "Saved Successfully!:";
    private static String saveLocationOverwriteDialogMessageText = "File already exists. Overwrite?";
    private static String saveLocationOverwriteDialogTitleText = "File Overwrite!";
    private static String cannotLoadDirectoryDialogMessageText = "Cannot load a Directory, please select a valid file.";
    private static String cannotLoadDirectoryDialogTitleText = "Error!";
    private static String successInstallerCreationDialogMessageText = "Successfully created installer at:";
    private static String successInstallerCreationDialogTitleText = "Success!";

    public static void saveSettingsToFile() {
        if (debug) System.out.println("Saving settings to file from map.");
        setSettingsMapFromUI();
        String path = openFileChooser(false);
        if (path == null) return;
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
        CommandAssembler.populateCommandMapFromCommandSettingsMap();
        ArrayList<String> cmd = CommandAssembler.getCommandList();
        String pwd = getCommandSettingsMap().get("main_jdkBinPath");
        new Thread(() -> runCommand(cmd, pwd)).start();
    }

    public static void showSettingsWindow() {
        if (debug) System.out.println("Showing settings window.");
        if (settingsWindow == null ||  !settingsWindow.isVisible()) {
            settingsWindow = new BasicSettingsWindow(
                    topFrame, MainWindow.fontName, MainWindow.fontSize,
                    prefs, debug, localeManager, new GeneralSettingsPanel(),
                    githubRepoURL + "tree/master/locale/", dro1dDevWebsite
            ) {
                @Override
                public void applySettings() {
                    currentLocale = localeManager.getCurrentLocale();

                    debug = prefs.getBoolean("debug", debug);
                    darkMode = prefs.getBoolean("darkMode", darkMode);

                    if (debug) {
                        showDebugConsole();
                        if (debug) System.out.println("Active locale: " + currentLocale);
                        System.out.println("Active: " + MainWindow.titleText + " v" + currentVersion);
                        System.out.println("Detected OS: " + MainWorker.detectedOS);
                    } else if (debugConsoleWindow != null) {
                        debugConsoleWindow.dispose();
                        debugConsoleWindow = null;
                        windowFrameArray[1] = null;
                    }

                    SwingGUI.switchLightOrDarkMode(darkMode, windowFrameArray);
                    getInstanceOfMainWindow().customActionsOnDarkModeSwitch();
                }

                @Override
                public Map<String, String> setOriginalSettingsMap() {
                    Map<String, String> originalSettingsMap = new TreeMap<>();

                    originalSettingsMap.put("debug", String.valueOf(debug));
                    originalSettingsMap.put("darkMode", String.valueOf(darkMode));

                    return originalSettingsMap;
                }

                @Override
                public Map<String, Boolean> setRestartRequiredSettingsMap() {
                    Map<String, Boolean> restartRequiredSettingsMap = new TreeMap<>();

                    restartRequiredSettingsMap.put("debug", false);
                    restartRequiredSettingsMap.put("darkMode", false);

                    return restartRequiredSettingsMap;
                }
            };
            windowFrameArray[2] = settingsWindow;
        } else {
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
            File existTest = new File(f.getAbsolutePath() + (load ? "" : (File.separator + assembleFileName() + ".txt")));
            if (debug) System.out.println("File: " + existTest.getAbsolutePath() + "\nExists: " + existTest.exists());
            if (!load && existTest.exists()) {
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
            } else if (load && f.isDirectory()) {
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

        dialogMap.put("successInstallerCreationDialogMessageText", successInstallerCreationDialogMessageText);
        dialogMap.put("successInstallerCreationDialogTitleText", successInstallerCreationDialogTitleText);

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

        successInstallerCreationDialogMessageText = dialogMap.getOrDefault("successInstallerCreationDialogMessageText", successInstallerCreationDialogMessageText);
        successInstallerCreationDialogTitleText = dialogMap.getOrDefault("successInstallerCreationDialogTitleText", successInstallerCreationDialogTitleText);
    }

    private static void runCommand(ArrayList<String> cmd, String pwd) {
        ProcessBuilder pb = new ProcessBuilder(cmd);
        if (pwd != null && (new File(pwd)).exists()) {
            pb.directory(new File(pwd));
        }

        try {
            currentProcess = pb.start();
            if (debug) System.out.println("Process started: " + currentProcess);

            Scanner errorScanner = new Scanner(currentProcess.getErrorStream());
            StringBuilder errorOutput = new StringBuilder();

            while (errorScanner.hasNextLine()) {
                String line = errorScanner.nextLine();
                errorOutput.append(line).append(System.lineSeparator());
            }

            currentProcess.waitFor();

            getWorkingDialog().dispose();

            if (currentProcess.exitValue() != 0 && !errorOutput.isEmpty()) {
                if (debug) System.err.println("Jpackage Error: " + errorOutput.toString().trim());
                JOptionPane.showMessageDialog(
                        topFrame,
                        errorOutput.toString().trim(),
                        "JPackage Error",
                        JOptionPane.ERROR_MESSAGE
                );

            } else if (currentProcess.exitValue() == 0) {
                if (debug) System.out.println("Successfully created installer.");
                String outputPath = getCommandSettingsMap().get("gen_outputPath");
                JOptionPane.showMessageDialog(
                        topFrame,
                        successInstallerCreationDialogMessageText + " \n"
                                + outputPath,
                        successInstallerCreationDialogTitleText,
                        JOptionPane.INFORMATION_MESSAGE
                );

                if (debug) System.out.println("Attempting to open: " + outputPath);
                Files.openInFileManager(outputPath);
            }

            if (debug) {
                System.out.println("JPackage process exited with code: " + currentProcess.exitValue());
            }
        } catch (Exception e) {
            if (debug) {
                e.printStackTrace(System.err);
            }
        } finally {
            if (debug) System.out.println("JPackage process completed. Setting currentProcess to null.");
            currentProcess = null;
        }
    }

    public static void interruptProcess() {
        if (currentProcess != null) {
            try {
                if (currentProcess.isAlive()) {
                    currentProcess.destroy();
                    if (debug) System.out.println("Process interrupted.");
                } else {
                    if (debug) System.out.println("Process already terminated.");
                }
            } catch (Exception e) {
                if (debug) {
                    System.err.println("Error while interrupting the process: " + e.getMessage());
                    e.printStackTrace(System.err);
                }
            }
        } else if (debug) {
            System.out.println("No process to interrupt.");
        }
    }

}
