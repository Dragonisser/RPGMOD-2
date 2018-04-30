package de.prwh.rpg.handler.command;

import java.util.ArrayList;
import java.util.List;

import de.prwh.rpg.capabilities.player.RpgPlayer;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class RaceCommand implements ICommand {

	private List<String> aliases;

	public RaceCommand() {
		this.aliases = new ArrayList<String>();
		this.aliases.add("r");
	}

	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public String getName() {
		return "race";
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
		case "set":
			setRace(player, args[1]);
			break;
		case "get":
			getRace(player);
			break;
		}

	}

	private void getRace(EntityPlayer player) {
		RpgPlayer rpgPlayer = new RpgPlayer(player);
		player.sendMessage(new TextComponentString(String.format("Your race is: " + rpgPlayer.getRpgRace().getRpgRaceName())));
	}

	private void setRace(EntityPlayer player, String string) {
		RpgPlayer rpgPlayer = new RpgPlayer(player);

		rpgPlayer.getRpgRaceInfo().setRpgRace(string);

		rpgPlayer.syncValuesMax();
		rpgPlayer.setMaxStats();
		rpgPlayer.syncValues();

		player.sendMessage(new TextComponentString(String.format("Your race is: " + rpgPlayer.getRpgRace().getRpgRaceName())));
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
