package com.bogdan.qol.Features.Tests;

import com.bogdan.qol.Events.TickEndEvent;
import com.bogdan.qol.utils.ControlUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClickTest {

    private static boolean enabled = false;

    public static void setEnabled() {
        enabled = true;
    }

    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if (enabled) {
            enabled = false;
            (new Thread(() -> {
                try {
                    while (ControlUtils.getOpenedInventory() == null || !ControlUtils.getOpenedInventory().getName().equals("Large Chest")) {
                        Thread.sleep(5L);
                    }

                    ControlUtils.getOpenedInventory().click(54, true, "LEFT");
                    Thread.sleep(500L);
                    ControlUtils.getOpenedInventory().click(55, true, "LEFT");
                    Thread.sleep(500L);
                } catch (Exception var1) {
                    var1.printStackTrace();
                }

            })).start();
        }

    }
}
