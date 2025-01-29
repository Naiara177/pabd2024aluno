/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pabd2024;

import java.sql.*;

/**
 *
 * @author 20221074010043
 */
public class PABD2024 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here

        

        Connection con = null;
        
        try {
            con = new ConnectionFactory(). getConnection();
            System.out.println("connection ok!");
            
              // ********* CRUD ***********
              // CREAT - Inserir dados
              String sql = "insert into customer"
                      + " (store_id, first_name, last_name, email, address_id, active)"
                      + " values"
                      + " (?, ?, ?, ?, ?, ?)";       
              PreparedStatement st = con.prepareStatement(sql);
              
              st.setInt(1, 1);
              st.setString(2, "Guilherme");
              st.setString(3, "Santos");
              st.setString(4, "guilherme.leal@ifrn.edu.br");
              st.setInt(5, 1);
              st.setInt(6, 1);
              
              
              st.execute();
              st.close();
              System.out.println("Customer added!");
              
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            con.close();
            System.out.println("connection closed!");
        }
        
        System.out.println("Conexão OK!");

      
        Statement st = con.createStatement();

        // READ - Leitura de dados 
        String query = "select * from customer limit 5";

        ResultSet rs = st.executeQuery(query);

        ResultSetMetaData md = rs.getMetaData();
        int col = md.getColumnCount();
        System.out.println("Table:"+md.getTableName(1));
        for (int i = 1; i <= col; i++) {
            System.out.print(md.getColumnName(i) + "\t");
        }
        System.out.println("");

        while (rs.next()) {
            for (int i = 1; i <= col; i++) {
                System.out.print(rs.getString(i) + "\t");
            }
             con.close();
            System.out.println("Conecction closed!");
        }

        con.close();
    }

}

package teste;

import java.sql.*;

public class Teste {

    public static void main(String[] args) throws SQLException {
        
        String url = "jdbc:mysql://localhost/sakila";
        String usr = "root";
        String pwd = "1234";
        
        Connection con = null;
        
        //CRUD - Create, Read, Update, Delete
        
        //Try catch (tenta achar erros, estabelecendo uma conexão,
        //Se ela der erro esse erro vai ser imprimido,
        //mas de toda forma a conexao vai ser encerrada com o con.close,
        //dando erro ou não)
        
        try{ 
            con = DriverManager.getConnection(url, usr, pwd);
            
            //inserir dados update ou deletar
            String sql = "insert into customer (store_id, first_name, last_name, email, address_id, active)"
                    + "values(?, ?, ?, ?, ?, ?)";
            //adicionar dados
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, 2);
            pst.setString(2, "Iasmim");
            pst.setString(3, "Vitoria");
            pst.setString(4, "Melo.iasmim@");
            pst.setInt(5, 24);
            pst.setInt(6, 1);
            
            pst.execute();
            pst.close();   
            
            //Read - Consultar os dados
            String query = "select * from customer"
                    + " order by customer_id desc"
                    + " limit 10 ";
            Statement st = con.createStatement();
            
            //percorrer linhas
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData md = rs.getMetaData();
            int col = md.getColumnCount();
            for (int i = 1; i <=col; i++) {
                System.out.print(md.getColumnName(i) + "\t");
            } System.out.println("");
            
            //percorre colunas
            while (rs.next()){
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i)+ "\t");
                    
                } System.out.println("");
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            con.close();
        }
        
        }
        
    }