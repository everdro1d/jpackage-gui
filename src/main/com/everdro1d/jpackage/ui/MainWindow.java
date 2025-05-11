package main.com.everdro1d.jpackage.ui;

import com.everdro1d.libs.swing.ImageUtils;
import com.everdro1d.libs.swing.SwingGUI;
import com.everdro1d.libs.swing.components.CollapsableTitledBorder;
import com.everdro1d.libs.swing.components.TextFieldFileChooser;
import com.everdro1d.libs.swing.dialogs.SimpleWorkingDialog;
import main.com.everdro1d.jpackage.core.ButtonAction;
import main.com.everdro1d.jpackage.core.MainWorker;
import main.com.everdro1d.jpackage.ui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import static main.com.everdro1d.jpackage.core.ButtonAction.interruptProcess;
import static main.com.everdro1d.jpackage.core.ButtonAction.showSettingsWindow;
import static main.com.everdro1d.jpackage.core.MainWorker.*;

public class MainWindow extends JFrame {
    // Variables ------------------------------------------------------------------------------------------------------|

    // Swing components - Follow tab hierarchy for organization -----------|
    public static JFrame topFrame;
    private JPanel mainPanel;
        private JPanel northPanel;
            private JLabel logoIconContainer;
            private JLabel titleLabel;
            private JButton settingsButton;
        private JPanel centerPanel;
            private JPanel jdkBinPanel;
                private JLabel jdkBinLabel;
                private TextFieldFileChooser jdkBinTextField;
                private JPanel genericOptionsPanel;
            private JTabbedPane osTabbedPane;
                private JPanel windowsPanel;
                private JPanel macOSPanel;
                private JPanel unixPanel;
        private JPanel southPanel;
            private JCheckBox useMonolithOptionFileCheckBox;
            private JSeparator buttonSeparator;
            private JPanel buttonPanel;
                private JButton saveSettingsButton;
                private JButton loadSettingsButton;
                private JButton runCommandButton;
        private JPanel eastPanel;
        private JPanel westPanel;

    private static SimpleWorkingDialog workingDialog;

    // End of Swing components --------------------------------------------|

    public static String titleText = "JPackage GUI";
    public static String jdkBinLabelText = "Path to JDK \"/bin\" directory:";
    public static String genericOptionsPanelTitleText = "Generic Options";
    public static String windowsTabText = "Windows";
    public static String macOSTabText = "MacOS";
    public static String unixTabText = "Unix";
    public static String osTabbedPaneTitleText = "OS Specific Options";
    public static String useMonolithOptionFileCheckBoxText = "Use Monolith Option File";
    public static String saveSettingsButtonText = "Save Options";
    public static String loadSettingsButtonText = "Load Options";
    public static String runCommandButtonText = "Create Installer";

    public static String fontName = SwingGUI.getDefaultFontNameForOS();
    // Font name for the application
    public static int fontSize = detectedOS.equals("unix") ? 17 : 16;
    // Font size for the application

    private final int WINDOW_WIDTH = 800;
    private final int EDGE_PADDING = 15;
    private final int WINDOW_HEIGHT = 750;
    private final int OPTION_PANEL_HEIGHT = detectedOS.equals("mac") ? 475 : detectedOS.equals("unix") ? 500 : 465; // expanded height

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
        mainMap.put("jdkBinLabelText", jdkBinLabelText);
        mainMap.put("genericOptionsPanelTitleText", genericOptionsPanelTitleText);
        mainMap.put("windowsTabText", windowsTabText);
        mainMap.put("macOSTabText", macOSTabText);
        mainMap.put("unixTabText", unixTabText);
        mainMap.put("osTabbedPaneTitleText", osTabbedPaneTitleText);
        mainMap.put("useMonolithOptionFileCheckBoxText", useMonolithOptionFileCheckBoxText);
        mainMap.put("saveSettingsButtonText", saveSettingsButtonText);
        mainMap.put("loadSettingsButtonText", loadSettingsButtonText);
        mainMap.put("runCommandButtonText", runCommandButtonText);

