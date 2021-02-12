/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.Entities;

/**
 *
 * @author ze1
 */
public class MiningDrone extends Entitie{
    private int Life;
    private int xpos;
    private int ypos;
    private Entitie colected;
    
    public MiningDrone(){
        super("Mining Drone", 'M');
        this.Life = 6;
        colected = null;
    }

    @Override
    public Boolean isMiningDrone() {
        return true;
    }
    
    public int getLife(){
        return this.Life;
    }
    
    public int getxpos(){
        return this.xpos;
    }
    
    public int getypos(){
        return this.ypos;
    }
    
    public void setxpos(int xpos){
        this.xpos = xpos;
    }
    
    public void setypos(int ypos){
        this.ypos = ypos;
    }
    
    public void move(int xTo,int yTo, Entitie[][] terrene){
        if(terrene[xTo][yTo] != null){
            this.colected = terrene[xTo][yTo];
        }
        terrene[xTo][yTo] = this;
        terrene[this.xpos][this.ypos]=null;
        this.xpos = xTo;
        this.ypos = yTo;
    }
    
    public Boolean isAlive(){
        return this.Life > 0;
    }
    
    public void lossOneLife(){
        this.Life--;
    }
    
    public Boolean canRecall(int x, int y){
        return (this.xpos == x && this.ypos ==y);
    }
    
    public Entitie getItensColected(){
        return this.colected;
    }
    
    public boolean ResourceColected(){
        return colected != null;
    }
   
}
