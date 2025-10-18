/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sociotorcedor.main;

import com.sociotorcedor.view.PainelSocioTorcedor; 

public class Main {

    public static void main(String[] args) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println("Falha ao carregar o tema Nimbus: " + ex);
        }


        java.awt.EventQueue.invokeLater(() -> {
            new PainelSocioTorcedor().setVisible(true);
        });
    }
}