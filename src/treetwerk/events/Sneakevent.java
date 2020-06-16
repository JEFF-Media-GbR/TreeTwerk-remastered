package treetwerk.events;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Tag;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import treetwerk.main.ConfigFolder;

public class Sneakevent implements Listener 
{
	public HashMap<Block, Integer> TwerkCount = new HashMap<Block, Integer>();

	@EventHandler
	public void PlayerEvent(PlayerToggleSneakEvent e) 
	{
		boolean configForTree = false;
		if (!(e.isSneaking()))
			return;
		
		ArrayList<Block> blocks = getNearbySaplings(e.getPlayer());
		
		for (Block block : blocks)
		{
			TreeType type = CheckTreeType(block);

			
	        if (block.getType().equals(Material.OAK_SAPLING))
	            configForTree = ConfigFolder.TwerkConfig2.get("OakTree");
	        
	        if (block.getType().equals(Material.SPRUCE_SAPLING))
	            configForTree = ConfigFolder.TwerkConfig2.get("SpruceTree");
	        
	        
	        if (block.getType().equals(Material.JUNGLE_SAPLING))
	            configForTree = ConfigFolder.TwerkConfig2.get("JungleTree");
	        
	        
	        if (block.getType().equals(Material.BIRCH_SAPLING))
	            configForTree = ConfigFolder.TwerkConfig2.get("BirchTree");
	                
	        if (block.getType().equals(Material.ACACIA_SAPLING))
	            configForTree = ConfigFolder.TwerkConfig2.get("AcaciaTree");
	        
	        if (block.getType().equals(Material.RED_MUSHROOM))
	            configForTree = ConfigFolder.TwerkConfig2.get("RedMushroomTree");
	        
	        	
	        if (block.getType().equals(Material.BROWN_MUSHROOM))
	            configForTree = ConfigFolder.TwerkConfig2.get("BrownMushroomTree");
			
			if (configForTree == true)
				GrowTree(block, type, e.getPlayer());
			
		}

	}
	
    private ArrayList<Block> getNearbySaplings(Player player) 
    {
    	ArrayList<Block> Br = new ArrayList< Block>();
    	int range = ConfigFolder.TwerkConfig.get("RangeForShifting");
        for (int x = player.getLocation().getBlockX() - range ; x <= player.getLocation().getBlockX() + range; x++) 
        {
            for (int y = player.getLocation().getBlockY() - range ; y <= player.getLocation().getBlockY() + range; y++)
            {
                for (int z = player.getLocation().getBlockZ() - range ; z <= player.getLocation().getBlockZ() + range; z++)
                {
                    Block block = player.getWorld().getBlockAt(x, y, z);
                    if (Tag.SAPLINGS.isTagged(block.getType()) || block.getType().equals(Material.BROWN_MUSHROOM) || block.getType().equals(Material.RED_MUSHROOM)) 
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
        
        if (block.getType().equals(Material.RED_MUSHROOM))
            type = TreeType.RED_MUSHROOM;
        
        	
        if (block.getType().equals(Material.BROWN_MUSHROOM))
            type = TreeType.BROWN_MUSHROOM;

        return type;
    }
    
    private void GrowTree(Block block, TreeType type, Player player)
    {
    	if (!TwerkCount.containsKey(block))
    		TwerkCount.put(block, 0);
    	
    	int newtwerk = TwerkCount.get(block) + 1; 
    	if (newtwerk >= ConfigFolder.TwerkConfig.get("RequiredTwerkCount"))
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
            	
            	if (type.equals(TreeType.BROWN_MUSHROOM));
        			block.setType(Material.BROWN_MUSHROOM);
        			
                if (type.equals(TreeType.RED_MUSHROOM));
            		block.setType(Material.RED_MUSHROOM);        			
            	
            }
    	}
    	else
    		TwerkCount.put(block, newtwerk); 
    	
    	if (ConfigFolder.TwerkConfig2.get("GrowingParticle").equals(true))
    		player.spawnParticle(Particle.SPELL, block.getLocation(), 20, 1.2D, 0D, 1.2D);
    	
    }
}