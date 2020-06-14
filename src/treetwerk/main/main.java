package treetwerk.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;


public class main extends JavaPlugin {

    private static main instance;

    public static main getInstance()
    {
        return instance;
    }    
    
    public String getDataFold() 
    {
    	return getDataFolder().toString();
    }
    
    private void registerCommands(String[] cmds, CommandExecutor cmdExecutor)
    {
        for (String cmd : cmds)
        {
            getCommand(cmd).setExecutor(cmdExecutor);
        }
    }

    @Override
    public void onLoad() 
    {
        instance = this;
   
    } 

    public void onEnable()
    {        
        Bukkit.getPluginManager().registerEvents(new treetwerk.events(), this);
       
        
        getLogger().info(ChatColor.DARK_PURPLE + "opMenu'nün komutlarý ve eventleri kaydedildi!");

        getLogger().info(ChatColor.DARK_PURPLE + "opMenu plugini aktifleþtirildi!");
    }

    public void onDisable()
    {    	
        getLogger().info(ChatColor.DARK_PURPLE + "opMenu plugini kapatýldý!");
    }
	
}
