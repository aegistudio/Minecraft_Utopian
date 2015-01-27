package net.minecraft.client.audio;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SoundPool
{
    /** The RNG used by SoundPool. */
    private Random rand = new Random();

    /**
     * Maps a name (can be sound/newsound/streaming/music/newmusic) to a list of SoundPoolEntry's.
     */
    private Map<String, ArrayList<SoundPoolEntry>> nameToSoundPoolEntriesMapping = new HashMap<String, ArrayList<SoundPoolEntry>>();

    /** A list of all SoundPoolEntries that have been loaded. */
    private List<SoundPoolEntry> allSoundPoolEntries = new ArrayList<SoundPoolEntry>();

    /**
     * The number of soundPoolEntry's. This value is computed but never used (should be equal to
     * allSoundPoolEntries.size()).
     */
    public int numberOfSoundPoolEntries = 0;
    public boolean isGetRandomSound = true;

    /**
     * Adds a sound to this sound pool.
     */
    public SoundPoolEntry addSound(String soundName, File soundFile)
    {
        try
        {
            String var3 = soundName;
            soundName = soundName.substring(0, soundName.indexOf("."));

            if (this.isGetRandomSound)
            {
                while (Character.isDigit(soundName.charAt(soundName.length() - 1)))
                {
                    soundName = soundName.substring(0, soundName.length() - 1);
                }
            }

            soundName = soundName.replaceAll("/", ".");

            if (!this.nameToSoundPoolEntriesMapping.containsKey(soundName))
            {
                this.nameToSoundPoolEntriesMapping.put(soundName, new ArrayList<SoundPoolEntry>());
            }

            SoundPoolEntry soundPoolEntry = new SoundPoolEntry(var3, soundFile.toURI().toURL());
            this.nameToSoundPoolEntriesMapping.get(soundName).add(soundPoolEntry);
            this.allSoundPoolEntries.add(soundPoolEntry);
            ++this.numberOfSoundPoolEntries;
            return soundPoolEntry;
        }
        catch (MalformedURLException var5)
        {
            var5.printStackTrace();
            throw new RuntimeException(var5);
        }
    }

    /**
     * gets a random sound from the specified (by name, can be sound/newsound/streaming/music/newmusic) sound pool.
     */
    public SoundPoolEntry getRandomSoundFromSoundPool(String soundName)
    {
        List<SoundPoolEntry> soundPoolEntries = this.nameToSoundPoolEntriesMapping.get(soundName);
        return soundPoolEntries == null ? null : (SoundPoolEntry)soundPoolEntries.get(this.rand.nextInt(soundPoolEntries.size()));
    }

    /**
     * Gets a random SoundPoolEntry.
     */
    public SoundPoolEntry getRandomSound()
    {
        return this.allSoundPoolEntries.isEmpty() ? null : (SoundPoolEntry)this.allSoundPoolEntries.get(this.rand.nextInt(this.allSoundPoolEntries.size()));
    }
}
