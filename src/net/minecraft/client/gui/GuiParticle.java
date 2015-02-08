package net.minecraft.client.gui;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class GuiParticle extends Gui
{
    private List<Particle> particles = new ArrayList<Particle>();
    private Minecraft mc;

    public GuiParticle(Minecraft par1Minecraft)
    {
        this.mc = par1Minecraft;
    }

    public void update()
    {
        for (int var1 = 0; var1 < this.particles.size(); ++var1)
        {
            Particle particle = (Particle)this.particles.get(var1);
            particle.preUpdate();
            particle.update(this);

            if (particle.isDead)
            {
                this.particles.remove(var1--);
            }
        }
    }

    public void draw(float par1)
    {
        this.mc.renderEngine.bindTexture("/gui/particles.png");

        for (int var2 = 0; var2 < this.particles.size(); ++var2)
        {
            Particle particle = (Particle)this.particles.get(var2);
            int var4 = (int)(particle.prevPosX + (particle.posX - particle.prevPosX) * (double)par1 - 4.0D);
            int var5 = (int)(particle.prevPosY + (particle.posY - particle.prevPosY) * (double)par1 - 4.0D);
            float var6 = (float)(particle.prevTintAlpha + (particle.tintAlpha - particle.prevTintAlpha) * (double)par1);
            float var7 = (float)(particle.prevTintRed + (particle.tintRed - particle.prevTintRed) * (double)par1);
            float var8 = (float)(particle.prevTintGreen + (particle.tintGreen - particle.prevTintGreen) * (double)par1);
            float var9 = (float)(particle.prevTintBlue + (particle.tintBlue - particle.prevTintBlue) * (double)par1);
            GL11.glColor4f(var7, var8, var9, var6);
            this.drawTexturedModalRect(var4, var5, 40, 0, 8, 8);
        }
    }
}
