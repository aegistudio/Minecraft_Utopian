package net.minecraft.util;

final class AABBLocalPool extends ThreadLocal<AABBPool>
{
    protected AABBPool createNewDefaultPool()
    {
        return new AABBPool(300, 2000);
    }

    protected AABBPool initialValue()
    {
        return this.createNewDefaultPool();
    }
}
