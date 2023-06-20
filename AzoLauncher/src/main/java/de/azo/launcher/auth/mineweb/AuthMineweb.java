package de.azo.launcher.auth.mineweb;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.azo.launcher.Main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicReference;

public class AuthMineweb {
    private boolean connected = false;
    private String pseudo;
    private String hash;

    public boolean isConnected() { return connected;}

    public String getPseudo() {
        return pseudo;
    }
    public String getHash() {return hash;}

    public AuthMineweb(String pseudo, String password) {
         try {
             URLConnection connection = (new URL("https://www.minecraft.net/de-de/auth/start?username=" + pseudo+ "&password="+getSHA256(password)).openConnection());
             connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
             connection.connect();
             InputStream is = connection.getInputStream();
             BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             String InputLine;
             while((InputLine = in.readLine()) !=  null) {
                 switch (InputLine) {
                     case "succes_ok":
                         Main.logger.log("Ameldung ok"  );
                         setHash(getSHA256(password));
                         setPseudo(pseudo);
                         setConnected(true);
                         break;
                     case "error_password":
                         Main.logger.warn("Passwort ist Ungültig ");
                         break;
                     default:
                         break;
                 }
             }
             in.close();
         } catch (Exception e){
             Main.logger.warn(e.getMessage() );
         }
    }
    public String getInfos(String info) {
       if(isConnected()) {
           try {
               URLConnection connection = (new URL("https://www.minecraft.net/de-de/auth/getDataLauncher?username=" + pseudo+ "&password="+getHash()).openConnection());
               connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
               connection.connect();
               InputStream is = connection.getInputStream();
               BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
               String InputLine;
               while ((InputLine = in.readLine()) != null){
                   JsonObject jsonObject = (JsonObject) new JsonParser().parse(InputLine);
                   return String.valueOf(jsonObject.get(info)).replaceAll("\"", "");
               }
           } catch (Exception e){
               Main.logger.warn(e.getMessage());
           }
       } else {
           Main.logger.warn("Wir können dich nicht Anmelden");
       }
       return "";
    }
    private String getSHA256(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        AtomicReference<StringBuffer> stringBuffer = new AtomicReference<>(new StringBuffer());
        for(byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1 )stringBuffer.get().append("0");
            stringBuffer.get().append(hex);
        }
        return  stringBuffer.toString();
    }


    private void setConnected(boolean connected) {
        this.connected = connected;
    }
    private void setPseudo(String pseudo) {
        this.connected = connected;
    }
    private void setHash(String hash) {
        this.connected = connected;
    }

}
