package to.joe.j2mc.admintoolkit.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;
import to.joe.j2mc.core.event.MessageEvent;

public class AdminModeCommand extends MasterCommand{
    
    J2MC_AdminToolkit plugin;

    public AdminModeCommand(J2MC_AdminToolkit plugin) {
        super(plugin);
        this.plugin = plugin;
    }
    
    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if(!J2MC_Manager.getPermissions().hasFlag(player.getName(), 'k')) {
	    	J2MC_Manager.getCore().adminAndLog(ChatColor.RED + player.getName() + " enabled GODMODE");
	        J2MC_Manager.getPermissions().addFlag(player, 'k');
	        player.getInventory().setHelmet(new ItemStack(51));
	        if (this.plugin.getServer().getPluginManager().isPluginEnabled("Chat")) {
	            if ((this.plugin.getServer().getPluginManager().getPlugin("Chat")).getConfig().getBoolean("enableformatinjection")){
	                player.setDisplayName(ChatColor.RED + player.getName());
	            }
	        }
        } else {
	        J2MC_Manager.getCore().adminAndLog(ChatColor.RED + player.getName() + " disabled GODMODE");
	        player.sendMessage(ChatColor.RED + "You fizzle out");
	        J2MC_Manager.getPermissions().delFlag(player, 'k');
	        player.getInventory().clear(51);
	        this.plugin.getServer().getPluginManager().callEvent(new MessageEvent(MessageEvent.compile("RESTORECOLOUR"), player.getName()));
        }
    }
    
}
