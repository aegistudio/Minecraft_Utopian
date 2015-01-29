package net.minecraft.world.chunk.storage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RegionFileCache
{
    /** A map containing Files as keys and RegionFiles as values */
    private static final Map<File, RegionFile> regionsByFilename = new HashMap<File, RegionFile>();

    public static synchronized RegionFile createOrLoadRegionFile(File par0File, int par1, int par2)
    {
        File regionRootFolder = new File(par0File, "region");
        File regionFile = new File(regionRootFolder, "r." + (par1 >> 5) + "." + (par2 >> 5) + ".mca");
        RegionFile mappedRegionFile = (RegionFile)regionsByFilename.get(regionFile);

        if (mappedRegionFile != null)
        {
            return mappedRegionFile;
        }
        else
        {
            if (!regionRootFolder.exists())
            {
                regionRootFolder.mkdirs();
            }

            if (regionsByFilename.size() >= 256)
            {
                clearRegionFileReferences();
            }

            RegionFile newRegionFile = new RegionFile(regionFile);
            regionsByFilename.put(regionFile, newRegionFile);
            return newRegionFile;
        }
    }

    /**
     * Saves the current Chunk Map Cache
     */
    public static synchronized void clearRegionFileReferences()
    {
        Iterator<RegionFile> var0 = regionsByFilename.values().iterator();

        while (var0.hasNext())
        {
            RegionFile var1 = (RegionFile)var0.next();

            try
            {
                if (var1 != null)
                {
                    var1.close();
                }
            }
            catch (IOException var3)
            {
                var3.printStackTrace();
            }
        }

        regionsByFilename.clear();
    }

    /**
     * Returns an input stream for the specified chunk. Args: worldDir, chunkX, chunkZ
     */
    public static DataInputStream getChunkInputStream(File par0File, int par1, int par2)
    {
        RegionFile var3 = createOrLoadRegionFile(par0File, par1, par2);
        return var3.getChunkDataInputStream(par1 & 31, par2 & 31);
    }

    /**
     * Returns an output stream for the specified chunk. Args: worldDir, chunkX, chunkZ
     */
    public static DataOutputStream getChunkOutputStream(File par0File, int par1, int par2)
    {
        RegionFile var3 = createOrLoadRegionFile(par0File, par1, par2);
        return var3.getChunkDataOutputStream(par1 & 31, par2 & 31);
    }
}
