package scenes.publics;

import javafx.scene.control.Label;
import scenes.MyGroup;
import scenes.MyScene;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class PublicScene extends MyGroup {
    @Override
    protected void prepareLayout() {
        view = new MyScene(this, 800, 600);
        Label label = new Label("Hello GUYS! INI PUBLIC SCENE");

        getChildren().add(label);
    }

    @Override
    protected void addListeners() {

    }
}
