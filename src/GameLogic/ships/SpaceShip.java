/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.ships;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ze1
 */
public class SpaceShip implements Serializable{
    private String Type; //military or mining
    private int WeaponSystem;
    private int[] nResources; //(recursos armazenados) 0-red, 1-green, 2-blue, 3-black
    private int nCargoHolds; //numero de cargos 1-4
    private int FuelStorage;
    private int Shield;
    
            
    
    //const's
    private int MAXWeponSystem;
    private int[] MAXResources; //based on nCargoHolds 0-6 / 1-12 / 2-18 / 3-24
    private final int MAXFuelStorage;
    private final int MAXSield;
    private final int MAXCargoHolds;
   
    public SpaceShip(String Type, int WeaponSystem, int MAXWeponSystem, int Shield, int MAXShield, int nCargoHolds,int MAXCargoHolds, int FuelStorage, int MAXFuelStorage){
        this.Type = Type;
        this.WeaponSystem = WeaponSystem;
        this.Shield = Shield;
        this.nCargoHolds = nCargoHolds;
        this.FuelStorage = FuelStorage;
        this.MAXWeponSystem = MAXWeponSystem;
        this.MAXFuelStorage = MAXFuelStorage;
        this.MAXCargoHolds = MAXCargoHolds;
        this.MAXSield = MAXShield;
        this.nResources = new int [4]; //isto podia ser uma lista[inventario] em que o expaço variava, mas ao mesmo tempo só tenho 4 recurso . . .
            this.nResources[0] = 0;
            this.nResources[1] = 0;
            this.nResources[2] = 0;
            this.nResources[3] = 0;
        this.MAXResources = new int [4];
            this.MAXResources[0] = 6;
            this.MAXResources[1] = 12;
            this.MAXResources[2] = 18;
            this.MAXResources[3] = 24;
    }
    
    //**************************************************************************
    //******************************gets****************************************
    //**************************************************************************
    
    public String getType() {
        return this.Type;
    }
    
    public int getWeaponSystem(){
        return this.WeaponSystem;
    }
    
    public int getFuelStorage(){
        return this.FuelStorage;
    }
    
    public Boolean hasFuel(){
        return this.FuelStorage > 0;
    }
    
    public int getShield(){
        return this.Shield;
    }
    
    public Boolean isMilitaryShip(){
        return (this.Type.compareTo("Military") == 0);
    }
    
    //******************************CONST'S*************************************
    
    public int getMAXWeponSystem(){
        return this.MAXWeponSystem;
    }
    
    public int getMAXFuelStorage(){
        return this.MAXFuelStorage;
    }
    
    public int getMAXSield(){
        return this.MAXSield;
    }
    
    public int getMAXResources(){
        return this.MAXResources[this.nCargoHolds-1];
    }
    
    //******************************Recursos************************************
    
    public int getRedResources(){
        return this.nResources[0];
    }
    
    public int getGreenResources(){
        return this.nResources[1];
    }
    
    public int getBlueResources(){
        return this.nResources[2];
    }
    
    public int getBlackResources(){
        return this.nResources[3];
    }
    
    //**************************************************************************
    //*************************Manutençao Recursos******************************
    //**************************************************************************
    
    public Boolean addRedResources(int value) {
        this.nResources[0] += value;
        if(nResources[0] > getMAXResources()){
            nResources[0] = getMAXResources();
            return true;
        }
        return false;
    }
    
    public Boolean addGreenResources(int value) {
        this.nResources[1] += value;
        if(nResources[1] > getMAXResources()){
            nResources[1] = getMAXResources();
            return true;
        }
        return false;
    }
    
    public Boolean addBlueResources(int value) {
        this.nResources[2] += value;
        if(nResources[2] > getMAXResources()){
            nResources[2] = getMAXResources();
            return true;
        }
        return false;
    }
    
    public Boolean addBlackResources(int value) {
        this.nResources[3] += value;
        if(nResources[3] > getMAXResources()){
            nResources[3] = getMAXResources();
            return true;
        }
        return false;
    }
    
