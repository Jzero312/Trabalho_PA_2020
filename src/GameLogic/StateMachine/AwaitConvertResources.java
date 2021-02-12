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
public class AwaitConvertResources extends StateAdapter{
    private final IState prevState;
    
    public AwaitConvertResources(GameData gameData, IState prevState) {
        super(gameData);
        this.prevState = prevState;
    }

    @Override
    public IState ConvertResourceIntoFuel() {
        if(this.gameData.canConvertResourceIntoFuel())
            this.gameData.ConvertResourceIntoFuel();
        return this;
    }

    @Override
    public IState ConvertResourcesIntoAMMO() {
        if(this.gameData.canConvertResourcesIntoAMMO())
            this.gameData.ConvertResourcesIntoAMMO();
        return this;
    }

    @Override
    public IState ConvertResourcesIntoEnergyShield() {
        if(this.gameData.canConvertResourcesIntoEnergyShield())
            this.gameData.ConvertResourcesIntoEnergyShield();
        return this;
    }

    @Override
    public IState ExitConvert() {
        return this.prevState;
    }
    
    public StatesInfo getStateInfo(){
        return StatesInfo.ACR;
    }
    
    
    
    
    
    
}
