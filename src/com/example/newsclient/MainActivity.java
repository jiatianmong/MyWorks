package com.example.newsclient;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {

	private List<News> newslists;
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		lv = (ListView) findViewById(R.id.lv);
		
		initListData();
		
	}
	

	public void initListData(){			

		new Thread() {
			@Override
			public void run() {
			// TODO Auto-generated method stub

			try {
				String path = "http://10.15.114.126:8080/news.xml";
				//创建一个url对象
				URL url = new URL(path);
				//获取。。
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(5000);
				int code = connection.getResponseCode();
				System.out.println("--->code= "+code);
				if (code == 200) {
					
					InputStream in = connection.getInputStream();
					newslists = XmlParserUtils.parserXml(in);
					
					runOnUiThread(new Runnable() {
						public void run() {
							System.out.println("进入了更新UI线程");
							lv.setAdapter(new Myadapter());
							System.out.println("结束更新UI");
						}
					});
					
					
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}			
		};}.start();
		
	}
		
	private class Myadapter extends BaseAdapter {
		@Override
		public int getCount() {
			return newslists.size();
		}
	
		@Override
		public Object getItem(int position) {
	
			return null;
		}
	
		@Override
		public long getItemId(int position) {
			return 0;
		}
	
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			System.out.println("进入了更新adapter");
			View view;
			if(convertView == null){
				view = View.inflate(getApplicationContext(), R.layout.item, null);	
			}else{
				view = convertView;
			}
			
			ImageView iv_con = (ImageView) view.findViewById(R.id.iv_icon);
			TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
			TextView tv_descri = (TextView) view.findViewById(R.id.tv_descri);
			TextView tv_link = (TextView) view.findViewById(R.id.tv_link);
			TextView tv_pubDate = (TextView) view.findViewById(R.id.tv_pubDate);
			//显示数据
			
			tv_title.setText(newslists.get(position).getTitle());
			tv_descri.setText(newslists.get(position).getDescription());
			tv_link.setText(newslists.get(position).getLink());
			tv_pubDate.setText(newslists.get(position).getPubDate());
			
			
			return view;
		}	
	
	}
	
}


