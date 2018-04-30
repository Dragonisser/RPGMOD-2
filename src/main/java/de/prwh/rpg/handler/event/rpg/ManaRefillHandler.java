package de.prwh.rpg.handler.event.rpg;

import de.prwh.rpg.capabilities.player.RpgPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ManaRefillHandler {

	int counter_fill = 0;
	int counter_refill_start = 0;
	int mana_regen = 1;

	// FIXME D:
	@SubscribeEvent
	public void onEvent(PlayerTickEvent event) {
		if (event.player instanceof EntityPlayerMP) {
			RpgPlayer rplayer = new RpgPlayer(event.player);
			float current_mana = rplayer.getMana();
			float max_mana = rplayer.getMaxMana();

			if (counter_refill_start < 50) {
				counter_refill_start++;
			}
			if (current_mana < max_mana) {
				counter_fill++;
				if (counter_fill >= 100) {
					rplayer.setMana((float)Math.round((current_mana + mana_regen) * 1) / 1);
					counter_fill = 0;
				}
			}
		}
	}
}
