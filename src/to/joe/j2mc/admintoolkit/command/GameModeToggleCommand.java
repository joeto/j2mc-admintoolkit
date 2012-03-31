package to.joe.j2mc.admintoolkit.command;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.command.MasterCommand;

public class GameModeToggleCommand extends MasterCommand {

    public GameModeToggleCommand(J2MC_AdminToolkit fun) {
        super(fun);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (!isPlayer) {
            sender.sendMessage("This is lost on you");
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL)) {
            player.setGameMode(GameMode.CREATIVE);
        } else {
            player.setGameMode(GameMode.SURVIVAL);
        }
        this.plugin.getLogger().info(player.getName() + " changed game mode to " + player.getGameMode().toString());
    }
}
