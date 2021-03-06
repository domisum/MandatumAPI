package de.domisum.lib.mandatum;

import de.domisum.lib.auxilium.util.java.annotations.API;
import de.domisum.lib.mandatum.exe.MandatumCommandExecutor;
import de.domisum.lib.mandatum.exe.MandatumCommandRegisterer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class MandatumLib
{

	// REFERENCES
	private static MandatumLib instance;
	private JavaPlugin plugin;

	private MandatumCommandExecutor commandExecutor;


	// CONSTRUCTOR
	private MandatumLib(JavaPlugin plugin)
	{
		this.plugin = plugin;

		onEnable();
	}

	@API public static void enable(JavaPlugin plugin)
	{
		if(instance != null)
			return;

		instance = new MandatumLib(plugin);
	}

	@API public static void disable()
	{
		if(instance == null)
			return;

		getInstance().onDisable();
		instance = null;
	}

	private void onEnable()
	{
		this.commandExecutor = new MandatumCommandExecutor();

		getLogger().info(this.getClass().getSimpleName()+" has been enabled");
	}

	private void onDisable()
	{
		getLogger().info(this.getClass().getSimpleName()+" has been disabled");
	}


	// GETTERS
	public static MandatumLib getInstance()
	{
		return instance;
	}

	public JavaPlugin getPlugin()
	{
		return this.plugin;
	}

	public static Logger getLogger()
	{
		return getInstance().plugin.getLogger();
	}


	public static MandatumCommandExecutor getCommandExecutor()
	{
		return getInstance().commandExecutor;
	}


	// REGISTERING
	@API public static void registerCommands(String classPath)
	{
		MandatumCommandRegisterer registerer = new MandatumCommandRegisterer(classPath);
		getInstance().commandExecutor.addCommandClasses(registerer.getCommandClasses());
	}

}
