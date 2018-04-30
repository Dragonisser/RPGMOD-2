package de.prwh.rpg.proxy;

import de.prwh.rpg.capabilities.CapabilityHandler;
import de.prwh.rpg.capabilities.health.Health;
import de.prwh.rpg.capabilities.health.HealthStorage;
import de.prwh.rpg.capabilities.health.IHealth;
import de.prwh.rpg.capabilities.level.ILevel;
import de.prwh.rpg.capabilities.level.Level;
import de.prwh.rpg.capabilities.level.LevelStorage;
import de.prwh.rpg.capabilities.mana.IMana;
import de.prwh.rpg.capabilities.mana.Mana;
import de.prwh.rpg.capabilities.mana.ManaStorage;
import de.prwh.rpg.capabilities.player.rpgClass.IRpgClass;
import de.prwh.rpg.capabilities.player.rpgClass.RpgClass;
import de.prwh.rpg.capabilities.player.rpgClass.RpgClassStorage;
import de.prwh.rpg.capabilities.player.rpgRace.IRpgRace;
import de.prwh.rpg.capabilities.player.rpgRace.RpgRace;
import de.prwh.rpg.capabilities.player.rpgRace.RpgRaceStorage;
import de.prwh.rpg.capabilities.stamina.IStamina;
import de.prwh.rpg.capabilities.stamina.Stamina;
import de.prwh.rpg.capabilities.stamina.StaminaStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxyRPG {

	public void init() {

		CapabilityManager.INSTANCE.register(IMana.class, new ManaStorage(), Mana.class);
		CapabilityManager.INSTANCE.register(IStamina.class, new StaminaStorage(), Stamina.class);
		CapabilityManager.INSTANCE.register(IHealth.class, new HealthStorage(), Health.class);
		CapabilityManager.INSTANCE.register(IRpgClass.class, new RpgClassStorage(), RpgClass.class);
		CapabilityManager.INSTANCE.register(ILevel.class, new LevelStorage(), Level.class);
		CapabilityManager.INSTANCE.register(IRpgRace.class, new RpgRaceStorage(), RpgRace.class);


		System.out.println("[RPGMOD] Trying to load Capabilities SERVERSIDE");
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());

	}

	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return ctx.getServerHandler().player;
	}
}