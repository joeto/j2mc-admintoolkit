package to.joe.j2mc.admintoolkit.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.command.MasterCommand;

public class ThorCommand extends MasterCommand<J2MC_AdminToolkit> {

    public ThorCommand(J2MC_AdminToolkit toolkit) {
        super(toolkit);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (isPlayer) {
            String possibleGod = player.getName().toLowerCase();
            if (this.plugin.thor.contains(possibleGod)) {
                this.plugin.thor.remove(possibleGod);
                player.sendMessage(ChatColor.GOLD + "You lose your mystical powers");
            } else {
                this.plugin.thor.add(possibleGod);
                player.sendMessage(ChatColor.GOLD + "You gain mystical powers");
            }
        }
    }

}
