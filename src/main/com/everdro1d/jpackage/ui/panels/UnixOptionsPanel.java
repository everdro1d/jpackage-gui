package main.com.everdro1d.jpackage.ui.panels;

import main.com.everdro1d.jpackage.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class UnixOptionsPanel extends JPanel {
    // Unix Options Panel ---------------------------------------------------------------------------------------------|
    /*
     * --linux-package-name name
     *   Name for Linux package
     *   Defaults to the application name.
     * --linux-deb-maintainer email-address
     *   Maintainer for .deb bundle
     * --linux-menu-group menu-group-name
     *   Menu group this application is placed in
     * --linux-package-deps
     *   Required packages or capabilities for the application
     * --linux-rpm-license-type type
     *   Type of the license ("License: value" of the RPM .spec)
     * --linux-app-release release
     *   Release value of the RPM <name>.spec file or Debian revision value of the DEB control file
     * --linux-app-category category-value
     *   Group value of the RPM /.spec file or Section value of DEB control file
     * --linux-shortcut
     *   Creates a shortcut for the application.
     */
    // Variables ------------------------------------------------------------------------------------------------------|
    private JLabel linuxPackageNameLabel;
        private String linuxPackageNameLabelText = "Name of Linux package:";
        private JTextField linuxPackageNameTextField;
    private JLabel linuxDebMaintainerLabel;
        private String linuxDebMaintainerLabelText = "Maintainer of .deb bundle:";
        private JTextField linuxDebMaintainerTextField;
    private JLabel linuxMenuGroupLabel;
        private String linuxMenuGroupLabelText = "Menu group this application is placed in:";
        private JTextField linuxMenuGroupTextField;
    private JLabel linuxPackageDependenciesLabel;
        private String linuxPackageDependenciesLabelText = "Required packages or capabilities for the application:";
        private JTextField linuxPackageDependenciesTextField;
    private JLabel linuxRpmLicenseTypeLabel;
        private String linuxRpmLicenseTypeLabelText = "License type:";
        private JTextField linuxRpmLicenseTypeTextField;
    private JLabel linuxAppReleaseLabel;
        private String linuxAppReleaseLabelText = "Release value of the RPM <name>.spec file or Debian revision value of the DEB control file:";
        private JTextField linuxAppReleaseTextField;
    private JLabel linuxAppCategoryLabel;
        private String linuxAppCategoryLabelText = "Group value of the RPM /.spec file or Section value of DEB control file:";
        private JTextField linuxAppCategoryTextField;
    private JLabel linuxShortcutLabel;
        private String linuxShortcutLabelText = "Whether to create a shortcut for the application:";
        private JCheckBox linuxShortcutCheckBox;

    // End of Variables -----------------------------------------------------------------------------------------------|

    // Constructors ---------------------------------------------------------------------------------------------------|
    public UnixOptionsPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);
        int widthPx = 290;

        // Col 0
        linuxPackageNameLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
                        linuxPackageNameLabelText +
                        "</div></html>"
        );
        add(linuxPackageNameLabel, gbc);
        gbc.gridy++;

        linuxDebMaintainerLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
                        linuxDebMaintainerLabelText +
                        "</div></html>"
        );
        add(linuxDebMaintainerLabel, gbc);
        gbc.gridy++;

        linuxMenuGroupLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
                        linuxMenuGroupLabelText +
                        "</div></html>"
        );
        add(linuxMenuGroupLabel, gbc);
        gbc.gridy++;

        linuxPackageDependenciesLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
                        linuxPackageDependenciesLabelText +
                        "</div></html>"
        );
        add(linuxPackageDependenciesLabel, gbc);
        gbc.gridy++;

        linuxRpmLicenseTypeLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
                        linuxRpmLicenseTypeLabelText +
                        "</div></html>"
        );
        add(linuxRpmLicenseTypeLabel, gbc);
        gbc.gridy++;

        linuxAppReleaseLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
                        linuxAppReleaseLabelText +
                        "</div></html>"
        );
        add(linuxAppReleaseLabel, gbc);
        gbc.gridy++;

        linuxAppCategoryLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
                        linuxAppCategoryLabelText +
                        "</div></html>"
        );
        add(linuxAppCategoryLabel, gbc);
        gbc.gridy++;

        gbc.weighty = 1;
        linuxShortcutLabel = new JLabel(
                "<html><div style='width:"+widthPx+"px'>" +
                        linuxShortcutLabelText +
                        "</div></html>"
        );
        add(linuxShortcutLabel, gbc);

        // Col 1
        gbc.gridx++;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add((linuxPackageNameTextField = new JTextField()), gbc);
        gbc.gridy++;

        add((linuxDebMaintainerTextField = new JTextField()), gbc);
        gbc.gridy++;

        add((linuxMenuGroupTextField = new JTextField()), gbc);
        gbc.gridy++;

        add((linuxPackageDependenciesTextField = new JTextField()), gbc);
        gbc.gridy++;

        add((linuxRpmLicenseTypeTextField = new JTextField()), gbc);
        gbc.gridy++;

        add((linuxAppReleaseTextField = new JTextField()), gbc);
        gbc.gridy++;

        add((linuxAppCategoryTextField = new JTextField()), gbc);
        gbc.gridy++;

        gbc.weighty = 1;
        add((linuxShortcutCheckBox = new JCheckBox()), gbc);

        for (Component component : getComponents()) {
            if (component.getFont() != null) {
                component.setFont(new Font(MainWindow.fontName, Font.PLAIN, MainWindow.fontSize));
            }
        }
    }

    // Getters and Setters --------------------------------------------------------------------------------------------|

    public String getLinuxPackageNameText() {
        return linuxPackageNameTextField.getText();
    }

    public String getLinuxDebMaintainerText() {
        return linuxDebMaintainerTextField.getText();
    }

    public String getLinuxMenuGroupText() {
        return linuxMenuGroupTextField.getText();
    }

    public String getLinuxPackageDependenciesText() {
        return linuxPackageDependenciesTextField.getText();
    }

    public String getLinuxRpmLicenseTypeText() {
        return linuxRpmLicenseTypeTextField.getText();
    }

    public String getLinuxAppReleaseText() {
        return linuxAppReleaseTextField.getText();
    }

    public String getLinuxAppCategoryText() {
        return linuxAppCategoryTextField.getText();
    }

    public boolean isLinuxDesktopShortcut() {
        return linuxShortcutCheckBox.isSelected();
    }

    public void setLinuxPackageNameText(String linuxPackageName) {
        linuxPackageNameTextField.setText(linuxPackageName);
    }

    public void setLinuxDebMaintainerText(String linuxDebMaintainer) {
        linuxDebMaintainerTextField.setText(linuxDebMaintainer);
    }

    public void setLinuxMenuGroupText(String linuxMenuGroup) {
        linuxMenuGroupTextField.setText(linuxMenuGroup);
    }

    public void setLinuxPackageDependenciesText(String linuxPackageDependencies) {
        linuxPackageDependenciesTextField.setText(linuxPackageDependencies);
    }

    public void setLinuxRpmLicenseTypeText(String linuxRpmLicenseType) {
        linuxRpmLicenseTypeTextField.setText(linuxRpmLicenseType);
    }

    public void setLinuxAppReleaseText(String linuxAppRelease) {
        linuxAppReleaseTextField.setText(linuxAppRelease);
    }

    public void setLinuxAppCategoryText(String linuxAppCategory) {
        linuxAppCategoryTextField.setText(linuxAppCategory);
    }

    public void setLinuxDesktopShortcut(boolean linuxShortcut) {
        linuxShortcutCheckBox.setSelected(linuxShortcut);
    }

    // End of Getters and Setters -------------------------------------------------------------------------------------|
}
