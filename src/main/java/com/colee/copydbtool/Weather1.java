package com.colee.copydbtool;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
 public class Weather1{
        String cityName;
        URLConnection urlConnection;
        StringBuilder stringBuilder;
        BufferedReader bufferedReader;//读取data数据流

        public static void main(String[] args) {
			try {
				new Weather1();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
        public Weather1() throws IOException {
        	//解析本机ip地址
        	URL url=new URL("http://wthrcdn.etouch.cn/WeatherApi?city=%E5%8D%97%E4%BA%AC");
        	HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
        	urlConnection.setConnectTimeout(1000);
        	try{
        		bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        		stringBuilder=new StringBuilder();
        		String line=null;
        		while ((line=bufferedReader.readLine())!=null){
        			stringBuilder.append(line);
        		}
        		System.out.println(stringBuilder.toString());
        		
        		
        		//打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream。 
        		Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream())); 
        		int c; 
        		while ((c = reader.read()) != -1) { 
        			System.out.print((char) c); 
        		} 
        		reader.close(); 
        	}
        	catch (SocketTimeoutException e){
        		System.out.println("连接超时");
        	}
        	catch (FileNotFoundException e){
        		System.out.printf("加载文件出错");
        	}
        	String datas=stringBuilder.toString();
        	}
        
        
        public Weather1(String cityName) throws IOException {
            this.cityName=cityName;
            //解析本机ip地址
            URL url=new URL("http://wthrcdn.etouch.cn/WeatherApi?city="+cityName);
            urlConnection=url.openConnection();
            urlConnection.setConnectTimeout(1000);
            try{
                bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),Charset.forName("GBK")));
                stringBuilder=new StringBuilder();
                String line=null;
                while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(new String(line.getBytes("GBK"),"UTF-8"));
                }
                System.out.println(stringBuilder.toString());
                
                
                //打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream。 
                Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream())); 
                int c; 
                while ((c = reader.read()) != -1) { 
                        System.out.print((char) c); 
                } 
                reader.close(); 
            }
            catch (SocketTimeoutException e){
                System.out.println("连接超时");
            }
            catch (FileNotFoundException e){
                System.out.printf("加载文件出错");
            }
            String datas=stringBuilder.toString();}
    }
