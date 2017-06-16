package com.myRetail;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MyRetailDAO {
	private static String DB = "myRetail";
	private static String COLLECTION = "price";

	public Product findById(int id) {
		//15117729, 16483589, 16696652, 16752456, 15643793) 
		for(Product product : createDummyProducts()){
			if(id == product.getId()){
				return product;
			}
		}
		return null;
	}

	public List<Product> createDummyProducts(){
		MongoClient mongoClient = ConnectionHelper.dbConnect();
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(DB);
		DBCollection collection = db.getCollection(COLLECTION);
		List<Product> list = new ArrayList<>();

		Product product = new Product();
		product.setId(15117729);
		product.setName("The Big Lebowski (Blu-ray) (Widescreen)");
		DBCursor newProductCursor = collection.find(new BasicDBObject().append("_id", product.getId()));
		Price price = getPrice(newProductCursor);
		product.setCurrent_price(price);
		list.add(product);

		product = new Product();
		product.setId(16483589);
		product.setName("Dazed and Confused");
		newProductCursor = collection.find(new BasicDBObject().append("_id", product.getId()));
		price = getPrice(newProductCursor);
		product.setCurrent_price(price);
		list.add(product);

		ConnectionHelper.closeDB(mongoClient);

		return list;

	}


	private Price getPrice(DBCursor cursor){
		List<Price> result = new ArrayList<Price>();
		while (cursor.hasNext()) {
			Price product = new Price();
			DBObject obj = new BasicDBObject();
			obj = cursor.next();
			product.set_id(obj.get("_id") == null ? 0 : Integer.parseInt(obj.get("_id").toString()));
			product.setValue(obj.get("value") == null ? 0 : Double.parseDouble(obj.get("value").toString()));
			product.setCurrency_code(obj.get("currency_code") == null ? "" : obj.get("currency_code").toString());
			result.add(product);
		}
		return result.get(0);
	}

	public List<Price> createDummyPriceData() {
		MongoClient mongoClient = ConnectionHelper.dbConnect();
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(DB);
		DBCollection collection = db.getCollection(COLLECTION);
		BasicDBObject document = new BasicDBObject();

		document.put("_id", 15117729);
		document.put("value", 13.49);
		document.put("currency_code", "USD");
		collection.insert(document);

		document.put("_id", 16483589);
		document.put("value", 13.49);
		document.put("currency_code", "USD");
		collection.insert(document);

		document.put("_id", 16696652);
		document.put("value", 13.49);
		document.put("currency_code", "USD");
		collection.insert(document);

		document.put("_id", 16752456);
		document.put("value", 13.49);
		document.put("currency_code", "USD");
		collection.insert(document);

		document.put("_id", 15643793);
		document.put("value", 13.49);
		document.put("currency_code", "USD");
		collection.insert(document);

		document.put("_id", 13860428);
		document.put("value", 13.49);
		document.put("currency_code", "USD");
		collection.insert(document);

		ConnectionHelper.closeDB(mongoClient);
		return null;
	}

	public String findNameById(int id) {
		for(Product product : createDummyProducts()){
			if(id == product.getId()){
				return product.getName();
			}
		}
		return "";
	}

	public Product update(int id,Product product) {
		MongoClient mongoClient = ConnectionHelper.dbConnect();
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(DB);
		DBCollection collection = db.getCollection(COLLECTION);
		BasicDBObject updateDocument = new BasicDBObject();
		if(id == product.getId()){
			Price price = product.getCurrent_price();
			updateDocument.put("value", price.getValue());
			updateDocument.put("currency_code", price.getCurrency_code());
			BasicDBObject searchQuery = new BasicDBObject().append("_id", product.getId());
			collection.update(searchQuery, updateDocument);
			DBCursor newProductCursor = collection.find(new BasicDBObject().append("_id", product.getId()));
			price = getPrice(newProductCursor);
			product.setCurrent_price(price);
		}
		ConnectionHelper.closeDB(mongoClient);
		return product;
	}

}
