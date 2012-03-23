package to.joe.j2mc.admintoolkit.command;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.admintoolkit.J2MC_AdminToolkit;
import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;
import to.joe.j2mc.core.exceptions.BadPlayerMatchException;

public class GetGroupCommand extends MasterCommand {

    public GetGroupCommand(J2MC_AdminToolkit adminToolKit) {
        super(adminToolKit);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (sender.hasPermission(J2MC_AdminToolkit.adminPerm)) {
            if (args.length != 1) {
                sender.sendMessage(ChatColor.RED + "/getgroup playername");
                return;
            }
            Player target = null;
            try {
                target = J2MC_Manager.getVisibility().getPlayer(args[0], null);
            } catch (final BadPlayerMatchException e) {
                sender.sendMessage(ChatColor.RED + e.getMessage());
                return;
            }
            ResultSet rs = null;
            try {
                final PreparedStatement ps = J2MC_Manager.getMySQL().getFreshPreparedStatementHotFromTheOven("SELECT `group` from users WHERE name=?");
                ps.setString(1, target.getName());
                rs = ps.executeQuery();
            } catch (final SQLException e) {
                return;
            } catch (final ClassNotFoundException e) {
                return;
            }
            String message = null;
            try {
                if (rs.next()) {
                    try {
                        message = "Player " + target.getName() + ": " + rs.getString(1);
                    } catch (final SQLException e) {
                        this.plugin.getLogger().warning("Unable to load user/group from MySQL. Oh hell!");
                        this.plugin.getLogger().log(Level.SEVERE, "SQL Exception:", e);
                    }
                }
            } catch (final SQLException e) {
                this.plugin.getLogger().warning("Unable to load user/group from MySQL. Oh hell!");
                this.plugin.getLogger().log(Level.SEVERE, "SQL Exception:", e);
            }
            sender.sendMessage(ChatColor.RED + message);
            this.plugin.getLogger().info(player.getName() + " looked up " + target.getName());
        }
    }

}
