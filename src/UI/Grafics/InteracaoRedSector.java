/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Grafics;

import GameLogic.GameObservavel;
import GameLogic.StateMachine.StatesInfo;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author ze1
 */
public class InteracaoRedSector extends Pane{
    private GameObservavel gameObservavel;
    private ImageView Planet;
    private Label Fuel, Shield, Artifacts, Weapons, Crew;
    private Label RBlack, RRed, RBlue, RGreen;
    private Label SystemInfo;
    
    
        public InteracaoRedSector(GameObservavel gameObservavel) {
        this.gameObservavel = gameObservavel;
        
        this.gameObservavel.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            atualizaVista();
        }); 
        
        organizaComponentes();
        atualizaVista();
        
        }

    private void organizaComponentes() {
                //background
        BackgroundSize backgroundSize = new BackgroundSize(600, 700, false, false, false, false);
        BackgroundImage myBI= new BackgroundImage(Imagens.getImagem(ConstantesGui.FUNDO_RED_SECTOR),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,backgroundSize);

        setBackground(new Background(myBI));
        
        // Butoes Travel e Landing
        Button Travel = new Button("Travel");
        Button Land = new Button("Land on Planet");

        Travel.setLayoutX(15);
        Travel.setLayoutY(595);
        Travel.setMinSize(120, 75);
        Land.setLayoutX(15);
        Land.setLayoutY(515);
        Land.setMinSize(120,75);
        
        Travel.setOnAction((ActionEvent e) -> {
            gameObservavel.Travel();
        });
        
        Land.setOnAction((ActionEvent e) -> {
            gameObservavel.LandOnPlanet();
        });
        
        //planet
        Planet = new ImageView();
        Planet.setFitHeight(300);
        Planet.setFitWidth(300);
        Planet.setLayoutX(10);
        Planet.setLayoutY(50);
                    //teste.setImage(Imagens.getImagem(ConstantesGui.REDPLANET));
        
        //System info
        SystemInfo = new Label("");
        SystemInfo.setTextFill(Color.CYAN);
        SystemInfo.setFont(new Font("Arial", 15));
        SystemInfo.setLayoutX(15);
        SystemInfo.setLayoutY(450);
        
        //Organize Buttons
        organizeButtons();
        
        
        //adiciona estes botoes primeiro
        getChildren().addAll(Travel,Land, Planet, SystemInfo);
        //Recursos info
        organizaRecursos();
        organizaInfo();
        
        
        
    }

    private void atualizaVista() {
        if(gameObservavel.getStateInfo() == StatesInfo.AARS){
            refreshView();
            setVisible(true);
        }
        else
            setVisible(false);
    }

    private void organizaRecursos() {
        HBox ResourcesInfo = new HBox();
        RBlack = new Label("00");
        RRed = new Label("00");
        RGreen = new Label("00");
        RBlue = new Label("00");
        
        ImageView IBlack = new ImageView(Imagens.getImagem(ConstantesGui.BLACKRESOURCE));
        ImageView IRed = new ImageView(Imagens.getImagem(ConstantesGui.REDRESOURCE));
        ImageView IBlue = new ImageView(Imagens.getImagem(ConstantesGui.BLUERESOURCE));
        ImageView IGreen = new ImageView(Imagens.getImagem(ConstantesGui.GREENRESOURCE));
        
        IBlack.setFitHeight(35);
        IBlack.setFitWidth(35);
        IRed.setFitHeight(35);
        IRed.setFitWidth(35);
        IBlue.setFitHeight(35);
        IBlue.setFitWidth(35);
        IGreen.setFitHeight(35);
        IGreen.setFitWidth(35);
        
        RBlack.setTextFill(Color.WHITE);
        RRed.setTextFill(Color.WHITE);
        RGreen.setTextFill(Color.WHITE);        
        RBlue.setTextFill(Color.WHITE);
        
        RBlack.setFont(new Font("Arial", 30));
        RRed.setFont(new Font("Arial", 30));
        RGreen.setFont(new Font("Arial", 30));       
        RBlue.setFont(new Font("Arial", 30));
        
        ResourcesInfo.getChildren().addAll(IBlack, RBlack, IRed, RRed, IBlue, RBlue, IGreen, RGreen);
        ResourcesInfo.spacingProperty();
        ResourcesInfo.setLayoutX(300);
        ResourcesInfo.setLayoutY(70);
        
        getChildren().add(ResourcesInfo);
    }

    private void organizaInfo() {
        VBox Info = new VBox();
        
        Fuel = new Label("Fuel : ");
        Shield = new Label("Shield : ");
        Weapons = new Label("Weapons : ");
        Artifacts = new Label("Artifacts : ");
        Crew = new Label();
        SystemInfo = new Label(); 
        
        Fuel.setTextFill(Color.WHITE);
        Shield.setTextFill(Color.WHITE);
        Weapons.setTextFill(Color.WHITE);        
        Artifacts.setTextFill(Color.WHITE);
        Crew.setTextFill(Color.WHITE);
        SystemInfo.setTextFill(Color.WHITE);
        
        Fuel.setFont(new Font("Arial", 30));
        Shield.setFont(new Font("Arial", 30));
        Weapons.setFont(new Font("Arial", 30));       
        Artifacts.setFont(new Font("Arial", 30));
        Crew.setFont(new Font("Arial", 30));
        SystemInfo.setFont(new Font("Arial", 15));
        
        SystemInfo.setLayoutX(10);
        SystemInfo.setLayoutY(380);
        
        Info.setLayoutX(310);
        Info.setLayoutY(110);
        
        Info.getChildren().addAll(Fuel,Shield,Weapons,Artifacts, Crew);
        
        getChildren().addAll(Info,SystemInfo);
    }

    private void organizeButtons() {
        Button Cargo = new Button("BUY");
        Button Hire = new Button("BUY");
        Button Wepon = new Button("BUY");
        Button Mining = new Button("BUY");
        
        Cargo.setLayoutX(510);
        Cargo.setLayoutY(485);
        Hire.setLayoutX(510);
        Hire.setLayoutY(530);
        Wepon.setLayoutX(510);
        Wepon.setLayoutY(580);
        Mining.setLayoutX(510);
        Mining.setLayoutY(625);
        
        Cargo.setOnAction((ActionEvent e) -> {
            gameObservavel.Buy(1);
        });
        
        Hire.setOnAction((ActionEvent e) -> {
            gameObservavel.Buy(2);
        });
        
        Wepon.setOnAction((ActionEvent e) -> {
            gameObservavel.Buy(3);
        });
        
        Mining.setOnAction((ActionEvent e) -> {
            gameObservavel.Buy(4);
        });
        
        getChildren().addAll(Cargo,Hire,Wepon,Mining);
    }

    private void refreshView() {
        switch(gameObservavel.getPlanetByNumber()){
            case 1:
                Planet.setImage(Imagens.getImagem(ConstantesGui.BLACKPLANET));
                break;
            case 2:
                Planet.setImage(Imagens.getImagem(ConstantesGui.REDPLANET));
                break;
            case 3:
                Planet.setImage(Imagens.getImagem(ConstantesGui.GREENPLANET));
                break;
            case 4:
                Planet.setImage(Imagens.getImagem(ConstantesGui.BLUEPLANET));
                break;
        }
        
            //Fuel, Shield, Artifacts, Weapons, Crew;
            //RBlack, RRed, RBlue, RGreen;
            
            Fuel.setText("Fuel : " + gameObservavel.getObjectByNumber(6));
            Shield.setText("Shield : " + gameObservavel.getObjectByNumber(7));
            Artifacts.setText("Artifacts : " + gameObservavel.getObjectByNumber(5));
            Weapons.setText("Weapons : " + gameObservavel.getObjectByNumber(8));
            Crew.setText("Crew : " + gameObservavel.getObjectByNumber(9));
            
            RBlack.setText(""+gameObservavel.getObjectByNumber(1));
            RRed.setText(""+gameObservavel.getObjectByNumber(2));
            RBlue.setText(""+gameObservavel.getObjectByNumber(4));
            RGreen.setText(""+gameObservavel.getObjectByNumber(3));
            
            SystemInfo.setText(gameObservavel.getSystemMSG());
            gameObservavel.ResetSystemMSG();
    }
}
