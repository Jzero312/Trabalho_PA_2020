/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.Planets;

import GameLogic.Entities.BlueResource;
import GameLogic.Entities.Dice;
import GameLogic.Entities.RedResource;

/**
 *
 * @author ze1
 */
public class RedPlanet extends Planet{
    
    
    public RedPlanet() { 
        super("Red",2);
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
        
        if(rand <= 50){
            this.Terrene[x][y] = new RedResource();
            this.resourceImprovisa = 2;
        }
        else{
            this.Terrene[x][y] = new BlueResource();
            this.resourceImprovisa = 4;
                    } 
        
        resX = x;
        resY = y;
            
    }
    
    @Override
    public int getPlanetByNumber(){
        return 2;
    }
    
}
