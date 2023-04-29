package com.bogdan.qol.Features.QOL;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.TickEndEvent;
import com.bogdan.qol.utils.EntityUtils;
import com.bogdan.qol.utils.MinecraftUtils;
import com.bogdan.qol.utils.SkyblockUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Iterator;

public class HideCreepers {
    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if (true) {
            if (Configs.HideCreepers) {
                if (MinecraftUtils.getWorld() != null) {
                    if (!SkyblockUtils.isInDungeon()) {
                        Iterator var2 = EntityUtils.getEntities().iterator();

                        while (var2.hasNext()) {
                            Entity var3 = (Entity) var2.next();
                            if (var3 instanceof EntityCreeper) {
                                var3.posY = var3.lastTickPosY = 9999.0;
                            }
                        }

                    }
                }
            }
        }
    }
}
