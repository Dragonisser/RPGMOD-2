package de.prwh.rpg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.prwh.rpg.handler.command.ClassCommand;
import de.prwh.rpg.handler.command.HealthCommand;
import de.prwh.rpg.handler.command.LevelCommand;
import de.prwh.rpg.handler.command.ManaCommand;
import de.prwh.rpg.handler.command.RaceCommand;
import de.prwh.rpg.handler.command.StaminaCommand;
import de.prwh.rpg.handler.event.EventHandlerList;
import de.prwh.rpg.network.RPGPacketDispatcher;
import de.prwh.rpg.proxy.CommonProxyRPG;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = RPGMain.MODID, version = RPGMain.VERSION)
public class RPGMain {
	public static final String MODID = "rpgmod";
	public static final String VERSION = "0.1";
	public static final String PROXY_CLIENT = "de.prwh.rpg.proxy.ClientProxyRPG";
	public static final String PROXY_SERVER = "de.prwh.rpg.proxy.CommonProxyRPG";

	private static final Logger log = LogManager.getLogger(MODID.toUpperCase());

	@SidedProxy(clientSide = PROXY_CLIENT, serverSide = PROXY_SERVER)
	public static CommonProxyRPG proxy;

	@Instance(MODID)
	public static RPGMain instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println("[RPGMOD] Loading preInit");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		System.out.println("[RPGMOD] Loading Init");
		proxy.init();
		RPGPacketDispatcher.registerPackets();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println("[RPGMOD] Loading postInit");
		EventHandlerList.register();
		System.out.println("[RPGMOD] Loaded successfully");
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new ManaCommand());
		event.registerServerCommand(new StaminaCommand());
		event.registerServerCommand(new HealthCommand());
		event.registerServerCommand(new RaceCommand());
		event.registerServerCommand(new ClassCommand());
		// event.registerServerCommand(new GameModeCommand());
		event.registerServerCommand(new LevelCommand());
	}

	public static Logger getLogger() {
		return log;
	}
}
