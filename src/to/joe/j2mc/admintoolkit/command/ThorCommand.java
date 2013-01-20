package to.joe.j2mc.admintoolkit.command;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.command.MasterCommand;

public class ThorCommand extends MasterCommand<J2MC_AdminToolkit> {

    public ThorCommand(J2MC_AdminToolkit toolkit) {
        super(toolkit);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (isPlayer) {
            boolean hit = false;
            for (ItemStack i : player.getInventory().getContents()) {
                if (i.getItemMeta().getDisplayName().equals(J2MC_AdminToolkit.THORAXE_NAME)) {
                    ItemMeta meta = i.getItemMeta();
                    meta.setDisplayName(null);
                    meta.setLore(null);
                    i.setItemMeta(meta);
                    hit = true;
                }
            }
            if (hit) {
                player.sendMessage(ChatColor.GOLD + "You lose your mystical powers.");
            } else {
                player.sendMessage(ChatColor.GOLD + "You suddenly gain mystical powers.");
                ItemStack kickAxe = new ItemStack(Material.GOLD_AXE, 1);
                ItemMeta meta = kickAxe.getItemMeta();
                meta.setDisplayName(J2MC_AdminToolkit.THORAXE_NAME);
                meta.setLore(Arrays.asList(J2MC_AdminToolkit.THORAXE_LORE));
                kickAxe.setItemMeta(meta);
                player.setItemInHand(kickAxe);
            }
        }
    }

}
