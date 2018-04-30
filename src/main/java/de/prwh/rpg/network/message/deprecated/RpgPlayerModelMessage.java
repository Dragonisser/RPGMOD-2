package de.prwh.rpg.network.message.deprecated;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.player.RpgPlayer;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * This message is sent to the client giving it the wanted bytes
 */
public class RpgPlayerModelMessage implements IMessage {

	private RpgPlayer player;

	public RpgPlayerModelMessage() {

	}

	public RpgPlayerModelMessage(RpgPlayer player) {
		this.player = player;
	}

	public RpgPlayer getRpgPlayer() {
		return player;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);

		try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes); ObjectInputStream ois = new ObjectInputStream(bis)) {
			this.player = (RpgPlayer) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			RPGMain.getLogger().error("Couldnt sync player", e);
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(bos)) {
			oos.writeObject(player);
			buf.writeBytes(bos.toByteArray());
		} catch (IOException e) {
			RPGMain.getLogger().error("Couldnt sync player with client", e);
		}
	}
}
