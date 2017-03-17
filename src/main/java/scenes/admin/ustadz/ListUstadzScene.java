package scenes.admin.ustadz;

import com.jfoenix.controls.JFXButton;
import core.components.UserComponent;
import database.models.User;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.jooq.Record;
import scenes.MyGroup;

/**
 * Created by ibnujakaria on 3/17/17.
 */
public class ListUstadzScene extends MyGroup {
    private VBox vBox, listUstadz;
    private JFXButton backButton, addUstadzButton;

    @Override
    protected void prepareLayout() {
        vBox = new VBox();

        Label label = new Label("Daftar Ustadz");

        backButton = new JFXButton("Back");
        addUstadzButton = new JFXButton("Tambah Ustadz");

        listUstadz = new VBox();
        vBox.getChildren().addAll(backButton, label, addUstadzButton, listUstadz);
        getChildren().addAll(vBox);

        prepareUstadz();
    }

    @Override
    protected void onAfterBack() {
        super.onAfterBack();
        prepareUstadz();
    }

    private void prepareUstadz() {
        listUstadz.getChildren().clear();
        for (Record r : User.getUstadz()) {
            listUstadz.getChildren().add(new UserComponent(r));
        }
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
