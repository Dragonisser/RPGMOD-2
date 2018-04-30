package de.prwh.rpg.capabilities.health;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * Health provider
 *
 * This class is responsible for providing a capability. Other modders may
 * attach their own provider with implementation that returns another
 * implementation of IMana to the target's (Entity, TE, ItemStack, etc.)
 * disposal.
 */
public class HealthProvider implements ICapabilitySerializable<NBTBase> {
	@CapabilityInject(IHealth.class)
	public static final Capability<IHealth> HEALTH_CAP = null;

	private IHealth instance = HEALTH_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == HEALTH_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == HEALTH_CAP ? HEALTH_CAP.<T>cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return HEALTH_CAP.getStorage().writeNBT(HEALTH_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		HEALTH_CAP.getStorage().readNBT(HEALTH_CAP, this.instance, null, nbt);
	}
}