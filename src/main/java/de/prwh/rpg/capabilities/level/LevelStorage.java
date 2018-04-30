package de.prwh.rpg.capabilities.level;

import de.prwh.rpg.RPGMain;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * This class is responsible for saving and reading mana data from or to server
 */
public class LevelStorage implements IStorage<ILevel> {
	@Override
	public NBTBase writeNBT(Capability<ILevel> capability, ILevel instance, EnumFacing side) {

		final NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat(RPGMain.MODID + "exp", instance.getExperience());
		nbt.setFloat(RPGMain.MODID + "totalExp", instance.getTotalExperience());
		nbt.setFloat(RPGMain.MODID + "level", instance.getLevel());
		
		return nbt;
	}

	@Override
	public void readNBT(Capability<ILevel> capability, ILevel instance, EnumFacing side, NBTBase nbt) {
		final NBTTagCompound tag = (NBTTagCompound) nbt;

		instance.setExperience(tag.getFloat(RPGMain.MODID + "exp"));
		instance.setTotalExperience(tag.getFloat(RPGMain.MODID + "totalExp"));
		instance.setLevel(tag.getFloat(RPGMain.MODID + "level"));
	}
}