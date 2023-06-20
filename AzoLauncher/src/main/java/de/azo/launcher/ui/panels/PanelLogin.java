package de.azo.launcher.ui.panels;

import de.azo.launcher.Main;
import de.azo.launcher.auth.mineweb.AuthMineweb;
import de.azo.launcher.ui.PanelManager;
import de.azo.launcher.ui.panel.Panel;
import fr.arinonia.arilibfx.auth.premium.Auth;
import fr.arinonia.arilibfx.auth.premium.exceptions.AuthenticationUnavailableException;
import fr.arinonia.arilibfx.auth.premium.exceptions.RequestException;
import fr.arinonia.arilibfx.auth.premium.responses.AuthenticationResponse;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;


import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicBoolean;

public class PanelLogin extends Panel {
    private HomePanel homePanel;

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        GridPane loginPanel = new GridPane();
        GridPane mainPanel = new GridPane();
        GridPane bottomPanel = new GridPane();
        AtomicBoolean connectWithMojang = new AtomicBoolean(false);

        loginPanel.setMaxWidth(400);
        loginPanel.setMinWidth(400);
        loginPanel.setMaxHeight(580);
        loginPanel.setMinHeight(580);

        GridPane.setVgrow(loginPanel, Priority.ALWAYS);
        GridPane.setHgrow(loginPanel,Priority.ALWAYS);
        GridPane.setValignment(loginPanel, VPos.CENTER);
        GridPane.setHalignment(loginPanel, HPos.CENTER);

        RowConstraints bottomConstraints = new RowConstraints();
        bottomConstraints.setValignment(VPos.BOTTOM);
        bottomConstraints.setMaxHeight(55);
        loginPanel.getRowConstraints().addAll(new RowConstraints(), bottomConstraints);
        loginPanel.add(mainPanel,0,0);
        loginPanel.add(bottomPanel,0,1);

        GridPane.setVgrow(mainPanel,Priority.ALWAYS);
        GridPane.setHgrow(mainPanel,Priority.ALWAYS);
        GridPane.setVgrow(bottomPanel,Priority.ALWAYS);
        GridPane.setHgrow(bottomPanel,Priority.ALWAYS);

        mainPanel.setStyle("-fx-background-color: #181818;");
        bottomPanel.setStyle("-fx-background-color: #181818; -fx-opacity: 50%");
        javafx.scene.control.Label noAccount = new javafx.scene.control.Label("Du hast noch kein Account?");
        javafx.scene.control.Label registerHere = new javafx.scene.control.Label("Sign up");

        GridPane.setVgrow(noAccount, Priority.ALWAYS);
        GridPane.setHgrow(noAccount, Priority.ALWAYS );
        GridPane.setValignment(noAccount, VPos.TOP);
        GridPane.setHalignment(noAccount, HPos.CENTER);
        noAccount.setStyle("-fx-text-fill: #bcc6e7; -fx-font-size: 14px;");

        GridPane.setVgrow(registerHere, Priority.ALWAYS);
        GridPane.setHgrow(registerHere, Priority.ALWAYS );
        GridPane.setValignment(registerHere, VPos.BOTTOM);
        GridPane.setHalignment(registerHere, HPos.CENTER);
        registerHere.setStyle("-fx-text-fill: #68a7ed; -fx-font-size: 14px;");
        registerHere.setUnderline(true);
        registerHere.setTranslateY(-10);
        registerHere.setOnMouseEntered(e->this.layout.setCursor(Cursor.HAND));
        registerHere.setOnMouseExited(e->this.layout.setCursor(Cursor.DEFAULT));
        registerHere.setOnMouseClicked(e->{
               if(connectWithMojang.get()){
                   openUrl("https://www.minecraft.net/de-de/login?return_url=/de-de/profile");
               }else{
                   return;
               }
        });
        bottomPanel.getChildren().addAll(noAccount,registerHere);
        this.layout.getChildren().add(loginPanel);

        Label connectLabel = new Label("Anmeldung");
        GridPane.setVgrow(connectLabel,Priority.ALWAYS);
        GridPane.setHgrow(connectLabel,Priority.ALWAYS);
        GridPane.setValignment(connectLabel, VPos.TOP);
        connectLabel.setTranslateY(27);
        connectLabel.setTranslateX(37.5);
        connectLabel.setStyle("-fx-text-fill: #bcc6e7; -fx-font-size: 16px");

        Separator connectSeparator = new Separator();
        GridPane.setVgrow(connectSeparator,Priority.ALWAYS);
        GridPane.setHgrow(connectSeparator,Priority.ALWAYS);
        GridPane.setValignment(connectSeparator, VPos.TOP);
        GridPane.setHalignment(connectSeparator,HPos.CENTER);
        connectSeparator.setTranslateY(60);
        connectSeparator.setMinWidth(325);
        connectSeparator.setMaxWidth(325);
        connectSeparator.setStyle("-fx-background-color: #fff; -fx-opacity: 50%");

