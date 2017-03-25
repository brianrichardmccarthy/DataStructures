
public class DictionaryItem implements Comparable<DictionaryItem> {

	private String spanishWord, englishWord;

	public DictionaryItem(String spanishWord, String englishWord) {
		this.spanishWord = spanishWord;
		this.englishWord = englishWord;
	}

	public String getSpanishWord() {
		return spanishWord;
	}

	public String getEnglishWord() {
		return englishWord;
	}

	@Override
	public String toString() {
		return "Spanish: " + spanishWord + ", English: " + englishWord + ".\n";
	}

	@Override
	public int compareTo(DictionaryItem that) {
		return this.spanishWord.compareTo(that.spanishWord);
	}

}
