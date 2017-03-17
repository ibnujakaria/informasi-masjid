package scenes.dashboard.question;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import scenes.MyGroup;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class ListQuestionScene extends MyGroup
{
    private JFXButton backButton;

    @Override
    protected void prepareLayout() {
        VBox vBox = new VBox();

        Label label = new Label("Daftar Pertanyaan Saya");
        label.setFont(new Font(40));

        backButton = new JFXButton("Back");

        vBox.getChildren().addAll(backButton, label);

        getChildren().add(vBox);
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });
    }
}
