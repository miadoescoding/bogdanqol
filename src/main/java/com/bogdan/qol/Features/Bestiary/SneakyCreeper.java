package com.bogdan.qol.Features.Bestiary;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Features.RenderEntityESP;
import com.bogdan.qol.Objects.EntityInfo;
import com.bogdan.qol.utils.SkyblockUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;

import java.util.HashMap;

public class SneakyCreeper extends RenderEntityESP {

    private static final String SNEAKYCREEPER_STRING = "Creeper";

    public boolean enabled() {
        return SkyblockUtils.isInGunpowderMines();
    }

    public boolean shouldDrawString(EntityInfo var1) {
        return Configs.SneakyCreeperDisplayName;
    }

    public boolean shouldRenderESP(EntityInfo var1) {
        return Configs.SneakyCreeperDisplayBox;
    }

    public EntityInfo getEntityInfo(Entity var1) {
        if (!(var1 instanceof EntityCreeper)) {
            return null;
        } else {
            HashMap var2 = new HashMap();
            var2.put("entity", var1);
            var2.put("width", 0.4F);
            var2.put("height", 1.85F);
            var2.put("drawString", EntityInfo.EnumDraw.DRAW_KIND);
            var2.put("kind", "Creeper");
            var2.put("fontColor", 3407667);
            return new EntityInfo(var2);
        }
    }
}
