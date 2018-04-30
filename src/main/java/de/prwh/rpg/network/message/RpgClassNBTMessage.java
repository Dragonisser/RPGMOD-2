package de.prwh.rpg.network.message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.player.rpgClass.IRpgClass;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * This message is sent to the client giving it the wanted bytes
 */
public class RpgClassNBTMessage implements IMessage {

	private IRpgClass rpgClass;

	public RpgClassNBTMessage() {
	}

	public RpgClassNBTMessage(IRpgClass rpgClass) {
		this.rpgClass = rpgClass;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);

		try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes); ObjectInputStream ois = new ObjectInputStream(bis)) {
			this.rpgClass = (IRpgClass) ois.readObject();
		} catch (EOFException e) {
			RPGMain.getLogger().error("Couldnt sync Rpgclass: EOFException", e);
		} catch (IOException e) {
			RPGMain.getLogger().error("Couldnt sync Rpgclass: IOException", e);
		} catch (ClassNotFoundException e) {
			RPGMain.getLogger().error("Couldnt sync Rpgclass: ClassNotFoundException", e);
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (this.rpgClass != null) {
			try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(bos)) {
				oos.writeObject(this.rpgClass);
				buf.writeBytes(bos.toByteArray());
			} catch (IOException e) {
				RPGMain.getLogger().error("Couldnt sync Rpgclass with client", e);
			}
		}
	}

	public void setValue(IRpgClass rpgClass) {
		this.rpgClass = rpgClass;
	}

	public IRpgClass getValue() {
		return this.rpgClass;
	}
}
