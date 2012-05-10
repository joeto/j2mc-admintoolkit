package to.joe.j2mc.admintoolkit.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;

public class KibblesCommand extends MasterCommand{
    
    J2MC_AdminToolkit plugin;

    public KibblesCommand(J2MC_AdminToolkit plugin) {
        super(plugin);
        this.plugin = plugin;
    }
    
    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        J2MC_Manager.getPermissions().addFlag(player, 'k');
        if (this.plugin.getServer().getPluginManager().isPluginEnabled("Chat")) {
            player.setDisplayName(ChatColor.RED + player.getName());
        }
    }
    
}
