package scenes;

import app.Main;
import javafx.scene.Group;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public abstract class MyGroup extends Group{

    protected MyGroup nextScene, previousScene;

    protected abstract void prepareLayout();
    protected abstract void addListeners();

    public MyGroup() {
        System.out.println("INSTANCE BARU: " + this.getClass().toString());

        prepareLayout();
        addListeners();
    }

    protected void onAfterNext() {

    }

    protected void onAfterBack() {

    }

    public void refreshContent () {

    }

    public void moveNextScene() {
        if (nextScene == null) {
            return;
        }

        Main.primaryStage.getScene().setRoot(nextScene);
        nextScene.onAfterNext();
    }

    public void movePreviousScene() {
        if (previousScene == null) {
            return;
        }

        Main.primaryStage.getScene().setRoot(previousScene);
        previousScene.onAfterBack();
    }

    public MyGroup getNextScene() {
        return nextScene;
    }

    public void setNextScene(MyGroup nextScene) {
        nextScene.setPreviousScene(this);
        this.nextScene = nextScene;
    }

    public MyGroup getPreviousScene() {
        return previousScene;
    }

    public void setPreviousScene(MyGroup previousScene) {
        this.previousScene = previousScene;
    }

}
