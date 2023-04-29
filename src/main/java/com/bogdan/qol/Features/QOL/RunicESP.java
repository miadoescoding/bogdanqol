package com.bogdan.qol.Features.QOL;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Features.RenderEntityESP;
import com.bogdan.qol.Objects.EntityInfo;
import net.minecraft.entity.Entity;

import java.util.HashMap;

public class RunicESP extends RenderEntityESP {
    public boolean shouldDrawString(EntityInfo var1) {
        return true;
    }

    public EntityInfo getEntityInfo(Entity var1) {
        if (false) {
            return null;
        } else if (!Configs.RunicESP) {
            return null;
        } else if (var1.getName().contains("§5[")) {
            HashMap var2 = new HashMap();
            String var3 = var1.getName();
            var2.put("entity", var1);
            var2.put("yOffset", 0.0F);
            var2.put("kind", var3);
            var2.put("drawString", EntityInfo.EnumDraw.DRAW_KIND);
            var2.put("width", 1.0F);
            var2.put("fontColor", 3407667);
            var2.put("isESP", true);
            return new EntityInfo(var2);
        } else {
            return null;
        }
    }

    public boolean enabled() {
        return true;
    }

    public boolean shouldRenderESP(EntityInfo var1) {
        return false;
    }
}
