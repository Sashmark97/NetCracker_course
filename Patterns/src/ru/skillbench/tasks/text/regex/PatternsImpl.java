package ru.skillbench.tasks.text.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternsImpl implements Patterns {

	public Pattern getSQLIdentifierPattern() {
        return Pattern.compile("[A-Za-z][A-Za-z0-9_$]{0,29}");
	}

	public Pattern getEmailPattern() {
		return Pattern.compile("[A-Za-z0-9][A-Za-z0-9_.-]{0,19}[A-Za-z0-9]{1,1}@" +
                "([A-Za-z0-9][A-Za-z0-9-]+[A-Za-z0-9]\\.)+(ru|com|net|org)$");
	}

	public Pattern getHrefTagPattern() {
        return Pattern.compile("<[\t\n\r\f ]*(A|a)[\t\n\r\f ]*(HREF|href)[\t\n\r\f ]*=[\t\n\r\f ]*([\\S]|\"[^\"]+\")+[\t\n\r\f ]*/?>");
	}

	public List<String> findAll(String input, Pattern pattern) {
		if(input == null || pattern == null){
            throw new IllegalArgumentException();
        }
        List<String> list = new ArrayList<String>();
        Matcher m = pattern.matcher(input);
        while(m.find()){
            	list.add(m.group());
            }
        return list;
	}

	public int countMatches(String input, String regex) {
		int count = 0;
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(input);
		while (m.find())
		    count++;
		return count;
	}

}
