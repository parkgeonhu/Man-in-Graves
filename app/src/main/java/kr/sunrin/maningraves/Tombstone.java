package kr.sunrin.maningraves;

import android.graphics.Rect;

public class Tombstone extends Structure
{
    public Tombstone(int num, int select)
    {
        super(num, select);
    }

    boolean update(Player player) {
        super.update();
        if (Rect.intersects(player.rect, super.rect) && player.isSuperarmour() == false) {
            if(player.isShield())
            {
                player.useShield();
                return false;
            }
            return true;
        }
        return false;
    }

}
