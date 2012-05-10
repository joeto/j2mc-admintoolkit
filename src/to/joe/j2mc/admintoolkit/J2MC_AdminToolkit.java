package to.joe.j2mc.admintoolkit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

import to.joe.j2mc.admintoolkit.command.*;
import to.joe.j2mc.core.J2MC_Manager;

public class J2MC_AdminToolkit extends JavaPlugin implements Listener{

    @Override
    public void onDisable() {
        this.getLogger().info("Admin Toolkit module disabled");
    }

    @Override
    public void onEnable() {
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
    
}
