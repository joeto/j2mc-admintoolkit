package to.joe.j2mc.admintoolkit.command;

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
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /g Message");
            return;
        }
        final String message = J2MC_Core.combineSplit(0, args, " ");
        final String adminMsg = "<" + sender.getName() + "> " + ChatColor.LIGHT_PURPLE + message;
        final String publicMsg = "<ADMIN> " + ChatColor.LIGHT_PURPLE + message;
        J2MC_Manager.getCore().adminAndLog(adminMsg);
        J2MC_Manager.getCore().messageNonAdmin(publicMsg);
        this.plugin.getServer().getPluginManager().callEvent(new MessageEvent(MessageEvent.compile("GAMEMSG"), "<ADMIN> " + message));
    }

}
