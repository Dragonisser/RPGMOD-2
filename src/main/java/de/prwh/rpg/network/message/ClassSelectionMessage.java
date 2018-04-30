package de.prwh.rpg.network.message;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * A simple message sending the class selection to the server.
 */
public class ClassSelectionMessage implements IMessage {

	private String classname;

	/**
	 * Only to be used by Forge message framework
	 */
	public ClassSelectionMessage() {
	}
	public ClassSelectionMessage(String classname) {
		this.classname = classname;
	}

	public String getClassname() {
		return classname;
	}
	
	@Override
	public void fromBytes(ByteBuf buffer) {
		classname = buffer.toString(Charset.forName("UTF-8"));
	}
	@Override
	public void toBytes(ByteBuf buffer) {
		buffer.writeBytes(classname.getBytes(Charset.forName("UTF-8")));
	}
}
