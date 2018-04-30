package de.prwh.rpg.capabilities.player.rpgSkill;

import de.prwh.rpg.capabilities.player.RpgPlayer;

public abstract class PassiveSkill extends Skill {

	public PassiveSkill(String name) {
		super(name);
	}

	public void activateSkill(RpgPlayer player) {
	}
}
