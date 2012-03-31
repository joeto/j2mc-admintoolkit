package to.joe.j2mc.admintoolkit.command;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;
import to.joe.j2mc.core.exceptions.BadPlayerMatchException;

public class WhereIsPlayerCommand extends MasterCommand {

    public WhereIsPlayerCommand(J2MC_AdminToolkit AdminToolKit) {
        super(AdminToolKit);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "/whereis player");
            return;
        }
        Player target = null;
        try {
            target = J2MC_Manager.getVisibility().getPlayer(args[0], null);
        } catch (final BadPlayerMatchException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
            return;
        }
        final Location loc = target.getLocation();
        sender.sendMessage(ChatColor.RED + target.getName() + ": " + loc.getX() + " " + loc.getY() + " " + loc.getZ());
    }
}
