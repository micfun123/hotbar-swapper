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
        if (Bukkit.getOnlinePlayers().size() >= 2) {
            swapHotbar();
        }
    }

    private void swapHotbar() {
        Random rand = new Random();
        int time = rand.nextInt(5) + 1; // get a random time between 1 and 5 seconds
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Bukkit.broadcastMessage("Swapping hotbars in " + time + " seconds!");

        //swap the hotbars of all players
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().setHeldItemSlot(rand.nextInt(9));
        }

        //if players are less than 2, then don't swap hotbars
        if (Bukkit.getOnlinePlayers().size() < 2) {
            return;
        }

        Bukkit.broadcastMessage("Hotbars have been swapped!");
        swapHotbar(); // uncomment this line if you want to keep swapping hotbars
    }
}