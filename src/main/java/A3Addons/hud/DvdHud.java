package A3Addons.hud;

import A3Addons.A3Addons;
import A3Addons.config.ConfigHandler;

import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.functions.ModNameFunction;

public class DvdHud {

    private long last_rendered;

    private float x;
    private float y;

    private float dirX;
    private float dirY;

    private static boolean rendering = false;

    public void newStartPos(ScaledResolution resolution) {
        final int screenBottom = resolution.getScaledHeight();
        final int screenRight = resolution.getScaledWidth();

        final FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;

        x = (float) Math.floor(Math.random() * (screenRight - fr.getStringWidth(ConfigHandler.dvdStr)));
        y = (float) Math.floor(Math.random() * (screenBottom - fr.FONT_HEIGHT));

        dirX = (float) screenRight / 240;
        dirY = (float) screenBottom / 240;

        if (ThreadLocalRandom.current().nextBoolean()) {dirX *= -1;}
        if (ThreadLocalRandom.current().nextBoolean()) {dirY *= -1;}
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        // this method is called multiple times per frame, you want to filter it
        // by checking the event type to only render your HUD once per frame
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            if (rendering && A3Addons.dvdEnabled){
                this.drawHUD(event.resolution);
            } else if (A3Addons.dvdEnabled) {
                rendering = true;
                newStartPos(event.resolution);
                this.drawHUD(event.resolution);
            } else {
                rendering = false;
            }
        }
    }

    private void drawHUD(ScaledResolution resolution) {
        final long now = Minecraft.getSystemTime();

        // for example here we draw text on the screen
        final FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;

        updatePos(resolution, fr.getStringWidth(ConfigHandler.dvdStr), fr.FONT_HEIGHT, now - last_rendered);

        fr.drawStringWithShadow(ConfigHandler.dvdStr, x, y, 0xFFFFFF);

        last_rendered = now;
    }

    private void updatePos(ScaledResolution resolution, int width, int height, long delta) {
        final int screenBottom = resolution.getScaledHeight();
        final int screenRight = resolution.getScaledWidth();
        final boolean xEdge = screenRight - width < x || x < 0;
        final boolean yEdge = screenBottom - height < y || y < 0;

        if (xEdge && yEdge) {
            // CORNER!!!
            dirX *= -1;
            dirY *= -1;
            Minecraft.getMinecraft().ingameGUI.displayTitle("§6CORNER!!!", ConfigHandler.dvdStr,  5, 10, 5);

        } else if (xEdge) {
            dirX *= -1;
        } else if (yEdge) {
            dirY *= -1;
        }

        x += dirX;
        y += dirY;
    }
}