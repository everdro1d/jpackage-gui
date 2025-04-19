package main.com.everdro1d.jpackage.ui.panels;

import main.com.everdro1d.jpackage.ui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

import static main.com.everdro1d.jpackage.core.MainWorker.localeManager;

public class WindowsOptionsPanel extends JPanel {
    // Windows Options Panel ------------------------------------------------------------------------------------------|
    /*
     * --win-console
     *   Launch with console window
     * --win-dir-chooser
     *   Adds a dialog to enable the user to choose a directory in which the product is installed.
     * --win-help-url url
     *   URL where user can obtain further information or technical support
     * --win-menu
     *   Request to add a Start Menu shortcut for this application
     * --win-menu-group menu-group-name
     *   Start Menu group this application is placed in
     * --win-per-user-install
     *   Request to perform an install on a per-user basis
     * --win-shortcut
     *   Request to create a desktop shortcut for this application
     * --win-shortcut-prompt
     *   Adds a dialog to enable the user to choose if shortcuts will be created by installer
     * --win-update-url url
     *   URL of available application update information
     * --win-upgrade-uuid id
     *   UUID associated with upgrades for this package
     */
    // Variables ------------------------------------------------------------------------------------------------------|
    public static String winConsoleLabelText = "Launch with console window:";
    public static String winDirChooserLabelText = "Allow the user to choose a directory in which the product is installed:";
    public static String winHelpUrlLabelText = "URL where user can obtain further information or support:";
    public static String winMenuLabelText = "Request to add a Start Menu shortcut for this application:";
    public static String winMenuGroupLabelText = "Start Menu group this application is placed in:";
    public static String winPerUserInstallLabelText = "Request to perform an install on a per-user basis:";
    public static String winShortcutLabelText = "Request to create a desktop shortcut for this application:";
    public static String winShortcutPromptLabelText = "Allow the user to choose if shortcuts will be created by installer:";
    public static String winUpdateUrlLabelText = "URL of available application update information:";
    public static String winUpgradeUuidLabelText = "UUID associated with upgrades for this package:";

    private JLabel winConsoleLabel;
        private JCheckBox winConsoleCheckBox;
    private JLabel winDirChooserLabel;
        private JCheckBox winDirChooserCheckBox;
    private JLabel winHelpUrlLabel;
        private JTextField winHelpUrlTextField;
    private JLabel winMenuLabel;
        private JCheckBox winMenuCheckBox;
    private JLabel winMenuGroupLabel;
        private JTextField winMenuGroupTextField;
    private JLabel winPerUserInstallLabel;
        private JCheckBox winPerUserInstallCheckBox;
    private JLabel winShortcutLabel;
        private JCheckBox winShortcutCheckBox;
    private JLabel winShortcutPromptLabel;
        private JCheckBox winShortcutPromptCheckBox;
    private JLabel winUpdateUrlLabel;
        private JTextField winUpdateUrlTextField;
    private JLabel winUpgradeUuidLabel;
        private JTextField winUpgradeUuidTextField;

    // End of Variables -----------------------------------------------------------------------------------------------|

    // Constructors ---------------------------------------------------------------------------------------------------|
    public WindowsOptionsPanel() {
        if (!localeManager.getComponentsInClassMap("MainWindow").contains("WindowsOptionsPanel")) {
            addClassToLocale();
        }
        useLocale();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);
        int widthPx = 290;

        // Col 0
        winConsoleLabel = new JLabel("<html><div style='width:" + widthPx + "px'>" +
                winConsoleLabelText +
                "</div></html>"
        );
        add(winConsoleLabel, gbc);
        gbc.gridy++;

        winDirChooserLabel = new JLabel("<html><div style='width:" + widthPx + "px'>" +
                winDirChooserLabelText +
                "</div></html>"
        );
        add(winDirChooserLabel, gbc);
        gbc.gridy++;

        winHelpUrlLabel = new JLabel("<html><div style='width:" + widthPx + "px'>" +
                winHelpUrlLabelText +
                "</div></html>"
        );
        add(winHelpUrlLabel, gbc);
        gbc.gridy++;

        winMenuLabel = new JLabel("<html><div style='width:" + widthPx + "px'>" +
                winMenuLabelText +
                "</div></html>"
        );
        add(winMenuLabel, gbc);
        gbc.gridy++;

