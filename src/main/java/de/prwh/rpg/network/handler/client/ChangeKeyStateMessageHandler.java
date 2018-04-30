package de.prwh.rpg.network.handler.client;

import de.prwh.rpg.network.message.ChangeKeyStateMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ChangeKeyStateMessageHandler extends AbstractClientMessageHandler<ChangeKeyStateMessage> {
	@Override
	public IMessage handleClientMessage(EntityPlayer player, ChangeKeyStateMessage message, MessageContext ctx) {

		KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSprint.getKeyCode(), message.getKeyState());
		return null;
	}
}