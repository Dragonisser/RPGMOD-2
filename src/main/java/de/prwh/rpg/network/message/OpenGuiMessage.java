package de.prwh.rpg.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * This message is sent to the client telling it to open a GUI
 */
public class OpenGuiMessage implements IMessage {
	// this will store the id of the gui to open
	private int guiId;

	// The basic, no-argument constructor MUST be included to use the new
	// automated handling
	public OpenGuiMessage() {
	}

	// if there are any class fields, be sure to provide a constructor that
	// allows
	// for them to be initialized, and use that constructor when sending the
	// packet
	public OpenGuiMessage(int guiId) {
		this.guiId = guiId;
	}
	
	public int getGuiId() {
		return guiId;
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		// basic Input/Output operations, very much like DataInputStream
		guiId = buffer.readInt();
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		// basic Input/Output operations, very much like DataOutputStream
		buffer.writeInt(guiId);
	}
}
