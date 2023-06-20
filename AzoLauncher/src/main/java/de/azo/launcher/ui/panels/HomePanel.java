package de.azo.launcher.ui.panels;

import com.sun.webkit.WebPage;
import de.azo.launcher.Main;
import de.azo.launcher.ui.PanelManager;
import de.azo.launcher.ui.panel.Panel;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.arinonia.arilibfx.ui.component.AProgressBar;
import fr.arinonia.arilibfx.updater.DownloadJob;
import fr.arinonia.arilibfx.updater.DownloadListener;
import javafx.collections.ListChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.Set;

public class HomePanel extends Panel implements DownloadListener {
    private GridPane centerPane = new GridPane();
    private AProgressBar leftDownloadBar;
    private AProgressBar getLeftDownloadBar;
    private AProgressBar aBigDownloadBar;
    @Override
    public void init(PanelManager panelManager){
        super.init(panelManager);
        ColumnConstraints menePainConstraint = new ColumnConstraints();
        menePainConstraint.setHalignment(HPos.LEFT);
        menePainConstraint.setMinWidth(300);
        menePainConstraint.setMaxWidth(300);
        this.layout.getColumnConstraints().addAll(menePainConstraint, new ColumnConstraints());

        GridPane leftBarPane = new GridPane();
        GridPane.setHgrow(leftBarPane, Priority.ALWAYS);
        GridPane.setVgrow(leftBarPane, Priority.ALWAYS);
        this.layout.add(leftBarPane,0,0);
        this.layout.add(this.centerPane,1,0);
        GridPane.setVgrow(centerPane, Priority.ALWAYS);
        GridPane.setHgrow(centerPane, Priority.ALWAYS);

        Separator rightSeperator = new Separator();
        GridPane.setHgrow(leftBarPane, Priority.ALWAYS);
        GridPane.setVgrow(leftBarPane, Priority.ALWAYS);
        GridPane.setHalignment(rightSeperator, HPos.RIGHT);
        rightSeperator.setOrientation(Orientation.VERTICAL);
        rightSeperator.setTranslateY(1);
        rightSeperator.setTranslateX(4);
        rightSeperator.setMinWidth(2);
        rightSeperator.setOpacity(0.30D);

        GridPane bottomGridPane = new GridPane();
        GridPane.setHgrow(bottomGridPane, Priority.ALWAYS);
        GridPane.setVgrow(bottomGridPane, Priority.ALWAYS);
        GridPane.setHalignment(bottomGridPane, HPos.LEFT);
        GridPane.setValignment(bottomGridPane, VPos.TOP);
        bottomGridPane.setTranslateY(30);
        bottomGridPane.setMinHeight(40);
        bottomGridPane.setMaxHeight(40);
        bottomGridPane.setMinWidth(300);
        bottomGridPane.setMaxWidth(300);
        showLeftBar(bottomGridPane);
        leftBarPane.getChildren().addAll(rightSeperator, bottomGridPane);


        ScrollPane scrollPane = new ScrollPane();
        GridPane.setVgrow(scrollPane,Priority.ALWAYS);
        GridPane.setHgrow(scrollPane, Priority.ALWAYS);
        scrollPane.getStylesheets().addAll(Main.class.getResource("/css/scrollbar.css").toExternalForm());

        VBox vBox = new VBox();
        GridPane.setVgrow(vBox, Priority.ALWAYS);
        GridPane.setHgrow(vBox, Priority.ALWAYS);
        vBox.setMinHeight(200);
        vBox.setMinWidth(900);
        vBox.setMaxWidth(900);
        vBox.setAlignment(Pos.CENTER);
        vBox.setTranslateX(30);

        GridPane topPanel = new GridPane();
        GridPane.setVgrow(topPanel, Priority.ALWAYS);
        GridPane.setHgrow(topPanel, Priority.ALWAYS);
        GridPane.setValignment(topPanel, VPos.TOP);
        topPanel.setMinWidth(880);
        topPanel.setMaxWidth(880);
        topPanel.setMinHeight(340);
        topPanel.setMaxHeight(340);
        addTopPanel(topPanel);
        this.centerPane.getChildren().add(scrollPane);
        scrollPane.setContent(vBox);
        vBox.getChildren().add(0,topPanel);
    }

