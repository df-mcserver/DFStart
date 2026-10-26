package uk.co.nikodem.dFStart;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.nikodem.dFStart.Commands.EnableEnd;
import uk.co.nikodem.dFStart.Commands.EnableNether;
import uk.co.nikodem.dFStart.Commands.EnablePvp;
import uk.co.nikodem.dFStart.Commands.StartServer;

import java.util.Objects;

public final class DFStart extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();

        ServerLockdownManager.onEnable();

        getServer().getPluginManager().registerEvents(new PortalListener(), this);
        getServer().getPluginManager().registerEvents(new CombatListener(), this);
        getServer().getPluginManager().registerEvents(new ServerLockdownManager(), this);

        Objects.requireNonNull(this.getCommand("startserver")).setExecutor(new StartServer());
        Objects.requireNonNull(this.getCommand("enablepvp")).setExecutor(new EnablePvp());
        Objects.requireNonNull(this.getCommand("enablenether")).setExecutor(new EnableNether());
        Objects.requireNonNull(this.getCommand("enableend")).setExecutor(new EnableEnd());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ServerLockdownManager.onDisable();
    }

    public static void showCountdownTitle(String msg) {
        showCountdownTitle(msg, Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
    }

    public static void showCountdownTitle(String msg, Sound sound) {
        Bukkit.getOnlinePlayers().forEach(plr -> {
            plr.playSound(plr.getLocation(), sound, 1f, 1f);
            plr.sendTitlePart(TitlePart.TITLE, MiniMessage.miniMessage().deserialize("<dark_aqua>" + msg));
        });
    }

    public static DFStart getPlugin() {
        return (DFStart) DFStart.getProvidingPlugin(DFStart.class);
    }
}
