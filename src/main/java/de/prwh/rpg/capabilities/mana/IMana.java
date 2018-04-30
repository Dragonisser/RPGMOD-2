package de.prwh.rpg.capabilities.mana;

/**
 * Mana storage capability
 */
public interface IMana {
	public void consumeMana(float points);

	public void fillMana(float points);

	public void setMana(float points);

	public float getMana();
	
	public void setMaxMana(float points);
	
	public float getMaxMana();
	
	public float getBaseMaxMana();
}