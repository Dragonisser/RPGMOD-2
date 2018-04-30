package de.prwh.rpg.handler.event.minecraft;

import de.prwh.rpg.capabilities.player.RpgPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class EventHandler {

	int counter = 0;
	// private static final String LOGIN_SYNC = "RPG_PLAYER_LOGIN_SYNC";

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		if (event.side.equals(Side.SERVER)) {
			counter++;
			if (counter >= 20) {
				// System.out.println("SYNC");
				RpgPlayer rpgPlayer = new RpgPlayer(event.player);
				rpgPlayer.syncValues();
				
				counter = 0;
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerDeath(PlayerEvent.Clone event) {
		
		if(event.isWasDeath()) {
			RpgPlayer ori_p = new RpgPlayer(event.getOriginal());
			RpgPlayer new_p = new RpgPlayer(event.getEntityPlayer());
			
			new_p.clonePlayer(ori_p);
			new_p.syncValuesMax();
			new_p.setMaxStats();
			new_p.syncValues();
		}
	}

	@SubscribeEvent
	public void onPlayerLogsIn(PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;

		System.out.println("Hello?");

		RpgPlayer rpgPlayer = new RpgPlayer(player);

		String message3 = "Hello there " + player.getName() + ". Your class is: " + rpgPlayer.getRpgClass().getRpgClassName();
		player.sendMessage(new TextComponentString(message3));
		
		String message = String.format("You've got §7%d§r mana left.", (int) rpgPlayer.getMana());
		player.sendMessage(new TextComponentString(message));
		
		String message1 = String.format("You've got §7%d§r stamina left.", (int) rpgPlayer.getStamina());
		player.sendMessage(new TextComponentString(message1));
		
		String message2 = String.format("You've got §7%d§r health left.", (int) rpgPlayer.getCurrentHealth());
		player.sendMessage(new TextComponentString(message2));
		
		String message4 = "You're level " + rpgPlayer.getRpgLevel().getLevel() + " with " + rpgPlayer.getRpgLevel().getExperiencePercent() + "% EXP";
		player.sendMessage(new TextComponentString(message4));
	}

	@SubscribeEvent
	public void onPlayerSleep(PlayerSleepInBedEvent event) {
		EntityPlayer player = event.getEntityPlayer();

		if (player.world.isRemote)
			return;

		RpgPlayer rpgPlayer = new RpgPlayer(player);
		rpgPlayer.fillMana(50);

		String message = String.format("You refreshed yourself in the bed. You received 50 mana, you have §7%d§r mana left.", (int) rpgPlayer.getMana());
		player.sendMessage(new TextComponentString(message));
	}

	@SubscribeEvent
	public void onPlayerFalls(LivingFallEvent event) {
		Entity entity = event.getEntity();

		if (entity.world.isRemote || !(entity instanceof EntityPlayerMP) || event.getDistance() < 3)
			return;

		EntityPlayer player = (EntityPlayer) entity;
		RpgPlayer rpgPlayer = new RpgPlayer(player);
		float points = rpgPlayer.getMana();
		float cost = Math.round((event.getDistance() * 2) * 1) / 1;

		if (points > cost) {
			rpgPlayer.consumeMana(cost);

			String message = String.format("You absorbed fall damage. It costed §7%d§r mana, you have §7%d§r mana left.", (int) cost,
					(int) rpgPlayer.getMana());
			player.sendMessage(new TextComponentString(message));

			event.setCanceled(true);
		}
	}
}