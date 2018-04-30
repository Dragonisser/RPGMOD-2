package de.prwh.rpg.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * A simple message sending the class selection to the server.
 */
public class ChangeKeyStateMessage implements IMessage {

	private boolean state;

	/**
	 * Only to be used by Forge message framework
	 */
	public ChangeKeyStateMessage() {
	}

	public ChangeKeyStateMessage(boolean state) {
		this.state = state;
	}

	public boolean getKeyState() {
		return state;
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		state = buffer.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		buffer.writeBoolean(state);
	}
}
