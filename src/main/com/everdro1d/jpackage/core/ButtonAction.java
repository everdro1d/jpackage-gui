package main.com.everdro1d.jpackage.core;

import com.everdro1d.libs.core.ApplicationCore;
import main.com.everdro1d.jpackage.ui.MainWindow;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static main.com.everdro1d.jpackage.core.MainWorker.debug;

public class ButtonAction {
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

    public static void saveSettingsToFile() {
        // TODO open file chooser to save directory
        getSettingsFromUI();
        if (debug) System.out.println("Saved settings to map.");
        // TODO Save the settings from the commandSettingsMap into a file

        if (debug) System.out.println("Saving settings to file from map.");
    }

    public static void loadSettingsFromFile() {
        // TODO open file chooser to load file
        loadSettingsFromFileToMap();
        if (debug) System.out.println("Loaded settings from file to map.");
        setSettingsFromMap();
        if (debug) System.out.println("Loaded settings from map to ui.");
    }

    public static void assembleAndRunJPackageCommand() {
        // TODO assemble the jpackage command from the commandSettingsMap
        getSettingsFromUI();
        if (debug) System.out.println("Saved settings from ui to map.");

    }

    // private methods ------------------------------------------------------------------------------------------------|

    private static void getSettingsFromUI() {
        commandSettingsMap.put(mainSettingKeyMethodPair[0][0], getAndInvokeMethod("MainWindow", mainSettingKeyMethodPair[0][1]));

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

    private static void loadSettingsFromFileToMap() {
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

    private static Map<String, String> getCommandSettingsMap() {
        return commandSettingsMap;
    }

}
