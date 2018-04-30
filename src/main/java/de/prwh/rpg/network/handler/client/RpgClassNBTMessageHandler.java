package de.prwh.rpg.network.handler.client;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.player.rpgClass.IRpgClass;
import de.prwh.rpg.capabilities.player.rpgClass.RpgClassProvider;
import de.prwh.rpg.network.message.RpgClassNBTMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class RpgClassNBTMessageHandler extends AbstractClientMessageHandler<RpgClassNBTMessage> {
	@Override
	public IMessage handleClientMessage(EntityPlayer player, RpgClassNBTMessage message, MessageContext ctx) {
		// RPGMain.getLogger().info("Trying to synced Health");
		if (player != null) {
			IRpgClass rpgClass = player.getCapability(RpgClassProvider.RPGCLASS_CAP, null);
			IRpgClass message_rpgClass = message.getValue();

			try {
				if (rpgClass != null && message_rpgClass != null) {
					rpgClass.setRpgClass((message_rpgClass.getRpgClassName()));
					//RPGMain.getLogger().info("RpgClass synced: " + rpgClass.getRpgClass());
				} else {
					RPGMain.getLogger().info("RpgClass synced failed.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}