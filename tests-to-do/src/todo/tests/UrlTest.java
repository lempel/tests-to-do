package todo.tests;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlTest {

	public static void main(String[] args) throws MalformedURLException {
		String urlString = "https://ssl.google-analytics.com/r/__utm.gif?utmwv=5.6.3&utms=1&utmn=1838896897&utmhn=soundcloud.com&utmcs=UTF-8&utmsr=0x0&utmsc=24-bit&utmul=en&utmje=0&utmfl=-&utmdt=Search%20Everything%20on%20SoundCloud%20-%20Hear%20the%20world%E2%80%99s%20sounds&utmhid=844410903&utmr=-&utmp=%2Fsearch%3Fq%3Dtriphop&utmht=1427380181417&utmac=UA-2519404-22&utmcc=__utma%3D179375142.1734893644.1427380181.1427380181.1427380181.1%3B%2B__utmz%3D179375142.1427380181.1.1.utmcsr%3D(direct)%7Cutmccn%3D(direct)%7Cutmcmd%3D(none)%3B&aip=1&utmjid=995763966&utmredir=1&utmu=qAQAAAAAAAAAAAAAAAAAAAAE~";
		
		URL url = new URL(urlString);
		
		System.out.println(urlString);
		System.out.println("url.getAuthority() = " + url.getAuthority());
		System.out.println("url.getFile() = " + url.getFile());
		System.out.println("url.getPath() = " + url.getPath());
		System.out.println("url.getQuery() = " + url.getQuery());
		System.out.println("url.getRef() = " + url.getRef());
		System.out.println("url.getUserInfo() = " + url.getUserInfo());
	}
}
