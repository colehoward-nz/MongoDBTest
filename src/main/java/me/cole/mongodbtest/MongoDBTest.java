package me.cole.mongodbtest;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.cole.mongodbtest.listeners.onPlayerChat;
import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MongoDBTest extends JavaPlugin {
    private MongoCollection<Document> collection;
    @Override
    public void onEnable() {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://Admin:admin@kitpvpcluster.q4efdgq.mongodb.net/?retryWrites=true&w=majority&appName=AtlasApp");
        MongoDatabase database = mongoClient.getDatabase("players");
        collection = database.getCollection("data");

        this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "MongoDBTest connected successfully");
        getServer().getPluginManager().registerEvents(new onPlayerChat(collection), this);
    }

    @Override
    public void onDisable() {
    }
}
