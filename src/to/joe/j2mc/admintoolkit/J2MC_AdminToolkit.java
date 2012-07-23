package to.joe.j2mc.admintoolkit;

import java.util.ArrayList;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import to.joe.j2mc.admintoolkit.command.*;
import to.joe.j2mc.core.J2MC_Manager;

public class J2MC_AdminToolkit extends JavaPlugin implements Listener {

    private static ArrayList<String> iAreGodz;

    @Override
    public void onDisable() {
        this.getLogger().info("Admin Toolkit module disabled");
    }

    @Override
    public void onEnable() {
        J2MC_AdminToolkit.iAreGodz = new ArrayList<String>();
        this.getCommand("hat").setExecutor(new HatCommand(this));
        this.getCommand("getgroup").setExecutor(new GetGroupCommand(this));
        this.getCommand("whereis").setExecutor(new WhereIsPlayerCommand(this));
        this.getCommand("a").setExecutor(new AdminChatCommand(this));
        this.getCommand("g").setExecutor(new AdminGlobalChatCommand(this));
        this.getCommand("time").setExecutor(new TimeCommand(this));
        this.getCommand("mob").setExecutor(new MobCommand(this));
        this.getCommand("coo").setExecutor(new CooCommand(this));
        this.getCommand("getflags").setExecutor(new GetFlagsCommand(this));
        this.getCommand("storm").setExecutor(new StormCommand(this));
        this.getCommand("whois").setExecutor(new WhoIsCommand(this));
        this.getCommand("gm").setExecutor(new GameModeToggleCommand(this));
        this.getCommand("kibbles").setExecutor(new KibblesCommand(this));
        this.getCommand("bits").setExecutor(new BitsCommand(this));
        this.getCommand("invsee").setExecutor(new InventoryInspectionCommand(this));
        this.getCommand("thor").setExecutor(new ThorCommand(this));

        this.getLogger().info("Admin Toolkit module enabled");
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (J2MC_Manager.getPermissions().hasFlag(player.getName(), 'k')) {
                event.setCancelled(true);
            }
        }
    }

    public static void giveGodlyPowers(String name) {
        J2MC_AdminToolkit.iAreGodz.add(name.toLowerCase());
    }

    public static void removeGodlyPowers(String name) {
        J2MC_AdminToolkit.iAreGodz.remove(name.toLowerCase());
    }

    public static boolean isAGod(Player player) {
        return J2MC_AdminToolkit.iAreGodz.contains(player.getName().toLowerCase());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getItemInHand();
        final boolean weather = player.getWorld().isThundering();

        if (itemInHand.getTypeId() == 258 && isAGod(player)) {
            if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                player.getWorld().strikeLightningEffect(event.getClickedBlock().getLocation());
                player.getWorld().setStorm(weather);
            }
            if (event.getAction().equals(Action.LEFT_CLICK_AIR)) {
                final Block target = player.getTargetBlock(null, 50);
                if (target != null) {
                    player.getWorld().strikeLightningEffect(target.getLocation());
                    player.getWorld().setStorm(weather);
                }
            }
        }

    }

}
