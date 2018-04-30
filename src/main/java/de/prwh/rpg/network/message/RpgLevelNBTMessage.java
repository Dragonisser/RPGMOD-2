package de.prwh.rpg.network.message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.level.ILevel;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * This message is sent to the client giving it the wanted bytes
 */
public class RpgLevelNBTMessage implements IMessage {

	private ILevel level;

	public RpgLevelNBTMessage() {
	}

	public RpgLevelNBTMessage(ILevel level) {
		this.level = level;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);

		try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes); ObjectInputStream ois = new ObjectInputStream(bis)) {
			this.level = (ILevel) ois.readObject();
		} catch (EOFException e) {
			RPGMain.getLogger().error("Couldnt sync level: EOFException", e);
		} catch (IOException e) {
			RPGMain.getLogger().error("Couldnt sync level: IOException", e);
		} catch (ClassNotFoundException e) {
			RPGMain.getLogger().error("Couldnt sync level: ClassNotFoundException", e);
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (this.level != null) {
			try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(bos)) {
				oos.writeObject(this.level);
				buf.writeBytes(bos.toByteArray());
			} catch (IOException e) {
				RPGMain.getLogger().error("Couldnt sync level with client", e);
			}
		}
	}

	public void setValue(ILevel level) {
		this.level = level;
	}

	public ILevel getValue() {
		return this.level;
	}
}
