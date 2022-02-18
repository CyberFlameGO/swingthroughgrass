package com.github.ascpixi.stg.command;

import com.github.ascpixi.stg.SwingThroughGrass;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Locale;

/**
 * The handler for the main SwingThroughGrass command.
 */
public class MainCommandHandler implements CommandExecutor {
    private final SwingThroughGrass plugin;

    /**
     * Constructs a new instance of the MainCommandHandler class.
     * @param plugin The calling plugin.
     */
    public MainCommandHandler(SwingThroughGrass plugin){
        this.plugin = plugin;
    }

    /**
     * Sends an "incorrect usage" message to the specified command sender.
     * @param sender The target command sender.
     * @param command The command in question.
     */
    void rejectWrongUsage(CommandSender sender, Command command){
        sender.sendMessage(
            ChatColor.RED + "Usage: " + command.getUsage()
        );
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1){
            switch (args[0].toLowerCase(Locale.ROOT)){
                case "reload":
                    plugin.reload();
                    sender.sendMessage(ChatColor.GREEN + "The plugin has been reloaded.");
                    return true;
                case "version":
                    sender.sendMessage(
                        "Running " +
                        ChatColor.YELLOW + "SwingThroughGrass " + plugin.getDescription().getVersion() +
                        ChatColor.RESET + "."
                    );
                    return true;
                default:
                    rejectWrongUsage(sender, command);
                    return true;
            }
        } else rejectWrongUsage(sender, command);

        return true;
    }
}
