/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic;
import GameLogic.Entities.*;
import GameLogic.Sectors.*;
import GameLogic.ships.*;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author ze1
 */
public class GameData implements Serializable{
    private int Artifacts;
    private int CrewMembers;       
    private final String[] CrewMenbersNames = { "Captain", "Navigation Officer", "Landing Party Officer", "Shield Officer", "Weapons Officer", "Cargo Hold Officer"}; 
    private SpaceShip spaceShip;
    private Boolean MiningDrone;
    private Sector Sector;
    
    private String SystemMSG;
    private Boolean GameEnd;
    
    //var to buys 
    //reset on turn
    private Boolean SSUCargoHold;
    private Boolean SSUConvertResource;
    private Boolean SSUHireCrew;
    
    private int MAX_ARTFACTS;

    
    public GameData(){
        this.GameEnd = false;
        this.Artifacts = 0;
        this.CrewMembers = 6; 
        this.MiningDrone = true;
        this.SSUCargoHold = true;
        this.SSUConvertResource = true;
        this.SSUHireCrew = true; 
        MAX_ARTFACTS = 5;
    }

    /***************************************************************************
     *                          Player chose Space ship
     */
    public void PlayerChoseMiningShip(){
        this.spaceShip = new SpaceShip("Mining",9,9,18,18,1,4,53,53);
        FirstWorld();
    }
    
    public void PlayerChoseMilitaryShip(){
        this.spaceShip = new SpaceShip("Military",18,18,9,9,1,2,35,35);
        FirstWorld();
    }
    

    /***************************************************************************
     *                          Create new Sector & world
     */
    private void FirstWorld(){
        ResetGame();
        this.Sector = new Sector("White");
        
    }
    
    private void CreateNewWorld(){
        Dice dice = new Dice();
        if(dice.getRandom(1,10) <= 7)                    // 7/10 probabilidade de ser sector branco
            this.Sector = new Sector("White");
        else
            this.Sector = new Sector("Red");    
    }
    
    private void ResetGame(){
        this.GameEnd = false;
        this.Artifacts = 0;
        this.CrewMembers = 6; 
        this.MiningDrone = true;
        this.SSUCargoHold = true;
        this.SSUConvertResource = true;
        this.SSUHireCrew = true;
    }
    
    /***************************************************************************
     *                  Confirmaçoes para maquina de estados if's
     * @return 
     */
    
    public Boolean isSectorWhite(){
        return this.Sector.isWhite();
    }
    
    public Boolean hasGameEnded(){
        if(Artifacts >= MAX_ARTFACTS || this.spaceShip.getFuelStorage() <= 0)
            this.GameEnd = true;
        return this.GameEnd;
    }
    
    public Boolean canLandOnPlanet(){
        if(!this.Sector.canMine()){
            this.SystemMSG = "No more Resources to mine!";
            return false;
        }
        if(this.CrewMembers < 3){
            this.SystemMSG = "You can't land, no " + this.getCrewMenberName(2);
            return false;
        }
        if(this.MiningDrone == null){
            this.SystemMSG = "You can't land, no Mining Drone";
            return false;
        }
        if(!this.spaceShip.hasFuel()){
            this.GameEnd = true;
            this.SystemMSG = "You can't land, no Fuel";
            return false;
        }
        //valida a msg
        return true;       
    }
    
    public Boolean canTravel(){
         if(!this.spaceShip.hasFuel()){
            this.SystemMSG = "You can't travel, no Fuel";
            this.GameEnd = true;
            return false;
        }
        if(this.CrewMembers < 1){
            this.SystemMSG = "You can't travel, no Crew Members";
            this.GameEnd = true;
            return false;
        }
        //msg
        return true;
    }
    
    public Boolean hasAlienAround(){
        return this.Sector.hasAlienAround();
    }
    
    public Boolean canMiningDroneContinue(){
        return this.Sector.canMiningDroneContinue();
    }
    
    public Boolean canMove(int direction){
        return this.Sector.canMove(direction);
    }
    
    /*****************************for buys**************************************/
    public Boolean canUpgradeCargoHold(){
        if(!this.spaceShip.canUpgradeCargoHold()){
            this.SystemMSG = "Cargo Hold on MAXLevel!";
            return false;
        }
        if(!this.SSUCargoHold){
            this.SystemMSG = "You already Upgrade CargoHold on this Station!";
            return false;
        }
        if(!this.hasResourceToBuy()){
            this.SystemMSG = "You don't have resources to Upgrade CargoHold!";
            return false;
        }
        return true;
    }
    
