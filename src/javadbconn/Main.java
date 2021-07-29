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
import java.sql.Connection;
import java.sql.PreparedStatement;


public class Main
{
public static void main(String[] args) throws Exception 
    {
    
    //Scanner sc = new Scanner(System.in);
    //System.out.println("Create the inventory table?");
    //String a = sc.nextLine();
    
    createTable(); 
    createUI();

    }

public static void createTable() throws Exception 
    {
        try
        {
            Connection con = conn.getConnection();
            String stringQuery = "Insert into inventory values (12349678,'Bread',15); ";
            PreparedStatement query = con.prepareStatement(stringQuery);
            //PreparedStatement query = con.prepareStatement("Insert into inventory values (123499678,'Bread',15); ");
            //query.executeUpdate();
        }catch(Exception e){System.out.println(e);}
    finally{System.out.println("Table Created");}
    }

        

public static void createUI()
    {
        JFrame frame1 = new JFrame("Inventory Checker");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(750, 500);
        int count = conn.count();
        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Options");
        
        mb.add(m1);
        int i = 0;
        String[] products = conn.getProd();
        
        JMenuItem addItem = new JMenuItem("Add Item");
        JMenuItem editItem = new JMenuItem("Edit Item");
        JMenuItem removeItem = new JMenuItem("Remove Item");
        m1.add(addItem);
        m1.add(editItem);
        m1.add(removeItem);

        //Creating the panel at bottom and adding components
        JPanel sp = new JPanel();
        JPanel p1 = new JPanel(); // the panel is not visible in output
        JPanel p2 = new JPanel();
        JLabel label = new JLabel("Product Lookup:");
        JComboBox options = new JComboBox(products);
        //JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton allInv = new JButton("All Inventory");
        JButton search = new JButton("Search");
        //JButton reset = new JButton("Reset");
        //p1.add(allInv);
        p2.add(label);
        p2.add(options);
        p2.add(search);
        sp.add(p2);
        sp.add(p1);
        //panel.add(reset);

        // Text Area at the Center
        JTextArea ta = new JTextArea();
        //Adding Components to the frame.
        //frame1.setLayout(new GridLayout(0, 1));
        frame1.getContentPane().add(BorderLayout.NORTH, mb);
        frame1.getContentPane().add(BorderLayout.CENTER, sp);
        frame1.getContentPane().add(BorderLayout.SOUTH, allInv);
        frame1.setVisible(true);
        
        addItem.addActionListener( new ActionListener() // Add Function
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JFrame nf = new JFrame("Add Item");
                nf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                nf.setSize(500, 300);
                nf.setVisible(true);
                
                JLabel lbl = new JLabel("Add Item:");
                JLabel lbl1 = new JLabel("Item Name:");
                JLabel lbl2 = new JLabel("Item Amount:");
                JLabel lbl3 = new JLabel("Price: ");
                JLabel res = new JLabel("blank");
                JButton confirm = new JButton("Add Item");
                JPanel p1 = new JPanel();
                JPanel p2 = new JPanel();
                JPanel p3 = new JPanel();
                JPanel p4 = new JPanel();
                JPanel p5 = new JPanel();
                JTextField prod = new JTextField(16);
                JTextField amt = new JTextField(16);
                JTextField price = new JTextField(16);
                
                p1.setLayout(new GridLayout(0, 2));
                p1.add(lbl1);
                p1.add(prod);
                p1.add(lbl2);
                p1.add(amt);
                p1.add(lbl3);
                p1.add(price);
                p2.add(confirm);
                p3.add(res);
                nf.add(BorderLayout.NORTH, p1);
                nf.add(BorderLayout.CENTER, p2);
                nf.add(BorderLayout.SOUTH, p3);
                
                //nf.getContentPane().add(BorderLayout.PAGE_END, p2);
                               
                confirm.addActionListener( new ActionListener() 
                {
                @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                    String    x = prod.getText();
                    String    y = amt.getText();
                    String    z = price.getText();
                    String query = "Insert into inventory values ('"+x+"','"+y+"',"+z+");";
                    System.out.println(query);
                    res.setText(query);
                    conn.executeQuery(query);
                    nf.hide();
                    }
                });       
            }
        });
       
        allInv.addActionListener( new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int rows = conn.count();
                String[] cols  =  conn.getCols();
                int colNo = cols.length;
                System.out.println("rows: "+rows);
                
                JPanel p = new JPanel();
                p.setLayout(new GridLayout(0,colNo));
                String query = "Select * from inventory";
                String[][] inv = conn.fiQuery(query);
                
                JFrame nf = new JFrame("Full Invenory");
                nf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                nf.setSize(400, 400);
                nf.setVisible(true);
                for(int j = 0; j < colNo; j++)
                    {
                        JLabel temp = new JLabel(cols[i]);
                        temp.setFont(temp.getFont().deriveFont(Font.BOLD, 14f));
                        p.add(temp);
                    }
                
                for (int i = 0; i < rows; i++)
                {   
                    for(int j = 0; j < colNo; j++)
                    {
                        JLabel temp = new JLabel(inv[i][j]);
                        p.add(temp);
                    }

                }
                nf.add(BorderLayout.CENTER, new JScrollPane(p));
            }
        });
        
        editItem.addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            
            String[] prod = conn.getProd();
                 
            String[] cols = conn.getCols();
            JFrame nf = new JFrame("Edit Item");
            nf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            nf.setSize(400, 400);
            nf.setVisible(true);
            JPanel p1 = new JPanel();
            JButton commit = new JButton("Commit");
            JLabel l1 = new JLabel("Select Product:");
            JLabel l2 = new JLabel("Feild to change:");
            JLabel l3 = new JLabel("New Value:");
            JTextField tf = new JTextField(16);
            JComboBox products = new JComboBox(prod);
            JComboBox columns = new JComboBox(cols);
            p1.setLayout(new GridLayout(0, 2));
            p1.add(l1);
            p1.add(products);
            p1.add(l2);
            p1.add(columns);
            p1.add(l3);
            p1.add(tf);
            
            nf.add(BorderLayout.NORTH, p1); 
            nf.add(BorderLayout.SOUTH, commit);  
            
        
            commit.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String x = products.getSelectedItem().toString();
                String y = columns.getSelectedItem().toString();
                String z = tf.getText();
                
                String query = "Update inventory Set "+y+" = '"+z+"' "
                        + "where prodName = '"+x+"';";
                System.out.println(query);
                conn.executeQuery(query);
                nf.hide();
            }            
        });
        }
    });        
        
        removeItem.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                
                JFrame nf = new JFrame("Remove Item");
                nf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                nf.setSize(400, 400);
                nf.setVisible(true);
                String[] prod = conn.getProd();
                JPanel p1 = new JPanel();
                JPanel p2 = new JPanel();
                JLabel txt = new JLabel("Item to Remove:");
                JComboBox items = new JComboBox(prod);
                JButton confirm = new JButton("Confirm");
                p1.setLayout(new GridLayout(0, 2));
                p1.add(txt);
                p1.add(items);
                p2.add(confirm);
                nf.add(BorderLayout.NORTH, p1); 
                nf.add(BorderLayout.CENTER, p2);                       
                confirm.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                
                    String x = items.getSelectedItem().toString();
                                    
                    String query = "Delete from inventory where prodName = '"+x+"';";
                    System.out.println(query);
                    conn.executeQuery(query);
                    nf.hide();
                    }            
                });                        
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

