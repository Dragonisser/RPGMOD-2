package de.prwh.rpg.network.handler.bidirectional;

import de.prwh.rpg.network.handler.AbstractMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

/**
 * For messages which require different handling on each Side; if the message is
 * handled identically regardless of Side, it is better to implement
 * {@link IMessageHandler} directly and register using
 * {@link PacketDispatcher#registerBiMessage}
 */
public abstract class RPGAbstractBiMessageHandler<T extends IMessage> extends AbstractMessageHandler<T> {

}
