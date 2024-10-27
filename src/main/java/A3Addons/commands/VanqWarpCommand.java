package A3Addons.commands;

import A3Addons.A3Addons;
import A3Addons.config.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class VanqWarpCommand extends GenericCommand {
    public VanqWarpCommand() {
        super("vanq", new String[]{"help", "list", "add", "clear", "party", "warp", "remove"});
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentText("§cError: Invalid usage. Try /vanq help"));
        } 
        else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                sender.addChatMessage(new ChatComponentText("§5 A3Addons §6/vanq"));
                sender.addChatMessage(new ChatComponentText("Usage:"));
                sender.addChatMessage(new ChatComponentText("- /vanq party - parties everybody on the list and sends coordinates"));
                sender.addChatMessage(new ChatComponentText("- /vanq warp - warps the party, sends coords, and leaves"));
                sender.addChatMessage(new ChatComponentText("- /vanq list - lists people to warp"));
                sender.addChatMessage(new ChatComponentText("- /vanq clear - clears list of people to warp"));
                sender.addChatMessage(new ChatComponentText("- /vanq add §ousername§r - adds a player to the list"));
                sender.addChatMessage(new ChatComponentText("- /vanq remove §ousername§r - removes a player from the list"));
                sender.addChatMessage(new ChatComponentText("- /vanq help - displays this menu"));
            } else if (args[0].equalsIgnoreCase("list")) {
                listList(sender);
            } else if (args[0].equalsIgnoreCase("clear")) {
                clearList(sender);
            } else if (args[0].equalsIgnoreCase("party")) {
                party(sender);
            } else if (args[0].equalsIgnoreCase("warp")) {
                warp(sender);
            } else {
                sender.addChatMessage(new ChatComponentText("§cError: Invalid usage. Try /vanq help"));
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("add")) {
                addToList(sender, args[1]);
            } else if (args[0].equalsIgnoreCase("remove")){
                removeFromList(sender, args[1]);
            } else {
                sender.addChatMessage(new ChatComponentText("§cError: Invalid usage. Try /vanq help"));
            }
        } else {
            sender.addChatMessage(new ChatComponentText("§cError: Invalid usage. Try /vanq help"));
        }
    }

    private static void warp(ICommandSender sender){
        if (A3Addons.vanqList.size() == 0) { 
            sender.addChatMessage(new ChatComponentText("Your vanquisher auto-warp list is empty!"));
            return;
        }

        new Thread( new Runnable() {
            public void run() {
                try  { 
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/party warp");
                    Thread.sleep(2000); 
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/patcher sendcoords");
                    Thread.sleep(500); 
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/p leave");
                }
                catch (InterruptedException ie)  {
                    System.out.println("Delay interuppted! Returning...");
                    return;
                }
            }
        }).start();
    }


    public static void party(ICommandSender sender){
        new Thread( new Runnable() {
            public void run() {
                try  { 
                    for (String player : A3Addons.vanqList){ 
                        Minecraft.getMinecraft().thePlayer.sendChatMessage("/party " + player);
                        Thread.sleep(250);
                    }
                }
                catch (InterruptedException ie)  {
                    System.out.println("Delay interuppted! Returning...");
                    return;
                }
            }
        }).start();
    }

    private static void addToList(ICommandSender sender, String player){
        A3Addons.vanqList.add(player);
        sender.addChatMessage(new ChatComponentText("Added " + player + " to the vanquisher auto-warp list"));
    }

    private static void removeFromList(ICommandSender sender, String player){
        A3Addons.vanqList.remove(player);
        sender.addChatMessage(new ChatComponentText("Removed " + player + " from the vanquisher auto-warp list"));
    }

    private static void clearList(ICommandSender sender){
        A3Addons.vanqList.clear();
        sender.addChatMessage(new ChatComponentText("Cleared vanquisher auto-warp list"));
    }

    private static void listList(ICommandSender sender){ 
        sender.addChatMessage(new ChatComponentText("§6 A3Addons Vanquisher Auto-Warp"));
        if (A3Addons.vanqList.size() == 0) {
            sender.addChatMessage(new ChatComponentText("Uh-oh, this list appears to be empty!"));
            return;
        }
        for (String player : A3Addons.vanqList) {
            sender.addChatMessage(new ChatComponentText("- " + player));
        }
    }
}
