package pl.gazda.model;
import java.sql.*;

public class Database {

    private static Connection con;

    public void connectToDatabase() {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/telebook?useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String username = "root";
        final String password = "";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Error");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createContactFromDatabase() {
        try {
            PreparedStatement add = con.prepareStatement("SELECT * FROM contacts");
            ResultSet resultSet = add.executeQuery();
            while (resultSet.next()) {
                Integer id = Integer.valueOf(resultSet.getString("id_contact"));
                String name = resultSet.getString("name");
                String tel = resultSet.getString("tel_number");
                TeleBook.addNewContactToMap(new Contact(id, name, tel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createContactToDatabase(String name, String number) throws SQLException, IllegalArgumentException, NullPointerException {
        String[] data = name.split(" ");
        if (data[0] == null || data[1] == null || number == null) {
            throw new NullPointerException("Podana wartość nie może być null");
        }
        if (data[0].isEmpty() || data[1].isEmpty() || number.isEmpty()) {
            throw new IllegalArgumentException("Podana wartość nie może być pusta");
        }
        Integer integer = Integer.valueOf(number);
        int length = number.length();
        if(length != 9){
            throw new IllegalArgumentException("Numer ma 9 cyfr !");
        }
        PreparedStatement add = con.prepareStatement("INSERT INTO contacts VALUES(NULL,'" + name + "','" + number + "');");
        add.execute();
    }

    public static void deleteContactFromDatabase(String name) throws SQLException {
        String [] data = name.split(" ");
        if(data[0] == null || data[1] == null){
            throw new NullPointerException("Podana wartość nie może być null");
        }
        if(data[0].isEmpty() || data[1].isEmpty()){
            throw new IllegalArgumentException("Podana wartość nie może być pusta");
        }
        PreparedStatement show = con.prepareStatement("SELECT * FROM contacts WHERE name = '" + name + "';");
        if(show.execute()){
            PreparedStatement del = con.prepareStatement("DELETE FROM contacts WHERE name = '" + name + "';");
            del.execute();
        }
        else {
            throw new IllegalArgumentException("Taki użytkownik nie istnieje !");
        }

    }
}
