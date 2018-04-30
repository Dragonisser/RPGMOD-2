package de.prwh.rpg.capabilities.player.rpgSkill;

import de.prwh.rpg.capabilities.player.RpgPlayer;

public abstract class Skill {
	private String name;

	public Skill(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract boolean fulfillsRequirement(RpgPlayer player);
}
