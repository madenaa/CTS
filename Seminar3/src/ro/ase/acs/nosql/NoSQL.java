package ro.ase.acs.nosql;

public class NoSQL {

	static String collectionName = "employees";

	public static void start() {
		NoSQLDataBase noSQLDataBase = new NoSQLDataBase();

		noSQLDataBase.createTable(collectionName);

		noSQLDataBase.insertEmployee("Popescu Ion", "Bucharest", 4000);
		noSQLDataBase.insertEmployee("Ionescu Vasile", "", 4500);

		noSQLDataBase.afisareColectie();

		noSQLDataBase.closeClient();

	}



}