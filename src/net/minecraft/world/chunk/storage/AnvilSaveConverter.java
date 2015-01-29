package net.minecraft.world.chunk.storage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraft.world.storage.SaveFormatOld;
import net.minecraft.world.storage.WorldInfo;

public class AnvilSaveConverter extends SaveFormatOld
{
    public AnvilSaveConverter(File par1File)
    {
        super(par1File);
    }

    public List<SaveFormatComparator> getSaveList() throws AnvilConverterException
    {
        if (this.savesDirectory != null && this.savesDirectory.exists() && this.savesDirectory.isDirectory())
        {
            ArrayList<SaveFormatComparator> var1 = new ArrayList<SaveFormatComparator>();
            File[] var2 = this.savesDirectory.listFiles();
            File[] var3 = var2;
            int var4 = var2.length;

            for (int var5 = 0; var5 < var4; ++var5)
            {
                File var6 = var3[var5];

                if (var6.isDirectory())
                {
                    String var7 = var6.getName();
                    WorldInfo var8 = this.getWorldInfo(var7);

                    if (var8 != null && (var8.getSaveVersion() == 19132 || var8.getSaveVersion() == 19133))
                    {
                        boolean var9 = var8.getSaveVersion() != this.getSaveVersion();
                        String var10 = var8.getWorldName();

                        if (var10 == null || MathHelper.stringNullOrLengthZero(var10))
                        {
                            var10 = var7;
                        }

                        long var11 = 0L;
                        var1.add(new SaveFormatComparator(var7, var10, var8.getLastTimePlayed(), var11, var8.getGameType(), var9, var8.isHardcoreModeEnabled(), var8.areCommandsAllowed()));
                    }
                }
            }

            return var1;
        }
        else
        {
            throw new AnvilConverterException("Unable to read or access folder where game worlds are saved!");
        }
    }

    protected int getSaveVersion()
    {
        return 19133;
    }

    public void flushCache()
    {
        RegionFileCache.clearRegionFileReferences();
    }

    /**
     * Returns back a loader for the specified save directory
     */
    public ISaveHandler getSaveLoader(String par1Str, boolean par2)
    {
        return new AnvilSaveHandler(this.savesDirectory, par1Str, par2);
    }

    /**
     * Checks if the save directory uses the old map format
     */
    public boolean isOldMapFormat(String par1Str)
    {
        WorldInfo var2 = this.getWorldInfo(par1Str);
        return var2 != null && var2.getSaveVersion() != this.getSaveVersion();
    }

    /**
     * Converts the specified map to the new map format. Args: worldName, loadingScreen
     */
    public boolean convertMapFormat(String root, IProgressUpdate par2IProgressUpdate)
    {
        par2IProgressUpdate.setLoadingProgress(0);
        ArrayList<File> surfaceConversion = new ArrayList<File>();
        ArrayList<File> netherConversion = new ArrayList<File>();
        ArrayList<File> endConversion = new ArrayList<File>();
        File rootDir = new File(this.savesDirectory, root);
        File netherDir = new File(rootDir, "DIM-1");
        File endDir = new File(rootDir, "DIM1");
        MinecraftServer.getServer().getLogAgent().logInfo("Scanning folders...");
        this.addRegionFilesToCollection(rootDir, surfaceConversion);

        if (netherDir.exists()) this.addRegionFilesToCollection(netherDir, netherConversion);
        if (endDir.exists()) this.addRegionFilesToCollection(endDir, endConversion);

        int totalConversionCount = surfaceConversion.size() + netherConversion.size() + endConversion.size();
        MinecraftServer.getServer().getLogAgent().logInfo("Total conversion count is " + totalConversionCount);
        WorldInfo var10 = this.getWorldInfo(root);
        Object var11 = null;

        if (var10.getTerrainType() == WorldType.FLAT)
        {
            var11 = new WorldChunkManagerHell(BiomeGenBase.plains, 0.5F, 0.5F);
        }
        else
        {
            var11 = new WorldChunkManager(var10.getSeed(), var10.getTerrainType());
        }

        this.convertFile(new File(rootDir, "region"), surfaceConversion, (WorldChunkManager)var11, 0, totalConversionCount, par2IProgressUpdate);
        this.convertFile(new File(netherDir, "region"), netherConversion, new WorldChunkManagerHell(BiomeGenBase.hell, 1.0F, 0.0F), surfaceConversion.size(), totalConversionCount, par2IProgressUpdate);
        this.convertFile(new File(endDir, "region"), endConversion, new WorldChunkManagerHell(BiomeGenBase.sky, 0.5F, 0.0F), surfaceConversion.size() + netherConversion.size(), totalConversionCount, par2IProgressUpdate);
        var10.setSaveVersion(19133);

        if (var10.getTerrainType() == WorldType.DEFAULT_1_1)
        {
            var10.setTerrainType(WorldType.DEFAULT);
        }

        this.createFile(root);
        ISaveHandler var12 = this.getSaveLoader(root, false);
        var12.saveWorldInfo(var10);
        return true;
    }

