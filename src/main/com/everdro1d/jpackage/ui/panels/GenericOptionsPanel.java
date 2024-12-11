package main.com.everdro1d.jpackage.ui.panels;

import com.everdro1d.libs.swing.components.TextFieldFileChooser;
import main.com.everdro1d.jpackage.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

import static main.com.everdro1d.jpackage.core.CmdSettings.getSubsetOSTypeArray;
import static main.com.everdro1d.jpackage.core.MainWorker.localeManager;

public class GenericOptionsPanel extends JPanel {
    private LeftGenericPanel leftGenericPanel;
    private RightGenericPanel rightGenericPanel;

    // Constructors ---------------------------------------------------------------------------------------------------|
    public GenericOptionsPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);

        add((leftGenericPanel = new LeftGenericPanel()), gbc);
        gbc.gridx++;
        gbc.weighty = 1;
        add((rightGenericPanel = new RightGenericPanel()), gbc);
    }

    public static class LeftGenericPanel extends JPanel {
        private JLabel nameLabel;
            private String nameText = "Name:";
            private JTextField nameTextField;
        private JLabel descriptionLabel;
            private String descriptionText = "Description:";
            private JTextField descriptionTextField;
        private JLabel iconLabel;
            private String iconText = "Icon Path:";
            private TextFieldFileChooser iconPathTextField;
        private JLabel vendorLabel;
            private String vendorText = "Vendor:";
            private JTextField vendorTextField;
        private JLabel versionLabel;
            private String versionText = "Version:";
            private JTextField versionTextField;
        private JLabel copyrightLabel;
            private String copyRightText = "CopyRight:";
            private JTextField copyRightTextField;
        private JLabel licenseLabel;
            private String licenseText = "License Path:";
            private TextFieldFileChooser licenseTextField;

        public LeftGenericPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.FIRST_LINE_START;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(4, 4, 4, 4);

            // Col 0
            add((nameLabel = new JLabel(nameText)), gbc);
            gbc.gridy++;
            add((descriptionLabel = new JLabel(descriptionText)), gbc);
            gbc.gridy++;
            add((iconLabel = new JLabel(iconText)), gbc);
            gbc.gridy++;
            add((vendorLabel = new JLabel(vendorText)), gbc);
            gbc.gridy++;
            add((versionLabel = new JLabel(versionText)), gbc);
            gbc.gridy++;
            add((copyrightLabel = new JLabel(copyRightText)), gbc);
            gbc.gridy++;
            gbc.weighty = 1;
            add((licenseLabel = new JLabel(licenseText)), gbc);

            // Col 1
            gbc.gridx++;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.weighty = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            add((nameTextField = new JTextField()), gbc);
            gbc.gridy++;

            add((descriptionTextField = new JTextField()), gbc);
            gbc.gridy++;

            add((iconPathTextField = new TextFieldFileChooser(localeManager)), gbc);
            gbc.gridy++;

            add((vendorTextField = new JTextField()), gbc);
            gbc.gridy++;

            add((versionTextField = new JTextField()), gbc);
            gbc.gridy++;

            add((copyRightTextField = new JTextField()), gbc);
            gbc.gridy++;

            gbc.weighty = 1;
            add((licenseTextField = new TextFieldFileChooser(localeManager)), gbc);

            for (Component component : getComponents()) {
                if (component.getFont() != null) {
                    component.setFont(new Font(MainWindow.fontName, Font.PLAIN, MainWindow.fontSize));
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

        public String getLicenseText() {
            return licenseTextField.getText();
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

        public void setLicenseText(String text) {
            licenseTextField.setText(text);
        }
    }

    public static class RightGenericPanel extends JPanel {
        private JLabel typeLabel;
            private String typeText = "Type:";
            private JComboBox<String> typeComboBox;
        private JLabel inputLabel; // path is jar or directory
            private String inputText = "Input Path:";
            private TextFieldFileChooser inputPathTextField;
        private JLabel outputLabel;
            private String outputText = "Output Path:";
            private TextFieldFileChooser outputPathTextField;
        private JLabel mainJarLabel;
            private String mainJarText = "Main Jar:";
            private JTextField mainJarTextField;
        private JLabel mainClassLabel;
            private String mainClassText = "Main Class:";
            private JTextField mainClassTextField;
        private JLabel argumentsLabel;
            private String argumentsText = "Arguments:";
            private JTextField argumentsTextField;
        private JLabel aboutLabel;
            private String aboutText = "About URL:";
            private JTextField aboutTextField;

        public RightGenericPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.FIRST_LINE_START;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(4, 4, 4, 4);

            // Col 0
            add((typeLabel = new JLabel(typeText)), gbc);
            gbc.gridy++;

            add((inputLabel = new JLabel(inputText)), gbc);
            gbc.gridy++;

            add((outputLabel = new JLabel(outputText)), gbc);
            gbc.gridy++;

            add((mainJarLabel = new JLabel(mainJarText)), gbc);
            gbc.gridy++;

            add((mainClassLabel = new JLabel(mainClassText)), gbc);
            gbc.gridy++;

            add((argumentsLabel = new JLabel(argumentsText)), gbc);
            gbc.gridy++;

            gbc.weighty = 1;
            add((aboutLabel = new JLabel(aboutText)), gbc);

            // Col 1
            gbc.gridx++;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.weighty = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            add((typeComboBox = new JComboBox<>(getSubsetOSTypeArray())), gbc);
            gbc.gridy++;

            add((inputPathTextField = new TextFieldFileChooser(localeManager)), gbc);
            gbc.gridy++;

            add((outputPathTextField = new TextFieldFileChooser(localeManager)), gbc);
            gbc.gridy++;

            add((argumentsTextField = new JTextField()), gbc);
            gbc.gridy++;

            add((mainJarTextField = new JTextField()), gbc);
            gbc.gridy++;

            add((mainClassTextField = new JTextField()), gbc);
            gbc.gridy++;

            gbc.weighty = 1;
            add((aboutTextField = new JTextField()), gbc);

            for (Component component : getComponents()) {
                if (component.getFont() != null) {
                    component.setFont(new Font(MainWindow.fontName, Font.PLAIN, MainWindow.fontSize));
                }
            }
        }

        // Getters and Setters ----------------------------------------------------------------------------------------|
        public String getTypeText() {
            return (String) typeComboBox.getSelectedItem();
        }

        public String getInputPathText() {
            return inputPathTextField.getText();
        }

        public String getOutputPathText() {
            return outputPathTextField.getText();
        }

        public String getArgumentsText() {
            return argumentsTextField.getText();
        }

        public String getMainJarText() {
            return mainJarTextField.getText();
        }

        public String getMainClassText() {
            return mainClassTextField.getText();
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

        public void setArgumentsText(String text) {
            argumentsTextField.setText(text);
        }

        public void setMainJarText(String text) {
            mainJarTextField.setText(text);
        }

        public void setMainClassText(String text) {
            mainClassTextField.setText(text);
        }

        public void setAboutText(String text) {
            aboutTextField.setText(text);
        }
    }

}
