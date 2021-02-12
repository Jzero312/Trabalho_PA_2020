/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.StateMachine;

/**
 *
 * @author ze1
 */
public interface IState {
    
    //**************************************************************************
    //*************************AwaitGameStart***********************************
    //**************************************************************************    
    IState StartNewGame();
    IState LoadGame();
    //**************************************************************************
    //*************************AwaitShipSelection*******************************
    //**************************************************************************
    IState ChoseMiningShip();
    IState ChoseMilitaryShip();
    //**************************************************************************
    //*************************ACTION ON SECTOR*********************************
    //**************************************************************************
    IState Travel();
    IState LandOnPlanet();
    //************************RedSector*****************************************
    IState UpradeCargoHold();
    IState ConvertResource(int from, int to);
    IState HireCrew();
    IState UpgradeWeponSystem();
    IState PurchaseMiningDrone();
    //**************************************************************************
    //*************************AwaitExploration*********************************
    //**************************************************************************
    IState Move(int direction);
    IState RecallToShip();
    //**************************************************************************
    //*************************AwaitConvertResources****************************
    //************************************************************************** 
    IState ConvertResourcesIntoEnergyShield();
    IState ConvertResourcesIntoAMMO();
    IState ConvertResourceIntoFuel();
    IState ExitConvert();
    //**************************************************************************
    IState GameEnded();
    
    StatesInfo getStateInfo();
}
