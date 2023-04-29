package com.bogdan.qol.Objects;

import com.bogdan.qol.utils.KeyBindUtils;
import net.minecraft.client.settings.KeyBinding;

public class KeyBind {

    private final KeyBinding keyBinding;

    public KeyBind(String var1, int var2) {
        this.keyBinding = new KeyBinding(var1, var2, "Addons - bogdanons");
        KeyBindUtils.addKeyBind(this);
    }

    public KeyBind(KeyBinding var1) {
        this.keyBinding = var1;
    }

    public boolean isKeyDown() {
        return this.keyBinding.isKeyDown();
    }

    public boolean isPressed() {
        return this.keyBinding.isPressed();
    }

    public KeyBinding mcKeyBinding() {
        return this.keyBinding;
    }
}
