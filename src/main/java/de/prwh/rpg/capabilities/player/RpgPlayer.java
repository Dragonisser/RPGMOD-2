package de.prwh.rpg.capabilities.player;

import java.io.Serializable;

import de.prwh.rpg.capabilities.health.HealthProvider;
import de.prwh.rpg.capabilities.health.IHealth;
import de.prwh.rpg.capabilities.level.ILevel;
import de.prwh.rpg.capabilities.level.LevelProvider;
import de.prwh.rpg.capabilities.mana.IMana;
import de.prwh.rpg.capabilities.mana.ManaProvider;
import de.prwh.rpg.capabilities.player.rpgClass.IRpgClass;
import de.prwh.rpg.capabilities.player.rpgClass.RpgClassProvider;
import de.prwh.rpg.capabilities.player.rpgClass.classes.NoClassClass;
import de.prwh.rpg.capabilities.player.rpgRace.IRpgRace;
import de.prwh.rpg.capabilities.player.rpgRace.RpgRaceProvider;
import de.prwh.rpg.capabilities.stamina.IStamina;
import de.prwh.rpg.capabilities.stamina.StaminaProvider;
import de.prwh.rpg.network.RPGPacketDispatcher;
import de.prwh.rpg.network.message.HealthNBTMessage;
import de.prwh.rpg.network.message.ManaNBTMessage;
import de.prwh.rpg.network.message.RpgClassNBTMessage;
import de.prwh.rpg.network.message.RpgLevelNBTMessage;
import de.prwh.rpg.network.message.RpgRaceNBTMessage;
import de.prwh.rpg.network.message.StaminaNBTMessage;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class RpgPlayer implements Serializable {

	private static final long serialVersionUID = 1L;
	EntityPlayer player;

	private float healthBoost = 1.1F;
	private float manaBoost = 1.1F;
	private float staminaBoost = 1.1F;

	public RpgPlayer(EntityPlayer player) {
		this.player = player;
		// this.getClassInfo().setClassName("Caster");
	}

	public EntityPlayer getRealPlayer() {
		return this.player;
	}

	private IMana getManaInfo() {
		IMana manaInfo = player.getCapability(ManaProvider.MANA_CAP, null);

		if (manaInfo != null) {
			ManaNBTMessage message = new ManaNBTMessage(manaInfo);
			RPGPacketDispatcher.sendTo(message, (EntityPlayerMP) this.player);
		}

		return manaInfo;
	}

	private IStamina getStaminaInfo() {
		IStamina staminaInfo = player.getCapability(StaminaProvider.STAMINA_CAP, null);

		if (staminaInfo != null) {
			StaminaNBTMessage message = new StaminaNBTMessage(staminaInfo);
			RPGPacketDispatcher.sendTo(message, (EntityPlayerMP) this.player);
		}

		return staminaInfo;
	}

	private IHealth getHealthInfo() {
		IHealth healthInfo = player.getCapability(HealthProvider.HEALTH_CAP, null);

		healthInfo.setHealth(getVanillaHealth());
		if (healthInfo != null) {
			HealthNBTMessage message = new HealthNBTMessage(healthInfo);
			RPGPacketDispatcher.sendTo(message, (EntityPlayerMP) this.player);
		}

		return healthInfo;
	}

	private IRpgClass getClassInfo() {
		IRpgClass classInfo = player.getCapability(RpgClassProvider.RPGCLASS_CAP, null);

		if (classInfo.getRpgClass() instanceof NoClassClass) {
			this.healthBoost = 1.0F;
			this.manaBoost = 1.0F;
			this.staminaBoost = 1.0F;
		} else {
			this.healthBoost = 1.1F;
			this.manaBoost = 1.1F;
			this.staminaBoost = 1.1F;
		}

		if (classInfo != null) {
			RpgClassNBTMessage message = new RpgClassNBTMessage(classInfo.getRpgClass());
			RPGPacketDispatcher.sendTo(message, (EntityPlayerMP) this.player);
		}
		return classInfo;
	}

	private IRpgRace getRaceInfo() {
		IRpgRace raceInfo = player.getCapability(RpgRaceProvider.RPGRACE_CAP, null);

		if (raceInfo != null) {
			RpgRaceNBTMessage message = new RpgRaceNBTMessage(raceInfo.getRpgRace());
			RPGPacketDispatcher.sendTo(message, (EntityPlayerMP) this.player);
		}
		return raceInfo;
	}

	private ILevel getLevelInfo() {
		ILevel levelInfo = player.getCapability(LevelProvider.LEVEL_CAP, null);
		if (levelInfo != null) {
			RpgLevelNBTMessage message = new RpgLevelNBTMessage(levelInfo);
			RPGPacketDispatcher.sendTo(message, (EntityPlayerMP) this.player);
		}
		return levelInfo;
	}

	public void syncValues() {
		getManaInfo();
		getStaminaInfo();
		getHealthInfo();
		getClassInfo();
		getLevelInfo();
	}

	public void syncValuesMax() {
		setMaxHealth();
		setMaxMana();
		setMaxStamina();
	}

	public void setMaxStats() {
		setMana(getMaxMana());
		setStamina(getMaxStamina());
		setCurrentHealth(getMaxHealth());
	}

	public float getMana() {
		return getManaInfo().getMana();
	}

	public void setMana(float points) {
		getManaInfo().setMana(points);
	}

	public void setMaxMana(float points) {
		getManaInfo().setMaxMana(points);
	}

	public void consumeMana(float points) {
		getManaInfo().consumeMana(points);
	}

	public void fillMana(float points) {
		getManaInfo().fillMana(points);
	}

	public void setMaxMana() {
		getManaInfo().setMaxMana(getMaxMana());
	}

	public float getMaxMana() {

		IMana manaInfo = player.getCapability(ManaProvider.MANA_CAP, null);

		float base = manaInfo.getBaseMaxMana();
		float multiplier = getRpgClass().getManaMultiplier();
		float multiplier_step = getRpgClass().getManaMultiplierStep();
		float realLevel = getRpgLevel().getRealLevel();

		float max = (base * (multiplier + realLevel * multiplier_step)) * manaBoost;

		return max;
	}

	public float getStamina() {
		return getStaminaInfo().getStamina();
	}

	public void consumeStamina(float points) {
		getStaminaInfo().consumeStamina(points);
	}

	public void fillStamina(float points) {
		getStaminaInfo().fillStamina(points);
	}

	public void setStamina(float points) {
		getStaminaInfo().setStamina(points);
	}

	public void setMaxStamina(float points) {
		getStaminaInfo().setMaxStamina(points);
	}

	public void setMaxStamina() {
		getStaminaInfo().setMaxStamina(getMaxStamina());
	}

	public float getMaxStamina() {
		IStamina staminaInfo = player.getCapability(StaminaProvider.STAMINA_CAP, null);

		float base = staminaInfo.getBaseMaxStamina();
		float multiplier = getRpgClass().getStaminaMultiplier();
		float multiplier_step = getRpgClass().getStaminaMultiplierStep();
		float realLevel = getRpgLevel().getRealLevel();

		float max = (base * (multiplier + realLevel * multiplier_step)) * staminaBoost;

		return max;
	}

	public void setVanillaHealth(float points) {
		player.setHealth(points);
	}

	public float getVanillaHealth() {
		return player.getHealth();
	}

	public void setVanillaMaxHealth(float points) {
		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(points);
	}

	/**
	 * Used since the vanilla health is located at the Entity itself and needs to by
	 * synced with the rpg health stored at the RpgPlayer
	 */
	public void setMaxHealth(float points) {
		getHealthInfo().setMaxHealth(points);
		setVanillaMaxHealth(getHealthInfo().getMaxHealth());
	}

	public void setMaxHealth() {
		getHealthInfo().setMaxHealth(getMaxHealth());
		setVanillaMaxHealth(getMaxHealth());
	}

	public float getMaxHealth() {

		float base = getHealthInfo().getBaseMaxHealth();
		float multiplier = getRpgClass().getHealthMultiplier();
		float multiplier_step = getRpgClass().getHealthMultiplierStep();
		float realLevel = getRpgLevel().getRealLevel();

		float max = (base * (multiplier + realLevel * multiplier_step)) * healthBoost;

		getHealthInfo().setMaxHealth(max);
		setVanillaMaxHealth(max);

		return max;
	}

	public float getCurrentHealth() {
		getHealthInfo().setHealth(getVanillaHealth());
		return getHealthInfo().getHealth();
	}

	public void setCurrentHealth(float points) {

		getHealthInfo().setHealth(points);
		setVanillaHealth(points);
	}

	/***
	 * Used for changing RpgClasses - Archer > Caster
	 * 
	 * @return
	 */
	public IRpgClass getRpgClassInfo() {
		return getClassInfo();
	}

	/***
	 * Used for getting values of the chosen RpgClass
	 * 
	 * @return
	 */
	public IRpgClass getRpgClass() {
		return getClassInfo().getRpgClass();
	}

	/***
	 * Used for changing RpgRaces - Human > Lizard
	 * 
	 * @return
	 */
	public IRpgRace getRpgRaceInfo() {
		return getRaceInfo();
	}

	/***
	 * Used for getting values of the chosen RpgRace
	 * 
	 * @return
	 */
	public IRpgRace getRpgRace() {
		return getRaceInfo().getRpgRace();
	}

	public ILevel getRpgLevel() {
		return getLevelInfo();
	}

	public void resetPlayer() {
		getRpgClassInfo().setRpgClass("NoClass");
		
		getRpgRaceInfo().setRpgRace("NoRace");

		getRpgLevel().setExperience(0);
		getRpgLevel().setLevel(1);
		getRpgLevel().setTotalExperience(0);

		getHealthInfo().setMaxHealth(getHealthInfo().getBaseMaxHealth());
		getHealthInfo().setHealth(getHealthInfo().getBaseMaxHealth());

		getManaInfo().setMaxMana(getManaInfo().getBaseMaxMana());
		getManaInfo().setMana(getManaInfo().getBaseMaxMana());

		getStaminaInfo().setMaxStamina(getStaminaInfo().getBaseMaxStamina());
		getStaminaInfo().setStamina(getStaminaInfo().getBaseMaxStamina());
	}

	public void clonePlayer(RpgPlayer ori) {
		getRpgClassInfo().setRpgClass(ori.getRpgClass().getRpgClassName());

		getRpgRaceInfo().setRpgRace(ori.getRpgRace().getRpgRaceName());

		getRpgLevel().setExperience(ori.getRpgLevel().getExperience());
		getRpgLevel().setLevel(ori.getRpgLevel().getLevel());
		getRpgLevel().setTotalExperience(ori.getRpgLevel().getTotalExperience());

		setMaxHealth(ori.getMaxHealth());
		setCurrentHealth(ori.getMaxHealth());

		getManaInfo().setMaxMana(ori.getMaxMana());
		getManaInfo().setMana(ori.getMaxMana());

		getStaminaInfo().setMaxStamina(ori.getMaxStamina());
		getStaminaInfo().setStamina(ori.getMaxStamina());
	}
}