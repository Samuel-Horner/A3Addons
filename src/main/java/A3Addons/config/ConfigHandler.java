package A3Addons.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    private static Configuration config;

    public static String dvdStr;

    // reads the config file and loads the values from
    // the config into the fields exampleBooleanSetting exampleStringSetting exampleDoubleSetting
    public static void loadConfig(File file) {
        config = new Configuration(file);
        config.load();
        dvdStr = config.get("General", "dvdStr", "§6DvD", "The string to display the dvd with.").getString();
    }

    // you want to call the saveConfig() when you change the values of the fields via code
    // and want to have the changes saved to the config file
    public static void saveConfig() {
        config.get("General", "dvdStr", "§6DvD", "The string to display the dvd with.").setValue(dvdStr);
        if (config.hasChanged()) {
            config.save();
        }
    }

}