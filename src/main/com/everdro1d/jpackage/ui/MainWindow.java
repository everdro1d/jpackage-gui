/* dro1dDev SwingGUI Templates - MainWindow.java
 *
 */

package main.com.everdro1d.jpackage.ui;

import com.everdro1d.libs.core.ApplicationCore;
import com.everdro1d.libs.swing.SwingGUI;
import com.everdro1d.libs.swing.components.CollapsableTitledBorder;
import main.com.everdro1d.jpackage.ui.panels.*;

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
        private JPanel northPanel;
            private JLabel titleLabel;
        private JPanel centerPanel;
            private JPanel genericOptionsPanel;
            public static JTabbedPane osTabbedPane;
                private JPanel windowsPanel;
                private JPanel macOSPanel;
                private JPanel unixPanel;
        private JPanel southPanel;
            private JButton saveSettingsButton;
            private JButton loadSettingsButton;
            private JButton runCommandButton;
        private JPanel eastPanel;
        private JPanel westPanel;

    // End of Swing components --------------------------------------------|

    private String titleText = "JPackage GUI";
    public static String fontName = "Tahoma";
    // Font name for the application
    public static int fontSize = 16;
    // Font size for the application

    private final int WINDOW_WIDTH = 800;
    private final int EDGE_PADDING = 15;
    private final int WINDOW_HEIGHT = 750;


    // End of variables -----------------------------------------------------------------------------------------------|

    public MainWindow() {
        // if the locale does not contain the class, add it and it's components
//        if (!localeManager.getClassesInLocaleMap().contains("MainWindow")) {
//            addClassToLocale();
//        } TODO re-enable when done with gui
//        useLocale();

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
            northPanel = new JPanel();
            northPanel.setLayout(new GridBagLayout());
            northPanel.setPreferredSize(new Dimension(WINDOW_WIDTH - (EDGE_PADDING * 2), 60));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0;
            gbc.weighty = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(4, 4, 4, 4);
            mainPanel.add(northPanel, BorderLayout.NORTH);
            {
                // Add components to northPanel
                titleLabel = new JLabel(titleText);
                titleLabel.setFont(new Font(fontName, Font.BOLD, fontSize + 12));
                titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                northPanel.add(titleLabel, gbc);

                gbc.gridy++;
                JSeparator separator = new JSeparator();
                separator.setPreferredSize(new Dimension(WINDOW_WIDTH - (EDGE_PADDING * 4), 4));
                northPanel.add(separator, gbc);

            }

            centerPanel = new JPanel();
            mainPanel.add(centerPanel, BorderLayout.CENTER);
            {
                boolean exclusive = true;

                genericOptionsPanel = new GenericOptionsPanel();
                genericOptionsPanel.setPreferredSize(new Dimension(WINDOW_WIDTH - (EDGE_PADDING * 2), 270));
                genericOptionsPanel.setFont(new Font(fontName, Font.PLAIN, fontSize));
                centerPanel.add(genericOptionsPanel);

                CollapsableTitledBorder b = new CollapsableTitledBorder(
                        genericOptionsPanel, "Generic Options", true,
                        exclusive, 270, 50);
                b.setTitleFont(new Font(fontName, Font.PLAIN, fontSize + 2));
                genericOptionsPanel.setBorder(b);

                // Add components to centerPanel
                osTabbedPane = new JTabbedPane();
                osTabbedPane.setTabPlacement(JTabbedPane.TOP);
                osTabbedPane.setFont(new Font(fontName, Font.PLAIN, fontSize));
                osTabbedPane.setPreferredSize(new Dimension(WINDOW_WIDTH - (EDGE_PADDING * 2), 520));
                osTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
                centerPanel.add(osTabbedPane);
                {
                    windowsPanel = new WindowsOptionsPanel();
                    osTabbedPane.addTab("Windows", windowsPanel);

                    macOSPanel = new MacOSOptionsPanel();
                    osTabbedPane.addTab("macOS", macOSPanel);

                    unixPanel = new UnixOptionsPanel();
                    osTabbedPane.addTab("Unix", unixPanel);
                }

                enableTabbedPaneWithOS(osTabbedPane);
                osTabbedPane.addChangeListener(e -> SwingGUI.setHandCursorToClickableComponents(osTabbedPane));

                CollapsableTitledBorder b2 = new CollapsableTitledBorder(
                        osTabbedPane, "OS Specific Options", false,
                        exclusive, 520, 50);
                b2.setTitleFont(new Font(fontName, Font.PLAIN, fontSize + 2));
                osTabbedPane.setBorder(b2);

            }

            southPanel = new JPanel();
            southPanel.setPreferredSize(new Dimension(WINDOW_WIDTH - (EDGE_PADDING * 2), 55));
            mainPanel.add(southPanel, BorderLayout.SOUTH);
            {
                // Add components to southPanel
                // save settings to file
                saveSettingsButton = new JButton("Save Settings");
                saveSettingsButton.setFont(new Font(fontName, Font.PLAIN, fontSize));
                southPanel.add(saveSettingsButton);

                // load settings from file
                loadSettingsButton = new JButton("Load Settings");
                loadSettingsButton.setFont(new Font(fontName, Font.PLAIN, fontSize));
                southPanel.add(loadSettingsButton);

                // run command
                runCommandButton = new JButton("Create Installer");
                runCommandButton.setFont(new Font(fontName, Font.PLAIN, fontSize));
                southPanel.add(runCommandButton);

            }

            eastPanel = new JPanel();
            eastPanel.setPreferredSize(new Dimension(EDGE_PADDING, 10));
            mainPanel.add(eastPanel, BorderLayout.EAST);

            westPanel = new JPanel();
            westPanel.setPreferredSize(new Dimension(EDGE_PADDING, 10));
            mainPanel.add(westPanel, BorderLayout.WEST);
        }
    }

    private void enableTabbedPaneWithOS(JTabbedPane tabbedPane) {
        String os = ApplicationCore.detectOS();
//        tabbedPane.setEnabledAt(0, os.equals("Windows")); TODO: re-enable when done with panels
//        tabbedPane.setEnabledAt(1, os.equals("macOS"));
//        tabbedPane.setEnabledAt(2, os.equals("Unix"));
        tabbedPane.setSelectedIndex(os.equals("Windows") ? 0 : os.equals("macOS") ? 1 : 2);
    }

}
