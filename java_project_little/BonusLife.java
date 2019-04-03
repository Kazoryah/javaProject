import java.util.Timer;
import java.util.TimerTask;
import java.lang.Math;

public class BonusLife
{
    double x;
    double y;
    IngameTimer still_drawn;
    IngameTimer still_active;
    int draw;
    String png = "png/testSpaceSecond.png";

    public BonusLife()
    {
        x = (double)(int)(Math.random() * 1152);
        y = 84;
        if (x > 1080)
            x = 1080;
        if (x < 72)
            x = 72;
        still_drawn = new IngameTimer(2000);
        still_active = null;
        draw = 1;
    }

    public void draw()
    {
        StdDraw.picture(x, y, png);
    }

    public void startBonus()
    {
        draw = 0;
        still_active = new IngameTimer(2000);
    }

    public int stillDrawn()
    {
        return still_drawn.time;
    }

    public int stillActive()
    {
        if (still_active == null)
            return -1;
        return still_active.time;
    }

    public int stayCreated(Player player)
    {
        double pX = player.getX();

        if (x < pX - 120 || x > pX + 120)
            return 1;
        return 0;
    }

    public int checkState(Player player)
    {
        if ((still_active != null && still_active.time == 0)
            || (still_drawn.time == 0 && draw == 1))
            return 0;
        else if (draw == 1)
        {
            double pX = player.getX();

            if ((x - pX) * (x - pX) < 3600)
            {
                startBonus();
                return 1;
            }
        }
        return -1;
    }
}
