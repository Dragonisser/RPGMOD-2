package de.prwh.rpg.capabilities.health;

/**
 * Health storage capability
 */
public interface IHealth {
	public void consumeHealth(float points);

	public void fillHealth(float points);

	public void setHealth(float points);

	public float getHealth();
	
	public void setMaxHealth(float points);
	
	public float getMaxHealth();
	
	public float getBaseMaxHealth();
}