    public Boolean hasResourceOfType(int n){
        //secalhar é melhor rodear esta funçao por um try catch
        try{
            return this.nResources[n] > 0;
        }catch(Exception e){
            return false;
        }
    }
    public void RemoveResource(int resource, int n){
        this.nResources[resource] -= n;
        if(this.nResources[resource] < 0)
            this.nResources[resource] = 0;
    }
    
    public void addReource(int resource, int n){
        this.nResources[resource] += n;
        if(nResources[resource] > getMAXResources())
            nResources[resource] = getMAXResources();
    }
    
    public void RemoveResourceFromStationBuy(){
        RemoveResource(0,1);
        RemoveResource(1,1);
        RemoveResource(2,1);
        RemoveResource(3,1);
    }
    
    
    
    /***************************************************************************/
    public void RemoveOneFuel(){
        this.FuelStorage -= 1;
    }
    
    public Boolean canUpgradeCargoHold(){
        return this.MAXCargoHolds > this.nCargoHolds;
    }
    
    public Boolean canUpgradeWeponSystem(){
        return this.MAXWeponSystem > this.WeaponSystem;
    }
    
    public Boolean canConvertResourcesIntoAMMO(){
        if(this.hasResourceOfType(2))
            if(this.hasResourceOfType(3))
                return true;
        return false;
    }
    
    public Boolean canConvertResourceIntoFuel(){
        if(this.hasResourceOfType(0))
            if(this.hasResourceOfType(1))
                if(this.hasResourceOfType(3))
                    return true;
        return false;
    }
    
    public Boolean canConvertResourcesIntoEnergyShield(){
        if(this.hasResourceOfType(1))
            if(this.hasResourceOfType(2))
                if(this.hasResourceOfType(3))
                    return true;
        return false;
    }
    
    /***************************************************************************
     *                  Buys
     */
    
    public void UpgradeCargoHold(){
        this.nCargoHolds++;
        RemoveResourceFromStationBuy();
    }
    
    public void ConvertResource(int from, int to){
        RemoveResource(from, 1);
        addReource(to, 1);
    }
    
    public void UpgradeWeaponSystem(){
        this.WeaponSystem++;
        RemoveResourceFromStationBuy();
    }
    
    public void ConvertResourceIntoFuel(){
        this.FuelStorage++;
        RemoveResource(0, 1);
        RemoveResource(1, 1);
        RemoveResource(3, 1);
    }
    
    public void ConvertResourcesIntoAMMO(){
        this.WeaponSystem++;
        RemoveResource(2, 1);
        RemoveResource(3, 1);
    }
    
    public void ConvertResourcesIntoEnergyShield(){
        this.Shield++;
        RemoveResource(1, 1);
        RemoveResource(2, 1);
        RemoveResource(3, 1);
    }
    
    public Boolean canGoTrowWormHoleSafe(Boolean safe){
        if(safe)
            return (this.FuelStorage >= 3 && this.Shield >= 2);
        else
            return (this.FuelStorage >= 4 && this.Shield >= 4);
    }
    
    public void spendResourcesToGoTrowWormHole(Boolean safe){
        if(safe){
            this.FuelStorage -= 3;
            this.Shield -= 2;
        }
        else{
            this.FuelStorage -= 4;
            this.Shield -= 4;
        }
    }
    
    public List<Integer> hasAnyResources(){
        List<Integer> aux = new ArrayList<>();
        for(int i = 0; i<4; i++){
            if(this.hasResourceOfType(i))
                aux.add(i);
        }
        return aux;
    }

    @Override
    public String toString() {
        String aux = this.Type;
        aux += "\nWeapon - " + this.WeaponSystem;
        aux += "\nShield - " + this.Shield;
        aux += "\nFUEL --- " + this.FuelStorage;
        aux += "\nResources---------------------- ";
        aux += "\nRed - " + this.nResources[0] + " Green - " + this.nResources[1] + " Blue - " + this.nResources[2] + " Black - " + this.nResources[3];
        aux += "\nNcargoHold - " + this.nCargoHolds;
        
        return aux;
    }
    
    
    
    

}
