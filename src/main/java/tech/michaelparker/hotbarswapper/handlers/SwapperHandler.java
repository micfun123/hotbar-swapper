package tech.michaelparker.hotbarswapper.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class SwapperHandler implements Listener, CommandExecutor {

    public SwapperHandler(JavaPlugin plugin) {
        // Register the command and set its executor to this class
        plugin.getCommand("startswap").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("startswap")) {
            // Check if the command sender is a player
            if (sender instanceof Player) {
                Player player = (Player) sender;
                // Check if there are at least two players online to perform the swap
                if (Bukkit.getOnlinePlayers().size() >= 2) {
                    swapHotbar();
                    Bukkit.broadcastMessage("Hotbars have been swapped!");
                } else {
                    player.sendMessage("Not enough players online to perform the swap.");
                }
            }
            return true;
        }
        return false;
    }

    private void swapHotbar() {
        Random rand = new Random();
        int time = rand.nextInt(300) + 1; // get a random time between 1 and 10 seconds

        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("HotBarSwapper"), () -> {

            // Swap the hotbars of all players with one another
            Player[] onlinePlayersArray = Bukkit.getOnlinePlayers().toArray(new Player[0]);
            int numPlayers = onlinePlayersArray.length;

            for (int i = 0; i < numPlayers; i++) {
                Player player = onlinePlayersArray[i];
                Player randomPlayer = onlinePlayersArray[rand.nextInt(numPlayers)];

                ItemStack[] tempHotbarContents = new ItemStack[9];
                ItemStack[] playerHotbarContents = player.getInventory().getContents().clone();
                ItemStack[] randomPlayerHotbarContents = randomPlayer.getInventory().getContents().clone();

                // Store the hotbar contents of the first player in a temporary array
                System.arraycopy(playerHotbarContents, 0, tempHotbarContents, 0, 9);

                // Set the hotbar contents of the first player to the hotbar contents of the second player
                System.arraycopy(randomPlayerHotbarContents, 0, playerHotbarContents, 0, 9);

                // Set the hotbar contents of the second player to the contents stored in the temporary array
                System.arraycopy(tempHotbarContents, 0, randomPlayerHotbarContents, 0, 9);

                // Update the inventories with the modified hotbar contents
                player.getInventory().setContents(playerHotbarContents);
                randomPlayer.getInventory().setContents(randomPlayerHotbarContents);
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
