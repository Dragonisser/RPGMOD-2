package de.prwh.rpg.network.message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.player.rpgRace.IRpgRace;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * This message is sent to the client giving it the wanted bytes
 */
public class RpgRaceNBTMessage implements IMessage {

	private IRpgRace rpgRace;

	public RpgRaceNBTMessage() {
	}

	public RpgRaceNBTMessage(IRpgRace rpgRace) {
		this.rpgRace = rpgRace;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);

		try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bis)) {
			this.rpgRace = (IRpgRace) ois.readObject();
		} catch (EOFException e) {
			RPGMain.getLogger().error("Couldnt sync Rpgrace: EOFException", e);
		} catch (IOException e) {
			RPGMain.getLogger().error("Couldnt sync Rpgrace: IOException", e);
		} catch (ClassNotFoundException e) {
			RPGMain.getLogger().error("Couldnt sync Rpgrace: ClassNotFoundException", e);
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (this.rpgRace != null) {
			try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(bos)) {
				oos.writeObject(this.rpgRace);
				buf.writeBytes(bos.toByteArray());
			} catch (IOException e) {
				RPGMain.getLogger().error("Couldnt sync Rpgclass with client", e);
			}
		}
	}

	public void setValue(IRpgRace rpgRace) {
		this.rpgRace = rpgRace;
	}

	public IRpgRace getValue() {
		return this.rpgRace;
	}
}
