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

public class GetGroupCommand extends MasterCommand {

    public GetGroupCommand(J2MC_AdminToolkit adminToolKit) {
        super(adminToolKit);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /getgroup <playername>");
            return;
        }
        ResultSet rs = null;
        try {
            final PreparedStatement ps = J2MC_Manager.getMySQL().getFreshPreparedStatementHotFromTheOven("SELECT `group` from users WHERE name=?");
            ps.setString(1, args[0]);
            rs = ps.executeQuery();
        } catch (final SQLException e) {
            return;
        } catch (final ClassNotFoundException e) {
            return;
        }
        String message = null;
        try {
            if (rs.next()) {
                message = "Player " + args[0] + ": " + rs.getString(1);
            }
        } catch (final SQLException e) {
            this.plugin.getLogger().warning("Unable to load user/group from MySQL. Oh hell!");
            this.plugin.getLogger().log(Level.SEVERE, "SQL Exception:", e);
        }
        sender.sendMessage(ChatColor.RED + message);
    }

}
