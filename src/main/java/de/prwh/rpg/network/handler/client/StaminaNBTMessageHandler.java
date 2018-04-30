package de.prwh.rpg.network.handler.client;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.stamina.IStamina;
import de.prwh.rpg.capabilities.stamina.StaminaProvider;
import de.prwh.rpg.network.message.StaminaNBTMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class StaminaNBTMessageHandler extends AbstractClientMessageHandler<StaminaNBTMessage> {
	@Override
	public IMessage handleClientMessage(EntityPlayer player, StaminaNBTMessage message, MessageContext ctx) {
		//RPGMain.getLogger().info("Trying to synced Stamina");
		if (player != null) {
			IStamina stamina = player.getCapability(StaminaProvider.STAMINA_CAP, null);
			IStamina message_stamina = message.getValue();
			try {
				if (stamina != null && message_stamina != null) {
					stamina.setStamina(message_stamina.getStamina());
					stamina.setMaxStamina(message_stamina.getMaxStamina());
					//RPGMain.getLogger().info("Stamina synced");
				} else {
					RPGMain.getLogger().info("Stamina synced failed.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}