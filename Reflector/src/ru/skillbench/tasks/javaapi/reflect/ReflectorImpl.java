package ru.skillbench.tasks.javaapi.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.function.Predicate;

public class ReflectorImpl implements Reflector {
	private Class<?> clazz;
	
	public void throwNullPo(Object o){
		if(o == null){
			throw new NullPointerException("Gah...");
		}
	}

	public void setClass(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Stream<String> getMethodNames(Class<?>... paramTypes) {
		throwNullPo(this.clazz);
		List<String> items = new ArrayList<String>();
		Method[] methods = clazz.getMethods(); 
		for (Method method : methods) { 
		    Class[] paramTypesCurrent = method.getParameterTypes(); 
		    if(Arrays.equals(paramTypesCurrent, paramTypes)){ 
		    	items.add(method.getName());
		    } 
		} 
		return items.stream();
	}

	public Stream<Field> getAllDeclaredFields() {
		throwNullPo(this.clazz);
		List<Field> items = new ArrayList<Field>();
		Field[] publicFields = clazz.getFields(); 
		return null;//items.stream()
				//.filter(p --> p.getAge() > 21 && p.getGender().equalsIgnoreCase("M"));
				//.filter((f)->java.lang.reflect.Modifier.isStatic(f.getModifiers() );;
	}

	public Object getFieldValue(Object target, String fieldName)
			throws NoSuchFieldException, IllegalAccessException {
		throwNullPo(this.clazz);
		// TODO Auto-generated method stub
		
		return null;
	}

	public Object getMethodResult(Object constructorParam, String methodName,
			Object... methodParams) throws IllegalAccessException,
			InstantiationException, NoSuchMethodException,
			InvocationTargetException {
		throwNullPo(this.clazz);
		// TODO Auto-generated method stub
		return null;
	}

}
