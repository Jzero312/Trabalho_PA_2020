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
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author ze1
 */
public class InteracaoMenu extends Pane {
    
    private GameObservavel gameObservavel;
    
    public InteracaoMenu(GameObservavel gameObservavel){
        this.gameObservavel = gameObservavel;
        
        this.gameObservavel.addPropertyChangeListener( new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                atualizaVista();
            }
        }
        ); 
        
        organizaComponentes();
        
        atualizaVista();
    }

    private void organizaComponentes() {
        BackgroundSize backgroundSize = new BackgroundSize(600, 700, false, false, false, false);
        BackgroundImage myBI= new BackgroundImage(Imagens.getImagem(ConstantesGui.FUNDO),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,backgroundSize);

        setBackground(new Background(myBI));
        
 
        
        Button NewGame = new Button("New Game");
       // NewGame.setPrefHeight(50);
        NewGame.setPrefSize(100, 50);
        NewGame.setLayoutX(250);
        NewGame.setLayoutY(500);
        
        NewGame.setOnAction((ActionEvent e) -> {
            gameObservavel.StartNewGame();
        });
        
        //setAlignment(Pos.CENTER);
        getChildren().add(NewGame);
    }

    private void atualizaVista() {
        if(gameObservavel.getStateInfo() == StatesInfo.AGS)
            setVisible(true); //verificar se é estado correto
        else
            setVisible(false);
    }
}
