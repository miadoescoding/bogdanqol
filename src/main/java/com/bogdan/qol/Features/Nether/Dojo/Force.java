package com.bogdan.qol.Features.Nether.Dojo;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.LeftClickEvent;
import com.bogdan.qol.bogdanqol;
import com.bogdan.qol.utils.ChatLib;
import com.bogdan.qol.utils.MathUtils;
import com.bogdan.qol.utils.MinecraftUtils;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Iterator;
import java.util.List;

public class Force {
    @SubscribeEvent
    public void onLeftClick(LeftClickEvent var1) {
        if (true) {
            if (Configs.ForceHelper) {
                if (DojoUtils.getTask() == EnumDojoTask.FORCE) {
                    MovingObjectPosition var2 = bogdanqol.mc.objectMouseOver;
                    Entity var3 = var2.entityHit;
                    if (var3 != null) {
                        double var4 = MathUtils.getX(var3);
                        double var6 = MathUtils.getY(var3);
                        double var8 = MathUtils.getZ(var3);
                        AxisAlignedBB var10 = (new AxisAlignedBB(var4, var6, var8, var4, var6, var8)).expand(0.2, 0.0, 0.2).addCoord(0.0, 2.5, 0.0);
                        List var11 = MinecraftUtils.getWorld().getEntitiesWithinAABBExcludingEntity(var3, var10);
                        Iterator var12 = var11.iterator();

                        while (var12.hasNext()) {
                            Entity var13 = (Entity) var12.next();
                            if (var13.hasCustomName()) {
                                String var14 = ChatLib.removeFormatting(var13.getCustomNameTag());
                                ChatLib.debug(var14);
                                if (var14.contains("-")) {
                                    var1.setCanceled(true);
                                    ChatLib.chat("Blocked negative mob hit in Force Challenge.");
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}
