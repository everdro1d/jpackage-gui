package main.com.everdro1d.jpackage.core;

import com.everdro1d.libs.core.ApplicationCore;
import main.com.everdro1d.jpackage.ui.MainWindow;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ButtonAction {
    //TODO update the get method names here to match actual
    private static final String[][] genericSettingKeyMethodPairs = new String[][] {
            {"gen_name","getNameText"},
            {"gen_description","getDescriptionText"},
            {"gen_iconPath","getIconPathText"},
            {"gen_vendorName","getVendorText"},
            {"gen_version","getVersionText"},
            {"gen_copyright","getCopyRightText"},
            {"gen_license","getLicenseText"},
            {"gen_fileType","getFileTypeText"},
            {"gen_inputPath","getInputPathText"},
            {"gen_outputPath","getOutputPathText"},
            {"gen_arguments","getArgumentsText"},
            {"gen_mainJarName","getMainJarText"},
            {"gen_mainClassName","getMainClassText"},
            {"gen_aboutURL","getAboutURLText"}
    };

    private static final String[][] winSettingKeyMethodPairs = new String[][] {
            {"win_console","isWinConsole"},
            {"win_dirChooser","isWinDirChooser"},
            {"win_helpURL","getWinHelpUrlText"},
            {"win_startMenu","isWinMenu"},
            {"win_startMenuGroupName","getWinMenuGroupText"},
            {"win_installPerUser","isWinPerUserInstall"},
            {"win_createShortcut","isWinShortcut"},
            {"win_promptCreateShortcutOption","isWinShortcutPrompt"},
            {"win_updateURL","getWinUpdateUrlText"},
            {"win_upgradeUUID","getWinUpgradeUuidText"}

    };

    private static final String[][] macSettingKeyMethodPairs = new String[][] {
            {"mac_packageIdentifier","getMacPackageIdentifierText"},
            {"mac_packageName","getMacPackageNameText"},
            {"mac_packageSigningPrefix","getMacPackageSigningPrefixText"},
            {"mac_willPackageSign","isMacSignPackage"},
            {"mac_signingKeychain","getMacSigningKeychainText"},
            {"mac_signingKeyUsername","getMacSigningKeyUserNameText"},
            {"mac_appStore","isMacAppStore"},
            {"mac_entitlements","getMacEntitlementsText"},
            {"mac_appCategory","getMacAppCategoryText"}
    };

    private static final String[][] nixSettingKeyMethodPairs = new String[][] {
            {"nix_packageName","getLinuxPackageNameText"},
            {"nix_debMaintainer","getLinuxDebMaintainerText"},
            {"nix_menuGroupText","getLinuxMenuGroupText"},
            {"nix_packageDependencies","getLinuxPackageDependenciesText"},
            {"nix_rpmLicenseType","getLinuxRpmLicenseTypeText"},
            {"nix_releaseValue","getLinuxAppReleaseText"},
            {"nix_appCategory","getLinuxAppCategoryText"},
            {"nix_desktopShortcut","isLinuxDesktopShortcut"}
    };

    private static Map<String,String> commandSettingsMap = new HashMap<>() {
        {
            // TODO - example values for testing
            put("main_jdkBinPath", "getJdkBinPathText");
            put("gen_name", "getNameText");
            put("gen_description", "getDescriptionText");
            put("gen_iconPath", "getIconPathText");
            put("gen_vendorName", "getVendorText");
        }
    };

    public static void saveSettingsToFile() {
        putIntoCommandSettingsMap();
        // TODO Save the settings from the commandSettingsMap into a file
    }

    public static void loadSettingsFromFile() {
        loadIntoCommandSettingsMap();
        setSettingsFromMap();
    }

    public static void assembleAndRunJPackageCommand() {
        // TODO assemble the jpackage command from the commandSettingsMap
        putIntoCommandSettingsMap();
    }

    // private methods ------------------------------------------------------------------------------------------------|

    private static void putIntoCommandSettingsMap() {
        commandSettingsMap.put("main_jdkBinPath", getAndInvokeMethod("MainWindow", "getJdkBinPathText"));

        for (String[] keyPair: genericSettingKeyMethodPairs) {
            commandSettingsMap.put(keyPair[0], getAndInvokeMethod("GenericOptionsPanel", keyPair[1]));
        }

        switch (ApplicationCore.detectOS()) {
            case "Windows" -> {
                // Windows specific code
                for (String[] keyPair : winSettingKeyMethodPairs) {
                    commandSettingsMap.put(keyPair[0], getAndInvokeMethod("WindowsOptionsPanel", keyPair[1]));
                }
            }
            case "macOS" -> {
                // macOS specific code
                for (String[] keyPair : macSettingKeyMethodPairs) {
                    commandSettingsMap.put(keyPair[0], getAndInvokeMethod("MacOptionsPanel", keyPair[1]));
                }
            }
            case "Unix" -> {
                // Unix specific code
                for (String[] keyPair : nixSettingKeyMethodPairs) {
                    commandSettingsMap.put(keyPair[0], getAndInvokeMethod("LinuxOptionsPanel", keyPair[1]));
                }
            }
        }
    }

    private static void loadIntoCommandSettingsMap() {
        // TODO Load the settings from file into the commandSettingsMap

    }

    private static void setSettingsFromMap() {
        // Set the settings from the commandSettingsMap
        for (Map.Entry<String, String> entry : commandSettingsMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value.contains("get")) {
                value = value.replace("get", "set");
            }

            if (key.equals("main_jdkBinPath")) {
                // Set the JDK bin path
                getAndInvokeMethod("MainWindow", value);
            }

            for (String[] keyPair : genericSettingKeyMethodPairs) {
                if (key.equals(keyPair[0])) {
                    // Set the generic settings
                    getAndInvokeMethod("GenericOptionsPanel", keyPair[1], value);
                    // TODO add optional value to set when using methods with parameters
                }
            }
        }
    }

    private static Map<String, String> getCommandSettingsMap() {
        return commandSettingsMap;
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
            System.out.println("Class: " + clazz);
            Method method = clazz.getMethod(methodName); // TODO idk why but instance cant find the panels to get.
            return (String) method.invoke(instance, (Object[]) optionalValue);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
