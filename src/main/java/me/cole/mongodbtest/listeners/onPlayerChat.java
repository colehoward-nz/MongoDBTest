package me.cole.mongodbtest.listeners;

import com.mongodb.client.MongoCollection;
import me.cole.mongodbtest.model.PlayerData;
import org.bson.Document;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.UUID;

public class onPlayerChat implements Listener {
    private final MongoCollection<Document> collection;
    private HashMap<UUID,PlayerData> playerDataHashMap;

    public onPlayerChat(MongoCollection<Document> collection) {
        this.collection = collection;
        this.playerDataHashMap = new HashMap<>();
    }

    public String getRank(Player player){
        Document playerDoc = new Document("UUID", player.getUniqueId().toString());
        Document found = collection.find(playerDoc).first();
        if (found != null) {
            return found.getString("RANK").toUpperCase();
        }
        else {
            return "USER";
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        Document playerDoc = new Document("UUID", player.getUniqueId().toString());
        //Document newPlayerDoc = new Document("UUID", player.getUniqueId().toString());
        Document found = collection.find(playerDoc).first();
        String rank = event.getMessage().toUpperCase();

        if (found != null) {
            player.sendMessage("Rank updated to " + rank);

            String foundRank = found.getString("RANK");
            playerDataHashMap.put(player.getUniqueId(), new PlayerData(player.getUniqueId().toString(), foundRank));
            collection.deleteOne(playerDoc);
            playerDoc.append("RANK", rank);
            collection.insertOne(playerDoc);

            //collection.insertOne(newPlayerDoc);
        }
        else {
            player.sendMessage("Profile created\nRank set to " + rank);

            playerDataHashMap.put(player.getUniqueId(), new PlayerData(player.getUniqueId().toString(), rank));
            collection.deleteOne(playerDoc);
            playerDoc.append("RANK", rank);
            collection.insertOne(playerDoc);
        }






        String chatFormat = event.getFormat();
        event.setFormat("[" + getRank(event.getPlayer()) + "] " + chatFormat);
    }
}