    private void addTopPanel(GridPane pane) {
        Label azotitle = new Label("Azo Client");
        GridPane.setVgrow(azotitle, Priority.ALWAYS);
        GridPane.setHgrow(azotitle, Priority.ALWAYS);
        GridPane.setValignment(azotitle, VPos.TOP);
        azotitle.setStyle("-fx-font-size: 26px; -fx-text-fill: #fff; -fx-font-weight: bold;");
        azotitle.setTranslateY(20);

        Label farm = new Label("Farm2Win");
        GridPane.setVgrow(farm, Priority.ALWAYS);
        GridPane.setHgrow(farm, Priority.ALWAYS);
        GridPane.setValignment(farm, VPos.TOP);
        farm.setStyle("-fx-font-size: 14px; -fx-text-fill: #fff; -fx-opacity: 70%;");
        farm.setTranslateY(70);

        Label complet = new Label("complet");
        GridPane.setVgrow(complet, Priority.ALWAYS);
        GridPane.setHgrow(complet, Priority.ALWAYS);
        GridPane.setValignment(complet, VPos.TOP);
        complet.setStyle("-fx-font-size: 14px; -fx-text-fill: #fff; -fx-opacity: 70%;");
        complet.setTranslateY(70);
        complet.setTranslateX(80);

        Label fluide = new Label("Fluide");
        GridPane.setVgrow(fluide, Priority.ALWAYS);
        GridPane.setHgrow(fluide, Priority.ALWAYS);
        GridPane.setValignment(fluide, VPos.TOP);
        fluide.setStyle("-fx-font-size: 14px; -fx-text-fill: #fff; -fx-opacity: 70%;");
        fluide.setTranslateY(70);
        fluide.setTranslateX(140);

        Label decs = new Label("bla bla bla ");
        GridPane.setVgrow(fluide, Priority.ALWAYS);
        GridPane.setHgrow(fluide, Priority.ALWAYS);
        GridPane.setValignment(fluide, VPos.TOP);
        fluide.setStyle("-fx-font-size: 14px; -fx-text-fill: #bcc6e7; -fx-opacity: 70%;");

        GridPane bigVideo = new GridPane();
        GridPane.setVgrow(fluide, Priority.ALWAYS);
        GridPane.setHgrow(fluide, Priority.ALWAYS);
        GridPane.setValignment(fluide, VPos.TOP);
        GridPane.setHalignment(bigVideo,HPos.RIGHT);
        bigVideo.setMinWidth(320);
        bigVideo.setMaxWidth(320);
        bigVideo.setMinHeight(430);
        bigVideo.setMaxHeight(430);
        String content_url = "<iframe style='background : rgba(0,0,0,0);' width=\"420\" height=\"320\" src=\"https://www.twitch.tv/videos/1047614443\" frameborder=\"0\" allow=\"accelermeter; autoplay=1; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></ifrane>";
        WebView webView = new WebView();
        webView.setStyle("overflow-x: hidden; overflow-y: hidden");
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(content_url);
        bigVideo.getChildren().add(webView);
        webView.getChildrenUnmodifiable().addListener((ListChangeListener<? super Node>) change->{
            Set<Node> deadSeaScrolls = webView.lookupAll(".scroll-bar");
            for (Node scroll : deadSeaScrolls){
                scroll.setVisible(false);
            }
        });

        try{
            Field field = webEngine.getClass().getDeclaredField("page");
            field.setAccessible(true);
            WebPage page = (WebPage)field.get(webEngine);
            SwingUtilities.invokeLater(()->page.setBackgroundColor(new java.awt.Color(255,255,255,0).getRGB()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Button installButton = new Button("Installer");
        GridPane.setVgrow(installButton, Priority.ALWAYS);
        GridPane.setHgrow(installButton,Priority.ALWAYS);
        GridPane.setValignment(installButton, VPos.TOP);
        GridPane.setHalignment(installButton,HPos.LEFT);
        installButton.setTranslateY(260);
        installButton.setMinWidth(140);
        installButton.setMaxHeight(40);
        installButton.setStyle("-fx-background-color: #115ffa; -fy-border-radius: 0; -fy-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: #fff");
        installButton.setOnMouseEntered(e->this.layout.setCursor(Cursor.HAND));
        installButton.setOnMouseExited(e->this.layout.setCursor(Cursor.DEFAULT));
        installButton.setOnMouseClicked(e->this.panelManager.getAzolauncher().launchGame());

        Button settingsButton = new Button();
        GridPane.setVgrow(settingsButton, Priority.ALWAYS);
        GridPane.setHgrow(settingsButton,Priority.ALWAYS);
        GridPane.setValignment(settingsButton, VPos.TOP);
        GridPane.setHalignment(settingsButton,HPos.LEFT);
        MaterialDesignIconView settingsIcon = new MaterialDesignIconView(MaterialDesignIcon.SETTINGS);
        settingsIcon.setSize("18px");
        settingsIcon.setFill(Color.rgb(17,95,170));
        settingsButton.setStyle("-fx-background-color: rgba(255,255,255,0.0); -fy-border-color: #115faa; -fy-border-radius: 2px;");
        settingsButton.setTranslateX(150);
        settingsButton.setTranslateY(266);
        settingsButton.setMinWidth(26);
        settingsButton.setMaxHeight(26);
        settingsButton.setMaxWidth(26);
        settingsButton.setMinHeight(26);
        settingsButton.setGraphic(settingsIcon);
        settingsButton.setOnMouseEntered(e->this.layout.setCursor(Cursor.HAND));
        settingsButton.setOnMouseExited(e->this.layout.setCursor(Cursor.DEFAULT));
        settingsButton.setOnMouseClicked(e->System.out.println("Clicked! "));

        aBigDownloadBar = new AProgressBar(400,20);
        aBigDownloadBar.setBackgroundColor(Color.rgb(3,48,90));
        Stop[] stops = new Stop[]{new Stop(0,Color.rgb(7,85,136)), new Stop(1,Color.rgb(3,163,219))};
        LinearGradient lg = new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE,stops);
        aBigDownloadBar.setForegroundColor(lg);
        aBigDownloadBar.setTranslateY(160);
        pane.getChildren().addAll(azotitle, farm, complet, fluide, decs, installButton, settingsButton, aBigDownloadBar);
    }

    private void showLeftBar(GridPane pane){
        Separator blueLeftSeparator = new Separator();
        GridPane.setVgrow(blueLeftSeparator, Priority.ALWAYS);
        GridPane.setHgrow(blueLeftSeparator, Priority.ALWAYS);
        blueLeftSeparator.setOrientation(Orientation.VERTICAL);
        blueLeftSeparator.setMinWidth(3);
        blueLeftSeparator.setMaxWidth(3);
        blueLeftSeparator.setMinHeight(40);
        blueLeftSeparator.setMaxHeight(40);
        blueLeftSeparator.setStyle("-fx-background-color: rgb(5,179,242); -fx-border-width: 3 3 3 0; -fx-border-color: rgb(5,179,242);");

        Image logoImageAzo = new Image(Main.class.getResource("/mojang.png").toExternalForm());
        ImageView imageViewAzo = new ImageView(logoImageAzo);
        GridPane.setVgrow(blueLeftSeparator, Priority.ALWAYS);
        GridPane.setHgrow(blueLeftSeparator, Priority.ALWAYS);
        GridPane.setValignment(imageViewAzo, VPos.CENTER);
        imageViewAzo.setTranslateX(34);
        imageViewAzo.setFitHeight(28);
        imageViewAzo.setFitWidth(28);

        Label azoLabel = new Label();
        GridPane.setVgrow(blueLeftSeparator, Priority.ALWAYS);
        GridPane.setHgrow(blueLeftSeparator, Priority.ALWAYS);
        GridPane.setValignment(imageViewAzo, VPos.CENTER);
        azoLabel.setTranslateX(90);
        azoLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #fff;");

        leftDownloadBar = new AProgressBar(170,3);
        leftDownloadBar.setBackgroundColor(Color.rgb(222,222,222,0.3d));
        leftDownloadBar.setForegroundColor(Color.rgb(255,255,255));
        leftDownloadBar.setTranslateX(90.0d);
        leftDownloadBar.setTranslateY(12.0d);
        pane.getChildren().addAll(blueLeftSeparator, imageViewAzo, azoLabel, leftDownloadBar);
    }

    @Override
    public void onDownloadJobFinished(DownloadJob downloadJob) {
          Main.logger.log("`"+ downloadJob.getName() + "is finsished");
    }

    @Override
    public void onDownloadJobProgressChanged(DownloadJob downloadJob) {
              this.leftDownloadBar.setProgress(downloadJob.getAllFiles().size() - downloadJob.getRemainingFiles().size() , downloadJob.getAllFiles().size());
        this.leftDownloadBar.setProgress(downloadJob.getAllFiles().size() - downloadJob.getRemainingFiles().size() , downloadJob.getAllFiles().size());
    }

    @Override
    public void onDownloadJobStarted(DownloadJob downloadJob) {
           Main.logger.log("`" + downloadJob.getName() + "` started to download");
    }
}
