import java.math.BigInteger;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
//  Task 1. Function that returns number of prime numbers from Integer[].
        Integer[] nmbrs = {2, 3, 5, 11, 13, 4, 6, 8, 9, 10, 12, 18, 20, 40, 60};
        Function <Integer[], Integer> primeNumbers = Main::countPrimeNumbers;
        System.out.println(primeNumbers.apply(nmbrs));
// Task 2. Function counting of consonants in the String.
        String str = "One characteristic of the desired result is that the matching elements should end up in one collection, and the non-matching elements should end up in a different collection.";
        String strTwo = "and the non-matching elements should end up in a different collection. In the pre-Java-8 mutative world, the easiest way to think about getting a collection";
        Function <String, Integer> consonantsCounter = Main::consonantCounter;
        System.out.println(consonantCounter(str));
// Task 3. Compose of function calculating the sum of char-codes from random string.
        int sum =  str.codePoints().sum(); //just for checking that func.compose returns right value.
        System.out.println(sum);

        Function <String, IntStream> charsFromString = String::chars;
        Function <IntStream, Integer> sumFromStream = IntStream::sum;
        Function <String, Integer> res = sumFromStream.compose(charsFromString);
        System.out.println(res.apply(str));
// Task 4. BiFunction <String, String, String[]>, returns array of matched words from two strings realization.
        BiFunction<String, String, String[]> beef = Main::equalWordsFinder;
        System.out.println(Arrays.toString(beef.apply(str, strTwo)));
// Task 5. Primitive realization of Function returns the integer value of year from the Calendar instance.
        Date date = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        ToIntFunction<Calendar> yearToLong = Calendar::getWeekYear;
        System.out.println(yearToLong.applyAsInt(cal));
    }
    public static Integer consonantCounter (String s){
        return Integer.parseInt(String.valueOf(Stream.of(s.split("")).filter(a -> "aeiouy".contains(String.valueOf(a))).count()));
    }
    public static Integer countPrimeNumbers (Integer[] numbers){
        return Integer.parseInt(String.valueOf(Stream.of(numbers)
                .map(a -> BigInteger.valueOf(a).isProbablePrime((int) Math.log(a)))
                .filter(a -> a.equals(true))
                .count()));
    }
    public static String[] equalWordsFinder (String s, String d){
        String[] wordsArrOne = s.split(" ");
        String[] wordsArrTwo = d.split(" ");
        List <String> matchArr = new ArrayList<>();
        for (int i = 0; i < wordsArrOne.length; i++){
            for (int j = 0; j < wordsArrTwo.length; j++){
                if (wordsArrOne[i].equals(wordsArrTwo[j])){
                    matchArr.add(wordsArrOne[i]);
                }
            }
        }
        String[] array = matchArr.toArray(new String[0]);
        return array;
    }
//    public static Long dateToLong (Date d){
//
//    }
}