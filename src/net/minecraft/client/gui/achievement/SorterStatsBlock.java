package net.minecraft.client.gui.achievement;

import java.util.Comparator;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatCrafting;
import net.minecraft.stats.StatList;

class SorterStatsBlock implements Comparator<StatCrafting>
{
    final GuiStats statsGUI;

    final GuiSlotStatsBlock slotStatsBlockGUI;

    SorterStatsBlock(GuiSlotStatsBlock par1GuiSlotStatsBlock, GuiStats par2GuiStats)
    {
        this.slotStatsBlockGUI = par1GuiSlotStatsBlock;
        this.statsGUI = par2GuiStats;
    }

    public int func_78334_a(StatCrafting par1StatCrafting, StatCrafting par2StatCrafting)
    {
        int var3 = par1StatCrafting.getItemID();
        int var4 = par2StatCrafting.getItemID();
        StatBase var5 = null;
        StatBase var6 = null;

        if (this.slotStatsBlockGUI.field_77264_j == 2)
        {
            var5 = StatList.mineBlockStatArray.get(var3);
            var6 = StatList.mineBlockStatArray.get(var4);
        }
        else if (this.slotStatsBlockGUI.field_77264_j == 0)
        {
            var5 = StatList.objectCraftStats.get(var3);
            var6 = StatList.objectCraftStats.get(var4);
        }
        else if (this.slotStatsBlockGUI.field_77264_j == 1)
        {
            var5 = StatList.objectUseStats.get(var3);
            var6 = StatList.objectUseStats.get(var4);
        }

        if (var5 != null || var6 != null)
        {
            if (var5 == null)
            {
                return 1;
            }

            if (var6 == null)
            {
                return -1;
            }

            int var7 = GuiStats.getStatsFileWriter(this.slotStatsBlockGUI.theStats).writeStat(var5);
            int var8 = GuiStats.getStatsFileWriter(this.slotStatsBlockGUI.theStats).writeStat(var6);

            if (var7 != var8)
            {
                return (var7 - var8) * this.slotStatsBlockGUI.field_77265_k;
            }
        }

        return var3 - var4;
    }

    public int compare(StatCrafting par1StatCrafting, StatCrafting par2StatCrafting)
    {
        return this.func_78334_a(par1StatCrafting, par2StatCrafting);
    }
}
