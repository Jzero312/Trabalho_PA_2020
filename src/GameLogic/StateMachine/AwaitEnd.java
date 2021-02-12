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
public class AwaitEnd extends StateAdapter{

    public AwaitEnd(GameData gameData) {
        super(gameData);
    }

    @Override
    public IState GameEnded() {
        return new AwaitGameStart(this.getGameData());
    }
    
    
    
    public StatesInfo getStateInfo(){
        return StatesInfo.AE;
    }
    
}
