/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.Entities;

import java.io.Serializable;

/**
 *
 * @author ze1
 */
public class Dice {
    
    public Dice(){
        
    }
    
    public int getRoll(){
        return (int)(Math.random()*6+1);
    }
    
    public int getRandom(int min,int max){
        return (int)(Math.random()*max+min);
    }
}
