package de.prwh.rpg.network.handler.client;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.network.message.OpenGuiMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class OpenGuiMessageHandler extends AbstractClientMessageHandler<OpenGuiMessage> {
	@Override
	public IMessage handleClientMessage(EntityPlayer player, OpenGuiMessage message, MessageContext ctx) {
		// because we sent the gui's id with the packet, we can handle all
		// cases with one line:
		player.openGui(RPGMain.instance, message.getGuiId(), player.world, (int) player.posX, (int) player.posY, (int) player.posZ);
		return null;
	}
}