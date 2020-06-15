package treetwerk.main;

import java.util.HashMap;

public class ConfigFolder 
{
	public static HashMap<String, Integer> TwerkConfig = new HashMap<String, Integer>();

	public void ReadFile()
	{
				
        YamlManager yml = new YamlManager(main.getInstance().getDataFold(), "config.yml");

        if (yml.isEmpty()) 
        	WriteFile(yml);
        
        TwerkConfig.put("RequiredTwerkCount", yml.getConfiguration().getInt("config.RequiredTwerkCount"));
        
        
	}
	
	public void WriteFile(YamlManager yml)
	{

        yml.getConfiguration().set("config.RequiredTwerkCount", 10);

        yml.save(); yml.load();
	}
}
