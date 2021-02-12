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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author ze1
 */
public class InteracaoLand extends Pane{
    private GameObservavel gameObservavel;
    private ImageView Alien,Mine,Drone,Ship;
    private Label Life, Info;
    
    public InteracaoLand(GameObservavel gameObservavel){
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
        BackgroundImage myBI= new BackgroundImage(Imagens.getImagem(ConstantesGui.FUNDO_LAND),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,backgroundSize);

        setBackground(new Background(myBI));
        
        //buttoes
        organizaButoes();
        
        Life = new Label();
        Info = new Label();
        Life.setTextFill(Color.CYAN);
        Life.setFont(new Font("Arial", 30));
        Life.setLayoutX(320);
        Life.setLayoutY(450);
        Info.setTextFill(Color.CYAN);
        Info.setFont(new Font("Arial", 15));
        Info.setLayoutX(340);
        Info.setLayoutY(490);
        
        getChildren().addAll(Life,Info); 
        
        //organizaImagens
        organizaImagens();
        
        
        
    }

    private void atualizaVista() {
        if(gameObservavel.getStateInfo() == StatesInfo.AEXP){
            refreshView();
            setVisible(true);
        }
        else
            setVisible(false);
    }

    private void organizaButoes() {
        Button Baixo = new Button("v");
        Button Cima = new Button("^");
        Button Direita = new Button(">");
        Button Esquerda = new Button("<");
        Button Recall = new Button("Recall");
        
        Baixo.setLayoutX(90);
        Baixo.setLayoutY(515);
        Baixo.setMinSize(60, 60);
        Cima.setLayoutX(90);
        Cima.setLayoutY(450);
        Cima.setMinSize(60,60);
        Direita.setLayoutX(155);
        Direita.setLayoutY(515);
        Direita.setMinSize(60, 60);
        Esquerda.setLayoutX(25);
        Esquerda.setLayoutY(515);
        Esquerda.setMinSize(60,60);
        Recall.setLayoutX(25);
        Recall.setLayoutY(595);
        Recall.setMinSize(190, 75);
        
        Baixo.setOnAction((ActionEvent e) -> {
            gameObservavel.Move(3);
        });
        
        Cima.setOnAction((ActionEvent e) -> {
            gameObservavel.Move(4);
        });
        
        Direita.setOnAction((ActionEvent e) -> {
            gameObservavel.Move(2);
        });
        
        Esquerda.setOnAction((ActionEvent e) -> {
            gameObservavel.Move(1);
        });
        
        Recall.setOnAction((ActionEvent e) -> {
            gameObservavel.RecallToShip();
        });
        


        getChildren().addAll(Baixo,Cima,Direita,Esquerda,Recall);
    }

    private void organizaImagens() {
        //Alien,Artfact,Drone,Ship;
        Alien = new ImageView(Imagens.getImagem(ConstantesGui.ALIEN));
        Mine = new ImageView();
        Drone = new ImageView(Imagens.getImagem(ConstantesGui.DRONE));
        Ship = new ImageView(Imagens.getImagem(ConstantesGui.SHIP));
        
        Alien.setFitHeight(100);  
        Alien.setFitWidth(100); 
        //Alien.setLayoutX(470);
        //Alien.setLayoutY(357);
        
        Mine.setFitHeight(100);
        Mine.setFitWidth(100);
        
        Drone.setFitHeight(95);
        Drone.setFitWidth(100);
        
        Ship.setFitHeight(85);
        Ship.setFitWidth(85);
        
        getChildren().addAll(Ship,Mine,Drone,Alien);
        
    }

    private void refreshView() {
        //Life, Info;
        Info.setText(gameObservavel.getSystemMSG());
        gameObservavel.ResetSystemMSG();
        
        Life.setText("Life : " + gameObservavel.getDroneLife());
        
        int[] pos = gameObservavel.getAllPositions();
        
        //Alien,Mine,Drone,Ship;
        Alien.setLayoutX((pos[0])*90+20);
        Alien.setLayoutY((pos[1])*67+22);
        Drone.setLayoutX((pos[2])*90+20);
        Drone.setLayoutY((pos[3])*67+22);
        Ship.setLayoutX((pos[4])*90+20);
        Ship.setLayoutY((pos[5])*67+22);
        
        if(pos[6]==-1){
            Mine.setVisible(false);
        }else{
            switch(gameObservavel.getResourceTypeByNumber()){
                case 1:
                    Mine.setImage(Imagens.getImagem(ConstantesGui.BLACKRESOURCE));
                    Mine.setFitHeight(100);
                    Mine.setFitWidth(100);
                    break;
                case 2:
                    Mine.setImage(Imagens.getImagem(ConstantesGui.REDRESOURCE));
                    Mine.setFitHeight(90);
                    Mine.setFitWidth(90);
                    break;
                case 3:
                    Mine.setImage(Imagens.getImagem(ConstantesGui.GREENRESOURCE));
                    Mine.setFitHeight(80);
                    Mine.setFitWidth(80);
                    break;
                case 4:
                    Mine.setImage(Imagens.getImagem(ConstantesGui.BLUERESOURCE));
                    Mine.setFitHeight(100);
                    Mine.setFitWidth(100);
                    break;
                case 5:
                    Mine.setImage(Imagens.getImagem(ConstantesGui.ARTFACT));
                    Mine.setFitHeight(100);
                    Mine.setFitWidth(100);
                    break;
            }
            Mine.setLayoutX((pos[6])*90+20);
            Mine.setLayoutY((pos[7])*67+22);
            Mine.setVisible(true);
        }
    }
}
