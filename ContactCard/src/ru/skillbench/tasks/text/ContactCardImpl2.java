package ru.skillbench.tasks.text;

import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ContactCardImpl2 implements ContactCard {
	private String fullName;
    private String organization;
    private String gender;
    private Calendar birthday;
    private Map<String, String> mapContacts; //= new HashMap<String, String>();

	@Override
	public ContactCard getInstance(Scanner scanner) {
    	this.mapContacts = new HashMap();
		scanner.useDelimiter("\r\n");
		@SuppressWarnings("unused")
		boolean beginFlag = false, endFlag = false, fnFlag = false, orgFlag = false;

			while(scanner.hasNext()) {
	            String string = scanner.next();
	            if(string.equals("BEGIN:VCARD"))
	                beginFlag = true;
	            if(string.equals("END:VCARD"))
	                endFlag = true;
	            if (!string.contains(":"))
	                throw new InputMismatchException("Wrong format");
	            Scanner anotherScanner = new Scanner(string);
	            anotherScanner.useDelimiter(":");
	            String fieldName = anotherScanner.next();
	            if (fieldName.equals("FN")) {
	                fnFlag = true;
	                this.fullName = anotherScanner.next();
	                anotherScanner.close();
	            }
	            if (fieldName.equals("ORG")) {
	                orgFlag = true;
	                this.organization = anotherScanner.next();
	                anotherScanner.close();
	            }
	            if (fieldName.equals("GENDER")) {
	                String gender = anotherScanner.next();
	                anotherScanner.close();
	                if (!gender.equals("M") && !gender.equals("F"))
	                    throw new InputMismatchException("Wrong format");
	                this.gender = gender;
	            }
	            if (fieldName.equals("BDAY")) {
	                String date = anotherScanner.next();
	                if (date.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
	                    int day = Integer.parseInt(date.split("-")[0]);
	                    int month = Integer.parseInt(date.split("-")[1]);
	                    int year = Integer.parseInt(date.split("-")[2]);
	                    this.birthday = new GregorianCalendar(year, month - 1, day);
	                    anotherScanner.close();
	                }
	                else throw new InputMismatchException("Wrong format");
	            }
	            
	            Scanner phoneScanner = new Scanner(string);
	            phoneScanner.useDelimiter(";");
	            if (phoneScanner.hasNext("TEL")) {
	                phoneScanner.next();
	                Scanner anotherPhoneScanner = new Scanner(phoneScanner.next());
	                anotherPhoneScanner.useDelimiter("=");
	                anotherPhoneScanner.next();
	                String[] phoneInfo = anotherPhoneScanner.next().split(":");
	                anotherPhoneScanner.close();
	                String phoneType = phoneInfo[0];
	                String phoneNumber = phoneInfo[1];
	                if (!phoneNumber.matches("[0-9]{10}"))
	                    throw new InputMismatchException("Wrong format");
	                this.mapContacts.put(phoneType, phoneNumber);
	            }
	            phoneScanner.close();
	            anotherScanner.close();
	            
	        }
			if (!fnFlag || !orgFlag || !beginFlag || !endFlag){
            	throw new NoSuchElementException("Field not found");
            }
			scanner.close();
			
		return null;
	}

	@Override
	public ContactCard getInstance(String data) {
		Scanner sc = new Scanner(data);
		return this.getInstance(sc);
	}

	@Override
	public String getFullName() {
		return this.fullName;
	}

	@Override
	public String getOrganization() {
		return this.organization;
	}

	@Override
	public boolean isWoman() {
		boolean flag = false;
		
        if (this.gender == null || this.gender.equals("M"))
            flag = false;
        else if (this.gender.equals("F"))
            flag = true;
        return flag;
	}

	@Override
	public Calendar getBirthday() {
		if (this.birthday != null)
            return this.birthday;
        else throw new NoSuchElementException("No birthday");
	}

	@Override
	public Period getAge() {
		if(this.birthday != null){
			int year = Calendar.getInstance().get(Calendar.YEAR);
			Period age = Period.ofYears(year - this.birthday.get(Calendar.YEAR));
			return age;
		}
		else throw new NoSuchElementException("Birthday not found");
	}

	@Override
	public int getAgeYears() {
		if(this.birthday != null){
			Calendar today = GregorianCalendar.getInstance();
	        Calendar birth = GregorianCalendar.getInstance();
	        birth.set(this.birthday.get(Calendar.YEAR), this.birthday.get(Calendar.MONTH), this.birthday.get(Calendar.DAY_OF_MONTH));
	        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
	        if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH) ||
	                today.get(Calendar.MONTH) == birth.get(Calendar.MONTH) &&
	                        today.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH)) {
	            age--;
	        }
	        return age;
		}
		else throw new NoSuchElementException("Birthday not found");
		
	}

	@Override
	public String getPhone(String type) {
		try{
			String s = new String();
			s = this.mapContacts.get(type);
			return "(" + s.substring(0,3) + ") "+ s.substring(3,6)+"-"+ s.substring(6,10);
		}
		catch(ClassCastException e){
			throw new InputMismatchException("No such phone type!");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	String GOOD[] = {
			"BEGIN:VCARD\r\nFN:Forrest Gump\r\nORG:Bubba Gump Shrimp Co.\r\nGENDER:M\r\nTEL;TYPE=HOME:4951234567\r\nEND:VCARD", 
			"BEGIN:VCARD\r\nFN:Chuck Norris\r\nORG:Hollywood\r\nBDAY:10-04-1940\r\nTEL;TYPE=WORK:1234567890\r\nEND:VCARD", 
	};
	String BAD[] = {
			"BEGIN:VCARD\r\nFN:Forrest Gump\r\nORG:Bubba Gump Shrimp Co.\r\nGENDER:M\r\nBDAY:06-06-1944\r\nTEL;TYPE=HOME:+1 234-567\r\nEND:VCARD", //wrong phone format
			"BEGIN:VCARD\r\nFN:Chuck Norris\r\nORG:Hollywood\r\nBDAY:10-04-1940\r\nTEL;TYPE=WORK:12345678901\r\nEND:VCARD", //digits > 10
	};
		ContactCard cc = new ContactCardImpl2();
		//cc.getInstance(GOOD[0]);
		cc.getInstance(GOOD[1]);
		//cc.getInstance(BAD[0]);
		//cc.getInstance(BAD[1]);
		/*cc.getInstance("BEGIN:VCARD\r\nFN:Forrest Gump\r\nORG:Bubba Gump Shrimp Co.\r\n" +
				"GENDER:F\r\nBDAY:06-06-1944\r\nTEL;TYPE=WORK,VOICE:4951234556\r\n" +
				"TEL;TYPE=CELL,VOICE:9150123456\r\nEND:VCARD");*/
		System.out.println(cc.getFullName());
		System.out.println(cc.getOrganization());
		System.out.println(cc.isWoman());
		//System.out.println(cc.getBirthday());
		System.out.println(cc.getAgeYears());
		System.out.println(cc.getAge());
		//System.out.println(birthday.get(Calendar.YEAR));
		//System.out.println(birthday.get(Calendar.MONTH));
		//System.out.println(birthday.get(Calendar.DAY_OF_MONTH));
		System.out.println(cc.getAge().getYears());
		//System.out.println(cc.getPhone("CELL"));
		System.out.println(cc.getPhone("WORK"));
	}

}
