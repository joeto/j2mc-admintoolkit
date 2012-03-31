package to.joe.j2mc.admintoolkit;

import org.bukkit.plugin.java.JavaPlugin;

import to.joe.j2mc.admintoolkit.command.*;

public class J2MC_AdminToolkit extends JavaPlugin {

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

        this.getLogger().info("Admin Toolkit module enabled");
    }
}
