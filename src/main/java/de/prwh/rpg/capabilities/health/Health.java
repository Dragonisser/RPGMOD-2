package de.prwh.rpg.capabilities.health;

import java.io.Serializable;

/**
 * Default implementation of IMana
 */
public class Health implements IHealth, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float currentHealth = 20.0F;
	private float maxHealth = 20.0F;
	private float maxHealth_base = 20.0F;

	@Override
	public void consumeHealth(float points) {
		this.currentHealth -= points;

		if (this.currentHealth < 0.0F)
			this.currentHealth = 0.0F;
	}

	@Override
	public void fillHealth(float points) {
		this.currentHealth += points;
	}

	@Override
	public void setHealth(float points) {
		this.currentHealth = points;
	}

	@Override
	public float getHealth() {
		return this.currentHealth;
	}
	
	@Override
	public void setMaxHealth(float points) {
		this.maxHealth = points;
		
	}

	@Override
	public float getMaxHealth() {
		return this.maxHealth;
	}

	@Override
	public float getBaseMaxHealth() {
		return this.maxHealth_base;
	}
}