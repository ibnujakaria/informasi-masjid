package scenes.admin.ustadz;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import scenes.MyGroup;

/**
 * Created by ibnujakaria on 3/17/17.
 */
public class ListUstadzScene extends MyGroup {
    private VBox vBox;
    private JFXButton backButton, addUstadzButton;

    @Override
    protected void prepareLayout() {
        vBox = new VBox();
        Label label = new Label("Daftar Ustadz");

        backButton = new JFXButton("Back");
        addUstadzButton = new JFXButton("Tambah Ustadz");

        vBox.getChildren().addAll(backButton, label);
        getChildren().addAll(vBox);
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });
    }
}
