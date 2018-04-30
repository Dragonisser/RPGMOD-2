package de.prwh.rpg.proxy;

import java.util.Map;

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
import de.prwh.rpg.gui.screen.GuiHealthBar;
import de.prwh.rpg.gui.screen.GuiManaBar;
import de.prwh.rpg.gui.screen.GuiStaminaBar;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientProxyRPG extends CommonProxyRPG {

	public void AddRenderer(Map<?, ?> map) {
	}

	public void init() {

		CapabilityManager.INSTANCE.register(IMana.class, new ManaStorage(), Mana.class);
		CapabilityManager.INSTANCE.register(IStamina.class, new StaminaStorage(), Stamina.class);
		CapabilityManager.INSTANCE.register(IHealth.class, new HealthStorage(), Health.class);
		CapabilityManager.INSTANCE.register(IRpgClass.class, new RpgClassStorage(), RpgClass.class);
		CapabilityManager.INSTANCE.register(ILevel.class, new LevelStorage(), Level.class);
		CapabilityManager.INSTANCE.register(IRpgRace.class, new RpgRaceStorage(), RpgRace.class);

		System.out.println("[RPGMOD] Trying to load Capabilities CLIENTSIDE");
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());

		MinecraftForge.EVENT_BUS.register(new GuiManaBar(Minecraft.getMinecraft()));
		MinecraftForge.EVENT_BUS.register(new GuiStaminaBar(Minecraft.getMinecraft()));
		MinecraftForge.EVENT_BUS.register(new GuiHealthBar(Minecraft.getMinecraft()));
	}

	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		// Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
		// your packets will not work as expected because you will be getting a
		// client player even when you are on the server!
		// Sounds absurd, but it's true.

		// Solution is to double-check side before returning the player:
		return (ctx.side.isClient() ? Minecraft.getMinecraft().player : super.getPlayerEntity(ctx));
	}
}