package ru.skillbench.tasks.javaapi.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import ru.skillbench.tasks.javaapi.reflect.Reflector.SampleNumber;

/**
 * Open test class for {@link Reflector}.
 * @author Alexey Evdokimov
 */
public class ReflectorTests {
	private Reflector impl;
	
	@Rule
	public TestRule globalTimeout = new DisableOnDebug(Timeout.seconds(1));
	
	@Before
	public void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		impl = (Reflector) Class.forName("ru.skillbench.tasks.javaapi.reflect.ReflectorImpl").newInstance();
	}
	
	@Test
	public void publicMethods1() {
		testMethods(new Class[]{Object.class}, HashMap.class, 
				"containsKey", "get", "equals", "remove", "containsValue");
	}
	@Test
	public void publicMethods2() {
		testMethods(new Class[0], MessageFormat.class, 
				"getFormats", "getFormatsByArgumentIndex", "getLocale", "toPattern",
				"getClass", "toString", "hashCode", "clone", "wait", "notify", "notifyAll");
	}
	
	private void testMethods(Class[] paramTypes, Class clazz, String... expected) {
		impl.setClass(clazz);
		List expectedList = Arrays.asList(expected);
		Collections.sort(expectedList);
		
		assertEquals("getMethodNames() failed with "+clazz.getSimpleName()+".class",
				expectedList,
				impl.getMethodNames(paramTypes).sorted().collect(Collectors.toList()) );
	}
	
	/*@Test
	public void declaredFields() {
		impl.setClass(AbstractMap.class);
		Set fields = impl.getAllDeclaredFields().map(Field::getName).collect(Collectors.toSet());
		String message = "getAllDeclaredFields() failed with AbstractList.class. ";
		
		assertEquals(message+"Wrong field count:", 2, fields.size());
		
		for(String fName: new String[]{"keySet", "values"}) {
			assertTrue(message+"Field named '"+fName+"' does not exist in "+fields, fields.contains(fName));
		}
	}*/
	
	@Test
  	public void getPrivateFieldValue() throws NoSuchFieldException, IllegalAccessException {
		SampleNumber num = new SampleNumber(Math.random());
		impl.setClass(null);//to get from num.getClass()
		assertEquals("Getting value of a private field (SampleNumber.value) failed:", 
			num.getValue(), impl.getFieldValue(num, "value"));
	}
	
	@Test(expected = InvocationTargetException.class)
  	public void getMethodResultTargetException() 
			throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
  		impl.setClass(Class.forName("ru.skillbench.tasks.javaapi.reflect.Reflector$SampleNumber"));
		impl.getMethodResult(null, "setValue", "abc");
	}
	@Test(expected = NumberFormatException.class)
  	public void getMethodResultRuntimeTargetException() 
			throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
  		impl.setClass(SampleNumber.class);
		impl.getMethodResult("2.5", "longValue");//"2.5" can't be parsed to long
	}
}