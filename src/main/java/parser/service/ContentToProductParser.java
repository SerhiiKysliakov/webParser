package parser.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import parser.model.Product;


public class ContentToProductParser {
    public Product[] contentToProductParser(String content) {
        JsonObject pageContent = new JsonParser().parse(content).getAsJsonObject();
        JsonArray entitiesArray = pageContent.get("entities").getAsJsonArray();
        Product[] products = new Product[entitiesArray.size()];
        int index = 0;
        for (JsonElement element : entitiesArray) {
            JsonObject entity = element.getAsJsonObject();
            JsonObject attributes = entity.get("attributes").getAsJsonObject();
            String productName = attributes.get("name")
                    .getAsJsonObject()
                    .get("values")
                    .getAsJsonObject()
                    .get("label")
                    .getAsString();
            String brand = attributes
                    .get("brand")
                    .getAsJsonObject()
                    .get("values")
                    .getAsJsonObject()
                    .get("label")
                    .getAsString();
            float price = entity.get("priceRange")
                    .getAsJsonObject()
                    .get("min")
                    .getAsJsonObject().get("withTax").getAsFloat() / 100;
            String articleID = entity.get("id").getAsString();
            String color = attributes
                    .get("colorDetail")
                    .getAsJsonObject()
                    .get("values")
                    .getAsJsonArray()
                    .get(0)
                    .getAsJsonObject()
                    .get("label")
                    .getAsString()
                    .toLowerCase();
            products[index++] = new Product(productName, brand, color, String.valueOf(price), articleID);
        }
        return products;
    }
}
