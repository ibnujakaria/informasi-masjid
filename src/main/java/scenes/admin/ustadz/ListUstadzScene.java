package scenes.admin.ustadz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import core.components.UserComponent;
import database.models.jooq.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.jooq.Record;
import scenes.MyGroup;

/**
 * Created by ibnujakaria on 3/17/17.
 */
public class ListUstadzScene extends MyGroup {
    private VBox vBox, listUstadz;
    private JFXButton backButton, addUstadzButton;
    JFXListView<VBox> listUstadzs;
    BorderPane pane,top,titleBar;
    HBox hBox;
    Label label;

    @Override
    protected void prepareLayout() {
        pane = new BorderPane();
        backButton = new JFXButton("Back");
        listUstadz = new VBox();
        addUstadzButton = new JFXButton("Tambah Ustadz");
        addUstadzButton.setStyle("-fx-background-color: #00bcd4ff; -fx-text-fill: white;");

        vBox = new VBox();

        label = new Label("Daftar Ustadz");
        label.setId("listUsr");
        label.setAlignment(Pos.CENTER);

        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label);
        vBox.setStyle("-fx-background-color: #3498db;");

        titleBar = new BorderPane();
        titleBar.setTop(vBox);
        titleBar.setRight(addUstadzButton);
        titleBar.setMargin(addUstadzButton, new Insets(2,80,7,0));
        titleBar.setStyle("-fx-background-color: #3498db;");

        hBox = titleBar(backButton, new Label("List Ustadz"));
//        hBox = new HBox();
//        hBox.setStyle("-fx-background-color: #ecf0f1;");
//        backButton.setAlignment(Pos.TOP_LEFT);
//        hBox.setAlignment(Pos.CENTER);
//        hBox.getChildren().addAll(backButton);
//        hBox.prefWidthProperty().bind(Main.primaryStage.widthProperty());

        top = new BorderPane();
        top.setLeft(backButton);
        top.setCenter(hBox);
        top.setStyle("-fx-background-color: #ecf0f1;");
        top.setMargin(backButton ,new Insets(5,1,5,8));

        pane.setTop(top);
        pane.setCenter(titleBar);
        pane.setBottom(listUstadz);
        getChildren().addAll(pane);

        prepareUstadz();
    }

    @Override
    public void refreshContent() {
        super.refreshContent();
        prepareUstadz();
    }

    @Override
    protected void onAfterBack() {
        super.onAfterBack();
        prepareUstadz();
    }

    private void prepareUstadz() {
        listUstadzs = new JFXListView<VBox>();
        listUstadz.getChildren().clear();
//        listUstadzs.prefHeightProperty().bind(Main.primaryStage.getScene().heightProperty());
//        listUstadzs.prefWidthProperty().bind(Main.primaryStage.getScene().widthProperty());
        for (Record r : User.getUstadz()) {
            listUstadzs.getItems().add(new VBox(new UserComponent(this, r)));
        }
        listUstadz.getChildren().addAll(listUstadzs);
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });

        addUstadzButton.setOnAction(event -> {
            setNextScene(new AddUstadzScene());
            moveNextScene();
        });
    }
}
