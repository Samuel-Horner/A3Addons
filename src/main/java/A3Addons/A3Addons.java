package A3Addons;

import A3Addons.commands.*;
import A3Addons.config.*;
import A3Addons.hud.DvdHud;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

@Mod(modid = A3Addons.MODID, version = A3Addons.VERSION)
public class A3Addons
{
    public static final String MODID = "A3Addons";
    public static final String VERSION = "1.0";

    public static boolean dvdEnabled = false;
    
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

        // Register HUD
        MinecraftForge.EVENT_BUS.register(new DvdHud());

        // Done!
        System.out.println("Loaded A3Addons");
    }
}
