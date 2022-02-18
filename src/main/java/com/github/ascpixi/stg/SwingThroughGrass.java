package com.github.ascpixi.stg;

import com.github.ascpixi.stg.command.MainCommandHandler;
import com.github.ascpixi.stg.listener.GrassSwingListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

/**
 * The main class of the SwingThroughGrass plugin.
 */
public final class SwingThroughGrass extends JavaPlugin {
    private static Metrics metrics;
    private static GrassSwingListener mainListener;

    /**
     * Gets the Metrics instance for the plugin.
     * @return The metrics instance, or null if the plugin hasn't been initialized yet
     */
    public static Metrics getMetrics(){
        return metrics;
    }

    /**
     * Reloads the plugin.
     */
    public void reload(){
        reloadConfig();
        mainListener.reload();
    }

    /**
     * Gets the command with the given name, specific to this plugin.
     * If the command with the given name does not exist, an IllegalStateException is thrown.
     * @param commandName The name of the command.
     * @return The found PluginCommand.
     */
    @Nonnull
    PluginCommand requireCommandExists(String commandName){
        PluginCommand command = getCommand(commandName);

        if(command == null)
            throw new IllegalStateException("The command \"" + commandName + "\" isn't registered in the plugin.yml file.");

        return command;
    }

    @Override
    public void onEnable() {
        metrics = new Metrics(this, 14356);

        saveDefaultConfig();

        mainListener = new GrassSwingListener(this);
        getServer()
            .getPluginManager()
            .registerEvents(mainListener, this);

        requireCommandExists("swingthroughgrass")
            .setExecutor(new MainCommandHandler(this));
    }
}
