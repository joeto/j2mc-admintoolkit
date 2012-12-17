package to.joe.j2mc.admintoolkit.command;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.command.MasterCommand;

public class CooCommand extends MasterCommand<J2MC_AdminToolkit> {

    public CooCommand(J2MC_AdminToolkit AdminToolKit) {
        super(AdminToolKit);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (isPlayer) {
            if (args.length != 3) {
                sender.sendMessage(ChatColor.RED + "Usage: /coo x y z");
                return;
            }
            final Entity vehicle = player.getVehicle();
            if (vehicle != null) {
                player.leaveVehicle();
                vehicle.remove();
            }
            try {
                player.teleport(new Location(player.getWorld(), Double.valueOf(args[0]), Double.valueOf(args[1]), Double.valueOf(args[2]), 0, 0));
                player.sendMessage(ChatColor.RED + "WHEEEEEEE. I HOPE THIS ISN'T UNDERGROUND!");
            } catch (final Exception e) {
                player.sendMessage(ChatColor.RED + "Maybe you'll write out numbers next time");
            }
        }
    }

}
