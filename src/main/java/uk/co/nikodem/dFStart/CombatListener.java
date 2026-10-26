package uk.co.nikodem.dFStart;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CombatListener implements Listener {
    public static Component pvpErrorMessage = MiniMessage.miniMessage().deserialize("<red>PVP isn't enabled yet!");

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player victim) {
            if (event.getDamageSource().getCausingEntity() instanceof Player attacker) {
                if (ConfigManager.isPVPEnabled()) {
                    event.setCancelled(true);
                    attacker.sendMessage(pvpErrorMessage);
                }
            }
        }
    }
}
