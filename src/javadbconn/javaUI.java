/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbconn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author Alistair
 */
public class javaUI  {
           
 
    public void javaUI() { 
        //Creating the Frame
        JFrame frame1 = new JFrame("Inventory Checker");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(750, 500);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Options");
        
        mb.add(m1);
        
        String[] products = {"Bread","Flour","Sugar"};
        JMenuItem m11 = new JMenuItem("Add Item");
        JMenuItem m12 = new JMenuItem("Edit Item");
        JMenuItem m13 = new JMenuItem("Remove Item");
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);

        //Creating the panel at bottom and adding components
        JPanel p1 = new JPanel(); // the panel is not visible in output
        JPanel p2 = new JPanel();
        JLabel label = new JLabel("Product Lookup:");
        JComboBox options = new JComboBox(products);
        //JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton allInv = new JButton("All Inventory");
        JButton search = new JButton("Search");
        //JButton reset = new JButton("Reset");
        //panel.add(label); // Components Added using Flow Layout
        //panel.add(tf);
        p1.add(allInv);
        p2.add(label);
        p2.add(options);
        p2.add(search);
        
        //panel.add(reset);

        // Text Area at the Center
        JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame1.getContentPane().add(BorderLayout.NORTH, mb);
        frame1.getContentPane().add(BorderLayout.CENTER, p2);
        frame1.getContentPane().add(BorderLayout.SOUTH, p1);
        frame1.setVisible(true);
        
        
        m11.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             newFrame("Add Item");
            }
        });
        
        
        
        allInv.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             newFrame("Full Inventory");
            }
        });
        
        
    }
    
    
    

 
    public static void newFrame(String name){
        JFrame nf = new JFrame(name);
        nf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        nf.setSize(400, 400);
        nf.setVisible(true);
    }
            
}

