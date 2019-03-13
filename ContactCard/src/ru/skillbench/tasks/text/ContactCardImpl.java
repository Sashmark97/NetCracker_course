/*package ru.skillbench.tasks.text;

import java.util.regex.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ContactCardImpl implements ContactCard {
	private String fullName;
    private String organization;
    private String gender;
    private static Calendar birthday;
    private String[][] phoneNumber = new String[5][2];

	public ContactCard getInstance(Scanner scanner) {
		LinkedList<String> values = new LinkedList<String>();
		while(scanner.hasNextLine()){
			values.add(scanner.nextLine());
		}
		if (values.size() < 2 || values.get(0)!="BEGIN:VCARD") {
            throw new NoSuchElementException();
        }
		//System.out.println(scanner.toString(	));
		System.out.println(values);
		fullName = values.get(1).split(":")[1];
		organization = values.get(2).split(":")[1];
		gender = values.get(3).split(":")[1];
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date date = sdf.parse(values.get(4).split(":")[1]);
			Calendar cal = sdf.getCalendar();
			birthday = cal;
			if(!values.get(3).split(":")[1].equals("F") && !values.get(3).split(":")[1].equals("M")){
				throw new InputMismatchException();
			}
		} catch (ParseException e) {
			throw new InputMismatchException();
		}
		
		int i = 5;
		while(this.searchPhone(i, values)){
			i++;
		}
		return this;
	}

	public ContactCard getInstance(String data) {
		Scanner sc = new Scanner(data);
		return this.getInstance(sc);
	}

	public String getFullName() {
		return fullName;
	}

	public String getOrganization() {
		return organization;
	}

	public boolean isWoman() {
		if(gender == "F"){
			return true;
		}
		else{
			return false;
		}
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public Period getAge() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int byear = birthday.get(Calendar.YEAR);
		Period age = Period.ofYears(year - byear);
		return age;
	}

	public int getAgeYears() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int byear = birthday.get(Calendar.YEAR);
		return year - byear;
	}

	public String getPhone(String type){
		String res = new String();
		for(int i = 0; i < 5; i++){
			if(phoneNumber[i][0].equals(type)){
				return "(" + phoneNumber[i][1].substring(0,3) + ") "+ phoneNumber[i][1].substring(3,6)+"-"+ phoneNumber[i][1].substring(6,10);
			}
		}
		return res;
	}
	
	public boolean searchPhone(int numOfIter, LinkedList<String> values){
		String reg=".*?(?:[a-z][a-z]+).*?(?:[a-z][a-z]+).*?((?:[a-z][a-z]+)).*?(\\d+)";
	    Pattern pg = Pattern.compile(reg, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	    Matcher mg1 = pg.matcher(values.get(numOfIter));
	    if (mg1.find())
	    {
	    	String wordg1=mg1.group(1);
	        String int1=mg1.group(2);
	        phoneNumber[numOfIter - 5][0] = wordg1;
	        phoneNumber[numOfIter - 5][1] = String.valueOf(int1);
	    }
	    Matcher mg2 = pg.matcher(values.get(numOfIter+1));
		return mg2.find();
	}

	/**
	 * @param args
	 */
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ContactCard cc = new ContactCardImpl();
		cc.getInstance("BEGIN:VCARD\r\nFN:Forrest Gump\r\nORG:Bubba Gump Shrimp Co.\r\nGENDER:M\r\nBDAY:06-06-1944\r\nTEL;TYPE=WORK,VOICE:4951234567\r\nTEL;TYPE=CELL,VOICE:9150123456\r\nEND:VCARD");
		//System.out.println(cc.getFullName());
		//System.out.println(cc.getOrganization());
		//System.out.println(cc.isWoman());
		//System.out.println(cc.getBirthday());
		//System.out.println(cc.getAgeYears());
		//System.out.println(birthday.get(Calendar.YEAR));
		//System.out.println(birthday.get(Calendar.MONTH));
		//System.out.println(birthday.get(Calendar.DAY_OF_MONTH));
		//System.out.println(cc.getAge().getYears());
		System.out.println(cc.getPhone("CELL"));
		
	}

}*/
