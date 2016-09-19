package com.example.newsclient;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class XmlParserUtils {

	public static List<News> parserXml(InputStream in) throws Exception{
		
		List<News> newsLists = null;
		News news = null;
		//获取xml解析器
		XmlPullParser parser = Xml.newPullParser();
		//设置要解析的内容
		parser.setInput(in,"utf-8");
		//获取解析器的事件类型
		int type = parser.getEventType();
		//不停的向下解析
		System.out.println("type=="+type);
		System.out.println("START_TAG=="+XmlPullParser.START_TAG);
		System.out.println("END_DOCUMENT=="+XmlPullParser.END_DOCUMENT);
		while(type!=XmlPullParser.END_DOCUMENT){
			
			switch (type) {
			
			case XmlPullParser.START_TAG:
				
				if("channel".equals(parser.getName())){
					newsLists = new ArrayList<News>();
		 		}else if("item".equals(parser.getName())){
		 			news = new News(); 
		 		}else if("title".equals(parser.getName())){
		 			news.setTitle(parser.nextText());
		 		}else if("link".equals(parser.getName())){
		 			news.setLink(parser.nextText());
		 		}else if("author".equals(parser.getName())){
		 			news.setAuthor(parser.nextText());
		 		}else if("pubDate".equals(parser.getName())){
		 			news.setPubDate(parser.nextText());
		 		}else if("description".equals(parser.getName())){
		 			news.setDescription(parser.nextText());
		 		}		
				break;
			case XmlPullParser.END_TAG:
				
				if("item".equals(parser.getName())){
					newsLists.add(news);
				}
				
				break;
			}
			
			type = parser.next();
			System.out.println("parser.next()=="+type);
			
		}	
		return newsLists;
		
	}

	
}
