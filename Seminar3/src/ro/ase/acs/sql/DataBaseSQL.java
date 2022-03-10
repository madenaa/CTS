package ro.ase.acs.sql;

import java.sql.*;

public class DataBaseSQL {

    String sqlDrop = "DROP TABLE IF EXISTS ";
    String sqlCreate = "CREATE TABLE ";
    String sqlCreateValues = "(id INTEGER PRIMARY KEY, name TEXT, address TEXT, salary REAL)";
    String sqlInsertWithParams = "INSERT INTO employees VALUES (?,?,?,?)";
    String sqlSelect = "SELECT * FROM ";
    String variable1 = "id";
    String variable2 = "name";
    String variable3 = "address";
    String variable4 = "salary";
    String sqlInsert = "INSERT INTO employees VALUES(";

    Connection connection;

    public DataBaseSQL() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        connection.setAutoCommit(false);
    }

    void createTable(String table) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlDrop + table);
        statement.executeUpdate(sqlCreate + table + sqlCreateValues);
        statement.close();
        connection.commit();
    }

    void insertDataWithSQLInsert(Integer id, String nume, String adresa, Double salariu) throws SQLException {
        String Insertsql = sqlInsert + id.toString() + "," + nume + "," + adresa + "," + salariu.toString() + ")";
        Statement statement = connection.createStatement();
        statement.executeUpdate(Insertsql);
        statement.close();

        connection.commit();
    }

    void insertDataWithPreparedStatement(Integer id, String nume, String adresa, Double salariu) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertWithParams);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, nume);
        preparedStatement.setString(3, adresa);
        preparedStatement.setDouble(4, salariu);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        connection.commit();
    }

    void closeConnection() throws SQLException {
        connection.close();
    }

    void readData(String tabel) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sqlSelect + tabel);
        while(rs.next()) {
            int id = rs.getInt(variable1);
            System.out.println("id: " + id);
            String name = rs.getString(variable2);
            System.out.println("name: " + name);
            String address = rs.getString(variable3);
            System.out.println("address: " + address);
            double salary = rs.getDouble(variable4);
            System.out.println("salary: " + salary);
        }
        rs.close();
        statement.close();
    }
}