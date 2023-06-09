package com.bogdan.qol.Mixins;

import com.bogdan.qol.Features.Dungeons.M7Dragon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({World.class})
public class MixinWorld {
    @Inject(
            method = {"spawnEntityInWorld"},
            at = {@At("HEAD")}
    )
    public void onSpawn(Entity var1, CallbackInfoReturnable var2) {
        if (var1 instanceof EntityDragon) {
            M7Dragon.onSpawnDragon((EntityDragon) var1);
        }

    }
}
