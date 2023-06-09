package com.bogdan.qol.Features.Nether;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Features.RenderEntityESP;
import com.bogdan.qol.Objects.EntityInfo;
import com.bogdan.qol.utils.ChatLib;
import com.bogdan.qol.utils.SkyblockUtils;
import net.minecraft.entity.Entity;

import java.util.HashMap;

public class ConvergenceESP extends RenderEntityESP {
    public boolean enabled() {
        return SkyblockUtils.isInNether();
    }

    public boolean shouldDrawString(EntityInfo var1) {
        return true;
    }

    public EntityInfo getEntityInfo(Entity var1) {
        if (false) {
            return null;
        } else if (!Configs.ConvergenceESP) {
            return null;
        } else {
            String var2 = ChatLib.removeFormatting(var1.getName());
            if (var2.equals("Convergence Center")) {
                HashMap var3 = new HashMap();
                var3.put("entity", var1);
                var3.put("yOffset", 0.0F);
                var3.put("kind", "Convergence Center");
                var3.put("drawString", EntityInfo.EnumDraw.DRAW_KIND);
                var3.put("width", 3.0F);
                var3.put("height", 6.0F);
                var3.put("fontColor", 16724787);
                var3.put("isESP", true);
                return new EntityInfo(var3);
            } else {
                return null;
            }
        }
    }

    public boolean shouldRenderESP(EntityInfo var1) {
        return true;
    }
}
