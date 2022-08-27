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
public class ServerAction implements Serializable {
    private String action;
    
    public ServerAction(String action) {
	super();
        this.action = action;
    }
    
    public String getAction() {
        return this.action;
    }
    
    public void writeAction(String newAction) {
        this.action = newAction;
    }
}
