package net.minecraft.client.mco;

public class ExceptionRetryCall extends ExceptionMcoService
{
    public final int field_96393_c;

    public ExceptionRetryCall(int par1)
    {
        super(503, "Retry operation");
        this.field_96393_c = par1;
    }
}
