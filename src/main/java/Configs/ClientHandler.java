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
    String clientAction;
    FileStructure fileStructure;
	
    public ClientHandler(ObjectOutputStream oos, ObjectInputStream ois, Socket socket, String clientAction) {
    	super();
	this.oos = oos;
	this.ois = ois;
	this.socket = socket;
	this.clientAction = clientAction;
    }
	
    @Override
    public void run() {
		
        try {
            ShippingData data = (ShippingData) ois.readObject();
            processClientData(data);
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    private void processClientData(ShippingData data) {
        
    }
}
