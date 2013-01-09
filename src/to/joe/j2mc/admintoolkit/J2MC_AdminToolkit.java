package to.joe.j2mc.admintoolkit;

import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import to.joe.j2mc.admintoolkit.command.AdminChatCommand;
import to.joe.j2mc.admintoolkit.command.AdminGlobalChatCommand;
import to.joe.j2mc.admintoolkit.command.CooCommand;
import to.joe.j2mc.admintoolkit.command.GameModeToggleCommand;
import to.joe.j2mc.admintoolkit.command.HatCommand;
import to.joe.j2mc.admintoolkit.command.InventoryInspectionCommand;
import to.joe.j2mc.admintoolkit.command.KickaxeCommand;
import to.joe.j2mc.admintoolkit.command.MobCommand;
import to.joe.j2mc.admintoolkit.command.StormCommand;
import to.joe.j2mc.admintoolkit.command.ThorCommand;
import to.joe.j2mc.admintoolkit.command.TimeCommand;
import to.joe.j2mc.admintoolkit.command.WhoIsCommand;
import to.joe.j2mc.core.J2MC_Manager;

public class J2MC_AdminToolkit extends JavaPlugin implements Listener {

    public HashSet<String> thor;
    public static final String KICKAXE_NAME = ChatColor.LIGHT_PURPLE + "KickAxe";
    public static final String[] KICKAXE_LORE = new String[] { "This special axe will kick players", "that are hit with it.", "Use it carefully." };

    @Override
    public void onDisable() {
        this.getLogger().info("Admin Toolkit module disabled");
    }

    @Override
    public void onEnable() {
        this.thor = new HashSet<String>();
        this.getCommand("hat").setExecutor(new HatCommand(this));
        this.getCommand("a").setExecutor(new AdminChatCommand(this));
        this.getCommand("g").setExecutor(new AdminGlobalChatCommand(this));
        this.getCommand("time").setExecutor(new TimeCommand(this));
        this.getCommand("mob").setExecutor(new MobCommand(this));
        this.getCommand("coo").setExecutor(new CooCommand(this));
        this.getCommand("storm").setExecutor(new StormCommand(this));
        this.getCommand("whois").setExecutor(new WhoIsCommand(this));
        this.getCommand("gm").setExecutor(new GameModeToggleCommand(this));
        this.getCommand("invsee").setExecutor(new InventoryInspectionCommand(this));
        this.getCommand("thor").setExecutor(new ThorCommand(this));
        this.getCommand("kickaxe").setExecutor(new KickaxeCommand(this));

        this.getServer().getPluginManager().registerEvents(this, this);

        this.getLogger().info("Admin Toolkit module enabled");
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();
            if (J2MC_Manager.getPermissions().hasFlag(player.getName(), 'k')) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player kicked = (Player) event.getEntity();
            Player player = (Player) event.getDamager();
            ItemStack itemInHand = player.getItemInHand();

            if (itemInHand != null && itemInHand.getType() == Material.GOLD_AXE && player.hasPermission("j2mc.admintoolkit.kickaxe") && itemInHand.getItemMeta().getDisplayName().equals(J2MC_AdminToolkit.KICKAXE_NAME)) {
                event.setCancelled(true);
                kicked.kickPlayer("IN DA FACE");
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack itemInHand = player.getItemInHand();
        final boolean weather = player.getWorld().isThundering();

        if ((itemInHand.getTypeId() == 258) && this.thor.contains(player.getName().toLowerCase())) {
            if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                event.setCancelled(true);
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
