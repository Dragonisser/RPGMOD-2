package de.prwh.rpg.network.message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.stamina.IStamina;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * This message is sent to the client giving it the wanted bytes
 */
public class StaminaNBTMessage implements IMessage {

	private IStamina stamina;

	public StaminaNBTMessage() {
	}

	public StaminaNBTMessage(IStamina stamina) {
		this.stamina = stamina;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);

		try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes); ObjectInputStream ois = new ObjectInputStream(bis)) {
			this.stamina = (IStamina) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			RPGMain.getLogger().error("Couldnt sync stamina", e);
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (this.stamina != null) {
			try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(bos)) {
				oos.writeObject(this.stamina);
				buf.writeBytes(bos.toByteArray());
			} catch (IOException e) {
				RPGMain.getLogger().error("Couldnt sync stamina with client", e);
			}
		}
	}

	public void setValue(IStamina stamina) {
		this.stamina = stamina;
	}

	public IStamina getValue() {
		return this.stamina;
	}
}
