package treetwerk.events;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class Sneakevent implements Listener 
{
	
	public HashMap<Block, Integer> TwerkCount = new HashMap<Block, Integer>();

	@EventHandler
	public void PlayerEvent(PlayerToggleSneakEvent e) 
	{
		ArrayList<Block> blocks = getNearbySaplings(e.getPlayer());
		
		for (Block block : blocks)
		{
			TreeType type = CheckTreeType(block);	 
			GrowTree(block, type);
		}

	}
	
    private ArrayList<Block> getNearbySaplings(Player player) 
    {
    	ArrayList<Block> Br = new ArrayList< Block>();
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
                    	Br.add(block);     
                    }
                }
            }
        }
        return Br;

    }
    
    private TreeType CheckTreeType(Block block) 
    {
        TreeType type = null;
        
        if (block.getType().equals(Material.OAK_SAPLING))
            type = TreeType.TREE;
        
        if (block.getType().equals(Material.SPRUCE_SAPLING))
        	type = TreeType.REDWOOD;
        
        
        if (block.getType().equals(Material.JUNGLE_SAPLING))
            type = TreeType.SMALL_JUNGLE;
        
        
        if (block.getType().equals(Material.BIRCH_SAPLING))
            type = TreeType.BIRCH;
        
        
        if (block.getType().equals(Material.ACACIA_SAPLING))
            type = TreeType.ACACIA;
        
        return type;
    }
    
    private void GrowTree(Block block, TreeType type)
    {
    	if (!TwerkCount.containsKey(block))
    		TwerkCount.put(block, 0);
    	
    	int newtwerk = TwerkCount.get(block) + 1; 
    	if (newtwerk >= 5)
    	{
            block.setType(Material.AIR);
            block.getWorld().generateTree(block.getLocation(), type); 
            TwerkCount.remove(block);

            if(block.getType().equals(Material.AIR))
            {
            	if (type.equals(TreeType.TREE))
            		block.setType(Material.OAK_SAPLING);
            		
            		
            	if (type.equals(TreeType.ACACIA))
            		block.setType(Material.ACACIA_SAPLING);
            	
            	
            	else if (type.equals(TreeType.JUNGLE))
            		block.setType(Material.JUNGLE_SAPLING);
            	
            	
            	if(type.equals(TreeType.BIRCH))
            		block.setType(Material.BIRCH_SAPLING);
            }
    	}
    	else
    		TwerkCount.put(block, newtwerk);    	
    }
}