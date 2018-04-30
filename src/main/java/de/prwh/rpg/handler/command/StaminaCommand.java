package de.prwh.rpg.handler.command;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import de.prwh.rpg.capabilities.player.RpgPlayer;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class StaminaCommand implements ICommand {

	private List<String> aliases;

	public StaminaCommand() {
		this.aliases = new ArrayList<String>();
		this.aliases.add("s");
	}

	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public String getName() {
		return "stamina";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "";
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		if (!(sender instanceof EntityPlayerMP)) {
			return;
		}

		EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();

		switch (args[0]) {
		case "set": setStamina(player, args[1]);
		break;
		case "setmax": setMaxStamina(player, args[1]);
		break;
		case "get": getStamina(player);
		break;
		}

	}

	private void setMaxStamina(EntityPlayer player, String string) {
		RpgPlayer rpgPlayer = new RpgPlayer(player);
		if(StringUtils.isNumeric(string)) {
			rpgPlayer.setMaxStamina(Integer.parseInt(string));
		}	
		player.sendMessage(new TextComponentString(String.format("You have §7%d§r max stamina.", (int) rpgPlayer.getMaxStamina())));	
	}

	private void getStamina(EntityPlayer player) {
		RpgPlayer rpgPlayer = new RpgPlayer(player);
		player.sendMessage(new TextComponentString(String.format("You have §7%d§r stamina left.", (int) rpgPlayer.getStamina())));
	}

	private void setStamina(EntityPlayer player, String string) {
		RpgPlayer rpgPlayer = new RpgPlayer(player);
		if (StringUtils.isNumeric(string)) {
			rpgPlayer.setStamina(Integer.parseInt(string));
		}
		player.sendMessage(new TextComponentString(String.format("You have §7%d§r stamina left.", (int) rpgPlayer.getStamina())));
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

}
