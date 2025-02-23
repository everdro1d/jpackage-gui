/* dro1dDev SwingGUI Templates - MainWorker.java
 * requires everdro1dCoreLib, flatlaf, and json libraries
 * TODO
 *  - Detect and autofill jdk bin directory
 *  - save jdk directory to preferences
 *  - actually let buttons do something
 *  - add command assembly line
 */

package main.com.everdro1d.jpackage.core;

import com.everdro1d.libs.commands.CommandInterface;
import com.everdro1d.libs.commands.CommandManager;
import com.everdro1d.libs.core.ApplicationCore;
import com.everdro1d.libs.core.LocaleManager;
import com.everdro1d.libs.swing.SwingGUI;
import com.everdro1d.libs.swing.components.DebugConsoleWindow;
import main.com.everdro1d.jpackage.core.commands.DebugCommand;
import main.com.everdro1d.jpackage.ui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.prefs.Preferences;

public class MainWorker {
    // Variables ------------------------------------------------------------------------------------------------------|
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

    private static MainWindow mainWindow;

    // End of variables -----------------------------------------------------------------------------------------------|

    public static void main(String[] args) {
        startUpActions(args);
        startMainWindow();
    }

    private static void startUpActions(String[] args) {
        ApplicationCore.checkCLIArgs(args, commandManager);
        checkOSCompatibility();

        SwingGUI.setLookAndFeel(true, false);
        SwingGUI.lightOrDarkMode(false, new JFrame[]{MainWindow.topFrame});
        // include all non-modal frames in the array. these frames will be updated when the mode is changed

        SwingGUI.uiSetup(false, MainWindow.fontName, MainWindow.fontSize);
        loadPreferences();

        localeManager.loadLocaleFromFile("locale_" + currentLocale);
        if (debug) showDebugConsole();
    }

    /**
     * Detects the OS to determine compat with application and dependencies.
     * @see #executeOSSpecificCode(String)
     */
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

    /**
     * Load the user settings from the preferences. And save the settings on exit.
     */
    private static void loadPreferences() {
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
                mainWindow = new MainWindow();
                SwingGUI.setFramePosition(
                        MainWindow.topFrame,
                        windowPosition[0], windowPosition[1], windowPosition[2]
                );
            } catch (Exception ex) {
                if (debug) ex.printStackTrace(System.err);
                System.err.println("Failed to start MainWindow.");
            }
        });
    }

    public static void showDebugConsole() {
        if (debugConsoleWindow == null) {
            debugConsoleWindow = new DebugConsoleWindow(
                    MainWindow.topFrame, MainWindow.fontName, MainWindow.fontSize - 2, prefs, debug, localeManager);
            if (debug) System.out.println("Debug console created.");
        } else if (!debugConsoleWindow.isVisible()) {
            debugConsoleWindow.setVisible(true);
            if (debug) System.out.println("Debug console shown.");
        } else {
            if (debug) System.out.println("Debug console already open.");
        }
    }

    public static MainWindow getInstanceOfMainWindow() {
        return mainWindow;
    }
}
