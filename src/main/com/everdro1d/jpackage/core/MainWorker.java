/* dro1dDev SwingGUI Templates - MainWorker.java
 *
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
    // CommandManager object for handling CLI commands

    private static String currentLocale = "eng";

    public static final LocaleManager localeManager = new LocaleManager(MainWorker.class);
    // LocaleManager object for handling locale changes. loads default locale on creation

    public static boolean debug = false;
    // Boolean to enable debug logging output all 'sout' statements must be wrapped in 'if (debug)'

    public static DebugConsoleWindow debugConsoleWindow;

    static final Preferences prefs = Preferences.userNodeForPackage(MainWorker.class);
    // Preferences object for saving and loading user settings

    public static int[] windowPosition = {0, 0, 0};
    // Default window position

    // End of variables -----------------------------------------------------------------------------------------------|

    public static void main(String[] args) {
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
            System.out.println("Detected OS: " + ApplicationCore.detectOS());
        }

        startMainWindow();

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
        executeOSSpecificCode(detectedOS);
    }

    /**
     * Execute OS specific code.
     * @param detectedOS the detected OS
     * @see #checkOSCompatibility()
     */
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
                System.out.println("Unknown OS detected. Exiting.");
                System.exit(1);
            }
        }
    }

    private static void loadPreferencesAndQueueSave() {
        loadWindowPosition();
        currentLocale = prefs.get("currentLocale", "eng");

        savePreferencesOnExit();
    }

    /**
     * Save the user settings on exit.
     */
    private static void savePreferencesOnExit() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            saveWindowPosition();
            prefs.put("currentLocale", currentLocale);
        }));
    }

    /**
     * Load the window position from the preferences. And save the position on exit.
     */
    private static void loadWindowPosition() {
        windowPosition[0] = prefs.getInt("framePosX", 0);
        windowPosition[1] = prefs.getInt("framePosY", 0);
        windowPosition[2] = prefs.getInt("activeMonitor", 0);
    }

    /**
     * Save the window position to the preferences.
     */
    private static void saveWindowPosition() {
        prefs.putInt("framePosX", windowPosition[0]);
        prefs.putInt("framePosY", windowPosition[1]);
        prefs.putInt("activeMonitor", windowPosition[2]);
    }

    /**
     * Start the MainWindow.
     */
    private static void startMainWindow() {
        EventQueue.invokeLater(() -> {
            try {
                new MainWindow();
                SwingGUI.setFramePosition(
                        MainWindow.topFrame,
                        windowPosition[0], windowPosition[1], windowPosition[2]
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

    public static void checkUpdate() {
        // checks project GitHub for the latest version at launch
        new Thread(() -> UpdateCheckerDialog.showUpdateCheckerDialog(currentVersion, null, debug,
                "https://github.com/everdro1d/jpackage-gui/releases/latest/",
                dro1dDevWebsite + "posts/jpackage-gui/", prefs, localeManager
        )).start(); //TODO webpage doesn't exist yet
    }
}
