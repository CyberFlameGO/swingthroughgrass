package com.github.ascpixi.swingthroughgrass;

import com.github.ascpixi.swingthroughgrass.listener.GrassSwingListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class of the SwingThroughGrass plugin.
 */
public final class SwingThroughGrass extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer()
            .getPluginManager()
            .registerEvents(new GrassSwingListener(), this);
    }
}
