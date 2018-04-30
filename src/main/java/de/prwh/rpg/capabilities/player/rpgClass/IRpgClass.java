package de.prwh.rpg.capabilities.player.rpgClass;

/**
 * RpgClass storage capability
 */
public interface IRpgClass {

	public void setRpgClass(String value);
	
	public IRpgClass getRpgClass();
	
	public String getRpgClassName();

	public float getHealthMultiplier();

	public float getManaMultiplier();

	public float getStaminaMultiplier();
	
	public float getHealthMultiplierStep();

	public float getManaMultiplierStep();

	public float getStaminaMultiplierStep();
}
