package net.minecraft.client;

import java.awt.Canvas;
import java.awt.Dimension;

@SuppressWarnings("serial")
class CanvasCrashReport extends Canvas
{
    public CanvasCrashReport(int par1)
    {
        this.setPreferredSize(new Dimension(par1, par1));
        this.setMinimumSize(new Dimension(par1, par1));
    }
}
