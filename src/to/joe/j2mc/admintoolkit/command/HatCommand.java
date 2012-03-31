package to.joe.j2mc.admintoolkit.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.command.MasterCommand;

public class HatCommand extends MasterCommand {

    public HatCommand(J2MC_AdminToolkit AdminToolKit) {
        super(AdminToolKit);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (isPlayer) {
            final ItemStack meow = player.getItemInHand();
            if ((meow.getAmount() > 0) && (meow.getTypeId() < 256)) {
                player.getInventory().setHelmet(new ItemStack(meow.getType(), 1));
                meow.setAmount(meow.getAmount() - 1);
                player.sendMessage(ChatColor.RED + "You pat your new helmet");
            } else {
                player.sendMessage(ChatColor.RED + "You pat your head");
            }
        }
    }

}
