package de.prwh.rpg.network.message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.health.IHealth;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * This message is sent to the client giving it the wanted bytes
 */
public class HealthNBTMessage implements IMessage {

	private IHealth health;

	public HealthNBTMessage() {
	}

	public HealthNBTMessage(IHealth health) {
		this.health = health;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);

		try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes); ObjectInputStream ois = new ObjectInputStream(bis)) {
			this.health = (IHealth) ois.readObject();
		} catch (EOFException e) {
			RPGMain.getLogger().error("Couldnt sync health: EOFException", e);
		} catch (IOException e) {
			RPGMain.getLogger().error("Couldnt sync health: IOException", e);
		} catch (ClassNotFoundException e) {
			RPGMain.getLogger().error("Couldnt sync health: ClassNotFoundException", e);
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (this.health != null) {
			try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(bos)) {
				oos.writeObject(this.health);
				buf.writeBytes(bos.toByteArray());
			} catch (IOException e) {
				RPGMain.getLogger().error("Couldnt sync health with client", e);
			}
		}
	}

	public void setValue(IHealth health) {
		this.health = health;
	}

	public IHealth getValue() {
		return this.health;
	}
}
