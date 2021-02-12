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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author ze1
 */
public class InteracaoEndGame extends Pane{

    private GameObservavel gameObservavel;
    private Label SystemInfo, artfacts;
    
     public InteracaoEndGame(GameObservavel gameObservavel) {
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

    private void atualizaVista() {
       if(gameObservavel.getStateInfo() == StatesInfo.AE){
            refreshView();
            setVisible(true);
        }
        else
            setVisible(false);
    }

    private void organizaComponentes() {
        Button close = new Button("Close");
        close.setMinSize(120,50);
        close.setLayoutX(240);
        close.setLayoutY(500);
        
        close.setOnAction((ActionEvent e) -> {
            gameObservavel.GameEnded();
        });
        //SystemInfo, artfacts
        SystemInfo = new Label();
        artfacts = new Label();
        
        artfacts.setFont(new Font("Arial", 50));
        artfacts.setLayoutX(190);
        artfacts.setLayoutY(350);
        artfacts.setTextFill(Color.YELLOW); 
        
        SystemInfo.setFont(new Font("Arial", 20));
        SystemInfo.setLayoutX(0);
        SystemInfo.setLayoutY(0);
        SystemInfo.setTextFill(Color.WHITESMOKE); 
        
        getChildren().addAll(close, artfacts, SystemInfo);
    }

    private void refreshView() {
        if(gameObservavel.hasWonTheGame()){
        BackgroundSize backgroundSize = new BackgroundSize(600, 700, false, false, false, false);
        BackgroundImage myBI= new BackgroundImage(Imagens.getImagem(ConstantesGui.FUNDO_VITORIA),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,backgroundSize);

        setBackground(new Background(myBI));
        }
        else{
        BackgroundSize backgroundSize = new BackgroundSize(600, 700, false, false, false, false);
        BackgroundImage myBI= new BackgroundImage(Imagens.getImagem(ConstantesGui.FUNDO_LOST),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,backgroundSize);

        setBackground(new Background(myBI));
        }
        
        SystemInfo.setText(gameObservavel.getSystemMSG());
        gameObservavel.ResetSystemMSG();
        artfacts.setText("Artfacts : " + gameObservavel.getObjectByNumber(5));
    }
    
}
