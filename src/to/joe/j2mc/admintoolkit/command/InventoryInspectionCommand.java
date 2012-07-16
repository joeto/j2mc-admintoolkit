package to.joe.j2mc.admintoolkit.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;
import to.joe.j2mc.core.exceptions.BadPlayerMatchException;

public class InventoryInspectionCommand extends MasterCommand {

    J2MC_AdminToolkit plugin;

    public InventoryInspectionCommand(J2MC_AdminToolkit plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (args.length == 1 && isPlayer) {
            final String targetName = args[0];
            Player target = null;
            try {
                target = J2MC_Manager.getVisibility().getPlayer(targetName, sender);
            } catch (final BadPlayerMatchException e) {
                sender.sendMessage(ChatColor.RED + e.getMessage());
                return;
            }
            player.openInventory(target.getInventory());
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: /invsee <playername>");
        }
    }

}