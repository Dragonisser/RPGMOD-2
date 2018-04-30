package de.prwh.rpg.capabilities.mana;

import de.prwh.rpg.RPGMain;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * This class is responsible for saving and reading mana data from or to server
 */
public class ManaStorage implements IStorage<IMana> {
	@Override
	public NBTBase writeNBT(Capability<IMana> capability, IMana instance, EnumFacing side) {

		final NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat(RPGMain.MODID + "mana", instance.getMana());
		nbt.setFloat(RPGMain.MODID + "manaMax", instance.getMaxMana());

		return nbt;
	}

	@Override
	public void readNBT(Capability<IMana> capability, IMana instance, EnumFacing side, NBTBase nbt) {
		final NBTTagCompound tag = (NBTTagCompound) nbt;

		instance.setMana(tag.getFloat(RPGMain.MODID + "mana"));
		instance.setMaxMana(tag.getFloat(RPGMain.MODID + "manaMax"));
	}
}