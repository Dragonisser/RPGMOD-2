package de.prwh.rpg.capabilities.mana;

import java.io.Serializable;

/**
 * Default implementation of IMana
 */
public class Mana implements IMana, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float currentMana = 20.0F;
	private float maxMana = 20.0F;
	private float maxMana_base = 20.0F;

	@Override
	public void consumeMana(float points) {
		this.currentMana -= points;

		if (this.currentMana < 0.0F)
			this.currentMana = 0.0F;
	}

	@Override
	public void fillMana(float points) {
		if(this.getMana() + points >= getMaxMana()) {
			setMana(getMaxMana());
		} else {
			setMana(getMana() + points);
		}
		
	}

	@Override
	public void setMana(float points) {
		this.currentMana = points;
	}

	@Override
	public float getMana() {
		return this.currentMana;
	}
	
	@Override
	public void setMaxMana(float points) {
		this.maxMana = points;
		
	}

	@Override
	public float getMaxMana() {
		return this.maxMana;
	}

	@Override
	public float getBaseMaxMana() {
		return this.maxMana_base;
	}
}