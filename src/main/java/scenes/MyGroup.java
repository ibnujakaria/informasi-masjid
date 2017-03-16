package scenes;

import app.Main;
import javafx.scene.Group;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public abstract class MyGroup extends Group{

    protected MyScene view;
    protected MyGroup nextScene, previousScene;

    protected abstract void prepareLayout();
    protected abstract void addListeners();

    public MyGroup() {
        System.out.println("INSTANCE BARU: " + this.getClass().toString());
        double newWidth, newHeight;

        newWidth = getPreviousScene() == null ? Main.width : getPreviousScene().getView().getWidth();
        newHeight = getPreviousScene() == null ? Main.height : getPreviousScene().getView().getHeight();

//        view = new MyScene(this, newWidth, newHeight);
        prepareLayout();
        addListeners();
    }

    protected void onAfterNext() {

    }

    protected void onAfterBack() {

    }

    public void moveNextScene() {
        if (nextScene == null) {
            return;
        }

//        Main.primaryStage.setScene(nextScene.getView());
        Main.primaryStage.getScene().setRoot(nextScene);
        nextScene.onAfterNext();
    }

    public void movePreviousScene() {
        if (previousScene == null) {
            return;
        }

//        Main.primaryStage.setScene(previousScene.getView());
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

    public MyScene getView() {
        return view;
    }

    public void setView(MyScene view) {
        this.view = view;
    }
}
