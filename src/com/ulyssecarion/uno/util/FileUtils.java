package com.ulyssecarion.uno.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileUtils {
	public static String readFile(String path) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			StringBuffer str = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				str.append(line);
				str.append("\n");
			}
			br.close();
			return str.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
