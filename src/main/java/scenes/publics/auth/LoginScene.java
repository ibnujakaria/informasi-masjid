package scenes.publics.auth;

import app.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToolbar;
import core.auth.Auth;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import scenes.MyGroup;
import scenes.dashboard.DashboardScene;
import scenes.publics.PublicScene;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class LoginScene extends MyGroup {
    JFXTextField usernameField;
    JFXPasswordField passwordField;
    JFXButton loginButton, signUpButton, backButton;
    Label errorLabel,header, title;
    VBox vBox;
    BorderPane back;
    ToolBar toolBar;
    Pane pane;
    final KeyCombination kb = new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_ANY);

    @Override
    protected void prepareLayout() {
        pane = new Pane();
        pane.setPrefWidth(300);
        title = new Label("Login");
        vBox = new VBox();
        toolBar = new ToolBar();
        toolBar.setPrefWidth(800);
        backButton = new JFXButton("Back");

        toolBar.getItems().addAll(
          backButton, pane,title

        );

        back = new BorderPane();
        back.setTop(toolBar);

        header = new Label(Main.primaryStage.getTitle()+" - Login");
        header.setId("titleLogin");
        usernameField = new JFXTextField();
        usernameField.setPromptText("Username");

        passwordField = new JFXPasswordField();
        passwordField.setPromptText("Password");
        usernameField.setMaxWidth(320);
        passwordField.setMaxWidth(320);
        usernameField.setMinHeight(50);
        passwordField.setMinHeight(50);

        loginButton = new JFXButton("Login");
        signUpButton = new JFXButton("Sign up");

        errorLabel = new Label("Login failed!");
        errorLabel.setVisible(false);

        GridPane buttons = new GridPane();
        buttons.setPadding(new Insets(0, 10, 0, 10));
        buttons.setVgap(10);
        buttons.setHgap(10);
        buttons.add(loginButton, 0, 2);
        buttons.add(signUpButton, 1, 2);
        buttons.setAlignment(Pos.CENTER);
        setvBoxFullScreen();

        vBox.setAlignment(Pos.CENTER);
        ObservableList list = vBox.getChildren();
        list.addAll(header,usernameField,passwordField,errorLabel,buttons);
        getChildren().addAll(vBox,back);
    }

    @Override
    protected void addListeners() {
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (kb.match(event)) {
                    if (previousScene == null) {
                        setPreviousScene(new PublicScene());
                        setvBoxFullScreen();
                    }
                    movePreviousScene();
                    setvBoxFullScreen();
                }
            }
        });

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER){
                    doLoginProcess();
                    setvBoxFullScreen();
                }
            }
        });

        loginButton.setOnAction(event -> {
            doLoginProcess();
            setvBoxFullScreen();
        });

        backButton.setOnAction(event -> {
            if (previousScene == null) {
                setPreviousScene(new PublicScene());
            }
            movePreviousScene();
            setvBoxFullScreen();
        });

        signUpButton.setOnAction(event -> {
            PublicScene publicScene = (PublicScene) getPreviousScene();
            publicScene.setNextScene(new SignupScene());
            publicScene.moveNextScene();
            setvBoxFullScreen();
        });

        Main.primaryStage.getScene().widthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setvBoxFullScreen();
            }
        });

        Main.primaryStage.getScene().heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setvBoxFullScreen();
            }
        });
    }

    private void doLoginProcess() {
        boolean success = Auth.attemps(usernameField.getText(), passwordField.getText());
        errorLabel.setVisible(false);

        if (success) {
            setNextScene(new DashboardScene());
            moveNextScene();
            setvBoxFullScreen();
        } else {
            errorLabel.setVisible(true);
            setvBoxFullScreen();
        }
    }

    private void setvBoxFullScreen () {
        vBox.setMinHeight(Main.primaryStage.getScene().getHeight());
        vBox.setMinWidth(Main.primaryStage.getScene().getWidth());
    }
}
