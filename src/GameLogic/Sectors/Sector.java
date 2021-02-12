/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.Sectors;

import GameLogic.Entities.Dice;
import GameLogic.Entities.Entitie;
import GameLogic.Planets.*;
import java.io.Serializable;

/**
 *
 * @author ze1
 */
public class Sector implements Serializable{
    private final String Color;
    private final Planet planet;
    
    public Sector(String Color){        
        Dice dice = new Dice();
        int num = dice.getRandom(1, 100);
        
        if(num <=25)
            this.planet = new RedPlanet();
        else if(num <=50)
            this.planet = new GreenPlanet();
        else if(num <= 75)
            this.planet = new BluePlanet();
        else
            this.planet = new BlackPlanet();
        
        this.Color = Color;
    }
    
    public Boolean isWhite(){
        return this.Color.compareTo("White") == 0;
    }
    
    public Boolean canMine(){
        return this.planet.canMine();
    }
    
    public void LandOnPlanet(){
        this.planet.GerateTerraneToLand();
    }
    
    public Boolean hasAlienAround(){
        return this.planet.hasAlienAround();
    }
    
    public Boolean canMiningDroneContinue(){
        return this.planet.canMiningDroneContinue();
    }
    
    public Boolean canMove(int direction){
        return this.planet.canMove(direction);
    }
    
    public void move(int direction){
        this.planet.move(direction);
    }
    
    public void AlienMove(){
        this.planet.AlienMove();
    }
    
    public String fightAlien(){
        return this.planet.fightAlien();
    }
    
    public void respawnAlien(){
        this.planet.respawnAlien();
    }
    
    public Boolean canRecall(){
        return this.planet.canRecall();
    }
    
    public Entitie getItensColected(){
        return this.planet.getItensColected();
    }
    
    public int getPlanetByNumber(){
        return planet.getPlanetByNumber();
    }
    
    public int getDroneLife(){
        return planet.getDroneLife();
    }
    
    public int getResourceTypeByNumber(){
        return planet.getResourceTypeByNumber();
    }
    /***************************************************************************
     * 
     */
    
    public String getMapInString(){
        return this.planet.toString();
    }

    @Override
    public String toString() {
        String aux = "Sector Color - " + this.Color;
        aux += "\n" + this.planet.getColor();
        
        return aux;
    }
    
    public int[] getAllPositions(){
        return planet.getAllPositions();
    }
    
    
    

}
