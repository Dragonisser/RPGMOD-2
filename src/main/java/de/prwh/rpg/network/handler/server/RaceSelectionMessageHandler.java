package de.prwh.rpg.network.handler.server;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.network.message.RaceSelectionMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class RaceSelectionMessageHandler extends AbstractServerMessageHandler<RaceSelectionMessage> {

	@Override
	public IMessage handleServerMessage(EntityPlayer player, RaceSelectionMessage message, MessageContext ctx) {

		// RpgPlayer rplayer = RpgPlayer.get(player);
		// rplayer.setRpgRace(RaceFactory.getInstance().createClass(message.getRacename()));

		/*
		 * TODO Build a working framework around the changes and synchronization
		 * 
		 * {@link RpgPlayer#syncWithClient()}
		 */
		// rplayer.syncWithClient();
		//
		// RaceSelectEvent event = new RaceSelectEvent(rplayer,
		// rplayer.getRpgRace());
		// if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) {
		// return null;
		// }

		RPGMain.getLogger().info("Race successfully chosen");
		return null;
	}
}
