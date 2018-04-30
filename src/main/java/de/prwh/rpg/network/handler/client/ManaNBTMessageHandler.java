package de.prwh.rpg.network.handler.client;

import de.prwh.rpg.RPGMain;
import de.prwh.rpg.capabilities.mana.IMana;
import de.prwh.rpg.capabilities.mana.ManaProvider;
import de.prwh.rpg.network.message.ManaNBTMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ManaNBTMessageHandler extends AbstractClientMessageHandler<ManaNBTMessage> {
	@Override
	public IMessage handleClientMessage(EntityPlayer player, ManaNBTMessage message, MessageContext ctx) {
		//RPGMain.getLogger().info("Trying to synced Mana");
		if (player != null) {
			IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);
			final IMana message_mana = message.getValue();
			try {	
				if (mana != null && message_mana != null) {
					mana.setMana(message_mana.getMana());
					mana.setMaxMana(message_mana.getMaxMana());
					//RPGMain.getLogger().info("Mana synced");
				} else {
					RPGMain.getLogger().info("Mana synced failed.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}