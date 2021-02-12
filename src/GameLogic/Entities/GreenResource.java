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
public class GreenResource extends Entitie{

    public GreenResource() {
        super("Green Resource", 'R');
    }
    
    @Override
    public Boolean isCollactable() {
        return true;
    }
    
    @Override
    public String storeCollactable(int n, SpaceShip spaceShip) {
        if(spaceShip.addGreenResources(n))
            return "Dispensar is full!\nExcess discarted!";
        return "Added with sucess!";
    }
    
}
