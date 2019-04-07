package ru.skillbench.tasks.javaapi.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.SortedSet;
import java.util.stream.Stream;

/**
 * ЦЕЛИ ЗАДАЧИ:<ul>
 * <li>разобраться с чтением символьных данных из файлов / байтовых потоков,
 *   и с записью символьных данных в байтовые потоки;</li>
 * <li>привыкать поддерживать поля класса в корректном состоянии 
 *   и генерировать исключения при неверном состоянии полей;</li>
 * <li>научиться выбирать подходящий класс-коллекцию.</li>
 * </ul>
 * <p/>
 * ЗАДАНИЕ<ul>
 * <li>Считывать текст из файла, из {@link InputStream} и через {@link Class#getResourceAsStream(String)}.</li>
 * <li>Находить в тексте слова, начинающиеся с заданной последовательности символов без учета их регистра.</li>
 * <li>Записывать найденные слова в {@link OutputStream}.</li>
 * </ul>
 * <p/>
 * ТРЕБОВАНИЯ<ul>
 * <li>Словом традиционно считается непустая последовательность символов, отделенная 
 *   от других произвольным количеством пробелов, символов табуляции и переводов строк.<br/>
 *   Причем переводом строки здесь может быть как Line Feed, так и Carriage Return,
 *    а в Windows переводом строки считается оба символа: CRLF.</li>
 * <li>Файл/поток с текстом можно прочитать лишь один раз.</li>
 * <li>Метод {@link #findWordsStartWith(String)} после чтения может вызываться многократно.</li>
 * <li>Метод {@link #writeWords(OutputStream)} работает с результатом последнего вызова
 *   метода {@link #findWordsStartWith(String)}.</li>
 * </ul>
 * <p/>
 * РЕКОМЕНДАЦИИ<br/>
 * - О кодировке текста можно не думать - тестироваться будет англоязычный текст.<br/>
 * - В качестве способов фильтрации слов без лишних циклов, можно использовать
 *  метод {@link Stream#filter(java.util.function.Predicate)}
 *  или метод {@link SortedSet#subSet(Object, Object)}<br/>
 * <br/>
 * @author Andrey Shevtsov
 * @author Alexey Evdokimov
 */
public interface WordFinder
{
	/**
	 * @return Текущий текст для поиска или <code>null</code>,
	 *  если ни один из set-методов не был выполнен успешно.
	 */
	String getText();
	/**
	 * Принимает готовый текст для поиска (а не читает текст из файла/потока).
	 * @param text Текст для поиска
	 * @throws IllegalArgumentException если <code>src == null</code>
	 */
	void setText(String text);
	/**
	 * Считывает текст из указанного потока ввода. Кодировка не важна.
	 * @param is Поток ввода
	 * @throws IOException в случае ошибок при чтении из потока
	 * @throws IllegalArgumentException если <code>is == null</code>
	 */
	void setInputStream(InputStream is) throws IOException;
	/**
	 * Считывает текст из указанного файла. Кодировка не важна.
	 * @param filePath Путь к файлу с текстом
	 * @throws IOException в случае ошибок при чтении файла
	 * @throws IllegalArgumentException если <code>filePath == null</code>
	 */
	void setFilePath(String filePath) throws IOException;
	/**
	 * Считывает текст из указанного файла через {@link Class#getResourceAsStream(String)}.<br/>
	 * Это позволяет указывать краткое имя файла и читать его даже в том случае, если он лежит
	 *   в jar-нике вместе с классами и если прямая работа с файлами запрещена настройками безопасности.<br/>
	 * Кодировка не важна.
	 * @param resourceName Короткое имя ресурса (файла), который должен лежать в том же пакете, что и класс
	 * @throws IOException в случае ошибок при чтении
	 * @throws IllegalArgumentException если <code>resourceName == null</code>
	 */
	void setResource(String resourceName) throws IOException;
	
	/**
	 * Ищет в тексте все слова, начинающиеся с указанной последовательности символов,
	 *   без учета их регистра ('А' и 'а' считаются одним и тем же символом). <br/>
	 * Результат возвращает в виде {@link Stream}, порядок элементов в котором не важен.<br/>
	 * Если <code>begin</code> - пустая строка или <code>null</code>,
	 *   то результат содержит все слова, найденные в тексте.<br/>
	 * Все возвращенные слова должны быть приведены к нижнему регистру.
	 * @param begin первые символы искомых слов
	 * @return слова, начинающиеся с указанной последовательности символов
	 * @throws IllegalStateException если нет текста для поиска 
	 *  (если ни один setter-метод не выполнялся или если он последний раз выполнился с ошибкой)
	 */
	Stream<String> findWordsStartWith(String begin);
	
	/**
	 * Превращает слова, найденные в {@link #findWordsStartWith(String)},
	 *  в текст с разделителями "пробел", 
	 *  предварительно приведя их к нижнему регистру
	 *  и отсортировав по алфавиту.<br/>
	 * И записывает этот текст в <code>os</code>.
	 * @param os Поток вывода. Кодировка не важна.
	 * @throws IOException в случае ошибок при записи в поток
	 * @throws IllegalStateException если поиск слов не выполнялся 
	 *  (если {@link #findWordsStartWith(String)} не выполнялся после setter-метода
	 *  или если setter-метод последний раз выполнился с ошибкой)
	 */
	void writeWords(OutputStream os) throws IOException;
	
}
