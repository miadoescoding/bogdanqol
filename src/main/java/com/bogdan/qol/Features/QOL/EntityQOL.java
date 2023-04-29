package com.bogdan.qol.Features.QOL;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.TickEndEvent;
import com.bogdan.qol.Features.Miscellaneous.Velocity;
import com.bogdan.qol.utils.EntityUtils;
import com.bogdan.qol.utils.MathUtils;
import com.bogdan.qol.utils.MinecraftUtils;
import com.bogdan.qol.utils.SkyblockUtils;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Iterator;
import java.util.List;

public class EntityQOL {

    private static int count;

    public static int getHiddenEntityCount() {
        return count;
    }

    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent var1) {
    }

    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if (true) {
            if (!SkyblockUtils.isInDungeon()) {
                if (MinecraftUtils.getWorld() != null) {
                    List var2 = EntityUtils.getEntities();
                    count = 0;
                    Iterator var3 = var2.iterator();

                    while (var3.hasNext()) {
                        Entity var4 = (Entity) var3.next();
                        if (EntityUtils.isPlayer(var4)) {
                            Velocity.onPlayerNearby(var4);
                            if (Configs.HidePlayers && MathUtils.distanceSquareFromPlayer(var4) <= (double) (Configs.HidePlayerRadius * Configs.HidePlayerRadius)) {
                                var4.posY = var4.lastTickPosY = 9999.0;
                            }
                        } else if (EntityUtils.isSummon(var4)) {
                            if (Configs.HideSummons) {
                                var4.posY = var4.lastTickPosY = 9999.0;
                            }
                        } else if (EntityUtils.isGoldenFish(var4)) {
                            if (Configs.HideGoldenFish) {
                                var4.posY = var4.lastTickPosY = 9999.0;
                            }
                        } else if (EntityUtils.isEmptyArmorStand(var4) && Configs.HideEmptyArmorStand) {
                            var4.posY = var4.lastTickPosY = 9999.0;
                            ++count;
                        }
                    }

                }
            }
        }
    }

    @SubscribeEvent
    public void onRenderEntity(RenderLivingEvent.Pre var1) {
    }
}
