package com.bogdan.qol.Features.Tests;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.utils.GuiUtils;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.vecmath.Vector3d;
import java.util.ArrayList;
import java.util.Iterator;

public class GuiTest {

    private static final ArrayList blocks = new ArrayList();

    public static void append(Vector3d var0) {
        synchronized (blocks) {
            blocks.add(var0);
        }
    }

    public static void clear() {
        synchronized (blocks) {
            blocks.clear();
        }
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent var1) {
        if (Configs.DevTracing) {
            GuiUtils.enableESP();
            synchronized (blocks) {
                Iterator var3 = blocks.iterator();

                while (true) {
                    if (!var3.hasNext()) {
                        break;
                    }

                    Vector3d var4 = (Vector3d) var3.next();
                    GuiUtils.drawBoxAtPos((float) var4.x, (float) var4.y, (float) var4.z, 100, 160, 240, 255, 0.1F, 0.1F, 0.0F);
                }
            }

            GuiUtils.disableESP();
        }
    }
}
