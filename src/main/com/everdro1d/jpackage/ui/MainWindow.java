/* dro1dDev SwingGUI Templates - MainWindow.java
 *
 */

package main.com.everdro1d.jpackage.ui;

import com.everdro1d.libs.core.ApplicationCore;
import com.everdro1d.libs.swing.SwingGUI;
import com.everdro1d.libs.swing.components.CollapsableTitledBorder;
import com.everdro1d.libs.swing.components.TextFieldFileChooser;
import main.com.everdro1d.jpackage.core.ButtonAction;
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
            private JSeparator titleSeparator;
        private JPanel centerPanel;
            private JPanel jdkBinPanel;
                private JLabel jdkBinLabel;
                private TextFieldFileChooser jdkBinTextField;
            private JPanel genericOptionsPanel;
            public static JTabbedPane osTabbedPane;
                private JPanel windowsPanel;
                private JPanel macOSPanel;
                private JPanel unixPanel;
        private JPanel southPanel;
            private JSeparator buttonSeparator;
            private JPanel buttonPanel;
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
    private final int GENERIC_OPTION_PANEL_HEIGHT = 500; // expanded height
    private final int OS_OPTION_PANEL_HEIGHT = 500; // expanded height

    // End of variables -----------------------------------------------------------------------------------------------|

    public MainWindow() {
        // if the locale does not contain the class, add it and it's components
//        if (!localeManager.getClassesInLocaleMap().contains("MainWindow")) {
//            addClassToLocale();
//        } TODO: re-enable when done with gui, and localize panels.
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
                titleSeparator = new JSeparator();
                titleSeparator.setPreferredSize(new Dimension(WINDOW_WIDTH - (EDGE_PADDING * 4), 4));
                northPanel.add(titleSeparator, gbc);

            }

            centerPanel = new JPanel();
            mainPanel.add(centerPanel, BorderLayout.CENTER);
            {
                // Path to jdk bin
                jdkBinPanel = new JPanel();
                jdkBinPanel.setPreferredSize(new Dimension(WINDOW_WIDTH - (EDGE_PADDING * 4), 40));
                jdkBinPanel.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 0;
                c.weightx = 0.05;
                c.weighty = 0;
                c.anchor = GridBagConstraints.WEST;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.insets = new Insets(4, 4, 4, 4);
                centerPanel.add(jdkBinPanel);
                {
                    jdkBinLabel = new JLabel("Path to the JDK's \"/bin\" directory:");
                    jdkBinLabel.setFont(new Font(fontName, Font.PLAIN, fontSize));
                    jdkBinPanel.add(jdkBinLabel, c);

                    c.gridx++;
                    c.weightx = 0.95;
                    jdkBinTextField = new TextFieldFileChooser(localeManager, false, true);
                    jdkBinTextField.setFont(new Font(fontName, Font.PLAIN, fontSize));
                    jdkBinPanel.add(jdkBinTextField, c);
                }

                // Option Panels
                boolean exclusive = true;

                genericOptionsPanel = new GenericOptionsPanel();
                genericOptionsPanel.setPreferredSize(new Dimension(WINDOW_WIDTH - (EDGE_PADDING * 2), GENERIC_OPTION_PANEL_HEIGHT));
                genericOptionsPanel.setFont(new Font(fontName, Font.PLAIN, fontSize));
                centerPanel.add(genericOptionsPanel);

                CollapsableTitledBorder b = new CollapsableTitledBorder(
                        genericOptionsPanel, "Generic Options", true,
                        exclusive, GENERIC_OPTION_PANEL_HEIGHT, this::enableTabbedPaneWithOS);
                b.setTitleFont(new Font(fontName, Font.PLAIN, fontSize + 2));
                genericOptionsPanel.setBorder(b);

                // Add components to centerPanel
                osTabbedPane = new JTabbedPane();
                osTabbedPane.setTabPlacement(JTabbedPane.TOP);
                osTabbedPane.setFont(new Font(fontName, Font.PLAIN, fontSize));
                osTabbedPane.setPreferredSize(new Dimension(WINDOW_WIDTH - (EDGE_PADDING * 2), OS_OPTION_PANEL_HEIGHT));
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
                osTabbedPane.addChangeListener(e -> SwingGUI.setHandCursorToClickableComponents(osTabbedPane));

                CollapsableTitledBorder b2 = new CollapsableTitledBorder(
                        osTabbedPane, "OS Specific Options", false,
                        exclusive, OS_OPTION_PANEL_HEIGHT, this::enableTabbedPaneWithOS);
                b2.setTitleFont(new Font(fontName, Font.PLAIN, fontSize + 2));
                osTabbedPane.setBorder(b2);
            }

            southPanel = new JPanel();
            southPanel.setPreferredSize(new Dimension(WINDOW_WIDTH - (EDGE_PADDING * 2), 50));
            southPanel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 0;
            c.weighty = 1;
            mainPanel.add(southPanel, BorderLayout.SOUTH);
            {
                buttonSeparator = new JSeparator();
                buttonSeparator.setPreferredSize(new Dimension(WINDOW_WIDTH - (EDGE_PADDING * 4), 4));
                southPanel.add(buttonSeparator, c);

                c.gridy++;
                buttonPanel = new JPanel();
                buttonPanel.setPreferredSize(new Dimension(WINDOW_WIDTH - (EDGE_PADDING * 4), 40));
                buttonPanel.setLayout(new GridBagLayout());
                GridBagConstraints gbc2 = new GridBagConstraints();
                gbc2.gridx = 0;
                gbc2.gridy = 0;
                gbc2.weightx = 0.5;
                gbc2.weighty = 0;
                gbc2.anchor = GridBagConstraints.WEST;
                gbc2.fill = GridBagConstraints.HORIZONTAL;
                gbc2.insets = new Insets(4, 4, 4, 4);
                southPanel.add(buttonPanel, c);
                {
                    // Add components to southPanel
                    // save settings to file
                    saveSettingsButton = new JButton("Save Settings");
                    saveSettingsButton.setFont(new Font(fontName, Font.PLAIN, fontSize));
                    buttonPanel.add(saveSettingsButton, gbc2);
                    saveSettingsButton.addActionListener((e) -> ButtonAction.saveSettingsToFile());

                    gbc2.gridx++;
                    // load settings from file
                    loadSettingsButton = new JButton("Load Settings");
                    loadSettingsButton.setFont(new Font(fontName, Font.PLAIN, fontSize));
                    buttonPanel.add(loadSettingsButton, gbc2);
                    loadSettingsButton.addActionListener((e) -> ButtonAction.loadSettingsFromFile());

                    gbc2.gridx++;
                    // run command
                    runCommandButton = new JButton("Create Installer");
                    runCommandButton.setFont(new Font(fontName, Font.PLAIN, fontSize));
                    buttonPanel.add(runCommandButton, gbc2);
                    runCommandButton.addActionListener((e) -> ButtonAction.assembleAndRunJPackageCommand());
                }
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
        tabbedPane.setEnabledAt(0, os.equals("Windows"));
        tabbedPane.setEnabledAt(1, os.equals("macOS"));
        tabbedPane.setEnabledAt(2, os.equals("Unix"));
        tabbedPane.setSelectedIndex(os.equals("Windows") ? 0 : os.equals("macOS") ? 1 : 2);
    }

    public String getJdkBinPathText() {
        return jdkBinTextField.getText();
    }

    public void setJdkBinPathText(String text) {
        jdkBinTextField.setText(text);
    }
}
