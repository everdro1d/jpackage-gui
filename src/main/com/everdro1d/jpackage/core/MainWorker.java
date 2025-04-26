/*
 * requires everdro1dCoreLib, flatlaf, and json libraries
 */
package main.com.everdro1d.jpackage.core;

import com.everdro1d.libs.commands.CommandInterface;
import com.everdro1d.libs.commands.CommandManager;
import com.everdro1d.libs.core.ApplicationCore;
import com.everdro1d.libs.core.LocaleManager;
import com.everdro1d.libs.swing.SwingGUI;
import com.everdro1d.libs.swing.dialogs.UpdateCheckerDialog;
import com.everdro1d.libs.swing.windows.DebugConsoleWindow;
import main.com.everdro1d.jpackage.core.commands.DebugCommand;
import main.com.everdro1d.jpackage.ui.MainWindow;

import java.awt.*;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import java.util.prefs.Preferences;

public class MainWorker {
    // Variables ------------------------------------------------------------------------------------------------------|
    public static final String dro1dDevWebsite = "https://everdro1d.github.io/";
    public static final String currentVersion = "0.0.1"; //TODO: update this with each release
    private static final Map<String, CommandInterface> CUSTOM_COMMANDS_MAP = Map.of(
            "-debug", new DebugCommand()
    );
    public static CommandManager commandManager = new CommandManager(CUSTOM_COMMANDS_MAP);
    private static String currentLocale = "eng";
    public static final LocaleManager localeManager = new LocaleManager(MainWorker.class);
    public static boolean debug = false;
    public static DebugConsoleWindow debugConsoleWindow;
    static final Preferences prefs = Preferences.userNodeForPackage(MainWorker.class);
    public static int[] windowPosition = {0, 0, 0};

    private static MainWindow mainWindow;
    public static String detectedOS = "";
    public static String jdkDirectory = "";

    // End of variables -----------------------------------------------------------------------------------------------|

    public static void main(String[] args) {
        startUpActions(args);
        jdkDirectory = getJdkDirectoryPath();
        startMainWindow();
    }

    private static void startUpActions(String[] args) {
        ApplicationCore.checkCLIArgs(args, commandManager);
        checkOSCompatibility();

        SwingGUI.setupLookAndFeel(true, false);

        SwingGUI.uiSetup(MainWindow.fontName, MainWindow.fontSize);

        loadPreferencesAndQueueSave();

        localeManager.loadLocaleFromFile("locale_" + currentLocale);

        if (debug) {
            showDebugConsole();
            if (debug) System.out.println("Loaded locale: locale_" + currentLocale);
            System.out.println("Starting " + MainWindow.titleText + " v" + currentVersion + "...");
            System.out.println("Detected OS: " + MainWorker.detectedOS);
        }

        //checkUpdate(); TODO re-enable when ready for release

        if (!localeManager.getClassesInLocaleMap().contains("!head")) {
            addVersionToLocale();
        }
    }

    private static void addVersionToLocale() {
        Map<String, Map<String, String>> classMap = new TreeMap<>();
        classMap.put("version", new TreeMap<>());
        Map<String, String> mainMap = classMap.get("version");
        mainMap.put("currentVersion", currentVersion);

        localeManager.addClassSpecificMap("!head", classMap);
    }

    public static void checkOSCompatibility() {
        String detectedOS = ApplicationCore.detectOS();
        MainWorker.detectedOS = detectedOS;
        executeOSSpecificCode(detectedOS);
    }

    public static void executeOSSpecificCode(String detectedOS) {
        switch (detectedOS) {
            case "Windows" -> {
                // Windows specific code
            }
            case "macOS" -> {
                // macOS specific code
            }
            case "Unix" -> {
                // Unix specific code
            }
            default -> {
                System.out.println("Unknown OS detected. Cannot guarantee functionality. Exiting.");
                System.exit(1);
            }
        }
    }

