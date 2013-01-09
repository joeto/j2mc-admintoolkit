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

public class KickaxeCommand extends MasterCommand<J2MC_AdminToolkit> {

    public KickaxeCommand(J2MC_AdminToolkit toolkit) {
        super(toolkit);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (isPlayer) {
            boolean hit = false;
            for (ItemStack i : player.getInventory().getContents()) {
                if (i.getItemMeta().getDisplayName().equals(J2MC_AdminToolkit.KICKAXE_NAME)) {
                    ItemMeta meta = i.getItemMeta();
                    meta.setDisplayName(null);
                    meta.setLore(null);
                    i.setItemMeta(meta);
                    hit = true;
                }
            }
            if (hit) {
                player.sendMessage(ChatColor.GOLD + "Your gold axe seems less cool now.");
            } else {
                player.sendMessage(ChatColor.GOLD + "You rub your gold axe.");
                ItemStack kickAxe = new ItemStack(Material.GOLD_AXE, 1);
                ItemMeta meta = kickAxe.getItemMeta();
                meta.setDisplayName(J2MC_AdminToolkit.KICKAXE_NAME);
                meta.setLore(Arrays.asList(J2MC_AdminToolkit.KICKAXE_LORE));
                kickAxe.setItemMeta(meta);
                player.setItemInHand(kickAxe);
            }
        }
    }

}
