package maowcraft.hypixelautoreport;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid=HypixelAutoReport.MODID)
@Config.LangKey("hypixelautoreport.config.title")
public class ModConfig {
	@Config.Comment("Delete the flagged message when it is received")
	public static boolean deleteMsgOnReceived = true;
	
	@Config.Comment("Enable the auto-report offending user feature")
	public static boolean enableReport = false;
	
	@Config.Comment("Enable the auto-ignore offending user feature")
	public static boolean enableIgnore = true;
	
	@EventBusSubscriber(modid=HypixelAutoReport.MODID)
	private static class EventHandler {
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(HypixelAutoReport.MODID)) {
				ConfigManager.sync(HypixelAutoReport.MODID, Config.Type.INSTANCE);
			}
		}
	}	
}
