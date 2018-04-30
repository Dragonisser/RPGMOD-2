package de.prwh.rpg.capabilities.stamina;

/**
 * Health storage capability
 */
public interface IStamina {

	public void consumeStamina(float points);

	public void fillStamina(float points);

	public void setStamina(float points);

	public float getStamina();
	
	public void setMaxStamina(float points);

	public float getMaxStamina();
	
	public float getBaseMaxStamina();
}
