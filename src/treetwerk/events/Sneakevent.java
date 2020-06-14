package treetwerk.events;

import java.util.HashMap;

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

public class Sneakevent implements Listener 
{
	
	public HashMap<Block, Integer> TwerkCount = new HashMap<Block, Integer>();

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
                    	if (!TwerkCount.containsKey(block))
                    		TwerkCount.put(block, 0);
                    	
                    	int newtwerk = TwerkCount.get(block) + 1; 
                    	if (newtwerk >= 5)
                    	{
                            TreeType type = null;
                            
                            if (block.getType().toString().equalsIgnoreCase("OAK_SAPLING"))
                                type = TreeType.TREE;

                            if (block.getType().toString().equalsIgnoreCase("DARK_OAK_SAPLING"))
                                type = TreeType.DARK_OAK;
                            
                            block.setType(Material.AIR);
                            block.getWorld().generateTree(block.getLocation(), type); 
                            TwerkCount.remove(block);
                            if(block.getType().equals(Material.AIR))
                            {
                            	block.setType(Material.OAK_SAPLING);
                            }
                    	}
                    	else
                    		TwerkCount.put(block, newtwerk);
                    	

                    }
                }
            }
        }

    }
}