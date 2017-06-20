package com.myRetail;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.myRetail.exception.GenericException;
import com.myRetail.exception.ProductDeletionException;
import com.myRetail.exception.ProductSearchException;
import com.myRetail.model.Price;
import com.myRetail.model.Product;
import com.myRetail.util.Utility;

public class MyRetailDAO {
	private static String DB = "myRetail";
	private static String COLLECTION = "price";
	ResourceBundle bundle = ResourceBundle.getBundle("retail");

	public Product findById(int id) {
		boolean found = false;
		for(Product product : createDummyProducts()){
			if(id == product.getId()){
				return product;
			}
		}
		if(!found){
			throw new ProductSearchException(bundle.getString("noproduct")+id);
		}
		return null;
	}

	public List<Product> createDummyProducts(){
		MongoClient mongoClient = Utility.dbConnect();
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
		
		product = new Product();
		product.setId(16696652);
		product.setName("Friday");
		newProductCursor = collection.find(new BasicDBObject().append("_id", product.getId()));
		price = getPrice(newProductCursor);
		product.setCurrent_price(price);
		list.add(product);
		
		product = new Product();
		product.setId(15643793);
		product.setName("Half Baked");
		newProductCursor = collection.find(new BasicDBObject().append("_id", product.getId()));
		price = getPrice(newProductCursor);
		product.setCurrent_price(price);
		list.add(product);
		
		product = new Product();
		product.setId(13860428);
		product.setName("Pineapple Express");
		newProductCursor = collection.find(new BasicDBObject().append("_id", product.getId()));
		price = getPrice(newProductCursor);
		product.setCurrent_price(price);
		list.add(product);

		Utility.closeDB(mongoClient);

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

	public String createDummyPriceData() {
		MongoClient mongoClient = Utility.dbConnect();
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

		Utility.closeDB(mongoClient);
		return "Created";
	}

	public String findNameById(int id) {
		boolean found = false;
		for(Product product : createDummyProducts()){
			if(id == product.getId()){
				return product.getName();
			}
		}
		if(!found){
			throw new GenericException(bundle.getString("generalMessage"));
		}
		return "";
	}

	public Product update(int id,Product product) {
		MongoClient mongoClient = Utility.dbConnect();
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(DB);
		DBCollection collection = db.getCollection(COLLECTION);
		BasicDBObject updateDocument = new BasicDBObject();
		if(id == product.getId() && null!=product.getCurrent_price() && 0.0!=product.getCurrent_price().getValue() && ""!=product.getCurrent_price().getCurrency_code()){
			Price price = product.getCurrent_price();
			updateDocument.put("value", price.getValue());
			updateDocument.put("currency_code", price.getCurrency_code());
			BasicDBObject searchQuery = new BasicDBObject().append("_id", product.getId());
			collection.update(searchQuery, updateDocument);
			DBCursor newProductCursor = collection.find(new BasicDBObject().append("_id", product.getId()));
			price = getPrice(newProductCursor);
			product.setCurrent_price(price);
		}else{
			throw new GenericException(bundle.getString("generalMessage"));
		}
		Utility.closeDB(mongoClient);
		return product;
	}

	public String delete(int id) {
		MongoClient mongoClient = Utility.dbConnect();
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(DB);
		BasicDBObject whereQuery = new BasicDBObject()
				.append("_id", id);
		DBObject dbObj = db.getCollection(COLLECTION).findAndRemove(whereQuery);
		if(null == dbObj){
			throw new ProductDeletionException("No such product exists");
		}
		Utility.closeDB(mongoClient);
		return "Deleted Successfully";
	}

}
