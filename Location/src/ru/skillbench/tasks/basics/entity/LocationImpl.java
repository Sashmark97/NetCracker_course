package ru.skillbench.tasks.basics.entity;

public class LocationImpl implements Location {	
	private String name;
	private Type type;
	private Location parent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setParent(Location parent) {
		this.parent = parent;

	}

	public String getParentName() {
		if(this.parent == null){
			return "--";
		}
		else{
			return this.parent.getName();
		}
	}

	public Location getTopLocation() {
		if (parent == null) {
            return this;
        } else {
        	return parent.getTopLocation();
        }
	}

	public boolean isCorrect() {
		if(this.parent == null || type.compareTo(parent.getType()) > 0 && parent.isCorrect()){
			return true;
		}
		return false;
	}

	public String getAddress() {
		int index = name.indexOf('.');
        String cur;
        if (index >= 0 && (index == name.length() - 1) || index + 1 == name.indexOf(' ')) {
            cur = name;
        } else {
            cur = type.getNameForAddress() + name;
        }
        if (parent == null) {
            return cur;
        } else {
            return cur + ", " + parent.getAddress();
}
	}
	
	public String toString(){
        return name + " (" + type.toString() + ")";
}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void Type(String nameForAddress) {
		// TODO Auto-generated method stub
		
	}

	public final String getNameForAddress() {
		// TODO Auto-generated method stub
		return null;
	}

}
