package de.prwh.rpg.handler.event.rpg;

import de.prwh.rpg.capabilities.player.RpgPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class LevelUpEvent extends PlayerEvent {

	private RpgPlayer rplayer;
	private float level;

	/**
	 * Fires when a player levels up
	 * 
	 * @param player
	 * @param level
	 */
	public LevelUpEvent(RpgPlayer player, float level) {
		super(player.getRealPlayer());
		this.rplayer = player;
		this.level = level;
	}

	public float getLevel() {
		return level;
	}

	public RpgPlayer getRplayer() {
		return rplayer;
	}
}