package de.prwh.rpg.handler.event;

import de.prwh.rpg.handler.event.minecraft.EventHandler;
import de.prwh.rpg.handler.event.minecraft.PlayerExpPickUpHandler;
import de.prwh.rpg.handler.event.rpg.LevelUpHandler;
import de.prwh.rpg.handler.event.rpg.ManaRefillHandler;
import de.prwh.rpg.handler.event.rpg.StaminaSprintHandler;
import net.minecraftforge.common.MinecraftForge;

public class EventHandlerList {

	public static void register() {
		/*
		 * Event handling
		 */
		// NetworkRegistry.INSTANCE.registerGuiHandler(RPGMain.instance, new
		// GuiHandler());
		// MinecraftForge.EVENT_BUS.register(new PlayerTickHandler());
		// MinecraftForge.EVENT_BUS.register(new PlayerLogOutHandler());
		// MinecraftForge.EVENT_BUS.register(new PlayerLogInHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerExpPickUpHandler());
		// MinecraftForge.EVENT_BUS.register(new EntityConstructingHandler());
		/*
		 * Rpg Event Handling
		 */
		MinecraftForge.EVENT_BUS.register(new LevelUpHandler());
		MinecraftForge.EVENT_BUS.register(new StaminaSprintHandler());
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		// MinecraftForge.EVENT_BUS.register(new ManaUseHandler());
		MinecraftForge.EVENT_BUS.register(new ManaRefillHandler());
		// MinecraftForge.EVENT_BUS.register(new StaminaUseHandler());
		// MinecraftForge.EVENT_BUS.register(new PlayerSaveHandler());
		// MinecraftForge.EVENT_BUS.register(new PassiveSkillSelectHandler());
	}
}