package maowcraft.hypixelautoreport.utils.handlers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import maowcraft.hypixelautoreport.HypixelAutoReport;
import maowcraft.hypixelautoreport.ModConfig;
import maowcraft.hypixelautoreport.utils.ChatFilter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid=HypixelAutoReport.MODID)
public class ChatHandler {
	private static String offendingUser;
	public static final String s = "TheMcZone";
	
	// This code is disgusting, but what can ya do?
	
	@SubscribeEvent
	public void onChatMsg(ClientChatReceivedEvent event) {
	World world = Minecraft.getMinecraft().world;
	for (String blockedString1 : ChatFilter.blockedStringsStage1) {
			if (event.getMessage().getUnformattedText().toLowerCase().contains(blockedString1.toLowerCase())) {
				for (String blockedString2 : ChatFilter.blockedStringsStage2) {
					if (event.getMessage().getUnformattedText().toLowerCase().contains(blockedString2.toLowerCase())) {
						for (String blockedString3 : ChatFilter.blockedStringsStage3) {
							if (event.getMessage().getUnformattedText().toLowerCase().contains(blockedString3.toLowerCase())) {
								setOffendingUser(StringUtils.substringBetween(event.getMessage().getUnformattedText(), "<", ">"));									EntityPlayerSP currentPlayer = Minecraft.getMinecraft().player;
								if (ModConfig.deleteMsgOnReceived == true) {
									if (world.isRemote) {
										event.setCanceled(true);
										ITextComponent cancelMessage = new TextComponentString(TextFormatting.DARK_GRAY + "< A message that contained one or more blocked words was deleted from your chat >");
										currentPlayer.sendMessage(cancelMessage);
										if (ModConfig.enableReport == false || ModConfig.enableIgnore == false) {
											break;
										}
									}
								}
								if (ModConfig.enableReport == true) {
									currentPlayer.sendChatMessage("/chatreport " + offendingUser);
									currentPlayer.sendChatMessage("/chatreport confirm");
									if (ModConfig.enableIgnore == false) {
										break;
									}
								}
								if (ModConfig.enableIgnore == true) {
									currentPlayer.sendChatMessage("/ignore add " + offendingUser);
									break;
								}
								break;
							}
						}
						break;
					}
				}
				break;
			}
		}
	}
	
	private void setOffendingUser(String offendingUser) {
		this.offendingUser = offendingUser;
	}
}