        Label usernameLabel = new Label("Name");
        GridPane.setVgrow(usernameLabel,Priority.ALWAYS);
        GridPane.setHgrow(usernameLabel,Priority.ALWAYS);
        GridPane.setValignment(usernameLabel, VPos.TOP);
        GridPane.setHalignment(usernameLabel,HPos.LEFT);
        usernameLabel.setStyle("-fx-text-fill: #96bad3; -fx-font-size: 14px");
        usernameLabel.setTranslateY(110);
        usernameLabel.setTranslateX(37.5);

        javafx.scene.control.TextField usernameField = new javafx.scene.control.TextField();
        GridPane.setVgrow(usernameField,Priority.ALWAYS);
        GridPane.setHgrow(usernameField,Priority.ALWAYS);
        GridPane.setValignment(usernameField, VPos.TOP);
        GridPane.setHalignment(usernameField, HPos.LEFT);
        usernameField.setStyle("-fx-background-color: #1e1e1e; -fx-font-size: 16px; -fx-text-fill: #e5e5e5");
        usernameField.setMaxWidth(325);
        usernameField.setMaxHeight(40);
        usernameField.setTranslateX(37.5);
        usernameField.setTranslateY(140);

        Separator usernameSeparator = new Separator();
        GridPane.setVgrow(usernameSeparator,Priority.ALWAYS);
        GridPane.setHgrow(usernameSeparator,Priority.ALWAYS);
        GridPane.setValignment(usernameSeparator, VPos.TOP);
        GridPane.setHalignment(usernameSeparator,HPos.CENTER);
        usernameSeparator.setTranslateY(181);
        usernameSeparator.setMinWidth(325);
        usernameSeparator.setMaxWidth(325);
        usernameSeparator.setMaxHeight(1);
        usernameSeparator.setStyle("-fx-opacity: 40%");

        Label passwordLabel = new Label("Passwort");
        GridPane.setVgrow(passwordLabel,Priority.ALWAYS);
        GridPane.setHgrow(passwordLabel,Priority.ALWAYS);
        GridPane.setValignment(passwordLabel, VPos.TOP);
        GridPane.setHalignment(passwordLabel,HPos.LEFT);
        passwordLabel.setStyle("-fx-text-fill: #96bad3; -fx-font-size: 14px");
        passwordLabel.setTranslateY(200);
        passwordLabel.setTranslateX(37.5);

        javafx.scene.control.PasswordField passwordField = new javafx.scene.control.PasswordField();
        GridPane.setVgrow(passwordField,Priority.ALWAYS);
        GridPane.setHgrow(passwordField,Priority.ALWAYS);
        GridPane.setValignment(passwordField, VPos.TOP);
        GridPane.setHalignment(passwordField, HPos.LEFT);
        passwordField.setStyle("-fx-background-color: #1e1e1e; -fx-font-size: 16px; -fx-text-fill: #e5e5e5");
        passwordField.setMaxWidth(325);
        passwordField.setMaxHeight(40);
        passwordField.setTranslateX(37.5);
        passwordField.setTranslateY(230);

        Separator passwordSeparator = new Separator();
        GridPane.setVgrow(passwordSeparator,Priority.ALWAYS);
        GridPane.setHgrow(passwordSeparator,Priority.ALWAYS);
        GridPane.setValignment(passwordSeparator, VPos.TOP);
        GridPane.setHalignment(passwordSeparator,HPos.CENTER);
        passwordSeparator.setTranslateY(271);
        passwordSeparator.setMinWidth(325);
        passwordSeparator.setMaxWidth(325);
        passwordSeparator.setMaxHeight(1);
        passwordSeparator.setStyle("-fx-opacity: 40%");

