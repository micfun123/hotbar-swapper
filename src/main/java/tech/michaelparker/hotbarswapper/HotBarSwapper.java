package tech.michaelparker.hotbarswapper;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.Random;


public final class HotBarSwapper extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("HotBarSwapper has been enabled!");
        // start the hotbar swapping
        swapHotbar();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public void swapHotbar() {
        getLogger().info("SWAPPING!");
        int time = 0;
        // get a random time between 1 and 5 seconds
        Random rand = new Random();
        time = rand.nextInt(5) + 1;
        // wait for the time
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bukkit.broadcastMessage("Swapping hotbars in " + time + " seconds!");

        // swap the hotbar of random players
        Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);
        // if there are no players online, return
        if (players.length == 0) {
            swapHotbar();
        }
        for (Player player : players) {
            int hotbar = 0;
            // get a random hotbar between 0 and 8 (inclusive)
            hotbar = rand.nextInt(9);
            // swap the hotbar
            player.getInventory().setHeldItemSlot(hotbar);
        }
        // send a message to the chat
        Bukkit.broadcastMessage("Hotbars have been swapped!");

        // repeat
        swapHotbar();
    }

}
