package net.minecraft.entity;

import java.util.concurrent.Callable;
import net.minecraft.potion.PotionEffect;

class CallableEffectDuration implements Callable<String>
{
    final PotionEffect field_102037_a;

    final EntityLiving field_102036_b;

    CallableEffectDuration(EntityLiving par1EntityLiving, PotionEffect par2PotionEffect)
    {
        this.field_102036_b = par1EntityLiving;
        this.field_102037_a = par2PotionEffect;
    }

    public String func_102035_a()
    {
        return this.field_102037_a.getDuration() + "";
    }

    public String call()
    {
        return this.func_102035_a();
    }
}