    private static String getJdkDirectoryPath() {
        String javaHome = System.getProperty("java.home");
        if (debug) System.out.println("Java Home: " + javaHome);

        File jdkDir = new File(javaHome);
        if (jdkDir.isDirectory() && jdkDir.getName().toLowerCase().contains("jdk")) {
            if (debug) System.out.println("JDK Directory: " + jdkDir.getAbsolutePath());
            return jdkDir.getAbsolutePath();
        }

        if (debug) System.err.println("Java Home could not be found. Attempting to auto-fill JDK path based on OS.");
        String pathTest = switch (detectedOS) {
            case "Windows" -> System.getenv("ProgramFiles") + "\\Java";
            case "macOS" -> "/Library/Java/JavaVirtualMachines/";
            case "Unix" -> "/usr/lib/jvm/";
            default -> null;
        };

        if (pathTest != null) {
            File jdkDirFallback = new File(pathTest);
            // Check if the directory exists, then check for JDKs
            if (jdkDirFallback.isDirectory()) {
                File[] jdkDirs = jdkDirFallback.listFiles();
                if (jdkDirs != null) {
                    for (File dir : jdkDirs) {
                        if (dir.isDirectory() && dir.getName().toLowerCase().contains("jdk")) {
                            if (debug) System.out.println("JDK Directory: " + dir.getAbsolutePath());
                            return dir.getAbsolutePath();
                        }
                    }
                }
            }
        }

        if (debug) System.err.println("JDK Directory could not be found. Please set it manually.");
        return "CHANGE ME to \"/bin\" directory of your JDK";
    }

    private static void loadPreferencesAndQueueSave() {
        loadWindowPosition();

        currentLocale = prefs.get("currentLocale", "eng");

        savePreferencesOnExit();
    }

    private static void savePreferencesOnExit() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            saveWindowPosition();

            prefs.put("currentLocale", currentLocale);
        }));
    }

    private static void loadWindowPosition() {
        windowPosition[0] = prefs.getInt("framePosX", 0);
        windowPosition[1] = prefs.getInt("framePosY", 0);
        windowPosition[2] = prefs.getInt("activeMonitor", 0);
    }

    private static void saveWindowPosition() {
        prefs.putInt("framePosX", windowPosition[0]);
        prefs.putInt("framePosY", windowPosition[1]);
        prefs.putInt("activeMonitor", windowPosition[2]);
    }

    private static void startMainWindow() {
        EventQueue.invokeLater(() -> {
            try {
                mainWindow = new MainWindow();
                SwingGUI.setFramePosition(
                        MainWindow.topFrame,
                        windowPosition[0],
                        windowPosition[1],
                        windowPosition[2]
                );
                SwingGUI.setFrameIcon(MainWindow.topFrame, "images/icon32.png", MainWorker.class);
            } catch (Exception ex) {
                if (debug) ex.printStackTrace(System.err);
                System.err.println("Failed to start MainWindow.");
            }
        });
    }

    public static void showDebugConsole() {
        if (debugConsoleWindow == null) {
            debugConsoleWindow = new DebugConsoleWindow(
                    MainWindow.topFrame, MainWindow.fontName,
                    MainWindow.fontSize - 2, prefs, debug, localeManager
            );
            if (debug) System.out.println("Debug console created.");

        } else if (!debugConsoleWindow.isVisible()) {
            debugConsoleWindow.setVisible(true);
            if (debug) System.out.println("Debug console shown.");

        } else {
            EventQueue.invokeLater(() -> debugConsoleWindow.toFront());
            if (debug) System.out.println("Debug console already open.");
        }
    }

    public static MainWindow getInstanceOfMainWindow() {
        return mainWindow;
    }

    public static void checkUpdate() {
        // checks project GitHub for the latest version at launch
        new Thread(() -> UpdateCheckerDialog.showUpdateCheckerDialog(currentVersion, null, debug,
                "https://github.com/everdro1d/jpackage-gui/releases/latest/",
                dro1dDevWebsite + "posts/jpackage-gui/", prefs, localeManager
        )).start(); //TODO webpage doesn't exist yet
    }
}
