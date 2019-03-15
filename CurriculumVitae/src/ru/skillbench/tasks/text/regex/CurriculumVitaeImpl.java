package ru.skillbench.tasks.text.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurriculumVitaeImpl implements CurriculumVitae {
	private String text;
	
	private List<Phone> phonesTemp;
	
	private String fullName;
	private String firstName;
	private String middleName;
	private String lastName;
	
	private final String NAME_PATTERN1 = "(\\b[A-Z]{1}[a-z]+)( )([A-Z]{1}[a-z]+)( )([A-Z]{1}[a-z]+\\b)";
	private final String NAME_PATTERN2 = "(\\b[A-Z]{1}[a-z]+)( )([A-Z]{1}[a-z]+\\b)";
	

	public void setText(String text) {
		this.text = text;
	}

	public String getText() throws IllegalStateException{
		if (this.text == null) {
            throw new IllegalStateException();
        }
		return this.text;
	}
	
	public void parseName(){
		String fn = getText().split("\n")[0];
		Pattern p1 = Pattern.compile(NAME_PATTERN1);
		Matcher m1 = p1.matcher(fn);
		if(m1.matches()){
			this.firstName = m1.group(1);
			this.middleName = m1.group(3);
			this.lastName = m1.group(5);
			this.fullName = firstName + " " + middleName + " " + lastName;
		}
		
		Pattern p2 = Pattern.compile(NAME_PATTERN2);
		Matcher m2 = p2.matcher(fn);
		if(m2.matches()){
			this.firstName = m2.group(1);
			this.middleName = null;
			this.lastName = m2.group(3);
			this.fullName = firstName + " " + lastName;
		}
		if(!m1.matches()&&!m2.matches()){
			throw new NoSuchElementException();
		}
	}

	public List<Phone> getPhones() throws IllegalStateException{

		if (this.text == null) {
            throw new IllegalStateException();
        }
		
		Pattern p = Pattern.compile(PHONE_PATTERN);
        Matcher m = p.matcher(getText());
        phonesTemp = new ArrayList<Phone>();
        Phone phone;
        while (m.find()) {
            int areaCode = -1;
            int extension = -1;
            if (m.group(2) != null) areaCode = Integer.parseInt(m.group(2));
            if (m.group(7) != null) extension = Integer.parseInt(m.group(7));
            phone = new Phone(m.group(), areaCode, extension);
            phonesTemp.add(phone);
        }
        return phonesTemp;
	}

	public String getFullName() throws NoSuchElementException, IllegalStateException{
		if (this.text == null) {
            throw new IllegalStateException();
        }
		parseName();
		if((this.fullName == null)){
			throw new NoSuchElementException();
		}
		
		return this.fullName;
	}

	public String getFirstName() {
		parseName();
		if((this.fullName == null)){
			throw new NoSuchElementException();
		}
		
		return this.firstName;
	}

	public String getMiddleName() {
		parseName();
		if((this.fullName == null)){
			throw new NoSuchElementException();
		}
		
		return this.middleName;
	}

	public String getLastName() {
		parseName();
		if((this.fullName == null)){
			throw new NoSuchElementException();
		}
		
		return this.lastName;
	}

	public void updateLastName(String newLastName) {
		if (this.text == null) {
            throw new IllegalStateException();
        }
		if((newLastName == null)){
			throw new IllegalArgumentException();
		}
		
		this.lastName = newLastName;
	}

	public void updatePhone(Phone oldPhone, Phone newPhone) {
		if((oldPhone == null)||(newPhone == null)){
			throw new IllegalArgumentException();
		}
		phonesTemp.remove(oldPhone);
		phonesTemp.add(newPhone);
	}

	public void hide(String piece) {
		if((piece == null)){
			throw new IllegalArgumentException();
		}


	}

	public void hidePhone(String phone) {
		if((phone == null)){
			throw new IllegalArgumentException();
		}

	}

	public int unhideAll() {
		if (this.text == null) {
            throw new IllegalStateException();
        }
		return 0;
	}

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */

}
