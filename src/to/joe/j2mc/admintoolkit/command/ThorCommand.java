package to.joe.j2mc.admintoolkit.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.command.MasterCommand;

public class ThorCommand extends MasterCommand {

    public ThorCommand(J2MC_AdminToolkit toolkit) {
        super(toolkit);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (isPlayer) {
            if(J2MC_AdminToolkit.isAGod(player)) {
                J2MC_AdminToolkit.removeGodlyPowers(player.toString());
                player.sendMessage(ChatColor.GOLD + "You lose your mystical powers");
            } else {
                J2MC_AdminToolkit.giveGodlyPowers(player.toString());
                player.sendMessage(ChatColor.GOLD + "You gain mystical powers");
            }
        }
    }

}