    public Boolean canConvertResource(int i, int j){
        if(!this.spaceShip.hasResourceOfType(i)){
            this.SystemMSG = "No resources for Conversion!";
            return false;
        }
        if(!this.SSUConvertResource){
            this.SystemMSG = "You alredy conver a resource on this Station";
            return false;
        }
        return true;
    }
    
    public Boolean canHireCrew(){
        if(this.CrewMembers >= 6){
            this.SystemMSG = "Your ship is already FULL!";
            return false;
        }
        if(!this.SSUHireCrew){
            this.SystemMSG = "You already hired a crew Member on this Station!";
            return false;
        }
        if(!this.hasResourceToBuy()){
            this.SystemMSG = "No resources to Hire a crewMember";
            return false;
        }
        return true;
    }
    
    public Boolean canUpgradeWeponSystem(){
        if(!this.spaceShip.isMilitaryShip()){
            this.SystemMSG = "Only avaliable to military Ship!";
            return false;
        }
        if(!this.hasResourceToBuy()){
            this.SystemMSG = "No resources to Upgrade Weapon!";
            return false;
        }
        if(!this.spaceShip.canUpgradeWeponSystem()){
            this.SystemMSG = "Can't Upgrade, Weapon System FULL";
            return false;
        }
        return true;   
    }
    
    public Boolean canPurchaseMiningDrone(){
        if(this.MiningDrone){
            this.SystemMSG = "You already have a Mining Drone!";
            return false;
        }
        if(!this.hasResourceToBuy()){
            this.SystemMSG = "No resources to buy a MiningDrone";
            return false;
        }
        return true;
    }
    
    public Boolean canRecall(){
    if(!this.Sector.canRecall()){
        this.SystemMSG = "You can't recal from this position!";
        return false;
    }
    return true;
    }
    
    public Boolean canConverResources(){
        if(!(this.CrewMembers >= 6)){
            this.SystemMSG = "No " + this.getCrewMenberName(5) + "!\nCan't converte Resources!";
            return false;
        }
        return true;
    }
    
    public Boolean canConvertResourcesIntoAMMO(){
        if(!this.spaceShip.canConvertResourcesIntoAMMO()){
            this.SystemMSG = "No resource avaliable to convert into AMMO!";
            return false;
        }
        return true;
    }
    
    public Boolean canConvertResourceIntoFuel(){
        if(!this.spaceShip.canConvertResourceIntoFuel()){
           this.SystemMSG = "No resource avaliable to convert into FUEL!";
            return false; 
        }
        return true;
    }
    
    public Boolean canConvertResourcesIntoEnergyShield(){
        if(!this.spaceShip.canConvertResourcesIntoEnergyShield()){
            this.SystemMSG = "No resource avaliable to convert into Energy Shield!";
            return false; 
        }
        return true; 
    }

    /***************************************************************************
     *                          Preparar exploraçao do planeta
     */

    public void LandOnPlanet(){
        this.Sector.LandOnPlanet();
        this.spaceShip.RemoveOneFuel();
    }
    
    private String getCrewMenberName(int n){
        if(n > 5)
            n = 5;
        return this.CrewMenbersNames[n];
    }
    
    /***************************************************************************
     *                      [Travel] / [Next Turn]
     */
    
    public void NextTurn(){
        this.SSUCargoHold = true;
        this.SSUConvertResource = true;
        this.SSUHireCrew = true;
        CreateNewWorld(); 
        if(!hasFoundWormHole()){
         this.spaceShip.RemoveOneFuel();
         RandomEvent();   
        }
        else
            this.SystemMSG = goTrowWormHole();
    }
    
    private String goTrowWormHole(){
        String aux = "During the Space Travel your crew come across a Black Hole!";
        if(this.spaceShip.canGoTrowWormHoleSafe(this.CrewMembers>=4)){
            this.spaceShip.spendResourcesToGoTrowWormHole(this.CrewMembers>=4);
            aux += "\nYou Lost some resources to go trow the Worm Hole!";
        }
        else{
            this.CrewMembers--;
            aux +="\nYou lost the" + this.getCrewMenberName(this.CrewMembers) + ". . .";
        }
        return aux;    
    }
    
    private Boolean hasFoundWormHole(){
        Dice dice = new Dice();
        return dice.getRandom(1, 8) == 1;
    }
    
