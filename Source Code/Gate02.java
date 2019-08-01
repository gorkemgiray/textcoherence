//import java.awt.RadialGradientPaint;
import java.io.File;
//import java.net.URL;
import java.util.*;
import java.lang.reflect.Constructor;

import gate.*;
import gate.creole.*;
import gate.creole.annotdelete.AnnotationDeletePR;
import gate.creole.gazetteer.FlexibleGazetteer;
import gate.creole.gazetteer.Gazetteer;
//import gate.creole.gazetteer.OntoGazetteer;
import gate.creole.morph.Morph;
import gate.creole.splitter.RegexSentenceSplitter;
//import gate.creole.splitter.SentenceSplitter;
import gate.creole.tokeniser.DefaultTokeniser;
import gate.gui.*;
import gate.creole.ontology.*;
import gate.util.OffsetComparator;
//import owlim.*;
//import java.rmi.RemoteException;

import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import uk.ac.open.kmi.watson.clientapi.OntologySearch;
import uk.ac.open.kmi.watson.clientapi.OntologySearchServiceLocator;
//import uk.ac.open.kmi.watson.clientapi.WatsonService;

import simpack.accessor.graph.JenaOntologyAccessor;
import simpack.accessor.string.StringAccessor;
//import simpack.api.IGraphAccessor;
import simpack.api.ISequenceAccessor;
import simpack.api.impl.*;
//import simpack.measure.external.secondstring.Jaccard;
//import simpack.measure.external.simmetrics.DiceSimilarity;
//import simpack.measure.external.simmetrics.JaccardSimilarity;
//import simpack.measure.graph.GraphIsomorphism;
//import simpack.measure.graph.MaxCommonSubgraphIsoValiente;
import simpack.measure.it.JiangConrath;
import simpack.measure.it.Lin;
import simpack.measure.it.Resnik;
//import simpack.tests.measure.external.secondstring.JaccardTest;
import simpack.tokenizer.SplittedStringTokenizer;
import simpack.measure.string.Jaro;

import com.hp.hpl.jena.ontology.OntModel;
//import com.hp.hpl.jena.ontology.OntModelSpec;

public class Gate02 {

	/**
	 * @param args
	 */

