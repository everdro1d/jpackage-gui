package main.com.everdro1d.jpackage.ui.panels;

import main.com.everdro1d.jpackage.ui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

import static main.com.everdro1d.jpackage.core.MainWorker.localeManager;

public class MacOSOptionsPanel extends JPanel {
    // macOS Options Panel --------------------------------------------------------------------------------------------|
    /*
     * --mac-package-identifier identifier
     *   An identifier that uniquely identifies the application for macOS
     *   Defaults to the main class name.
     *   May only use alphanumeric (A-Z,a-z,0-9), hyphen (-), and period (.) characters.
     * --mac-package-name name
     *   Name of the application as it appears in the Menu Bar
     *   This can be different from the application name.
     *   This name must be less than 16 characters long and be suitable for displaying in the menu bar and the application Info window. Defaults to the application name.
     * --mac-package-signing-prefix prefix
     *   When signing the application package, this value is prefixed to all components that need to be signed that don't have an existing package identifier.
     * --mac-sign
     *   Request that the package or the predefined application image be signed.
     * --mac-signing-keychain keychain-name
     *   Name of the keychain to search for the signing identity
     *   If not specified, the standard keychains are used.
     * --mac-signing-key-user-name name
     *   Team or username portion in Apple signing identities
     * --mac-app-store
     *   Indicates that the jpackage output is intended for the Mac App Store.
     * --mac-entitlements path
     *   Path to file containing entitlements to use when signing executables and libraries in the bundle
     * --mac-app-category category
     *   String used to construct LSApplicationCategoryType in application plist
     *   The default value is "utilities".
     */

    // Variables ------------------------------------------------------------------------------------------------------|
    public static String macPackageIdentifierLabelText =
            "An identifier that uniquely identifies the application for macOS:";
    public static String macPackageNameLabelText =
            "Name of the application as it appears in the Menu Bar:";
    public static String macPackageSigningPrefixLabelText =
            "Component package identifier prefix:";
    public static String macSignLabelText =
            "Request that the package be signed:";
    public static String macSigningKeychainLabelText =
            "Name of the keychain to search for the signing identity:";
    public static String macSigningKeyUsernameLabelText =
            "Team or username portion in Apple signing identities:";
    public static String macAppStoreLabelText =
            "Indicates that the jpackage output is intended for the Mac App Store:";
    public static String macEntitlementsLabelText =
            "Path to file containing entitlements to use:";
    public static String macAppCategoryLabelText =
            "String used to construct LSApplicationCategoryType in application plist:";

    private JLabel macPackageIdentifierLabel;
        private JTextField macPackageIdentifierTextField;
    private JLabel macPackageNameLabel;
        private JTextField macPackageNameTextField;
    private JLabel macPackageSigningPrefixLabel;
        private JTextField macPackageSigningPrefixTextField;
    private JLabel macSignLabel;
        private JCheckBox macSignCheckBox;
    private JLabel macSigningKeychainLabel;
        private JTextField macSigningKeychainTextField;
    private JLabel macSigningKeyUsernameLabel;
        private JTextField macSigningKeyUsernameTextField;
    private JLabel macAppStoreLabel;
        private JCheckBox macAppStoreCheckBox;
    private JLabel macEntitlementsLabel;
        private JTextField macEntitlementsTextField;
    private JLabel macAppCategoryLabel;
        private JTextField macAppCategoryTextField;

    // End of Variables -----------------------------------------------------------------------------------------------|

