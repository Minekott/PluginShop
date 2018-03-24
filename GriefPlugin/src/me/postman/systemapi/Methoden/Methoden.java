package me.postman.systemapi.Methoden;

import me.postman.systemapi.Main.Main;
import org.bukkit.entity.Player;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Methoden
{

    public static void download(URL url, String path, String name, Player p){
        try {
            System.out.println("Downloading "+name+"...");
            final URLConnection conn = url.openConnection();
            final InputStream is = new BufferedInputStream(conn.getInputStream());
            final OutputStream os = new BufferedOutputStream(new FileOutputStream(path+name));
            byte[] chunk = new byte[1024];
            int chunkSize;
            while ((chunkSize = is.read(chunk)) != -1) {
                os.write(chunk, 0, chunkSize);
            }
            os.flush();
            os.close();
            is.close();
            p.sendMessage(Main.prefix + "§aThe download of §c" + name + " §ahas been finished.");
        } catch (IOException e) {

        }
    }
}
