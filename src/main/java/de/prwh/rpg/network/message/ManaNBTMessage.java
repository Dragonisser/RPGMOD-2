package de.prwh.rpg.network.message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.mana.IMana;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * This message is sent to the client giving it the wanted bytes
 */
public class ManaNBTMessage implements IMessage {

	private IMana mana;

	public ManaNBTMessage() {
	}

	public ManaNBTMessage(IMana mana) {
		this.mana = mana;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);

		try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes); ObjectInputStream ois = new ObjectInputStream(bis)) {
			this.mana = (IMana) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			RPGMain.getLogger().error("Couldnt sync mana", e);
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (this.mana != null) {
			try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(bos)) {
				oos.writeObject(this.mana);
				buf.writeBytes(bos.toByteArray());
			} catch (IOException e) {
				RPGMain.getLogger().error("Couldnt sync mana with client", e);
			}
		}
	}

	public void setValue(IMana mana) {
		this.mana = mana;
	}

	public IMana getValue() {
		return this.mana;
	}
}
