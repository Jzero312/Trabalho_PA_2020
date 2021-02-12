/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.Entities;

import GameLogic.ships.SpaceShip;

/**
 *
 * @author ze1
 */
public class RedResource extends Entitie{
    //private int resourceNumber;
    
    public RedResource(){
        super("Red Resource",'R');
    }
    
//    public void setType(String Type, int resourceNumber){
//        this.Type = Type;
//        //this.resourceNumber = resourceNumber;
//    }

    @Override
    public Boolean isCollactable() {
        return true;
    }
    
    @Override
    public String storeCollactable(int n, SpaceShip spaceShip) {
        if(spaceShip.addRedResources(n))
            return "Dispensar is full!\nExcess discarted!";
        return "Added with sucess!";
    }
    
    
}
