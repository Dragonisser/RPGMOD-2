package de.prwh.rpg.network.handler.client;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.health.HealthProvider;
import de.prwh.rpg.capabilities.health.IHealth;
import de.prwh.rpg.network.message.HealthNBTMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class HealthNBTMessageHandler extends AbstractClientMessageHandler<HealthNBTMessage> {
	@Override
	public IMessage handleClientMessage(EntityPlayer player, HealthNBTMessage message, MessageContext ctx) {
		//RPGMain.getLogger().info("Trying to synced Health");
		if (player != null) {
			IHealth health = player.getCapability(HealthProvider.HEALTH_CAP, null);
			IHealth message_health = message.getValue();
			
			try {
				if (health != null && message_health != null) {
					health.setHealth(message_health.getHealth());
					health.setMaxHealth(message_health.getMaxHealth());
					//RPGMain.getLogger().info("Health synced");
				} else {
					RPGMain.getLogger().info("Health synced failed.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}