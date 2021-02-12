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
import javafx.geometry.Pos;
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
public class InteracaoConvert extends Pane{
        private GameObservavel gameObservavel;
        private Label SystemInfo;
        private Label RBlack, RRed, RBlue, RGreen;
        private Label Fuel, Shield, Weapons;
    
    public InteracaoConvert(GameObservavel gameObservavel) {
        this.gameObservavel = gameObservavel;
        
        this.gameObservavel.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            atualizaVista();
        }); 
        
        organizaComponentes();
        organizaRecursos();
        organizeInfo();
        atualizaVista();
        
    }

    private void organizaComponentes() {
        
        BackgroundSize backgroundSize = new BackgroundSize(600, 700, false, false, false, false);
        BackgroundImage myBI= new BackgroundImage(Imagens.getImagem(ConstantesGui.FUNDO_CONVERT),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,backgroundSize);

        setBackground(new Background(myBI));
        
        
        VBox caixa = new VBox();
        
        Button BFuel = new Button("FUEL");
        Button BAmmo = new Button("AMMO");
        Button BShield = new Button("SHIEL");
        Button BExit = new Button("Exit");
        
        BFuel.setMinSize(120,50);
        BAmmo.setMinSize(120,50);
        BShield.setMinSize(120,50);
        BExit.setMinSize(120,50);
        
        BFuel.setOnAction((ActionEvent e) -> {
            gameObservavel.ConvertResources(1);
        });
        
        BAmmo.setOnAction((ActionEvent e) -> {
            gameObservavel.ConvertResources(2);
        });
        
        BShield.setOnAction((ActionEvent e) -> {
            gameObservavel.ConvertResources(3);
        });
        
        BExit.setOnAction((ActionEvent e) -> {
            gameObservavel.ExitConvert();
        });
        
        
        caixa.setAlignment(Pos.CENTER);
        caixa.setLayoutX(240);
        caixa.setLayoutY(200);
        caixa.setSpacing(50);
        
        caixa.getChildren().addAll(BFuel,BAmmo,BShield,BExit);
        
        
        getChildren().add(caixa);
       
    }

    private void atualizaVista() {
         if(gameObservavel.getStateInfo() == StatesInfo.ACR){
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
        ResourcesInfo.setLayoutX(0);
        ResourcesInfo.setLayoutY(0);
        
        getChildren().add(ResourcesInfo);
    }

    private void organizeInfo() {
        VBox Info = new VBox();
        
        Fuel = new Label("Fuel : ");
        Shield = new Label("Shield : ");
        Weapons = new Label("Weapons : ");
        SystemInfo = new Label();
        SystemInfo.setLayoutY(600);
        SystemInfo.setTextFill(Color.WHITESMOKE);
        
        Fuel.setTextFill(Color.WHITESMOKE);
        Shield.setTextFill(Color.WHITESMOKE);
        Weapons.setTextFill(Color.WHITESMOKE);        
        
        
        Fuel.setFont(new Font("Arial", 30));
        Shield.setFont(new Font("Arial", 30));
        Weapons.setFont(new Font("Arial", 30));       
       
        
        Info.setLayoutX(0);
        Info.setLayoutY(50);
        
        Info.getChildren().addAll(Fuel,Shield,Weapons);
        
        getChildren().addAll(Info, SystemInfo);
    }

    private void refreshView() {
        Fuel.setText("Fuel : " + gameObservavel.getObjectByNumber(6));
        Shield.setText("Shield : " + gameObservavel.getObjectByNumber(7));
        Weapons.setText("Weapons : " + gameObservavel.getObjectByNumber(8));
        
            RBlack.setText(""+gameObservavel.getObjectByNumber(1));
            RRed.setText(""+gameObservavel.getObjectByNumber(2));
            RBlue.setText(""+gameObservavel.getObjectByNumber(4));
            RGreen.setText(""+gameObservavel.getObjectByNumber(3));
            
            SystemInfo.setText(gameObservavel.getSystemMSG());
            gameObservavel.ResetSystemMSG();
    }
}
