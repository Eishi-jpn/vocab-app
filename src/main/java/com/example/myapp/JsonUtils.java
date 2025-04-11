package com.example.myapp;

import java.nio.charset.Charset;
import java.nio.file.Path;

public class JsonUtils {
	public static final Charset CHARSET = Charset.forName("UTF-8");
	public static String readAll(Path path){
		return PathUtils.readAll(path, CHARSET);
	}
	public static Path writeString(Path path, String all){
		return PathUtils.writeString(path, all, CHARSET);
	}
}
