package scenes.pieces;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Created by abdullah on 17/03/17.
 */
public class TopMenu extends HBox {
    JFXButton menu, profile, logout;
    JFXPopup popup;
    Label title;
    StackPane menuPane;


    public TopMenu() {
        setMinHeight(100);
        title = new Label("Info Masjid");
        setMargin(title, new Insets(10,0,0,10));
        addLeftMenu();
        setStyle("-fx-background-color: #2f4f4f;");
        getChildren().addAll(title, menuPane);
    }

    private void addLeftMenu() {
        menuPane = new StackPane();

        menu = new JFXButton("User");

        profile = new JFXButton("Profil");
        profile.setMaxWidth(75);
        logout = new JFXButton("Keluar");
        logout.setMaxWidth(75);
        VBox dropdown = new VBox(profile, logout);

        VBox menuBox = new VBox(menu);
        menuBox.setAlignment(Pos.TOP_RIGHT);

        popup = new JFXPopup();
        popup.setContent(dropdown);
        popup.setPopupContainer(menuBox);
        popup.setSource(menu);


        menuPane.getChildren().addAll(menuBox);
        menuPane.setAlignment(Pos.TOP_RIGHT);
        // Add offset to right for question mark to compensate for RIGHT
        // alignment of all nodes
        StackPane.setMargin(menuBox, new Insets(10, 10, 0, 0));
        setHgrow(menuPane, Priority.ALWAYS);

        addListener(menu);
    }

    private void addListener(JFXButton button) {
        button.setOnMouseClicked(event -> {
            popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
        });
    }
}
