import sun.io.Converters;
import uk.ac.open.kmi.watson.clientapi.OntologySearch;
import uk.ac.open.kmi.watson.clientapi.OntologySearchServiceLocator;
import uk.ac.open.kmi.watson.clientapi.WatsonService;


import uk.ac.open.kmi.watson.clientapi.EntityResult;
import uk.ac.open.kmi.watson.clientapi.SemanticContentResult;
import uk.ac.open.kmi.watson.clientapi.WatsonSearch;
import uk.ac.open.kmi.watson.clientapi.WatsonSearchServiceLocator;
import uk.ac.open.kmi.watson.clientapi.WatsonService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import java.io.*;
import java.net.URL;
import java.util.Collection;

import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.BindingType;
import javax.xml.ws.Provider;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;
import javax.xml.ws.http.HTTPException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Map;
import java.net.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.codec.DecoderException;

import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;

public class OntolojiArama {

	int sayac;
	String[] res;
	OntologySearch os;
	// The service client interface
	private WatsonSearch ws;
	SemanticContentResult[] sr;
	
	public OntolojiArama()
	{
		sayac = 0;
		WatsonSearchServiceLocator locator = new WatsonSearchServiceLocator();
		try {
			//os = oslocator.getUrnOntologySearch();
			ws = locator.getUrnWatsonSearch();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getCause());
		}
	}
	
	public OntolojiArama(String String1, String String2)
	{
		sayac = 0;
		WatsonMotorundaAra(String1, String2);
	}
	
	private void WatsonMotorundaAra(String String1, String String2)
	{
		//OntologySearch os;
		OntologySearchServiceLocator locator = new OntologySearchServiceLocator();
		try
		{
			os = locator.getUrnOntologySearch();			
			String[] params = {String1, String2};
			res = os.getSemanticContentByKeywords(params);
			
			//for (String s : res) System.out.println(s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e.getCause());
		}
	}
	
	private void WatsonMotorundaAra2(String String1, String String2) throws RemoteException {
		String[] params = { String1, String2 };
		int scopeModifier =  WatsonService.LOCAL_NAME + WatsonService.LABEL ;
		int entityTypeModifier = WatsonService.CLASS + WatsonService.PROPERTY + WatsonService.INDIVIDUAL;
		int scInfo = WatsonSearch.SC_SIZE_INFO + WatsonSearch.SC_LANGUAGES_INFO;
		int entInfo = 255;
		sr = ws.searchWatsonWithKeywords(params,
				scopeModifier, entityTypeModifier, WatsonService.TOKEN_MATCH, scInfo, entInfo);
		double benzerlik = 0;
		String ontURI;
		do
		{
			ontURI = OntolojiAdresiniAl2();
			System.out.println("ontURI: " + ontURI);
			if (ontURI != "")
			{
				benzerlik = Patika.compareOntologyConcepts(ontURI, String1, String2);
				System.out.println("benzerlik: " + benzerlik);
			}
		} while (benzerlik < 0 && ontURI != "");
		//SonuclariYazdir();
	}

	public ArrayList<String> WatsonMotorundaAra3(String String1, String String2) throws RemoteException {
		String[] params = { String1, String2 };
		int scopeModifier =  WatsonService.LOCAL_NAME + WatsonService.LABEL ;
		int entityTypeModifier = WatsonService.CLASS + WatsonService.PROPERTY + WatsonService.INDIVIDUAL;
		int scInfo = WatsonSearch.SC_SIZE_INFO + WatsonSearch.SC_LANGUAGES_INFO;
		int entInfo = 255;
		sr = ws.searchWatsonWithKeywords(params,
				scopeModifier, entityTypeModifier, WatsonService.TOKEN_MATCH, scInfo, entInfo);

		String ontURI;
		ArrayList<String> ontURIs;
		ontURIs = new ArrayList<String>();
		do
		{
			ontURI = OntolojiAdresiniAl2();
			if (ontURI != "")
			{
				ontURIs.add(ontURI);
			}
		} while (ontURI != "");
		//System.out.println("ontURIs Size: " + ontURIs.size());
		return ontURIs;
	}

	private void SonuclariYazdir()
	{
		try
		{
			System.out.println("Number of results: " + sr.length);
			for (SemanticContentResult r : sr) 
			{
				System.out.println("URI:: " + r.getURI() + " (Size: " + r.getSize() + " B)");
				String[] languages = r.getLanguages();
				if (languages != null) {
					System.out.print("  Languages: ");
					for (String l : languages)
						System.out.print(l + " ");
					System.out.println();
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e.getCause());
		}
	}

	public String OntolojiAdresiniAl2()
	{
		SemanticContentResult r;
		boolean sizeFlag;
		boolean langFlag;
		boolean wantedOntFlag;
		if (sayac == sr.length)
		{
			return "";
		}
		else
		{
			for (int i = sayac; i < sr.length; i++)
			{
				r = sr[i];
				sizeFlag = true;
				langFlag = true;
				wantedOntFlag = true;
				if (r.getSize() > 65536)
					sizeFlag = false;
				String[] languages = r.getLanguages();
				if (languages != null) 
				{
					for (String l : languages)
					{
						if (l == "DAML+OIL")
							langFlag = false;
					}
				}
				if (r.getURI().contains("foaf") || r.getURI().contains("talkdigger"))
				{
					wantedOntFlag = false;
				}
				if (sizeFlag && langFlag && wantedOntFlag)
				{
					sayac = i + 1;
					return r.getURI();
				}
			}
			return "";
		}
	}
	
	public String OntolojiAdresiniAl()
	{
		if (res != null)
		{
			if (res.length > 0)
			{
				return res[sayac++];
			}
			else
			{
				return "";
			}
		}
		else
		{
			return "";
		}
	}

	public OntolojiArama(int i) {
		//OntologySearchServiceLocator oslocator = new OntologySearchServiceLocator();
		WatsonSearchServiceLocator locator = new WatsonSearchServiceLocator();
		try {
			//os = oslocator.getUrnOntologySearch();
			ws = locator.getUrnWatsonSearch();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getCause());
		}
	}

	public static void main(String[] args) {

			/*try 
			{
				String urlString = "http://owl.man.ac.uk/tutorial/twopets.rdf";
				URL url = new URL(urlString);
				int index = urlString.lastIndexOf("/");
				String fileName = urlString.substring(index + 1);
				System.out.println("Dosya adý: " + fileName);
				File destination = new File("I:\\Kod\\Workspace\\Ontologies\\" + fileName);
				// Copy bytes from the URL to the destination file.
				FileUtils.copyURLToFile(url, destination);

				
				Collection<File> found = FileUtils.listFiles(new File("I:\\Kod\\Workspace\\Ontologies\\"), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
				for (File f : found) {
				      System.out.println("Found file: " + f);
				}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}*/
		
		
		/*OntolojiArama app = new OntolojiArama();
		try {
			app.WatsonMotorundaAra3("wine", "grape");
			//app.searchComplex();
			//app.searchLimit();
			//app.bestCoverage();
		} catch (RemoteException e) {
			e.printStackTrace();
		}*/
		
		try
		{
			/*String url_str = "http://logos.cs.umbc.edu:8080/swoogle31/";
			String sonuc;
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("queryType", "search_swd_ontology");
			hm.put("searchString", "car");
			hm.put("key", "demo");
			sonuc = callRestfulWebService(url_str, hm, null, 0);
			System.out.println(sonuc);*/
			
			//System.out.println(callWebService());
			/*OntolojiArama oa = new OntolojiArama();
			ArrayList<String> arrResult;
			arrResult = oa.SwoogleMotorundaAra("person", "employee");
			for (int m = 0; m < arrResult.size(); m++)
			{
				System.out.println(arrResult.get(m).toString());
			}*/
			
			ArrayList<String> ontAramaSonuclari = new ArrayList<String>();
			ontAramaSonuclari.add("http://www.eswc2006.org/technologies/ontology-content/2006-09-21.rdf");
			OntolojiArama oa = new OntolojiArama();
			ArrayList<String> arrResult;
			arrResult = oa.OntolojiKontrol("Artefact", "agreesWith",  false, false, ontAramaSonuclari);
			for (int m = 0; m < arrResult.size(); m++)
			{
				System.out.println(arrResult.get(m).toString());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void searchComplex() throws RemoteException {
		System.out.println("***** Searching Cat and dog *****");
		String[] params = { "dog" };
		int scopeModifier =  WatsonService.LOCAL_NAME + WatsonService.LABEL + WatsonService.COMMENT + WatsonService.LITERAL;
		int entityTypeModifier = WatsonService.CLASS + WatsonService.PROPERTY + WatsonService.INDIVIDUAL;
		int scInfo = WatsonSearch.SC_DLEXPR_INFO + WatsonSearch.SC_LANGUAGES_INFO;
		int entInfo = 255;
		SemanticContentResult[] sr = ws.searchWatsonWithKeywords(params,
				scopeModifier, entityTypeModifier, WatsonService.TOKEN_MATCH, scInfo, entInfo);
		displayResult(sr);
		System.out.println(sr.length);
	}
	
	private void searchLimit() throws RemoteException {
		System.out.println("***** Searching Cat and dog with limit 10 *****");
		String[] params = { "cat", "dog" };
		int scopeModifier = WatsonService.LOCAL_NAME + WatsonService.LABEL + WatsonService.COMMENT + WatsonService.LITERAL;
		int entityTypeModifier = WatsonService.CLASS + WatsonService.PROPERTY + WatsonService.INDIVIDUAL;
		int scInfo = WatsonSearch.SC_DLEXPR_INFO + WatsonSearch.SC_SIZE_INFO + WatsonSearch.SC_LANGUAGES_INFO;
		int entInfo = WatsonSearch.ENT_TYPE_INFO + WatsonSearch.ENT_LABEL_INFO;// +ENT_ANYRELATIONTO_INFO;
		SemanticContentResult[] sr = ws.searchWatsonWithKeywordsWithLimit(params,
				scopeModifier, entityTypeModifier, WatsonService.TOKEN_MATCH, scInfo, entInfo, 10);
		displayResult(sr);
	}

	private void bestCoverage() throws RemoteException {
		System.out.println("***** Best Coverage of \"mammal man woman child god *****");
		String[] params = // { "mammal", "man", "woman", "child", "god" };
         //		 other example...
		{"artificial intelligence", 
		"information retrieval", 
		"semantic web", 
		"ontologies",
		"service", 
		"metadata", 
		"natural language processing", 
		"semantics", 
		"language", 
		"processable" };
		int scopeModifier = WatsonService.LOCAL_NAME + WatsonService.LABEL;
		int entityTypeModifier = WatsonService.CLASS;
		int scInfo = WatsonSearch.SC_DLEXPR_INFO + WatsonSearch.SC_SIZE_INFO + WatsonSearch.SC_LANGUAGES_INFO;
		int entInfo = WatsonSearch.ENT_TYPE_INFO + WatsonSearch.ENT_LABEL_INFO;// +ENT_ANYRELATIONTO_INFO;
		SemanticContentResult[] sr = ws.searchBestCoverageWithRestriction(params,
				scopeModifier, entityTypeModifier, WatsonService.EXACT_MATCH, scInfo, entInfo);
		displayResult(sr);
	}
	
	private void displayResult(SemanticContentResult[] sr) {
		System.out.println("Number of results: " + sr.length);
		for (SemanticContentResult r : sr) {
			System.out.println("SC:: " + r.getURI() + " ("
					+ r.getDLExpressivness() + ", C:" + r.getNBClasses()
					+ ", P:" + r.getNBProperties() + ", I:"
					+ r.getNBIndividuals() + r.getSize() + "B)");
			String[] languages = r.getLanguages();
			if (languages != null) {
				System.out.print("  Languages: ");
				for (String l : languages)
					System.out.print(l + " ");
				System.out.println();
			}
			String[] locations = r.getLocations();
			if (locations != null) {
				System.out.print("  Locations: ");
				for (String l : locations)
					System.out.print(l + " ");
				System.out.println();
			}
			EntityResult[] er = r.getEntityResultList();
			for (EntityResult e : er) {
				System.out.println("\t" + e.getType() + "::" + e.getURI() + "("
						+ e.getLabel() + ")");
				String[][] relFrom = e.getRelationFrom();
				if (relFrom != null)
					for (String[] rf : relFrom)
						System.out.println("\t\t" + rf[1] + " -- " + rf[2]);
				String[][] relTo = e.getRelationTo();
				if (relTo != null)
					for (String[] rt : relTo)
						System.out.println("\t\t" + rt[2] + " -- " + rt[1]);
				String[][] literals = e.getLiterals();
				if (literals != null)
					for (String[] l : literals)
						System.out.println("\t\t" + l[1] + " = " + l[2]);
			}
		}

	}

	public ArrayList<String> SwoogleMotorundaAra(String String1, String String2)
	{
		ArrayList<String> sonuclar;
		ArrayList<String> esanlamlilar;
		sonuclar = SwoogleMotorundaAra2(String1, String2, false, false);
		if ((sonuclar == null) || (sonuclar.size() == 0))
		{
			//1. kelime icin
			esanlamlilar = this.getSynonyms(String1);
			if (esanlamlilar != null)
			{
				for (int i = 0; i < esanlamlilar.size(); i++)
				{
					sonuclar = SwoogleMotorundaAra2(esanlamlilar.get(i), String2, true, false);
					if ((sonuclar != null) && (sonuclar.size() > 0))
					{
						return sonuclar;
					}
				}
			}
			
			//2. kelime icin
			esanlamlilar = this.getSynonyms(String2);
			if (esanlamlilar != null)
			{
				for (int i = 0; i < esanlamlilar.size(); i++)
				{
					sonuclar = SwoogleMotorundaAra2(String1, esanlamlilar.get(i), false, true);
					if ((sonuclar != null) && (sonuclar.size() > 0))
					{
						return sonuclar;
					}
				}
			}
		}
		return sonuclar;
	}
	
    public ArrayList<String> SwoogleMotorundaAra2(String String1, String String2, boolean String1_Wordnet, boolean String2_Wordnet)
    {
    	//String request = "http://api.search.yahoo.com/WebSearchService/V1/webSearch?appid=YahooDemo&query=umbrella&results=10";
    	String request = "http://logos.cs.umbc.edu:8080/swoogle31/q?queryType=search_swd_ontology&searchString=" + String1 + "+" + String2 + "&key=demo";
    	//String request = "http://logos.cs.umbc.edu:8080/swoogle31/q?queryType=search_swd_ontology&searchString=" + String1 + "+" + String2 + "&key=3a3844b70760d9a14553148449d62eea";
    	String result = "";
    	
    	try
    	{
	    	HttpClient client = new HttpClient();
	        GetMethod method = new GetMethod(request);
			// Send GET request
	        int statusCode = client.executeMethod(method);
	        
	        if (statusCode != HttpStatus.SC_OK) {
	        	System.err.println("Method failed: " + method.getStatusLine());
	        }
	        InputStream rstream = null;
	        
	        // Get the response body
	        rstream = method.getResponseBodyAsStream();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(rstream));
	        String line;
	        while ((line = br.readLine()) != null) {
	            result = result + line;
	        	//System.out.println(line);
	        }
	        br.close();
    	}
		catch (Exception e) {
			//e.printStackTrace();
		}
        //return OntolojiKontrol(String1, String2, String1_Wordnet, String2_Wordnet, parseSwoogleXML(result));
        return parseSwoogleXML(result);
    }

    public ArrayList<String> parseSwoogleXML(String xmlString)
    {
    	ArrayList<String> arrURLs = new ArrayList<String>();
        try {
            DocumentBuilderFactory dbf =
                DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlString));

            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName("wob:SemanticWebDocument");

            // iterate the employees
            for (int i = 0; i < nodes.getLength(); i++) {
               Element element = (Element) nodes.item(i);

               NodeList ftype = element.getElementsByTagName("swoogle:hasFiletype");
               Element line = (Element) ftype.item(0);

               NodeList fsize = element.getElementsByTagName("swoogle:hasLength");
               Element line1 = (Element) fsize.item(0);
               
               String fileType = getCharacterDataFromElement(line);
               String tmpFileSize;
               tmpFileSize = getCharacterDataFromElement(line1);
               long fileSize;
               if (tmpFileSize.compareTo("---") != 0)
               {
            	   fileSize = Long.parseLong(tmpFileSize);
               }
               else
               {
            	   fileSize = 0;
               }
               if ((fileType.compareTo("owl") == 0 || fileType.compareTo("rdf") == 0) && fileSize < 65536)
               {
            	   arrURLs.add(element.getAttribute("rdf:about"));
            	   //System.out.println("swoogle:hasFiletype: " + fileType);
	               //System.out.println("rdf:about: " + element.getAttribute("rdf:about"));
               }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return arrURLs;
    }
    
    public static String getCharacterDataFromElement(Element e) 
    {
        org.w3c.dom.Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
           CharacterData cd = (CharacterData) child;
           return cd.getData();
        }
        return "?";
    }
    
    private ArrayList<String> OntolojiKontrol(String String1, String String2, boolean String1_Wordnet, boolean String2_Wordnet, ArrayList<String> ontAramaSonuclari)
    {
    	OntModel model;
        model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
        //model.getDocumentManager().addAltEntry( ontolojiDokumaniURI, ontolojiDokumaniYeri );
    	boolean String1Bulundu = false;
    	boolean String2Bulundu = false;
    	com.hp.hpl.jena.rdf.model.Resource tmpRes;
    	ArrayList<String> tmpOnt = new ArrayList<String>();
        devam:
        for (int i = 0; i < ontAramaSonuclari.size(); i++)
    	{
    		try
    		{
    			model.read(ontAramaSonuclari.get(i));
    	    	Statement ifade;
    	    	Iterator<Statement> gecici = model.listStatements();
    	    	String1Bulundu = false;
    	    	String2Bulundu = false;
    	    	if(gecici.hasNext()) 
    	    	{
    		        while (gecici.hasNext()) 
    		        {
    		        	ifade = (Statement)gecici.next();
    		        	if (!String1Bulundu)
    		        	{
	    		        	if (ifade.getSubject().getLocalName().compareTo(String1) == 0)
	    		        	{
	    		        		String1Bulundu = true;
	    		        	}
	    		        	else
	    		        	{
	    		        		if (ifade.getObject().isResource())
	    		        		{
	    		        			tmpRes = (com.hp.hpl.jena.rdf.model.Resource)ifade.getObject();
	    		        			if (tmpRes.getLocalName().compareTo(String1) == 0)
	    	    		        	{
	    	    		        		String1Bulundu = true;
	    	    		        	}
	    		        		}
	    		        	}
    		        	}
    		        	if (!String2Bulundu)
    		        	{
	    		        	if (ifade.getSubject().getLocalName().compareTo(String2) == 0)
	    		        	{
	    		        		String2Bulundu = true;
	    		        	}
	    		        	else
	    		        	{
	    		        		if (ifade.getObject().isResource())
	    		        		{
	    		        			tmpRes = (com.hp.hpl.jena.rdf.model.Resource)ifade.getObject();
	    		        			if (tmpRes.getLocalName().compareTo(String2) == 0)
	    	    		        	{
	    	    		        		String2Bulundu = true;
	    	    		        	}
	    		        		}
	    		        	}
    		        	}
    		        	if (String1Bulundu && String2Bulundu)
    		        	{
    		        		if (String1_Wordnet)
    		        		{
    		        			tmpOnt.add("String1_Wordnet: " + String1 + " - " + ontAramaSonuclari.get(i));
    		        		}
    		        		else if (String2_Wordnet)
    		        		{
    		        			tmpOnt.add("String2_Wordnet: " + String2 + " - " + ontAramaSonuclari.get(i));
    		        		}
    		        		else
    		        		{
    		        			tmpOnt.add(ontAramaSonuclari.get(i));
    		        		}
    		        		break;
    		        	}
    		        }
    	    	}
    		}
            catch (Exception e) 
            {
                continue devam;
            }
    	}
		return tmpOnt;
    }
    
	IDictionary dict;
	public ArrayList<String> getSynonyms(String wordstr) 
	{
		String path = "I:\\Kod\\Workspace\\WordNet\\3.0\\dict";
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
					//System.out.println("Lemma = " + w.getLemma());
				}
			}
		}
		return sonuc;
	}
	
	private IIndexWord getNounIIndexWord(String wordStr){
		
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

}
