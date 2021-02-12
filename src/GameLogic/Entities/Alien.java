/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.Entities;

import java.util.List;

/**
 *
 * @author ze1
 */
public class Alien extends Entitie{
    private int Life;
    private int xpos;
    private int ypos;
    
    private Boolean xmove;
    private Boolean ymove;
    
    private int probAttack;
    private int probDeath;
    
    
    public Alien(){
        super("Alien", 'E');
        this.Life = 1;       
        
    }
    
    public void setType(String Type, int probAttack, int probDeath ){
        this.Type = Type;
        this.probAttack = probAttack;
        this.probDeath = probDeath;
        this.Life = 1;
        this.xmove = true;
        this.ymove = true;
    }
    
    public Boolean isAlive(){
        return this.Life > 0;
    }
    
    

    @Override
    public Boolean isAlien() {
        return true;
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
    
//    public void move(int xTarget, int yTarget, Entitie[][] terrene){
//        if(this.xpos == xTarget){
//            this.xmove = true;
//            this.ymove = true;
//            if(this.ypos < yTarget){
//                if(changePosition(this.xpos, this.ypos+1, terrene)){
//                    return;
//                }
//            }
//            if(this.ypos > yTarget){
//                if(changePosition(this.xpos, this.ypos-1, terrene)){
//                return;
//                }   
//            }
//        }
//        
//        if(this.ypos == yTarget){
//            this.xmove = true;
//            this.ymove = true;
//            if(this.xpos < xTarget){
//                if(changePosition(this.xpos+1, this.ypos, terrene)){  
//                    return;
//                }
//            }
//            if(this.xpos > xTarget){
//                if(changePosition(this.xpos-1, this.ypos, terrene)){  
//                    return;
//                }
//            }
//        }
//        
//        boolean corner = Corner(xTarget,yTarget);
//        boolean Diagonal = inOnDiagonal(this.xpos-xTarget, this.ypos-yTarget);
//        //(this.xmove || inOnDiagonal(this.xpos-xTarget, this.ypos-yTarget))
//        
//        
//        if(this.xpos < xTarget && Decicion(this.xmove, Diagonal, corner)){
//            if(changePosition(this.xpos+1, this.ypos, terrene)){ 
//                this.xmove=false;
//                this.ymove=true;
//                return;
//            }
//        }
//        if(this.xpos > xTarget && Decicion(this.xmove, Diagonal, corner)){
//            if(changePosition(this.xpos-1, this.ypos, terrene)){  
//                this.ymove=true;
//                this.xmove=false;
//                return;
//            }
//        }
//        if(this.ypos < yTarget && Decicion(this.ymove, Diagonal, corner)){
//            if(changePosition(this.xpos, this.ypos+1, terrene)){
//                this.xmove=true;
//                this.ymove=false;
//                return;
//            }
//        }
//        if(this.ypos > yTarget && Decicion(this.ymove, Diagonal, corner)){
//            if(changePosition(this.xpos, this.ypos-1, terrene)){
//                this.xmove=true;
//                this.ymove=false;
//               return;
//            }
//        }
//    }
    
    public void move(int xTarget, int yTarget, Entitie[][] terrene){
        if(this.xpos == xTarget){
            if(this.ypos < yTarget)
                if(changePosition(this.xpos, this.ypos+1, terrene))
                    return;
            if(this.ypos > yTarget)
                if(changePosition(this.xpos, this.ypos-1, terrene))
                return;
        }
        if(this.ypos == yTarget){
            if(this.xpos < xTarget)
                if(changePosition(this.xpos+1, this.ypos, terrene))
                    return;
            if(this.xpos > xTarget)
                if(changePosition(this.xpos-1, this.ypos, terrene))
                    return;
        }

        boolean UPandDown = PrioritizeDirection(this.xpos - xTarget, this.ypos - yTarget);
        
        if(this.xpos < xTarget && !UPandDown)
            if(changePosition(this.xpos+1, this.ypos, terrene))
                return;
        if(this.xpos > xTarget && !UPandDown)
            if(changePosition(this.xpos-1, this.ypos, terrene))  
                return;
        if(this.ypos < yTarget && UPandDown)
            if(changePosition(this.xpos, this.ypos+1, terrene))
                return;
        if(this.ypos > yTarget && UPandDown)
            if(changePosition(this.xpos, this.ypos-1, terrene))
               return;
    }
        
        //determinar o lado mais comprido a percorrer
        private Boolean PrioritizeDirection(int dist1, int dist2){
        dist1 = Math.abs(dist1);
        dist2 = Math.abs(dist2);
        
        return (dist1<dist2);
    }
    
//    private Boolean Decicion(boolean move, boolean diagn, boolean corner){
//        if(corner && diagn)
//            return move;
//        else if(diagn)
//            return diagn;
//        else
//            return move;
//    }
    

    
//    private Boolean inOnDiagonal(int dist1, int dist2){
//        double hypotenuse = Math.sqrt(Math.pow(dist1, 2) + Math.pow(dist2, 2));
//        return hypotenuse == Math.sqrt(5);
//    }
//    
//    private Boolean Corner(int x, int y){
//        if(x==0 && y==0)
//            return true;
//        else if(x==0 && y==6)
//            return true;
//        else if(x==6 && y==0)
//            return true;
//        else if(x==6 && y==6)
//            return true;
//        return false;
//    }
    
//    private Boolean TargetOnDiagonals(int x, int y){
//        if(this.xpos+1==x && this.ypos+1==y)
//            return true;
//        else if(this.xpos+1==x && this.ypos-1==y)
//            return true;
//        else if(this.xpos-1==x && this.ypos-1==y)
//            return true;
//        else if(this.xpos-1==x && this.ypos+1==y)
//            return true;
//        return false;
//    }
    
    private Boolean changePosition(int xTo, int yTo, Entitie[][] terrene){
        if(terrene[xTo][yTo] != null){
            return false;
        }
        terrene[xTo][yTo] = this;
        terrene[this.xpos][this.ypos] = null;
        this.xpos = xTo;
        this.ypos = yTo;
        return true;
    }
    
    public String attack(MiningDrone miningDrone){
        Dice dice = new Dice();
        
        if(this.probAttack >= dice.getRandom(1, 100)){
            miningDrone.lossOneLife();
            return "The Alien Attack and you lost 1 HP\n";
        }else{
            return "The Alien Attack miss\n";
        }
    }
    
    public String defend(){
        Dice dice = new Dice();
        
        if(this.probDeath >= dice.getRandom(1, 100)){
            this.Life--;
            return "You attack witch sucess!\n";
        }else{
            return "Your attack miss!\n";
        }
    }
    
    
}
