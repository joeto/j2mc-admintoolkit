package to.joe.j2mc.admintoolkit.command;

import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.J2MC_Core;
import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;
import to.joe.j2mc.core.event.MessageEvent;

public class AdminGlobalChatCommand extends MasterCommand {

    public AdminGlobalChatCommand(J2MC_AdminToolkit adminToolKit) {
        super(adminToolKit);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (sender.hasPermission(J2MC_AdminToolkit.adminPerm)) {
            if (args.length < 1) {
                sender.sendMessage(ChatColor.RED + "Usage: /g Message");
                return;
            }
            final String message = J2MC_Core.combineSplit(0, args, " ");
            for (final Player plr : J2MC_Manager.getVisibility().getOnlinePlayers(null)) {
                if (plr.hasPermission(J2MC_AdminToolkit.adminPerm)) {
                    plr.sendMessage("<" + player.getName() + "> " + ChatColor.LIGHT_PURPLE + message);
                } else {
                    plr.sendMessage("<ADMIN> " + ChatColor.LIGHT_PURPLE + message);
                }
            }
            HashSet<String> targets = new HashSet<String>();
            targets.add("GAMEMSG");
            plugin.getServer().getPluginManager().callEvent(new MessageEvent(targets, "<ADMIN> " + ChatColor.LIGHT_PURPLE + message));
        }
    }

}
