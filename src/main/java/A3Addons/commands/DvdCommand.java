package A3Addons.commands;

import A3Addons.A3Addons;
import A3Addons.config.ConfigHandler;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class DvdCommand extends GenericCommand {

    public DvdCommand() {
        super("dvd", new String[]{"help", "str", "speed", "default"});
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            toggle(sender);
        }
        else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                sender.addChatMessage(new ChatComponentText("§5 A3Addons §6/dvd"));
                sender.addChatMessage(new ChatComponentText("Usage:"));
                sender.addChatMessage(new ChatComponentText("- /dvd - toggles dvd on/off with default string"));
                sender.addChatMessage(new ChatComponentText("- /dvd str §ostr§r - Sets custom dvd string with custom string. Formatting codes work to, use && as the symbol. For Example: '&&d&&ka&&r&&6DvD&&r&&d&&ka'"));
                sender.addChatMessage(new ChatComponentText("- /dvd speed §ospeed§r - Sets the speed of the dvd. Defaults to 32"));
                sender.addChatMessage(new ChatComponentText("- /dvd default - Reverts string and speed to default"));
                sender.addChatMessage(new ChatComponentText("- /dvd help - displays this menu"));
            } else if (args[0].equalsIgnoreCase("default")) {
                setStr(sender, "§6DvD");
                setSpeed(sender, 32);
            }
        } else if (args.length >= 2) {
            if (args[0].equalsIgnoreCase("str")) {
                String str = "";
                for (int i = 1; i < args.length; i ++){
                    str += args[i];
                }
                setStr(sender, str);
            } else if (args[0].equalsIgnoreCase("speed")) {
                int speed = 0;
                try {
                    speed = Integer.parseInt(args[1]);
                } catch(Exception e) {
                    sender.addChatMessage(new ChatComponentText("§cError: Invalid speed. Make sure the speed is an integer e.g. 20 not 20.5"));
                    return;
                }
                setSpeed(sender, speed);
            }
        } else {
            sender.addChatMessage(new ChatComponentText("§cError: Invalid usage. Try /dvd help"));
        }
    }

    private static void setStr(ICommandSender sender, String str) {
        str = str.replace("&&", "§");
        ConfigHandler.dvdStr = str;
        ConfigHandler.saveConfig();
        sender.addChatMessage(new ChatComponentText("Set dvd string to " + str));
    }

    private static void setSpeed(ICommandSender sender, int speed) {
        ConfigHandler.dvdSpeed = speed;
        ConfigHandler.saveConfig();
        sender.addChatMessage(new ChatComponentText("Set dvd speed to " + Integer.toString(speed)));
    }

    private static void toggle(ICommandSender sender) {
        if (!A3Addons.dvdEnabled) {
            sender.addChatMessage(new ChatComponentText("Enabling DVD"));
        } else {
            sender.addChatMessage(new ChatComponentText("Disabling DVD"));
        }

        A3Addons.dvdEnabled = !A3Addons.dvdEnabled;
    }
}