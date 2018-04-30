package de.prwh.rpg.network.handler.server;

import de.prwh.rpg.network.handler.AbstractMessageHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Handler for messages sent to the SERVER Only allows implementation of
 * {@link AbstractMessageHandler#handleServerMessage handleServerMessage}
 */
public abstract class AbstractServerMessageHandler<T extends IMessage> extends AbstractMessageHandler<T> {

	@Override
	public IMessage handleClientMessage(EntityPlayer player, T message, MessageContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}
	// implementing a final version of the client message handler both prevents
	// it from
	// appearing automatically and prevents us from ever accidentally overriding
	// it

}