package ro.ase.acs.sql;

public class SQL {
    static String tabel = "employee";

    public static void start() {
        try {

            DataBaseSQL dataBaseSQL = new DataBaseSQL();
            dataBaseSQL.createTable(tabel);
            dataBaseSQL.insertDataWithSQLInsert(1, "Popescu Ion", "Bucharest", 4000.0);
            dataBaseSQL.insertDataWithPreparedStatement(2, "Ionescu Vasile", "Brasov", 4500.0);
            dataBaseSQL.readData(tabel);
            dataBaseSQL.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}