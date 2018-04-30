package de.prwh.rpg.capabilities.stamina;

import java.io.Serializable;

/**
 * Created by max on 17.02.2017.
 */

public class Stamina implements IStamina, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float currentStamina = 20.0F;
	private float maxStamina = 20.0F;
	private float maxStamina_base = 20.0F;

	@Override
	public void consumeStamina(float points) {
		this.currentStamina -= points;

		if (this.currentStamina < 0.0F)
			this.currentStamina = 0.0F;
	}

	@Override
	public void fillStamina(float points) {

		this.currentStamina += points;
	}

	@Override
	public void setStamina(float points) {

		this.currentStamina = points;
	}

	@Override
	public float getStamina() {

		return this.currentStamina;
	}

	@Override
	public void setMaxStamina(float points) {
		this.maxStamina = points;
	}
	
	@Override
	public float getMaxStamina() {
		return this.maxStamina;
	}

	@Override
	public float getBaseMaxStamina() {
		return this.maxStamina_base;
	}
}
