package main.com.everdro1d.jpackage.core;

import java.util.HashMap;
import java.util.Map;

public class CmdSettings {
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

}
