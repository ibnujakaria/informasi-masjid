package scenes.admin;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import scenes.MyGroup;
import scenes.admin.ustadz.AddUstadzScene;

/**
 * Created by ibnujakaria on 3/17/17.
 */
public class AdminScene extends MyGroup {
    private VBox vBox;
    private JFXButton backButton, listUstadzButton, listUsersButton,
            listQuestionsButton, listSchedulesButton, listRecordingButton;

    @Override
    protected void prepareLayout() {
        vBox = new VBox();

        Label label = new Label("Admin");
        label.setFont(new Font(40));

        backButton = new JFXButton("Back");
        listUstadzButton = new JFXButton("Ustadz");
        listUsersButton = new JFXButton("User");
        listQuestionsButton = new JFXButton("Pertanyaan");
        listSchedulesButton = new JFXButton("Jadwal Kajian");
        listRecordingButton = new JFXButton("Rekaman Kajian");


        vBox.getChildren().addAll(backButton, label, listUsersButton, listUstadzButton,
                listQuestionsButton, listSchedulesButton, listRecordingButton);
        getChildren().addAll(vBox);
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });

        listUstadzButton.setOnAction(event -> {
            setNextScene(new AddUstadzScene());
            moveNextScene();
        });
    }
}
