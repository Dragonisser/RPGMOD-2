package de.prwh.rpg.capabilities.health;

import de.prwh.rpg.RPGMain;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * This class is responsible for saving and reading mana data from or to server
 */
public class HealthStorage implements IStorage<IHealth> {
	@Override
	public NBTBase writeNBT(Capability<IHealth> capability, IHealth instance, EnumFacing side) {

		final NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat(RPGMain.MODID + "health", instance.getHealth());
		nbt.setFloat(RPGMain.MODID + "healthMax", instance.getMaxHealth());

		return nbt;
	}

	@Override
	public void readNBT(Capability<IHealth> capability, IHealth instance, EnumFacing side, NBTBase nbt) {
		final NBTTagCompound tag = (NBTTagCompound) nbt;

		instance.setHealth(tag.getFloat(RPGMain.MODID + "health"));
		instance.setMaxHealth(tag.getFloat(RPGMain.MODID + "healthMax"));
	}
}