package uk.co.nikodem.dFStart.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFStart.ConfigManager;
import uk.co.nikodem.dFStart.DFStart;

public class EnableEnd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        DFStart.showCountdownTitle("5");
        Bukkit.getScheduler().runTaskLater(DFStart.getPlugin(), () -> DFStart.showCountdownTitle("4"), 20L);
        Bukkit.getScheduler().runTaskLater(DFStart.getPlugin(), () -> DFStart.showCountdownTitle("3"), 40L);
        Bukkit.getScheduler().runTaskLater(DFStart.getPlugin(), () -> DFStart.showCountdownTitle("2"), 60L);
        Bukkit.getScheduler().runTaskLater(DFStart.getPlugin(), () -> DFStart.showCountdownTitle("1"), 80L);
        Bukkit.getScheduler().runTaskLater(DFStart.getPlugin(), () -> {
            DFStart.showCountdownTitle("<purple>The End is enabled!", Sound.ENTITY_ENDER_DRAGON_AMBIENT);
            ConfigManager.setEndEnabled(true);
        }, 100L);
        return false;
    }
}
