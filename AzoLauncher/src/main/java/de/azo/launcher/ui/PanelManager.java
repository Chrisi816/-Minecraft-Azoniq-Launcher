package de.azo.launcher.ui;

import de.azo.launcher.AzoLauncher;
import de.azo.launcher.ui.panel.IPanel;
import de.azo.launcher.ui.panels.includes.TopPanel;
import fr.arinonia.arilibfx.AriLibFX;
import fr.arinonia.arilibfx.ui.utils.ResizeHelper;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PanelManager {
    private final AzoLauncher azolauncher;
    private final Stage stage;
    private GridPane layout;
    private final TopPanel topPanel = new TopPanel();
    private final GridPane centerPanel = new GridPane();

    public PanelManager(AzoLauncher azoLauncher, Stage stage) {
        this.azolauncher = azoLauncher;
        this.stage = stage;
    }
    public void init(){
        this.stage.setTitle("Azo Launcher");
        this.stage.setMinWidth(1280);
        this.stage.setWidth(1280);
        this.stage.setMinHeight(720);
        this.stage.setHeight(720);
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.centerOnScreen();
        this.stage.show();

        this.layout = new GridPane();
        this.layout.setStyle(AriLibFX.setResponsiveBackground("https://s20.directupload.net/images/210608/xkjf6vpy.png"));
        this.stage.setScene(new Scene(this.layout));

        RowConstraints topPanalConstraints = new RowConstraints();
        topPanalConstraints.setValignment(VPos.TOP);
        topPanalConstraints.setMinHeight(25);
        topPanalConstraints.setMaxHeight(25);
        this.layout.getRowConstraints().addAll(topPanalConstraints, new RowConstraints());
        this.layout.add(this.topPanel.getLayout(),0,0);
        this.topPanel.init(this);

        this.layout.add(this.centerPanel,0,1);
        GridPane.setVgrow(this.centerPanel, Priority.ALWAYS);
        GridPane.setHgrow(this.centerPanel, Priority.ALWAYS);
        ResizeHelper.addResizeListener(this.stage);
    }
    public void showPanal(IPanel panel) {
        this.centerPanel.getChildren().clear();
        this.centerPanel.getChildren().add(panel.getLayout());
        panel.init(this);
        panel.onShow();
    }

    public Stage getStage() {
        return stage;
    }
    public AzoLauncher getAzolauncher() {
        return azolauncher;
    }
    public TopPanel getTopPanel() {
        return topPanel;
    }
}