        winMenuGroupLabel = new JLabel("<html><div style='width:" + widthPx + "px'>" +
                winMenuGroupLabelText +
                "</div></html>"
        );
        add(winMenuGroupLabel, gbc);
        gbc.gridy++;

        winPerUserInstallLabel = new JLabel("<html><div style='width:" + widthPx + "px'>" +
                winPerUserInstallLabelText +
                "</div></html>"
        );
        add(winPerUserInstallLabel, gbc);
        gbc.gridy++;

        winShortcutLabel = new JLabel("<html><div style='width:" + widthPx + "px'>" +
                winShortcutLabelText +
                "</div></html>"
        );
        add(winShortcutLabel, gbc);
        gbc.gridy++;

        winShortcutPromptLabel = new JLabel("<html><div style='width:" + widthPx + "px'>" +
                winShortcutPromptLabelText +
                "</div></html>"
        );
        add(winShortcutPromptLabel, gbc);
        gbc.gridy++;

        winUpdateUrlLabel = new JLabel("<html><div style='width:" + widthPx + "px'>" +
                winUpdateUrlLabelText +
                "</div></html>"
        );
        add(winUpdateUrlLabel, gbc);
        gbc.gridy++;

        gbc.weighty = 1;
        winUpgradeUuidLabel = new JLabel("<html><div style='width:" + widthPx + "px'>" +
                winUpgradeUuidLabelText +
                "</div></html>"
        );
        add(winUpgradeUuidLabel, gbc);

        // Col 1
        gbc.gridx++;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add((winConsoleCheckBox = new JCheckBox()), gbc);
        gbc.gridy++;

        add((winDirChooserCheckBox = new JCheckBox()), gbc);
        gbc.gridy++;

        add((winHelpUrlTextField = new JTextField()), gbc);
        gbc.gridy++;

        add((winMenuCheckBox = new JCheckBox()), gbc);
        gbc.gridy++;

        add((winMenuGroupTextField = new JTextField()), gbc);
        gbc.gridy++;

        add((winPerUserInstallCheckBox = new JCheckBox()), gbc);
        gbc.gridy++;

        add((winShortcutCheckBox = new JCheckBox()), gbc);
        gbc.gridy++;

        add((winShortcutPromptCheckBox = new JCheckBox()), gbc);
        gbc.gridy++;

        add((winUpdateUrlTextField = new JTextField()), gbc);
        gbc.gridy++;

        gbc.weighty = 1;
        add((winUpgradeUuidTextField = new JTextField()), gbc);

