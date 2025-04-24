package main.com.everdro1d.jpackage.core;

import main.com.everdro1d.jpackage.ui.MainWindow;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CmdSettings {
    private static final String[][] mainSettingKeyMethodPair = new String[][] {
            // MainWindow
            {"main_jdkBinPath","getJdkBinPath"}
    };

    private static final String[][] genericSettingKeyMethodPairs = new String[][] {
            // GenericOptionsPanel
            {"gen_name","getNameField"},
            {"gen_description","getDescription"},
            {"gen_iconPath","getIconPath"},
            {"gen_vendorName","getVendor"},
            {"gen_version","getVersion"},
            {"gen_copyright","getCopyright"},
            {"gen_license","getLicense"},
            {"gen_fileType","getFileType"},
            {"gen_inputPath","getInputPath"},
            {"gen_outputPath","getOutputPath"},
            {"gen_arguments","getArguments"},
            {"gen_mainJarName","getMainJar"},
            {"gen_mainClassName","getMainClass"},
            {"gen_aboutURL","getAboutURL"}
    };

    private static final String[][] winSettingKeyMethodPairs = new String[][] {
            // WindowsOptionsPanel
            {"win_console","isWinConsole"},
            {"win_dirChooser","isWinDirChooser"},
            {"win_helpURL","getWinHelpURL"},
            {"win_startMenu","isWinMenu"},
            {"win_startMenuGroupName","getWinMenuGroup"},
            {"win_installPerUser","isWinPerUserInstall"},
            {"win_createShortcut","isWinShortcut"},
            {"win_promptCreateShortcutOption","isWinShortcutPrompt"},
            {"win_updateURL","getWinUpdateURL"},
            {"win_upgradeUUID","getWinUpgradeUuid"}

    };

    private static final String[][] macSettingKeyMethodPairs = new String[][] {
            // MacOSOptionsPanel
            {"mac_packageIdentifier","getMacPackageIdentifier"},
            {"mac_packageName","getMacPackageName"},
            {"mac_packageSigningPrefix","getMacPackageSigningPrefix"},
            {"mac_willPackageSign","isMacSignPackage"},
            {"mac_signingKeychain","getMacSigningKeychain"},
            {"mac_signingKeyUsername","getMacSigningKeyUsername"},
            {"mac_appStore","isMacAppStore"},
            {"mac_entitlements","getMacEntitlements"},
            {"mac_appCategory","getMacAppCategory"}
    };

    private static final String[][] nixSettingKeyMethodPairs = new String[][] {
            // UnixOptionsPanel
            {"nix_packageName","getLinuxPackageName"},
            {"nix_debMaintainer","getLinuxDebMaintainer"},
            {"nix_menuGroup","getLinuxMenuGroup"},
            {"nix_packageDependencies","getLinuxPackageDependencies"},
            {"nix_rpmLicenseType","getLinuxRpmLicenseType"},
            {"nix_releaseValue","getLinuxAppRelease"},
            {"nix_appCategory","getLinuxAppCategory"},
            {"nix_desktopShortcut","isLinuxDesktopShortcut"}
    };

    private static Map<String,String> commandSettingsMap = new HashMap<>() {};

    public static final Map<String, String> osTypeMap = new HashMap<>() {
        {
            put("exe", "Windows");
            put("msi", "Windows");
            put("pkg", "macOS");
            put("dmg", "macOS");
            put("rpm", "Unix");
            put("deb", "Unix");
        }
    };

    public static String[] getSubsetOSTypeArray() {
        return osTypeMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(MainWorker.detectedOS))
                .map(Map.Entry::getKey)
                .toArray(String[]::new);
    }

    protected static void getSettingsFromUI() {
        commandSettingsMap.put(mainSettingKeyMethodPair[0][0], getAndInvokeMethod("MainWindow", mainSettingKeyMethodPair[0][1]));

        addSettingsToMapFromPanel("Generic", genericSettingKeyMethodPairs);

        switch (MainWorker.detectedOS) {
            case "Windows" -> addSettingsToMapFromPanel("Windows", winSettingKeyMethodPairs);
            case "macOS" -> addSettingsToMapFromPanel("MacOS", macSettingKeyMethodPairs);
            case "Unix" -> addSettingsToMapFromPanel("Unix", nixSettingKeyMethodPairs);
        }
    }

    protected static void addSettingsToMapFromPanel(String panel, String[][] settingKeyMethodPairs) {
        for (String[] keyPair : settingKeyMethodPairs) {
            commandSettingsMap.put(keyPair[0], getAndInvokeMethod(panel + "OptionsPanel", keyPair[1]));
        }
    }

    protected static void loadSettingsFromFileToMap() {
        // TODO Load the settings from file into the commandSettingsMap

    }

    protected static void setSettingsFromMap() {
        // Set the settings from the commandSettingsMap
        for (Map.Entry<String, String> entry : commandSettingsMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (value == null) { value = ""; }

            // this is the jdk "/bin" dir
            if (key.equals(mainSettingKeyMethodPair[0][0])) {
                getAndInvokeMethod("MainWindow", convertMethodPrefix(mainSettingKeyMethodPair[0][1]), value);
            }

            setSettingsFromMapToPanel("Generic", genericSettingKeyMethodPairs, key, value);

            switch (MainWorker.detectedOS) {
                case "Windows" -> setSettingsFromMapToPanel("Windows", winSettingKeyMethodPairs, key, value);
                case "macOS" -> setSettingsFromMapToPanel("MacOS", macSettingKeyMethodPairs, key, value);
                case "Unix" -> setSettingsFromMapToPanel("Unix", nixSettingKeyMethodPairs, key, value);
            }
        }
    }

    protected static void setSettingsFromMapToPanel(String panel, String[][] settingKeyMethodPairs, String key, String value) {
        for (String[] keyPair : settingKeyMethodPairs) {
            if (key.equals(keyPair[0])) {
                getAndInvokeMethod(panel + "OptionsPanel", convertMethodPrefix(keyPair[1]), value);
            }
        }
    }

    private static String convertMethodPrefix(String method) {
        if (method.startsWith("get")) {
            method = method.replace("get", "set");
        } else if (method.startsWith("is")) {
            method = method.replace("is", "set");
        }
        return method;
    }

    private static String getAndInvokeMethod(String className, String methodName, String... optionalValue) {
        try {
            Class<?> clazz;
            MainWindow instanceOfMainWindow = MainWorker.getInstanceOfMainWindow();
            Object instance;

            if (!className.equals("MainWindow")) {
                clazz = Class.forName("main.com.everdro1d.jpackage.ui.panels." + className);
                instance = instanceOfMainWindow.getInstanceOf(clazz, instanceOfMainWindow);

            } else {
                clazz = Class.forName("main.com.everdro1d.jpackage.ui." + className);
                instance = instanceOfMainWindow;
            }

            // checkboxes need to pass bools, everything else needs a str
            Class<?>[] parameterTypes = Arrays.stream(optionalValue)
                    .map(value -> (value.equals("true") || value.equals("false")) ? boolean.class : String.class)
                    .toArray(Class<?>[]::new);
            // convert the optional value to a bool if necessary
            Object[] parsedValues = Arrays.stream(optionalValue)
                    .map(value -> (value.equals("true") || value.equals("false")) ? Boolean.parseBoolean(value) : value)
                    .toArray();

            // setter methods require a parameter to pass getters dont
            Method method;
            method = methodName.startsWith("set")
                    ? clazz.getMethod(methodName, parameterTypes)
                    : clazz.getMethod(methodName);

            Object returnValue = method.invoke(instance, (methodName.startsWith("set") ? parsedValues : null) );
            if (returnValue == null) return null;
            return returnValue.toString();

        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, String> getCommandSettingsMap() {
        return commandSettingsMap;
    }

}
