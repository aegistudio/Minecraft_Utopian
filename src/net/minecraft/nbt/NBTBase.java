package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;

public abstract class NBTBase
{
    public static String[] NBTTypes;

    /** The UTF string key used to lookup values. */
    private String name;

    /**
     * Write the actual data contents of the tag, implemented in NBT extension classes
     */
    abstract void write(DataOutput var1) throws IOException;

    /**
     * Read the actual data contents of the tag, implemented in NBT extension classes
     */
    abstract void load(DataInput var1) throws IOException;

    /**
     * Gets the type byte for the tag.
     */
    public abstract byte getId();

    protected NBTBase(String par1Str)
    {
        if (par1Str == null)
        {
            this.name = "";
        }
        else
        {
            this.name = par1Str;
        }
    }

    /**
     * Sets the name for this tag and returns this for convenience.
     */
    public NBTBase setName(String par1Str)
    {
        if (par1Str == null) this.name = "";
        else this.name = par1Str;
        
        return this;
    }

    /**
     * Gets the name corresponding to the tag, or an empty string if none set.
     */
    public String getName()
    {
        return this.name == null ? "" : this.name;
    }

    /**
     * Reads and returns a tag from the given DataInput, or the End tag if no tag could be read.
     */
    public static NBTBase readNamedTag(DataInput dataInput) throws IOException
    {
        byte var1 = dataInput.readByte();

        if (var1 == 0) return new NBTTagEnd();
        else
        {
            String data = dataInput.readUTF();
            NBTBase nbt = newTag(var1, data);

            try
            {
                nbt.load(dataInput);
                return nbt;
            }
            catch (IOException var7)
            {
                CrashReport crashReport = CrashReport.makeCrashReport(var7, "Loading NBT data");
                CrashReportCategory crashReportCategory = crashReport.makeCategory("NBT Tag");
                crashReportCategory.addCrashSection("Tag name", data);
                crashReportCategory.addCrashSection("Tag type", Byte.valueOf(var1));
                throw new ReportedException(crashReport);
            }
        }
    }

    /**
     * Writes the specified tag to the given DataOutput, writing the type byte, the UTF string key and then calling the
     * tag to write its data.
     */
    public static void writeNamedTag(NBTBase par0NBTBase, DataOutput par1DataOutput) throws IOException
    {
        par1DataOutput.writeByte(par0NBTBase.getId());

        if (par0NBTBase.getId() != 0)
        {
            par1DataOutput.writeUTF(par0NBTBase.getName());
            par0NBTBase.write(par1DataOutput);
        }
    }

    @SuppressWarnings("rawtypes")
	private static Constructor[] NBT_TAG_CONSTRUCTOR;
    
    static
    {
    	NBTTableDrivenLoader loader = new NBTTableDrivenLoader()
		.addProperConstructor("END", NBTTagEnd.class, "TAG_End")
		.addProperConstructor("BYTE", NBTTagByte.class, "TAG_Byte")
		.addProperConstructor("SHORT", NBTTagShort.class, "TAG_Short")
		.addProperConstructor("INT", NBTTagInt.class, "TAG_Int")
		.addProperConstructor("LONG", NBTTagLong.class, "TAG_Long")
		.addProperConstructor("FLOAT", NBTTagFloat.class, "TAG_Float")
		.addProperConstructor("DOUBLE", NBTTagDouble.class, "TAG_Double")
		.addProperConstructor("BYTE[]", NBTTagByteArray.class, "TAG_Byte_Array")
		.addProperConstructor("STRING", NBTTagString.class, "TAG_String")
		.addProperConstructor("LIST", NBTTagList.class, "TAG_List")
		.addProperConstructor("COMPOUND", NBTTagCompound.class, "TAG_Compound")
		.addProperConstructor("INT[]", NBTTagIntArray.class, "TAG_Int_Array");
    	
    	NBTBase.NBTTypes = loader.nbttypes.toArray(new String[0]);
		NBTBase.NBT_TAG_CONSTRUCTOR = loader.nbtconstructors.toArray(new Constructor[0]);
		NBTBase.NBT_TAG_NAME = loader.nbttagnames.toArray(new String[0]);
    }
    
    /**
     * Creates and returns a new tag of the specified type, or null if invalid.
     */
    
    public static NBTBase newTag(byte index, String argument)
    {
		try
		{
			if(index > 0 && index < NBT_TAG_CONSTRUCTOR.length)
				return (NBTBase) NBT_TAG_CONSTRUCTOR[index].newInstance(argument);
			else return null;
		}
        catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * Returns the string name of a tag with the specified type, or 'UNKNOWN' if invalid.
     */
    
    private static String[] NBT_TAG_NAME;
    
    public static String getTagName(byte index)
    {
    	if(index > 0 && index < NBT_TAG_NAME.length) return NBT_TAG_NAME[index];
    	else return "UNKNOWN";
    }

    /**
     * Creates a clone of the tag.
     */
    public abstract NBTBase copy();

    public boolean equals(Object par1Obj)
    {
        if (!(par1Obj instanceof NBTBase))
        {
            return false;
        }
        else
        {
            NBTBase var2 = (NBTBase)par1Obj;
            return this.getId() != var2.getId() ? false : ((this.name != null || var2.name == null) && (this.name == null || var2.name != null) ? this.name == null || this.name.equals(var2.name) : false);
        }
    }

    public int hashCode()
    {
        return this.name.hashCode() ^ this.getId();
    }
}

class NBTTableDrivenLoader
{
	final List<Constructor<? extends NBTBase>> nbtconstructors = new ArrayList<Constructor<? extends NBTBase>>();
	final List<String> nbttagnames = new ArrayList<String>();
	final List<String> nbttypes = new ArrayList<String>();
	
	public NBTTableDrivenLoader addProperConstructor(String nbtType, Class<? extends NBTBase> nbtClazz, String nbtTagName)
    {
		nbttagnames.add(nbtTagName);
		nbttypes.add(nbtType);
    	try
		{
			nbtconstructors.add(nbtClazz.getConstructor(String.class));
		}
    	catch(Exception e)
		{
			e.printStackTrace();
			nbtconstructors.add(null);
		}
    	return this;
    }
}
