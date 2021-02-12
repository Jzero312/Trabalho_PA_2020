/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic;

import GameLogic.StateMachine.AwaitGameStart;
import GameLogic.StateMachine.IState;
import GameLogic.StateMachine.StatesInfo;
import java.io.Serializable;

/**
 *
 * @author ze1
 */
public class Game implements Serializable{
    private GameData gameData;
    private IState iState;
    
    public Game(){
        gameData = new GameData();
        iState = new AwaitGameStart(gameData);
    }
    
    private void setState(IState iState){
        this.iState = iState;
    }
    
    
    /***************************************************************************
     * 
     */
    
    public void StartNewGame(){
        setState(iState.StartNewGame());
    }
    
    public void GameEnded(){
        setState(iState.GameEnded());
    }
    
    public void ChoseMiningShip(){
        setState(iState.ChoseMiningShip());
    }
    
    public void ChoseMilitaryShip(){
        setState(iState.ChoseMilitaryShip());
    }
    
    public void Travel(){
        setState(iState.Travel());
    }
    
    public void LandOnPlanet(){
        setState(iState.LandOnPlanet());
    }
    
    public void UpradeCargoHold(){
        setState(iState.UpradeCargoHold());
    }
    //falta na view
    public void ConvertResource(int from, int to){
        setState(iState.ConvertResource(from, to));
    }
    
    public void HireCrew(){
        setState(iState.HireCrew());
    }
    
    public void UpgradeWeponSystem(){
        setState(iState.UpgradeWeponSystem());
    }
    
    public void PurchaseMiningDrone(){
        setState(iState.PurchaseMiningDrone());
    }
    
    public void Move(int direction){
        setState(iState.Move(direction));
    }
    
    public void RecallToShip(){
        setState(iState.RecallToShip());
    }
    
    public void ConvertResourcesIntoEnergyShield(){
        setState(iState.ConvertResourcesIntoEnergyShield());
    }
    
    public void ConvertResourcesIntoAMMO(){
        setState(iState.ConvertResourcesIntoAMMO());
    }
    
    public void ConvertResourceIntoFuel(){
        setState(iState.ConvertResourceIntoFuel());
    }
    
    public void ExitConvert(){
        setState(iState.ExitConvert());
    }
    
    public StatesInfo getStateInfo(){
        return this.iState.getStateInfo();
    }
    
    public String getSystemMSG(){
        return this.gameData.getSystemMSG();
    }
    
    public void ResetSystemMSG(){
        this.gameData.ResetSystemMSG();
    }
    
    public String getMapInString(){
        return this.gameData.getMapInString();
    }
    
    public String getInformation(){
        return this.gameData.toString();
    }
    
    public String GameEnd(){
        return this.gameData.GameEnd();
    }
    
    public int getPlanetByNumber(){
        return gameData.getPlanetByNumber();
    }
    
    public int getObjectByNumber(int x){
        return gameData.getObjectByNumber(x);
    }
    
    public int getDroneLife(){
        return gameData.getDroneLife();
    }
    
    public int getResourceTypeByNumber(){
        return gameData.getResourceTypeByNumber();
    }
    
    public int[] getAllPositions(){
        return gameData.getAllPositions();
    }
    
    public Boolean hasWonTheGame(){
        return gameData.hasWonTheGame();
    }
    
    /***************************************************************************
     *                      Debug comands
     * @param opt 
     */
    public void CMD_Event(int opt){
            this.gameData.SelectedEvent(opt);
    }
    
    public void CMD_MAXResources(){
        this.gameData.CMD_MAXResources();
    }
    
    public void CMD_ADDArtefact(){
        this.gameData.CMD_ADDArtefact();
    }
    
    public void CMD_ADDDrone(){
        this.gameData.CMD_ADDDrone();
    }
    
    public void GameMode(int x){
        gameData.GameMode(x);
    }
    
}
