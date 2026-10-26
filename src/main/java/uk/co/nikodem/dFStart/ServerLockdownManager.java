package uk.co.nikodem.dFStart;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ServerLockdownManager implements Listener {
    public static Component joinErrorMessage = MiniMessage.miniMessage().deserialize("<aqua>Welcome! The server is currently paused.");
    public static Component interactErrorMessage = MiniMessage.miniMessage().deserialize("<red>The server is paused!");

    public static void onEnable() {
        if (!ConfigManager.isServerEnabled()) {
            World world = Bukkit.getWorld(ConfigManager.getWorldName());
            if (world != null) {
                world.getWorldBorder().setCenter(world.getSpawnLocation());
                world.getWorldBorder().changeSize(ConfigManager.getWorldBorderStartSize(), 0);
            }
            Bukkit.getServer().getServerTickManager().setFrozen(true);
        }
    }

    public static void onDisable() {
        if (!ConfigManager.isServerEnabled()) {
            World world = Bukkit.getWorld(ConfigManager.getWorldName());
            if (world != null) {
                world.getWorldBorder().setCenter(world.getSpawnLocation());
                world.getWorldBorder().changeSize(ConfigManager.getWorldBorderEndSize(), 0);
            }
            Bukkit.getServer().getServerTickManager().setFrozen(false);
        }
    }

    public static void onServerStart() {
        World world = Bukkit.getWorld(ConfigManager.getWorldName());
        if (world != null) {
            world.getWorldBorder().setCenter(world.getSpawnLocation());
            world.getWorldBorder().changeSize(ConfigManager.getWorldBorderEndSize(), 20*15);
        }
        Bukkit.getServer().getServerTickManager().setFrozen(false);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player plr = event.getPlayer();

        if (!ConfigManager.isServerEnabled()) {
            World world = Bukkit.getWorld(ConfigManager.getWorldName());
            if (world != null) {
                plr.teleport(world.getSpawnLocation());
                plr.sendMessage(joinErrorMessage);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player plr = event.getPlayer();
        if (!ConfigManager.isServerEnabled()) {
            event.setCancelled(true);
            plr.sendActionBar(interactErrorMessage);
        }
    }
}
