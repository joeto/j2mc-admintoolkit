package to.joe.j2mc.admintoolkit.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.J2MC_Core;
import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;

public class AdminChatCommand extends MasterCommand {

    public AdminChatCommand(J2MC_AdminToolkit AdminToolKit) {
        super(AdminToolKit);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /a message");
            return;
        }
        String message = J2MC_Core.combineSplit(0, args, " ");
        message = "<" + ChatColor.LIGHT_PURPLE + sender.getName() + ChatColor.WHITE + "> " + message;
        J2MC_Manager.getCore().adminAndLog(message);
    }

}
