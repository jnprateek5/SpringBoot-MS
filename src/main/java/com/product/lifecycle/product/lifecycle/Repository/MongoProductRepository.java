package com.product.lifecycle.product.lifecycle.Repository;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.product.lifecycle.product.lifecycle.Model.Product;
import jakarta.annotation.PostConstruct;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
public class MongoProductRepository {

    private MongoCollection<Product> collection;

    @Value("${mongoDb.url}")
    private String loginUrl;

    private MongoClientSettings init(){


        ConnectionString connectionString = new ConnectionString(loginUrl);
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                pojoCodecRegistry);

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .serverApi(serverApi)
                .build();

        return clientSettings;

    }


    public String insertRecord(Product product) {

        MongoClientSettings clientSettings = init();
        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
            try {
                MongoDatabase database = mongoClient.getDatabase("FirstApp");
                collection = database.getCollection("Products", Product.class);
                String id = collection.insertOne(product).getInsertedId().toString();
                return "Insert Success - Record id : " + id;
            }
            catch (MongoException e) {
                e.printStackTrace();
                return "insert fail due to " + e.getLocalizedMessage();
            }
            finally {
                mongoClient.close();
            }
        }
    }
    public Product getRecord(String productId){
        MongoClientSettings clientSettings  = init();
        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
            try {
                MongoDatabase database = mongoClient.getDatabase("FirstApp");
                collection = database.getCollection("Products", Product.class);
                Product product = collection.find(Filters.eq("product_id", productId)).first();
                return product;
            }
            catch (MongoException ex){
                ex.printStackTrace();
            }
            finally {
                mongoClient.close();
            }
        }
        return null;

    }
}

