package net.minecraft.entity;

import java.util.concurrent.Callable;

class CallableEntityType implements Callable<String>
{
    final Entity theEntity;

    CallableEntityType(Entity par1Entity)
    {
        this.theEntity = par1Entity;
    }

    public String callEntityType()
    {
        return EntityList.getEntityString(this.theEntity) + " (" + this.theEntity.getClass().getCanonicalName() + ")";
    }

    public String call()
    {
        return this.callEntityType();
    }
}
