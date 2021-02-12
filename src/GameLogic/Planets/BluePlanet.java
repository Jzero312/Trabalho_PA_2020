/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.Planets;

import GameLogic.Entities.Artifact;
import GameLogic.Entities.BlackResource;
import GameLogic.Entities.BlueResource;
import GameLogic.Entities.Dice;
import GameLogic.Entities.GreenResource;

/**
 *
 * @author ze1
 */
public class BluePlanet extends Planet{
    
    public BluePlanet(){
        super("Blue",3);
    }


    @Override
    protected void SpawnResourceOrArtifact() {
        int x,y;
        Dice dice = new Dice();
        
        do{
            x = dice.getRandom(0, 5);
            y = dice.getRandom(0, 5);
        }while(this.Terrene[x][y] != null);
        
        switch(dice.getRandom(1, 4)){
            case 1:
                this.Terrene[x][y] = new Artifact();
                this.resourceImprovisa = 5;
                break;
            case 2:
                this.Terrene[x][y] = new BlackResource();
                this.resourceImprovisa = 1;
                break;
            case 3:
                this.Terrene[x][y] = new GreenResource();
                this.resourceImprovisa = 3;
                break;
            case 4:
                this.Terrene[x][y] = new BlueResource();
                this.resourceImprovisa = 4;
                break;
        }  
        
        resX = x;
        resY = y;
    }
    
    @Override
    public int getPlanetByNumber(){
        return 4;
    }
    
}
