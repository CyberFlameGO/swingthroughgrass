package com.github.ascpixi.stg;

import com.github.ascpixi.stg.listener.GrassSwingListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class of the SwingThroughGrass plugin.
 */
public final class SwingThroughGrass extends JavaPlugin {
    private static Metrics metrics;

    /**
     * Gets the Metrics instance for the plugin.
     * @return The metrics instance, or null if the plugin hasn't been initialized yet
     */
    public static Metrics getMetrics(){
        return metrics;
    }

    @Override
    public void onEnable() {
        metrics = new Metrics(this, 14356);

        getServer()
            .getPluginManager()
            .registerEvents(new GrassSwingListener(), this);
    }
}
