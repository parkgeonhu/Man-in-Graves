package kr.sunrin.maningraves;

import android.graphics.Rect;

import java.util.Random;

public class ItemFast extends Structure{
    private int selectItem;
    private boolean isremove = false;
    Random rand = new Random();
    final int Y1 = 265 - 65;
    final int Y2 = 265 + 15;

    public ItemFast(int select)
    {

        super(0,select);
        selectItem = rand.nextInt(2);
        if (select == 0) setCenterY(Y1);
        else setCenterY(Y2);
    }

    public void update(Player player[], int select)
    {
        super.update();

        if(Rect.intersects(player[select].rect, rect))
        {
            if(selectItem == 0) {
                for (int i = 0; i < 2; i++) {
                    player[i].moveItem(1);
                }
            }
            else { player[select].addShield(); }
            isremove = true;
        }
    }

    public boolean isRemove() { return isremove;}

    public int getSelect()  { return selectItem;}
}
