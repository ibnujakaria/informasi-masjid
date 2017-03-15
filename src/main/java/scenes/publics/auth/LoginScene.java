package scenes.publics.auth;

import com.jfoenix.controls.JFXButton;
import scenes.MyGroup;
import scenes.MyScene;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class LoginScene extends MyGroup {
    JFXButton tes;

    @Override
    protected void prepareLayout() {
        view = new MyScene(this, 300, 300);
    }

    @Override
    protected void addListeners() {
    }
}
