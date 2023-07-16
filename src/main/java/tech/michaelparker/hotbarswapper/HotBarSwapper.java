package tech.michaelparker.hotbarswapper;

import org.bukkit.plugin.java.JavaPlugin;
import tech.michaelparker.hotbarswapper.handlers.swapper;

public final class HotBarSwapper extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("HotBarSwapper has been enabled!");
        swapper.swapHotbar();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
