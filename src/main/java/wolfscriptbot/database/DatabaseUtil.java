package wolfscriptbot.database;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import wolfscriptbot.main.Main;

public class DatabaseUtil {

    private Main main;

    public DatabaseUtil(Main main) {
        this.main = main;
    }

    public Document getDocument(String jsonKey, String name, String collectionName) {
        Bson filter = Filters.eq(jsonKey, name);
        Document document = main.getMongoDatabase().getCollection(collectionName).find(filter)
                .first();
        return document;
    }

    public boolean documentExists(String filterByKey, String name, String collectionName) {
        Bson filter = Filters.eq(filterByKey, name);
        Document document = main.getMongoDatabase().getCollection(collectionName).find(filter)
                .first();
        return document != null;
    }

    public DatabaseUtil addObjectToDocument(String documentKey, String documentValue, String key, String value, String collectionName) {
        main.getMongoDatabase().getCollection(collectionName).updateOne(
                Filters.eq(documentKey, documentValue), Updates.combine(Updates.set(key, value)));
        return this;
    }

    public DatabaseUtil changeObjectFromDocument(String documentKey, String documentValue, String key, Object value, String collectionName) {
        main.getMongoDatabase().getCollection(collectionName).updateOne(
                Filters.eq(documentKey, documentValue), Updates.combine(Updates.set(key, value)));
        return this;
    }

    public DatabaseUtil removeObjectFromDocument(String documentKey, String documentValue, String key, String collectionName) {
        main.getMongoDatabase().getCollection(collectionName)
                .updateOne(Filters.eq(documentKey, documentValue), Updates.combine(Updates.unset(key)));
        return this;
    }

}