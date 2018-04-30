package de.prwh.rpg.capabilities.player.rpgClass;

import java.io.Serializable;

import de.prwh.rpg.capabilities.player.rpgClass.classes.ArcherClass;
import de.prwh.rpg.capabilities.player.rpgClass.classes.AssassinClass;
import de.prwh.rpg.capabilities.player.rpgClass.classes.CasterClass;
import de.prwh.rpg.capabilities.player.rpgClass.classes.NoClassClass;
import de.prwh.rpg.capabilities.player.rpgClass.classes.SwordsmanClass;

/**
 * Default implementation of IRpgClass
 */
public class RpgClass implements IRpgClass, Serializable {

	private static final long serialVersionUID = 4166993179307495636L;
	
	private IRpgClass rpgClass;
	private String className = "";
	
	public IRpgClass getRpgClass() {
		if(rpgClass == null) {
			rpgClass = new NoClassClass();
		} else {
			return rpgClass;
		}
		return rpgClass;
	}
	
	public String getRpgClassName() {
		return className;
	}
	
	public void setRpgClass(String className){
		if(parseRpgClass(className) != null) {
			rpgClass = parseRpgClass(className);
		}
	}
	
	private IRpgClass parseRpgClass(String className) {
		
		switch (className.toLowerCase()) {
		case "archer":
			return new ArcherClass();
		case "assassin":
			return new AssassinClass();
		case "caster":
			return new CasterClass();
		case "swordsman":
			return new SwordsmanClass();
		case "noclass":
			return new NoClassClass();
		default:
			return null;
		}
	}
	
	@Override
	public float getHealthMultiplier() {
		return 0;
	}

	@Override
	public float getManaMultiplier() {
		return 0;
	}

	@Override
	public float getStaminaMultiplier() {
		return 0;
	}

	@Override
	public float getHealthMultiplierStep() {
		return 0;
	}

	@Override
	public float getManaMultiplierStep() {
		return 0;
	}

	@Override
	public float getStaminaMultiplierStep() {
		return 0;
	}
}