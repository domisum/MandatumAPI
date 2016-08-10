package de.domisum.mandatumapi.cmd;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.domisum.mandatumapi.MandatumAPI;

public abstract class MandatumCommand
{

	// REFERENCES
	protected Player sender;
	protected List<String> args;
	protected String argumentSequenceName;
	// this is not set in the constructor, since it will be determined after this object has been constructed


	// -------
	// CONSTRUCTOR
	// -------
	public MandatumCommand()
	{

	}

	public MandatumCommand(CommandSender commandSender, List<String> args)
	{
		if(commandSender instanceof Player)
			this.sender = (Player) commandSender;

		this.args = args;
	}


	// -------
	// GETTERS
	// -------
	public abstract String getName();

	public abstract boolean canBeRunByConsole();

	public abstract String getRequiredPermissionNode();

	public abstract String getUsage();

	public abstract List<ArgumentSequence> getArgumentSequences();


	public Player getSender()
	{
		return this.sender;
	}

	protected String getSenderName()
	{
		return this.sender != null ? ("'"+this.sender.getName()+"'") : "the console";
	}


	protected String getMessage(int startArgsIndex)
	{
		String message = "";
		for(int i = startArgsIndex; i < this.args.size(); i++)
			message += this.args.get(i)+((i+1) != this.args.size() ? " " : "");

		return message;
	}


	// -------
	// SETTERS
	// -------
	public void setArgumentSequenceName(String argumentSequenceName)
	{
		this.argumentSequenceName = argumentSequenceName;
	}


	// -------
	// COMMUNICATION
	// -------
	public void sendMessage(String message)
	{
		if(this.sender != null)
			this.sender.sendMessage(message);
		else
			// send to console
			MandatumAPI.getLogger().info(message);
	}

	public void sendUsageMessage()
	{
		sendMessage("The command format is wrong: "+getUsage());
	}


	// -------
	// EXECUTION
	// -------
	public abstract void execute();

}
