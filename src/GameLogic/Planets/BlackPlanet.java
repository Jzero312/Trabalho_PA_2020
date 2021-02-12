/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.Planets;

import GameLogic.Entities.BlackResource;
import GameLogic.Entities.BlueResource;
import GameLogic.Entities.Dice;

/**
 *
 * @author ze1
 */
public class BlackPlanet extends Planet{
    
    public BlackPlanet() {
        super("Black",2);
    }



    @Override
    protected void SpawnResourceOrArtifact() {
        int x,y;
        Dice dice = new Dice();
        int rand = dice.getRandom(1, 100);
        
        do{
            x = dice.getRandom(0, 5);
            y = dice.getRandom(0, 5);
        }while(this.Terrene[x][y] != null);
        
        //this.Terrene[x][y] = this.resource;
        
        if(rand <= 50){
            //this.resource.setType("Black Resource", 3);
            this.Terrene[x][y] = new BlackResource();
            this.resourceImprovisa = 1;
        }
        else {
            //this.resource.setType("Blue Resource", 2);
            this.Terrene[x][y] = new BlueResource();
            this.resourceImprovisa = 4;
        }
        
        resX = x;
        resY = y;
    }
    
    @Override
    public int getPlanetByNumber(){
        return 1;
    }
    
    
    
}
