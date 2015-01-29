package net.minecraft.command;

@SuppressWarnings("serial")
public class WrongUsageException extends SyntaxErrorException
{
    public WrongUsageException(String par1Str, Object ... par2ArrayOfObj)
    {
        super(par1Str, par2ArrayOfObj);
    }
}
