package de.prwh.rpg.network.handler.server;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.player.RpgPlayer;
import de.prwh.rpg.network.message.deprecated.RequestSynchronizationMessage;
import de.prwh.rpg.network.message.deprecated.RpgPlayerModelMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class RequestSynchronizationMessageHandler extends AbstractServerMessageHandler<RequestSynchronizationMessage> {

	@Override
	public IMessage handleServerMessage(EntityPlayer player, RequestSynchronizationMessage message, MessageContext ctx) {
		RpgPlayer rpg = new RpgPlayer((EntityPlayerMP) player);
		RpgPlayerModelMessage back = new RpgPlayerModelMessage(rpg);
		RPGMain.getLogger().info("RpgPlayer send to " + player);
		return back;
	}
}
