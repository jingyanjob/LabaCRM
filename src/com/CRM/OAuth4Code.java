package com.CRM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import weibo4j.Oauth;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

public class OAuth4Code {
	public static void main(String[] str){
		try{
			Oauth oauth = new Oauth();
			BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
			System.out.println(oauth.authorize("code"));
			System.out.print("Hit enter when it's done.[Enter]:");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String code = br.readLine();
			System.out.print(code);
			System.out.println(oauth.getAccessTokenByCode(code));
		} catch (WeiboException e) {
			if(401 == e.getStatusCode()){
				System.out.println("Unable to get the access token.");
			}else{
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
