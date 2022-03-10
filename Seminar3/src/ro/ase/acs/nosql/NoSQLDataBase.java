package ro.ase.acs.nosql;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class NoSQLDataBase {

    String host = "localhost";
    Integer port = 27017;
    String databaseName = "test";
    String name = "name";
    String address = "address";
    String salary = "salary";

    MongoClient mongoClient;
    MongoDatabase mongoDb;
    MongoCollection<Document> collection = null;

    public NoSQLDataBase() {
        mongoClient = new MongoClient(host, port);
        mongoDb = mongoClient.getDatabase(databaseName);
    }

    public void createTable(String collectionName) {
        try {
            if (mongoDb.getCollection(collectionName) != null) {
                mongoDb.getCollection(collectionName).drop();
            }
            mongoDb.createCollection(collectionName);
            collection = mongoDb.getCollection(collectionName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeClient() {
        mongoClient.close();
    }

    public Document createEmployee(String nume, String adresa, Integer salariu) {
        return new Document().append(name, nume).append(address, adresa).append(salary, salariu);
    }

    public void insertEmployee(String nume, String adresa, Integer salariu) {
        collection.insertOne(createEmployee(nume, adresa, salariu));
    }

    public void afisareColectie() {
        FindIterable<Document> result = collection.find();
        for(Document doc : result) {
            System.out.println(doc);
        }
    }

}