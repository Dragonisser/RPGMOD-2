package de.prwh.rpg.capabilities.player.rpgRace;

import de.prwh.rpg.RPGMain;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * This class is responsible for saving and reading mana data from or to server
 */
public class RpgRaceStorage implements IStorage<IRpgRace> {
	@Override
	public NBTBase writeNBT(Capability<IRpgRace> capability, IRpgRace instance, EnumFacing side) {

		final NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString(RPGMain.MODID + "raceName", instance.getRpgRace().getRpgRaceName());

		return nbt;
	}

	@Override
	public void readNBT(Capability<IRpgRace> capability, IRpgRace instance, EnumFacing side, NBTBase nbt) {
		final NBTTagCompound tag = (NBTTagCompound) nbt;

		instance.setRpgRace((tag.getString(RPGMain.MODID + "raceName")));
	}
}