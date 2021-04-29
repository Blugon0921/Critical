package tk.blugon0921.critical;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public static String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "Critical" + ChatColor.GRAY + "] ";
    public static String enable = prefix + ChatColor.AQUA + "플러그인이 활성화 되었습니다";
    public static String disable = prefix + ChatColor.AQUA + "플러그인이 비활성화 되었습니다";


    @Override
    public void onEnable() {
        System.out.println(enable);
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(disable);
    }


    @EventHandler
    public void EntityDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            int i = (int) (Math.random() * 9) + 1;
            if (i < 2) {
                event.setDamage(event.getDamage() * 5);
                Location location = event.getEntity().getLocation();
                location.setY(location.getY() + 1);

                Particle.DustOptions orange = new Particle.DustOptions(Color.ORANGE, 1);
                Particle.DustOptions red = new Particle.DustOptions(Color.RED, 1);
                event.getEntity().getWorld().spawnParticle(Particle.REDSTONE, location, 100, 0.4, 0.4, 0.4, 0, orange, true);
                event.getEntity().getWorld().spawnParticle(Particle.REDSTONE, location, 100, 0.4, 0.4, 0.4, 0, red, true);
                ((Player)event.getDamager()).sendTitle("",ChatColor.RED+"Critical!",10,20,10);
            }
        } else if(event.getDamager() instanceof Projectile) {
            if (((Projectile) event.getDamager()).getShooter() instanceof Player) {
                int i = (int) (Math.random() * 9) + 1;
                if (i < 3) {
                    event.setDamage(event.getDamage() * 5);
                    Location location = event.getEntity().getLocation();
                    location.setY(location.getY() + 1);

                    Particle.DustOptions orange = new Particle.DustOptions(Color.ORANGE, 1);
                    Particle.DustOptions red = new Particle.DustOptions(Color.RED, 1);
                    event.getEntity().getWorld().spawnParticle(Particle.REDSTONE, location, 100, 0.4, 0.4, 0.4, 0, orange, true);
                    event.getEntity().getWorld().spawnParticle(Particle.REDSTONE, location, 100, 0.4, 0.4, 0.4, 0, red, true);
                    ((Player) ((Projectile) event.getDamager()).getShooter()).sendTitle("",ChatColor.RED+"Critical!",10,20,10);
                }
            }
        }
    }
}
