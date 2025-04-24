package main.com.everdro1d.jpackage.ui.panels;

import com.everdro1d.libs.swing.components.TextFieldFileChooser;
import main.com.everdro1d.jpackage.ui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

import static main.com.everdro1d.jpackage.core.CommandSettings.getSubsetOSTypeArray;
import static main.com.everdro1d.jpackage.core.MainWorker.localeManager;

public class GenericOptionsPanel extends JPanel {
    private LeftGenericPanel leftGenericPanel;
        public static String nameLabelText          = "Name:";
        public static String descriptionLabelText   = "Description:";
        public static String iconPathLabelText      = "Icon Path:";
        public static String vendorLabelText        = "Vendor:";
        public static String versionLabelText       = "Version:";
        public static String copyrightLabelText     = "Copyright:";
        public static String licensePathLabelText   = "License Path:";
    private RightGenericPanel rightGenericPanel;
        public static String typeLabelText      = "Type:";
        public static String inputLabelText     = "Input Path:";
        public static String outputLabelText    = "Output Path:";
        public static String mainJarLabelText   = "Main Jar:";
        public static String mainClassLabelText = "Main Class:";
        public static String argumentsLabelText = "Arguments:";
        public static String aboutLabelText     = "About URL:";


    // Constructors ---------------------------------------------------------------------------------------------------|
    public GenericOptionsPanel() {
        if (!localeManager.getComponentsInClassMap("MainWindow").contains("GenericOptionsPanel")) {
            addClassToLocale();
        }
        useLocale();

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

    private void addClassToLocale() {
        Map<String, String> map = new TreeMap<>();
        // left generic panel
        map.put("nameLabelText", nameLabelText);
        map.put("descriptionLabelText", descriptionLabelText);
        map.put("iconPathLabelText", iconPathLabelText);
        map.put("vendorLabelText", vendorLabelText);
        map.put("versionLabelText", versionLabelText);
        map.put("copyrightLabelText", copyrightLabelText);
        map.put("licensePathLabelText", licensePathLabelText);

        // right generic panel
        map.put("typeLabelText", typeLabelText);
        map.put("inputLabelText", inputLabelText);
        map.put("outputLabelText", outputLabelText);
        map.put("mainJarLabelText", mainJarLabelText);
        map.put("mainClassLabelText", mainClassLabelText);
        map.put("argumentsLabelText", argumentsLabelText);
        map.put("aboutLabelText", aboutLabelText);

        localeManager.addComponentSpecificMap("MainWindow","GenericOptionsPanel", map);
    }

    private void useLocale() {
        Map<String, String> varMap =
                localeManager.getComponentSpecificMap("MainWindow","GenericOptionsPanel");

        nameLabelText = varMap.getOrDefault("nameLabelText", nameLabelText);
        descriptionLabelText = varMap.getOrDefault("descriptionLabelText", descriptionLabelText);
        iconPathLabelText = varMap.getOrDefault("iconPathLabelText", iconPathLabelText);
        vendorLabelText = varMap.getOrDefault("vendorLabelText", vendorLabelText);
        versionLabelText = varMap.getOrDefault("versionLabelText", versionLabelText);
        copyrightLabelText = varMap.getOrDefault("copyrightLabelText", copyrightLabelText);
        licensePathLabelText = varMap.getOrDefault("licensePathLabelText", licensePathLabelText);

        // right generic panel
        typeLabelText = varMap.getOrDefault("typeLabelText", typeLabelText);
        inputLabelText = varMap.getOrDefault("inputLabelText", inputLabelText);
        outputLabelText = varMap.getOrDefault("outputLabelText", outputLabelText);
        mainJarLabelText = varMap.getOrDefault("mainJarLabelText", mainJarLabelText);
        mainClassLabelText = varMap.getOrDefault("mainClassLabelText", mainClassLabelText);
        argumentsLabelText = varMap.getOrDefault("argumentsLabelText", argumentsLabelText);
        aboutLabelText = varMap.getOrDefault("aboutLabelText", aboutLabelText);
    }

    public static class LeftGenericPanel extends JPanel {
        private JLabel nameLabel;
        protected static JTextField nameTextField;
        private JLabel descriptionLabel;
        protected static JTextField descriptionTextField;
        private JLabel iconLabel;
        protected static TextFieldFileChooser iconPathTextField;
        private JLabel vendorLabel;
        protected static JTextField vendorTextField;
        private JLabel versionLabel;
        protected static JTextField versionTextField;
        private JLabel copyrightLabel;
        protected static JTextField copyrightTextField;
        private JLabel licenseLabel;
        protected static TextFieldFileChooser licenseTextField;

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
            add((iconLabel = new JLabel(iconPathLabelText)), gbc);
            gbc.gridy++;
            add((vendorLabel = new JLabel(vendorLabelText)), gbc);
            gbc.gridy++;
            add((versionLabel = new JLabel(versionLabelText)), gbc);
            gbc.gridy++;
            add((copyrightLabel = new JLabel(copyrightLabelText)), gbc);
            gbc.gridy++;
            gbc.weighty = 1;
            add((licenseLabel = new JLabel(licensePathLabelText)), gbc);

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
    }

    public static class RightGenericPanel extends JPanel {
        private JLabel typeLabel;
        protected static JComboBox<String> typeComboBox;
        private JLabel inputLabel; // path is jar or directory
        protected static TextFieldFileChooser inputPathTextField;
        private JLabel outputLabel;
        protected static TextFieldFileChooser outputPathTextField;
        private JLabel mainJarLabel;
        protected static JTextField mainJarTextField;
        private JLabel mainClassLabel;
        protected static JTextField mainClassTextField;
        private JLabel argumentsLabel;
        protected static JTextField argumentsTextField;
        private JLabel aboutLabel;
        protected static JTextField aboutTextField;

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

    }

    // Getters and Setters --------------------------------------------------------------------------------------------|
    public String getNameField() {
        return LeftGenericPanel.nameTextField.getText();
    }

    public String getDescription() {
        return LeftGenericPanel.descriptionTextField.getText();
    }

    public String getIconPath() {
        return LeftGenericPanel.iconPathTextField.getText();
    }

    public String getVendor() {
        return LeftGenericPanel.vendorTextField.getText();
    }

    public String getVersion() {
        return LeftGenericPanel.versionTextField.getText();
    }

    public String getCopyright() {
        return LeftGenericPanel.copyrightTextField.getText();
    }

    public String getLicense() {
        return LeftGenericPanel.licenseTextField.getText();
    }

    public void setNameField(String text) {
        LeftGenericPanel.nameTextField.setText(text);
    }

    public void setDescription(String text) {
        LeftGenericPanel.descriptionTextField.setText(text);
    }

    public void setIconPath(String text) {
        LeftGenericPanel.iconPathTextField.setText(text);
    }

    public void setVendor(String text) {
        LeftGenericPanel.vendorTextField.setText(text);
    }

    public void setVersion(String text) {
        LeftGenericPanel.versionTextField.setText(text);
    }

    public void setCopyright(String text) {
        LeftGenericPanel.copyrightTextField.setText(text);
    }

    public void setLicense(String text) {
        LeftGenericPanel.licenseTextField.setText(text);
    }

    public String getFileType() {
        return (String) RightGenericPanel.typeComboBox.getSelectedItem();
    }

    public String getInputPath() {
        return RightGenericPanel.inputPathTextField.getText();
    }

    public String getOutputPath() {
        return RightGenericPanel.outputPathTextField.getText();
    }

    public String getArguments() {
        return RightGenericPanel.argumentsTextField.getText();
    }

    public String getMainJar() {
        return RightGenericPanel.mainJarTextField.getText();
    }

    public String getMainClass() {
        return RightGenericPanel.mainClassTextField.getText();
    }

    public String getAboutURL() {
        return RightGenericPanel.aboutTextField.getText();
    }

    public void setFileType(String text) {
        int count = RightGenericPanel.typeComboBox.getItemCount();
        for (int i = 0; i < count; i++) {
            if (text.equals(RightGenericPanel.typeComboBox.getItemAt(i))) {
                RightGenericPanel.typeComboBox.setSelectedIndex(i);
            }
        }
    }

    public void setInputPath(String text) {
        RightGenericPanel.inputPathTextField.setText(text);
    }

    public void setOutputPath(String text) {
        RightGenericPanel.outputPathTextField.setText(text);
    }

    public void setArguments(String text) {
        RightGenericPanel.argumentsTextField.setText(text);
    }

    public void setMainJar(String text) {
        RightGenericPanel.mainJarTextField.setText(text);
    }

    public void setMainClass(String text) {
        RightGenericPanel.mainClassTextField.setText(text);
    }

    public void setAboutURL(String text) {
        RightGenericPanel.aboutTextField.setText(text);
    }

}