        localeManager.addClassSpecificMap("MainWindow", map);
    }

    private void useLocale() {
        Map<String, String> varMap = localeManager.getAllVariablesWithinClassSpecificMap("MainWindow");
        titleText = varMap.getOrDefault("titleText", titleText);
        jdkBinLabelText = varMap.getOrDefault("jdkBinLabelText", jdkBinLabelText);
        genericOptionsPanelTitleText = varMap.getOrDefault(
                "genericOptionsPanelTitleText", genericOptionsPanelTitleText
        );
        windowsTabText = varMap.getOrDefault("windowsTabText", windowsTabText);
        macOSTabText = varMap.getOrDefault("macOSTabText", macOSTabText);
        unixTabText = varMap.getOrDefault("unixTabText", unixTabText);
        osTabbedPaneTitleText = varMap.getOrDefault("osTabbedPaneTitleText", osTabbedPaneTitleText);
        useMonolithOptionFileCheckBoxText = varMap.getOrDefault("useMonolithOptionFileCheckBoxText", useMonolithOptionFileCheckBoxText);
        saveSettingsButtonText = varMap.getOrDefault("saveSettingsButtonText", saveSettingsButtonText);
        loadSettingsButtonText = varMap.getOrDefault("loadSettingsButtonText", loadSettingsButtonText);
        runCommandButtonText = varMap.getOrDefault("runCommandButtonText", runCommandButtonText);

    }

    private void initializeWindowProperties() {
        topFrame = this;
        topFrame.setTitle(titleText);
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
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.anchor = GridBagConstraints.LINE_END;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(4, EDGE_PADDING + 50 - 8, 4, 4);
            mainPanel.add(northPanel, BorderLayout.NORTH);
            {
                // Add components to northPanel
                logoIconContainer = new JLabel();
                logoIconContainer.setPreferredSize(new Dimension(50, 50));
                Icon logoIcon = ImageUtils.getApplicationIcon("images/icon50.png", this.getClass());
                logoIconContainer.setIcon(logoIcon);
                logoIconContainer.setHorizontalAlignment(SwingConstants.RIGHT);
                northPanel.add(logoIconContainer, gbc);

                gbc.gridx++;
                gbc.weightx = 1;
                gbc.anchor = GridBagConstraints.LINE_START;
                gbc.insets = new Insets(4, 4, 4, 4);

                titleLabel = new JLabel(titleText);
                titleLabel.setFont(new Font(fontName, Font.BOLD, fontSize + 12));
                titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
                northPanel.add(titleLabel, gbc);

                gbc.gridx++;
                gbc.weightx = 0;
                gbc.fill = GridBagConstraints.NONE;
                gbc.anchor = GridBagConstraints.LINE_END;
                gbc.insets = new Insets(4, 4, 4, detectedOS.equals("mac") ? ((EDGE_PADDING*2) - 8) : EDGE_PADDING);

                settingsButton = new JButton();
                settingsButton.setPreferredSize(new Dimension(50, 50));
                Icon settingsIcon = ImageUtils.getApplicationIcon("images/button-icons/settings.png", this.getClass());
                settingsButton.setIcon(settingsIcon);
                settingsButton.setBorderPainted(false);
                settingsButton.setContentAreaFilled(false);
                northPanel.add(settingsButton, gbc);

                settingsButton.addActionListener(e -> showSettingsWindow());
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
                    jdkBinLabel = new JLabel(jdkBinLabelText);
                    jdkBinLabel.setFont(new Font(fontName, Font.PLAIN, fontSize));
                    jdkBinPanel.add(jdkBinLabel, c);

                    c.gridx++;
                    c.weightx = 0.95;
                    jdkBinTextField = new TextFieldFileChooser(localeManager, false, true);
                    setJdkBinPath(jdkDirectory);
                    jdkBinTextField.setFont(new Font(fontName, Font.PLAIN, fontSize));
                    jdkBinPanel.add(jdkBinTextField, c);

                    jdkBinTextField.getTextField().addKeyListener(new KeyListener() {
                        @Override
                        public void keyTyped(KeyEvent e) {}
                        @Override
                        public void keyPressed(KeyEvent e) {}

                        @Override
                        public void keyReleased(KeyEvent e) {
                            jdkDirectory = getJdkBinPath();
                        }
                    });
                }

                // Option Panels
                boolean exclusive = true;

                genericOptionsPanel = new GenericOptionsPanel();
                genericOptionsPanel.setPreferredSize(new Dimension(
                        WINDOW_WIDTH - (EDGE_PADDING * 4), OPTION_PANEL_HEIGHT)
                );
                genericOptionsPanel.setFont(new Font(fontName, Font.PLAIN, fontSize));
                centerPanel.add(genericOptionsPanel);

                CollapsableTitledBorder b = new CollapsableTitledBorder(
                        genericOptionsPanel, genericOptionsPanelTitleText, true,
                        exclusive, OPTION_PANEL_HEIGHT);
                b.setTitleFont(new Font(fontName, Font.PLAIN, fontSize + 2));
                genericOptionsPanel.setBorder(b);

                // Add components to centerPanel
                osTabbedPane = new JTabbedPane();
                osTabbedPane.setTabPlacement(JTabbedPane.TOP);
                osTabbedPane.setFont(new Font(fontName, Font.PLAIN, fontSize));
                osTabbedPane.setPreferredSize(new Dimension(
                        WINDOW_WIDTH - (EDGE_PADDING * 4), OPTION_PANEL_HEIGHT)
                );
                osTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
                centerPanel.add(osTabbedPane);
                {
                    windowsPanel = new WindowsOptionsPanel();
                    osTabbedPane.addTab(windowsTabText, windowsPanel);

                    macOSPanel = new MacOSOptionsPanel();
                    osTabbedPane.addTab(macOSTabText, macOSPanel);

                    unixPanel = new UnixOptionsPanel();
                    osTabbedPane.addTab(unixTabText, unixPanel);
                }
                osTabbedPane.addChangeListener(e -> SwingGUI.setHandCursorToClickableComponents(osTabbedPane));

                CollapsableTitledBorder b2 = new CollapsableTitledBorder(
                        osTabbedPane, osTabbedPaneTitleText, false,
                        exclusive, OPTION_PANEL_HEIGHT, this::enableTabbedPaneWithOS, true);
                b2.setTitleFont(new Font(fontName, Font.PLAIN, fontSize + 2));
                osTabbedPane.setBorder(b2);
            }

            southPanel = new JPanel();
            southPanel.setPreferredSize(new Dimension(WINDOW_WIDTH - (EDGE_PADDING * 2), 80));
            southPanel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 0;
            c.weighty = 1;
            mainPanel.add(southPanel, BorderLayout.SOUTH);
            {
                useMonolithOptionFileCheckBox = new JCheckBox(useMonolithOptionFileCheckBoxText);
                useMonolithOptionFileCheckBox.setFont(new Font(fontName, Font.PLAIN, fontSize));
                useMonolithOptionFileCheckBox.setSelected(useMonolithOptionFile);
                southPanel.add(useMonolithOptionFileCheckBox, c);

                useMonolithOptionFileCheckBox.addActionListener(e -> {
                    useMonolithOptionFile = useMonolithOptionFileCheckBox.isSelected();
                    if (debug) System.out.println("Monolith Option File: " + useMonolithOptionFile);
                    enableTabbedPaneWithOS(osTabbedPane);
                });

                c.gridy++;
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
                gbc2.weightx = 1;
                gbc2.weighty = 0;
                gbc2.anchor = GridBagConstraints.CENTER;
                gbc2.fill = GridBagConstraints.HORIZONTAL;
                gbc2.insets = new Insets(4, 4, 4, 4);
                southPanel.add(buttonPanel, c);
                {
                    // Add components to southPanel
                    // save settings to file
                    saveSettingsButton = new JButton(saveSettingsButtonText);
                    saveSettingsButton.setFont(new Font(fontName, Font.PLAIN, fontSize));
                    buttonPanel.add(saveSettingsButton, gbc2);
                    saveSettingsButton.addActionListener((e) -> ButtonAction.saveSettingsToFile());

                    gbc2.gridx++;
                    // load settings from file
                    loadSettingsButton = new JButton(loadSettingsButtonText);
                    loadSettingsButton.setFont(new Font(fontName, Font.PLAIN, fontSize));
                    buttonPanel.add(loadSettingsButton, gbc2);
                    loadSettingsButton.addActionListener((e) -> ButtonAction.loadSettingsFromFile());

                    gbc2.gridx++;
                    // run command
                    runCommandButton = new JButton(runCommandButtonText);
                    runCommandButton.setFont(new Font(fontName, Font.PLAIN, fontSize));
                    buttonPanel.add(runCommandButton, gbc2);
                    runCommandButton.addActionListener((e) -> {
                        if (debug) System.out.println("Running JPackage Command.");
                        ButtonAction.assembleAndRunJPackageCommand();

                        workingDialog = new SimpleWorkingDialog(
                                "Creating Installer...", localeManager
                        ) {
                            @Override
                            public void onCancel() {
                                interruptProcess();
                            }
                        };
                        workingDialog.showDialog(topFrame, true);
                    });
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
        tabbedPane.setEnabledAt(0, MainWorker.detectedOS.equals("windows") || useMonolithOptionFile);
        tabbedPane.setEnabledAt(1, MainWorker.detectedOS.equals("mac") || useMonolithOptionFile);
        tabbedPane.setEnabledAt(2, MainWorker.detectedOS.equals("unix") || useMonolithOptionFile);
        tabbedPane.setSelectedIndex(
                isOSTabbedPaneExpanded()
                ? MainWorker.detectedOS.equals("windows")
                    ? 0 : MainWorker.detectedOS.equals("mac")
                    ? 1 : 2
                : -1
        );
    }

    private boolean isOSTabbedPaneExpanded() {
        return !(osTabbedPane.getTabComponentAt(0) instanceof JLabel);
    }

    public String getJdkBinPath() {
        return jdkBinTextField.getText();
    }

    public void setJdkBinPath(String text) {
        String s = File.separator;
        String binPath;
        if (detectedOS.equals("macOS") && !text.contains("Contents"+s+"Home")) {
            binPath = s + "Contents" + s + "Home" + s + "bin";
        } else {
            binPath = s + "bin" + s;
        }

        if (!text.endsWith(binPath)) {
            text = text + binPath;
        }
        jdkBinTextField.setText(text);
    }

    public boolean isMonolithOptionFile() {
        return useMonolithOptionFile;
    }

    public void setMonolithOptionFile(boolean useMonolithOptionFile) {
        MainWorker.useMonolithOptionFile = useMonolithOptionFile;
        useMonolithOptionFileCheckBox.setSelected(MainWorker.useMonolithOptionFile);
    }

    public Object getInstanceOf(Class<?> clazz, MainWindow instance) {
        switch(clazz.getName()) {
            case "main.com.everdro1d.jpackage.ui.panels.GenericOptionsPanel" -> {
                return instance.genericOptionsPanel;
            }
            case "main.com.everdro1d.jpackage.ui.panels.WindowsOptionsPanel" -> {
                return instance.windowsPanel;
            }
            case "main.com.everdro1d.jpackage.ui.panels.MacOSOptionsPanel" -> {
                return instance.macOSPanel;
            }
            case "main.com.everdro1d.jpackage.ui.panels.UnixOptionsPanel" -> {
                return instance.unixPanel;
            }
            default -> {
                return null;
            }
        }
    }

    public static SimpleWorkingDialog getWorkingDialog() {
        return workingDialog;
    }

    public void darkModeSwitch() {
        SwingGUI.switchLightOrDarkMode(darkMode, windowFrameArray);

        Icon i = ImageUtils.changeIconColor(
                settingsButton.getIcon(),
                UIManager.getColor("RootPane.foreground")
        );
        settingsButton.setIcon(i);

        Icon logoIcon = ImageUtils.getApplicationIcon("images/icon50.png", this.getClass());
        logoIconContainer.setIcon(darkMode ? ImageUtils.darkenIcon(logoIcon) : logoIcon);
    }
}
