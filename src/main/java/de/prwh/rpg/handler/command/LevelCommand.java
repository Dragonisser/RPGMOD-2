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

public class LevelCommand implements ICommand {

	private List<String> aliases;

	public LevelCommand() {
		this.aliases = new ArrayList<String>();
		this.aliases.add("l");
	}

	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public String getName() {
		return "level";
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
		case "setexp": setEXP(player, args[1]);
		break;
		case "addexp": addEXP(player, args[1]);
		break;
		case "setlevel": setLevel(player, args[1]);
		break;
		case "getexp": getEXP(player);
		break;
		case "getlevel": getLevel(player);
		break;
		}

	}

	private void setEXP(EntityPlayer player, String string) {
		RpgPlayer rpgPlayer = new RpgPlayer(player);
		if(StringUtils.isNumeric(string)) {
			rpgPlayer.getRpgLevel().setExperience(Integer.parseInt(string), rpgPlayer);
		}	
		player.sendMessage(new TextComponentString(String.format("You have §7%d§r exp.", (int) rpgPlayer.getRpgLevel().getExperience())));
	}
	
	private void addEXP(EntityPlayer player, String string) {
		RpgPlayer rpgPlayer = new RpgPlayer(player);
		if(StringUtils.isNumeric(string)) {
			rpgPlayer.getRpgLevel().setExperience(Integer.parseInt(string), rpgPlayer);
		}	
		player.sendMessage(new TextComponentString(String.format("You have §7%d§r exp.", (int) rpgPlayer.getRpgLevel().getExperience())));
	}

	private void setLevel(EntityPlayer player, String string) {
		RpgPlayer rpgPlayer = new RpgPlayer(player);
		if (StringUtils.isNumeric(string)) {
			rpgPlayer.getRpgLevel().setLevel(Integer.parseInt(string));
		}
		player.sendMessage(new TextComponentString(String.format("You are level §7%d§r.", (int) rpgPlayer.getRpgLevel().getLevel())));
	}
	
	private void getEXP(EntityPlayer player) {
		RpgPlayer rpgPlayer = new RpgPlayer(player);
		player.sendMessage(new TextComponentString(String.format("You have §7%d§r exp.", (int) rpgPlayer.getRpgLevel().getExperience())));
	}
	
	private void getLevel(EntityPlayer player) {
		RpgPlayer rpgPlayer = new RpgPlayer(player);
		player.sendMessage(new TextComponentString(String.format("You are level §7%d§r.", (int) rpgPlayer.getRpgLevel().getLevel())));
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
