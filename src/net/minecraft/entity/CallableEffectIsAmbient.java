package net.minecraft.entity;

import java.util.concurrent.Callable;
import net.minecraft.potion.PotionEffect;

class CallableEffectIsAmbient implements Callable<String>
{
    final PotionEffect field_102046_a;

    final EntityLiving field_102045_b;

    CallableEffectIsAmbient(EntityLiving par1EntityLiving, PotionEffect par2PotionEffect)
    {
        this.field_102045_b = par1EntityLiving;
        this.field_102046_a = par2PotionEffect;
    }

    public String func_102044_a()
    {
        return this.field_102046_a.getIsAmbient() + "";
    }

    public String call()
    {
        return this.func_102044_a();
    }
}
