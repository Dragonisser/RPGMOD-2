package de.prwh.rpg.handler.event.rpg;

import de.prwh.rpg.capabilities.player.RpgPlayer;
import de.prwh.rpg.network.RPGPacketDispatcher;
import de.prwh.rpg.network.message.ChangeKeyStateMessage;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class StaminaSprintHandler {
	int counter_deplete = 0;
	int counter_fill = 0;
	int staminause = 1;
	int counter_refill_start = 0;
	int stamina_regen = 1;
	int timer = 0;

	@SubscribeEvent
	public void onEvent(PlayerTickEvent event) {
		if (event.player instanceof EntityPlayerMP) {
			EntityPlayerMP playerMP = (EntityPlayerMP) event.player;

			RpgPlayer rpgPlayer = new RpgPlayer(event.player);
			float current_stamina = rpgPlayer.getStamina();

			if (event.player.isSprinting() && current_stamina > 0) {
				counter_deplete++;

				if (rpgPlayer.getStamina() - staminause >= 0 && !playerMP.capabilities.isCreativeMode) {
					if (counter_deplete >= 15) {
						rpgPlayer.setStamina(rpgPlayer.getStamina() - staminause);
						counter_refill_start = 0;
						counter_deplete = 0;
					}
				}
				if (rpgPlayer.getStamina() == 0) {
					playerMP.setSprinting(false);
					ChangeKeyStateMessage message = new ChangeKeyStateMessage(false);
					RPGPacketDispatcher.sendTo(message, playerMP);
					counter_deplete = 0;
				}
			}
			if (rpgPlayer.getStamina() == 0) {
				playerMP.setSprinting(false);
				ChangeKeyStateMessage message = new ChangeKeyStateMessage(false);
				RPGPacketDispatcher.sendTo(message, playerMP);
				// System.out.println("YOU SHALL NOT SPRINT");
				if (counter_refill_start < 50) {
					counter_refill_start++;
				}
			}
			if (!event.player.isSprinting() && rpgPlayer.getStamina() > 0) {
				if (counter_refill_start < 50) {
					counter_refill_start++;
				}
			}
			if (!event.player.isSprinting() && rpgPlayer.getStamina() >= 0 && counter_refill_start >= 40) {
				counter_fill++;
				if (counter_fill >= 80) {
					if ((float)(int)rpgPlayer.getStamina() + staminause <= rpgPlayer.getMaxStamina()) {
						rpgPlayer.setStamina(rpgPlayer.getStamina() + stamina_regen);
					}
					counter_fill = 0;
				}
			}

		}
	}
}