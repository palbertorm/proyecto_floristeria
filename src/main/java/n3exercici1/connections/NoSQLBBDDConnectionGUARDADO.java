package n3exercici1.connections;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;

import static n2exercici1.services.InputData.askString;

public class NoSQLBBDDConnectionGUARDADO {

    private static String user;
    private static String password;
    private static boolean initConnection = false;

    private static void checkDataConnection(){
        if (!initConnection) askData();
    }
    private static void askData(){
        user = askString("Introduce your username: ");
        password = askString("Introduce your password: ");
        initConnection = true;
    }
    public static MongoClient getConnection(String database) throws NullPointerException {
        checkDataConnection();
        MongoClient mongoClient;
        try {
            System.out.println("CONECTANDO CON LA BASE DE DATOS");
            mongoClient = createMongoClient(createCredential(database));
            System.out.println("CONECTADO CON LA BASE DE DATOS");
        } catch (MongoException e){
            System.out.println("Error connecting to MongoDB: " + e.getMessage());
            mongoClient = null;
        }
        return mongoClient;
    }
    private static MongoCredential createCredential(String database){
        return MongoCredential.createCredential(user, database, password.toCharArray());
    }
    private static MongoClient createMongoClient(MongoCredential credential){
        return MongoClients.create(MongoClientSettings.builder()
                        .applyToClusterSettings(builder -> builder.hosts(List.of(new ServerAddress("localhost", 27017))))
                        .credential(credential)
                        .codecRegistry(getCodecRegistry())
                        .build());
    }
    private static CodecRegistry getCodecRegistry(){
        return org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
    }
}
