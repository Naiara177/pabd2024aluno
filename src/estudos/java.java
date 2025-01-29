/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estudos;

/**
 *
 * @author 20221074010043
 */
public class java {
     
    
    

public class Teste {

    public static void main(String[] args) throws SQLException {
        
        String url = "jdbc:mysql://localhost/sakila";
        String usr = "root";
        String pwd = "1234";
        
        Connection con = null;
        
        try { 
            // Establish connection
            con = DriverManager.getConnection(url, usr, pwd);
            
            // --- INSERT (Create) --- 
            String insertSQL = "INSERT INTO customer (store_id, first_name, last_name, email, address_id, active)"
                    + " VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement pstInsert = con.prepareStatement(insertSQL);
            pstInsert.setInt(1, 2);
            pstInsert.setString(2, "Iasmim");
            pstInsert.setString(3, "Vitoria");
            pstInsert.setString(4, "Melo.iasmim@");
            pstInsert.setInt(5, 24);
            pstInsert.setInt(6, 1);
            pstInsert.execute();
            pstInsert.close();
            System.out.println("Data inserted successfully!");

            // --- UPDATE (Update) --- 
            String updateSQL = "UPDATE customer SET email = ? WHERE customer_id = ?";
            PreparedStatement pstUpdate = con.prepareStatement(updateSQL);
            pstUpdate.setString(1, "new.email@example.com");
            pstUpdate.setInt(2, 1); // Assuming customer_id is 1
            int rowsUpdated = pstUpdate.executeUpdate();
            pstUpdate.close();
            if (rowsUpdated > 0) {
                System.out.println("Customer updated successfully!");
            } else {
                System.out.println("No customer found with that ID.");
            }
            
            // --- DELETE (Delete) --- 
            String deleteSQL = "DELETE FROM customer WHERE customer_id = ?";
            PreparedStatement pstDelete = con.prepareStatement(deleteSQL);
            pstDelete.setInt(1, 1); // Assuming customer_id is 1
            int rowsDeleted = pstDelete.executeUpdate();
            pstDelete.close();
            if (rowsDeleted > 0) {
                System.out.println("Customer deleted successfully!");
            } else {
                System.out.println("No customer found with that ID to delete.");
            }
            
            // --- READ (Select) --- 
            String query = "SELECT * FROM customer ORDER BY customer_id DESC LIMIT 10";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData md = rs.getMetaData();
            int col = md.getColumnCount();
            
            // Print column names
            for (int i = 1; i <= col; i++) {
                System.out.print(md.getColumnName(i) + "\t");
            }
            System.out.println();
            
            // Print row data
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
            st.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
}
}
