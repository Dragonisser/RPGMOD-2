package de.prwh.rpg.capabilities.player.rpgRace;

import de.prwh.rpg.capabilities.player.rpgSkill.Skill;

/**
 * RpgClass storage capability
 */
public interface IRpgRace {

	public void setRpgRace(String value);

	public IRpgRace getRpgRace();

	public String getRpgRaceName();

	public Skill getRaceSkill();
}
