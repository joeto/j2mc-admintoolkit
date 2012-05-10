package to.joe.j2mc.admintoolkit.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;
import to.joe.j2mc.core.event.MessageEvent;

public class BitsCommand extends MasterCommand{

    J2MC_AdminToolkit plugin;
    
    public BitsCommand(J2MC_AdminToolkit plugin) {
        super(plugin);
        this.plugin = plugin;
    }
    
    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        J2MC_Manager.getCore().adminAndLog(ChatColor.RED + player.getName() + " disabled GODMODE");
        player.sendMessage(ChatColor.RED + "You fizzle out");
        J2MC_Manager.getPermissions().delFlag(player, 'k');
        plugin.getServer().getPluginManager().callEvent(new MessageEvent(MessageEvent.compile("RESTORECOLOUR"), player.getName()));
        player.getInventory().clear(39);
    }
    
}
