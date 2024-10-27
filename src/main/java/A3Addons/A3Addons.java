package A3Addons;

import A3Addons.commands.*;
import A3Addons.config.*;
// import A3Addons.util.*;
import A3Addons.hud.*;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

import java.util.ArrayList;
import java.util.List;

@Mod(modid = A3Addons.MODID, version = A3Addons.VERSION)
public class A3Addons
{
    public static final String MODID = "A3Addons";
    public static final String VERSION = "1.0";

    public static boolean dvdEnabled = false;
    public static List<String> vanqList = new ArrayList<String>();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // loads the config file from the disk
        ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Register Command
        ClientCommandHandler.instance.registerCommand(new DvdCommand());
        ClientCommandHandler.instance.registerCommand(new VanqWarpCommand());

        // Register HUD
        MinecraftForge.EVENT_BUS.register(new DvdHud());

        // Done!
        System.out.println("Loaded A3Addons");
    }
}
