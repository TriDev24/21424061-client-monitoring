/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configs;

import java.io.Serializable;

/**
 *
 * @author bin
 */
public class ServerRequestPackage implements Serializable {

    private String action;
    private String directoryPath;

    public ServerRequestPackage(String action, String directoryPath) {
        super();
        this.action = action;
        this.directoryPath = directoryPath;
    }

    public String getAction() {
        return this.action;
    }

    public String getDirectoryPath() {
        return this.directoryPath;
    }

    public void writeAction(String newAction) {
        this.action = newAction;
    }
}
