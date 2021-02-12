/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.Planets;

import GameLogic.Entities.*;
import java.io.Serializable;


/**
 *
 * @author ze1
 */
public abstract class Planet implements Serializable{
    protected String Color;
    protected int MAXMiningTimes;
    protected int MiningTimes;
    protected Entitie[][] Terrene;
    
    protected MiningDrone miningDrone;
    protected int xStart;
    protected int yStart;
    
    protected Alien alien;
    
    protected int resourceImprovisa;
    
    protected int resX, resY;
    
    
    //protected RedResource resource;
    
    public Planet (String color, int MAXMiningTimes) {
        this.Color = color;
        this.MAXMiningTimes = MAXMiningTimes;
        this.MiningTimes = 0;
    }
    
    //**************************************************************************
    //******************************gets****************************************
    //**************************************************************************
    
    public String getColor(){
        return this.Color;
    }
    
    public Boolean canMine(){
        return this.MAXMiningTimes > this.MiningTimes;
    }
    
    public void GerateTerraneToLand(){
        this.Terrene = new Entitie[6][6];
        this.miningDrone = new MiningDrone();
        this.alien = new Alien();
        SpawnMiningDrone();
        SpawnAlien();
        SpawnResourceOrArtifact();
        this.MiningTimes++;
    }
    
    protected void SpawnMiningDrone(){
        Dice dice = new Dice();
        this.miningDrone.setxpos(dice.getRandom(0, 6));
        this.miningDrone.setypos(dice.getRandom(0, 6));
        this.Terrene[this.miningDrone.getxpos()][this.miningDrone.getypos()] = this.miningDrone;
        this.xStart = this.miningDrone.getxpos();
        this.yStart = this.miningDrone.getypos();  
    }
    
    protected void SpawnAlien(){
        Dice dice = new Dice();
        int rand = dice.getRandom(1, 100);
        this.alien = new Alien();
        if(rand <= 25)
            this.alien.setType("Black Alien", 17, 34);
        else if(rand <= 50)
            this.alien.setType("Green Alien", 34, 50);
        else if(rand <= 75)
            this.alien.setType("Blue Alien", 50, 50);
        else
            this.alien.setType("Red Alien", 34, 34);
        
        int x,y;
        
        do{
            x = dice.getRandom(0, 5);
            y = dice.getRandom(0, 5);
        }while(this.Terrene[x][y] != null);
        
        this.Terrene[x][y] = this.alien;
        this.alien.setxpos(x);
        this.alien.setypos(y); 
    }
    
    protected abstract void SpawnResourceOrArtifact();
    
    public Boolean hasAlienAround(){
        if(checkAlienInPos(this.miningDrone.getxpos()-1, this.miningDrone.getypos()))
            return true;
        if(checkAlienInPos(this.miningDrone.getxpos(), this.miningDrone.getypos() + 1))
            return true;
        if(checkAlienInPos(this.miningDrone.getxpos() + 1, this.miningDrone.getypos()))
            return true;
        if(checkAlienInPos(this.miningDrone.getxpos(), this.miningDrone.getypos() - 1))
                return true;
        return false;
    }
    
    protected Boolean checkAlienInPos(int x, int y){
        if(x < 0 || y < 0 || x>=6 || y>=6)
            return false;
        if(this.Terrene[x][y] == null)
            return false;
        return this.Terrene[x][y].isAlien();
    }
    
    public Boolean canMiningDroneContinue(){
        return this.miningDrone.getLife() > 0;
    }
    
    public Boolean canMove(int direction){
        switch(direction){
            case 1: //UP
                return canMoveto(this.miningDrone.getxpos()-1,this.miningDrone.getypos());
            case 2: //DOWN
                return canMoveto(this.miningDrone.getxpos()+1,this.miningDrone.getypos());
            case 3: //RIGHT
                return canMoveto(this.miningDrone.getxpos(),this.miningDrone.getypos()+1);
            case 4: //LEFT
                return canMoveto(this.miningDrone.getxpos(),this.miningDrone.getypos()-1);
        }
        return false;
    }
    
    protected Boolean canMoveto(int x, int y){
         if(x < 0 || y < 0 || x>=6 || y>=6)
            return false;
         if(this.Terrene[x][y] == null)
             return true;
         return !this.Terrene[x][y].isAlien();
    }
    
    public void move(int direction){
        switch(direction){
            case 1: //esquerda
                this.miningDrone.move(this.miningDrone.getxpos()-1 , this.miningDrone.getypos(), Terrene);
                break;
            case 2: //direita
                this.miningDrone.move(this.miningDrone.getxpos()+1, this.miningDrone.getypos(), Terrene);
                break;
            case 3: //baixo
                this.miningDrone.move(this.miningDrone.getxpos(), this.miningDrone.getypos()+1, Terrene);
                break;
            case 4: //cima
                this.miningDrone.move(this.miningDrone.getxpos(), this.miningDrone.getypos()-1, Terrene);
                break;
        }
        
    
        
    }
    
    public void AlienMove(){
        this.alien.move(this.miningDrone.getxpos(),this.miningDrone.getypos(),this.Terrene);
    }
    
    public String fightAlien(){
        String actions = "";
        int turn = 0;
        
        while(this.miningDrone.isAlive() && this.alien.isAlive()){
            if(turn % 2 == 0){
                actions += this.alien.attack(this.miningDrone);
            }else{
                actions += this.alien.defend();
            }
            turn++;
        }
        return actions;
        
    }
    
    public void respawnAlien(){
        this.Terrene[this.alien.getxpos()][this.alien.getypos()]=null;
        this.SpawnAlien();
    }
    
    public Boolean canRecall(){
        return this.miningDrone.canRecall(this.xStart,this.yStart);
    }
    
    public Entitie getItensColected(){
        return this.miningDrone.getItensColected();
    }

    @Override
    public String toString() {
        String aux;
        aux = "Recal[" + (this.xStart+1) + "," + (this.yStart+1) + "];";
        aux += "\nDrone Life: " + this.miningDrone.getLife();
        aux += "\nAlien life: " + this.alien.isAlive();
        
        aux +="\n  1 2 3 4 5 6";
        for(int i = 0; i<6;i++){
            aux += "\n ";
            aux +=i+1;
            for(int j=0; j<6; j++){
                if(this.Terrene[i][j] == null)
                    aux += "  ";
                else 
                    aux += this.Terrene[i][j].getRep() + " ";
            }
        }
        
        return aux;
    }
    
    public int getPlanetByNumber(){
        return 0;
    }
    
    public int getDroneLife(){
        return miningDrone.getLife();
    }
    
    public int getResourceTypeByNumber(){
        return this.resourceImprovisa;
    }
    
    private boolean ResourceColected(){
        return this.miningDrone.ResourceColected();
    }
    
    public int[] getAllPositions(){
        int[] pos = new int[8];
        pos[0] = this.alien.getxpos();
        pos[1] = this.alien.getypos();
        pos[2] = this.miningDrone.getxpos();
        pos[3] = this.miningDrone.getypos();
        pos[4] = this.xStart;
        pos[5] = this.yStart;
        if(this.miningDrone.ResourceColected()){
            pos[6] = -1;
            pos[7] = -1;
        }else{
            pos[6] = this.resX;
            pos[7] = this.resY;
        }
        
        
        
        return pos;
    }
    

    
}
