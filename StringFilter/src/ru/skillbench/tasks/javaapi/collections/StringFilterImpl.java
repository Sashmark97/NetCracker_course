package ru.skillbench.tasks.javaapi.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class StringFilterImpl implements StringFilter {
	private static Set<String> storage = new HashSet<>();
	
	public int goWild(String s){
		int asteriskCount = 0;
		for(char c : s.toCharArray()) {
		    if(c == '*'){
		    	asteriskCount++;
		    }
		}
		return asteriskCount;
	}
	
	public void add(String s) {
		if(s == null){
			storage.add(null);
		}
		else{
			storage.add(s.toLowerCase());
		}
	}

	public boolean remove(String s) {
		boolean present;
		if(s == null){
			present = storage.remove(null);
		}
		else{
			present = storage.remove(s.toLowerCase());
		}
		
		return present;
	}

	public void removeAll() {
		storage.clear();
	}

	public Collection<String> getCollection() {
		return storage;
	}

	public Iterator<String> getStringsContaining(String chars) {
		Set<String> temp = new HashSet<>();
		if((chars == null)||(chars.isEmpty())){
			return storage.iterator();
		}
		else{
			for(String s : storage){
				if((s != null) && (s.toLowerCase().contains(chars))){
					temp.add(s);
				}
			}
		}
		return temp.iterator();
	}

	public Iterator<String> getStringsStartingWith(String begin) {
		Set<String> temp = new HashSet<>();
		if((begin == null) || (begin.isEmpty())){
			return storage.iterator();
		}
		else{
			for(String s : storage){
				if((s != null) && (s.toLowerCase().startsWith(begin.toLowerCase()))){
					temp.add(s);
				}
			}
		}
		return temp.iterator();
	}

	public Iterator<String> getStringsByNumberFormat(String format) {
		Set<String> temp = new HashSet<>();
		String allowedNumbers = "0123456789";
		if((format == null) || (format.isEmpty())){
			return storage.iterator();
		}
		else{
			for(String s : storage){
				int correct = 0;
				if(s.length() == format.length()){
					for(int i = 0; i < format.toCharArray().length; i++){
						if((format.toCharArray()[i] != '#' ) && s.toCharArray()[i] == format.toCharArray()[i]){
							correct++;
						}
						if((format.toCharArray()[i] == '#') && (allowedNumbers.contains(s.toCharArray()[i]+""))){
							correct++;
						}
						if(correct == format.length()){
							temp.add(s);
						}
					}
				}
			}
		}
		return temp.iterator();
	}

	public Iterator<String> getStringsByPattern(String pattern) {
		Set<String> temp = new HashSet<>();
		if((pattern == null) || (pattern.isEmpty()) || goWild(pattern) == 0){
			return storage.iterator();
		}
		
		if(goWild(pattern) == 1){
			if(pattern.indexOf("*") == pattern.length() - 1){
				for (String s : storage){
					if((s != null) && (s.startsWith(pattern.substring(pattern.length() - 1)))){
						temp.add(s);
					}
				}
				
			}
			
			if(pattern.indexOf("*") == 0){
				for (String s : storage){
					if((s != null) && (s.endsWith(pattern.substring(1)))){
						temp.add(s);
					}
				}
			}
			
			else{
				for (String s : storage){
					if(
							   (s != null)
							&& (s.startsWith(pattern.substring(0 , pattern.indexOf("*"))))
							&& (s.endsWith(pattern.substring(pattern.indexOf("*") + 1)))){
						temp.add(s);
					}
				}
			}
			
		}
		
		if(goWild(pattern) == 2){
			if((pattern.indexOf("*") == pattern.length() - 1) && (pattern.indexOf("*") == 0)){
				for (String s : storage){
					if((s != null) && (s.contains(pattern.substring(1, pattern.length() - 1)))){
						temp.add(s);
					}
				}
			}
			
			if((pattern.indexOf("*") == 0) && (pattern.lastIndexOf("*") != pattern.length() - 1)){
				for (String s : storage){
					if(
							   (s != null) 
							&& (s.endsWith(pattern.substring(pattern.lastIndexOf("*"), pattern.length() - 1))) 
							&& (s.contains(pattern.substring(1, pattern.lastIndexOf("*"))))){
						temp.add(s);
					}
				}
			}
			
			else{
				for (String s : storage){
					if(
							   (s != null)
							&& (s.startsWith(pattern.substring(0 , pattern.indexOf("*"))))
							&& (s.contains(pattern.substring(pattern.indexOf("*") + 1, pattern.length() - 1)))){
						temp.add(s);
					}
				}
			}
			
		}
		return temp.iterator();
	}

}
