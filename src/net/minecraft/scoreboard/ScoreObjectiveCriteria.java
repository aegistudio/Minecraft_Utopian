package net.minecraft.scoreboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;

public interface ScoreObjectiveCriteria
{
    Map<String, ScoreObjectiveCriteria> nameToCriteriaMap = new HashMap<String, ScoreObjectiveCriteria>();
    ScoreObjectiveCriteria criteriaDummy = new ScoreDummyCriteria("dummy");
    ScoreObjectiveCriteria criteriaDeathCount = new ScoreDummyCriteria("deathCount");
    ScoreObjectiveCriteria criteriaPlayerKillCount = new ScoreDummyCriteria("playerKillCount");
    ScoreObjectiveCriteria criteriaTotalKillCount = new ScoreDummyCriteria("totalKillCount");
    ScoreObjectiveCriteria criteriaHealth = new ScoreHealthCriteria("health");

    String func_96636_a();

    int calculateScore(List<? extends EntityPlayer> var1);

    boolean isReadOnly();
}
