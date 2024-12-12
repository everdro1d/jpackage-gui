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
            private String nameLabelText = "Name:";
            private JTextField nameTextField;
        private JLabel descriptionLabel;
            private String descriptionLabelText = "Description:";
            private JTextField descriptionTextField;
        private JLabel iconLabel;
            private String iconLabelText = "Icon Path:";
            private TextFieldFileChooser iconPathTextField;
        private JLabel vendorLabel;
            private String vendorLabelText = "Vendor:";
            private JTextField vendorTextField;
        private JLabel versionLabel;
            private String versionLabelText = "Version:";
            private JTextField versionTextField;
        private JLabel copyrightLabel;
            private String copyrightLabelText = "Copyright:";
            private JTextField copyrightTextField;
        private JLabel licenseLabel;
            private String licenseLabelText = "License Path:";
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
            add((nameLabel = new JLabel(nameLabelText)), gbc);
            gbc.gridy++;
            add((descriptionLabel = new JLabel(descriptionLabelText)), gbc);
            gbc.gridy++;
            add((iconLabel = new JLabel(iconLabelText)), gbc);
            gbc.gridy++;
            add((vendorLabel = new JLabel(vendorLabelText)), gbc);
            gbc.gridy++;
            add((versionLabel = new JLabel(versionLabelText)), gbc);
            gbc.gridy++;
            add((copyrightLabel = new JLabel(copyrightLabelText)), gbc);
            gbc.gridy++;
            gbc.weighty = 1;
            add((licenseLabel = new JLabel(licenseLabelText)), gbc);

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

            add((copyrightTextField = new JTextField()), gbc);
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

        public String getCopyrightText() {
            return copyrightTextField.getText();
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
            copyrightTextField.setText(text);
        }

        public void setLicenseText(String text) {
            licenseTextField.setText(text);
        }
    }

    public static class RightGenericPanel extends JPanel {
        private JLabel typeLabel;
            private String typeLabelText = "Type:";
            private JComboBox<String> typeComboBox;
        private JLabel inputLabel; // path is jar or directory
            private String inputLabelText = "Input Path:";
            private TextFieldFileChooser inputPathTextField;
        private JLabel outputLabel;
            private String outputLabelText = "Output Path:";
            private TextFieldFileChooser outputPathTextField;
        private JLabel mainJarLabel;
            private String mainJarLabelText = "Main Jar:";
            private JTextField mainJarTextField;
        private JLabel mainClassLabel;
            private String mainClassLabelText = "Main Class:";
            private JTextField mainClassTextField;
        private JLabel argumentsLabel;
            private String argumentsLabelText = "Arguments:";
            private JTextField argumentsTextField;
        private JLabel aboutLabel;
            private String aboutLabelText = "About URL:";
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
            add((typeLabel = new JLabel(typeLabelText)), gbc);
            gbc.gridy++;

            add((inputLabel = new JLabel(inputLabelText)), gbc);
            gbc.gridy++;

            add((outputLabel = new JLabel(outputLabelText)), gbc);
            gbc.gridy++;

            add((mainJarLabel = new JLabel(mainJarLabelText)), gbc);
            gbc.gridy++;

            add((mainClassLabel = new JLabel(mainClassLabelText)), gbc);
            gbc.gridy++;

            add((argumentsLabel = new JLabel(argumentsLabelText)), gbc);
            gbc.gridy++;

            gbc.weighty = 1;
            add((aboutLabel = new JLabel(aboutLabelText)), gbc);

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
        public String getFileTypeText() {
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
