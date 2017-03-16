package scenes.dashboard;

import core.auth.Auth;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import scenes.MyGroup;


/**
 * Created by ibnujakaria on 3/15/17.
 */
public class DashboardScene extends MyGroup {
    @Override
    protected void prepareLayout() {
        Label label = new Label("Dashboard");
        label.setFont(new Font(40));
        Label welcome = new Label("Selamat datang: " + Auth.getUser().get("name"));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, welcome);
        getChildren().add(vBox);
    }

    @Override
    protected void addListeners() {

    }
}
