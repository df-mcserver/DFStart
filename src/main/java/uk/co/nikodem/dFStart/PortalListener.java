package uk.co.nikodem.dFStart;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.inventory.ItemStack;

public class PortalListener implements Listener {
    public static Component endErrorMessage = MiniMessage.miniMessage().deserialize("<red>The end isn't enabled yet!");
    public static Component netherErrorMessage = MiniMessage.miniMessage().deserialize("<red>The nether isn't enabled yet!");

    @EventHandler
    public void onLight(PortalCreateEvent event) {
        if (event.getReason().equals(PortalCreateEvent.CreateReason.END_PLATFORM)) {
            if (ConfigManager.isEndEnabled()) return;
            // the end
            event.setCancelled(true);

            if (event.getEntity() != null) {
                if (event.getEntity() instanceof Player plr) {
                    plr.sendMessage(endErrorMessage);
                }
            }
        } else {
            if (ConfigManager.isNetherEnabled()) return;
            // nether
            event.setCancelled(true);

            if (event.getEntity() != null) {
                if (event.getEntity() instanceof Player plr) {
                    plr.sendMessage(netherErrorMessage);
                }
            }
        }
    }

    @EventHandler
    public void onEnter(EntityPortalEnterEvent event) {
        switch (event.getPortalType()) {
            case ENDER -> {
                if (ConfigManager.isEndEnabled()) return;

                event.setCancelled(true);

                if (event.getEntity() instanceof Player plr) {
                    plr.sendMessage(endErrorMessage);
                }
            }

            case NETHER -> {
                if (ConfigManager.isNetherEnabled()) return;

                event.setCancelled(true);

                if (event.getEntity() instanceof Player plr) {
                    plr.sendMessage(netherErrorMessage);
                }
            }
        }
    }

    @EventHandler
    public void onPlaceEnderEye(PlayerInteractEvent event) {
        if (ConfigManager.isEndEnabled()) return;
        if (event.hasItem() && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player plr = event.getPlayer();
            ItemStack item = event.getItem();
            Block block = event.getClickedBlock();

            if (item == null) return;
            if (block == null) return;

            if (item.getType().equals(Material.ENDER_EYE) && block.getType().equals(Material.END_PORTAL_FRAME)) {
                event.setCancelled(true);
                plr.sendMessage(endErrorMessage);
            }
        }
    }
}
