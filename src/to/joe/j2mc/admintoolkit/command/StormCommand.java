package to.joe.j2mc.admintoolkit.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;

public class StormCommand extends MasterCommand {

    public StormCommand(J2MC_AdminToolkit toolkit) {
        super(toolkit);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (isPlayer) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Usage: /storm <start/stop>");
                return;
            }
            if (args[0].equalsIgnoreCase("start")) {
                player.getWorld().setStorm(true);
                J2MC_Manager.getCore().adminAndLog(ChatColor.RED + player.getName() + " starts up a storm");
                J2MC_Manager.getCore().messageNonAdmin(ChatColor.RED + "Somebody has started a storm!");
            } else if (args[0].equalsIgnoreCase("stop")) {
                player.getWorld().setStorm(false);
                J2MC_Manager.getCore().adminAndLog(ChatColor.RED + player.getName() + " stops the storm");
                J2MC_Manager.getCore().messageNonAdmin(ChatColor.RED + "Somebody has prevented a storm!");
            } else {
                player.sendMessage(ChatColor.RED + "Usage: /storm <start/stop>");
            }
        }
    }

}
