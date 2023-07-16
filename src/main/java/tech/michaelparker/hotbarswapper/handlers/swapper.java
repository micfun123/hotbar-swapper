package tech.michaelparker.hotbarswapper.handlers;
import org.bukkit.entity.Player;
import java.util.Random;
import static org.bukkit.Bukkit.getLogger;



public class swapper {

    // randomly swap a player's hotbar
    public static void swapHotbar() {
        getLogger().info("SWAPPING !");
        int time = 0;
        //get a random time between 1 and 5 seconds
        Random rand = new Random();
        time = rand.nextInt(5) + 1;
        //wait for the time
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        org.bukkit.Bukkit.broadcastMessage("Swapping hotbars in " + time + " seconds!");
        //swap the hotbar of random players
        Player[] players = org.bukkit.Bukkit.getOnlinePlayers().toArray(new Player[0]);
        //if there are no players online, return
        if (players.length == 0) {
            return;
        }
        for (Player player : players) {
            int hotbar = 0;
            //get a random hotbar between 1 and 9
            hotbar = rand.nextInt(9) + 1;
            //swap the hotbar
            player.getInventory().setHeldItemSlot(hotbar);
        }
        //send a message to the chat
        org.bukkit.Bukkit.broadcastMessage("Hotbars have been swapped!");


        //repeat
        swapHotbar();

    }

}
