package net.minecraft.client.gui;

import net.minecraft.client.renderer.Tessellator;

class SelectionListInvited extends SelectionListBase
{
    final GuiScreenConfigureWorld field_98264_a;

    public SelectionListInvited(GuiScreenConfigureWorld par1GuiScreenConfigureWorld)
    {
        super(GuiScreenConfigureWorld.func_96265_a(par1GuiScreenConfigureWorld), GuiScreenConfigureWorld.func_96271_b(par1GuiScreenConfigureWorld), GuiScreenConfigureWorld.func_96274_a(par1GuiScreenConfigureWorld, 2), GuiScreenConfigureWorld.func_96269_c(par1GuiScreenConfigureWorld), GuiScreenConfigureWorld.func_96274_a(par1GuiScreenConfigureWorld, 9) - GuiScreenConfigureWorld.func_96274_a(par1GuiScreenConfigureWorld, 2), 12);
        this.field_98264_a = par1GuiScreenConfigureWorld;
    }

    protected int func_96608_a()
    {
        return GuiScreenConfigureWorld.func_96266_d(this.field_98264_a).field_96402_f.size() + 1;
    }

    protected void func_96615_a(int par1, boolean par2)
    {
        if (par1 < GuiScreenConfigureWorld.func_96266_d(this.field_98264_a).field_96402_f.size())
        {
            GuiScreenConfigureWorld.func_96270_b(this.field_98264_a, par1);
        }
    }

    protected boolean func_96609_a(int par1)
    {
        return par1 == GuiScreenConfigureWorld.func_96263_e(this.field_98264_a);
    }

    protected int func_96613_b()
    {
        return this.func_96608_a() * 12;
    }

    protected void func_96611_c() {}

    protected void func_96610_a(int par1, int par2, int par3, int par4, Tessellator par5Tessellator)
    {
        if (par1 < GuiScreenConfigureWorld.func_96266_d(this.field_98264_a).field_96402_f.size())
        {
            this.func_98263_b(par1, par2, par3, par4, par5Tessellator);
        }
    }

    private void func_98263_b(int par1, int par2, int par3, int par4, Tessellator par5Tessellator)
    {
        String var6 = (String)GuiScreenConfigureWorld.func_96266_d(this.field_98264_a).field_96402_f.get(par1);
        this.field_98264_a.drawString(GuiScreenConfigureWorld.func_96273_f(this.field_98264_a), var6, par2 + 2, par3 + 1, 16777215);
    }
}