    /**
     * par: filename for the level.dat_mcr backup
     */
    private void createFile(String par1Str)
    {
        File var2 = new File(this.savesDirectory, par1Str);

        if (!var2.exists())
        {
            System.out.println("Warning: Unable to create level.dat_mcr backup");
        }
        else
        {
            File var3 = new File(var2, "level.dat");

            if (!var3.exists())
            {
                System.out.println("Warning: Unable to create level.dat_mcr backup");
            }
            else
            {
                File var4 = new File(var2, "level.dat_mcr");

                if (!var3.renameTo(var4))
                {
                    System.out.println("Warning: Unable to create level.dat_mcr backup");
                }
            }
        }
    }

    private void convertFile(File par1File, Iterable<File> par2Iterable, WorldChunkManager par3WorldChunkManager, int par4, int par5, IProgressUpdate par6IProgressUpdate)
    {
        Iterator<File> var7 = par2Iterable.iterator();

        while (var7.hasNext())
        {
            File var8 = (File)var7.next();
            this.convertChunks(par1File, var8, par3WorldChunkManager, par4, par5, par6IProgressUpdate);
            ++par4;
            int var9 = (int)Math.round(100.0D * (double)par4 / (double)par5);
            par6IProgressUpdate.setLoadingProgress(var9);
        }
    }

    /**
     * copies a 32x32 chunk set from par2File to par1File, via AnvilConverterData
     */
    private void convertChunks(File par1File, File par2File, WorldChunkManager par3WorldChunkManager, int par4, int par5, IProgressUpdate par6IProgressUpdate)
    {
        try
        {
            String var7 = par2File.getName();
            RegionFile var8 = new RegionFile(par2File);
            RegionFile var9 = new RegionFile(new File(par1File, var7.substring(0, var7.length() - ".mcr".length()) + ".mca"));

            for (int var10 = 0; var10 < 32; ++var10)
            {
                int var11;

                for (var11 = 0; var11 < 32; ++var11)
                {
                    if (var8.isChunkSaved(var10, var11) && !var9.isChunkSaved(var10, var11))
                    {
                        DataInputStream var12 = var8.getChunkDataInputStream(var10, var11);

                        if (var12 == null)
                        {
                            MinecraftServer.getServer().getLogAgent().logWarning("Failed to fetch input stream");
                        }
                        else
                        {
                            NBTTagCompound var13 = CompressedStreamTools.read(var12);
                            var12.close();
                            NBTTagCompound var14 = var13.getCompoundTag("Level");
                            AnvilConverterData var15 = ChunkLoader.load(var14);
                            NBTTagCompound var16 = new NBTTagCompound();
                            NBTTagCompound var17 = new NBTTagCompound();
                            var16.setTag("Level", var17);
                            ChunkLoader.convertToAnvilFormat(var15, var17, par3WorldChunkManager);
                            DataOutputStream var18 = var9.getChunkDataOutputStream(var10, var11);
                            CompressedStreamTools.write(var16, var18);
                            var18.close();
                        }
                    }
                }

                var11 = (int)Math.round(100.0D * (double)(par4 * 1024) / (double)(par5 * 1024));
                int var20 = (int)Math.round(100.0D * (double)((var10 + 1) * 32 + par4 * 1024) / (double)(par5 * 1024));

                if (var20 > var11)
                {
                    par6IProgressUpdate.setLoadingProgress(var20);
                }
            }

            var8.close();
            var9.close();
        }
        catch (IOException var19)
        {
            var19.printStackTrace();
        }
    }

    /**
     * filters the files in the par1 directory, and adds them to the par2 collections
     */
    private void addRegionFilesToCollection(File regionParentDir, Collection<File> par2Collection)
    {
        File regionFolder = new File(regionParentDir, "region");
        File[] files = regionFolder.listFiles(new AnvilSaveConverterFileFilter(this));

        if (files != null) Collections.addAll(par2Collection, files);
    }
}
