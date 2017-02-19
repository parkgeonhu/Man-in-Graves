package kr.sunrin.maningraves;

import java.util.ArrayList;
import java.util.Random;

import kr.sunrin.framework.Graphics;
import kr.sunrin.framework.Image;

public class StructureManager
{
    private ArrayList<Tombstone> tombstones = new ArrayList<Tombstone>();
    private ArrayList<ItemFast> items = new ArrayList<ItemFast>();
    private ArrayList<SkeletonHand> skeletonHands = new ArrayList<SkeletonHand>();
    private int select, num;
    private final int MAX_STRUCTURE = 8;
    private boolean isMake = true;
    private static boolean length = false;
    private static int selectCheck;
    private static int structureCount=4;
    public static int term = 1000;

    Random rand = new Random();

    public StructureManager(int select)
    {
        this.select = select - 1;
        term = 1000;
    }

    public void newStructure() {
        num = rand.nextInt(MAX_STRUCTURE);
        if (length && selectCheck == select) {
            length = false;
        }
        if (num > 2 && length == false) {
            length = true;
            selectCheck = select;
        }
        if (length && selectCheck != select) {
            num = rand.nextInt(3);
        }
        switch (rand.nextInt(51)/50) {
            case 0:
                if (rand.nextInt(2) == 0) {
                    for (int i = 0; i < num; i++) {
                        Tombstone tombstone = new Tombstone(i, select);
                        tombstones.add(tombstone);
                    }
                } else {
                    for (int i = 0; i < num; i++) {
                        SkeletonHand skeletonHand = new SkeletonHand(i, select);
                        skeletonHands.add(skeletonHand);
                    }
                }
                break;
            case 1:
                ItemFast item = new ItemFast(select);
                items.add(item);
                break;
        }
    }

    public void update(Player player[], int select, GameScreen gameScreen)
    {
        isMake = true;
        for (int i = 0; i < tombstones.size(); i++) {
            Tombstone p = (Tombstone) tombstones.get(i);
            if(p.update(player[select])) { gameScreen.GAMEOVER = true; }
            if(p.getCenterX() > term + (rand.nextInt(structureCount)) * 50) { isMake = false; }
        }
        for (int i = 0; i < skeletonHands.size(); i++) {
            SkeletonHand p = (SkeletonHand) skeletonHands.get(i);
            p.update(player, select);
            if(p.getCenterX() > term + (rand.nextInt(structureCount) * 50)) { isMake = false; }
        }
        for(int i=0; i < items.size();i++)
        {
            ItemFast p = (ItemFast) items.get(i);
            p.update(player, select);
            if(p.getCenterX() > term + (rand.nextInt(structureCount) * 50)) { isMake = false; }
        }

        for(int i=0; i < items.size();i++)
        {
            ItemFast p = (ItemFast) items.get(i);
            if(p.isRemove() || p.getCenterX() < -50) { if(p.isRemove()) { gameScreen.addScore(1000); Assets.itemget.play(0.5f); } items.remove(i);}
        }
        for (int i = 0; i < tombstones.size(); i++) {
            Tombstone p = (Tombstone) tombstones.get(i);
            if(p.getCenterX() < - 50) { tombstones.remove(i); gameScreen.addScore(130);}
        }
        for (int i = 0; i < skeletonHands.size(); i++) {
            SkeletonHand p = (SkeletonHand) skeletonHands.get(i);
            if(p.getCenterX() < - 50) { skeletonHands.remove(i); gameScreen.addScore(100); }
        }

        if(isMake) { newStructure(); }
    }

    public void paint(Graphics g, Image hand, Image stone)
    {
        if(select == 0) {
            for (int i = 0; i < tombstones.size(); i++) {
                Tombstone p = (Tombstone) tombstones.get(i);
                g.drawImage(stone, p.getCenterX() - 25, p.getCenterY() - 25);
            }

            for (int i = 0; i < skeletonHands.size(); i++) {
                SkeletonHand p = (SkeletonHand) skeletonHands.get(i);
                g.drawImage(hand, p.getCenterX() - 25, p.getCenterY() - 25);
            }

            for (int i = 0; i < items.size(); i++) {
                ItemFast p = (ItemFast) items.get(i);
                if (p.getSelect() == 0) {
                    g.drawImage(Assets.moving, p.getCenterX() - 25, p.getCenterY() - 25);
                } else {
                    g.drawImage(Assets.shield, p.getCenterX() - 25, p.getCenterY() - 25);
                }
            }
        }
        else
        {
            for (int i = 0; i < tombstones.size(); i++) {
                Tombstone p = (Tombstone) tombstones.get(i);
                g.drawImage(Assets.tombstone2, p.getCenterX() - 25, p.getCenterY() - 25);
            }

            for (int i = 0; i < skeletonHands.size(); i++) {
                SkeletonHand p = (SkeletonHand) skeletonHands.get(i);
                g.drawImage(Assets.skeletonHand2, p.getCenterX() - 25, p.getCenterY() - 25);
            }

            for (int i = 0; i < items.size(); i++) {
                ItemFast p = (ItemFast) items.get(i);
                if (p.getSelect() == 0) {
                    g.drawImage(Assets.moving2, p.getCenterX() - 25, p.getCenterY() - 25);
                } else {
                    g.drawImage(Assets.shield2, p.getCenterX() - 25, p.getCenterY() - 25);
                }
            }
        }
    }

    public void setFinalSpeedX(int speedX)
    {
        for (int i = 0; i < tombstones.size(); i++) {
            Tombstone p = (Tombstone) tombstones.get(i);
            p.setSpeedX(speedX);
        }
        for (int i = 0; i < skeletonHands.size(); i++) {
            SkeletonHand p = (SkeletonHand) skeletonHands.get(i);
            p.setSpeedX(speedX);
        }
        for (int i = 0; i < items.size(); i++) {
            ItemFast p = (ItemFast) items.get(i);
            p.setSpeedX(speedX);
        }
    }

}
