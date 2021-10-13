import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Main {
	public static void main(String[] args) {
		Main awsLambda = new Main();
		System.out.println("Testing [AWS] API Gateway - Lambda - DynamoDB");
		System.out.println("creating new product");
		HttpResponse<String> response = awsLambda.createProduct("1212", "10000");
		System.out.println("response from api gateway: ");
		System.out.println("status code: " + response.getStatus());
		System.out.println("body: " + response.getBody());

		response = awsLambda.updateProduct("1212", "10000", "clean code book", "https://cdn.dribbble.com/users/71691/screenshots/6283648/cleancode.pnga");
		System.out.println("response from api gateway: ");
		System.out.println("status code: " + response.getStatus());
		System.out.println("body: " + response.getBody());
	}

	public HttpResponse<String> updateProduct(String id, String price, String productName, String pictureUrl){
		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = null;
		try {
			response = Unirest.post("https://lwkgob2hfd.execute-api.eu-north-1.amazonaws.com/default/lambda1")
					.header("Content-Type", "application/json")
					.body("{\n  \"methodType\":\"update\",\n  " +
							      "\"id\": \""+id+"\",\n  " +
							      "\"price\": \""+price+"\",\n  " +
							      "\"productName\":\""+productName+"\",\n  " +
							      "\"pictureUrl\":\""+pictureUrl+"\"\n}")
					.asString();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return response;
	}

	public HttpResponse<String> createProduct(String id, String price){
		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = null;
		try {
			response = Unirest.post("https://lwkgob2hfd.execute-api.eu-north-1.amazonaws.com/default/lambda1")
					.header("Content-Type", "application/json")
					.body("{\n  \"methodType\":\"create\",\n  \"id\": \""+id+"\",\n  \"price\": \""+price+"\"}")
					.asString();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return response;
	}
}
