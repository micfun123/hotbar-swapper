package tech.michaelparker.hotbarswapper.handlers;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Random;

public class SwapperHandler implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // Your custom logic when a player joins
        Bukkit.broadcastMessage("you have`"+ Bukkit.getOnlinePlayers().size() + " amount of people");
        if (Bukkit.getOnlinePlayers().size() == 2) {
            swapHotbar();
        }
    }

    private void swapHotbar() {
        Random rand = new Random();
        int time = rand.nextInt(5) + 1; // get a random time between 1 and 5 seconds

        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("HotBarSwapper"), () -> {
            Bukkit.broadcastMessage("Swapping hotbars in " + time + " seconds!");

            // Swap the full hotbars of all players with another player
            for (Player player : Bukkit.getOnlinePlayers()) {
                Player randomPlayer = (Player) Bukkit.getOnlinePlayers().toArray()[rand.nextInt(Bukkit.getOnlinePlayers().size())];
                player.getInventory().setContents(randomPlayer.getInventory().getContents());
            }

            // If players are less than 2, then don't swap hotbars
            if (Bukkit.getOnlinePlayers().size() < 2) {
                return;
            }

            Bukkit.broadcastMessage("Hotbars have been swapped!");
            swapHotbar(); // uncomment this line if you want to keep swapping hotbars
        }, time * 20); // Convert seconds to ticks (1 second = 20 ticks)

    }
}