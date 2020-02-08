package maowcraft.hypixelautoreport.util.handlers;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import maowcraft.hypixelautoreport.HypixelAutoReport;
import maowcraft.hypixelautoreport.util.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class ChatHandler {
	public static final String flaggedMsg = "join my minecraft s3rver pls <3 | TheMcZone <dot> Net";
	
	// Check new chat messages, if chat message = spam ad, ignore and report the offending user.	
	@SubscribeEvent
	public void onChatMessage(ClientChatReceivedEvent event) {
		if (event.message.getUnformattedText().toString().endsWith(flaggedMsg) && ModConfig.config.get(ModConfig.CATEGORY_CLIENT, "Toggle mod", true).getBoolean() == true) {
			EntityPlayerSP currentPlayer = Minecraft.getMinecraft().thePlayer;
			if (ModConfig.config.get(ModConfig.CATEGORY_CLIENT, "Remove message", true).getBoolean() == true) {
				event.setCanceled(true);
				IChatComponent cancelMessage = new ChatComponentText(EnumChatFormatting.DARK_GRAY + "< Deleted Spam Message >");
				currentPlayer.addChatMessage(cancelMessage);
			}
			System.out.println("[HypixelAutoReport] Found offending string: " + event.message);
			String offendingUser = StringUtils.substringBetween(event.message.getUnformattedText(), "<", ">");
			System.out.println("[HypixelAutoReport] Offending user: " + offendingUser);
			currentPlayer.sendChatMessage("/chatreport " + offendingUser);
			if (ModConfig.config.get(ModConfig.CATEGORY_CLIENT, "Auto confirm", true).getBoolean() == true) {
				currentPlayer.sendChatMessage("/chatreport confirm");
			}
			currentPlayer.sendChatMessage("/ignore add " + offendingUser);
			System.out.println("[HypixelAutoReport] Finished process");
		}
	}
}
