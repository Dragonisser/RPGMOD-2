package de.prwh.rpg.network.handler.client;

import de.prwh.rpg.network.message.deprecated.RpgPlayerModelMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class RpgPlayerModelMessageHandler extends AbstractClientMessageHandler<RpgPlayerModelMessage> {
	@Override
	public IMessage handleClientMessage(EntityPlayer player, RpgPlayerModelMessage message, MessageContext ctx) {
		// RpgPlayer rpgPlayer = new RpgPlayer(player);
		// rpgPlayer = (RpgPlayer) message.getRpgPlayer();
		//
		// RPGMain.getLogger().debug("Synced player on client");
		return null;

		// TODO Change to only sync the nbttag and not the whole rpgplayer(new
		// sync class)
	}
}