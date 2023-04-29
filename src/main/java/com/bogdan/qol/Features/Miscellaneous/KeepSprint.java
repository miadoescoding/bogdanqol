package com.bogdan.qol.Features.Miscellaneous;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.TickEndEvent;
import com.bogdan.qol.utils.ControlUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class KeepSprint {
    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if (true) {
            if (Configs.KeepSprint) {
                ControlUtils.holdSprint();
            }
        }
    }
}
