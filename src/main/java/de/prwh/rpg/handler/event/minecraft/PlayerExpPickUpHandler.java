package de.prwh.rpg.handler.event.minecraft;

import de.prwh.rpg.capabilities.player.RpgPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerExpPickUpHandler {
	@SubscribeEvent
	public void onPickup(PlayerPickupXpEvent event) {

//		if (event.getEntity() instanceof EntityPlayerMP) {
//			RpgPlayer player = new RpgPlayer(event.getEntityPlayer());
//
//			player.getRpgLevel().addExperience(event.getOrb().xpValue, player);
//			player.syncValues();
//		}
	}
	
	@SubscribeEvent
	public void onDrop(LivingExperienceDropEvent event) {
		if (event.getAttackingPlayer() instanceof EntityPlayerMP) {
			RpgPlayer player = new RpgPlayer(event.getAttackingPlayer());

			player.getRpgLevel().addExperience(event.getDroppedExperience(), player);
			player.syncValues();
		}
	}
}
