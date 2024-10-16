package A3Addons;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = A3Addons.MODID, version = A3Addons.VERSION)
public class A3Addons
{
    public static final String MODID = "A3Addons";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println("Loaded A3Addons");
    }
}
