package com.bogdan.qol.Features.Dungeons;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Features.Dungeons.Map.Dungeon;
import com.bogdan.qol.utils.CommandsUtils;
import com.bogdan.qol.utils.SkyblockUtils;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MimicWarn {
    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent var1) {
        if (true) {
            if (SkyblockUtils.isInDungeon()) {
                if (var1.entity instanceof EntityZombie && ((EntityZombie) var1.entity).isChild()) {
                    if (Configs.MimicWarn) {
                        CommandsUtils.addCommand("/pc Mimic dead!");
                    }

                    Dungeon.isMimicDead = true;
                }

            }
        }
    }
}
