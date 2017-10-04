package me.Elithous.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
	private static void save(Properties prop) throws IOException {
		File settingsFile = new File("settings.properties");
		FileWriter writer = new FileWriter(settingsFile);
		
		prop.store(writer, "settings");
		writer.close();
	}
	
	public static void resetToDefault() throws IOException {
		Properties prop = new Properties();
		
		prop.setProperty("WIDTH", "1080");
		prop.setProperty("ASPECT_RATIO", "16:9");
		prop.setProperty("FPS", "0");
		
		save(prop);
	}
	
	public static String[] load() throws IOException{
		String[] test = new String[3];
		
		InputStream input = new FileInputStream("settings.properties");
		Properties prop = new Properties();
		prop.load(input);
		
		test[0] = prop.getProperty("WIDTH");
		test[1] = prop.getProperty("ASPECT_RATIO");
		test[2] = prop.getProperty("FPS");
		
		for(int i=0;i<test.length;i++) {
			if (test[i] == null){
				throw new IOException("Missing Settings");
			}
		}
		
		input.close();
		return test;
	}
}
