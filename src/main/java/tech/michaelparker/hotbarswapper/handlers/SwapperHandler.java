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

        Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);
        if (players.length == 0) {
            return;
        }

        for (Player player : players) {
            int hotbar = rand.nextInt(9); // get a random hotbar between 0 and 8
            player.getInventory().setHeldItemSlot(hotbar); // swap the hotbar
        }

        Bukkit.broadcastMessage("Hotbars have been swapped!");
        swapHotbar(); // uncomment this line if you want to keep swapping hotbars
    }
}