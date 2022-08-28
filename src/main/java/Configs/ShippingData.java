/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configs;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author bin
 */
public class ShippingData implements Serializable {
    private String clientIP;
    private LocalDateTime createdAt;
    private String action;
    private FileStructure fileStructure;
    private String defaultDirectory;
    private String description;
    
    
    public ShippingData(String clientIP, String action, LocalDateTime createdAt, String defaultDirectory, FileStructure fileStructure, String description) {
        this.clientIP = clientIP;
        this.action = action;
        this.createdAt = createdAt;
        this.defaultDirectory = defaultDirectory;
        this.fileStructure = fileStructure;
        this.description = description;
    }
    
    public String getClientIP() {
        return this.clientIP;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    
    public String getAction() {
        return this.action;
    }
    
    public String getDefaultDirectory() {
        return this.defaultDirectory;
    }
    
    public FileStructure getFileStructure() {
        return this.fileStructure;
    }
}
