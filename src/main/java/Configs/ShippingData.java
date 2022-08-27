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
    private String message;
    private String actionDescription;
    private FileStructure fileStructure;
    
    
    public ShippingData(String clientIP, LocalDateTime createdAt, String message, FileStructure fileStructure) {
        this.clientIP = clientIP;
        this.createdAt = createdAt;
        this.message = message;
        this.fileStructure = fileStructure;
    }
    
    public String getClientIP() {
        return this.clientIP;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    
    public String getActionDescription() {
        return this.actionDescription;
    }
    
    public FileStructure getFileStructure() {
        return this.fileStructure;
    }
}
