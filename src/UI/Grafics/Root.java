/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Grafics;

/**
 *
 * @author ze1
 */

import GameLogic.Files.FileUtility;
import GameLogic.Game;
import GameLogic.GameObservavel;
import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;



import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;


public class Root extends BorderPane {
    private GameObservavel gameObservavel;
    
    public Root(GameObservavel gameObservavel){
        this.gameObservavel = gameObservavel;
        
        organizaComponentes();
    }
    
    private void organizaComponentes(){
        
        InteracaoMenu interacaoMenu = new InteracaoMenu(gameObservavel);
        InteracaoEscolheShip interacaoEscolheShip  = new InteracaoEscolheShip(gameObservavel);
        InteracaoWhiteSector interacaoWhiteSector = new InteracaoWhiteSector(gameObservavel);
        InteracaoLand interacaoLand = new InteracaoLand(gameObservavel);
        InteracaoRedSector interacaoRedSector = new InteracaoRedSector(gameObservavel);
        InteracaoConvert interacaoConvert = new InteracaoConvert(gameObservavel);
        InteracaoEndGame interacaoEndGame = new InteracaoEndGame(gameObservavel);
        
        StackPane layout = new StackPane(interacaoMenu, interacaoEscolheShip, interacaoWhiteSector, interacaoLand, interacaoRedSector, interacaoConvert, interacaoEndGame);
        //getChildren().add(layout);
        this.setCenter(layout);
      
        //getChildren().add(btn);
        //this.setCenter(btn);
        //cria as vistas
        //menus();
        
        
        //StackPane views = new StackPane(vista1,vista2,...,vistafinal);
        //menu bar
        MenuBar menuBar = new MenuBar();
        
        //menu 1
        Menu gameMenu = new Menu("Game");
        
        //menu 1 itens
        MenuItem readObjMI = new MenuItem("Load");
        MenuItem saveObjMI = new MenuItem("Save");
        MenuItem exitMI = new MenuItem("Exit");
        
        readObjMI.setOnAction(new LoadAction());
        saveObjMI.setOnAction(new SaveAction());
        exitMI.setOnAction(new ExitAction());
        
        //menu 2
        Menu cheatMenu = new Menu("cheats");
        
        //menu 2 itens
        MenuItem RESfil = new MenuItem("Fill Resources");
        MenuItem addArt = new MenuItem("Add Artefact");
        MenuItem addmin = new MenuItem("Add Drone");
        
        RESfil.setOnAction(new ResFilAction());
        addArt.setOnAction(new addArtAction());
        addmin.setOnAction(new addminAction());
        
        Menu Dificuldade = new Menu("Game Mode");
        
        MenuItem normal = new MenuItem("Normal");
        MenuItem Hard = new MenuItem("Harde");
        MenuItem Survivel = new MenuItem("Survivel");
        
        //teste
        normal.setOnAction((ActionEvent e) -> {
            gameObservavel.GameMode(1);
        });
        
        Hard.setOnAction((ActionEvent e) -> {
            gameObservavel.GameMode(2);
        });
        
        Survivel.setOnAction((ActionEvent e) -> {
            gameObservavel.GameMode(3);
        });
        
        //add menu itens
        gameMenu.getItems().addAll(readObjMI, saveObjMI, exitMI);
        cheatMenu.getItems().addAll(RESfil, addArt, addmin);
        Dificuldade.getItems().addAll(normal,Hard,Survivel);
        
        //add menus menubar
        menuBar.getMenus().addAll(gameMenu,Dificuldade,cheatMenu);
        
        setTop(menuBar);
        
    }
    
    class ResFilAction implements EventHandler<ActionEvent>  {
        @Override
        public void handle(ActionEvent e) {
            gameObservavel.CMD_MAXResources();
        }
    }
    
    class addArtAction implements EventHandler<ActionEvent>  {
        @Override
        public void handle(ActionEvent e) {
            gameObservavel.CMD_ADDArtefact();
        }
    }
        
    class addminAction implements EventHandler<ActionEvent>  {
        @Override
        public void handle(ActionEvent e) {
            gameObservavel.CMD_ADDDrone();
        }
    }
    
   
    
        class LoadAction implements EventHandler<ActionEvent>  {
        @Override
        public void handle(ActionEvent e) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./saves"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {           
                try{
                    Game game = (Game)FileUtility.retrieveGameFromFile(selectedFile);
                    if(game != null){
                     gameObservavel.setGame(game);
                    }
                }catch(IOException | ClassNotFoundException ex){
                    Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                    dialogoResultado.setHeaderText("Load");
                    dialogoResultado.setContentText("Operation failed: " + e);
                    dialogoResultado.showAndWait();
                }
          
            } else {
                System.out.println("Operation canceled ");
            }
        }
    }
        
    class SaveAction implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./saves"));
            File selectedFile = fileChooser.showSaveDialog(null);           
            if (selectedFile != null) {
                try{
                    FileUtility.saveGameToFile(selectedFile, gameObservavel.getGame());
                }catch(IOException ex){
                    Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                    dialogoResultado.setHeaderText("Save");
                    dialogoResultado.setContentText("Operation failed: " + e);
                    dialogoResultado.showAndWait();
                    System.out.println("Operation failed: " + e);
                }
            } else {
                System.out.println("Operation canceled ");
            }
        }
    }

    class ExitAction implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            Platform.exit();
        }
    }
    

}
