package com.flaky.maven.extension.util;
 
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class KeyWordCatcher {
	public static void main(String[] args) {
		
		String returnXml = "MODIFY UserController.java";
		System.out.println(returnXml.substring(returnXml.lastIndexOf("/")+1,returnXml.lastIndexOf(".")));
		String regex = "U(.*?)\\.";
		List<String> list = new ArrayList<String>();
		List<String> extvounoLists = new ArrayList<String>();
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(returnXml);
		while (m.find()) {  
            int i = 1;  
            list.add(m.group(i));  
            i++;  
        } 
		for (String str : list) {
			System.out.println(str);
			String[] strs = str.split("-");
			String strss = strs[strs.length-1];
			extvounoLists.add(strs[strs.length-1]);
		}
		
		for (String string : extvounoLists) {
			System.out.println(string);
		}
	}
}