    /***************************************************************************
     *                          Eventos
     */
    
    public void SelectedEvent(int opt){
        switch(opt){
            case 1:
                EventCrewDeath();
                break;
            case 2:
                EventSalvageShip();
                break;
            case 3:
                EventCargoLoss();
                break;
            case 4:
                EventFuelLoss();
                break;
            case 5:
                this.SystemMSG = "Just a normal Space Travel";
                break;
            case 6:
                EventCrewRescue();
                break;
        }  
    }
    
    private void RandomEvent(){
        Dice dice = new Dice();
        int opt = dice.getRandom(1, 6);
        
        switch(opt){
            case 1:
                EventCrewDeath();
                break;
            case 2:
                EventSalvageShip();
                break;
            case 3:
                EventCargoLoss();
                break;
            case 4:
                EventFuelLoss();
                break;
            case 5:
                this.SystemMSG = "Just a normal Space Travel";
                break;
            case 6:
                EventCrewRescue();
                break;
        }  
    }
    
    private void EventCrewDeath(){
        if(this.CrewMembers <= 1){
            this.SystemMSG = "Your last crew member is dead!";
            this.GameEnd = true;
        }
        else{
            this.CrewMembers--;
            this.SystemMSG = "You lost a crew member!";
        }
    }
    
    private void EventSalvageShip(){
        Dice dice = new Dice();
        addResourceTimesRand(dice.getRandom(0, 3));   
    }
    
    private void EventCargoLoss(){
        List<Integer> aux = this.spaceShip.hasAnyResources();
        if(aux.isEmpty()){
            this.SystemMSG = "You suffer a acident, but nothing happen!";
            return;
        }
        Dice dice = new Dice();
        //int Rtype;
        //do{
          int Rtype = dice.getRandom(0, aux.size()-1);
        //}while(!this.spaceShip.hasResourceOfType(Rtype));
        
        this.spaceShip.RemoveResource(aux.get(Rtype), dice.getRandom(1, 3));
        this.SystemMSG = "You suffer a acident, and lost some resources!";
    }
    
    private void EventFuelLoss(){
        this.SystemMSG = "Your Space Travel take to long, your ship lost 1 adicional Fuel cell";
        this.spaceShip.RemoveOneFuel();
    }
    
    
    private void EventCrewRescue(){
        if(this.CrewMembers < 6){
            this.SystemMSG = "During the Space travel you meet a old friend, and he joins the crew.";
            this.CrewMembers++;
        }
        else
            this.SystemMSG = "During the Space travel you meet a old friend";
    }
    
    /***************************************************************************
     *                      Gestao de recursos add/remove
     */
    
    public void addResourceTimesRand(int Resource){
        Dice dice = new Dice();
        int nResources = dice.getRoll();
        this.SystemMSG = "You came across a abandoned ship, and found some resources!";
        switch(Resource){
            case 0:
                this.spaceShip.addRedResources(nResources);
                break;
            case 1:
                this.spaceShip.addGreenResources(nResources);
                break;
            case 2:
                this.spaceShip.addBlueResources(nResources);
                break;
            case 3:
                this.spaceShip.addBlackResources(nResources);
                break;
        }
    }
    
    private Boolean hasResourceToBuy(){
        if(this.spaceShip.hasResourceOfType(0))
            if(this.spaceShip.hasResourceOfType(1))
                if(this.spaceShip.hasResourceOfType(2))
                    if(this.spaceShip.hasResourceOfType(3))
                        return true;
        return false;
           
        //return (this.spaceShip.hasResourceOfType(0) && this.spaceShip.hasResourceOfType(1) && this.spaceShip.hasResourceOfType(2) && this.spaceShip.hasResourceOfType(3));
    }
    
    /***************************************************************************
     *                      BUYS
     */
    public void UpgradeCargoHold(){
        this.spaceShip.UpgradeCargoHold();
        this.SSUCargoHold = false;
        //msg blah...
    }
    
    public void ConvertResource(int from, int to){
        this.spaceShip.ConvertResource(from, to);
        this.SSUConvertResource = false;
        //msg
    }
    
    public void HireCrew(){
        this.CrewMembers++;
        this.SSUHireCrew = false;
    }
    
    public void UpgradeWeaponSystem(){
        this.spaceShip.UpgradeWeaponSystem();
    }
    
    public void PurchaseMiningDrone(){
        this.MiningDrone = true;
    }
    
