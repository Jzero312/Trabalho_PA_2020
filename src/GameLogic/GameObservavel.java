/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic;

/**
 *
 * @author ze1
 */

import GameLogic.StateMachine.StatesInfo;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class GameObservavel {
    private PropertyChangeSupport propertyChangeSupport;
    private Game game;
    
    public GameObservavel(Game game){
        this.game = game;
        propertyChangeSupport = new PropertyChangeSupport(game);
    }
    
    public void setGame(Game game){
        this.game = game;
         propertyChangeSupport.firePropertyChange(null, false, true);
    }
    
    public Game getGame(){
        return game;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    
    public void StartNewGame(){
        game.StartNewGame();
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    
    public void ChoseShip(int ship){
        switch(ship){
            case 1:
                game.ChoseMiningShip();
            case 2:
                game.ChoseMilitaryShip();
        }
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    
    public void Travel(){
        game.Travel();
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    
    public void LandOnPlanet(){
        game.LandOnPlanet();
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    
    public void Buy(int buy){
        switch(buy){
            case 1:
                game.UpradeCargoHold();
                break;
            case 2:
                game.HireCrew();
                break;
            case 3:
                game.UpgradeWeponSystem();
                break;
            case 4:
                game.PurchaseMiningDrone();
                break;
        }

        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    
    public void Move(int direction){
        game.Move(direction);
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    
    public void RecallToShip(){
        game.RecallToShip();
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    
    public void ConvertResources(int Res){
        switch(Res){
            case 1:
                game.ConvertResourceIntoFuel();
                break;
            case 2:
                game.ConvertResourcesIntoAMMO();
                break;
            case 3:
                game.ConvertResourcesIntoEnergyShield();
                break;
        }
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    
    public void ExitConvert(){
        game.ExitConvert();
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    
    public void GameEnded(){
        game.GameEnded();
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    
    //------------------------Cheats--------------------------------------------
    
//    public void CMD_Event(int opt){
//            game.CMD_Event(opt);
//            propertyChangeSupport.firePropertyChange(null, false, true);
//    }
    
    public void CMD_MAXResources(){
        game.CMD_MAXResources();
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    
    public void CMD_ADDArtefact(){
        game.CMD_ADDArtefact();
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    
    public void CMD_ADDDrone(){
        game.CMD_ADDDrone();
        propertyChangeSupport.firePropertyChange(null, false, true);
    }
    
    //--------------------------------------------GET_INFO----------------------
    
    public StatesInfo getStateInfo(){
        return game.getStateInfo();
    }
    
    public int getPlanetByNumber(){
        return game.getPlanetByNumber();
    }
    
    public int getObjectByNumber(int x){
       return game.getObjectByNumber(x);
    }
    
    public String getSystemMSG(){
        return game.getSystemMSG();
    }
    
    public void ResetSystemMSG(){
        game.ResetSystemMSG();
    }
    
    public int getDroneLife(){
        return game.getDroneLife();
    }
    
    public int getResourceTypeByNumber(){
        return game.getResourceTypeByNumber();
    }
    
    public int[] getAllPositions(){
        return game.getAllPositions();
    }
    
    public Boolean hasWonTheGame(){
        return game.hasWonTheGame();
    }
    
    //
    
    public void GameMode(int x){
        game.GameMode(x);
    }
}
