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
public class AwaitGameStart extends StateAdapter{

    public AwaitGameStart(GameData gameData) {
        super(gameData);
    }

    @Override
    public IState StartNewGame() {
        return new AwaitShipSelection(this.getGameData());
    }
    
    
    
    public StatesInfo getStateInfo(){
        return StatesInfo.AGS;
    }
    
    
}