        Label forgotPasswordLabel = new Label("Passwort Vergessen? ");
        GridPane.setVgrow(forgotPasswordLabel,Priority.ALWAYS);
        GridPane.setHgrow(forgotPasswordLabel,Priority.ALWAYS);
        GridPane.setValignment(forgotPasswordLabel, VPos.CENTER);
        GridPane.setHalignment(forgotPasswordLabel, HPos.LEFT);
        forgotPasswordLabel.setStyle("-fx-text-fill: #69a7ed; -fx-font-size: 12px");
        forgotPasswordLabel.setUnderline(true);
        forgotPasswordLabel.setTranslateX(37.5);
        forgotPasswordLabel.setTranslateY(30);
        forgotPasswordLabel.setOnMouseEntered(e->this.layout.setCursor(Cursor.HAND));
        forgotPasswordLabel.setOnMouseExited(e->this.layout.setCursor(Cursor.DEFAULT));
        forgotPasswordLabel.setOnMouseClicked(e->{
            if(connectWithMojang.get()){
                openUrl("https://www.minecraft.net/de-de/password/forgot");
            }else{
                return;
            }
        });
        javafx.scene.control.Button connectionButton = new javafx.scene.control.Button("Anmelden");
        GridPane.setVgrow(connectionButton,Priority.ALWAYS);
        GridPane.setHgrow(connectionButton,Priority.ALWAYS);
        GridPane.setValignment(connectionButton, VPos.CENTER);
        GridPane.setHalignment(connectionButton, HPos.LEFT);
        connectionButton.setTranslateX(37.5);
        connectionButton.setTranslateY(80);
        connectionButton.setMinWidth(325);
        connectionButton.setMinHeight(50);
        connectionButton.setStyle("-fx-background-color: #007dbe; -fx-border-radius: 0px; -fx-background-insets: 0; -fx-font-size: 14px");
        connectionButton.setOnMouseEntered(e->this.layout.setCursor(Cursor.HAND));
        connectionButton.setOnMouseExited(e->this.layout.setCursor(Cursor.DEFAULT));
        connectionButton.setOnMouseClicked(e->{
            if(connectWithMojang.get()){
                try {
                    AuthenticationResponse response = Auth.authenticate(usernameField.getText( ),passwordField.getText());
                    Main.logger.log("=================[Auth]===============");
                    Main.logger.log("Acces token: " + response.getAccessToken() );
                    Main.logger.log("Account nam: " + response.getSelectedProfile().getName());
                    Main.logger.log("Account id: "  + response.getSelectedProfile().getUUID());
                    Main.logger.log("======================================");
                    this.panelManager.showPanal(new HomePanel());
                } catch (RequestException | AuthenticationUnavailableException ex) {
                    Main.logger.warn(ex.getMessage());
                }
            }else{
                AuthMineweb authMineweb = new AuthMineweb(usernameField.getText(), passwordField.getText());
                if(authMineweb.isConnected()){
                    Main.logger.log("Du kannst dich nicht Amelden" + authMineweb.getInfos("pseudo") + "\n Rank : " + authMineweb.getInfos("rank"));
                    this.panelManager.showPanal(new HomePanel());
                }
            }
            this.panelManager.showPanal(homePanel = new HomePanel());
        });
        Separator chooseConnectSeparator = new Separator();
        GridPane.setVgrow(chooseConnectSeparator,Priority.ALWAYS);
        GridPane.setHgrow(chooseConnectSeparator,Priority.ALWAYS);
        GridPane.setValignment(chooseConnectSeparator, VPos.CENTER);
        GridPane.setHalignment(chooseConnectSeparator, HPos.CENTER);
        chooseConnectSeparator.setTranslateY(130);
        chooseConnectSeparator.setTranslateX(0);
        chooseConnectSeparator.setMaxWidth(325);
        chooseConnectSeparator.setStyle("-fx-opacity: 30%");


        Button chooseConnection = new Button ("Anmeldung mit");
        GridPane.setVgrow(chooseConnection,Priority.ALWAYS);
        GridPane.setHgrow(chooseConnection,Priority.ALWAYS);
        GridPane.setValignment(chooseConnection, VPos.CENTER);
        GridPane.setHalignment(chooseConnection, HPos.CENTER);
        chooseConnection.setTranslateY(120);
        chooseConnection.setStyle("-fx-background-color: #181818; -fx-text-fill: #5e5e5e; -fx-font-size: 14px;");

        // Image logoImageMojang = new javafx.scene.image.Image(String.valueOf(Main.class.getResource("/mojang.png")))

       // ImageView imageViewMojang = new ImageView((Element) logoImageMojang);

        Button mojangButton = new Button("Mojang");
        GridPane.setVgrow(mojangButton ,Priority.ALWAYS);
        GridPane.setHgrow(mojangButton,Priority.ALWAYS);
        GridPane.setValignment(mojangButton, VPos.CENTER);
        GridPane.setHalignment(mojangButton, HPos.LEFT);
        mojangButton.setTranslateX(37.5);
        mojangButton.setTranslateY(170);
        mojangButton.setMinHeight(40);
        mojangButton.setMinWidth(140);
        mojangButton.setStyle("-fx-background-color: #34aa2f; -fx-border-radius: 0px; -fx-background-insets: 0; -fx-font-size: 14px ; -fx-text-fill: #fff");
        mojangButton.setOnMouseEntered(e->this.layout.setCursor(Cursor.HAND));
        mojangButton.setOnMouseExited(e->this.layout.setCursor(Cursor.DEFAULT));
        mojangButton.setOnMouseClicked(e->{
            if(connectWithMojang.get()){
                connectWithMojang.set(false);
                usernameLabel.setText("Name des Spielers");
                mojangButton.setText("Mojang");
            }else{
                connectWithMojang.set(true);
                usernameLabel.setText("Email Adress");
                mojangButton.setText("Azo Launcher");
            }
        });

        mainPanel.getChildren().addAll(connectLabel, connectSeparator, usernameField, usernameLabel, usernameSeparator, passwordField, passwordLabel, passwordSeparator, forgotPasswordLabel, connectionButton,
        chooseConnection, chooseConnectSeparator, mojangButton);
    }
    private void openUrl(String url){
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e){
            Main.logger.warn(e.getMessage());
        }
    }
    public  HomePanel getHomePanel(){
        return homePanel;
    }
}
