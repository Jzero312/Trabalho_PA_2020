/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa_trabalhopratico;
import GameLogic.Entities.Dice;
import GameLogic.Game;
import GameLogic.GameObservavel;
import UI.Grafics.Root;
//import UI.TextBase.UI;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author ze1
 */
public class PA_TrabalhoPratico extends Application{

    /**
     * @param args the command line arguments
     */
    //public static void main(String[] args) {

    //}
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Game game = new Game();        
        GameObservavel gameObservavel = new GameObservavel(game);
 
        //Scene scene = new Scene(new Root(maquinaGomasObservavel),400,260); 
        //Button btn = new Button();
        //btn.setText("Say 'Hello World'");
        //btn.setOnAction(new EventHandler<ActionEvent>() {
            
        //    @Override
        //    public void handle(ActionEvent event) {
        //        System.out.println("Hello World!");
        //    }
        //});
        
        //StackPane root = new StackPane();
        //root.getChildren().add(btn);
        
        Scene scene = new Scene(new Root(gameObservavel));
        primaryStage.setWidth(600);
        primaryStage.setHeight(750);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene); 
        primaryStage.setTitle("Planet Bound"); 
        primaryStage.show();
    }
    public static void main(String[] args) {
    //    Game game = new Game();
    //    UI ui = new UI(game);
    //    ui.run();
        launch(args);
    }
    
}
