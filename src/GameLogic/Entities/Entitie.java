/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.Entities;

import GameLogic.ships.SpaceShip;
import java.io.Serializable;

/**
 *
 * @author ze1
 */
public class Entitie implements Serializable{
    protected String Type;
    protected char Rep;    //para representar o char que aparece no mapa
    
    public Entitie(String Type, char Rep){
        this.Type = Type;
        this.Rep = Rep;
    }
    
    //**************************************************************************
    //*************************is this an?**************************************
    //**************************************************************************
    public Boolean isAlien(){
        return false;
    }
    public Boolean isArtifact(){
        return false;
    }
    public Boolean isMiningDrone(){
        return false;
    }
    public Boolean isCollactable(){
        return false;
    }


    
    //**************************************************************************
    //*************************GET'S********************************************
    //**************************************************************************
    
    public String getType(){
        return this.Type;
    }
    public char getRep(){
        return this.Rep;
    }
    
    //**************************************************************************
    public String storeCollactable(int n, SpaceShip spaceShip){
        return "";
    }
}
