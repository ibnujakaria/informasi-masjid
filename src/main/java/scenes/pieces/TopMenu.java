package scenes.pieces;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import core.auth.Auth;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import scenes.MyGroup;
import scenes.publics.auth.LoginScene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by abdullah on 17/03/17.
 */
public class TopMenu extends HBox {
    JFXButton menu, option, login, logout;
    JFXPopup popup;
    Label title;
    StackPane menuPane;
    Image user;
    MyGroup myGroup;

    public TopMenu(MyGroup myGroup, JFXButton option) {
        this.myGroup = myGroup;
        setMinHeight(120);
        title = new Label("Info Masjid");
        title.setId("title");
        setId("top-menu");
        setMargin(title, new Insets(10,0,0,10));
        addLeftMenu(option);
        getChildren().addAll(title, menuPane);
    }

    private void addLeftMenu(JFXButton option) {
        menuPane = new StackPane();
        VBox menuBox = new VBox();

        if (Auth.isLogin()) {
            try {
                user = new Image(new FileInputStream("dist/images/user/user.png"),40,40,false,false);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            menu = new JFXButton();
            menu.setId("userBtn");

            menu.setGraphic(new ImageView(user));
            this.option = option;
            option.setMaxWidth(75);
            logout = new JFXButton("Keluar");
            logout.setMaxWidth(75);
            VBox dropdown = new VBox(this.option, logout);

            popup = new JFXPopup();
            popup.setContent(dropdown);
            popup.setPopupContainer(menuBox);
            popup.setSource(menu);

            menuBox.getChildren().add(menu);
            addListener(Auth.isLogin());
        } else {
            login = new JFXButton("Masuk");
            menuBox.getChildren().add(login);
            addListener(Auth.isLogin());
        }

        menuBox.setAlignment(Pos.TOP_RIGHT);

        menuPane.getChildren().addAll(menuBox);
        menuPane.setAlignment(Pos.TOP_RIGHT);
        // Add offset to right for question mark to compensate for RIGHT
        // alignment of all nodes
        StackPane.setMargin(menuBox, new Insets(10, 10, 0, 0));
        setHgrow(menuPane, Priority.ALWAYS);

    }

    private void addListener(boolean isLogin) {
        if (isLogin) {
            menu.setOnMouseClicked(event -> {
                popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
            });
            logout.setOnAction(event -> {
                Auth.logout();

                myGroup.setPreviousScene(new LoginScene());
                myGroup.movePreviousScene();
            });
        } else {
            login.setOnAction(event -> {
                myGroup.setNextScene(new LoginScene());
                myGroup.moveNextScene();
            });
        }
    }
}
