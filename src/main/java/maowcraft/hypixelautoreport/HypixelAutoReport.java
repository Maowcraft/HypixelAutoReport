package maowcraft.hypixelautoreport;

import java.io.File;

import maowcraft.hypixelautoreport.util.config.ModConfig;
import maowcraft.hypixelautoreport.util.handlers.ChatHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=HypixelAutoReport.MODID,
	name=HypixelAutoReport.MODNAME,
	version=HypixelAutoReport.VERSION,
	acceptedMinecraftVersions=HypixelAutoReport.MCVERSION,
	guiFactory = "maowcraft."+HypixelAutoReport.MODID+".util.config.GuiConfigFactory",
	clientSideOnly = true
	)
public class HypixelAutoReport {
	public static final String MODID = "hypixelautoreport";
	public static final String MODNAME = "Hypixel Auto-Report";
	public static final String VERSION = "1.0.0";
	public static final String MCVERSION = "1.8.8";
	
	public static File configFile;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) { 
		MinecraftForge.EVENT_BUS.register(new ChatHandler());
		
		configFile = event.getSuggestedConfigurationFile();
		ModConfig.LoadConfigSettings(configFile);
	}
	@EventHandler
	public static void init(FMLInitializationEvent event) {}
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {}
}
