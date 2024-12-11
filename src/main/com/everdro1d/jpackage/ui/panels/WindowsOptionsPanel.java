package main.com.everdro1d.jpackage.ui.panels;

import main.com.everdro1d.jpackage.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

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
    private JLabel winConsoleLabel;
        private String winConsoleLabelText = "Launch with console window:";
        private JCheckBox winConsoleCheckBox;
    private JLabel winDirChooserLabel;
        private String winDirChooserLabelText = "Adds a dialog to enable the user to choose a directory in which the product is installed.";
        private JCheckBox winDirChooserCheckBox;
    private JLabel winHelpUrlLabel;
        private String winHelpUrlLabelText = "URL where user can obtain further information or technical support:";
        private JTextField winHelpUrlTextField;
    private JLabel winMenuLabel;
        private String winMenuLabelText = "Request to add a Start Menu shortcut for this application:";
        private JCheckBox winMenuCheckBox;
    private JLabel winMenuGroupLabel;
        private String winMenuGroupLabelText = "Start Menu group this application is placed in:";
        private JTextField winMenuGroupTextField;
    private JLabel winPerUserInstallLabel;
        private String winPerUserInstallLabelText = "Request to perform an install on a per-user basis:";
        private JCheckBox winPerUserInstallCheckBox;
    private JLabel winShortcutLabel;
        private String winShortcutLabelText = "Request to create a desktop shortcut for this application:";
        private JCheckBox winShortcutCheckBox;
    private JLabel winShortcutPromptLabel;
        private String winShortcutPromptLabelText = "Adds a dialog to enable the user to choose if shortcuts will be created by installer:";
        private JCheckBox winShortcutPromptCheckBox;
    private JLabel winUpdateUrlLabel;
        private String winUpdateUrlLabelText = "URL of available application update information:";
        private JTextField winUpdateUrlTextField;
    private JLabel winUpgradeUuidLabel;
        private String winUpgradeUuidLabelText = "UUID associated with upgrades for this package:";
        private JTextField winUpgradeUuidTextField;

    // End of Variables -----------------------------------------------------------------------------------------------|

    // Constructors ---------------------------------------------------------------------------------------------------|
    public WindowsOptionsPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);
        int widthPx = 290;

        // Col 0
        winConsoleLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
                        winConsoleLabelText +
                        "</div></html>"
        );
        add(winConsoleLabel, gbc);
        gbc.gridy++;

        winDirChooserLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
                        winDirChooserLabelText +
                        "</div></html>"
        );
        add(winDirChooserLabel, gbc);
        gbc.gridy++;

        winHelpUrlLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
                        winHelpUrlLabelText +
                        "</div></html>"
        );
        add(winHelpUrlLabel, gbc);
        gbc.gridy++;

        winMenuLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
                        winMenuLabelText +
                        "</div></html>"
        );
        add(winMenuLabel, gbc);
        gbc.gridy++;

        winMenuGroupLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
                        winMenuGroupLabelText +
                        "</div></html>"
        );
        add(winMenuGroupLabel, gbc);
        gbc.gridy++;

        winPerUserInstallLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
                        winPerUserInstallLabelText +
                        "</div></html>"
        );
        add(winPerUserInstallLabel, gbc);
        gbc.gridy++;

        winShortcutLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
                        winShortcutLabelText +
                        "</div></html>"
        );
        add(winShortcutLabel, gbc);
        gbc.gridy++;

        winShortcutPromptLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
                        winShortcutPromptLabelText +
                        "</div></html>"
        );
        add(winShortcutPromptLabel, gbc);
        gbc.gridy++;

        winUpdateUrlLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
                        winUpdateUrlLabelText +
                        "</div></html>"
        );
        add(winUpdateUrlLabel, gbc);
        gbc.gridy++;

        gbc.weighty = 1;
        winUpgradeUuidLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
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

    // Getter and Setters ---------------------------------------------------------------------------------------------|

    public boolean isWinConsole() {
        return winConsoleCheckBox.isSelected();
    }

    public boolean isWinDirChooser() {
        return winDirChooserCheckBox.isSelected();
    }

    public String getWinHelpUrlText() {
        return winHelpUrlTextField.getText();
    }

    public boolean isWinMenu() {
        return winMenuCheckBox.isSelected();
    }

    public String getWinMenuGroupText() {
        return winMenuGroupTextField.getText();
    }

    public boolean isWinPerUserInstall() {
        return winPerUserInstallCheckBox.isSelected();
    }

    public boolean isWinShortcut() {
        return winShortcutCheckBox.isSelected();
    }

    public boolean isWinShortcutPrompt() {
        return winShortcutPromptCheckBox.isSelected();
    }

    public String getWinUpdateUrlText() {
        return winUpdateUrlTextField.getText();
    }

    public String getWinUpgradeUuidText() {
        return winUpgradeUuidTextField.getText();
    }

    public void setWinConsole(boolean selected) {
        winConsoleCheckBox.setSelected(selected);
    }

    public void setWinDirChooser(boolean selected) {
        winDirChooserCheckBox.setSelected(selected);
    }

    public void setWinHelpUrlText(String text) {
        winHelpUrlTextField.setText(text);
    }

    public void setWinMenu(boolean selected) {
        winMenuCheckBox.setSelected(selected);
    }

    public void setWinMenuGroupText(String text) {
        winMenuGroupTextField.setText(text);
    }

    public void setWinPerUserInstall(boolean selected) {
        winPerUserInstallCheckBox.setSelected(selected);
    }

    public void setWinShortcut(boolean selected) {
        winShortcutCheckBox.setSelected(selected);
    }

    public void setWinShortcutPrompt(boolean selected) {
        winShortcutPromptCheckBox.setSelected(selected);
    }

    public void setWinUpdateUrlText(String text) {
        winUpdateUrlTextField.setText(text);
    }

    public void setWinUpgradeUuidText(String text) {
        winUpgradeUuidTextField.setText(text);
    }

    // End of Getter and Setters --------------------------------------------------------------------------------------|
}
