package de.prwh.rpg.capabilities;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.health.HealthProvider;
import de.prwh.rpg.capabilities.level.LevelProvider;
import de.prwh.rpg.capabilities.mana.ManaProvider;
import de.prwh.rpg.capabilities.player.rpgClass.RpgClassProvider;
import de.prwh.rpg.capabilities.player.rpgRace.RpgRaceProvider;
import de.prwh.rpg.capabilities.stamina.StaminaProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Capability handler
 * 
 * This class is responsible for attaching our capabilities
 */
public class CapabilityHandler {
	
	public CapabilityHandler() {
		System.out.println("CAPABILITIES LOADING");
	}
	
	public static final ResourceLocation MANA_CAP = new ResourceLocation(RPGMain.MODID, "mana");
	public static final ResourceLocation STAMINA_CAP = new ResourceLocation(RPGMain.MODID, "stamina");
	public static final ResourceLocation HEALTH_CAP = new ResourceLocation(RPGMain.MODID, "health");
	public static final ResourceLocation RPGCLASS_CAP = new ResourceLocation(RPGMain.MODID, "rpgclass");
	public static final ResourceLocation RPGRACE_CAP = new ResourceLocation(RPGMain.MODID, "rpgrace");
	public static final ResourceLocation RPGLEVEL_CAP = new ResourceLocation(RPGMain.MODID, "rpglevel");
	
	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent.Entity event) {
		if (!(event.getEntity() instanceof EntityPlayer))
			return;

		event.addCapability(MANA_CAP, new ManaProvider());
		event.addCapability(STAMINA_CAP, new StaminaProvider());
		event.addCapability(HEALTH_CAP, new HealthProvider());
		event.addCapability(RPGCLASS_CAP, new RpgClassProvider());
		event.addCapability(RPGLEVEL_CAP, new LevelProvider());
		event.addCapability(RPGLEVEL_CAP, new RpgRaceProvider());
		
		System.out.println("CAPABILITIES LOADED");
		System.out.println(event.getCapabilities());
	}
}