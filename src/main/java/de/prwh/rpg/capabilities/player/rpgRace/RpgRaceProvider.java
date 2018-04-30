package de.prwh.rpg.capabilities.player.rpgRace;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class RpgRaceProvider implements ICapabilitySerializable<NBTBase> {
	
	@CapabilityInject(IRpgRace.class)
	public static final Capability<IRpgRace> RPGRACE_CAP = null;

	private IRpgRace instance = RPGRACE_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == RPGRACE_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == RPGRACE_CAP ? RPGRACE_CAP.<T>cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return RPGRACE_CAP.getStorage().writeNBT(RPGRACE_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		RPGRACE_CAP.getStorage().readNBT(RPGRACE_CAP, this.instance, null, nbt);
	}
}