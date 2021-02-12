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
public class AwaitActionRedSector extends StateAdapter{

    public AwaitActionRedSector(GameData gameData) {
        super(gameData);
    }
    
    @Override
    public IState LandOnPlanet() {
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
            if(!this.gameData.isSectorWhite())
                return this;
            else
                return new AwaitActionsWhiteSector(this.getGameData());
        }
        return this; //sinceramente ñ estou a ver um return this a ser necessario ...
    }

    @Override
    public IState UpradeCargoHold() {
        if(this.gameData.canUpgradeCargoHold())
            this.gameData.UpgradeCargoHold();
        return this; 
    }

    @Override
    public IState ConvertResource(int from, int to) {
        if(this.gameData.canConvertResource(from, to))
            this.gameData.ConvertResource(from, to);
        return this;
    }

    @Override
    public IState HireCrew() {
        if(this.gameData.canHireCrew())
            this.gameData.HireCrew();
        return this;
    }

    @Override
    public IState PurchaseMiningDrone() {
        if(this.gameData.canPurchaseMiningDrone())
            this.gameData.PurchaseMiningDrone();
        return this;
    }

    @Override
    public IState UpgradeWeponSystem() {
        if(this.gameData.canUpgradeWeponSystem())
            this.gameData.UpgradeWeaponSystem();
        return this;
    }
    
    public StatesInfo getStateInfo(){
        return StatesInfo.AARS;
    }
      
}
