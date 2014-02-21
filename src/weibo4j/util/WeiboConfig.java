package weibo4j.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

public class WeiboConfig {
	private static String filePath = "src/config.properties";
	public WeiboConfig(){}
	private static Properties props = new Properties(); 
	static{
//		try {
//			//props.load(new FileInputStream(filePath));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	/**
	 	client_ID=150214841
		client_SERCRET =f4eb5fefada5cc5d74b05668a357abc4
		redirect_URI=http://www.bizlaba.com/CRM/mainframe.jsp
		baseURL=https://api.weibo.com/2/
		accessTokenURL=https://api.weibo.com/oauth2/access_token
		authorizeURL=https://api.weibo.com/oauth2/authorize
	 */
	public static String getValue(String key){
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put("client_ID", "595918520");
		ht.put("client_SERCRET", "c1fc8d4df66f920cf999ff310942c30a");
		ht.put("redirect_URI", "http://labacrm.com/CRM/sinaauthcallback.jsp");
		ht.put("baseURL", "https://api.weibo.com/2/");
		ht.put("accessTokenURL", "https://api.weibo.com/oauth2/access_token");
		ht.put("authorizeURL", "https://api.weibo.com/oauth2/authorize");
		return ht.get(key);
		//return props.getProperty(key);
	}

    public static void updateProperties(String key,String value) {    
            props.setProperty(key, value); 
    } 
}
