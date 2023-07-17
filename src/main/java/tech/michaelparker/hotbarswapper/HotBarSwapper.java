package tech.michaelparker.hotbarswapper;


import org.bukkit.plugin.java.JavaPlugin;
import tech.michaelparker.hotbarswapper.handlers.SwapperHandler;


public final class HotBarSwapper extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("HotBarSwapper has been enabled!");
        //load the handler
        getServer().getPluginManager().registerEvents(new SwapperHandler(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }







}
