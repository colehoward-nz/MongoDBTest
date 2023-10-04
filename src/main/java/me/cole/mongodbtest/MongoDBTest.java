package me.cole.mongodbtest;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public final class MongoDBTest extends JavaPlugin {

    @Override
    public void onEnable() {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://poo:Uncrackable!DWLn4CTVLWXeI5a0@kitpvpcluster.avyl0on.mongodb.net/?retryWrites=true&w=majority&appName=AtlasApp");
        MongoDatabase database = mongoClient.getDatabase("sample_weatherdata");
        MongoCollection<Document> collection = database.getCollection("data");

        collection.find().forEach((Consumer<Document>) document -> {
            Object test = document.get("_id");
            System.out.println(test);
        });

    }

    @Override
    public void onDisable() {

    }
}
