package de.prwh.rpg.network.handler.client;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.level.ILevel;
import de.prwh.rpg.capabilities.level.LevelProvider;
import de.prwh.rpg.capabilities.player.RpgPlayer;
import de.prwh.rpg.network.message.RpgLevelNBTMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class RpgLevelNBTMessageHandler extends AbstractClientMessageHandler<RpgLevelNBTMessage> {
	@Override
	public IMessage handleClientMessage(EntityPlayer player, RpgLevelNBTMessage message, MessageContext ctx) {
		// RPGMain.getLogger().info("Trying to synced Health");
		if (player != null) {
			ILevel level = player.getCapability(LevelProvider.LEVEL_CAP, null);
			ILevel message_level = message.getValue();

			try {
				if (level != null && message_level != null) {
					RpgPlayer rpgPlayer = new RpgPlayer(player);
					level.setExperience(message_level.getExperience(), rpgPlayer);
					level.setLevel(message_level.getLevel());
					// RPGMain.getLogger().info("Health synced");
				} else {
					RPGMain.getLogger().info("Level synced failed.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}