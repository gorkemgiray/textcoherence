import java.io.*;
//import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import simpack.accessor.graph.JenaOntologyAccessor;
import simpack.api.IGraphNode;
import simpack.measure.graph.ConceptualSimilarity;
import simpack.measure.graph.ScaledShortestPath;
import simpack.measure.it.Resnik;
import simpack.measure.it.JiangConrath;
import simpack.measure.it.Lin;

//import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;

import edu.sussex.nlp.jws.ICFinder;
import edu.sussex.nlp.jws.JWS;
import edu.sussex.nlp.jws.*;
//import com.hp.hpl.jena.rdf.model.ModelFactory;

import gate.*;
import gate.creole.*;
import gate.creole.annotdelete.AnnotationDeletePR;
import gate.creole.gazetteer.FlexibleGazetteer;
import gate.creole.gazetteer.Gazetteer;
//import gate.creole.gazetteer.OntoGazetteer;
import gate.creole.morph.Morph;
import gate.creole.ontology.Ontology;
import gate.creole.splitter.RegexSentenceSplitter;
//import gate.creole.splitter.SentenceSplitter;
import gate.creole.tokeniser.DefaultTokeniser;
import gate.gui.*;
import gate.util.OffsetComparator;

public class BagdasiklikOlcumu {

	/**
	 * @param args
	 */
	static BufferedWriter yaz;
	static String ontURI;
	public static void main(String[] args) {
		try
		{
			GetDocuments();
			//yaz = new BufferedWriter(new FileWriter("I:\\Kod\\Workspace\\Deney3\\Metin_sonuc.txt", true));
			yaz = new BufferedWriter(new FileWriter("D:\\Metin_sonuc.txt", true));
			//yaz.write("basladi");
			for (int m = 0; m < docs.length; m++)
			{
				//System.out.println("doc: " + docs[m].getName());
				//yaz.write("doc: " + docs[m].getName());
	        	//yaz.newLine();
				//parseDocuments2(docs[m]);
				//ontURI = "http://localhost/Deney3/D307.M.250.B.C/D307.M.250.B.C_All.owl";
				//ontURI = "http://localhost/Deney3/Document2/photography.owl";
				ontURI = "http://localhost/Deney3/Document1/movie.owl";
				//ontURI = "http://localhost/Deney3/Deneme/Deneme_All.owl";
				//ontURI = "http://localhost/Deney3/D30028.M.100.T.F/D30028.M.100.T.F_All.owl";
				//ontURI = "http://localhost/Deney3/Document9/scf.rdf";
				//ontURI = "http://www.co-ode.org/ontologies/photography/photography.owl";
				parseDocuments(docs[m]);
			}
	    	//yaz.close();
	    	//System.out.println("yazma islemi bitti");
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}

	}

