package vn.credit.home.helper.vnpost;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class GeneralUtil {
	public static Date convertDate(String text){
	    return convertDate(text, "dd/MM/yyyy-hh:mm:ss");
	}
	
	public static Date convertDate(String text, String pattern){
	    DateFormat df = new SimpleDateFormat(pattern); 
	    Date startDate;
	    try {
	        startDate = df.parse(text);
	        return startDate;
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static String toString(Date date, String pattern){
	    DateFormat df = new SimpleDateFormat(pattern); 
	    String startDate;
	    try {
	        startDate = df.format(date);
	        return startDate;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	public static String toString(Date date){
		return toString(date, "yyyy/MM/dd");
	}
	

	public static Properties readConfig(){
		Properties config = new Properties();
		FileInputStream input;
		try {
			input = new FileInputStream(System.getProperty("user.home") + "/config.properties");
			config.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return config;
	}
}
