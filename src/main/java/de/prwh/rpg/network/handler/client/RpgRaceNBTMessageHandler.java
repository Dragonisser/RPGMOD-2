package de.prwh.rpg.network.handler.client;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.player.rpgRace.IRpgRace;
import de.prwh.rpg.capabilities.player.rpgRace.RpgRaceProvider;
import de.prwh.rpg.network.message.RpgRaceNBTMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class RpgRaceNBTMessageHandler extends AbstractClientMessageHandler<RpgRaceNBTMessage> {
	@Override
	public IMessage handleClientMessage(EntityPlayer player, RpgRaceNBTMessage message, MessageContext ctx) {
		// RPGMain.getLogger().info("Trying to synced Health");
		if (player != null) {
			IRpgRace rpgRace = player.getCapability(RpgRaceProvider.RPGRACE_CAP, null);
			IRpgRace message_rpgRace = message.getValue();

			try {
				if (rpgRace != null && message_rpgRace != null) {
					rpgRace.setRpgRace((message_rpgRace.getRpgRaceName()));
					// RPGMain.getLogger().info("RpgClass synced: " + rpgClass.getRpgClass());
				} else {
					RPGMain.getLogger().info("RpgRace synced failed.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}