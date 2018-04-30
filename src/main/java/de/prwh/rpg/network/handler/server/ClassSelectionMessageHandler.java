package de.prwh.rpg.network.handler.server;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.network.message.ClassSelectionMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClassSelectionMessageHandler extends AbstractServerMessageHandler<ClassSelectionMessage> {

	@Override
	public IMessage handleServerMessage(EntityPlayer player, ClassSelectionMessage message, MessageContext ctx) {

		// RpgPlayer rplayer = RpgPlayer.get(player);
		// rplayer.setRpgClass(ClassFactory.getInstance().createClass(message.getClassname()));

		// if (rplayer.getRpgRace() == null) {
		//
		// EntityPlayerMP playerMP = (EntityPlayerMP) player;
		//
		// OpenGuiMessage message1 = new OpenGuiMessage(1);
		// RPGPacketDispatcher.sendTo(message1, playerMP);
		// RPGMain.getLogger().info("Sending race selection to gui");
		// }

		/*
		 * TODO Build a working framework around the changes and synchronization
		 * 
		 * {@link RpgPlayer#syncWithClient()}
		 */
		// rplayer.syncWithClient();
		// rplayer.updateAllStats();

		// ClassSelectEvent event = new ClassSelectEvent(rplayer,
		// rplayer.getRpgClass());
		// if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) {
		// return null;
		// }

		RPGMain.getLogger().info("Class successfully chosen");
		return null;
	}
}