        for (Component component : getComponents()) {
            if (component.getFont() != null) {
                component.setFont(new Font(MainWindow.fontName, Font.PLAIN, MainWindow.fontSize));
            }
        }

    }

    private void addClassToLocale() {
        Map<String, String> map = new TreeMap<>();
        // left generic panel
        map.put("winConsoleLabelText", winConsoleLabelText);
        map.put("winDirChooserLabelText", winDirChooserLabelText);
        map.put("winHelpUrlLabelText", winHelpUrlLabelText);
        map.put("winMenuLabelText", winMenuLabelText);
        map.put("winMenuGroupLabelText", winMenuGroupLabelText);
        map.put("winPerUserInstallLabelText", winPerUserInstallLabelText);
        map.put("winShortcutLabelText", winShortcutLabelText);
        map.put("winShortcutPromptLabelText", winShortcutPromptLabelText);
        map.put("winUpdateUrlLabelText", winUpdateUrlLabelText);
        map.put("winUpgradeUuidLabelText", winUpgradeUuidLabelText);

        localeManager.addComponentSpecificMap("MainWindow","WindowsOptionsPanel", map);
    }

    private void useLocale() {
        Map<String, String> varMap =
                localeManager.getComponentSpecificMap("MainWindow","WindowsOptionsPanel");

        winConsoleLabelText = varMap.getOrDefault("winConsoleLabelText", winConsoleLabelText);
        winDirChooserLabelText = varMap.getOrDefault("winDirChooserLabelText", winDirChooserLabelText);
        winHelpUrlLabelText = varMap.getOrDefault("winHelpUrlLabelText", winHelpUrlLabelText);
        winMenuLabelText = varMap.getOrDefault("winMenuLabelText", winMenuLabelText);
        winMenuGroupLabelText = varMap.getOrDefault("winMenuGroupLabelText", winMenuGroupLabelText);
        winPerUserInstallLabelText = varMap.getOrDefault("winPerUserInstallLabelText", winPerUserInstallLabelText);
        winShortcutLabelText = varMap.getOrDefault("winShortcutLabelText", winShortcutLabelText);
        winShortcutPromptLabelText = varMap.getOrDefault("winShortcutPromptLabelText", winShortcutPromptLabelText);
        winUpdateUrlLabelText = varMap.getOrDefault("winUpdateUrlLabelText", winUpdateUrlLabelText);
        winUpgradeUuidLabelText = varMap.getOrDefault("winUpgradeUuidLabelText", winUpgradeUuidLabelText);
    }

    // Getter and Setters ---------------------------------------------------------------------------------------------|

    public JCheckBox getWinConsoleCheckBox() {
        return winConsoleCheckBox;
    }

    public JCheckBox getWinDirChooserCheckBox() {
        return winDirChooserCheckBox;
    }

    public JTextField getWinHelpUrlTextField() {
        return winHelpUrlTextField;
    }

    public JCheckBox getWinMenuCheckBox() {
        return winMenuCheckBox;
    }

    public JTextField getWinMenuGroupTextField() {
        return winMenuGroupTextField;
    }

    public JCheckBox getWinPerUserInstallCheckBox() {
        return winPerUserInstallCheckBox;
    }

    public JCheckBox getWinShortcutCheckBox() {
        return winShortcutCheckBox;
    }

    public JCheckBox getWinShortcutPromptCheckBox() {
        return winShortcutPromptCheckBox;
    }

    public JTextField getWinUpdateUrlTextField() {
        return winUpdateUrlTextField;
    }

    public JTextField getWinUpgradeUuidTextField() {
        return winUpgradeUuidTextField;
    }

    public boolean getWinConsoleCheckBoxSelected() {
        return winConsoleCheckBox.isSelected();
    }

    public boolean getWinDirChooserCheckBoxSelected() {
        return winDirChooserCheckBox.isSelected();
    }

    public String getWinHelpUrlTextFieldText() {
        return winHelpUrlTextField.getText();
    }

    public boolean getWinMenuCheckBoxSelected() {
        return winMenuCheckBox.isSelected();
    }

    public String getWinMenuGroupTextFieldText() {
        return winMenuGroupTextField.getText();
    }

    public boolean getWinPerUserInstallCheckBoxSelected() {
        return winPerUserInstallCheckBox.isSelected();
    }

    public boolean getWinShortcutCheckBoxSelected() {
        return winShortcutCheckBox.isSelected();
    }

    public boolean getWinShortcutPromptCheckBoxSelected() {
        return winShortcutPromptCheckBox.isSelected();
    }

    public String getWinUpdateUrlTextFieldText() {
        return winUpdateUrlTextField.getText();
    }

    public String getWinUpgradeUuidTextFieldText() {
        return winUpgradeUuidTextField.getText();
    }

    public void setWinConsoleCheckBoxSelected(boolean selected) {
        winConsoleCheckBox.setSelected(selected);
    }

    public void setWinDirChooserCheckBoxSelected(boolean selected) {
        winDirChooserCheckBox.setSelected(selected);
    }

    public void setWinHelpUrlTextFieldText(String text) {
        winHelpUrlTextField.setText(text);
    }

    public void setWinMenuCheckBoxSelected(boolean selected) {
        winMenuCheckBox.setSelected(selected);
    }

    public void setWinMenuGroupTextFieldText(String text) {
        winMenuGroupTextField.setText(text);
    }

    public void setWinPerUserInstallCheckBoxSelected(boolean selected) {
        winPerUserInstallCheckBox.setSelected(selected);
    }

    public void setWinShortcutCheckBoxSelected(boolean selected) {
        winShortcutCheckBox.setSelected(selected);
    }

    public void setWinShortcutPromptCheckBoxSelected(boolean selected) {
        winShortcutPromptCheckBox.setSelected(selected);
    }

    public void setWinUpdateUrlTextFieldText(String text) {
        winUpdateUrlTextField.setText(text);
    }

    public void setWinUpgradeUuidTextFieldText(String text) {
        winUpgradeUuidTextField.setText(text);
    }

    // End of Getter and Setters --------------------------------------------------------------------------------------|
}
