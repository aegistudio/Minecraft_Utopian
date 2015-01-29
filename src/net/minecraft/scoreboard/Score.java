package net.minecraft.scoreboard;

import java.util.Comparator;
import java.util.List;

public class Score
{
    public static final Comparator<Score> scoreComparator = new ScoreComparator();
    private final Scoreboard scoreboard;
    private final ScoreObjective scoreObjective;
    private final String field_96654_d;
    private int field_96655_e;

    public Score(Scoreboard scoreboard, ScoreObjective scoreObjective, String par3Str)
    {
        this.scoreboard = scoreboard;
        this.scoreObjective = scoreObjective;
        this.field_96654_d = par3Str;
    }

    public void func_96649_a(int par1)
    {
        if (this.scoreObjective.getCriteria().isReadOnly())
        {
            throw new IllegalStateException("Cannot modify read-only score");
        }
        else
        {
            this.func_96647_c(this.func_96652_c() + par1);
        }
    }

    public void func_96646_b(int par1)
    {
        if (this.scoreObjective.getCriteria().isReadOnly())
        {
            throw new IllegalStateException("Cannot modify read-only score");
        }
        else
        {
            this.func_96647_c(this.func_96652_c() - par1);
        }
    }

    public void func_96648_a()
    {
        if (this.scoreObjective.getCriteria().isReadOnly())
        {
            throw new IllegalStateException("Cannot modify read-only score");
        }
        else
        {
            this.func_96649_a(1);
        }
    }

    public int func_96652_c()
    {
        return this.field_96655_e;
    }

    public void func_96647_c(int par1)
    {
        int var2 = this.field_96655_e;
        this.field_96655_e = par1;

        if (var2 != par1)
        {
            this.getScoreboard().func_96536_a(this);
        }
    }

    public ScoreObjective getScoreObjective()
    {
        return this.scoreObjective;
    }

    public String func_96653_e()
    {
        return this.field_96654_d;
    }

    public Scoreboard getScoreboard()
    {
        return this.scoreboard;
    }

    public void func_96651_a(List par1List)
    {
        this.func_96647_c(this.scoreObjective.getCriteria().calculateScore(par1List));
    }
}
