/* dro1dDev SwingGUI Templates - MainWindow.java
 *
 */

package main.com.everdro1d.jpackage.ui;

import com.everdro1d.libs.swing.SwingGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Map;
import java.util.TreeMap;

import static main.com.everdro1d.jpackage.core.MainWorker.localeManager;
import static main.com.everdro1d.jpackage.core.MainWorker.windowPosition;

public class MainWindow extends JFrame {
    // Variables ------------------------------------------------------------------------------------------------------|

    // Swing components - Follow tab hierarchy for organization -----------|
    public static JFrame topFrame;
        private JPanel mainPanel;

    // End of Swing components --------------------------------------------|

    private String titleText = "Template Application - MainWindow";

    public static String fontName = "Tahoma";
    // Font name for the application
    public static int fontSize = 16;
    // Font size for the application

    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 400;


    // End of variables -----------------------------------------------------------------------------------------------|

    public MainWindow() {
        // if the locale does not contain the class, add it and it's components
        if (!localeManager.getClassesInLocaleMap().contains("MainWindow")) {
            addClassToLocale();
        }
        useLocale();

        initializeWindowProperties();
        initializeGUIComponents();

        topFrame.setVisible(true);

        SwingGUI.setHandCursorToClickableComponents(topFrame);
    }

    private void addClassToLocale() {
        Map<String, Map<String, String>> map = new TreeMap<>();
        map.put("Main", new TreeMap<>());
        Map<String, String> mainMap = map.get("Main");
        mainMap.put("titleText", titleText);

        localeManager.addClassSpecificMap("MainWindow", map);
    }

    private void useLocale() {
        Map<String, String> varMap = localeManager.getAllVariablesWithinClassSpecificMap("MainWindow");
        titleText = varMap.getOrDefault("titleText", titleText);
    }

    /**
     * Initialize the window.
     */
    private void initializeWindowProperties() {
        topFrame = this;
        topFrame.setTitle(titleText);
        SwingGUI.setFrameIcon(topFrame, "images/icon32.png", this.getClass());
        topFrame.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topFrame.setResizable(false);
        topFrame.setLocationRelativeTo(null);

        topFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                windowPosition = SwingGUI.getFramePositionOnScreen(topFrame);
            }
        });
    }

    /**
     * Initialize the GUI components.
     */
    private void initializeGUIComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        topFrame.add(mainPanel);
        {
            // Add components here
        }
    }
}
