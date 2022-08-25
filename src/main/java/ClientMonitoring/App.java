/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package ClientMonitoring;

/**
 *
 * @author bin
 */
public class App {

    private static DashBoard dashBoard;

    public static void main(String[] args) {
        dashBoard = new DashBoard();
        dashBoard.setVisible(true);
    }
}
