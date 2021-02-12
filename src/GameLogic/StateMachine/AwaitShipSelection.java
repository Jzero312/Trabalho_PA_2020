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
public class AwaitShipSelection extends StateAdapter{

    public AwaitShipSelection(GameData gameData) {
        super(gameData);
    }

    @Override
    public IState ChoseMilitaryShip() {
        this.gameData.PlayerChoseMilitaryShip();
            return new AwaitActionsWhiteSector(this.getGameData());
    }

    @Override
    public IState ChoseMiningShip() {
        this.gameData.PlayerChoseMiningShip();
        return new AwaitActionsWhiteSector(this.getGameData());
    }
    
    public StatesInfo getStateInfo(){
        return StatesInfo.ASS;
    }
    
    
    
  
    
    
}
