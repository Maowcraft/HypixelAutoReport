package maowcraft.hypixelautoreport;

import java.io.File;

import maowcraft.hypixelautoreport.utils.handlers.ChatHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name=HypixelAutoReport.MODNAME,modid=HypixelAutoReport.MODID,version=HypixelAutoReport.VERSION,acceptedMinecraftVersions=HypixelAutoReport.MCVERSION,clientSideOnly=true)
public class HypixelAutoReport {
	public static final String MODNAME = "Hypixel Auto-Report";
	public static final String MODID = "hypixelautoreport";
	public static final String VERSION = "1.1.0";
	public static final String MCVERSION = "1.12.2"; 
	
	public static String path;
	
	@Instance("hypixelautoreport")
	public static HypixelAutoReport instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		String absolutePath = event.getSourceFile().getAbsolutePath();
		path = event.getSourceFile().getAbsolutePath().substring(0,absolutePath.lastIndexOf(File.separator));
		MinecraftForge.EVENT_BUS.register(new ChatHandler());
	}
}
