package net.minecraft.world;

import java.util.Iterator;
import java.util.TreeMap;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class GameRules
{
    private TreeMap<String, GameRuleValue> theGameRules = new TreeMap<String, GameRuleValue>();

    public GameRules()
    {
        this.addGameRule("doFireTick", "true");
        this.addGameRule("mobGriefing", "true");
        this.addGameRule("keepInventory", "false");
        this.addGameRule("doMobSpawning", "true");
        this.addGameRule("doMobLoot", "true");
        this.addGameRule("doTileDrops", "true");
        this.addGameRule("doExplosionDestroyBlocks", "true");
        this.addGameRule("doFluidFlows", "true");
        this.addGameRule("commandBlockOutput", "true");
    }

    /**
     * Define a game rule and its default value.
     */
    public void addGameRule(String gameruleName, String gameruleValue)
    {
        this.theGameRules.put(gameruleName, new GameRuleValue(gameruleValue));
    }

    public void setOrCreateGameRule(String par1Str, String par2Str)
    {
        GameRuleValue gamerule = (GameRuleValue)this.theGameRules.get(par1Str);

        if (gamerule != null) gamerule.setValue(par2Str);
        else this.addGameRule(par1Str, par2Str);
    }

    /**
     * Gets the string Game Rule value.
     */
    public String getGameRuleStringValue(String gameruleName)
    {
        GameRuleValue gameruleValue = (GameRuleValue)this.theGameRules.get(gameruleName);
        return gameruleValue != null ? gameruleValue.getGameRuleStringValue() : "";
    }

    /**
     * Gets the boolean Game Rule value.
     */
    public boolean getGameRuleBooleanValue(String gameruleName)
    {
        GameRuleValue gameruleValue = (GameRuleValue)this.theGameRules.get(gameruleName);
        return gameruleValue != null ? gameruleValue.getGameRuleBooleanValue() : false;
    }

    /**
     * Gets the integer Game Rule value.
     */
    public int getGameRuleIntegerValue(String gameruleName)
    {
        GameRuleValue gameruleValue = (GameRuleValue)this.theGameRules.get(gameruleName);
        return gameruleValue != null ? gameruleValue.getGameRuleIntegerValue() : 0;
    }
    
    /**
     * Gets the double Game Rule value.
     */
    public double getGameRuleDoubleValue(String gameruleName)
    {
        GameRuleValue gameruleValue = (GameRuleValue)this.theGameRules.get(gameruleName);
        return gameruleValue != null ? gameruleValue.getGameRuleDoubleValue() : 0.0D;
    }
    
    /**
     * Return the defined game rules as NBT.
     */
    public NBTTagCompound writeGameRulesToNBT()
    {
        NBTTagCompound nbt = new NBTTagCompound("GameRules");
        Iterator<String> gameruleNames = this.theGameRules.keySet().iterator();

        while (gameruleNames.hasNext())
        {
            String gameruleName = (String)gameruleNames.next();
            GameRuleValue gameruleValue = (GameRuleValue)this.theGameRules.get(gameruleName);
            nbt.setString(gameruleName, gameruleValue.getGameRuleStringValue());
        }

        return nbt;
    }

    /**
     * Set defined game rules from NBT.
     */
    public void readGameRulesFromNBT(NBTTagCompound nbtTagCompound)
    {
        Iterator<NBTBase> nbts = nbtTagCompound.getTags().iterator();

        while (nbts.hasNext())
        {
            NBTBase nbt = (NBTBase)nbts.next();
            String gameruleName = nbt.getName();
            String gameruleValue = nbtTagCompound.getString(nbt.getName());
            this.setOrCreateGameRule(gameruleName, gameruleValue);
        }
    }

    /**
     * Return the defined game rules.
     */
    public String[] getRules()
    {
        return (String[])this.theGameRules.keySet().toArray(new String[0]);
    }

    /**
     * Return whether the specified game rule is defined.
     */
    public boolean hasRule(String gameruleName)
    {
        return this.theGameRules.containsKey(gameruleName);
    }
}
