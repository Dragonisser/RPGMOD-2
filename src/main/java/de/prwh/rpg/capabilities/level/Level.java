package de.prwh.rpg.capabilities.level;

import java.io.Serializable;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.player.RpgPlayer;
import de.prwh.rpg.handler.event.rpg.LevelUpEvent;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Default implementation of IMana
 */
public class Level implements ILevel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float currentLevel = 1;
	private float maxlevel = 100;
	private float currentExperience = 0.0F;
	private float experienceTotal = 0;

	/***
	 * Use this if the set exp is below the max exp
	 * 
	 */
	public void setExperience(float points) {
		this.currentExperience = points;
	}
	
	
	/***
	 * Use this if you know that the set exp can be higher than the max exp at the current level
	 * 
	 */
	@Override
	public void setExperience(float points, RpgPlayer player) {
		if (points > getMaxExperience()) {

			while (points - getMaxExperience() > getMaxExperience()) {
				this.addLevel(1, player);
				points = points - getMaxExperience();
				System.out.println("Adding level, EXP left: " + points);
			}
			
			System.out.println("Current experience: " + this.currentExperience + "[setExperience]");
			this.currentExperience = points - getMaxExperience();
		} else {
			this.currentExperience = this.currentExperience + points;
		}
	}

	@Override
	public void addExperience(float points, RpgPlayer player) {

		float i = Integer.MAX_VALUE - this.experienceTotal;

		if (points > i) {
			points = i;
		}

		this.currentExperience += (float) points / (float) this.getMaxExperience();

		for (this.experienceTotal += points; this.currentExperience >= 1.0F; this.currentExperience /= (float) this.getMaxExperience()) {
			this.currentExperience = (this.currentExperience - 1.0F) * (float) this.getMaxExperience();
			int level = 1;
			this.addLevel(level, player);
			
			if(player.getRealPlayer() instanceof EntityPlayerMP) {
				//player.setMaxStats();
//				player.syncValuesMax();
//				player.syncValues();
			}
		}
		System.out.println("Current experience: " + this.currentExperience + "[addExperience]");
	}

	@Override
	public float getExperience() {
		return this.currentExperience;
	}
	
	@Override
	public void setTotalExperience(float points) {
		this.experienceTotal = this.experienceTotal + points;
	}

	@Override
	public float getTotalExperience() {
		return this.experienceTotal;
	}
	
	/**
	 * Returns the current experience in percent (0-100)
	 */
	public int getExperiencePercent() {
		return (int) (100 * this.getExperience());
	}
	
	/**********************************************/

	@Override
	public void setLevel(float points) {
		this.currentLevel = points;
	}

	@Override
	public void addLevel(float level, RpgPlayer player) {
		this.setLevel(this.currentLevel+level);

		LevelUpEvent event = new LevelUpEvent(player, level);
		if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) {
			return;
		}

		RPGMain.getLogger().info("Current Real Level " + getRealLevel());
		RPGMain.getLogger().info("Current Shown Level " + getLevel());

		if (this.currentLevel < 0) {
			this.currentLevel = 0;
			this.currentExperience = 0.0F;
			this.experienceTotal = 0;
		}
	}

	@Override
	public float getLevel() {
		return this.currentLevel;
	}

	@Override
	public float getRealLevel() {
		if(this.currentLevel == 0) {
			return 0;
		}
		return this.currentLevel - 1;
	}

	@Override
	public void setMaxLevel(float points) {
		this.maxlevel = points;
	}

	@Override
	public float getMaxLevel() {
		return this.maxlevel;
	}

	private float getMaxExperience() {
		return this.currentLevel >= 30 ? (112 + (this.currentLevel - 30) * 9) * 2
				: (this.currentLevel >= 15 ? (37 + (this.currentLevel - 15) * 5) * 2 : (7 + this.currentLevel * 2) * 2);
	}
}