package ru.skillbench.tasks.javaapi.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * ЦЕЛЬ ЗАДАЧИ - научиться извлекать информацию о классах и обращаться 
 * к полям и методам объектов с помощью Reflection API.<br/>
 * Дополнительно: упаковывать данные в {@link Stream} и менять тип выбрасываемого исключения.<br/> 
 * <br/>
 * ЗАДАНИЕ<br/>
 * Реализовать методы данного интерфейса путем вызова Reflection API
 *  (с минимальным объемом собственной логики в реализуемых методах).<br/>
 * Reflector по сути дополняет функционал класса Class в тех нередких ситуациях,
 *  для которых Class не имеет готовых методов.<br/>
 * 
 * @author Alexey Evdokimov 
 */
public interface Reflector {
	
	/**
	 * Задает класс, у которого нужно брать метаданные в других методах.
	 * @param clazz
	 */
	public void setClass(Class<?> clazz);
	
	/**
	 * Метод возвращает имена всех public методов, принимающих параметры заданных типов, -
	 *   для класса, заданного методом {@link #setClass(Class)}, в т.ч. его суперклассов.<br/>
	 * Если метод какого-либо суперкласса переопределен методом класса, и имеет то же возвращаемое значение,
	 *  метод суперкласса возвращаться не должен.
	 * @param paramTypes Типы параметров, которые входят в сигнатуру искомых методов
	 * @throws NullPointerException если класс равен null
	 * @return Набор имен методов
	 */
	public Stream<String> getMethodNames(Class<?>... paramTypes);
	
	/**
	 * Метод возвращает набор всех не-static полей класса, заданного методом {@link #setClass(Class)}, 
	 *   в т.ч. полей его суперклассов.<br/>
	 * Для выделения не-статических полей рекомендуется использовать метод {@link Stream#filter(java.util.function.Predicate)}
	 *   с lambda-выражением.<br/>
	 * В отличие от {@link Class#getFields()}, возвращаемые поля могут иметь любые модификаторы доступа:
	 *   private, public, protected или default. 
	 * @throws NullPointerException если класс равен null
	 * @return Набор не-static полей для всей иерархии наследования данного класса. 
	 */	
	public Stream<Field> getAllDeclaredFields();	
	
	/**
	 * Возвращает значение поля заданного объекта без расчета на то, что у поля есть getter.<br/>
	 * Поле может иметь любой идентификатор доступа.<br/>
	 * Поле объявлено в классе, который задан методом {@link #setClass(Class), а если он не задан, -
	 *   в классе <code>target.getClass()</code>.<br/>
	 * Примечание: объект <code>target</code> может быть экземпляром подкласса, и тогда
	 *   в <code>target.getClass()</code> не объявлено поле с заданным именем.
	 * @param target Объект, где хранится значение поля
	 * @param fieldName Имя поля
	 * @throws NoSuchFieldException если поля с указанным именем не существует
	 * @throws IllegalAccessException если к полю нет доступа 
	 *  (при правильно реализованном методе такого исключения возникать не должно)
	 * @return Значение поля
	 */
	public Object getFieldValue(Object target, String fieldName) throws NoSuchFieldException, IllegalAccessException;
	
	/**
	 * Метод создает экземпляр класса, заданного методом {@link #setClass(Class)}, 
	 *  с помощью public конструктора с параметром <code>constructorParameter</code>,
	 *  после чего вызвает его метод с <code>methodName</code>, в который передаются <code>methodParams</code>.<br/>
	 * Метод может иметь любой модификатор доступа - необязательно public, 
	 *  однако если метод не public, то объявлен именно в этом классе (а не в его суперклассе).
	 *  
	 * @param constructorParam Передаваемый конструктору параметр или null, чтобы использовать 
	 *   конструктор без параметров. Тип параметра конструктора равен <code>constructorParam.getClass()</code>
	 * @param methodName Название метода, который нужно вызвать.
	 * @param methodParams Массив параметров для вызова метода, ни один из которых не равен null; 
	 *   предполагается, что сигнатура метода содержит типы элементов methodParams (а не их супертипы).
	 * @return Результат, который возвращает метод. Может быть Void
	 * 
	 * @throws IllegalAccessException если к конструктору или к методу нет доступа 
	 *  (при правильно реализованном {@link #getMethodResult(Object, String, Object...)} такого возникать не должно)
	 * @throws InstantiationException если класс не может быть инстанциирован (является абстрактным и т.п.)
	 * @throws NoSuchMethodException если в классе не существует подходящего конструктора или метода
	 * @throws InvocationTargetException конструктор или метод при вызове выбросили проверяемое исключение
	 * @throws RuntimeException если конструктор или метод при вызове выбросили непроверяемое исключение;
	 *  для этого в {@link #getMethodResult(Object, String, Object...)} необходимо обрабатывать InvocationTargetException
	 */
	public Object getMethodResult(Object constructorParam, String methodName, Object... methodParams)
			throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;
	
	
	
	/**
	 * Класс, который может использоваться для проверки работоспособности класса ReflectorImpl.
	 * Также иллюстрирует разные варианты преобразований из строки в число и обратно.
	 */
	public static class SampleNumber extends Number {
		private static final long serialVersionUID = 657180560399216609L;

		private static DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
		//Locale.US is a way to use '.' as a decimal separator. This is another way to use it:
//		static {
//			DecimalFormatSymbols symbols = new DecimalFormatSymbols();
//			symbols.setDecimalSeparator('.');
//			format = new DecimalFormat("#.##", symbols);
//		}
		
		private String value;
		
		public SampleNumber() {
		}
		
		public SampleNumber(double value) {
			this.value = format.format(value);
		}
		
		public SampleNumber(String value) throws ParseException {
			this.value = formatValue(value);
		}
		
		public final String getValue() {
			return value;
		}

		public void setValue(String value) throws ParseException {
			this.value = formatValue(value);
		}

		private String formatValue(String value) throws ParseException {
			Double num = format.parse(value).doubleValue();
			return format.format(num);
		}
		
		public SampleNumber sum(SampleNumber a){
			return toNumber(doubleValue() + a.doubleValue());
		}
		
		public SampleNumber multiply(SampleNumber a){
			return toNumber(doubleValue() * a.doubleValue());
		}
		
		private SampleNumber toNumber(double value){
			String s = format.format(value);
			try {
				return new SampleNumber(s);
			} catch (ParseException e) {
				throw new Error("A Format can't produce a String that it's unable to parse", e);
			}
		}

		@Override
		public int intValue() {
			return (int) longValue();
		}

		@Override
		public long longValue() {
			String s = value.endsWith(".0") ? value.substring(0,value.length()-2) : value;
			return Long.parseLong(s);
		}

		@Override
		public float floatValue() {
			return Float.parseFloat(value);
		}

		@Override
		public double doubleValue() {
			return Double.parseDouble(value);
		}
		
	} 
	
}