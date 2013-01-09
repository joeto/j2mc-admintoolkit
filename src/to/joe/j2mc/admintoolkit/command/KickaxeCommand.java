package to.joe.j2mc.admintoolkit.command;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.command.MasterCommand;

public class KickaxeCommand extends MasterCommand<J2MC_AdminToolkit> {

    public KickaxeCommand(J2MC_AdminToolkit toolkit) {
        super(toolkit);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (isPlayer) {
            String username = player.getName().toLowerCase();
            if (this.plugin.kickAxe.contains(username)) {
                this.plugin.kickAxe.remove(username);
                player.sendMessage(ChatColor.GOLD + "Your gold axe seems less cool now.");
                for (ItemStack i : player.getInventory().getContents()) {
                    if (i.getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "KickAxe")) {
                        i.getItemMeta().setDisplayName(null);
                        i.getItemMeta().setLore(null);
                    }
                }
            } else {
                this.plugin.kickAxe.add(username);
                player.sendMessage(ChatColor.GOLD + "You rub your gold axe.");
                ItemStack kickAxe = new ItemStack(Material.GOLD_AXE, 1);
                kickAxe.getItemMeta().setDisplayName(ChatColor.LIGHT_PURPLE + "KickAxe");
                kickAxe.getItemMeta().setLore(Arrays.asList(new String[] { "This special axe will kick players", "that are hit with it.", "Use it carefully." }));
                player.setItemInHand(kickAxe);
            }
        }
    }

}
