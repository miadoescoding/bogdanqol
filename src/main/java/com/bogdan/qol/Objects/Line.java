package com.bogdan.qol.Objects;

import javax.vecmath.Vector3d;
import java.awt.*;

public class Line {

    public Vector3d from;

    public Vector3d to;

    public Color color;

    public Line(Vector3d var1, Vector3d var2, boolean var3) {
        this.from = var1;
        this.to = var2;
        this.color = var3 ? new Color(0, 255, 0) : new Color(255, 0, 0);
    }
}
