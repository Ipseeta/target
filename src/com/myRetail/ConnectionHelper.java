package com.myRetail;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class ConnectionHelper {
	
	public static MongoClient dbConnect(){
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/myRetail?maxPoolSize=10" ));
		return mongoClient;
	}
	
	public static void closeDB(MongoClient mongoClient) {
		mongoClient.close();
	}

}
