package scenes.pieces;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import core.auth.Auth;
import core.components.QuestionComponent;
import database.models.Question;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.jooq.Record;
import scenes.MyGroup;
import scenes.publics.PublicScene;
import scenes.publics.auth.LoginScene;

/**
 * Created by abdullah on 17/03/17.
 */
public class PertanyaanContent extends JFXListView<VBox> {
    JFXButton tanyaButton;

    public PertanyaanContent(MyGroup myGroup) {
        for (Record question : Question.getAnsweredQuestion()) {
            getItems().add(new VBox(new QuestionComponent(question, myGroup)));
        }

//        setPadding(new Insets(10, 10, 10, 10));
//        setVgap(5);
//        setHgap(5);
        tanyaButton = new JFXButton("Tanya");
        getItems().add(new VBox(tanyaButton));
//        add(tanyaButton, 0, 2);

//        addListener(loginButton);
    }

    private void addListener(JFXButton button) {
        button.setOnAction(event -> {
            System.out.println("hello world");
        });
    }
}
