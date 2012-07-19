package to.joe.j2mc.admintoolkit.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;

public class TimeCommand extends MasterCommand {

    public TimeCommand(J2MC_AdminToolkit AdminToolKit) {
        super(AdminToolKit);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (isPlayer) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Usage: /time <day/night>");
                return;
            }
            boolean day;
            if (args[0].equalsIgnoreCase("day") || args[0].equalsIgnoreCase("d")) {
                day = true;
            } else {
                if (args[0].equalsIgnoreCase("night") || args[0].equalsIgnoreCase("n")) {
                    day = false;
                } else {
                    sender.sendMessage("Usage: /time <day/night>");
                    return;
                }
            }
            if (day) {
                player.getWorld().setTime(1000);
                J2MC_Manager.getCore().adminAndLog(ChatColor.DARK_AQUA + sender.getName() + " changed time to day.");
            } else {
                player.getWorld().setTime(13000);
                J2MC_Manager.getCore().adminAndLog(ChatColor.DARK_AQUA + sender.getName() + " changed time to night.");
            }
        }
    }

}
