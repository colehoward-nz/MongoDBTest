package me.cole.mongodbtest;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class MongoDBTest extends JavaPlugin {
    private MongoCollection<Document> collection;
    private HashMap<UUID,PlayerData> playerDataHashMap;

    @Override
    public void onEnable() {
        this.playerDataHashMap = new HashMap<>();
        this.getServer().getPluginManager().registerEvents(this, this);

        String uri = "mongodb://Admin:admin@mongodb-shard-00-00-altt9.mongodb.net:27017,mongodb-shard-00-01-altt9.mongodb.net:27017,mongodb-shard-00-02-altt9.mongodb.net:27017/admin?replicaSet=MongoDB-shard-0&ssl=true";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("MongoDB");
        collection = mongoDatabase.getCollection("Minecraft");

        this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[MONGODB] Database Connected");
    }

    @Override
    public void onDisable() {

    }
}
