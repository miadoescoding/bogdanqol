package com.bogdan.qol.Features.Dragons;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Features.RenderEntityESP;
import com.bogdan.qol.Objects.EntityInfo;
import com.bogdan.qol.utils.SkyblockUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;

import java.util.HashMap;

public class EnderCrystalESP extends RenderEntityESP {

    public static final String ENDERCRYSTAL_STRING = "Crystal";

    public boolean enabled() {
        return SkyblockUtils.isInDragon();
    }

    public boolean shouldRenderESP(EntityInfo var1) {
        return true;
    }

    public boolean shouldDrawString(EntityInfo var1) {
        return true;
    }

    public EntityInfo getEntityInfo(Entity var1) {
        if (var1 instanceof EntityEnderCrystal && Configs.CrystalESP) {
            HashMap var2 = new HashMap();
            var2.put("entity", var1);
            var2.put("r", 255);
            var2.put("g", 0);
            var2.put("b", 0);
            var2.put("drawString", EntityInfo.EnumDraw.DRAW_KIND);
            var2.put("kind", "Crystal");
            var2.put("fontColor", 14423100);
            var2.put("isFilled", true);
            var2.put("isESP", true);
            return new EntityInfo(var2);
        } else {
            return null;
        }
    }
}