	static Document[] docs;
	public static void GetDocuments()
	{
		try
		{
			docs = new Document[2];
			docs[0] = Factory.newDocument(new File("I:\\Kod\\Workspace\\DUC\\D100.M.100.A.H").toURL());
			docs[1] = Factory.newDocument(new File("I:\\Kod\\Workspace\\DUC\\D101.M.100.A.H").toURL());
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	public static void WatsonSearch()
	{
		OntologySearch os;
		OntologySearchServiceLocator locator = new OntologySearchServiceLocator();
		try
		{
			os = locator.getUrnOntologySearch();			
			System.out.println("***** Searching Cat and Dog *****");
			String[] params = {"cat", "dog"};
			String[] res = os.getSemanticContentByKeywords(params);
			for (String s : res) System.out.println(s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e.getCause());
		}
	}
	
	public static double compareStringsResnik()
	{
		double r_sim = 0;
		try
		{
			OntModel model;
			model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM ); //cikarsamasiz
	        model.read("file:///I://Kod//Workspace//deneme.owl");
	        JenaOntologyAccessor joa = new JenaOntologyAccessor("file:///I://Kod//Workspace//deneme.owl", "file:///I://Kod//Workspace//deneme.owl", null, null, OntModelSpec.OWL_MEM);
	        //System.out.println(joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#US_President").getLabel());
	        //Resnik r = new Resnik(joa, joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#Obama1"), joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#US_President"));
	        Resnik r = new Resnik(joa, joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#WineColor"), joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#TasimaAraci"));
	        //System.out.println(joa.getMostRecentCommonAncestor(joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#WineColor"), joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#US_President")).getLabel());
	        //System.out.println(joa.getMostRecentCommonAncestor(joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#PrincipalResidentOfWhiteHouse"), joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#US_President")).getLabel());
	        r_sim = r.getSimilarity();
	        System.out.println("Resnik sim: " + r_sim);
	        
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("Baþarýlý");
		return r_sim;
	}
	
	public static double compareStringsLin()
	{
		double l_sim = 0;
		try
		{
	        JenaOntologyAccessor joa = new JenaOntologyAccessor("file:///I://Kod//Workspace//deneme.owl", "file:///I://Kod//Workspace//deneme.owl", null, null, OntModelSpec.OWL_MEM);
	        //System.out.println(joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#US_President").getLabel());
	        //Resnik r = new Resnik(joa, joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#Obama1"), joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#US_President"));
	        Lin l = new Lin(joa, joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#US_President"), joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#PrincipalResidentOfWhiteHouse"));
	        //System.out.println(joa.getMostRecentCommonAncestor(joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#WineColor"), joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#US_President")).getLabel());
	        //System.out.println(joa.getMostRecentCommonAncestor(joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#PrincipalResidentOfWhiteHouse"), joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#US_President")).getLabel());
	        l_sim = l.getSimilarity();
	        System.out.println("Lin sim: " + l_sim);
	        
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("Baþarýlý");
		return l_sim;
	}
	
	public static double compareStringsJC()
	{
		double jc_sim = 0;
		try
		{
	        JenaOntologyAccessor joa = new JenaOntologyAccessor("file:///I://Kod//Workspace//deneme.owl", "file:///I://Kod//Workspace//deneme.owl", null, null, OntModelSpec.OWL_MEM);
	        //System.out.println(joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#US_President").getLabel());
	        //Resnik r = new Resnik(joa, joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#Obama1"), joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#US_President"));
	        JiangConrath jc = new JiangConrath(joa, joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#Obama2"), joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#PrincipalResidentOfWhiteHouse"));
	        //System.out.println(joa.getMostRecentCommonAncestor(joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#WineColor"), joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#US_President")).getLabel());
	        //System.out.println(joa.getMostRecentCommonAncestor(joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#PrincipalResidentOfWhiteHouse"), joa.getNode("http://www.w3.org/2001/sw/WebOnt/guide-src/wine#US_President")).getLabel());
	        jc_sim = jc.getSimilarity();
	        System.out.println("J&C sim: " + jc_sim);
	        
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("Baþarýlý");
		return jc_sim;
	}
	
    public static double compareStrings(String method, String s1, String s2) {
        StringAccessor a1 = new StringAccessor(s1, new SplittedStringTokenizer(" "));
        StringAccessor a2 = new StringAccessor(s2, new SplittedStringTokenizer(" "));
        double similarity;
        try {
        	Jaro j = new Jaro(new StringAccessor("dogs"), new StringAccessor("dog"));
        	System.out.println("Jaro sim: " + j.getSimilarity());
        	//DiceSimilarity dice = new DiceSimilarity("cat", "dog");
        	//System.out.println("Dice sim: " + dice.getSimilarity());
        	//Jaccard js = new Jaccard(a1, a2);
        	//System.out.println("Jaccard sim: " + js.getSimilarity());
            	System.out.println("A");
                Class clazz = Class.forName(method);            
                Constructor con;
                AbstractSimilarityMeasure similaritymeasure = null;
                try {
                	System.out.println("B");
                        con = clazz.getConstructor(new Class[]{ISequenceAccessor.class, ISequenceAccessor.class});
                        similaritymeasure = (AbstractSimilarityMeasure) con.newInstance(a1, a2);
                        

                } 
                catch (NoSuchMethodException e) {
                	try {
                        	System.out.println("C");
                                con = clazz.getConstructor(new Class[]{String.class, String.class});
                                similaritymeasure = (AbstractSimilarityMeasure) con.newInstance(s1, s2);
                        } catch (NoSuchMethodException e1) {
                                // hmmm.. shouldn't happen
                                similarity = -1;
                                System.out.println("sim:-1");
                        }
                }
            	System.out.println("D");
                similarity = similaritymeasure.getSimilarity();
        } catch (Exception e) {
                // ups...
        	System.out.println(e.toString());    
                similarity = 0;
        }
        return similarity;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Merhaba");
		try
		{
			//WatsonSearch();
			
			//System.out.println("Similarity: " + compareStrings("simpack.measure.it.Resnik", "dogs", "dog"));
			
			compareStringsResnik();
			compareStringsLin();
			compareStringsJC();
			
			Gate.setGateHome(new File("C:\\Program Files\\GATE-5.2.1\\"));
			//Gate.setUserConfigFile(new File("I:\\Kod\\Workspace\\Tez01\\src\\gate.xml"));
			Gate.init();
			showGUI();
			
			Document doc = Factory.newDocument("I have a project. There is a plan for it. There is lots of developers working for it. There is an otobus. Mr and Mrs. Brown are in this project.");
			Document doc11 = doc;
			Corpus corp = Factory.newCorpus("A Corpus");
			corp.add(doc);
			doc = Factory.newDocument(new File("I:\\Kod\\Workspace\\KodAciklama.txt").toURL());
			corp.add(doc);
			//Load an ontology
			FeatureMap fm = Factory.newFeatureMap(); 
			fm.put("rdfXmlURL", new File("I:\\Kod\\Workspace\\Software.owl").toURL()); 
			//fm.put("baseURI", theBaseURI); 
			//fm.put("mappingsURL", urlOfTheMappingsFile); 
			Ontology ont = (Ontology)
				Factory.createResource("gate.creole.ontology.impl.sesame.OWLIMOntology", fm); 
			//corp.add(ont);
			//2. ontoloji
			fm.clear();
			fm.put("rdfXmlURL", new File("I:\\Kod\\Workspace\\deneme1.owl").toURL()); 
			Ontology ont2 = (Ontology)
				Factory.createResource("gate.creole.ontology.impl.sesame.OWLIMOntology", fm); 
			
			
			//create a controller (a.k.a GATE application)
			SerialAnalyserController controller = (SerialAnalyserController)
				Factory.createResource("gate.creole.SerialAnalyserController");

			//create an annotation delete PR
			AnnotationDeletePR annotdelPR = 
				(AnnotationDeletePR)Factory.createResource("gate.creole.annotdelete.AnnotationDeletePR");
			controller.add(annotdelPR);
			
			//create a RegexSentenceSplitter
			RegexSentenceSplitter regexsensip =
				(RegexSentenceSplitter)Factory.createResource("gate.creole.splitter.RegexSentenceSplitter");
			controller.add(regexsensip);
			
			FeatureMap params = Factory.newFeatureMap();
			FeatureMap features = Factory.newFeatureMap();
			//features.put("foo", "bar");
			//create a Tokenizer
			DefaultTokeniser tok = (DefaultTokeniser)Factory.createResource("gate.creole.tokeniser.DefaultTokeniser", params, features, "A Tokeniser");
			controller.add(tok);
			
			//create a POSTagger
			POSTagger postagger = 
				(POSTagger)Factory.createResource("gate.creole.POSTagger");
			controller.add(postagger);
			
			//
			Morph morph =
				(Morph)Factory.createResource("gate.creole.morph.Morph");
			controller.add(morph);
			
			//create an onto root gazetteer
			params.put("morpher", morph);
			params.put("ontology", ont);
			params.put("posTagger", postagger);
			params.put("tokeniser", tok);
			Gazetteer ontogaz =
				(Gazetteer)Factory.createResource("gate.clone.ql.OntoRootGaz", params);

			controller.add(ontogaz);
			
			//create an onto root gazetteer 2
			params.remove("ontology");
			params.put("ontology", ont2);
			Gazetteer ontogaz2 =
				(Gazetteer)Factory.createResource("gate.clone.ql.OntoRootGaz", params);

			controller.add(ontogaz2);
			
			//create a flexible gazetteer
			params.clear();
			params.put("gazetteerInst", ontogaz);
			FlexibleGazetteer flexgaz =
				(FlexibleGazetteer)Factory.createResource("gate.creole.gazetteer.FlexibleGazetteer", params);
			controller.add(flexgaz);
			
			//
			ANNIETransducer annieNETransducer =
				(ANNIETransducer)Factory.createResource("gate.creole.ANNIETransducer");
			controller.add(annieNETransducer);
			
			controller.setCorpus(corp);
			controller.execute();

			//get annotation set
			AnnotationSet annSet = doc11.getAnnotations();
/*			String type = "Token";
			AnnotationSet annUnknownSet = annSet.get(type);
			List unknownList = new ArrayList(annUnknownSet);
			
			Iterator unknownIter = unknownList.iterator();
			while(unknownIter.hasNext())
			{
				//System.out.println(unknownIter.toString());
				System.out.println(((FeatureMap)(((Annotation)unknownIter.next()).getFeatures())).get("category").toString());
				
			}
*/
			/*for (int i = 0; i <= 9; i++)
			{
				System.out.println(((FeatureMap)(((Annotation)unknownList.get(i)).getFeatures())).get("root").toString());
			}*/
			//get annotation set bitti

			//get annotation set for sentences
			/*type = "Sentence";
			AnnotationSet annSetSent = annSet.get(type);
			List sentList = new ArrayList(annSetSent);
			
			System.out.println(sentList.size());
			Iterator sentListIter = sentList.iterator();
			while(sentListIter.hasNext())
			{
				FeatureMap fm1 = (FeatureMap)(((Annotation)sentListIter.next()).getFeatures());
				Set s = fm1.keySet();
				Iterator iter = s.iterator();
				while(iter.hasNext())
				{
					System.out.println(iter.next().toString());
				}
				//System.out.println("-");
				//System.out.println(((FeatureMap)(((Annotation)sentListIter.next()).getFeatures())).get("StartNode").toString());
				
			}*/
			//get annotation set for sentences bitti
			
			AnnotationSet sentencesAS = doc11.getAnnotations().get("Sentence");
			System.out.println(sentencesAS.size());
			if(sentencesAS != null && sentencesAS.size() > 0)
			{
				//define a comparator for annotations by start offset
				Comparator offsetComparator = new OffsetComparator();
				
				//read all the tokens and all the sentences
				List sentencesList = new ArrayList(sentencesAS);
				Collections.sort(sentencesList, offsetComparator);

				Iterator sentencesIter = sentencesList.iterator();
				int h = 1;
				while(sentencesIter.hasNext())
				{
					System.out.println("Sentence: " + h++);
					Annotation currentSentence = (Annotation)sentencesIter.next();
					//get tokens of the current sentence
					java.util.List<Annotation> tokenslist = new java.util.LinkedList<gate.Annotation>(annSet.get("Token", currentSentence.getStartNode().getOffset(), currentSentence.getEndNode().getOffset()));
					//sort the token list
					Collections.sort(tokenslist, new OffsetComparator());
					//build the token list
					String[][] tokens = new String[tokenslist.size()][2];
					System.out.println(tokenslist.size());
					int k = 0;
					for (Annotation token : tokenslist) {
						tokens[k][0] = (String)token.getFeatures().get("string");
						tokens[k][1] = (String)token.getFeatures().get("category");
						System.out.println(tokens[k][0]);
						System.out.println(tokens[k][1]);
						k++;
					}
					//System.out.println(currentSentence.toString());
					//System.out.println(currentSentence.getFeatures().get("StartNode"));
				}
			}
			else
			{
				System.out.println("No sentences were found in the document");
			}

			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
	}

	public static void showGUI()
	{
		MainFrame mainFrame = new MainFrame();
		mainFrame.setSize(800, 600);
		mainFrame.setVisible(true);
	}
	
	public static void test1(Corpus corp) throws Exception
	{
		//create a controller (a.k.a GATE application)
		SerialAnalyserController controller = (SerialAnalyserController)
			Factory.createResource("gate.creole.SerialAnalyserController");

		//create an annotation delete PR
		AnnotationDeletePR annotdelPR = 
			(AnnotationDeletePR)Factory.createResource("gate.creole.annotdelete.AnnotationDeletePR");
		controller.add(annotdelPR);
		
		//create a RegexSentenceSplitter
		RegexSentenceSplitter regexsensip =
			(RegexSentenceSplitter)Factory.createResource("gate.creole.splitter.RegexSentenceSplitter");
		controller.add(regexsensip);
		
		FeatureMap params = Factory.newFeatureMap();
		FeatureMap features = Factory.newFeatureMap();
		//features.put("foo", "bar");
		//create a Tokenizer
		DefaultTokeniser tok = (DefaultTokeniser)Factory.createResource("gate.creole.tokeniser.DefaultTokeniser", params, features, "A Tokeniser");
		controller.add(tok);
		
		//run the Tokeniser
		//tok.setDocument(doc);
		//tok.execute();
		
		//create a POSTagger
		POSTagger postagger = 
			(POSTagger)Factory.createResource("gate.creole.POSTagger");
		controller.add(postagger);
		
		//
		Morph morph =
			(Morph)Factory.createResource("gate.creole.morph.Morph");
		controller.add(morph);
		
		//create an onto root gazetteer
		Gazetteer gaz =
			(Gazetteer)Factory.createResource("gate.clone.ql.OntoRootGaz");
		params.put("morpher", morph);
		params.put("ontology", morph);
		params.put("posTagger", postagger);
		params.put("tokeniser", tok);

		controller.add(gaz);
		
		//create a flexible gazetteer
		FlexibleGazetteer flexgaz =
			(FlexibleGazetteer)Factory.createResource("gate.creole.gazzetteer.FlexibleGazetteer");
		controller.add(flexgaz);
		
		//run the tokeniser over the whole corpus
		//create a sentence splitter
		//SentenceSplitter splitter = (SentenceSplitter)Factory.createResource("gate.creole.splitter.SentenceSplitter");
		//controller.add(splitter);
		
		controller.setCorpus(corp);
		controller.execute();
	}
}
