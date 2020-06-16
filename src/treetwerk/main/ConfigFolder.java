package treetwerk.main;

import java.util.HashMap;

public class ConfigFolder 
{
	public static HashMap<String, Integer> TwerkConfig = new HashMap<String, Integer>();
	public static HashMap<String, Boolean> TwerkConfig2 = new HashMap<String, Boolean>();

	public void ReadFile()
	{
				
        YamlManager yml = new YamlManager(main.getInstance().getDataFold(), "config.yml");

        if (yml.isEmpty()) 
        	WriteFile(yml);
        
        TwerkConfig.put("RequiredTwerkCount", yml.getConfiguration().getInt("config.RequiredTwerkCount"));
        TwerkConfig.put("RangeForShifting", yml.getConfiguration().getInt("config.RangeForShifting"));
        TwerkConfig2.put("GrowingParticle", yml.getConfiguration().getBoolean("config.GrowingParticle"));

        TwerkConfig2.put("OakTree", yml.getConfiguration().getBoolean("Trees.OakTree"));
        TwerkConfig2.put("SpruceTree", yml.getConfiguration().getBoolean("Trees.SpruceTree"));
        TwerkConfig2.put("BirchTree", yml.getConfiguration().getBoolean("Trees.BirchTree"));
        TwerkConfig2.put("AcaciaTree", yml.getConfiguration().getBoolean("Trees.AcaciaTree"));
        TwerkConfig2.put("JungleTree", yml.getConfiguration().getBoolean("Trees.JungleTree"));
        TwerkConfig2.put("BigOakTree", yml.getConfiguration().getBoolean("Trees.BigOakTree"));
        TwerkConfig2.put("DarkOakTree", yml.getConfiguration().getBoolean("Trees.DarkOakTree"));
        TwerkConfig2.put("RedMushroomTree", yml.getConfiguration().getBoolean("Trees.RedMushroomTree"));
        TwerkConfig2.put("BrownMushroomTree", yml.getConfiguration().getBoolean("Trees.BrownMushroomTree"));

	}
	
	public void WriteFile(YamlManager yml)
	{
		
        yml.getConfiguration().set("config.RequiredTwerkCount", 10);
        yml.getConfiguration().set("config.RangeForShifting", 5);
        yml.getConfiguration().set("config.GrowingParticle", true);

        yml.getConfiguration().set("Trees.OakTree", true);
        yml.getConfiguration().set("Trees.SpruceTree", true);
        yml.getConfiguration().set("Trees.BirchTree", true);
        yml.getConfiguration().set("Trees.AcaciaTree", true);
        yml.getConfiguration().set("Trees.JungleTree", true);
        yml.getConfiguration().set("Trees.BigOakTree", false);
        yml.getConfiguration().set("Trees.DarkOakTree", false);
        yml.getConfiguration().set("Trees.RedMushroomTree", false);
        yml.getConfiguration().set("Trees.BrownMushroomTree", false);


        yml.save(); yml.load();
	}
	
	public void ClearHashMap()
	{
		TwerkConfig.clear();
		TwerkConfig2.clear();
	}
}
