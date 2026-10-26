package uk.co.nikodem.dFStart;

public class ConfigManager {
    public static String netherConfigValue = "nether-enabled";
    public static String endConfigValue = "end-enabled";
    public static String pvpConfigValue = "pvp-enabled";
    public static String serverConfigValue = "server-started";

    public static boolean isNetherEnabled() {
        return getBoolean(netherConfigValue);
    }
    public static void setNetherEnabled(boolean value) {
        set(netherConfigValue, value);
    }

    public static boolean isEndEnabled() {
        return getBoolean(endConfigValue);
    }
    public static void setEndEnabled(boolean value) {
        set(endConfigValue, value);
    }

    public static boolean isPVPEnabled() {
        return getBoolean(pvpConfigValue);
    }
    public static void setPVPEnabled(boolean value) {
        set(pvpConfigValue, value);
    }

    public static boolean isServerEnabled() {
        return getBoolean(serverConfigValue);
    }
    public static void setServerEnabled(boolean value) {
        set(serverConfigValue, value);
    }

    public static String getWorldName() {
        String name = getString("world-name");
        return name == null ? "world" : name;
    }

    public static Integer getWorldBorderStartSize() {
        return getInteger("start-worldborder-size");
    }

    public static Integer getWorldBorderEndSize() {
        return getInteger("end-worldborder-size");
    }

    public static void set(String path, Object value) {
        DFStart.getPlugin().getConfig().set(path, value);
        DFStart.getPlugin().saveConfig();
    }

    public static Object get(String path) {
        return DFStart.getPlugin().getConfig().get(path);
    }

    public static boolean getBoolean(String path) {
        return DFStart.getPlugin().getConfig().getBoolean(path);
    }

    public static int getInteger(String path) {
        return DFStart.getPlugin().getConfig().getInt(path);
    }

    public static String getString(String path) {
        return DFStart.getPlugin().getConfig().getString(path);
    }
}
