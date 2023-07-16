package tech.michaelparker.hotbarswapper;

import org.bukkit.plugin.java.JavaPlugin;

public final class HotBarSwapper extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("HotBarSwapper has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
