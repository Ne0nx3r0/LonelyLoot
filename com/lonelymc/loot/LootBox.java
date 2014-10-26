package com.lonelymc.loot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class LootBox {
    private final String node;
    private final LootBox parent;
    private final int rarity;
    private final ArrayList<LootBox> children;
    private final LootGenerator lootGenerator;
    private int childrenTotalRarity;
    
    public LootBox(String node,LootBox parent,int rarity,LootGenerator lg){
        this.node = node;
        this.parent = parent;
        this.rarity = rarity;
        this.lootGenerator = lg;
        
        this.children = new ArrayList<>();
        
        this.childrenTotalRarity = 0;
    }
    
    public boolean hasParent(){
        return this.parent != null;
    }
    
    public LootBox getParent(){
        return this.parent;
    }
    
    public Iterable<LootBox> getChildren(){
        return this.children;
    }
    
    public int getRarity(){
        return this.rarity;
    }
    
    public boolean hasChildren(){
        return !this.children.isEmpty();
    }
    
    public void addChild(LootBox node){
        
        this.children.add(node);
        
        Collections.sort(this.children, new Comparator<LootBox>() {
            @Override
            public int compare(LootBox box1, LootBox box2) {
                return box1.getRarity() - box2.getRarity();
            }
        });
        
        this.childrenTotalRarity = 0;
        
        Iterator<LootBox> it = this.children.iterator();

        while(it.hasNext()){
            this.childrenTotalRarity += it.next().getRarity();
        }
    }

    public int getChildrenTotalRarity(){
        return this.childrenTotalRarity;
    }

    public String getNode() {
        return this.node;
    }
}
