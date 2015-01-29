package net.minecraft.scoreboard;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;

public class ScoreDummyCriteria implements ScoreObjectiveCriteria
{
    private final String field_96644_g;

    public ScoreDummyCriteria(String criteriaName)
    {
        this.field_96644_g = criteriaName;
        ScoreObjectiveCriteria.nameToCriteriaMap.put(criteriaName, this);
    }

    public String func_96636_a()
    {
        return this.field_96644_g;
    }

    public int calculateScore(List<? extends EntityPlayer> par1List)
    {
        return 0;
    }

    public boolean isReadOnly()
    {
        return false;
    }
}
