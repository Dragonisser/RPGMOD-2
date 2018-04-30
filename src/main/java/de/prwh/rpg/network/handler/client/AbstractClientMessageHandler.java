package de.prwh.rpg.network.handler.client;

import de.prwh.rpg.network.handler.AbstractMessageHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * 
 * Handler for messages sent to the CLIENT Only allows implementation of
 * {@link AbstractMessageHandler#handleClientMessage handleClientMessage}
 * 
 */
public abstract class AbstractClientMessageHandler<T extends IMessage> extends AbstractMessageHandler<T> {
	// implementing a final version of the server message handler both prevents
	// it from
	// appearing automatically and prevents us from ever accidentally overriding
	// it
	public final IMessage handleServerMessage(EntityPlayer player, T message, MessageContext ctx) {
		return null;
	}
}
