/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.TextBase;

import GameLogic.Game;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ze1
 */
public class UI {
    private final Game game;
    private Boolean LeaveGame;
    
    public UI(Game game){
        this.game = game;
        LeaveGame = false;
    }
    
    public void run(){
        while(!LeaveGame){
            this.game.ResetSystemMSG();
            System.out.println("_______________.:-+^^+-:._______________");
            System.out.println(this.game.getStateInfo().toString());
            switch(this.game.getStateInfo()){
                case AGS:
                    UI_AwaitGameStart();
                    break;
                case ASS:
                    UI_AwaitShipSelection();
                    break;
                case AAWS:
                    UI_AwaitActionsWhiteSector();
                    break;
                case AARS:
                    UI_AwaitActionRedSector();
                    break;
                case AEXP:
                    UI_AwaitExploration();
                    break;
                case ACR:
                    UI_AwaitConvertResources();
                    break;
                case AE:
                    UI_AwaitEnd();
                    break;
            }
        }
    }
    
    private void UI_AwaitGameStart(){
        System.out.println("(1)Start New Game");
        System.out.println("(2)Leave Game");
        
        switch(this.getInputFromUser()){
            case 1:
                this.game.StartNewGame();
                break;
            case 2:
                this.LeaveGame = true;
                break;
        }
    }
    
    private void UI_AwaitShipSelection(){
        System.out.println("(1)Mining Ship");
        System.out.println("(2)Military Ship");
        
        switch(this.getInputFromUser()){
            case 1:
                this.game.ChoseMiningShip();
                break;
            case 2:
                this.game.ChoseMilitaryShip();
                break;
        }
    }
    
    private void UI_AwaitActionsWhiteSector(){
        System.out.println(this.game.getInformation());
        System.out.println("_______________________________________");
        System.out.println("(1)Travel to Other Sector");
        System.out.println("(2)Explore Planet");
        
        switch(this.getInputFromUser()){
            case 1:
                this.game.Travel();
                break;
            case 2:
                this.game.LandOnPlanet();
                break;
        }
        PrintSystemMSG();
    }
    
    private void UI_AwaitActionRedSector(){
        System.out.println(this.game.getInformation());
        System.out.println("_______________________________________");
        
        System.out.println("(1)Travel to Other Sector");
        System.out.println("(2)Explore Planet");
        System.out.println("_______Space Station_______");
        System.out.println("(3)Upgrade Cargo Hold");
        System.out.println("(4)Convert Resource");
        System.out.println("(5)Hire Crew Member");
        System.out.println("(6)Upgrade Weapon System");
        System.out.println("(7)Purchase Mining Drone");
        
        

        switch(this.getInputFromUser()){
            case 1:
                this.game.Travel();
                break;
            case 2:
                this.game.LandOnPlanet();
                break;
            case 3:
                this.game.UpradeCargoHold();
                break;
            case 4:
                System.out.println("Chose your resource:");
                int from = this.getResorce();
                System.out.println("Convert to:");
                int to = this.getResorce();
                this.game.ConvertResource(from, to);
                break;
            case 5:
                this.game.HireCrew();
                break;
            case 6:
                this.game.UpgradeWeponSystem();
                break;
            case 7:
                this.game.PurchaseMiningDrone();
                break;
        }
        PrintSystemMSG();
    }
    
    private void UI_AwaitExploration(){
         System.out.println(this.game.getMapInString());
        System.out.println("(1)Move UP");
        System.out.println("(2)Move Down");
        System.out.println("(3)Move Right");
        System.out.println("(4)Move Left");
        System.out.println("(5)Recall");
        
        switch(this.getInputFromUser()){
            case 1:
                this.game.Move(1);
                break;
            case 2: 
                this.game.Move(2);
                break;
            case 3:
                this.game.Move(3);
                break;
            case 4:
                this.game.Move(4);
                break;
            case 5:
                this.game.RecallToShip();
                break;
        }
        PrintSystemMSG();
    }
    
    private void UI_AwaitConvertResources(){
        System.out.println(this.game.getInformation());
        System.out.println("_______________________________________");
        System.out.println("(1)Convert Resources into FUEL");
        System.out.println("(2)Convert Resources into AMMO");
        System.out.println("(3)Convert Resources into Shield");
        System.out.println("(4)Finish conversion");
        
        switch(this.getInputFromUser()){
            case 1:
                this.game.ConvertResourceIntoFuel();
                break;
            case 2:
                this.game.ConvertResourcesIntoAMMO();
                break;
            case 3:
                this.game.ConvertResourcesIntoEnergyShield();
                break;
            case 4:
                this.game.ExitConvert();
                break;
        }
        PrintSystemMSG();
    }
    
    private void UI_AwaitEnd(){
        System.out.println(this.game.GameEnd());
        System.out.println("Press enter to finish...");
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.game.GameEnded();
        
    }
    
    private int getInputFromUser(){
        Scanner sc = new Scanner(System.in);
        
        String opt1 = sc.nextLine().trim();
        Cheats(opt1);
        int opt;
        try{
            opt = Integer.parseInt(opt1);
        } catch(NumberFormatException e){
            opt = -1;
        }
        return opt;
    }
    
    private void Cheats(String str){
        str = str.toUpperCase();
        String[] arr = str.split(" ");
        
        if(arr[0] == null){
        }
        else if(arr[0].compareTo("EVENT")==0){
            try{
                int i = Integer.parseInt(arr[1]);
                this.game.CMD_Event(i);
            }catch(Exception e){
            }
        }
        else if(arr[0].compareTo("MAXRES")==0){
            //ADICIONAR RECURSOS
            try{
                this.game.CMD_MAXResources();
            }catch(Exception e){
                
            }
            
        }
        else if(arr[0].compareTo("ADDART")==0){
            //ADICIONA ARTEFACTO
            try{
                this.game.CMD_ADDArtefact();
            }catch(Exception e){
                
            }
            
        }
        else if(arr[0].compareTo("ADDRONE")==0){
            try{
                this.game.CMD_ADDDrone();
            }catch(Exception e){
                
            }
            
        }
        else if(arr[0].compareTo("QQQ")==0)
            this.LeaveGame = true;
    }
    
    private int getResorce(){
        System.out.println("(1)Red Resource");
        System.out.println("(2)Green Resource");
        System.out.println("(3)Blue Resource");
        System.out.println("(4)Black Resource");
        
        return(this.getInputFromUser()-1);
    }
    
    private void PrintSystemMSG(){
        System.out.println("________________________________");
        System.out.println(this.game.getSystemMSG());
        this.game.ResetSystemMSG();
        
    }
}
