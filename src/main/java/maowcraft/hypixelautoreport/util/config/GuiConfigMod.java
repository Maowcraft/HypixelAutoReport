package maowcraft.hypixelautoreport.util.config;

import maowcraft.hypixelautoreport.HypixelAutoReport;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class GuiConfigMod extends GuiConfig {
	public GuiConfigMod(GuiScreen parent) {
		super(parent, 
				new ConfigElement(
						ModConfig.config.getCategory(Configuration.CATEGORY_CLIENT))
							.getChildElements(), 
				HypixelAutoReport.MODID, 
				false, 
				false, 
				"Hypixel Auto-Report Configuration");
		titleLine2 = HypixelAutoReport.configFile.getAbsolutePath();
	}
}