    // Constructors ---------------------------------------------------------------------------------------------------|
    public MacOSOptionsPanel() {
        if (!localeManager.getComponentsInClassMap("MainWindow").contains("MacOSOptionsPanel")) {
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
        macPackageNameLabel = new JLabel("<html><div style='width:"+widthPx+"px'>" +
                macPackageNameLabelText +
                "</div></html>"
        );
        add(macPackageNameLabel, gbc);
        gbc.gridy++;

        macPackageIdentifierLabel = new JLabel("<html><div style='width:"+widthPx+"px'>" +
                macPackageIdentifierLabelText +
                "</div></html>"
        );
        add(macPackageIdentifierLabel, gbc);
        gbc.gridy++;

        macPackageSigningPrefixLabel = new JLabel("<html><div style='width:"+widthPx+"px'>" +
                macPackageSigningPrefixLabelText +
                "</div></html>"
        );
        add(macPackageSigningPrefixLabel, gbc);
        gbc.gridy++;

        macSignLabel = new JLabel("<html><div style='width:"+widthPx+"px'>" +
                macSignLabelText +
                "</div></html>"
        );
        add(macSignLabel, gbc);
        gbc.gridy++;

        macSigningKeychainLabel = new JLabel("<html><div style='width:"+widthPx+"px'>" +
                macSigningKeychainLabelText +
                "</div></html>"
        );
        add(macSigningKeychainLabel, gbc);
        gbc.gridy++;

        macSigningKeyUsernameLabel = new JLabel("<html><div style='width:"+widthPx+"px'>" +
                macSigningKeyUsernameLabelText +
                "</div></html>"
        );
        add(macSigningKeyUsernameLabel, gbc);
        gbc.gridy++;

        macAppStoreLabel = new JLabel("<html><div style='width:"+widthPx+"px'>" +
                macAppStoreLabelText +
                "</div></html>"
        );
        add(macAppStoreLabel, gbc);
        gbc.gridy++;

        macEntitlementsLabel = new JLabel("<html><div style='width:"+widthPx+"px'>" +
                macEntitlementsLabelText +
                "</div></html>"
        );
        add(macEntitlementsLabel, gbc);
        gbc.gridy++;

        gbc.weighty = 1;
        macAppCategoryLabel = new JLabel("<html><div style='width:"+widthPx+"px'>" +
                macAppCategoryLabelText +
                "</div></html>"
        );
        add(macAppCategoryLabel, gbc);

        // Col 1
        gbc.gridx++;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add((macPackageNameTextField = new JTextField()), gbc);
        gbc.gridy++;

        add((macPackageIdentifierTextField = new JTextField()), gbc);
        gbc.gridy++;

        add((macPackageSigningPrefixTextField = new JTextField()), gbc);
        gbc.gridy++;

        add((macSignCheckBox = new JCheckBox()), gbc);
        gbc.gridy++;

        add((macSigningKeychainTextField = new JTextField()), gbc);
        gbc.gridy++;

        add((macSigningKeyUsernameTextField = new JTextField()), gbc);
        gbc.gridy++;

        add((macAppStoreCheckBox = new JCheckBox()), gbc);
        gbc.gridy++;

        add((macEntitlementsTextField = new JTextField()), gbc);
        gbc.gridy++;

        gbc.weighty = 1;
        add((macAppCategoryTextField = new JTextField()), gbc);

        for (Component component : getComponents()) {
            if (component.getFont() != null) {
                component.setFont(new Font(MainWindow.fontName, Font.PLAIN, MainWindow.fontSize));
            }
        }
    }

    private void addClassToLocale() {
        Map<String, String> map = new TreeMap<>();
        // left generic panel
        map.put("macPackageIdentifierLabelText", macPackageIdentifierLabelText);
        map.put("macPackageNameLabelText", macPackageNameLabelText);
        map.put("macPackageSigningPrefixLabelText", macPackageSigningPrefixLabelText);
        map.put("macSignLabelText", macSignLabelText);
        map.put("macSigningKeychainLabelText", macSigningKeychainLabelText);
        map.put("macSigningKeyUserNameLabelText", macSigningKeyUsernameLabelText);
        map.put("macAppStoreLabelText", macAppStoreLabelText);
        map.put("macEntitlementsLabelText", macEntitlementsLabelText);
        map.put("macAppCategoryLabelText", macAppCategoryLabelText);

        localeManager.addComponentSpecificMap("MainWindow","MacOSOptionsPanel", map);
    }

    private void useLocale() {
        Map<String, String> varMap =
                localeManager.getComponentSpecificMap("MainWindow","MacOSOptionsPanel");

        macPackageIdentifierLabelText = varMap.getOrDefault("macPackageIdentifierLabelText", macPackageIdentifierLabelText);
        macPackageNameLabelText = varMap.getOrDefault("macPackageNameLabelText", macPackageNameLabelText);
        macPackageSigningPrefixLabelText = varMap.getOrDefault("macPackageSigningPrefixLabelText", macPackageSigningPrefixLabelText);
        macSignLabelText = varMap.getOrDefault("macSignLabelText", macSignLabelText);
        macSigningKeychainLabelText = varMap.getOrDefault("macSigningKeychainLabelText", macSigningKeychainLabelText);
        macSigningKeyUsernameLabelText = varMap.getOrDefault("macSigningKeyUserNameLabelText", macSigningKeyUsernameLabelText);
        macAppStoreLabelText = varMap.getOrDefault("macAppStoreLabelText", macAppStoreLabelText);
        macEntitlementsLabelText = varMap.getOrDefault("macEntitlementsLabelText", macEntitlementsLabelText);
        macAppCategoryLabelText = varMap.getOrDefault("macAppCategoryLabelText", macAppCategoryLabelText);

    }

    // Getters and Setters --------------------------------------------------------------------------------------------|
    public String getMacPackageIdentifier() {
        return macPackageIdentifierTextField.getText();
    }

    public String getMacPackageName() {
        return macPackageNameTextField.getText();
    }

    public String getMacPackageSigningPrefix() {
        return macPackageSigningPrefixTextField.getText();
    }

    public boolean isMacSignPackage() {
        return macSignCheckBox.isSelected();
    }

    public String getMacSigningKeychain() {
        return macSigningKeychainTextField.getText();
    }

    public String getMacSigningKeyUsername() {
        return macSigningKeyUsernameTextField.getText();
    }

    public boolean isMacAppStore() {
        return macAppStoreCheckBox.isSelected();
    }

    public String getMacEntitlements() {
        return macEntitlementsTextField.getText();
    }

    public String getMacAppCategory() {
        return macAppCategoryTextField.getText();
    }

    public void setMacPackageIdentifier(String text) {
        macPackageIdentifierTextField.setText(text);
    }

    public void setMacPackageName(String text) {
        macPackageNameTextField.setText(text);
    }

    public void setMacPackageSigningPrefix(String text) {
        macPackageSigningPrefixTextField.setText(text);
    }

    public void setMacSignPackage(boolean checked) {
        macSignCheckBox.setSelected(checked);
    }

    public void setMacSigningKeychain(String text) {
        macSigningKeychainTextField.setText(text);
    }

    public void setMacSigningKeyUsername(String text) {
        macSigningKeyUsernameTextField.setText(text);
    }

    public void setMacAppStore(boolean checked) {
        macAppStoreCheckBox.setSelected(checked);
    }

    public void setMacEntitlements(String text) {
        macEntitlementsTextField.setText(text);
    }

    public void setMacAppCategory(String text) {
        macAppCategoryTextField.setText(text);
    }

    // End of Getters and Setters -------------------------------------------------------------------------------------|
}
