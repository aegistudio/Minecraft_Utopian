package net.minecraft.scoreboard;

import java.util.Comparator;

final class ScoreComparator implements Comparator<Score>
{
    public int compareScore(Score par1Score, Score par2Score)
    {
        return par1Score.func_96652_c() > par2Score.func_96652_c() ? 1 : (par1Score.func_96652_c() < par2Score.func_96652_c() ? -1 : 0);
    }

    public int compare(Score par1Obj, Score par2Obj)
    {
        return this.compareScore((Score)par1Obj, (Score)par2Obj);
    }
}
