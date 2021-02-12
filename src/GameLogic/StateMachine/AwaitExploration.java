/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.StateMachine;

import GameLogic.GameData;

/**
 *
 * @author ze1
 */
public class AwaitExploration extends StateAdapter{
    private final IState prevState;
    
    public AwaitExploration(GameData gameData, IState prev) {
        super(gameData);
        this.prevState = prev;
    }

    @Override
    public IState Move(int direction) {
        if(this.gameData.hasAlienAround()){
            this.gameData.fightAlien();
            if(!this.gameData.canMiningDroneContinue()){
                this.gameData.fightlost();
                return this.prevState;
            }
            else{
                this.gameData.respawnAlien();
                return this; //isto faz com que o player acabe o turno dps da luta
            }
        }
        else if(this.gameData.canMove(direction)){
            this.gameData.move(direction);
            this.gameData.AlienMove();
            
        }
        return this;        
    }

    

    @Override
    public IState RecallToShip() {
        if(this.gameData.canRecall()){
            this.gameData.colectResources();
            if(this.gameData.canConverResources())
                return new AwaitConvertResources(this.getGameData(), this.prevState);
            else
                return this.prevState;  
        }
        return this;
    }
    
    public StatesInfo getStateInfo(){
        return StatesInfo.AEXP;
    }
    
}
