package de.prwh.rpg.handler.event.rpg;

import de.prwh.rpg.capabilities.player.RpgPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LevelUpHandler {
	@SubscribeEvent
	public void onlevelup(LevelUpEvent event) {

		if (event.getEntity() instanceof EntityPlayerMP) {
			RpgPlayer player = new RpgPlayer(event.getEntityPlayer());
			
			player.syncValues();
			player.syncValuesMax();	
		}
	}
}