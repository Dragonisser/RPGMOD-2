package de.prwh.rpg.network.message;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * A simple message sending the class selection to the server.
 */
public class RaceSelectionMessage implements IMessage {

	private String racename;

	/**
	 * Only to be used by Forge message framework
	 */
	public RaceSelectionMessage() {
	}
	public RaceSelectionMessage(String racename) {
		this.racename = racename;
	}

	public String getRacename() {
		return racename;
	}
	
	@Override
	public void fromBytes(ByteBuf buffer) {
		racename = buffer.toString(Charset.forName("UTF-8"));
	}
	@Override
	public void toBytes(ByteBuf buffer) {
		buffer.writeBytes(racename.getBytes(Charset.forName("UTF-8")));
	}
}
