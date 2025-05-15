package main.com.everdro1d.jpackage.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static main.com.everdro1d.jpackage.core.CommandSettings.getCommandSettingsMap;
import static main.com.everdro1d.jpackage.core.CommandSettings.osDependentGenericCheck;
import static main.com.everdro1d.jpackage.core.MainWorker.detectedOS;

public class CommandAssembler {
    private static final String[][] genericSettingKeyArgumentPairs = new String[][] {
            // GenericOptionsPanel
            {"gen_name","--name"},
            {"gen_description","--description"},
            {"gen_vendorName","--vendor"},
            {"gen_version","--app-version"},
            {"gen_copyright","--copyright"},
            {"gen_fileType","--type"},
            {"gen_arguments","--arguments"},
            {"gen_mainJarName","--main-jar"},
            {"gen_mainClassName","--main-class"},
            {"gen_aboutURL","--about-url"}
    };

    private static String[][] osDependentGenericSettingKeyMethodPairs = new String[][] {
            {"gen_iconPath","--icon"},
            {"gen_license","--license-file"},
            {"gen_inputPath","--input"},
            {"gen_outputPath","--dest"}
    };

    private static final String[][] winSettingKeyArgumentPairs = new String[][] {
            // WindowsOptionsPanel
            {"win_console","--win-console"},
            {"win_dirChooser","--win-dir-chooser"},
            {"win_helpURL","--win-help-url"},
            {"win_startMenu","--win-menu"},
            {"win_startMenuGroupName","--win-menu-group"},
            {"win_installPerUser","--win-per-user-install"},
            {"win_createShortcut","--win-shortcut"},
            {"win_promptCreateShortcutOption","--win-shortcut-prompt"},
            {"win_updateURL","--win-update-url"},
            {"win_upgradeUUID","--win-upgrade-uuid"}

    };

    private static final String[][] macSettingKeyArgumentPairs = new String[][] {
            // MacOSOptionsPanel
            {"mac_packageIdentifier","--mac-package-identifier"},
            {"mac_packageName","--mac-package-name"},
            {"mac_packageSigningPrefix","--mac-package-signing-prefix"},
            {"mac_willPackageSign","--mac-sign"},
            {"mac_signingKeychain","--mac-signing-keychain"},
            {"mac_signingKeyUsername","--mac-signing-key-username"},
            {"mac_appStore","--mac-app-store"},
            {"mac_entitlements","--mac-entitlements"},
            {"mac_appCategory","--mac-app-category"}
    };

    private static final String[][] nixSettingKeyArgumentPairs = new String[][] {
            // UnixOptionsPanel
            {"nix_packageName","--linux-package-name"},
            {"nix_debMaintainer","--linux-deb-maintainer"},
            {"nix_menuGroup","--linux-menu-group"},
            {"nix_packageDependencies","--linux-package-deps"},
            {"nix_rpmLicenseType","--linux-rpm-license-type"},
            {"nix_releaseValue","--linux-app-release"},
            {"nix_appCategory","--linux-app-category"},
            {"nix_desktopShortcut","--linux-shortcut"}
    };

    private static Map<String, String> commandMap = new LinkedHashMap<>() {};

    public static void populateCommandMapFromCommandSettingsMap() {
        Map<String,String> commandSettingsMap = getCommandSettingsMap();
        commandMap.clear();
        parseCommandSettingsMap(genericSettingKeyArgumentPairs, commandSettingsMap);
        parseCommandSettingsMap(osDependentGenericSettingKeyMethodPairs, commandSettingsMap);
        switch (detectedOS) {
            case "windows":
                parseCommandSettingsMap(winSettingKeyArgumentPairs, commandSettingsMap);
                break;
            case "mac":
                parseCommandSettingsMap(macSettingKeyArgumentPairs, commandSettingsMap);
                break;
            case "unix":
                parseCommandSettingsMap(nixSettingKeyArgumentPairs, commandSettingsMap);
                break;
            default:
                System.err.println("Unknown OS: " + detectedOS);
        }
    }

    private static void parseCommandSettingsMap(String[][] settingKeyArgumentPairs, Map<String, String> commandSettingsMap) {
        for (String[] settingKeyArgumentPair : settingKeyArgumentPairs) {
            String key = osDependentGenericCheck(settingKeyArgumentPair[0]);
            String argument = settingKeyArgumentPair[1];
            String value = commandSettingsMap.get(key);

            if (value != null && !value.isEmpty()) {
                commandMap.put(argument, value);
            }
        }
    }

    public static ArrayList<String> getCommandList() {
        ArrayList<String> commandList = new ArrayList<>();
        commandList.add("jpackage");
        for (Map.Entry<String, String> entry : commandMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null && !value.isEmpty()) {
                if (value.equals("true")) {
                    commandList.add(key);
                } else if (!value.equals("false")) {
                    commandList.add(key);
                    commandList.add(value);
                }
            }
        }
        return commandList;
    }

    public static Map<String, String> getCommandMap() {
        return commandMap;
    }
}
