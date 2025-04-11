package com.example.myapp;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class PathUtils {
	public static final String CRLF = "\r\n";
	public static List<String> readAllLines(Path path, Charset charset){
		String all = readAll(path, charset);
		return Arrays.asList(all.split(CRLF));
	}
	public static String readAll(Path path, Charset charset){
		try{
			return new String(Files.readAllBytes(path), charset);
		} catch (IOException e){
			e.printStackTrace();
		}
		return "";
	}
	public static Path writeString(Path path, String all, Charset charset){
		try{
			return Files.write(path, all.getBytes(charset));
		} catch (IOException e){
			e.printStackTrace();
		}
		return path;
	}
}
