package com.lonelymc.loot;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class LonelyLoot {
    private final HashMap<String, LootBox> lootBoxes;
    
    public LonelyLoot(){
        this.lootBoxes = new HashMap<>();
        
        this.addLootBoxContainer("all", 1);
    }

    public void addLootBoxContainer(String node, int rarity){
        this.addLootBox(node, rarity, null);
    }
    
    public void addLootBox(String node, int rarity,LootGenerator lg) {
        // Root node
        if(!node.contains(".")){
            this.lootBoxes.put(node, new LootBox(node,null,rarity,lg));
            
            return;
        }
        
        String parentNode = node.substring(0, node.lastIndexOf("."));
        LootBox parentBox = this.lootBoxes.get(parentNode);
        if(parentBox == null){
            throw new NullPointerException("Parent node '"+parentNode+"' does not exist, it should be created manually before adding node '"+node+"'");
        }
        
        LootBox lb = new LootBox(node,parentBox,rarity,lg);
        
        this.lootBoxes.put(node, lb);
        
        parentBox.addChild(lb);
    }
    
    public LootBox getRandomLootBox(String startingNode,double chanceHigherNode,int maxHigherNodes,int magicFindModifier){
        LootBox box = this.lootBoxes.get(startingNode);
        
        if(box == null){
            throw new NoSuchElementException("Invalid node: "+startingNode);
        }
        
        Random random = new Random();
        
        // Roll and grab a parent box if chance wins
        if(chanceHigherNode > 0 && box.hasParent()){
            if(random.nextDouble() < chanceHigherNode){
                int nodesUp = random.nextInt(maxHigherNodes)+1;
                
                for(int i=0;i<nodesUp;i++){
                    if(box.hasParent()){
                        box = box.getParent();
                    }
                    else {//reached the oldest node
                        break;
                    }
                }
            }
        }
        
        while(box.hasChildren()){
            Iterator<LootBox> it = box.getChildren().iterator();

            int roll = random.nextInt(box.getChildrenTotalRarity())+1;
            
            while(it.hasNext()){
                LootBox next = it.next();
                
                if(roll < next.getRarity() + magicFindModifier){
                    box = next;
                    
                    break;
                }
                else {
                    roll -= next.getRarity();
                }
            }
        }
        
        return box;
    }
}
