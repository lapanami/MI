//package vn.credit.home.helper.vnpost;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//
//
//public class VNPostTrace{
//	
//	private final String USER_AGENT = "Mozilla/5.0";
//	private DefaultHttpClient httpclient;
//	
//	
//	public void StartToTrace() {
//		
//	}
//	
//	private void login() throws Exception{
//		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
//
//		//Properties properties = GeneralUtil.readConfig();
//		//String username = properties.getProperty("config.account.config.khl.username");
//		//String password = properties.getProperty("config.account.config.khl.password");
//		
//		String username = "70001k66001724000";
//		String password = "ppfhcm";
//		
//		System.out.println(username + " - " + password);
//		
//		urlParameters.add(new BasicNameValuePair("ctl00$MainContent$txtUser", username));		
//		urlParameters.add(new BasicNameValuePair("ctl00$MainContent$txtPassword", password));
//		urlParameters.add(new BasicNameValuePair("ctl00$MainContent$btnLogin", "Đăng nhập"));
//		//urlParameters.add(new BasicNameValuePair("__VIEWSTATE", "/wEPDwULLTE0MjYwNjQ1NDEPZBYCZg9kFgICAw9kFgICBQ8PFgIeBFRleHQFUFhpbiBt4budaSA8Zm9udCBjb2xvcj0ncmVkJz48Yj48YSBocmVmPSdMb2dpbi5hc3B4Jz7EkMSDbmcgbmjhuq1wPC9hPjwvYj48L2ZvbnQ+ZGRk6Z5DSx+XoMH0kRfI+qOJa/UT8QU="));
//		//urlParameters.add(new BasicNameValuePair("__EVENTVALIDATION", "/wEWBALBtvCqAgKqkqLyBwKOsPfSCQKRnIq9DwCRhmbaoKqePMJFmiplsStGsH6Q"));
//		
//		//TEST THU DATE: 27/10/2014
//		//Hungnvv
//		String result = sendGet("https://khl.vnpost.vn/Customer.aspx?act=BG&id=");
//		String viewstage = "";
//		String viewvalidation = "";
//		System.out.println(result);
//		System.out.println("1233");
//		
//		result = result.replaceAll("\"", "@");
//		System.out.println(result);
//				
//		//get viewstage
//		result = result.substring(result.indexOf("id=@__VIEWSTATE@"));
//		viewstage = result.substring(0,result.indexOf("@ />"));
//		viewstage = viewstage.replaceAll("id=@__VIEWSTATE@ value=@", "");
//		//System.out.println(viewstage);
//
//		
//		//get viewvalidation
//		result = result.substring(result.indexOf("id=@__EVENTVALIDATION@"));
//		viewvalidation = result.substring(0,result.indexOf("@ />"));
//		viewvalidation = viewvalidation.replaceAll("id=@__EVENTVALIDATION@ value=@", "");
//		
//		//System.out.println(viewvalidation);		
//		
//		urlParameters.add(new BasicNameValuePair("__VIEWSTATE", viewstage));
//		urlParameters.add(new BasicNameValuePair("__EVENTVALIDATION", viewvalidation));
//		
//		//End Hungnvv
//		//11/11/2014
//		
//		//END TEST THU
//		sendPost("https://khl.vnpost.vn/Login.aspx", urlParameters);
//		//System.out.println(http.sendPost("http://khl.vnpost.vn/Login.aspx", urlParameters));
//		//System.out.println(username);
//		//System.out.println(password);
//	}
//	
//	public String sendPost(String url, List<NameValuePair> urlParameters) throws ClientProtocolException, IOException{
//		httpclient = getHttpClient();
//		
//		HttpPost post = new HttpPost(url);
//		post.setHeader("User-Agent", USER_AGENT);		
//		post.setEntity(new UrlEncodedFormEntity(urlParameters));
//		
//		HttpResponse response = httpclient.execute(post);
//		
//		int code = response.getStatusLine().getStatusCode();
//		System.out.println(code);
//		if(code == 407){
//			throw new ClientProtocolException("Invalid window acccount. Please check your domain account");
//		}
//		
//		String content = renderResponeToString(response);
//		EntityUtils.consume(response.getEntity());
//		
//		post.abort();
//		
//		return content;
//	}
//
//}
