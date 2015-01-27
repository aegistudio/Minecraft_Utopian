package net.minecraft.entity;

import java.util.concurrent.Callable;

class CallableEntityName implements Callable<String>
{
    final Entity theEntity;

    CallableEntityName(Entity par1Entity)
    {
        this.theEntity = par1Entity;
    }

    public String callEntityName()
    {
        return this.theEntity.getEntityName();
    }

    public String call()
    {
        return this.callEntityName();
    }
}
