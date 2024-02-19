package n3exercici1.connections;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class NoSQLBBDDConnection {

    public static MongoClientSettings getSettings(){
        return MongoClientSettings.builder().applyConnectionString(new ConnectionString("mongodb://localhost:27017")).build();
    }

    public static MongoClient getClient() throws NullPointerException{
        return MongoClients.create(getSettings());
    }
    public static MongoDatabase getDataBase(MongoClient client, String dataBaseName){
        return client.getDatabase(dataBaseName);
    }
    public static MongoCollection<Document> getConnection(String dataBaseName, String collection) {
        return getDataBase(getClient(), dataBaseName).getCollection(collection);
    }

}
