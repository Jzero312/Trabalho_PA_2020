/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.StateMachine;
import GameLogic.*;
import java.io.Serializable;

/**
 *
 * @author ze1
 */
public abstract class StateAdapter implements IState, Serializable{
    protected GameData gameData;
    
    public StateAdapter (GameData gameData) {
        this.gameData = gameData;
    }
    
    public GameData getGameData() {
        return this.gameData;
    }

    /***************************************************************************
     * @return 
     *                          @Override's
     */
    
    @Override
    public IState ChoseMilitaryShip() {
        return this;
    }

    @Override
    public IState ChoseMiningShip() {
        return this;
    }

    @Override
    public IState LandOnPlanet() {
        return this;
    }

    @Override
    public IState LoadGame() {
        return this;
    }


    @Override
    public IState Move(int direction) {
        return this;
    }

    @Override
    public IState RecallToShip() {
        return this;
    }

    @Override
    public IState StartNewGame() {
        return this;
    }

    @Override
    public IState Travel() {
        return this;
    }

    @Override
    public IState UpradeCargoHold() {
        return this;
    }


    @Override
    public IState ConvertResource(int from, int to) {
        return this;
    }


    @Override
    public IState HireCrew() {
        return this;
    }


    @Override
    public IState PurchaseMiningDrone() {
        return this;
    }


    @Override
    public IState UpgradeWeponSystem() {
        return this;
    }

    @Override
    public IState ConvertResourceIntoFuel(){
        return this;
    }

    @Override
    public IState ConvertResourcesIntoAMMO() {
        return this;
    }

    @Override
    public IState ConvertResourcesIntoEnergyShield() {
        return this;
    }

    @Override
    public IState ExitConvert() {
        return this;
    }
    
    @Override
    public abstract StatesInfo getStateInfo();
    
    @Override
    public IState GameEnded(){
        return this;
    }

    
    
    
    

}
