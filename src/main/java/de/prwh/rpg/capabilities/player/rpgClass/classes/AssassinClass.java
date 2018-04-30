package de.prwh.rpg.capabilities.player.rpgClass.classes;

import de.prwh.rpg.capabilities.player.rpgClass.RpgClass;

public class AssassinClass extends RpgClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6694410412780476748L;
	private static float HEALTH_MULTIPLIER_BASE = 0.7f;
	private static float MANA_MULTIPLIER_BASE = 1.3f;
	private static float STAMINA_MULTIPLIER_BASE = 1.3f;

	private static float HEALTH_MULTIPLIER_STEP = 0.05f;
	private static float MANA_MULTIPLIER_STEP = 0.05f;
	private static float STAMINA_MULTIPLIER_STEP = 0.05f;

	private String className = "Assassin";
	
	public AssassinClass() {
	}
	
	public String getRpgClassName() {
		return className;
	}

	@Override
	public float getHealthMultiplier() {
		return HEALTH_MULTIPLIER_BASE;
	}

	@Override
	public float getManaMultiplier() {
		return MANA_MULTIPLIER_BASE;
	}

	@Override
	public float getStaminaMultiplier() {
		return STAMINA_MULTIPLIER_BASE;
	}
	
	@Override
	public float getHealthMultiplierStep() {
		return HEALTH_MULTIPLIER_STEP;
	}

	@Override
	public float getManaMultiplierStep() {
		return MANA_MULTIPLIER_STEP;
	}

	@Override
	public float getStaminaMultiplierStep() {
		return STAMINA_MULTIPLIER_STEP;
	}
}
