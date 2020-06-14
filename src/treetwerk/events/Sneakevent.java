package treetwerk.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.material.Sapling;

public class Sneakevent implements Listener {

	@EventHandler
	public void PlayerEvent(PlayerToggleSneakEvent e) 
	{
		getNearbySaplings(e.getPlayer());
	}
	
    private void getNearbySaplings(Player player) 
    {
        int range = 5;
        for (int x = player.getLocation().getBlockX() - range ; x <= player.getLocation().getBlockX() + range; x++) 
        {
            for (int y = player.getLocation().getBlockY() - range ; y <= player.getLocation().getBlockY() + range; y++)
            {
                for (int z = player.getLocation().getBlockZ() - range ; z <= player.getLocation().getBlockZ() + range; z++)
                {
                    Block block = player.getWorld().getBlockAt(x, y, z);
                    if (Tag.SAPLINGS.isTagged(block.getType())) 
                    {
                        TreeType type = null;
                        if (block.getType().toString().equalsIgnoreCase("OAK_SAPLING"))
                            type = TreeType.TREE;
                        block.setType(Material.AIR);
                        block.getWorld().generateTree(block.getLocation(), type);
                    }
                }
            }
        }

    }
}