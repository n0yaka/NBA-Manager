package nba.ds.asgmnt;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.util.*;
/**
 *
 * @author radin
 */
public class Contract {
    String url = "jdbc:mysql://localhost:3306/nba";
    String user = "root";
    String password = "afiqahnajla21";
    Queue<String> contractQ = new LinkedList<>();
    
    Connection connect = null;
    
    public Contract() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    } 
    
    public void ContractExtension(int id){
        try{
            String contractSql = "Select name from firstteam where id = ?";
            PreparedStatement contractStmt = connect.prepareStatement(contractSql);
            contractStmt.setInt(1, id);
            ResultSet rs = contractStmt.executeQuery();
            
            if(rs.next()){
                String name = rs.getString("name");
                contractQ.offer(name);
                System.out.println(name+" add to queue");
            }
            
            contractStmt.close();
            rs.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void ExtendContract(){
        if(!contractQ.isEmpty()){
            System.out.println("Player : "+contractQ.peek());
            contractQ.poll();
        }
    }
    
    public void ContractQueueList(){
        int count = 1;
        if(contractQ.isEmpty()){
            System.out.println("No one need to extend contract");
        }
        else{
            for (String player : contractQ) {
                System.out.println(count +": "+player);
                count++;
            }
        }
    }
}