	static Document[] docs;
	//static Document doc;
	public static void GetDocuments()
	{
		try
		{
			//Gate.setUserConfigFile(new File("I:\\Kod\\Workspace\\Tez01\\src\\gate.xml"));
			Gate.setGateHome(new File("C:\\Program Files\\GATE-5.2.1\\"));
			Gate.init();
			docs = new Document[1];
			//doc = Factory.newDocument("Scientists believe schizophrenia is produced by hereditary pre-disposition and environmental trigger, perhaps during fetal development (viral infection, malnutrition, delivery trauma, etc.). Possibly involved are abnormalities in Chromosomes 1 and 13; deficiencies in the sensation-processing thalamus; and ower brain systems, suggested by schizophrenics' smaller olfactory bulbs. Schizophrenics' increased palm wrinkles and fingerprint ridge dissociation could aid diagnosis. (Skin develops simultaneously with the nervous system and could reflect abnormalities.) Pre-diagnosed adolescents score low on intellectual and socialization tests. Genetic testing could make early treatment possible. A pulsing magnetic field directed to a small area of the brain dispels some imaginary voices. A link between schizophrenia and smoking suggests treatments utilizing nicotine-receptive pathways.");
			//docs[0] = Factory.newDocument(new File("I:\\Kod\\Workspace\\DUC\\D100M100AH.txt").toURL());
			//docs[0] = Factory.newDocument(new File("I:\\Kod\\Workspace\\Deney3\\SweetDoc3.txt").toURL());
			//docs[0] = Factory.newDocument(new File("I:\\Kod\\Workspace\\DUC\\KodAciklama.txt").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\models\\D301.M.250.I.A").toURL());
			
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\models\\D301.M.250.I.B").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\models\\D301.M.250.I.C").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\models\\D301.M.250.I.I").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\models\\D307.M.250.B.A").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\models\\D307.M.250.B.B").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\models\\D307.M.250.B.C").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\models\\D307.M.250.B.D").toURL());
			//YOK docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\models\\D311.M.250.I.B").toURL());
			//YOK docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\models\\D311.M.250.I.C").toURL());
			//YOK docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\models\\D311.M.250.I.D").toURL());
			//YOK docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\models\\D311.M.250.I.E").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D301.M.250.I.2").toURL());
			//YOK docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D301.M.250.I.4").toURL());
			//YOK docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D301.M.250.I.5").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D301.M.250.I.6").toURL());
			//YOK docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D301.M.250.I.8").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D301.M.250.I.9").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D307.M.250.B.1").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D324.M.250.E.2").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D431.M.250.H.1").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D695.M.250.C.16").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D436.M.250.J.1").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D343.M.250.C.14").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D313.M.250.E.1").toURL());
			//docs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D357.M.250.I.2").toURL());
			
			//docs[0] = Factory.newDocument(new File("I:\\Kod\\Workspace\\Deney4\\Belge2_I.txt").toURL());

			//YOKdocs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D438.M.250.G.23").toURL());
			//YOKdocs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D385.M.250.G.20").toURL());
			//YOKdocs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D383.M.250.J.20").toURL());
			//YOKdocs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D357.M.250.I.23").toURL());
			//YOKdocs[0] = Factory.newDocument(new File("D:\\Gorkem\\NIST\\2005\\results\\ROUGE\\peers\\D354.M.250.C.E").toURL());
			
			
			//Diger Metinler
			docs[0] = Factory.newDocument(new File("I:\\Kod\\Workspace\\Bagdasiklik_Deneyi\\sum-docs3\\D31050.M.100.T.F.txt").toURL());
			/*docs[1] = Factory.newDocument(new File("I:\\Kod\\Workspace\\Bagdasiklik_Deneyi\\sum-docs3\\D31033.M.100.T.13.txt").toURL());
			docs[2] = Factory.newDocument(new File("I:\\Kod\\Workspace\\Bagdasiklik_Deneyi\\sum-docs3\\D31033.M.100.T.16.txt").toURL());
			docs[3] = Factory.newDocument(new File("I:\\Kod\\Workspace\\Bagdasiklik_Deneyi\\sum-docs3\\D31033.M.100.T.18.txt").toURL());
			docs[4] = Factory.newDocument(new File("I:\\Kod\\Workspace\\Bagdasiklik_Deneyi\\sum-docs3\\D31041.M.100.T.13.txt").toURL());
			docs[5] = Factory.newDocument(new File("I:\\Kod\\Workspace\\Bagdasiklik_Deneyi\\sum-docs3\\D31041.M.100.T.16.txt").toURL());
			docs[6] = Factory.newDocument(new File("I:\\Kod\\Workspace\\Bagdasiklik_Deneyi\\sum-docs3\\D31041.M.100.T.6.txt").toURL());
			docs[7] = Factory.newDocument(new File("I:\\Kod\\Workspace\\Bagdasiklik_Deneyi\\sum-docs3\\D31050.M.100.T.13.txt").toURL());
			*/
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	static Corpus corp;
	static SerialAnalyserController controller;
	public static void parseDocuments(Document doc)
	{
		try
		{
			showGUI();
			System.out.println("doc: " + doc.getName());
			corp = Factory.newCorpus("My Corpus");
			//corp.add(docs[0]);
			corp.add(doc);
			
			//create a controller (a.k.a GATE application)
			controller = (SerialAnalyserController)
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
			
			FeatureMap fm = Factory.newFeatureMap();
			fm.put("rdfXmlURL", new URL(ontURI));
			//Ontology ont = (Ontology)
			  //Factory.createResource("gate.creole.ontology.impl.sesame.OWLIMOntology", fm); 
			Ontology ont = (Ontology)
				Factory.createResource("gate.creole.ontology.owlim.OWLIMOntologyLR", fm); 

			//create an onto root gazetteer
			params.put("morpher", morph);
			params.put("ontology", ont);
			params.put("posTagger", postagger);
			params.put("tokeniser", tok);
			Gazetteer ontogaz =
				(Gazetteer)Factory.createResource("gate.clone.ql.OntoRootGaz", params);
			controller.add(ontogaz);
			
			//create a flexible gazetteer
			params.clear();
			params.put("gazetteerInst", ontogaz);
			List<String> l = new ArrayList<String>();
			l.add("Token.root");
			l.add("Token.string");
			params.put("inputFeatureNames", l);
			FlexibleGazetteer flexgaz =
				(FlexibleGazetteer)Factory.createResource("gate.creole.gazetteer.FlexibleGazetteer", params);
			controller.add(flexgaz);

			controller.setCorpus(corp);
			controller.execute();

			//Lookup yapisini olustur
			//AnnotationSet labelsAS = docs[0].getAnnotations().get("Lookup");
			AnnotationSet labelsAS = doc.getAnnotations().get("Lookup");
			String[][] URI_type = null;
			long[][] label_start_end = null;
			if(labelsAS != null && labelsAS.size() > 0)
			{
				//define a comparator for annotations by start offset
				Comparator offsetComparator = new OffsetComparator();

				List labelsList = new ArrayList(labelsAS);
				Collections.sort(labelsList, offsetComparator);
				
				Iterator labelsIter = labelsList.iterator();
				//String[][] tokens = new String[tokenslist.size()][2];
				URI_type = new String[labelsList.size()][2];
				label_start_end = new long[labelsList.size()][2];
				int k = 0;
				while(labelsIter.hasNext())
				{
					Annotation currentLabel = (Annotation)labelsIter.next();
					URI_type[k][0] = (String)currentLabel.getFeatures().get("URI");
					URI_type[k][1] = (String)currentLabel.getFeatures().get("type");
					label_start_end[k][0] = (long)currentLabel.getStartNode().getOffset();
					label_start_end[k][1] = (long)currentLabel.getEndNode().getOffset();
					k++;
				}
			}

			//Cumle veri yapisini olustur
			//AnnotationSet sentencesAS = docs[0].getAnnotations().get("Sentence");
			AnnotationSet sentencesAS = doc.getAnnotations().get("Sentence");
			//System.out.println(sentencesAS.size());
			ArrayList<CumleDugum> arrCumleler = new ArrayList<CumleDugum>();
			CumleDugum simdikiCumle;
			int cumleIndeks = 1;
			long kelimeBas = 0;
			long kelimeBit = 0;
			int aramaDevam = 0;
			String kelime = "";
			String kelimeKategori = "";
			if(sentencesAS != null && sentencesAS.size() > 0)
			{
				//define a comparator for annotations by start offset
				Comparator offsetComparator = new OffsetComparator();
				
				//read all the tokens and all the sentences
				List sentencesList = new ArrayList(sentencesAS);
				Collections.sort(sentencesList, offsetComparator);

				Iterator sentencesIter = sentencesList.iterator();
				while(sentencesIter.hasNext())
				{
					Annotation currentSentence = (Annotation)sentencesIter.next();
					simdikiCumle = new CumleDugum(cumleIndeks, currentSentence.getStartNode().getOffset(), currentSentence.getEndNode().getOffset());
					arrCumleler.add(simdikiCumle);
					//get tokens of the current sentence
					//java.util.List<Annotation> tokenslist = new java.util.LinkedList<gate.Annotation>(docs[0].getAnnotations().get("Token", currentSentence.getStartNode().getOffset(), currentSentence.getEndNode().getOffset()));
					java.util.List<Annotation> tokenslist = new java.util.LinkedList<gate.Annotation>(doc.getAnnotations().get("Token", currentSentence.getStartNode().getOffset(), currentSentence.getEndNode().getOffset()));
					//sort the token list
					Collections.sort(tokenslist, new OffsetComparator());
					//build the token list
					for (Annotation token : tokenslist) 
					{
						 /*  Set of Noun categories supported by the POS tagger:
					      NN - noun - singular or mass
					      NNP - proper noun - singular: All words in names usually are capitalized 
							but titles might not be.
					      NNPS - proper noun - plural: All words in names usually are capitalized 
							but titles might not be.
					      NNS - noun - plural
					      NP - proper noun - singular
					      NPS - proper noun - plural
					      JJ - adjective: Hyphenated compounds that are used as modifiers; happy-go-lucky.
							JJR - adjective - comparative: Adjectives with the comparative ending '-er' and a comparative
							meaning. Sometimes 'more' and 'less'.
							JJS - adjective - superlative: Adjectives with the superlative ending '-est' (and 'worst').
							Sometimes 'most' and 'least'.
							VBD - verb - past tense: includes conditional form of the verb `to be'; `If I were/VBD rich...'.
							VBG - verb - gerund or present participle
							VBN - verb - past participle
							VBP - verb - non-3rd person singular present
							VB - verb - base form: subsumes imperatives, innitives and subjunctives.
							VBZ - verb - 3rd person singular present
					  */
						kelime = ((String)token.getFeatures().get("string")).trim();
						kelimeKategori = ((String)token.getFeatures().get("category")).trim();
						KelimeDugum tmpKD;
						if ((kelimeKategori.compareTo("NN") == 0) ||
								(kelimeKategori.compareTo("NNP") == 0) ||
								(kelimeKategori.compareTo("NNPS") == 0) ||
								(kelimeKategori.compareTo("NNS") == 0) ||
								(kelimeKategori.compareTo("NP") == 0) ||
								(kelimeKategori.compareTo("NPS") == 0) ||
								(kelimeKategori.compareTo("JJ") == 0) ||
								(kelimeKategori.compareTo("JJR") == 0) ||
								(kelimeKategori.compareTo("JJS") == 0) ||
								(kelimeKategori.compareTo("VBD") == 0) ||
								(kelimeKategori.compareTo("VBG") == 0) ||
								(kelimeKategori.compareTo("VBN") == 0) ||
								(kelimeKategori.compareTo("VBP") == 0) ||
								(kelimeKategori.compareTo("VB") == 0) ||
								(kelimeKategori.compareTo("VBZ") == 0))

						{
							kelimeBas = (long)token.getStartNode().getOffset();
							kelimeBit = (long)token.getEndNode().getOffset();
							tmpKD = new KelimeDugum(cumleIndeks, kelime,
									kelimeBas, kelimeBit, (String)token.getFeatures().get("root"), kelimeKategori);
							simdikiCumle.ekleKelimeDugum(tmpKD);
							if (label_start_end != null && URI_type != null)
							{
								for (int i = aramaDevam; i < label_start_end.length; i++)
								{
									if (label_start_end[i][0] == kelimeBas && label_start_end[i][1] == kelimeBit)
									{
										tmpKD.setEtiketBilgisi(URI_type[i][0], URI_type[i][1]);
										//System.out.println("URIIIII: " + URI_type[i][0] + " - " + URI_type[i][1]);
										aramaDevam = i + 1;
										break;
									}
								}
							}
						}
					}
					cumleIndeks++;
				}
			}
			
			//BagdasiklikHesapla(arrCumleler);
			BagdasiklikHesaplaWordnet(arrCumleler);
			
			/*System.out.println("11");
			AnnotationSet labelsAS = docs[0].getAnnotations().get("Lookup");
			System.out.println(labelsAS.size());
			if(labelsAS != null && labelsAS.size() > 0)
			{
				//define a comparator for annotations by start offset
				Comparator offsetComparator = new OffsetComparator();

				List labelsList = new ArrayList(labelsAS);
				Collections.sort(labelsList, offsetComparator);
				
				Iterator labelsIter = labelsList.iterator();
				while(labelsIter.hasNext())
				{
					Annotation currentLabel = (Annotation)labelsIter.next();
					System.out.println("URIII: " + (currentLabel.getFeatures().get("URI")));
					System.out.println("Start: " + currentLabel.getStartNode().getOffset());
					System.out.println("End: " + currentLabel.getEndNode().getOffset());
					System.out.println(currentLabel.toString());
					//get tokens of the current sentence
					java.util.List<Annotation> tokenslist = new java.util.LinkedList<gate.Annotation>(docs[0].getAnnotations().get("Token", currentLabel.getStartNode().getOffset(), currentLabel.getEndNode().getOffset()));
					//java.util.List<Annotation> tokenslist = new java.util.LinkedList<gate.Annotation>(doc.getAnnotations().get("Token", currentSentence.getStartNode().getOffset(), currentSentence.getEndNode().getOffset()));
					//sort the token list
					Collections.sort(tokenslist, new OffsetComparator());
					for (Annotation token : tokenslist) {
						System.out.println("Lookup URI: " + (String)token.getFeatures().get("URI"));
						//System.out.println((String)token.getFeatures().get("category"));
					}
				}
			}
			System.out.println("22");
*/

		//Cumle ve kelime veri yapilarini yazdir
		
			/*for (int m = 0; m < arrCumleler.size(); m++)
			{
				System.out.println("Cümle No: " + arrCumleler.get(m).CumleNo() + " Baþlangýç: " + arrCumleler.get(m).CumleBaslangic()
						+ " Bitiþ: " + arrCumleler.get(m).CumleBitis() + " Baðdaþýklýk: " + arrCumleler.get(m).ResnikBagdasiklik());
				for (int n = 0; n < arrCumleler.get(m).Kelimeler().size(); n++)
				{
					System.out.println("Cümle No: " + arrCumleler.get(m).Kelimeler().get(n).CumleNo() +
							" Kelime: " + arrCumleler.get(m).Kelimeler().get(n).Kelime() +
							" Kelime Baþlangýç: " + arrCumleler.get(m).Kelimeler().get(n).KelimeBaslangic() +
							" Kelime Bitiþ: " + arrCumleler.get(m).Kelimeler().get(n).KelimeBitis() +
							" Kelime Kök: " + arrCumleler.get(m).Kelimeler().get(n).KelimeKok() +
							" Kelime Kategori: " + arrCumleler.get(m).Kelimeler().get(n).KelimeKategori() +
							" Etiket Ontoloji URI: " + arrCumleler.get(m).Kelimeler().get(n).EtiketOntolojiURI() +
							" Etiket URI: " + arrCumleler.get(m).Kelimeler().get(n).EtiketURI() +
							" Etiket Tip: " + arrCumleler.get(m).Kelimeler().get(n).EtiketTip());
				}
			}*/

			//Eslesme oranini hesapla
			/*int isim_sayisi = 0;
			int isim_sayisi_eslesen = 0;
			Hashtable<String, Integer> ht_isim = new Hashtable<String, Integer>();
			int sifat_sayisi = 0;
			int sifat_sayisi_eslesen = 0;
			Hashtable<String, Integer> ht_sifat = new Hashtable<String, Integer>();
			int fiil_sayisi = 0;
			int fiil_sayisi_eslesen = 0;
			Hashtable<String, Integer> ht_fiil = new Hashtable<String, Integer>();
			String tmpkelimeKategori = "";
			int tmp_int = 0;
			for (int m = 0; m < arrCumleler.size(); m++)
			{
				System.out.println("Cümle No: " + arrCumleler.get(m).CumleNo() + " Baþlangýç: " + arrCumleler.get(m).CumleBaslangic()
						+ " Bitiþ: " + arrCumleler.get(m).CumleBitis() + " Baðdaþýklýk: " + arrCumleler.get(m).ResnikBagdasiklik());
				for (int n = 0; n < arrCumleler.get(m).Kelimeler().size(); n++)
				{
					System.out.println("Cümle No: " + arrCumleler.get(m).Kelimeler().get(n).CumleNo() +
							" Kelime: " + arrCumleler.get(m).Kelimeler().get(n).Kelime() +
							" Kelime Baþlangýç: " + arrCumleler.get(m).Kelimeler().get(n).KelimeBaslangic() +
							" Kelime Bitiþ: " + arrCumleler.get(m).Kelimeler().get(n).KelimeBitis() +
							" Kelime Kök: " + arrCumleler.get(m).Kelimeler().get(n).KelimeKok() +
							" Kelime Kategori: " + arrCumleler.get(m).Kelimeler().get(n).KelimeKategori() +
							" Etiket Ontoloji URI: " + arrCumleler.get(m).Kelimeler().get(n).EtiketOntolojiURI() +
							" Etiket URI: " + arrCumleler.get(m).Kelimeler().get(n).EtiketURI() +
							" Etiket Tip: " + arrCumleler.get(m).Kelimeler().get(n).EtiketTip());
					tmpkelimeKategori = arrCumleler.get(m).Kelimeler().get(n).KelimeKategori();
					if ((tmpkelimeKategori.compareTo("NN") == 0) ||
							(tmpkelimeKategori.compareTo("NNP") == 0) ||
							(tmpkelimeKategori.compareTo("NNPS") == 0) ||
							(tmpkelimeKategori.compareTo("NNS") == 0) ||
							(tmpkelimeKategori.compareTo("NP") == 0) ||
							(tmpkelimeKategori.compareTo("NPS") == 0))
					{
						isim_sayisi++;
						if (arrCumleler.get(m).Kelimeler().get(n).EtiketURI().compareTo("") != 0)
						{
							isim_sayisi_eslesen++;
							if(ht_isim.containsKey(arrCumleler.get(m).Kelimeler().get(n).EtiketTip()))
							{
								tmp_int = (Integer) ht_isim.get(arrCumleler.get(m).Kelimeler().get(n).EtiketTip());
								tmp_int++;
								ht_isim.remove(arrCumleler.get(m).Kelimeler().get(n).EtiketTip());
								ht_isim.put(arrCumleler.get(m).Kelimeler().get(n).EtiketTip(), new Integer(tmp_int));
							}
							else
							{
								ht_isim.put(arrCumleler.get(m).Kelimeler().get(n).EtiketTip(), new Integer(1));
							}
						}
					}
					else if	((tmpkelimeKategori.compareTo("JJ") == 0) ||
							(tmpkelimeKategori.compareTo("JJR") == 0) ||
							(tmpkelimeKategori.compareTo("JJS") == 0))
					{
						sifat_sayisi++;
						if (arrCumleler.get(m).Kelimeler().get(n).EtiketURI().compareTo("") != 0)
						{
							sifat_sayisi_eslesen++;
							if(ht_sifat.containsKey(arrCumleler.get(m).Kelimeler().get(n).EtiketTip()))
							{
								tmp_int = (Integer) ht_sifat.get(arrCumleler.get(m).Kelimeler().get(n).EtiketTip());
								tmp_int++;
								ht_sifat.remove(arrCumleler.get(m).Kelimeler().get(n).EtiketTip());
								ht_sifat.put(arrCumleler.get(m).Kelimeler().get(n).EtiketTip(), new Integer(tmp_int));
							}
							else
							{
								ht_sifat.put(arrCumleler.get(m).Kelimeler().get(n).EtiketTip(), new Integer(1));
							}
						}
					}
					else if ((tmpkelimeKategori.compareTo("VBD") == 0) ||
							(tmpkelimeKategori.compareTo("VBG") == 0) ||
							(tmpkelimeKategori.compareTo("VBN") == 0) ||
							(tmpkelimeKategori.compareTo("VBP") == 0) ||
							(tmpkelimeKategori.compareTo("VB") == 0) ||
							(tmpkelimeKategori.compareTo("VBZ") == 0))
					{
						fiil_sayisi++;
						if (arrCumleler.get(m).Kelimeler().get(n).EtiketURI().compareTo("") != 0)
						{
							fiil_sayisi_eslesen++;
							if(ht_fiil.containsKey(arrCumleler.get(m).Kelimeler().get(n).EtiketTip()))
							{
								tmp_int = (Integer) ht_fiil.get(arrCumleler.get(m).Kelimeler().get(n).EtiketTip());
								tmp_int++;
								ht_fiil.remove(arrCumleler.get(m).Kelimeler().get(n).EtiketTip());
								ht_fiil.put(arrCumleler.get(m).Kelimeler().get(n).EtiketTip(), new Integer(tmp_int));
							}
							else
							{
								ht_fiil.put(arrCumleler.get(m).Kelimeler().get(n).EtiketTip(), new Integer(1));
							}
						}
					}
				}
			}
			System.out.println("Ýsim sayýsý: " + isim_sayisi);
			System.out.println("Ýsim sayýsý eþleþen: " + isim_sayisi_eslesen);
			System.out.println("Ýsim eþleþme oraný: %" + (((double)isim_sayisi_eslesen / (double)isim_sayisi) * 100));
			System.out.println("Sýfat sayýsý: " + sifat_sayisi);
			System.out.println("Sýfat sayýsý eþleþen: " + sifat_sayisi_eslesen);
			System.out.println("Sýfat eþleþme oraný: %" + (((double)sifat_sayisi_eslesen / (double)sifat_sayisi) * 100));
			System.out.println("Fiil sayýsý: " + fiil_sayisi);
			System.out.println("Fiil sayýsý eþleþen: " + fiil_sayisi_eslesen);
			System.out.println("Fiil eþleþme oraný: %" + ((double)fiil_sayisi_eslesen / (double)fiil_sayisi) * 100);
			System.out.println("Toplam kelime sayýsý: " + ((double)isim_sayisi + (double)sifat_sayisi + (double)fiil_sayisi));
			System.out.println("Toplam kelime eþleþen: " + ((double)isim_sayisi_eslesen + (double)sifat_sayisi_eslesen + (double)fiil_sayisi_eslesen));
			System.out.println("Toplam kelime eþleþme oraný: %" + (((double)isim_sayisi_eslesen + (double)sifat_sayisi_eslesen + (double)fiil_sayisi_eslesen)) / ((double)isim_sayisi + (double)sifat_sayisi + (double)fiil_sayisi) * 100);

			String key;
			Integer value;
			Enumeration<String> e = ht_isim.keys();
	        while (e.hasMoreElements())
	        { 
	            key = (String) e.nextElement(); 
	            value = (Integer) ht_isim.get (key);
	            System.out.println ("Ýsim: { " + key + ", " + value + " }"); 
	        } 
			e = ht_sifat.keys();
	        while (e.hasMoreElements())
	        { 
	            key = (String) e.nextElement(); 
	            value = (Integer) ht_sifat.get (key);
	            System.out.println ("Sýfat: { " + key + ", " + value + " }"); 
	        } 
			e = ht_fiil.keys();
	        while (e.hasMoreElements())
	        { 
	            key = (String) e.nextElement(); 
	            value = (Integer) ht_fiil.get (key);
	            System.out.println ("Fiil: { " + key + ", " + value + " }"); 
	        } */
			
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	private static void BagdasiklikHesapla(ArrayList<CumleDugum> arrCumleler)
	{
		if (arrCumleler != null)
		{
			int cumleIndeks = 0;
			int n_max = 0;
			ArrayList<EslestirmeSonucu> es = new ArrayList<EslestirmeSonucu>();
			int esIndex = 0;
			WordNetBenzerlik wnb = new WordNetBenzerlik();
			try
			{
				while (cumleIndeks + 1 < arrCumleler.size())
				{
					System.out.println("cümleIndeks: " + cumleIndeks);
					double[] kelimeBenzerlikGG = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					double[] kelimeBenzerlikResnik = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					double[] kelimeBenzerlikLin = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					double[] kelimeBenzerlikJC = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					double[] kelimeBenzerlikWP = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					double[] kelimeBenzerlikLC = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					double[] kelimeBenzerlikResnikWordnet = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					double[] kelimeBenzerlikLinWordnet = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					double[] kelimeBenzerlikJCWordnet = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					double[] kelimeBenzerlikWPWordnet = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					double[] kelimeBenzerlikLCWordnet = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					double[] kelimeBenzerlikHSOWordnet = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					n_max = arrCumleler.get(cumleIndeks + 1).Kelimeler().size();
					for (int m = 0; m < arrCumleler.get(cumleIndeks).Kelimeler().size(); m++)
					{
						devam:
						for (int n = 0; n < arrCumleler.get(cumleIndeks + 1).Kelimeler().size(); n++)
						{
							/*System.out.println("Kel1: " + arrCumleler.get(cumleIndeks).Kelimeler().get(m).Kelime() +
									" Kel2: " + arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).Kelime() +
									" Ont1: " + arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketOntolojiURI() +
									" Ont2: " + arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).EtiketOntolojiURI());*/
							/*if ((arrCumleler.get(cumleIndeks).Kelimeler().get(m).Kelime().compareTo("S") == 0) &&
									arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).Kelime().compareTo("S") == 0)
							{
								System.out.println("Europeeeee - Mexicooooo");
							}*/

							if (arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok().compareTo(arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok()) == 0)
							{
								//System.out.println("m * n_max + n: " + (m * n_max + n));
								kelimeBenzerlikGG[m * n_max + n] = 1;
								//System.out.println("kelimeBenzerlikGG[" + (m * n_max + n) + "]: " + kelimeBenzerlikGG[m * n_max + n]);
								kelimeBenzerlikResnik[m * n_max + n] = 1;
								kelimeBenzerlikLin[m * n_max + n] = 1;
								kelimeBenzerlikJC[m * n_max + n] = 1;
								kelimeBenzerlikWP[m * n_max + n] = 1;
								kelimeBenzerlikLC[m * n_max + n] = 1;
							}
							else if ((arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketURI().compareTo("") != 0) &&
									(arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).EtiketURI().compareTo("") != 0))
							{
								if (arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketURI().compareTo(arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).EtiketURI()) == 0)
								{
									kelimeBenzerlikGG[m * n_max + n] = 1;
									kelimeBenzerlikResnik[m * n_max + n] = 1;
									kelimeBenzerlikLin[m * n_max + n] = 1;
									kelimeBenzerlikJC[m * n_max + n] = 1;
									kelimeBenzerlikWP[m * n_max + n] = 1;
									kelimeBenzerlikLC[m * n_max + n] = 1;
								}
								else if (arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketOntolojiURI().compareTo(arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).EtiketOntolojiURI()) == 0)
								{
									try
									{
										esIndex = -1;
										for (int e = 0; e < es.size(); e++)
										{
											if (((es.get(e).getString1().compareTo(arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok()) == 0) &&
													(es.get(e).getString2().compareTo(arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok()) == 0)) ||
													((es.get(e).getString2().compareTo(arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok()) == 0) &&
													(es.get(e).getString1().compareTo(arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok()) == 0)))
													{
														esIndex = e;
														break;
													}
										}
										if (esIndex == -1)
										{
											try
											{
												/*System.out.println("Kel1: " + arrCumleler.get(cumleIndeks).Kelimeler().get(m).Kelime() +
														" Kel2: " + arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).Kelime() +
														" Ont1: " + arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketOntolojiURI() +
														" Ont2: " + arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).EtiketOntolojiURI());*/
												kelimeBenzerlikGG[m * n_max + n] = Patika.compareOntologyConcepts(arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketOntolojiURI(),
														arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketURI(),
														arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).EtiketURI());
											}
											catch (Exception e)
											{
											}
											try
											{
												kelimeBenzerlikResnik[m * n_max + n] = compareStringsResnik(arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketOntolojiURI(),
														arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketURI(),
														arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).EtiketURI());
											}
											catch (Exception e)
											{
											}
											//System.out.println("Resnik: " + kelimeBenzerlikResnik[2 * m + n]);
											try
											{
												kelimeBenzerlikLin[m * n_max + n] = compareStringsLin(arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketOntolojiURI(),
														arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketURI(),
														arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).EtiketURI());
											}
											catch (Exception e)
											{
											}
											//System.out.println("Lin: " + kelimeBenzerlikLin[2 * m + n]);
											try
											{
												kelimeBenzerlikJC[m * n_max + n] = compareStringsJiangConrath(arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketOntolojiURI(),
														arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketURI(),
														arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).EtiketURI());
											}
											catch (Exception e)
											{
											}
											//System.out.println("JC: " + kelimeBenzerlikJC[2 * m + n]);
											try
											{
												kelimeBenzerlikLC[m * n_max + n] = compareStringsLeacockChodorow(arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketOntolojiURI(),
														arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketURI(),
														arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).EtiketURI());
											}
											catch (Exception e)
											{
											}
											//System.out.println("LC: " + kelimeBenzerlikLC[2 * m + n]);
											try
											{
												kelimeBenzerlikWP[m * n_max + n] = compareStringsWuPalmer(arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketOntolojiURI(),
														arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketURI(),
														arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).EtiketURI());
											}
											catch (Exception e)
											{
											}
											//System.out.println("WP: " + kelimeBenzerlikWP[2 * m + n]);
											
											//Bulunan ilintililik/benzerlik degerlerini sakla
											es.add(new EslestirmeSonucu(arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok(), 
													arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok(), 
													kelimeBenzerlikGG[m * n_max + n], kelimeBenzerlikResnik[m * n_max + n], 
													kelimeBenzerlikLin[m * n_max + n], kelimeBenzerlikJC[m * n_max + n], 
													kelimeBenzerlikLC[m * n_max + n], kelimeBenzerlikWP[m * n_max + n]));
											System.out.println("Eklendi: " + arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok() + " - " +
													arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok() + " - " +
													kelimeBenzerlikGG[m * n_max + n]);
										}
										else
										{
											//Daha once zaten bulunmus olan ilintililik/benzerlik degerlerini al
											kelimeBenzerlikGG[m * n_max + n] = es.get(esIndex).get_GG_Ilintililik();
											kelimeBenzerlikResnik[m * n_max + n] = es.get(esIndex).get_Resnik_Benzerlik();
											kelimeBenzerlikLin[m * n_max + n] = es.get(esIndex).get_Lin_Benzerlik();
											kelimeBenzerlikJC[m * n_max + n] = es.get(esIndex).get_JC_Benzerlik();
											kelimeBenzerlikLC[m * n_max + n] = es.get(esIndex).get_LC_Benzerlik();
											kelimeBenzerlikWP[m * n_max + n] = es.get(esIndex).get_WP_Benzerlik();
											System.out.println("Alindi: " + arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok() + " - " +
													arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok() + " - " +
													kelimeBenzerlikGG[m * n_max + n]);
										}
									}
									catch (Exception e)
									{
										continue devam;
									}
								}
								else
								{
									kelimeBenzerlikGG[m * n_max + n] = 0;
									kelimeBenzerlikResnik[m * n_max + n] = 0;
									kelimeBenzerlikLin[m * n_max + n] = 0;
									kelimeBenzerlikJC[m * n_max + n] = 0;
									kelimeBenzerlikWP[m * n_max + n] = 0;
									kelimeBenzerlikLC[m * n_max + n] = 0;
								}
							}
							else
							{
								kelimeBenzerlikGG[m * n_max + n] = 0;
								kelimeBenzerlikResnik[m * n_max + n] = 0;
								kelimeBenzerlikLin[m * n_max + n] = 0;
								kelimeBenzerlikJC[m * n_max + n] = 0;
								kelimeBenzerlikWP[m * n_max + n] = 0;
								kelimeBenzerlikLC[m * n_max + n] = 0;
							}
							/*System.out.println("Kel1: " + arrCumleler.get(cumleIndeks).Kelimeler().get(m).Kelime() +
									" Kel2: " + arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).Kelime() +
									" GG_Ilintililik: " + kelimeBenzerlikGG[2 * m + n]);*/
						}						
					}
					double tmpToplamBenzerlikGG = 0;
					double tmpToplamBenzerlikResnik = 0;
					double tmpToplamBenzerlikLin = 0;
					double tmpToplamBenzerlikJC = 0;
					double tmpToplamBenzerlikWP = 0;
					double tmpToplamBenzerlikLC = 0;
					double tmpToplamBenzerlikResnikWordnet = 0;
					double tmpToplamBenzerlikLinWordnet = 0;
					double tmpToplamBenzerlikJCWordnet = 0;
					double tmpToplamBenzerlikWPWordnet = 0;
					double tmpToplamBenzerlikLCWordnet = 0;
					double tmpToplamBenzerlikHSOWordnet = 0;
					for (int k = 0; k < kelimeBenzerlikGG.length; k++)
					{
						//System.out.println("kelimeBenzerlikGG[" + k + "]: " + kelimeBenzerlikGG[k]);
						tmpToplamBenzerlikGG = tmpToplamBenzerlikGG + kelimeBenzerlikGG[k];
					}
					
					arrCumleler.get(cumleIndeks).setGGBagdasiklik(tmpToplamBenzerlikGG / kelimeBenzerlikGG.length);
					System.out.println("tmpToplamBenzerlikGG: " + tmpToplamBenzerlikGG);
					System.out.println("kelimeBenzerlikGG.length: " + kelimeBenzerlikGG.length);
					System.out.println("Cumle bag: " + arrCumleler.get(cumleIndeks).GGBagdasiklik());
					for (int k = 0; k < kelimeBenzerlikResnik.length; k++)
					{
						tmpToplamBenzerlikResnik = tmpToplamBenzerlikResnik + kelimeBenzerlikResnik[k];
					}
					arrCumleler.get(cumleIndeks).setResnikBagdasiklik(tmpToplamBenzerlikResnik / kelimeBenzerlikResnik.length);
					for (int k = 0; k < kelimeBenzerlikLin.length; k++)
					{
						tmpToplamBenzerlikLin = tmpToplamBenzerlikLin + kelimeBenzerlikLin[k];
					}
					arrCumleler.get(cumleIndeks).setLinBagdasiklik(tmpToplamBenzerlikLin / kelimeBenzerlikLin.length);
					for (int k = 0; k < kelimeBenzerlikJC.length; k++)
					{
						tmpToplamBenzerlikJC = tmpToplamBenzerlikJC + kelimeBenzerlikJC[k];
					}
					arrCumleler.get(cumleIndeks).setJiangConrathBagdasiklik(tmpToplamBenzerlikJC / kelimeBenzerlikJC.length);
					for (int k = 0; k < kelimeBenzerlikWP.length; k++)
					{
						tmpToplamBenzerlikWP = tmpToplamBenzerlikWP + kelimeBenzerlikWP[k];
					}
					arrCumleler.get(cumleIndeks).setWuPalmerBagdasiklik(tmpToplamBenzerlikWP / kelimeBenzerlikWP.length);
					for (int k = 0; k < kelimeBenzerlikLC.length; k++)
					{
						tmpToplamBenzerlikLC = tmpToplamBenzerlikLC + kelimeBenzerlikLC[k];
					}
					arrCumleler.get(cumleIndeks).setLeacockChodorowBagdasiklik(tmpToplamBenzerlikLC / kelimeBenzerlikLC.length);
					for (int k = 0; k < kelimeBenzerlikResnikWordnet.length; k++)
					{
						tmpToplamBenzerlikResnikWordnet = tmpToplamBenzerlikResnikWordnet + kelimeBenzerlikResnikWordnet[k];
					}
					arrCumleler.get(cumleIndeks).setResnikWordnetBagdasiklik(tmpToplamBenzerlikResnikWordnet / kelimeBenzerlikResnikWordnet.length);
					for (int k = 0; k < kelimeBenzerlikLinWordnet.length; k++)
					{
						tmpToplamBenzerlikLinWordnet = tmpToplamBenzerlikLinWordnet + kelimeBenzerlikLinWordnet[k];
					}
					arrCumleler.get(cumleIndeks).setLinWordnetBagdasiklik(tmpToplamBenzerlikLinWordnet / kelimeBenzerlikLinWordnet.length);
					for (int k = 0; k < kelimeBenzerlikJCWordnet.length; k++)
					{
						tmpToplamBenzerlikJCWordnet = tmpToplamBenzerlikJCWordnet + kelimeBenzerlikJCWordnet[k];
					}
					arrCumleler.get(cumleIndeks).setJCWordnetBagdasiklik(tmpToplamBenzerlikJCWordnet / kelimeBenzerlikJCWordnet.length);
					for (int k = 0; k < kelimeBenzerlikWPWordnet.length; k++)
					{
						tmpToplamBenzerlikWPWordnet = tmpToplamBenzerlikWPWordnet + kelimeBenzerlikWPWordnet[k];
					}
					arrCumleler.get(cumleIndeks).setWPWordnetBagdasiklik(tmpToplamBenzerlikWPWordnet / kelimeBenzerlikWPWordnet.length);
					for (int k = 0; k < kelimeBenzerlikLCWordnet.length; k++)
					{
						tmpToplamBenzerlikLCWordnet = tmpToplamBenzerlikLCWordnet + kelimeBenzerlikLCWordnet[k];
					}
					arrCumleler.get(cumleIndeks).setLCWordnetBagdasiklik(tmpToplamBenzerlikLCWordnet / kelimeBenzerlikLCWordnet.length);
					for (int k = 0; k < kelimeBenzerlikHSOWordnet.length; k++)
					{
						tmpToplamBenzerlikHSOWordnet = tmpToplamBenzerlikHSOWordnet + kelimeBenzerlikHSOWordnet[k];
					}
					arrCumleler.get(cumleIndeks).setHSOWordnetBagdasiklik(tmpToplamBenzerlikHSOWordnet / kelimeBenzerlikHSOWordnet.length);

					cumleIndeks++;
				}
				double tmpToplamGGBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamGGBagdasiklik = tmpToplamGGBagdasiklik + arrCumleler.get(l).GGBagdasiklik();
				}
				double tmpToplamResnikBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamResnikBagdasiklik = tmpToplamResnikBagdasiklik + arrCumleler.get(l).ResnikBagdasiklik();
				}
				double tmpToplamLinBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamLinBagdasiklik = tmpToplamLinBagdasiklik + arrCumleler.get(l).LinBagdasiklik();
				}
				double tmpToplamJCBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamJCBagdasiklik = tmpToplamJCBagdasiklik + arrCumleler.get(l).JiangConrathBagdasiklik();
				}
				double tmpToplamWPBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamWPBagdasiklik = tmpToplamWPBagdasiklik + arrCumleler.get(l).WuPalmerBagdasiklik();
				}
				double tmpToplamLCBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamLCBagdasiklik = tmpToplamLCBagdasiklik + arrCumleler.get(l).LeacockChodorowBagdasiklik();
				}
				double tmpToplamResnikWordnetBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamResnikWordnetBagdasiklik = tmpToplamResnikWordnetBagdasiklik + arrCumleler.get(l).ResnikWordnetBagdasiklik();
				}
				double tmpToplamLinWordnetBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamLinWordnetBagdasiklik = tmpToplamLinWordnetBagdasiklik + arrCumleler.get(l).LinWordnetBagdasiklik();
				}
				double tmpToplamJCWordnetBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamJCWordnetBagdasiklik = tmpToplamJCWordnetBagdasiklik + arrCumleler.get(l).JCWordnetBagdasiklik();
				}
				double tmpToplamWPWordnetBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamWPWordnetBagdasiklik = tmpToplamWPWordnetBagdasiklik + arrCumleler.get(l).WPWordnetBagdasiklik();
				}
				double tmpToplamLCWordnetBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamLCWordnetBagdasiklik = tmpToplamLCWordnetBagdasiklik + arrCumleler.get(l).LCWordnetBagdasiklik();
				}
				double tmpToplamHSOWordnetBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamHSOWordnetBagdasiklik = tmpToplamHSOWordnetBagdasiklik + arrCumleler.get(l).HSOWordnetBagdasiklik();
				}
				System.out.println("GG Bagdasiklik: " + (tmpToplamGGBagdasiklik / (double)(arrCumleler.size() - 1)));
				System.out.println("Resnik Bagdasiklik: " + (tmpToplamResnikBagdasiklik / (double)(arrCumleler.size() - 1)));
				System.out.println("Lin Bagdasiklik: " + (tmpToplamLinBagdasiklik / (double)(arrCumleler.size() - 1)));
				System.out.println("JiangConrath Bagdasiklik: " + (tmpToplamJCBagdasiklik / (double)(arrCumleler.size() - 1)));
				System.out.println("WuPalmer Bagdasiklik: " + (tmpToplamWPBagdasiklik / (double)(arrCumleler.size() - 1)));
				System.out.println("LeacockChodorow Bagdasiklik: " + (tmpToplamLCBagdasiklik / (double)(arrCumleler.size() - 1)));
				System.out.println("Resnik Wordnet Bagdasiklik: " + (tmpToplamResnikWordnetBagdasiklik / (double)(arrCumleler.size() - 1)));
				System.out.println("Lin Wordnet Bagdasiklik: " + (tmpToplamLinWordnetBagdasiklik / (double)(arrCumleler.size() - 1)));
				System.out.println("JC Wordnet Bagdasiklik: " + (tmpToplamJCWordnetBagdasiklik / (double)(arrCumleler.size() - 1)));
				System.out.println("WP Wordnet Bagdasiklik: " + (tmpToplamWPWordnetBagdasiklik / (double)(arrCumleler.size() - 1)));
				System.out.println("LC Wordnet Bagdasiklik: " + (tmpToplamLCWordnetBagdasiklik / (double)(arrCumleler.size() - 1)));
				System.out.println("HSO Wordnet Bagdasiklik: " + (tmpToplamHSOWordnetBagdasiklik / (double)(arrCumleler.size() - 1)));
			}
			catch (Exception e)
			{
				//System.out.println(e.toString());
				e.printStackTrace();
			}
		}
	}
	
	private static void BagdasiklikHesaplaWordnet(ArrayList<CumleDugum> arrCumleler)
	{
		if (arrCumleler != null)
		{
			int cumleIndeks = 0;
			int n_max = 0;
			WordNetBenzerlik wnb = new WordNetBenzerlik();
			try
			{
				while (cumleIndeks + 1 < arrCumleler.size())
				{
					System.out.println("cümleIndeks: " + cumleIndeks);
					double[] kelimeBenzerlikResnikWordnet = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					double[] kelimeBenzerlikLinWordnet = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					double[] kelimeBenzerlikJCWordnet = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					double[] kelimeBenzerlikWPWordnet = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					double[] kelimeBenzerlikLCWordnet = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					double[] kelimeBenzerlikHSOWordnet = new double[arrCumleler.get(cumleIndeks).Kelimeler().size() * arrCumleler.get(cumleIndeks + 1).Kelimeler().size()];
					n_max = arrCumleler.get(cumleIndeks + 1).Kelimeler().size();
					for (int m = 0; m < arrCumleler.get(cumleIndeks).Kelimeler().size(); m++)
					{
						devam:
						for (int n = 0; n < arrCumleler.get(cumleIndeks + 1).Kelimeler().size(); n++)
						{
							/*System.out.println("Kel1: " + arrCumleler.get(cumleIndeks).Kelimeler().get(m).Kelime() +
									" Kel2: " + arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).Kelime() +
									" Ont1: " + arrCumleler.get(cumleIndeks).Kelimeler().get(m).EtiketOntolojiURI() +
									" Ont2: " + arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).EtiketOntolojiURI());*/
							/*if ((arrCumleler.get(cumleIndeks).Kelimeler().get(m).Kelime().compareTo("S") == 0) &&
									arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).Kelime().compareTo("S") == 0)
							{
								System.out.println("Europeeeee - Mexicooooo");
							}*/
							try
							{
								kelimeBenzerlikResnikWordnet[m * n_max + n] = wnb.compareStringsResnik(arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok(),
										arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok());
								//System.out.println("Resnik WordNet: " + arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok() + " - " +
								//		arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok() + " : " + kelimeBenzerlikResnikWordnet[m * n_max + n]);
							}
							catch (Exception e)
							{
							}
							try
							{
								kelimeBenzerlikLinWordnet[m * n_max + n] = wnb.compareStringsLin(arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok(),
										arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok());
							}
							catch (Exception e)
							{
							}
							try
							{
								kelimeBenzerlikJCWordnet[m * n_max + n] = wnb.compareStringsJC(arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok(),
										arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok());
							}
							catch (Exception e)
							{
							}
							try
							{
								kelimeBenzerlikWPWordnet[m * n_max + n] = wnb.compareStringsWP(arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok(),
										arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok());
							}
							catch (Exception e)
							{
							}
							try
							{
								kelimeBenzerlikLCWordnet[m * n_max + n] = wnb.compareStringsLC(arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok(),
										arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok());
							}
							catch (Exception e)
							{
							}
							try
							{
								//kelimeBenzerlikHSOWordnet[m * n_max + n] = wnb.compareStringsHSO(arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok(),
								//		arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok());
							}
							catch (Exception e)
							{
							}

						}						
					}
					double tmpToplamBenzerlikResnikWordnet = 0;
					double tmpToplamBenzerlikLinWordnet = 0;
					double tmpToplamBenzerlikJCWordnet = 0;
					double tmpToplamBenzerlikWPWordnet = 0;
					double tmpToplamBenzerlikLCWordnet = 0;
					double tmpToplamBenzerlikHSOWordnet = 0;
					for (int k = 0; k < kelimeBenzerlikResnikWordnet.length; k++)
					{
						tmpToplamBenzerlikResnikWordnet = tmpToplamBenzerlikResnikWordnet + kelimeBenzerlikResnikWordnet[k];
					}
					arrCumleler.get(cumleIndeks).setResnikWordnetBagdasiklik(tmpToplamBenzerlikResnikWordnet / kelimeBenzerlikResnikWordnet.length);
					for (int k = 0; k < kelimeBenzerlikLinWordnet.length; k++)
					{
						tmpToplamBenzerlikLinWordnet = tmpToplamBenzerlikLinWordnet + kelimeBenzerlikLinWordnet[k];
					}
					arrCumleler.get(cumleIndeks).setLinWordnetBagdasiklik(tmpToplamBenzerlikLinWordnet / kelimeBenzerlikLinWordnet.length);
					for (int k = 0; k < kelimeBenzerlikJCWordnet.length; k++)
					{
						tmpToplamBenzerlikJCWordnet = tmpToplamBenzerlikJCWordnet + kelimeBenzerlikJCWordnet[k];
					}
					arrCumleler.get(cumleIndeks).setJCWordnetBagdasiklik(tmpToplamBenzerlikJCWordnet / kelimeBenzerlikJCWordnet.length);
					for (int k = 0; k < kelimeBenzerlikWPWordnet.length; k++)
					{
						tmpToplamBenzerlikWPWordnet = tmpToplamBenzerlikWPWordnet + kelimeBenzerlikWPWordnet[k];
					}
					arrCumleler.get(cumleIndeks).setWPWordnetBagdasiklik(tmpToplamBenzerlikWPWordnet / kelimeBenzerlikWPWordnet.length);
					for (int k = 0; k < kelimeBenzerlikLCWordnet.length; k++)
					{
						tmpToplamBenzerlikLCWordnet = tmpToplamBenzerlikLCWordnet + kelimeBenzerlikLCWordnet[k];
					}
					arrCumleler.get(cumleIndeks).setLCWordnetBagdasiklik(tmpToplamBenzerlikLCWordnet / kelimeBenzerlikLCWordnet.length);
					for (int k = 0; k < kelimeBenzerlikHSOWordnet.length; k++)
					{
						tmpToplamBenzerlikHSOWordnet = tmpToplamBenzerlikHSOWordnet + kelimeBenzerlikHSOWordnet[k];
					}
					arrCumleler.get(cumleIndeks).setHSOWordnetBagdasiklik(tmpToplamBenzerlikHSOWordnet / kelimeBenzerlikHSOWordnet.length);

					cumleIndeks++;
				}
				double tmpToplamResnikWordnetBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamResnikWordnetBagdasiklik = tmpToplamResnikWordnetBagdasiklik + arrCumleler.get(l).ResnikWordnetBagdasiklik();
				}
				double tmpToplamLinWordnetBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamLinWordnetBagdasiklik = tmpToplamLinWordnetBagdasiklik + arrCumleler.get(l).LinWordnetBagdasiklik();
				}
				double tmpToplamJCWordnetBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamJCWordnetBagdasiklik = tmpToplamJCWordnetBagdasiklik + arrCumleler.get(l).JCWordnetBagdasiklik();
				}
				double tmpToplamWPWordnetBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamWPWordnetBagdasiklik = tmpToplamWPWordnetBagdasiklik + arrCumleler.get(l).WPWordnetBagdasiklik();
				}
				double tmpToplamLCWordnetBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamLCWordnetBagdasiklik = tmpToplamLCWordnetBagdasiklik + arrCumleler.get(l).LCWordnetBagdasiklik();
				}
				double tmpToplamHSOWordnetBagdasiklik = 0;
				for (int l = 0; l < arrCumleler.size() - 1; l++) //son satirda bagdasiklik degeri yok
				{
					tmpToplamHSOWordnetBagdasiklik = tmpToplamHSOWordnetBagdasiklik + arrCumleler.get(l).HSOWordnetBagdasiklik();
				}
				System.out.println("Resnik Wordnet Bagdasiklik: " + (tmpToplamResnikWordnetBagdasiklik / (double)(arrCumleler.size() - 1)));
				System.out.println("Lin Wordnet Bagdasiklik: " + (tmpToplamLinWordnetBagdasiklik / (double)(arrCumleler.size() - 1)));
				System.out.println("JC Wordnet Bagdasiklik: " + (tmpToplamJCWordnetBagdasiklik / (double)(arrCumleler.size() - 1)));
				System.out.println("WP Wordnet Bagdasiklik: " + (tmpToplamWPWordnetBagdasiklik / (double)(arrCumleler.size() - 1)));
				System.out.println("LC Wordnet Bagdasiklik: " + (tmpToplamLCWordnetBagdasiklik / (double)(arrCumleler.size() - 1)));
				System.out.println("HSO Wordnet Bagdasiklik: " + (tmpToplamHSOWordnetBagdasiklik / (double)(arrCumleler.size() - 1)));
			}
			catch (Exception e)
			{
				//System.out.println(e.toString());
				e.printStackTrace();
			}
		}
	}

	private static double compareStringsResnik(String ontURI, String res1URI, String res2URI) throws Exception
	{
		double r_sim = 0;
		try
		{
			//System.out.println("ontURI: " + ontURI);
			//System.out.println("res1URI: " + res1URI);
			//System.out.println("res2URI: " + res2URI);
			JenaOntologyAccessor joa = new JenaOntologyAccessor(ontURI, ontURI, null, null, OntModelSpec.OWL_MEM); //cikarsamasiz
			IGraphNode res1 = joa.getNode(res1URI);
			IGraphNode res2 = joa.getNode(res2URI);
			//System.out.println("res1: " + res1.toString());
			//System.out.println("res2: " + res2.toString());
			if (joa != null && res1 != null && res2 != null)
			{
				Resnik r = new Resnik(joa, res1, res2);
				//Resnik r = new Resnik(joa, joa.getNode(res1URI), joa.getNode(res2URI));
		        r_sim = r.getSimilarity();
				return r_sim;
			}
			else
			{
				return 0;
			}
		}
		catch (Exception e)
		{
			//throw e;
			System.out.println(e.getMessage());
			return 0;
		}
	}

	private static double compareStringsLin(String ontURI, String res1URI, String res2URI) throws Exception
	{
		double l_sim = 0;
		try
		{
	        JenaOntologyAccessor joa = new JenaOntologyAccessor(ontURI, ontURI, null, null, OntModelSpec.OWL_MEM); //cikarsamasiz
			IGraphNode res1 = joa.getNode(res1URI);
			IGraphNode res2 = joa.getNode(res2URI);
	        Lin l = new Lin(joa, res1, res2);
	        l_sim = l.getSimilarity();
			return l_sim;
		}
		catch (Exception e)
		{
			//throw e;
			System.out.println(e.getMessage());
			return 0;
		}
	}

	private static double compareStringsJiangConrath(String ontURI, String res1URI, String res2URI) throws Exception
	{
		double jc_sim = 0;
		try
		{
	        JenaOntologyAccessor joa = new JenaOntologyAccessor(ontURI, ontURI, null, null, OntModelSpec.OWL_MEM); //cikarsamasiz
			IGraphNode res1 = joa.getNode(res1URI);
			IGraphNode res2 = joa.getNode(res2URI);
	        JiangConrath jc = new JiangConrath(joa, res1, res2);
	        jc_sim = jc.getSimilarity();
			return jc_sim;
		}
		catch (Exception e)
		{
			//throw e;
			System.out.println(e.getMessage());
			return 0;
		}
	}

	private static double compareStringsWuPalmer(String ontURI, String res1URI, String res2URI) throws Exception
	{
		double wp_sim = 0;
		try
		{
	        JenaOntologyAccessor joa = new JenaOntologyAccessor(ontURI, ontURI, null, null, OntModelSpec.OWL_MEM); //cikarsamasiz
			IGraphNode res1 = joa.getNode(res1URI);
			IGraphNode res2 = joa.getNode(res2URI);
	        ConceptualSimilarity wp = new ConceptualSimilarity(joa, res1, res2);
	        wp_sim = wp.getSimilarity();
			return wp_sim;
		}
		catch (Exception e)
		{
			//throw e;
			System.out.println(e.getMessage());
			return 0;
		}
	}

	private static double compareStringsLeacockChodorow(String ontURI, String res1URI, String res2URI) throws Exception
	{
		double lc_sim = 0;
		try
		{
	        JenaOntologyAccessor joa = new JenaOntologyAccessor(ontURI, ontURI, null, null, OntModelSpec.OWL_MEM); //cikarsamasiz
			IGraphNode res1 = joa.getNode(res1URI);
			IGraphNode res2 = joa.getNode(res2URI);
	        ScaledShortestPath lc = new ScaledShortestPath(joa, res1, res2);
	        lc_sim = lc.getSimilarity();
			return lc_sim;
		}
		catch (Exception e)
		{
			//throw e;
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public static void parseDocuments2(Document doc)
	{
		try
		{
			showGUI();
			
			corp = Factory.newCorpus("My Corpus");
			//corp.add(docs[0]);
			corp.add(doc);
			
			//create a controller (a.k.a GATE application)
			controller = (SerialAnalyserController)
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
			
			controller.setCorpus(corp);
			controller.execute();

			//Lookup yapisini olustur
			//AnnotationSet labelsAS = docs[0].getAnnotations().get("Lookup");
			AnnotationSet labelsAS = doc.getAnnotations().get("Lookup");
			String[][] URI_type = null;
			long[][] label_start_end = null;
			if(labelsAS != null && labelsAS.size() > 0)
			{
				//define a comparator for annotations by start offset
				Comparator offsetComparator = new OffsetComparator();

				List labelsList = new ArrayList(labelsAS);
				Collections.sort(labelsList, offsetComparator);
				
				Iterator labelsIter = labelsList.iterator();
				//String[][] tokens = new String[tokenslist.size()][2];
				URI_type = new String[labelsList.size()][2];
				label_start_end = new long[labelsList.size()][2];
				int k = 0;
				while(labelsIter.hasNext())
				{
					Annotation currentLabel = (Annotation)labelsIter.next();
					URI_type[k][0] = (String)currentLabel.getFeatures().get("URI");
					URI_type[k][1] = (String)currentLabel.getFeatures().get("type");
					label_start_end[k][0] = (long)currentLabel.getStartNode().getOffset();
					label_start_end[k][1] = (long)currentLabel.getEndNode().getOffset();
					k++;
				}
			}

			//Cumle veri yapisini olustur
			//AnnotationSet sentencesAS = docs[0].getAnnotations().get("Sentence");
			AnnotationSet sentencesAS = doc.getAnnotations().get("Sentence");
			System.out.println(sentencesAS.size());
			ArrayList<CumleDugum> arrCumleler = new ArrayList<CumleDugum>();
			CumleDugum simdikiCumle;
			int cumleIndeks = 1;
			long kelimeBas = 0;
			long kelimeBit = 0;
			int aramaDevam = 0;
			String kelime = "";
			String kelimeKategori = "";
			if(sentencesAS != null && sentencesAS.size() > 0)
			{
				//define a comparator for annotations by start offset
				Comparator offsetComparator = new OffsetComparator();
				
				//read all the tokens and all the sentences
				List sentencesList = new ArrayList(sentencesAS);
				Collections.sort(sentencesList, offsetComparator);

				Iterator sentencesIter = sentencesList.iterator();
				while(sentencesIter.hasNext())
				{
					Annotation currentSentence = (Annotation)sentencesIter.next();
					simdikiCumle = new CumleDugum(cumleIndeks, currentSentence.getStartNode().getOffset(), currentSentence.getEndNode().getOffset());
					arrCumleler.add(simdikiCumle);
					//get tokens of the current sentence
					//java.util.List<Annotation> tokenslist = new java.util.LinkedList<gate.Annotation>(docs[0].getAnnotations().get("Token", currentSentence.getStartNode().getOffset(), currentSentence.getEndNode().getOffset()));
					java.util.List<Annotation> tokenslist = new java.util.LinkedList<gate.Annotation>(doc.getAnnotations().get("Token", currentSentence.getStartNode().getOffset(), currentSentence.getEndNode().getOffset()));
					//sort the token list
					Collections.sort(tokenslist, new OffsetComparator());
					//build the token list
					for (Annotation token : tokenslist) 
					{
						 /*  Set of Noun categories supported by the POS tagger:
					      NN - noun - singular or mass
					      NNP - proper noun - singular: All words in names usually are capitalized 
							but titles might not be.
					      NNPS - proper noun - plural: All words in names usually are capitalized 
							but titles might not be.
					      NNS - noun - plural
					      NP - proper noun - singular
					      NPS - proper noun - plural
					      JJ - adjective: Hyphenated compounds that are used as modifiers; happy-go-lucky.
							JJR - adjective - comparative: Adjectives with the comparative ending '-er' and a comparative
							meaning. Sometimes 'more' and 'less'.
							JJS - adjective - superlative: Adjectives with the superlative ending '-est' (and 'worst').
							Sometimes 'most' and 'least'.
							VBD - verb - past tense: includes conditional form of the verb `to be'; `If I were/VBD rich...'.
							VBG - verb - gerund or present participle
							VBN - verb - past participle
							VBP - verb - non-3rd person singular present
							VB - verb - base form: subsumes imperatives, innitives and subjunctives.
							VBZ - verb - 3rd person singular present
					  */
						kelime = ((String)token.getFeatures().get("string")).trim();
						kelimeKategori = ((String)token.getFeatures().get("category")).trim();
						KelimeDugum tmpKD;
						if ((kelimeKategori.compareTo("NN") == 0) ||
								(kelimeKategori.compareTo("NNP") == 0) ||
								(kelimeKategori.compareTo("NNPS") == 0) ||
								(kelimeKategori.compareTo("NNS") == 0) ||
								(kelimeKategori.compareTo("NP") == 0) ||
								(kelimeKategori.compareTo("NPS") == 0) ||
								(kelimeKategori.compareTo("JJ") == 0) ||
								(kelimeKategori.compareTo("JJR") == 0) ||
								(kelimeKategori.compareTo("JJS") == 0) ||
								(kelimeKategori.compareTo("VBD") == 0) ||
								(kelimeKategori.compareTo("VBG") == 0) ||
								(kelimeKategori.compareTo("VBN") == 0) ||
								(kelimeKategori.compareTo("VBP") == 0) ||
								(kelimeKategori.compareTo("VB") == 0) ||
								(kelimeKategori.compareTo("VBZ") == 0))
						{
							kelimeBas = (long)token.getStartNode().getOffset();
							kelimeBit = (long)token.getEndNode().getOffset();
							tmpKD = new KelimeDugum(cumleIndeks, kelime,
									kelimeBas, kelimeBit, (String)token.getFeatures().get("root"), kelimeKategori);
							simdikiCumle.ekleKelimeDugum(tmpKD);
							if (label_start_end != null && URI_type != null)
							{
								for (int i = aramaDevam; i < label_start_end.length; i++)
								{
									if (label_start_end[i][0] == kelimeBas && label_start_end[i][1] == kelimeBit)
									{
										tmpKD.setEtiketBilgisi(URI_type[i][0], URI_type[i][1]);
										aramaDevam = i + 1;
										break;
									}
								}
							}
						}
					}
					cumleIndeks++;
				}
			}

			ontolojiBaslangicOlustur();
			
			int topBulOnt = 0;
			int topFarkliKelCift = 0;
			OntolojiArama oa = new OntolojiArama();
			ArrayList<String> OntolojiAramaSonuclari = null;
			if (arrCumleler != null)
			{
				cumleIndeks = 0;
				try
				{
					while (cumleIndeks + 1 < arrCumleler.size())
					{
						System.out.println("cümleIndeks: " + cumleIndeks);
						yaz.write("cümleIndeks: " + cumleIndeks);
			        	yaz.newLine();
			        	topBulOnt = 0;
			        	topFarkliKelCift = 0;
			        	int n = 0;
						for (int m = 0; m < arrCumleler.get(cumleIndeks).Kelimeler().size(); m++)
						{
							devam:
							for (n = 0; n < arrCumleler.get(cumleIndeks + 1).Kelimeler().size(); n++)
							{
								if ((arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok().compareTo(arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok())) != 0 &&
										(arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok().compareTo("be") != 0) &&
										(arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok().compareTo("be") != 0))
								{
									//OntolojiAramaSonuclari = oa.SwoogleMotorundaAra(arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok(), 
									//		arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok());
									//if (OntolojiAramaSonuclari.size() == 0)
									//{
									try
									{
										System.out.println("Aranan kelime çifti: " + arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok() + " - " +
												arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok());
										OntolojiAramaSonuclari = oa.SwoogleMotorundaAra(arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok(),
												arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok());
										//OntolojiAramaSonuclari = oa.WatsonMotorundaAra3(arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok(), 
										//		arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok());
									}
									catch (Exception e)
									{
										
									}
									finally
									{
										if (OntolojiAramaSonuclari.size() == 0)
										{
											try
											{
												//OntolojiAramaSonuclari = oa.SwoogleMotorundaAra(arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok(),
												//		arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok());
												OntolojiAramaSonuclari = oa.WatsonMotorundaAra3(arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok(), 
														arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok());
											}
											catch (Exception e)
											{
												
											}
										}
									}
									//}
									ontolojiEkle(OntolojiAramaSonuclari);
									yaz.write("Kelime Çifti: " + arrCumleler.get(cumleIndeks).Kelimeler().get(m).Kelime() + " - " + 
											arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).Kelime());
						        	yaz.newLine();
						        	if (OntolojiAramaSonuclari.size() > 0)
						        	{
						        		topFarkliKelCift++;
						        		topBulOnt = topBulOnt + OntolojiAramaSonuclari.size();
						        		System.out.println("Kelime Çifti: " + arrCumleler.get(cumleIndeks).Kelimeler().get(m).Kelime() + " - " + 
											arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).Kelime() + " - Kelime Kokleri: " +
											arrCumleler.get(cumleIndeks).Kelimeler().get(m).KelimeKok() + " - " +
											arrCumleler.get(cumleIndeks + 1).Kelimeler().get(n).KelimeKok());
						        		for (int y = 0; y < OntolojiAramaSonuclari.size(); y++)
						    			{
						        			System.out.println("Ont: " + OntolojiAramaSonuclari.get(y));
						        			yaz.write("Ont: " + OntolojiAramaSonuclari.get(y));
								        	yaz.newLine();
						    			}
						        	}
								}
							}
						}
						yaz.write("topFarkliKelCift: " + topFarkliKelCift);
			        	yaz.newLine();
						yaz.write("topBulOnt: " + topBulOnt);
			        	yaz.newLine();
			        	System.out.println("topFarkliKelCift: " + topFarkliKelCift);
			        	System.out.println("topBulOnt: " + topBulOnt);
						cumleIndeks++;
					}
				}
				catch (Exception e)
				{
					//System.out.println(e.toString());
					e.printStackTrace();
				}
			}
			
			ontolojiBitisOlustur();
			
			
			FeatureMap fm = Factory.newFeatureMap();
			fm.put("rdfXmlURL", new URL("I:\\Kod\\Workspace\\Deney3\\WatsonOntolojileri.owl"));
			Ontology ont = (Ontology)
				Factory.createResource("gate.creole.ontology.impl.sesame.OWLIMOntology", fm); 

			//create an onto root gazetteer
			params.put("morpher", morph);
			params.put("ontology", ont);
			params.put("posTagger", postagger);
			params.put("tokeniser", tok);
			Gazetteer ontogaz =
				(Gazetteer)Factory.createResource("gate.clone.ql.OntoRootGaz", params);
			controller.add(ontogaz);
			
			//create a flexible gazetteer
			params.clear();
			params.put("gazetteerInst", ontogaz);
			FlexibleGazetteer flexgaz =
				(FlexibleGazetteer)Factory.createResource("gate.creole.gazetteer.FlexibleGazetteer", params);
			controller.add(flexgaz);

			controller.setCorpus(corp);
			controller.execute();

			
			
			
			//BagdasiklikHesapla(arrCumleler);
			
			
			
			/*System.out.println("11");
			AnnotationSet labelsAS = docs[0].getAnnotations().get("Lookup");
			System.out.println(labelsAS.size());
			if(labelsAS != null && labelsAS.size() > 0)
			{
				//define a comparator for annotations by start offset
				Comparator offsetComparator = new OffsetComparator();

				List labelsList = new ArrayList(labelsAS);
				Collections.sort(labelsList, offsetComparator);
				
				Iterator labelsIter = labelsList.iterator();
				while(labelsIter.hasNext())
				{
					Annotation currentLabel = (Annotation)labelsIter.next();
					System.out.println("URIII: " + (currentLabel.getFeatures().get("URI")));
					System.out.println("Start: " + currentLabel.getStartNode().getOffset());
					System.out.println("End: " + currentLabel.getEndNode().getOffset());
					System.out.println(currentLabel.toString());
					//get tokens of the current sentence
					java.util.List<Annotation> tokenslist = new java.util.LinkedList<gate.Annotation>(docs[0].getAnnotations().get("Token", currentLabel.getStartNode().getOffset(), currentLabel.getEndNode().getOffset()));
					//java.util.List<Annotation> tokenslist = new java.util.LinkedList<gate.Annotation>(doc.getAnnotations().get("Token", currentSentence.getStartNode().getOffset(), currentSentence.getEndNode().getOffset()));
					//sort the token list
					Collections.sort(tokenslist, new OffsetComparator());
					for (Annotation token : tokenslist) {
						System.out.println("Lookup URI: " + (String)token.getFeatures().get("URI"));
						//System.out.println((String)token.getFeatures().get("category"));
					}
				}
			}
			System.out.println("22");
*/

		//Cumle ve kelime veri yapilarini yazdir
		
			for (int m = 0; m < arrCumleler.size(); m++)
			{
				System.out.println("Cümle No: " + arrCumleler.get(m).CumleNo() + " Baþlangýç: " + arrCumleler.get(m).CumleBaslangic()
						+ " Bitiþ: " + arrCumleler.get(m).CumleBitis() + " Baðdaþýklýk: " + arrCumleler.get(m).ResnikBagdasiklik());
				for (int n = 0; n < arrCumleler.get(m).Kelimeler().size(); n++)
				{
					System.out.println("Cümle No: " + arrCumleler.get(m).Kelimeler().get(n).CumleNo() +
							" Kelime: " + arrCumleler.get(m).Kelimeler().get(n).Kelime() +
							" Kelime Baþlangýç: " + arrCumleler.get(m).Kelimeler().get(n).KelimeBaslangic() +
							" Kelime Bitiþ: " + arrCumleler.get(m).Kelimeler().get(n).KelimeBitis() +
							" Kelime Kök: " + arrCumleler.get(m).Kelimeler().get(n).KelimeKok() +
							" Kelime Kategori: " + arrCumleler.get(m).Kelimeler().get(n).KelimeKategori() +
							" Etiket Ontoloji URI: " + arrCumleler.get(m).Kelimeler().get(n).EtiketOntolojiURI() +
							" Etiket URI: " + arrCumleler.get(m).Kelimeler().get(n).EtiketURI() +
							" Etiket Tip: " + arrCumleler.get(m).Kelimeler().get(n).EtiketTip());
				}
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	static String WatsonOntStr;
	private static void ontolojiBaslangicOlustur()
	{
		WatsonOntStr = "";
		WatsonOntStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n";
		WatsonOntStr = WatsonOntStr + "<!DOCTYPE rdf:RDF [" + "\n";
		WatsonOntStr = WatsonOntStr + "<!ENTITY owl \"http://www.w3.org/2002/07/owl#\">" + "\n";
		WatsonOntStr = WatsonOntStr + "<!ENTITY rdf \"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">" + "\n";
		WatsonOntStr = WatsonOntStr + "<!ENTITY rdfs \"http://www.w3.org/2000/01/rdf-schema#\">" + "\n";
		WatsonOntStr = WatsonOntStr + "<!ENTITY xsd \"http://www.w3.org/2001/XMLSchema#\">" + "\n";
		WatsonOntStr = WatsonOntStr + "]>" + "\n";
		WatsonOntStr = WatsonOntStr + "<rdf:RDF xml:base=\"&all;\"" + "\n";
		WatsonOntStr = WatsonOntStr + "xmlns:all=\"&all;\"" + "\n";
		WatsonOntStr = WatsonOntStr + "xmlns:owl=\"&owl;\"" + "\n";
		WatsonOntStr = WatsonOntStr + "xmlns:rdf=\"&rdf;\"" + "\n";
		WatsonOntStr = WatsonOntStr + "xmlns:rdfs=\"&rdfs;\"" + "\n";
		WatsonOntStr = WatsonOntStr + "xmlns:xsd = \"&xsd;\">" + "\n";
		WatsonOntStr = WatsonOntStr + "<owl:Ontology rdf:about=\"\" owl:versionInfo=\"2.1\">" + "\n";
	}
	
	private static void ontolojiEkle(ArrayList<String> OntListesi)
	{
		if (OntListesi != null)
		{
			for (int m = 0; m < OntListesi.size(); m++)
			{
				WatsonOntStr = WatsonOntStr + "<owl:imports rdf:resource=\"" + OntListesi.get(m) + "\"/>" + "\n";
			}
		}
	}
	
	private static void ontolojiBitisOlustur()
	{
		WatsonOntStr = WatsonOntStr + "</owl:Ontology>" + "\n";
		WatsonOntStr = WatsonOntStr + "</rdf:RDF>";
		
		try
		{
		//Birlestirilmis ontoloji stringini dosyaya yaz.
		File file = new File("I:\\Kod\\Workspace\\Deney3\\WatsonOntolojileri.owl");
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF8"));
		out.write(WatsonOntStr);
		out.close();
		System.out.println("Birleþtirilmiþ ontoloji dosyasý oluþturuldu.");
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	public static void parseDocuments1()
	{
		try
		{
			showGUI();
			
			corp = Factory.newCorpus("My Corpus");
			corp.add(docs[0]);
			//corp.add(doc);
			
			//create a controller (a.k.a GATE application)
			controller = (SerialAnalyserController)
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
			
			controller.setCorpus(corp);
			controller.execute();

			AnnotationSet sentencesAS = docs[0].getAnnotations().get("Sentence");
			//AnnotationSet sentencesAS = doc.getAnnotations().get("Sentence");
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
				LinkedList<String> firstSentenceTokenList = new LinkedList<String>();
				LinkedList<String> secondSentenceTokenList = new LinkedList<String>();
				boolean firstEntrance = true;
				while(sentencesIter.hasNext())
				{
					for (int i = 0; i < firstSentenceTokenList.size(); i++)
						secondSentenceTokenList.add(firstSentenceTokenList.get(i));
					firstSentenceTokenList.clear();
					System.out.println("Sentence: " + h++);
					Annotation currentSentence = (Annotation)sentencesIter.next();
					//get tokens of the current sentence
					java.util.List<Annotation> tokenslist = new java.util.LinkedList<gate.Annotation>(docs[0].getAnnotations().get("Token", currentSentence.getStartNode().getOffset(), currentSentence.getEndNode().getOffset()));
					//java.util.List<Annotation> tokenslist = new java.util.LinkedList<gate.Annotation>(doc.getAnnotations().get("Token", currentSentence.getStartNode().getOffset(), currentSentence.getEndNode().getOffset()));
					//sort the token list
					Collections.sort(tokenslist, new OffsetComparator());
					//build the token list
					String[][] tokens = new String[tokenslist.size()][2];
					System.out.println(tokenslist.size());
					int k = 0;
					for (Annotation token : tokenslist) {
						tokens[k][0] = (String)token.getFeatures().get("root");
						tokens[k][1] = (String)token.getFeatures().get("category");
						 /*  Set of Noun categories supported by the POS tagger:
					      NN - noun - singular or mass
					      NNP - proper noun - singular: All words in names usually are capitalized 
					but titles might not be.
					      NNPS - proper noun - plural: All words in names usually are capitalized 
					but titles might not be.
					      NNS - noun - plural
					      NP - proper noun - singular
					      NPS - proper noun - plural
					  */
						if ((String)token.getFeatures().get("category") == "NN" ||
								(String)token.getFeatures().get("category") == "NNP" ||
								(String)token.getFeatures().get("category") == "NNPS" ||
								(String)token.getFeatures().get("category") == "NNS" ||
								(String)token.getFeatures().get("category") == "NP" ||
								(String)token.getFeatures().get("category") == "NPS")
						{
							firstSentenceTokenList.add((String)token.getFeatures().get("root"));
						}
						System.out.println(tokens[k][0]);
						System.out.println(tokens[k][1]);
						k++;
					}
					//System.out.println(currentSentence.toString());
					//System.out.println(currentSentence.getFeatures().get("StartNode"));
					if (secondSentenceTokenList.size() != 0)
					{
						//compareSentences(firstSentenceTokenList, secondSentenceTokenList);
						String String1;
						String String2;
						try
						{
						for (int m = 0; m < firstSentenceTokenList.size(); m++)
						{
							for (int n = 0; n < secondSentenceTokenList.size(); n++)
							{
								String1 = firstSentenceTokenList.get(m);
								String2 = secondSentenceTokenList.get(n);
								ArrayList<String> ontURIs;
								ArrayList<Ontology> ont = new ArrayList<Ontology>();
								FeatureMap fm = Factory.newFeatureMap();
								ArrayList<Gazetteer> ontogaz = new ArrayList<Gazetteer>();
								ArrayList<FlexibleGazetteer> flexgaz = new ArrayList<FlexibleGazetteer>();
									OntolojiArama oa = new OntolojiArama();
									System.out.println("WATSON ARAMA: " + String1 + " VE " + String2);
									ontURIs = oa.WatsonMotorundaAra3(String1, String2);
									if (ontURIs != null)
									{
										contgetontologies:
										for (int i = 0; i < ontURIs.size(); i++)
										{
											try
											{
												fm.clear();
												fm.put("rdfXmlURL", new URL(ontURIs.get(i)));
												System.out.println("URI: " + ontURIs.get(i));
												ont.add((Ontology)Factory.createResource("gate.creole.ontology.impl.sesame.OWLIMOntology", fm));
												//create an onto root gazetteer
												params.clear();
												params.put("morpher", morph);
												params.put("ontology", ont.get(ont.size() - 1));
												params.put("posTagger", postagger);
												params.put("tokeniser", tok);
												ontogaz.add((Gazetteer)Factory.createResource("gate.clone.ql.OntoRootGaz", params));
												controller.add(ontogaz.get(ontogaz.size() - 1));
					
												//create a flexible gazetteer
												params.clear();
												params.put("gazetteerInst", ontogaz);
												flexgaz.add((FlexibleGazetteer)Factory.createResource("gate.creole.gazetteer.FlexibleGazetteer", params));
												controller.add(flexgaz.get(flexgaz.size() - 1));
											}
											catch (Exception e)
											{
												System.out.println(e.toString());
												continue contgetontologies;
											}			
										}//for
									}//if
								
								}
							}
							controller.setCorpus(corp);
							controller.execute();					
						}//try
						catch (Exception e)
						{
							System.out.println(e.toString());
						}
					}
				}
			}
			else
			{
				System.out.println("No sentences were found in the document");
			}

		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	public static void compareSentences(LinkedList<String> firstSentenceTokenList, LinkedList<String> secondSentenceTokenList)
	{
		for (int i = 0; i < firstSentenceTokenList.size(); i++)
			System.out.print(firstSentenceTokenList.get(i).toString() + " - ");
		System.out.println();
		for (int i = 0; i < secondSentenceTokenList.size(); i++)
			System.out.print(secondSentenceTokenList.get(i).toString() + " - ");
		System.out.println();
		
		/*String ontolojiURI;
		String String1 = firstSentenceTokenList.get(1);
		String String2 = secondSentenceTokenList.get(1);
		double benzerlik = 0;
		try
		{
			OntolojiArama oa = new OntolojiArama(String1, String2);
			do
			{
				ontolojiURI = oa.OntolojiAdresiniAl();
				if (ontolojiURI != "")
				{
					benzerlik = Patika.compareOntologyConcepts(ontolojiURI, String1, String2);
					System.out.println("Benzerlik: " + benzerlik);
				}
			} while (benzerlik == 0 && ontolojiURI != "");
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}*/

		String String1 = firstSentenceTokenList.get(0);
		String String2 = secondSentenceTokenList.get(0);
		ArrayList<String> ontURIs;
		Ontology ont;
		FeatureMap fm = Factory.newFeatureMap();
		try
		{
			OntolojiArama oa = new OntolojiArama();
			//ontURIs = oa.WatsonMotorundaAra3(String1, String2);
			ontURIs = new ArrayList<String>();
			ontURIs.add("http://sweet.jpl.nasa.gov/2.1/sweetAll.owl");
			if (ontURIs != null)
			{
				for (int i = 0; i < ontURIs.size(); i++)
				{
					fm.clear();
					fm.put("rdfXmlURL", new URL(ontURIs.get(i)));
					ont = (Ontology)Factory.createResource("gate.creole.ontology.impl.sesame.OWLIMOntology", fm);
				}
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	public static void showGUI()
	{
		MainFrame mainFrame = new MainFrame();
		mainFrame.setSize(800, 600);
		mainFrame.setVisible(true);
	}
}
