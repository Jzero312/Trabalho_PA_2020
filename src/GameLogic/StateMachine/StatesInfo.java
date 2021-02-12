/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.StateMachine;

/**
 *
 * @author ze1
 */
public enum StatesInfo {
    AARS("Red Sector"),
    AAWS("White Sector"),
    ACR("Converting Resources"),
    AE("End of Game"),
    AEXP("Exploring Planet"),
    AGS("Game Menu"),
    ASS("Ship Selection");
    
    private final String Description;
    
    StatesInfo(String desc){
        this.Description = desc;
    }
    
    @Override
    public String toString(){
        return this.Description;
    }
}
