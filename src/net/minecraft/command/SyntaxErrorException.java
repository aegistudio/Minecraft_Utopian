package net.minecraft.command;

@SuppressWarnings("serial")
public class SyntaxErrorException extends CommandException
{
    public SyntaxErrorException()
    {
        this("commands.generic.snytax", new Object[0]);
    }

    public SyntaxErrorException(String par1Str, Object ... par2ArrayOfObj)
    {
        super(par1Str, par2ArrayOfObj);
    }
}
