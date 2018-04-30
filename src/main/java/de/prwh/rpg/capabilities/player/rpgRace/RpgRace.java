package de.prwh.rpg.capabilities.player.rpgRace;

import java.io.Serializable;

import de.prwh.rpg.capabilities.player.rpgRace.races.DwarfRace;
import de.prwh.rpg.capabilities.player.rpgRace.races.ElfRace;
import de.prwh.rpg.capabilities.player.rpgRace.races.HumanRace;
import de.prwh.rpg.capabilities.player.rpgRace.races.LizardRace;
import de.prwh.rpg.capabilities.player.rpgRace.races.NoRace;
import de.prwh.rpg.capabilities.player.rpgSkill.Skill;

public class RpgRace implements IRpgRace, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1514117239029030136L;
	private String raceName;
	private IRpgRace rpgRace;

	public IRpgRace getRpgRace() {
		if (rpgRace == null) {
			rpgRace = new NoRace();
		} else {
			return rpgRace;
		}
		return rpgRace;
	}

	public String getRpgRaceName() {
		return raceName;
	}

	public void setRpgRace(String raceName) {
		if (parseRpgRace(raceName) != null) {
			rpgRace = parseRpgRace(raceName);
		}
	}

	private IRpgRace parseRpgRace(String raceName) {

		switch (raceName.toLowerCase()) {
		case "lizard":
			return new LizardRace();
		case "dwarf":
			return new DwarfRace();
		case "elf":
			return new ElfRace();
		case "human":
			return new HumanRace();
		case "norace":
			return new NoRace();
		default:
			return null;
		}
	}

	@Override
	public Skill getRaceSkill() {
		return null;
	}
}
