package kr.sunrin.maningraves;

import android.graphics.Rect;

/**
 * Created by Home on 2015. 7. 12..
 */
public class SkeletonHand extends Structure
{
    public SkeletonHand(int num, int select)
    {
        super(num, select);
    }

    void update(Player player[], int select) {
        super.update();
        if (Rect.intersects(player[select].rect, super.rect)) {
            player[0].moveCenterX(super.getSpeedX() + 7);
            player[1].moveCenterX(super.getSpeedX() + 7);
        }
    }

}
