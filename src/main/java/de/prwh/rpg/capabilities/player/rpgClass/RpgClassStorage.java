package de.prwh.rpg.capabilities.player.rpgClass;

import de.prwh.rpg.RPGMain;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * This class is responsible for saving and reading mana data from or to server
 */
public class RpgClassStorage implements IStorage<IRpgClass> {
	@Override
	public NBTBase writeNBT(Capability<IRpgClass> capability, IRpgClass instance, EnumFacing side) {

		final NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString(RPGMain.MODID + "className", instance.getRpgClass().getRpgClassName());

		return nbt;
	}

	@Override
	public void readNBT(Capability<IRpgClass> capability, IRpgClass instance, EnumFacing side, NBTBase nbt) {
		final NBTTagCompound tag = (NBTTagCompound) nbt;

		instance.setRpgClass((tag.getString(RPGMain.MODID + "className")));
	}
}