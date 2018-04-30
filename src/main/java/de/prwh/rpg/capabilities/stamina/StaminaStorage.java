package de.prwh.rpg.capabilities.stamina;

import de.prwh.rpg.RPGMain;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * Created by max on 17.02.2017.
 */
public class StaminaStorage implements IStorage<IStamina> {

	@Override
	public NBTBase writeNBT(Capability<IStamina> capability, IStamina instance, EnumFacing side) {

		final NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat(RPGMain.MODID + "stamina", instance.getStamina());
		nbt.setFloat(RPGMain.MODID + "staminaMax", instance.getMaxStamina());

		return nbt;
	}

	@Override
	public void readNBT(Capability<IStamina> capability, IStamina instance, EnumFacing side, NBTBase nbt) {

		final NBTTagCompound tag = (NBTTagCompound) nbt;

		instance.setStamina(tag.getFloat(RPGMain.MODID + "stamina"));
		instance.setMaxStamina(tag.getFloat(RPGMain.MODID + "staminaMax"));
	}
}
