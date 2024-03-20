/* dro1dDev SwingGUI Templates - MainWindow.java
 *
 */

package main.com.everdro1d.jpackage.ui;

import com.everdro1d.libs.core.ApplicationCore;
import com.everdro1d.libs.swing.SwingGUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Map;
import java.util.TreeMap;

import static main.com.everdro1d.jpackage.core.CmdSettings.getSubsetOSTypeArray;
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
    private final int WINDOW_HEIGHT = 700;


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
                titleLabel.setFont(new Font(fontName, Font.BOLD, fontSize + 8));
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
                genericOptionsPanel = new GenericOptionsPanel();
                genericOptionsPanel.setPreferredSize(new Dimension(WINDOW_WIDTH - (EDGE_PADDING * 2), 240));
                genericOptionsPanel.setFont(new Font(fontName, Font.PLAIN, fontSize));
                    TitledBorder genericTitledBorder = BorderFactory.createTitledBorder("Generic Options");
                    genericTitledBorder.setTitleFont(new Font(fontName, Font.PLAIN, fontSize + 2));
                    genericTitledBorder.setTitleColor(Color.BLACK);
                    genericTitledBorder.setTitleJustification(TitledBorder.LEFT);
                    genericTitledBorder.setTitlePosition(TitledBorder.TOP);
                genericOptionsPanel.setBorder(genericTitledBorder);
                centerPanel.add(genericOptionsPanel);

                // Add components to centerPanel
                osTabbedPane = new JTabbedPane();
                osTabbedPane.setTabPlacement(JTabbedPane.TOP);
                osTabbedPane.setFont(new Font(fontName, Font.PLAIN, fontSize));
                osTabbedPane.setPreferredSize(new Dimension(WINDOW_WIDTH - (EDGE_PADDING * 2), 280));
                osTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
                    TitledBorder osTitledBorder = BorderFactory.createTitledBorder("OS Specific Options");
                    osTitledBorder.setTitleFont(new Font(fontName, Font.PLAIN, fontSize + 2));
                    osTitledBorder.setTitleColor(Color.BLACK);
                    //osTitledBorder.setTitleJustification(TitledBorder.CENTER);
                    osTitledBorder.setTitlePosition(TitledBorder.TOP);
                osTabbedPane.setBorder(osTitledBorder);
                centerPanel.add(osTabbedPane);
                {
                    //genericOptionsPanel = new GenericOptionsPanel();
                    //osTabbedPane.addTab("Generic", genericOptionsPanel);

                    windowsPanel = new JPanel();
                    osTabbedPane.addTab("Windows", windowsPanel);
                    {
                        // Add components to windowsPanel
                    }

                    macOSPanel = new JPanel();
                    osTabbedPane.addTab("macOS", macOSPanel);
                    {
                        // Add components to macOSPanel
                    }

                    unixPanel = new JPanel();
                    osTabbedPane.addTab("Unix", unixPanel);
                    {
                        // Add components to unixPanel
                    }
                }
                String os = ApplicationCore.detectOS();
                osTabbedPane.setEnabledAt(0, os.equals("Windows"));
                osTabbedPane.setEnabledAt(1, os.equals("macOS"));
                osTabbedPane.setEnabledAt(2, os.equals("Unix"));
                osTabbedPane.setSelectedIndex(os.equals("Windows") ? 0 : os.equals("macOS") ? 1 : 2);

                osTabbedPane.addChangeListener(e -> SwingGUI.setHandCursorToClickableComponents(osTabbedPane));

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

    public static class GenericOptionsPanel extends JPanel {
        private LeftGenericPanel leftGenericPanel;
        private RightGenericPanel rightGenericPanel;

        public GenericOptionsPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.weighty = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(4, 4, 4, 4);

            add((leftGenericPanel = new LeftGenericPanel()), gbc);
            gbc.gridx++;
            add((rightGenericPanel = new RightGenericPanel()), gbc);
        }
    }

    public static class LeftGenericPanel extends JPanel {
        private JLabel nameLabel;
        private JTextField nameTextField;
        private JLabel descriptionLabel;
        private JTextField descriptionTextField;
        private JLabel iconLabel;
        private JTextField iconPathTextField;
        private JLabel vendorLabel;
        private JTextField vendorTextField;
        private JLabel versionLabel;
        private JTextField versionTextField;
        private JLabel copyrightLabel;
        private JTextField copyRightTextField;

        public LeftGenericPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0;
            gbc.weighty = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.VERTICAL;
            gbc.insets = new Insets(4, 4, 4, 4);

            add((nameLabel = new JLabel("Name:")), gbc);
            gbc.gridy++;
            add((descriptionLabel = new JLabel("Description:")), gbc);
            gbc.gridy++;
            add((iconLabel = new JLabel("Icon Path:")), gbc);
            gbc.gridy++;
            add((vendorLabel = new JLabel("Vendor:")), gbc);
            gbc.gridy++;
            add((versionLabel = new JLabel("Version:")), gbc);
            gbc.gridy++;
            add((copyrightLabel = new JLabel("Copyright:")), gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add((nameTextField = new JTextField()), gbc);
            gbc.gridy++;
            add((descriptionTextField = new JTextField()), gbc);
            gbc.gridy++;
            add((iconPathTextField = new JTextField()), gbc);
            gbc.gridy++;
            add((vendorTextField = new JTextField()), gbc);
            gbc.gridy++;
            add((versionTextField = new JTextField()), gbc);
            gbc.gridy++;
            add((copyRightTextField = new JTextField()), gbc);

            for (Component component : getComponents()) {
                if (component instanceof JTextField || component instanceof JLabel) {
                    component.setFont(new Font(fontName, Font.PLAIN, fontSize));
                }
            }
        }

        // getters and setters
        public String getNameText() {
            return nameTextField.getText();
        }

        public String getDescriptionText() {
            return descriptionTextField.getText();
        }

        public String getIconPathText() {
            return iconPathTextField.getText();
        }

        public String getVendorText() {
            return vendorTextField.getText();
        }

        public String getVersionText() {
            return versionTextField.getText();
        }

        public String getCopyRightText() {
            return copyRightTextField.getText();
        }

        public void setNameText(String text) {
            nameTextField.setText(text);
        }

        public void setDescriptionText(String text) {
            descriptionTextField.setText(text);
        }

        public void setIconPathText(String text) {
            iconPathTextField.setText(text);
        }

        public void setVendorText(String text) {
            vendorTextField.setText(text);
        }

        public void setVersionText(String text) {
            versionTextField.setText(text);
        }

        public void setCopyRightText(String text) {
            copyRightTextField.setText(text);
        }
    }

    public static class RightGenericPanel extends JPanel {
        private JLabel typeLabel;
        private JComboBox<String> typeComboBox;
        private JLabel inputLabel; // path is jar or directory
        private JTextField inputPathTextField;
        private JLabel outputLabel;
        private JTextField outputPathTextField;
        private JLabel mainJarLabel;
        private JTextField mainJarTextField;
        private JLabel licenseLabel;
        private JTextField licenseTextField;
        private JLabel aboutLabel;
        private JTextField aboutTextField;

        public RightGenericPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0;
            gbc.weighty = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.VERTICAL;
            gbc.insets = new Insets(4, 4, 4, 4);

            add((typeLabel = new JLabel("Type:")), gbc);
            gbc.gridy++;
            add((inputLabel = new JLabel("Input Path:")), gbc);
            gbc.gridy++;
            add((outputLabel = new JLabel("Output Path:")), gbc);
            gbc.gridy++;
            add((mainJarLabel = new JLabel("Main Jar:")), gbc);
            gbc.gridy++;
            add((licenseLabel = new JLabel("License URL:")), gbc);
            gbc.gridy++;
            add((aboutLabel = new JLabel("About URL:")), gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add((typeComboBox = new JComboBox<>(getSubsetOSTypeArray())), gbc);
            gbc.gridy++;
            add((inputPathTextField = new JTextField()), gbc);
            gbc.gridy++;
            add((outputPathTextField = new JTextField()), gbc);
            gbc.gridy++;
            add((mainJarTextField = new JTextField()), gbc);
            gbc.gridy++;
            add((licenseTextField = new JTextField()), gbc);
            gbc.gridy++;
            add((aboutTextField = new JTextField()), gbc);

            for (Component component : getComponents()) {
                if (component.getFont() != null) {
                    component.setFont(new Font(fontName, Font.PLAIN, fontSize));
                }
            }
        }

        // getters and setters
        public String getTypeText() {
            return (String) typeComboBox.getSelectedItem();
        }

        public String getInputPathText() {
            return inputPathTextField.getText();
        }

        public String getOutputPathText() {
            return outputPathTextField.getText();
        }

        public String getMainJarText() {
            return mainJarTextField.getText();
        }

        public String getLicenseText() {
            return licenseTextField.getText();
        }

        public String getAboutText() {
            return aboutTextField.getText();
        }

        public void setTypeText(String text) {
            typeComboBox.setSelectedItem(text);
        }

        public void setInputPathText(String text) {
            inputPathTextField.setText(text);
        }

        public void setOutputPathText(String text) {
            outputPathTextField.setText(text);
        }

        public void setMainJarText(String text) {
            mainJarTextField.setText(text);
        }

        public void setLicenseText(String text) {
            licenseTextField.setText(text);
        }

        public void setAboutText(String text) {
            aboutTextField.setText(text);
        }
    }
}
