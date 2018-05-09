package vn.credit.home.helper;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import oracle.sql.TIMESTAMP;

public class Test {

	public static void main(String[] args){
//		System.out.print("sadas");
//		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//		HttpUriRequest request = new HttpGet("https://jsonplaceholder.typicode.com/posts/1");
//		request.addHeader(HttpHeaders.ACCEPT, "application/json");
//		try (CloseableHttpResponse httpResponse = httpClient.execute(request)){
//			int statusCode = httpResponse.getStatusLine().getStatusCode();
//			System.out.println("statusCode = " + statusCode);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Date now = Calendar.getInstance().getTime();
//		String uniqueID = UUID.randomUUID().toString();
//		String test = "CN=Minh\\, Lam Pham Nhat,OU=Operation MIS,OU=Operations Division,OU=Phu Nu Building,OU=HQ,OU=Hcnet_Users,DC=hcnet,DC=vn";
//		int end = test.indexOf(",OU");
//		int position = test.indexOf(",OU", end+1);
//		String name = test.substring(3, end).replace("\\", "");
//		String division = test.substring(end + 4, position);
//		System.out.println(division);
//		System.out.println(name);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("Timestamp: " + timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy HH.mm.ss");
        System.out.println("Time: " + sdf.format(timestamp));
        String testDate = "05/01/2018";
        try {
        	SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
        	Date date = sdf1.parse(testDate);
        	System.out.println("date: " + date);
        	sdf1.applyPattern("yyyy-MM-dd"); 
        	String newString = sdf1.format(date);
        	System.out.println(newString);
        	Date date1 = sdf1.parse(newString);
        	System.out.println(date1);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        //ApachePOIExcelWrite writer = new ApachePOIExcelWrite();
        //ApachePOIExcelWrite.ApachePOIExcelWrite("08.05.18", list, "test");
	}
}
