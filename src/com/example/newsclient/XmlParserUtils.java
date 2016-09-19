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
		//��ȡxml������
		XmlPullParser parser = Xml.newPullParser();
		//����Ҫ����������
		parser.setInput(in,"utf-8");
		//��ȡ���������¼�����
		int type = parser.getEventType();
		//��ͣ�����½���
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
