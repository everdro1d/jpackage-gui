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
    private JTextField linuxPackageNameTextField;
    private JLabel linuxDebMaintainerLabel;
    private JTextField linuxDebMaintainerTextField;
    private JLabel linuxMenuGroupLabel;
    private JTextField linuxMenuGroupTextField;
    private JLabel linuxPackageDepsLabel;
    private JTextField linuxPackageDepsTextField;
    private JLabel linuxRpmLicenseTypeLabel;
    private JTextField linuxRpmLicenseTypeTextField;
    private JLabel linuxAppReleaseLabel;
    private JTextField linuxAppReleaseTextField;
    private JLabel linuxAppCategoryLabel;
    private JTextField linuxAppCategoryTextField;
    private JLabel linuxShortcutLabel;
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
        linuxPackageNameLabel = new JLabel("<html><div style='width:"+widthPx+"px'>Name for Linux package:</div></html>");
        add(linuxPackageNameLabel, gbc);
        gbc.gridy++;

        linuxDebMaintainerLabel = new JLabel("<html><div style='width:"+widthPx+"px'>Maintainer for .deb bundle:</div></html>");
        add(linuxDebMaintainerLabel, gbc);
        gbc.gridy++;

        linuxMenuGroupLabel = new JLabel("<html><div style='width:"+widthPx+"px'>Menu group this application is placed in:</div></html>");
        add(linuxMenuGroupLabel, gbc);
        gbc.gridy++;

        linuxPackageDepsLabel = new JLabel("<html><div style='width:"+widthPx+"px'>Required packages or capabilities for the application:</div></html>");
        add(linuxPackageDepsLabel, gbc);
        gbc.gridy++;

        linuxRpmLicenseTypeLabel = new JLabel("<html><div style='width:"+widthPx+"px'>Type of the license:</div></html>");
        add(linuxRpmLicenseTypeLabel, gbc);
        gbc.gridy++;

        linuxAppReleaseLabel = new JLabel("<html><div style='width:"+widthPx+"px'>Release value of the RPM <name>.spec file or Debian revision value of the DEB control file:</div></html>");
        add(linuxAppReleaseLabel, gbc);
        gbc.gridy++;

        linuxAppCategoryLabel = new JLabel("<html><div style='width:"+widthPx+"px'>Group value of the RPM /.spec file or Section value of DEB control file:</div></html>");
        add(linuxAppCategoryLabel, gbc);
        gbc.gridy++;

        gbc.weighty = 1;
        linuxShortcutLabel = new JLabel("<html><div style='width:"+widthPx+"px'>Creates a shortcut for the application:</div></html>");
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

        add((linuxPackageDepsTextField = new JTextField()), gbc);
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

    public String getLinuxPackageName() {
        return linuxPackageNameTextField.getText();
    }

    public String getLinuxDebMaintainer() {
        return linuxDebMaintainerTextField.getText();
    }

    public String getLinuxMenuGroup() {
        return linuxMenuGroupTextField.getText();
    }

    public String getLinuxPackageDeps() {
        return linuxPackageDepsTextField.getText();
    }

    public String getLinuxRpmLicenseType() {
        return linuxRpmLicenseTypeTextField.getText();
    }

    public String getLinuxAppRelease() {
        return linuxAppReleaseTextField.getText();
    }

    public String getLinuxAppCategory() {
        return linuxAppCategoryTextField.getText();
    }

    public boolean isLinuxShortcut() {
        return linuxShortcutCheckBox.isSelected();
    }

    public void setLinuxPackageName(String linuxPackageName) {
        linuxPackageNameTextField.setText(linuxPackageName);
    }

    public void setLinuxDebMaintainer(String linuxDebMaintainer) {
        linuxDebMaintainerTextField.setText(linuxDebMaintainer);
    }

    public void setLinuxMenuGroup(String linuxMenuGroup) {
        linuxMenuGroupTextField.setText(linuxMenuGroup);
    }

    public void setLinuxPackageDeps(String linuxPackageDeps) {
        linuxPackageDepsTextField.setText(linuxPackageDeps);
    }

    public void setLinuxRpmLicenseType(String linuxRpmLicenseType) {
        linuxRpmLicenseTypeTextField.setText(linuxRpmLicenseType);
    }

    public void setLinuxAppRelease(String linuxAppRelease) {
        linuxAppReleaseTextField.setText(linuxAppRelease);
    }

    public void setLinuxAppCategory(String linuxAppCategory) {
        linuxAppCategoryTextField.setText(linuxAppCategory);
    }

    public void setLinuxShortcut(boolean linuxShortcut) {
        linuxShortcutCheckBox.setSelected(linuxShortcut);
    }

    // End of Getters and Setters -------------------------------------------------------------------------------------|
}
