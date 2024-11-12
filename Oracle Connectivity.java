
import java.sql.*;
import java.util.Scanner;

public class OracleDemo {

    public static void main(String[] args) throws Exception {
        int n, sno;
        String name, telephone, gender;
        Scanner in = new Scanner(System.in);

        // 1. Connect to Oracle Database
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "2004");
        
        // 2. Create Statement and PreparedStatement
        Statement stmt = con.createStatement();
        PreparedStatement pstm = con.prepareStatement("INSERT INTO Personal (sno, name, telephone, gender) VALUES (?, ?, ?, ?)");
        PreparedStatement pstm1 = con.prepareStatement("DELETE FROM Personal WHERE sno = ?");

        // 3. Insert Records
        System.out.print("Enter the number of records you want to insert: ");
        n = in.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("\nData " + (i + 1) + "\nEnter Sno: ");
            sno = in.nextInt();
            pstm.setInt(1, sno);
            
            System.out.print("Enter Name: ");
            name = in.next();
            pstm.setString(2, name);
            
            System.out.print("Enter Telephone: ");
            telephone = in.next();
            pstm.setString(3, telephone);
            
            System.out.print("Enter Gender: ");
            gender = in.next();
            pstm.setString(4, gender);
            
            pstm.executeUpdate();
        }

        // 4. Display Records After Insertion
        ResultSet rs = stmt.executeQuery("SELECT * FROM Personal");
        System.out.println("\nAfter Insertion");
        System.out.println("Sno\tName\tTelephone\tGender");
        while (rs.next()) {
            System.out.println(rs.getInt("sno") + "\t" + rs.getString("name") + "\t" + rs.getString("telephone") + "\t" + rs.getString("gender"));
        }

        // 5. Delete Records
        System.out.print("Enter the number of records you want to delete: ");
        n = in.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("\nData " + (i + 1) + "\nEnter Sno: ");
            sno = in.nextInt();
            pstm1.setInt(1, sno);
            pstm1.executeUpdate();
        }

        // 6. Display Records After Deletion
        ResultSet rs1 = stmt.executeQuery("SELECT * FROM Personal");
        System.out.println("\nAfter Deletion");
        System.out.println("Sno\tName\tTelephone\tGender");
        while (rs1.next()) {
            System.out.println(rs1.getInt("sno") + "\t" + rs1.getString("name") + "\t" + rs1.getString("telephone") + "\t" + rs1.getString("gender"));
        }

        // 7. Close Resources
        rs.close();
        rs1.close();
        pstm.close();
        pstm1.close();
        stmt.close();
        con.close();
 }
}  