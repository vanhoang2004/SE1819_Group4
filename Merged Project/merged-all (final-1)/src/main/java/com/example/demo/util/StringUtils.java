package com.example.demo.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {
	public static String removeDiacritics(String str) {
		String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String result = pattern.matcher(normalized).replaceAll("");
       
        return result;
    }
}
