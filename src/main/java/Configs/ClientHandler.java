/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configs;

import Configs.FileStructure;
import Configs.ShippingData;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bin
 */
public class ClientHandler extends Thread {

    ObjectOutputStream oos;
    ObjectInputStream ois;
    Socket socket;
    String action;
    FileStructure fileStructure;
    IHandler handler;
    

    public ClientHandler(ObjectOutputStream oos, ObjectInputStream ois, Socket socket, String action, IHandler handler) {
        super();
        this.oos = oos;
        this.ois = ois;
        this.socket = socket;
        this.action = action;
        this.handler = handler;
    }

    @Override
    public void run() {

        try {
            ShippingData data = (ShippingData) ois.readObject();
            handleProcess(data);
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleProcess(ShippingData data) {
        this.handler.handleProcess(data);
    }

    public void changeFolderPath(String path) {
        try {
            oos.writeObject(new ServerRequestPackage(ActionName.ChangeClientFolder, path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
