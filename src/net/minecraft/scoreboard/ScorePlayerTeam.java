package net.minecraft.scoreboard;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ScorePlayerTeam
{
    private final Scoreboard theScoreboard;
    private final String defaultName;

    /** A set of all team member usernames. */
    private final Set<String> membershipSet = new HashSet<String>();
    private String displayName;
    private String prefix = "";
    private String suffix = "";
    private boolean field_96672_g = true;
    private boolean field_98301_h = true;

    public ScorePlayerTeam(Scoreboard scoreboard, String defaultName)
    {
        this.theScoreboard = scoreboard;
        this.defaultName = defaultName;
        this.displayName = defaultName;
    }

    public String getDefaultName()
    {
        return this.defaultName;
    }

    public String getDisplayName()
    {
        return this.displayName;
    }

    public void setDisplayName(String name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("Name cannot be null");
        }
        else
        {
            this.displayName = name;
            this.theScoreboard.func_96538_b(this);
        }
    }

    public Collection<String> getMembershipCollection()
    {
        return this.membershipSet;
    }

    public String getTeamPrefix()
    {
        return this.prefix;
    }

    public void setTeamPrefix(String prefix)
    {
        if (prefix == null)
        {
            throw new IllegalArgumentException("Prefix cannot be null");
        }
        else
        {
            this.prefix = prefix;
            this.theScoreboard.func_96538_b(this);
        }
    }

    public String getTeamSuffix()
    {
        return this.suffix;
    }

    public void setTeamSuffix(String suffix)
    {
        if (suffix == null)
        {
            throw new IllegalArgumentException("Suffix cannot be null");
        }
        else
        {
            this.suffix = suffix;
            this.theScoreboard.func_96538_b(this);
        }
    }

    public static String convertPlayerName(ScorePlayerTeam scorePlayerTeam, String playerName)
    {
        return scorePlayerTeam == null ? playerName : scorePlayerTeam.getTeamPrefix() + playerName + scorePlayerTeam.getTeamSuffix();
    }

    public boolean func_96665_g()
    {
        return this.field_96672_g;
    }

    public void func_96660_a(boolean par1)
    {
        this.field_96672_g = par1;
        this.theScoreboard.func_96538_b(this);
    }

    public boolean func_98297_h()
    {
        return this.field_98301_h;
    }

    public void func_98300_b(boolean par1)
    {
        this.field_98301_h = par1;
        this.theScoreboard.func_96538_b(this);
    }

    public int func_98299_i()
    {
        int var1 = 0;
        int var2 = 0;

        if (this.func_96665_g())
        {
            var1 |= 1 << var2++;
        }

        if (this.func_98297_h())
        {
            var1 |= 1 << var2++;
        }

        return var1;
    }

    public void func_98298_a(int par1)
    {
        byte var2 = 0;
        int var4 = var2 + 1;
        this.func_96660_a((par1 & 1 << var2) > 0);
        this.func_98300_b((par1 & 1 << var4++) > 0);
    }
}
