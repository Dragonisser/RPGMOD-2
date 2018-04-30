package de.prwh.rpg.capabilities.player.rpgClass;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class RpgClassProvider implements ICapabilitySerializable<NBTBase> {
	
	@CapabilityInject(IRpgClass.class)
	public static final Capability<IRpgClass> RPGCLASS_CAP = null;

	private IRpgClass instance = RPGCLASS_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == RPGCLASS_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == RPGCLASS_CAP ? RPGCLASS_CAP.<T>cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return RPGCLASS_CAP.getStorage().writeNBT(RPGCLASS_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		RPGCLASS_CAP.getStorage().readNBT(RPGCLASS_CAP, this.instance, null, nbt);
	}
}