    public void ConvertResourceIntoFuel(){
        this.spaceShip.ConvertResourceIntoFuel();
        //msg
    }
    
    public void ConvertResourcesIntoAMMO(){
        this.spaceShip.ConvertResourcesIntoAMMO();
        //msg
    }
    
    public void ConvertResourcesIntoEnergyShield(){
        this.spaceShip.ConvertResourcesIntoEnergyShield();
        //msg
    }
    
    
    /***************************************************************************
     *                      MOVE ON planet / actions
     */
    
    public void move(int direction){
        this.Sector.move(direction);
    }
    
    public void AlienMove(){
        this.Sector.AlienMove();
    }
    
    public void fightAlien(){
        this.SystemMSG = this.Sector.fightAlien();
    }
    
    public void respawnAlien(){
        this.Sector.respawnAlien();
    }
    public void fightlost(){
        this.MiningDrone = false;
    }
    
    public void colectResources(){
        Entitie colected;
        
        colected = this.Sector.getItensColected();
        
        if(colected == null){
            this.SystemMSG = "No Resources colected!";
        } else if(colected.isArtifact()){
            this.SystemMSG = "You collect a artifact!";
            this.Artifacts++;
        } else if(colected.isCollactable()){
            Dice dice = new Dice();
            int aux = dice.getRoll();
            this.SystemMSG = "You colected " + aux + " units of " + colected.getType();
            this.SystemMSG += "\n" + colected.storeCollactable(aux, this.spaceShip);
        } 

    }
    
    public String getSystemMSG(){
        return this.SystemMSG;
    }
    
    public void ResetSystemMSG(){
        this.SystemMSG = "";
    }
    
    public String getMapInString(){
        return this.Sector.getMapInString();
    }
    
    public int getPlanetByNumber(){
        return Sector.getPlanetByNumber();
    }
    
    public int getObjectByNumber(int x){
        switch(x){
            case 1:    
                return spaceShip.getBlackResources();
            case 2:
                return spaceShip.getRedResources();
            case 3:
                return spaceShip.getGreenResources();
            case 4:
                return spaceShip.getBlueResources();
            case 5:
                return Artifacts;
            case 6:
                return spaceShip.getFuelStorage();
            case 7:
                return spaceShip.getShield();
            case 8:
                return spaceShip.getWeaponSystem();
            case 9:
                return CrewMembers;
                
        }
        return 0;
    }
    

    
    public int getDroneLife(){
        return Sector.getDroneLife();
    }
    
    /***************************************************************************
     * 
     * @return 
     */
    
    public String GameEnd(){
        if(this.Artifacts >= 5)
            return "You collected all 5 artifacts!!!!\n    Game Won!";
        else if(this.CrewMembers <= 0)
            return "You lost your crew!\n Game Lost!";
        else if(this.spaceShip.getFuelStorage() <=0)
            return "You run out of Fuel!\n Game Lost!";
        return "Game Lost!";
            
    }

    @Override
    public String toString() {
        String aux = this.spaceShip.toString();
        aux += "\nNCrew - " + this.CrewMembers;
        aux += "\nHas a drone - " + this.MiningDrone;
        aux += "\nArtifacts - " + this.Artifacts;
        aux += "\n" + this.Sector.toString();
        return aux;
    }
    
    public int getResourceTypeByNumber(){
        return this.Sector.getResourceTypeByNumber();
    }
    
    public int[] getAllPositions(){
        return this.Sector.getAllPositions();
    }
    
    public Boolean hasWonTheGame(){
        return (Artifacts >= MAX_ARTFACTS);
    }
    
    /***************************************************************************
     * 
     */

    public void CMD_MAXResources(){
        this.spaceShip.addBlackResources(26);
        this.spaceShip.addBlueResources(26);
        this.spaceShip.addGreenResources(26);
        this.spaceShip.addRedResources(26);
             
    }
    
    public void CMD_ADDArtefact(){
        this.Artifacts++;
    }
    
    public void CMD_ADDDrone(){
        this.MiningDrone = true;
    }
    
    public void GameMode(int x){
        switch(x){
            case 1:
                this.MAX_ARTFACTS = 5;
                break;
            case 2:
                this.MAX_ARTFACTS = 100;
                break;
            case 3: 
                this.MAX_ARTFACTS = 2147483600; //cabe mais 48 xd
                break;
                
        }
    }
}
