package com.myRetail.util;

import java.util.ResourceBundle;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.myRetail.exception.GenericException;
/**
 * A helper class for myRetail project
 * @author ipseeta
 *
 */
public class Utility {
	
	public static MongoClient dbConnect(){
		ResourceBundle bundle = ResourceBundle.getBundle("retail");
		MongoClient mongoClient = null;
		try{
			mongoClient = new MongoClient(new MongoClientURI(bundle.getString("mongo.url")));
		}catch(Exception e){
			throw new GenericException(bundle.getString("connectionFailure"));
		}
		return mongoClient;
	}
	
	public static void closeDB(MongoClient mongoClient) {
		mongoClient.close();
	}

}
