
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		// Your code goes here
		return (str.substring(1)); 
	}

	public static int levenshtein(String word1, String word2) {
		// Your code goes here
		if ((word1.length()==0) || (word2.length() == 0)){
			return (Math.max(word1.length(), word2.length())); 
		}

		if (word1.charAt(0) == (word2.charAt(0))){
			levenshtein(tail(word1),tail(word2)); 
		}
		if (levenshtein(tail(word1),word2) > levenshtein(word1,tail(word2))){
			
			return 1 + Math.min(levenshtein(word1,tail(word2)) , levenshtein(tail(word1),tail(word2)));
		}
		else {
			return 1 + Math.min(levenshtein(tail(word1),word2) , levenshtein(tail(word1),tail(word2)));
		}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		String word = ""; 
		In in = new In(fileName);

		for (int i=0; i < 3000; i++){
			word = in.readLine(); 
			dictionary[i] = word; 
		}
		// Your code here

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Your code goes here
		int sumLev = 0; 
		int min = threshold; 
		String close = word; 
		for (int i=0; i<3000; i++){
			sumLev = levenshtein(word, dictionary[i]); 
			if (sumLev <= min){
				close = dictionary[i]; 
				min = sumLev; 
			} 
		}
		return close;
	}

}
