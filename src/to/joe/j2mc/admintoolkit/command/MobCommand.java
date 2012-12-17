package to.joe.j2mc.admintoolkit.command;

import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.command.MasterCommand;

public class MobCommand extends MasterCommand<J2MC_AdminToolkit> {

    public MobCommand(J2MC_AdminToolkit AdminToolKit) {
        super(AdminToolKit);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, final Player player, boolean isPlayer) {
        if (isPlayer) {
            if ((args.length == 0)) {
                sender.sendMessage(ChatColor.RED + "Usage: /mob name");
                return;
            }
            if (args[0].equalsIgnoreCase("list")) {
                sender.sendMessage(StringUtils.join(EntityType.values()));
                return;
            }
            if (args[0].equals("MissingNo")) {
                for (int x = 0; x < 20; x++) {
                    player.sendMessage(ChatColor.MAGIC + "locloclocloclocloclocloclocloclocloc");
                }
                this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
                    @Override
                    public void run() {
                        player.kickPlayer(ChatColor.MAGIC + "loc loc loc loc loc");
                    }
                }, 100);
            }
            final EntityType mob = EntityType.fromName(args[0]);
            if (mob != null) {
                final Block pos = player.getTargetBlock(null, 50);
                if (pos == null) {
                    sender.sendMessage("Unable to spawn mob there.");
                    return;
                }
                final Location loc = pos.getLocation().add(0, 1, 0);
                Entity ent = player.getWorld().spawnEntity(loc, mob);
                if (ent.getType() == EntityType.SLIME && args.length > 1) {
                    try {
                        int i = Integer.parseInt(args[1]);
                        if (i > 0 && i < 101) {
                            ((Slime) ent).setSize(i);
                        }
                    } catch (Exception e) {
                    }
                }
                sender.sendMessage("Spawned one " + mob.getName() + " at " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ());
                this.plugin.getLogger().log(Level.INFO, sender.getName() + " spawned one " + mob.getName() + " at " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ());
            } else {
                sender.sendMessage("Invalid mob.");
            }
        }
    }

}
