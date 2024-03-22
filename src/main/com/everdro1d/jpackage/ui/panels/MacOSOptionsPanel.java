package main.com.everdro1d.jpackage.ui.panels;

import main.com.everdro1d.jpackage.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

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
    private JLabel macSigningKeyUserNameLabel;
        private JTextField macSigningKeyUserNameTextField;
    private JLabel macAppStoreLabel;
        private JCheckBox macAppStoreCheckBox;
    private JLabel macEntitlementsLabel;
        private JTextField macEntitlementsTextField;
    private JLabel macAppCategoryLabel;
        private JTextField macAppCategoryTextField;

    // End of Variables -----------------------------------------------------------------------------------------------|

    // Constructors ---------------------------------------------------------------------------------------------------|
    public MacOSOptionsPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);
        int widthPx = 290;

        // Col 1
        macPackageIdentifierLabel = new JLabel("<html><div style='width:"+widthPx+"px'>An identifier that uniquely identifies the application for macOS: </div></html>");
        add(macPackageIdentifierLabel, gbc);
        gbc.gridy++;

        macPackageNameLabel = new JLabel("<html><div style='width:"+widthPx+"px'>Name of the application as it appears in the Menu Bar: </div></html>");
        add(macPackageNameLabel, gbc);
        gbc.gridy++;

        macPackageSigningPrefixLabel = new JLabel("<html><div style='width:"+widthPx+"px'>Component package identifier prefix: </div></html>");
        add(macPackageSigningPrefixLabel, gbc);
        gbc.gridy++;

        macSignLabel = new JLabel("<html><div style='width:"+widthPx+"px'>Request that the package be signed: </div></html>");
        add(macSignLabel, gbc);
        gbc.gridy++;

        macSigningKeychainLabel = new JLabel("<html><div style='width:"+widthPx+"px'>Name of the keychain to search for the signing identity: </div></html>");
        add(macSigningKeychainLabel, gbc);
        gbc.gridy++;

        macSigningKeyUserNameLabel = new JLabel("<html><div style='width:"+widthPx+"px'>Team or username portion in Apple signing identities: </div></html>");
        add(macSigningKeyUserNameLabel, gbc);
        gbc.gridy++;

        macAppStoreLabel = new JLabel("<html><div style='width:"+widthPx+"px'> Indicates that the jpackage output is intended for the Mac App Store: </div></html>");
        add(macAppStoreLabel, gbc);
        gbc.gridy++;

        macEntitlementsLabel = new JLabel("<html><div style='width:"+widthPx+"px'>Path to file containing entitlements to use: </div></html>");
        add(macEntitlementsLabel, gbc);
        gbc.gridy++;

        macAppCategoryLabel = new JLabel("<html><div style='width:"+widthPx+"px'>String used to construct LSApplicationCategoryType in application plist: </div></html>");
        add(macAppCategoryLabel, gbc);

        gbc.gridx++;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Col 2

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

        add((macSigningKeyUserNameTextField = new JTextField()), gbc);
        gbc.gridy++;

        add((macAppStoreCheckBox = new JCheckBox()), gbc);
        gbc.gridy++;

        add((macEntitlementsTextField = new JTextField()), gbc);
        gbc.gridy++;

        add((macAppCategoryTextField = new JTextField()), gbc);

        for (Component component : getComponents()) {
            if (component.getFont() != null) {
                component.setFont(new Font(MainWindow.fontName, Font.PLAIN, MainWindow.fontSize));
            }
        }

    }
}
