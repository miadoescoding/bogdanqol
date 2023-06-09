package com.bogdan.qol.Features.QOL;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.CheckEntityRenderEvent;
import com.bogdan.qol.utils.SkyblockUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DisableEntityRender {

    @SubscribeEvent
    public void onRenderEntity(CheckEntityRenderEvent var1) {
        if (true) {
            if (SkyblockUtils.isInSkyblock()) {
                Entity var2 = var1.entity;
                if (Configs.DisableDying && var2 instanceof EntityLivingBase && (((EntityLivingBase) var1.entity).getHealth() <= 0.0F || var2.isDead)) {
                    var1.setCanceled(true);
                }

            }
        }
    }
}
