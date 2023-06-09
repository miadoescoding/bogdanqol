package com.bogdan.qol.Features.Nether;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Features.RenderEntityESP;
import com.bogdan.qol.Objects.EntityInfo;
import com.bogdan.qol.utils.EntityUtils;
import com.bogdan.qol.utils.SkyblockUtils;
import net.minecraft.entity.Entity;

import java.util.HashMap;

public class AshFangGravityESP extends RenderEntityESP {
    public EntityInfo getEntityInfo(Entity var1) {
        if (this.isGravityCenter(var1)) {
            HashMap var2 = new HashMap();
            var2.put("r", 255);
            var2.put("g", 0);
            var2.put("b", 0);
            var2.put("a", 200);
            var2.put("entity", var1);
            var2.put("yOffset", -2.5F);
            var2.put("kind", "Gravity Center");
            var2.put("fontColor", 16711680);
            var2.put("drawString", EntityInfo.EnumDraw.DRAW_KIND);
            var2.put("width", 0.2F);
            var2.put("height", 0.4F);
            var2.put("isESP", true);
            var2.put("isFilled", true);
            return new EntityInfo(var2);
        } else {
            return null;
        }
    }

    public boolean shouldRenderESP(EntityInfo var1) {
        return Configs.AshfangGravityCenterESP;
    }

    public boolean enabled() {
        return SkyblockUtils.isInAshFang();
    }

    public boolean shouldDrawString(EntityInfo var1) {
        return Configs.AshfangGravityCenterESP;
    }

    private boolean isGravityCenter(Entity var1) {
        String var2 = EntityUtils.getHeadTexture(var1);
        return var2 != null && var2.equals("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWE2OWNjZjdhZDkwNGM5YTg1MmVhMmZmM2Y1YjRlMjNhZGViZjcyZWQxMmQ1ZjI0Yjc4Y2UyZDQ0YjRhMiJ9fX0=");
    }
}
