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
public class AwaitActionsWhiteSector extends StateAdapter{

    public AwaitActionsWhiteSector(GameData gameData) {
        super(gameData);
    }

    @Override
    public IState LandOnPlanet() {
        /*
                IF CAN LAND?
                    GERA TUDO
                    RETURN aWAITeXPLORATION(...);
        */
        if(this.gameData.hasGameEnded()){
            return new AwaitEnd(this.getGameData());
        }
        else if(this.gameData.canLandOnPlanet()){
            this.gameData.LandOnPlanet();
            return new AwaitExploration(this.getGameData(), this);
        }
        //por agora deixo this... Mas posso verificar se o fuel acabou
        //ou se o fuel acabou o jogo informa que acabou e verifico se o jogo acabou
        return this;
    }

    @Override
    public IState Travel() {     
        if(this.gameData.hasGameEnded()){
            return new AwaitEnd(this.getGameData());
        }
        else if(this.gameData.canTravel()){
            this.gameData.NextTurn();
            if(this.gameData.isSectorWhite())
                return this;
            else
                return new AwaitActionRedSector(this.getGameData());
        }
        return this; //sinceramente ñ estou a ver um return this a ser necessario ...
    }
    
    public StatesInfo getStateInfo(){
        return StatesInfo.AAWS;
    }
    
  
    
    
    
}
