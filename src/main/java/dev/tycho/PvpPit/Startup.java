package dev.tycho.PvpPit;

import dev.tycho.PvpPit.Listeners.SignListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Startup extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("PvpPit is enabled!");
        this.saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new SignListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("PvpPit is disabled!");
    }
}
