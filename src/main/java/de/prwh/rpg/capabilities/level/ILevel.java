package de.prwh.rpg.capabilities.level;

import de.prwh.rpg.capabilities.player.RpgPlayer;

/**
 * Level storage capability
 */
public interface ILevel {

	public void setExperience(float points);
	
	public void setExperience(float points, RpgPlayer player);

	public void addExperience(float points, RpgPlayer player);
	
	public void setTotalExperience(float points);

	public float getExperience();
	
	public float getTotalExperience();
	
	public int getExperiencePercent();

	public void setLevel(float points);

	public void addLevel(float points, RpgPlayer player);

	public float getLevel();

	public void setMaxLevel(float points);

	public float getMaxLevel();

	public float getRealLevel();
}