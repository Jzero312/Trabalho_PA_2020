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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

/**
 *
 * @author ze1
 */
public class InteracaoEscolheShip extends Pane {
     
    private GameObservavel gameObservavel;
    
    public InteracaoEscolheShip(GameObservavel gameObservavel) {
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
        BackgroundImage myBI= new BackgroundImage(Imagens.getImagem(ConstantesGui.FUNDO_ESCOLHE_SHIP),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,backgroundSize);

        setBackground(new Background(myBI));
        
        Button Mining = new Button("Chose");
        Button Battle = new Button("Chose");
        //ImageView teste = new ImageView();
       //Image selectedImage = Imagens.getImagem(ConstantesGui.BLACKPLANET);
       //teste.setImage(selectedImage);
       //teste.setFitHeight(150);
       //teste.setFitWidth(150);
       
        
        Mining.setLayoutX(120);
        Mining.setLayoutY(480);
        Battle.setLayoutX(400);
        Battle.setLayoutY(480);
        
        Mining.setOnAction((ActionEvent e) -> {
            gameObservavel.ChoseShip(1);
        });
        
        Battle.setOnAction((ActionEvent e) -> {
            gameObservavel.ChoseShip(2);
        });
        
        getChildren().addAll(Battle,Mining);
    }

    private void atualizaVista() {
            setVisible(gameObservavel.getStateInfo() == StatesInfo.ASS); //verificar se é estado correto
    }
    
}
