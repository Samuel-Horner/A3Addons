package A3Addons.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class GenericCommand extends CommandBase {
    private String command_str;
    private String[] command_options;

    public GenericCommand(String command, String[] options) {
        command_str = command;
        command_options = options;
    }

    @Override
    public String getCommandName() {
        return command_str;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + this.getCommandName();
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true; // return true otherwise you won't be able to use the command
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        // Overide this method to actually do something
        sender.addChatMessage(new ChatComponentText("This command doesnt seem to do anything."));
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        // when you type the command and press tab complete,
        // this method will allow you to cycle through the words that match what you typed
        return getListOfStringsMatchingLastWord(args, command_options);
    }

}

/*
public class ExampleCommand extends GenericCommand {

    public ExampleCommand() {
        super("examplecommand", new String[]{"option1", "option2", "option3"});
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        // this code here runs when you use the command ingame
        sender.addChatMessage(new ChatComponentText("Hey your command is running!"));
        if (args.length > 0 && args[0].equalsIgnoreCase("option1")) {
            sender.addChatMessage(new ChatComponentText("Running option1!"));
        } else if (args.length > 0 && args[0].equalsIgnoreCase("option2")) {
            sender.addChatMessage(new ChatComponentText("Running option2!"));
        } else if (args.length > 0 && args[0].equalsIgnoreCase("option3")) {
            sender.addChatMessage(new ChatComponentText("Running option3!"));
        }
    }
}
*/