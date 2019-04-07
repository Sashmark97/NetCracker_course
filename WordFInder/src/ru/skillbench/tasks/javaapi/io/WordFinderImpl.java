package ru.skillbench.tasks.javaapi.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class WordFinderImpl implements WordFinder {
	private String text;

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setInputStream(InputStream is) throws IOException {
		if (is == null) {
            throw new IllegalArgumentException("InputStream is null...");
        }
		
		try {
            byte[] str = new byte[is.available()];
            is.read(str);
            this.text = new String(str);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

	public void setFilePath(String filePath) throws IOException {
		try {
		      this.text = new String(Files.readAllBytes(Paths.get(filePath)));
		    } catch (IOException e) {
		      e.printStackTrace();
		    }

	}

	public void setResource(String resourceName) throws IOException {
		try{
			@SuppressWarnings("rawtypes")
			Class cls = Class.forName("ClassLoaderDemo");
	        ClassLoader cLoader = cls.getClassLoader();
			this.setInputStream(cLoader.getResourceAsStream(resourceName));
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public Stream<String> findWordsStartWith(String begin) {
		if (this.text == null) {
            throw new IllegalStateException("Text is null...");
        }

        Set<String> parseText = new HashSet<>();

        Pattern p = Pattern.compile("\\S"+ begin + "$");
        Matcher m = p.matcher(this.text.toLowerCase());

        while (m.find()) {
            if (begin == null || begin.length() == 0 ||
                    (begin.toLowerCase()).equals(m.group().substring(0, begin.length()))) {
                parseText.add(m.group());
            }
        }
        return parseText.stream();
	}

	public void writeWords(OutputStream os) throws IOException {
		if (os == null) {
            throw new IllegalArgumentException("InputStream is null...");
        }

	}

}
