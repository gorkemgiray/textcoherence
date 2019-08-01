import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.sussex.nlp.jws.ICFinder;
import edu.sussex.nlp.jws.JWS;
import edu.sussex.nlp.jws.JiangAndConrath;

import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class Main {

	/**
	 * @param args
	 */
	static IDictionary dict;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// construct the URL to the Wordnet dictionary directory
		//String wnhome = System.getenv("WNHOME");
		//Main.getSynonyms("car");
		// look up first sense of the word "dog"
		/*IIndexWord idxWord = dict.getIndexWord("dog", POS.NOUN);
		IWordID wordID = idxWord.getWordIDs().get(0);
		IWord word = dict.getWord(wordID);
		System.out.println("Id = " + wordID);
		System.out.println("Lemma = " + word.getLemma());
		System.out.println("Gloss = " + word.getSynset().getGloss());*/
		
		//WordNet Similarity
		//System.out.println(Main.getJiangAndConrathSimilarity("apple", "banana"));
		//System.out.println(Main.getJiangAndConrathSimilarity("run", "walk"));
		
		try
		{
			JarFile jarfile = new JarFile("I:\\Kod\\Workspace\\Watson\\watson-client-api.jar");
			Manifest manifest = jarfile.getManifest();
			Map<String, Attributes> m = manifest.getEntries();
			System.out.println("okudum" + m.size());
			   Iterator it = m.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry pairs = (Map.Entry)it.next();
			        System.out.println(pairs.getKey() + " = " + pairs.getValue());
			    }
		}
		catch (Exception e)
		{
			
		}
	}

	public static ArrayList<String> getSynonyms(String wordstr) 
	{
		String path = "I:\\Kod\\Workspace\\WordNet\\WordNet-3.0\\dict";
		URL url = null;
		try {
			if (dict == null)
			{
				url = new URL("file", null, path);
				// construct the dictionary object and open it
				if (url != null)
				{
					dict = new Dictionary(url);
					dict.open();
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		ArrayList<String> sonuc = new ArrayList<String>();
		// look up first sense of the word "dog"
		IIndexWord idxWord = getNounIIndexWord(wordstr);
		if (idxWord == null) 
			return sonuc;
		for(int myi = 0; myi<idxWord.getWordIDs().size();myi++){
			IWordID wordID = idxWord.getWordIDs().get(myi); // 1st meaning
			IWord word = dict.getWord(wordID);
			ISynset synset = word.getSynset();
			for (IWord w : synset.getWords()) {
				if (wordstr.compareTo(w.getLemma()) != 0)
				{
					sonuc.add(w.getLemma());
					System.out.println("Lemma = " + w.getLemma());
				}
			}
		}
		return sonuc;
	}
	
	private static IIndexWord getNounIIndexWord(String wordStr){
		
		IIndexWord idxWord = dict.getIndexWord(wordStr, POS.NOUN);
		if (idxWord == null && wordStr.length() > 2) {
			wordStr = wordStr.substring(0, wordStr.length() - 1);
			idxWord = dict.getIndexWord(wordStr, POS.NOUN);
		}
		if (idxWord == null && wordStr.length() > 2) {
			wordStr = wordStr.substring(0, wordStr.length() - 1);
			idxWord = dict.getIndexWord(wordStr, POS.NOUN);
		}
		return idxWord;
	}
	
	private static double getJiangAndConrathSimilarity(String String1, String String2)
	{
		String dir = "I:/Kod/Workspace/WordNet";
		
		//ICFinder icf = new ICFinder("I:/Kod/Workspace/WordNet/3.0/WordNet-InfoContent-3.0/ic-bnc-resnik-add1.dat");
		//return icf.getIC("apple", "apple");
		JWS	ws = new JWS(dir, "3.0");
		JiangAndConrath jc = ws.getJiangAndConrath();
		System.out.println("noun: " + jc.max("apple", "orange", "n"));
		System.out.println("verb: " + jc.max("apple", "orange", "v"));
		System.out.println("adjective: " + jc.max("good", "bad", "a"));
		return jc.max(String1, String2, "n");
	}
}
