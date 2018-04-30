package de.prwh.rpg.capabilities.player.rpgSkill;

import de.prwh.rpg.capabilities.player.RpgPlayer;

public abstract class ActiveSkill extends Skill {
	public ActiveSkill(String name) {
		super(name);
	}

	public abstract void useSkill(RpgPlayer player);
}
