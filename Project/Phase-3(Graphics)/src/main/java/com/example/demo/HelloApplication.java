
package com.example.demo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

public class HelloApplication extends Application implements Initializable {
    public static Controller controller;
    @FXML
    private Button BackBtn;
    int bet=0;
    @FXML
    private javafx.scene.control.ChoiceBox<Integer> ChoiceBox;

    @FXML
    private Button MODE;
    public boolean isWinnerTakesAll = false;

    @FXML
    private Text CoinText;
    @FXML
    private Button StartBtn;

    @FXML
    boolean coinMiss = false;

    @FXML
    private Text CoinMiss;
    @FXML
    private AnchorPane imageView;

     public Stage primaryStage = new Stage();

     public HelloApplication(Controller controller){
         this.controller = controller;
     }
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("SelectGameMode");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main() {
        launch();
    }

    @FXML
    void BackBtn(ActionEvent event) {
        Platform.exit();
    }


    @FXML
    void ModeBtn(ActionEvent event) {
        isWinnerTakesAll = !isWinnerTakesAll;
        ChoiceBox.setVisible(isWinnerTakesAll);
        if (isWinnerTakesAll) {
            MODE.setText("WINNER TAKES ALL");
            CoinText.setVisible(true);
        }
        else {
            MODE.setText("CLASSIC");
            CoinText.setVisible(false);
            CoinMiss.setVisible(false);
        }
    }

    @FXML
    void StartBtn(ActionEvent event) throws IOException {
        if (isWinnerTakesAll){
            if (coinMiss) {
                controller.SelectCharacter(isWinnerTakesAll,bet);
            }
        }
        else {
            controller.SelectCharacter(isWinnerTakesAll,bet);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChoiceBox.getItems().addAll(10,25,50,100,250,500,1000);
        ChoiceBox.setValue(10);
    }
    @FXML
    void choiceBox(MouseEvent event) {
        bet = ChoiceBox.getValue();
        CoinMiss.setVisible(bet>controller.loggedInUser.coins | bet > controller.SecondUser.coins);
        coinMiss = bet>controller.loggedInUser.coins | bet > controller.SecondUser.coins;
    }
}