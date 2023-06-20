package de.azo.launcher;

import de.azo.launcher.files.FileManager;
import de.azo.launcher.ui.PanelManager;
import de.azo.launcher.ui.panels.PanelLogin;
import fr.arinonia.arilibfx.updater.DownloadJob;
import fr.arinonia.arilibfx.updater.DownloadManager;
import fr.arinonia.arilibfx.updater.Updater;
import javafx.stage.Stage;

public class AzoLauncher {

    private final FileManager fileManager = new FileManager("AzoClienttest");

    private PanelManager panelManager;
    private PanelLogin panelLogin;

    public void init(Stage stage){
        this.panelManager = new PanelManager(this,stage);
        this.panelManager.init();
        this.panelManager.showPanal(panelLogin = new PanelLogin());
    }

    public void launchGame(){
        Updater updater = new Updater();
        DownloadJob game = new DownloadJob("game",this.panelLogin.getHomePanel());
        game.setExecutorService(5);
        updater.addJobToDownload(new DownloadManager("file:///C:/Users/jedch/Desktop/instance.json", game,fileManager.getGameFolder()));
        updater.setFileDeleter(true);
        Thread thread = new Thread(updater::start);
        thread.start();
    }
}
