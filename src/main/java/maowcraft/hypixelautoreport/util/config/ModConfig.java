package maowcraft.hypixelautoreport.util.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ModConfig {
	public static final String CATEGORY_CLIENT = "client";
	public static Configuration config = null;
	
	public static void LoadConfigSettings(File configFile) {
		ReadConfigSettings(configFile, true);
	}
	
	public static void SaveConfigSettings() {
		ReadConfigSettings(null, false);
	}
	    
	private static void ReadConfigSettings(File configFile, boolean loadSettings) {
	    if(loadSettings) {
	    	config = new Configuration(configFile);
	        config.load();
	    }

	    Property toggleMod;
	    config.addCustomCategoryComment(CATEGORY_CLIENT, "Toggle the mod.");
	    toggleMod = config.get(CATEGORY_CLIENT, "Toggle mod", true);
	    toggleMod.comment = "Toggle the mod";	    
	    
	    Property removeMsg;
	    config.addCustomCategoryComment(CATEGORY_CLIENT, "Toggle the removal of a spam message.");
	    removeMsg = config.get(CATEGORY_CLIENT, "Remove message", true);
	    removeMsg.comment = "Toggle the removal of a spam message";
	    
	    Property autoConfirm;
	    config.addCustomCategoryComment(CATEGORY_CLIENT, "Toggle /chatreport confirm - Use this to avoid possible false-reports");
	    removeMsg = config.get(CATEGORY_CLIENT, "Auto confirm", true);
	    removeMsg.comment = "Toggle /chatreport confirm - Use this to avoid possible false-reports";
	      
	    config.save();
	}
}
