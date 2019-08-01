import java.io.BufferedWriter;
import java.io.FileWriter;
//import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModelSpec;
//import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
//import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.ModelFactory;
//import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.OWL;


public class Patika {

	static OntModel model;
    static String rdfns = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    static String rdfsns = "http://www.w3.org/2000/01/rdf-schema#";
    static String owlns = "http://www.w3.org/2002/07/owl#";
    static String xsdns = "http://www.w3.org/2001/XMLSchema#";

    static List<Kenar> patikalar;
    //static List<String> ziyaretEdilmisDugumler;
    static List<KaynakDugum> ziyaretEdilmisDugumler;
    static List<Kenar> ziyaretEdilmisKenarlar;
    static List<Dugum> hedefDugumler;

    static String onlolojiDokumaniAdi = "publication.owl";
    static String ontolojiDokumaniURI = "http://www.w3.org/2001/sw/WebOnt/guide-src/wine";
    static String ontolojiDokumaniDizini = "file:///I:/Kod/Workspace/Deney/";
    static String ontolojiDokumaniDizini1 = "I:\\Kod\\Workspace\\Deney\\";
    static String ontolojiDokumaniYeri = ontolojiDokumaniDizini + onlolojiDokumaniAdi;
    
    /*static final double degerInverseOf = 0.84478;
    static final double degerEquivalentClass = 0.79435;
    static final double degerSubClassOf_ozelden_genele = 0.72171;
    static final double degerDomain_ozellikten_konuAlanina = 0.70158;
    static final double degerSubPropertyOf_ozelden_genele = 0.69873;
    static final double degerSubClassOf_genelden_ozele = 0.69154;
    static final double degerEquivalentProperty = 0.67092;
    static final double degerDisjointWith = 0.65896;
    static final double degerSubPropertyOf_genelden_ozele = 0.65283;
    static final double degerDomain_konuAlanindan__ozellige = 0.57998;
    static final double degerRange_ozellikten_menzile = 0.50652;
    static final double degerRange_menzilden_ozellige = 0.29493;
    static final double degerDifferentFrom = 0.17772;*/

    static final double degerInverseOf = 0.85254;
    static final double degerEquivalentClass = 0.80228;
    static final double degerSubClassOf_ozelden_genele = 0.72149;
    static final double degerDomain_ozellikten_konuAlanina = 0.69768;
    static final double degerSubPropertyOf_ozelden_genele = 0.70409;
    static final double degerSubClassOf_genelden_ozele = 0.69384;
    static final double degerEquivalentProperty = 0.68156;
    static final double degerDisjointWith = 0.65774;
    static final double degerSubPropertyOf_genelden_ozele = 0.66135;
    static final double degerDomain_konuAlanindan_ozellige = 0.58072;
    static final double degerRange_ozellikten_menzile = 0.50690;
    static final double degerRange_menzilden_ozellige = 0.30593;
    static final double degerDifferentFrom = 0.19194;

    public static void main(String[] args) {
    	//ontolojidekiIfadeleriListele();
    	
    	model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
        //model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
        model.getDocumentManager().addAltEntry( ontolojiDokumaniURI, ontolojiDokumaniYeri );
        model.read( ontolojiDokumaniURI );

        //printIterator(model.listClasses());
        //printIterator(model.listSubjects());
        
        //IfadeleriYazdir(model);
        
        //ziyaretEdilmisDugumler = new LinkedList<String>();
        ziyaretEdilmisDugumler = new LinkedList<KaynakDugum>();
        ziyaretEdilmisKenarlar = new LinkedList<Kenar>();
        
        String baslangicDugum;
        String bitisDugum;
        baslangicDugum= "http://ebiquity.umbc.edu/ontology/publication.owl#Book";
        bitisDugum = "http://ebiquity.umbc.edu/ontology/publication.owl#author";
        PatikaBul(baslangicDugum, bitisDugum);
        System.out.println("BITTI"); 
        PatikalariDosyayaYazdir();
        //printIteratorKenar(IfadeleriListele("http://keg.cs.tsinghua.edu.cn/ontology/software#Person").iterator());
	
	}
	
    public static double compareOntologyConcepts(String ontologyURI, String concept1, String concept2) //throws Exception
    {
    	String baslangicDugum;
        String bitisDugum;
    	try
    	{
	    	model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
	        //model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
	        model.read( ontologyURI );
	        //System.out.println("Ontoloji okundu");
	        
	        OntResource or1 = model.getOntResource(concept1);
	        OntResource or2 = model.getOntResource(concept2);
	        
	        if (or1 == null || or2 == null)
	        {
	        	System.out.println("Düðümler bulunamadý");
	        	return 0;
	        }
	        
	        baslangicDugum = or1.toString();
	        bitisDugum = or2.toString();
	        
	        //System.out.println("Düðümler bulundu");
	        ziyaretEdilmisDugumler = new LinkedList<KaynakDugum>();
	        ziyaretEdilmisKenarlar = new LinkedList<Kenar>();
	        PatikaBul(baslangicDugum, bitisDugum);
	        return PatikaDegeriHesapla();
    	}
    	catch (Exception e)
    	{
			//System.out.println("BURADAAAAAAAA");
			e.printStackTrace();

    		return 0;
    		//throw e;
    	}
    }
    
    public static void Basla()
    {
        model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
        //model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
        model.getDocumentManager().addAltEntry( ontolojiDokumaniURI, ontolojiDokumaniYeri );
        model.read( ontolojiDokumaniURI );
    }
    
    public static ExtendedIterator<OntClass> SiniflariAl()
    {
    	Basla();
    	return model.listClasses();
    }
    
    public static void IfadeleriYazdir(OntModel m)
    {
	    fileIterator(m.listStatements(), null);
	    
    }
    
	public static void PatikaBul(String baslangicDugum, String bitisDugum)
	{
        patikalar = new LinkedList<Kenar>();
        hedefDugumler = new LinkedList<Dugum>();
        //System.out.println("baslangicDugum: " + baslangicDugum);
        //System.out.println("bitisDugum: " + bitisDugum);
        CocuklariBul(baslangicDugum, bitisDugum, (Dugum)null, 0);
        
	}

	public static void CocuklariBul(String baslangicDugum, String bitisDugum, Dugum eklenenDugum, int derinlik)
	{
		//Baslangic dugumunun daha önce ziyaret edilip edilmedigini kontrol et.
		//if (ziyaretEdilmisDugumler.contains(baslangicDugum))
		//{
			//Baslangic dugumu daha once ziyaret edilmisse islem yapmadan cik.
			//return;
		//}
		//else
		//{
			//Baslangic dugumu ilk defa ziyaret ediliyorsa bu dugumu ziyaret edilmis dugumler listesine ekle.
			//ziyaretEdilmisDugumler.add(baslangicDugum);
		//}
		
		KaynakDugum geciciDugum;
		Iterator<?> j = ziyaretEdilmisDugumler.iterator();
		boolean ekle = true;
		while (j.hasNext())
		{
			geciciDugum = (KaynakDugum)j.next();
			if (geciciDugum.Dugum().compareTo(baslangicDugum) == 0) 
			{
				if (derinlik != geciciDugum.Derinlik())
				{
					return;
				}
				else
				{
					ekle = false;
					break;
				}
			}
		}
		if (ekle)
			ziyaretEdilmisDugumler.add(new KaynakDugum(baslangicDugum, derinlik));
		
		//List<Kenar> ifadeListesi = new LinkedList<Kenar>();
		//ifadeListesi = IfadeleriListele(baslangicDugum);
		Iterator<Kenar> ifadeListesi;
		ifadeListesi = (IfadeleriListele(baslangicDugum)).iterator();
		Kenar k;
		boolean bayrak = false;
		boolean ziyaretEdilmismi;
		
		if (ifadeListesi != null)
		{
			while (ifadeListesi.hasNext())
			{
				bayrak = false;
				k = (Kenar)ifadeListesi.next();
				/*Kenar gecici;
				Iterator<?> i = ziyaretEdilmisKenarlar.iterator();*/
				ziyaretEdilmismi = false;
				/*while (i.hasNext())
				{
					gecici = (Kenar)i.next();
					if ((gecici.ifadeAl().getSubject().toString().compareTo(k.ifadeAl().getSubject().toString()) == 0) && 
					(gecici.ifadeAl().getPredicate().toString().compareTo(k.ifadeAl().getPredicate().toString()) == 0) &&
					(gecici.ifadeAl().getObject().toString().compareTo(k.ifadeAl().getObject().toString()) == 0))
					{
						ziyaretEdilmismi = true;
						break;
					}
				}*/
				if (!ziyaretEdilmismi)
				{
					//Baslangic dugumu ilk defa ziyaret ediliyorsa bu dugumu ziyaret edilmis dugumler listesine ekle.
					ziyaretEdilmisKenarlar.add(k);

					//System.out.println("BASLA: " + k.ifadeAl().toString());
					
					if (k.yonAl())
					{
			        	if ((k.ifadeAl().getSubject().toString().compareTo(baslangicDugum) == 0) && (k.ifadeAl().getObject().toString().compareTo(bitisDugum) == 0))
			        	{
			        		//baslangic ile bitis dugumler arasinda bir ifadeden olusan iliski var
			        		Dugum d = new Dugum(k, eklenenDugum);
			            	hedefDugumler.add(d);
			            	bayrak = true;
			            	//System.out.println("BULDUM");
			            	//PatikaYazdir(d);
			            	//PatikaDosyayaYazdir(d);
			        	}
					}
					else
					{
			        	if ((k.ifadeAl().getSubject().toString().compareTo(bitisDugum) == 0) && (k.ifadeAl().getObject().toString().compareTo(baslangicDugum) == 0))
			        	{
			        		//baslangic ile bitis dugumler arasinda bir ifadeden olusan iliski var
			        		Dugum d = new Dugum(k, eklenenDugum);
			            	hedefDugumler.add(d);
			            	bayrak = true;
			            	//System.out.println("BULDUM");
			            	//PatikaYazdir(d);
			            	//PatikaDosyayaYazdir(d);
			        	}
					}
					
					if (!bayrak)
					{
						if (k.yonAl())
						{
							//System.out.println(derinlik + ". CocuklariBul(" + k.ifadeAl().getObject().toString() + ", " + bitisDugum + ")");
							CocuklariBul(k.ifadeAl().getObject().toString(), bitisDugum, new Dugum(k, eklenenDugum), ++derinlik);
						}
						else
						{
							//System.out.println(derinlik + ". CocuklariBul(" + k.ifadeAl().getSubject().toString() + ", " + bitisDugum + ")");
							CocuklariBul(k.ifadeAl().getSubject().toString(), bitisDugum, new Dugum(k, eklenenDugum), ++derinlik);
						}
					}
				}

			}
		}
		//Baslangic dugumu ilk defa ziyaret ediliyorsa bu dugumu ziyaret edilmis dugumler listesine ekle.
		//ziyaretEdilmisDugumler.add(baslangicDugum);
	}
	
	public static List<Kenar> IfadeleriListele(String baslangicDugum)
	{
    	List<Kenar> ifadeler = new LinkedList<Kenar>();
    	Statement ifade;
    	Iterator<Statement> gecici = model.listStatements();

		Resource kaynak = model.getResource(baslangicDugum);
    	
        if (kaynak == null)
        	return null;
        
    	if(gecici.hasNext()) 
    	{
	        while (gecici.hasNext()) 
	        {
	        	ifade = (Statement)gecici.next();
	        	
		        if ((ifade.getSubject().toString().compareTo(baslangicDugum) == 0) ||
		        		(ifade.getObject().toString().compareTo(baslangicDugum) == 0))
		        {
		        	if (ifade.getSubject().toString().compareTo(ifade.getObject().toString()) != 0)
		        	{
			        	if (ifade.getObject().isURIResource())
			            {
			            	if ((((Resource)ifade.getObject()).getNameSpace().compareTo(xsdns)) != 0)
			            	{
					            if (((ifade.getPredicate().toString().compareTo(rdfns + "type") == 0) & (ifade.getObject().toString().compareTo(owlns + "Thing") == 0)) ||
					            		((ifade.getPredicate().toString().compareTo(rdfsns + "subClassOf") == 0) & (ifade.getObject().toString().compareTo(owlns + "Thing") == 0)) ||
					            		((ifade.getPredicate().toString().compareTo(rdfns + "type") == 0) & (ifade.getObject().toString().compareTo(owlns + "Class") == 0)) ||
					            		((ifade.getPredicate().toString().compareTo(rdfns + "type") == 0) & (ifade.getObject().toString().compareTo(owlns + "ObjectProperty") == 0)) ||
					            		((ifade.getPredicate().toString().compareTo(rdfns + "type") == 0) & (ifade.getObject().toString().compareTo(owlns + "DatatypeProperty") == 0)) ||
					            		((ifade.getPredicate().toString().compareTo(rdfns + "type") == 0) & (ifade.getObject().toString().compareTo(owlns + "FunctionalProperty") == 0)) ||
					            		((ifade.getPredicate().toString().compareTo(rdfns + "type") == 0) & (ifade.getObject().toString().compareTo(owlns + "InverseFunctionalProperty") == 0)) ||
					            		((ifade.getPredicate().toString().compareTo(rdfns + "type") == 0) & (ifade.getObject().toString().compareTo(owlns + "TransitiveProperty") == 0)) ||
					            		((ifade.getPredicate().toString().compareTo(rdfns + "type") == 0) & (ifade.getObject().toString().compareTo(owlns + "SymmetricProperty") == 0)) ||
					            		((ifade.getPredicate().toString().compareTo(rdfns + "type") == 0) & (ifade.getObject().toString().compareTo(owlns + "Restriction") == 0)) ||
					            		((ifade.getPredicate().toString().compareTo(rdfns + "type") == 0) & (ifade.getObject().toString().compareTo(owlns + "DataRange") == 0)) ||
					            		((ifade.getPredicate().toString().compareTo(rdfns + "type") == 0) & (ifade.getObject().toString().compareTo(owlns + "Ontology") == 0)) ||
					            		((ifade.getSubject().toString().compareTo(ifade.getObject().toString())) == 0) || //equivalentClass, equivalentProperty, sameAs, subPropertyOf  
					    	        	//(((ifade.getPredicate().getURI().compareTo(owlns + "equivalentClass") == 0) & (ifade.getSubject().getURI().compareTo(ifade.getObject().toString())) == 0)) ||
					    	        	//(((ifade.getPredicate().getURI().compareTo(owlns + "equivalentProperty") == 0) & (ifade.getSubject().getURI().compareTo(ifade.getObject().toString())) == 0)) ||
					    	        	//(((ifade.getPredicate().getURI().compareTo(owlns + "sameAs") == 0) & (ifade.getSubject().getURI().compareTo(ifade.getObject().toString())) == 0)) ||
					            		//ifade.getObject().isLiteral() ||
					            		//?????????(ifade.getObject().isURIResource() & (((Resource)ifade.getObject()).getNameSpace().compareTo(xsdns)) ==0) ||
					            		(ifade.getPredicate().toString().compareTo(rdfns + "type") == 0) || //su anda http://www.w3.org/1999/02/22-rdf-syntax-ns#type'in anlamsal ilintililik katsayisi yok
					            		(ifade.getObject().toString().compareTo(rdfsns + "label") == 0) ||
					            		(ifade.getObject().toString().compareTo(rdfsns + "comment") == 0) ||
					            		(ifade.getObject().toString().compareTo(owlns + "topDataProperty") == 0) ||
					    	        	(ifade.getObject().toString().compareTo(owlns + "bottomDataProperty") == 0) ||
					    	        	(ifade.getSubject().toString().compareTo(owlns + "bottomDataProperty") == 0) ||
					            		(ifade.getObject().toString().compareTo(owlns + "topObjectProperty") == 0) ||
					    	        	(ifade.getObject().toString().compareTo(owlns + "bottomObjectProperty") == 0) ||
					    	        	(ifade.getSubject().toString().compareTo(owlns + "bottomObjectProperty") == 0) ||
					    	        	(ifade.getObject().toString().compareTo(owlns + "Nothing") == 0) ||
					    	        	(ifade.getSubject().toString().compareTo(owlns + "Nothing") == 0) ||
					    	        	(ifade.getPredicate().toString().compareTo(owlns + "propertyDisjointWith") == 0) ||
					    	        	(ifade.getObject().toString().compareTo(rdfns + "nil") == 0))
					            {
					            	//Bazi cikarsama ifadelerini ele. Bunlar patika cikarmak icin gerekli degil.
					            } 
					            else if (ifade.getPredicate().toString().compareTo(rdfsns + "subClassOf") == 0)
					            {
		
					            	//Subject'in sadece direk superclass'larini al. Diger turlu hiyerarsideki genelligi hesaplayamayiz.
						            OntClass oc = model.getOntClass(ifade.getSubject().getURI());
						            //ExtendedIterator<OntClass> j = oc.listSuperClasses(true);
						            ExtendedIterator<OntClass> j = oc.listSuperClasses(false);
						            OntClass tmp;
						            if(j.hasNext()) {
					        	        while (j.hasNext()) 
					        	        {
					        	        	tmp = j.next();
					        	        	if ((tmp.getURI() != owlns + "Thing") && (tmp.isURIResource()))
					        	        		//Thing superclass'ini goz ardi et
					        	        		//URý'si olmayan kaynaklari goz ardi et
					        	        	{
						        	        	if (ifade.getObject().toString().compareTo(tmp.getURI()) == 0)
						        	        	{
						        	        		if (ifade.getSubject().toString().compareTo(kaynak.toString()) == 0)
						        	        		{
						        	        			ifadeler.add(new Kenar(ifade, true));
						        	        		}
						        	        		else
						        	        		{
						        	        			ifadeler.add(new Kenar(ifade, false));
						        	        		}
						        	        		//System.out.println(" - " + ifade.toString());
						        	        		break;
						        	        	}
					        	        	}
					        	        }
					                }
					            }
						        else if ((ifade.getPredicate().toString().compareTo(rdfsns + "domain") == 0) || 
						        		(ifade.getPredicate().toString().compareTo(rdfsns + "range") == 0) //|| 
						        		//(ifade.getPredicate().toString().compareTo(rdfsns + "subClassOf") == 0))
						        	)
						        {
						        	/*if ((ifade.getObject().isResource()) || (ifade.getObject().isAnon()))
						        	{
						        		if (ifade.getObject().toString().compareTo(kaynak.toString()) == 0)
						        			ifadeler.add(new Kenar(ifade, false));
						        	}
						        	else if ((ifade.getSubject().isResource()) || (ifade.getSubject().isAnon()))
						        	{
						        		if (ifade.getSubject().toString().compareTo(kaynak.toString()) == 0)
						        			ifadeler.add(new Kenar(ifade, true));
						        	}*/
						        	if (ifade.getSubject().toString().compareTo(kaynak.toString()) == 0)
						        	{
						        		if ((ifade.getObject().isResource()) || (ifade.getObject().isAnon()))
						        		{
						        			ifadeler.add(new Kenar(ifade, true));
						        		}
						        	}
						        	else if (ifade.getObject().toString().compareTo(kaynak.toString()) == 0)
						        	{
						        		if ((ifade.getSubject().isResource()) || (ifade.getSubject().isAnon()))
						        		{
						        			ifadeler.add(new Kenar(ifade, false));		
						        		}
						        	}
						        }
					            else if (ifade.getSubject().toString().compareTo(kaynak.toString()) == 0)
					        	{
					        		ifadeler.add(new Kenar(ifade, true));
					        	}
					            else if (ifade.getObject().toString().compareTo(kaynak.toString()) == 0)
					        	{
					        		ifadeler.add(new Kenar(ifade, false));
					        	}
					            else
					            {
					        		//ifadeler.add(ifade);
					        		//System.out.println(" - " + ifade.toString());
					        	}
			            	}
			            }
		        	}		        	
		        }
	        }
        }       

    	return ifadeler;
	}
	
    public static void printIterator(Iterator<?> i) {
        
        if(i.hasNext()) {
	        while (i.hasNext()) 
	            System.out.println( i.next() );
        }       
        else
            System.out.println("<EMPTY>");
    }

    public static void printIteratorKenar(Iterator<?> i) {
        
    	Kenar k;
        if(i.hasNext()) {
	        while (i.hasNext()) 
	        {
	        	k = (Kenar)(i.next());
	        	System.out.println( k.ifadeAl() + " - YÖN: " + k.yonAl());
	        }
        }       
        else
            System.out.println("<EMPTY>");
    }

    private static void PatikaYazdir(Dugum d)
    {
    	Dugum gecici;
    	
    	gecici = d;
    	
    	do
    	{
    		System.out.println(gecici.Kenar().ifadeAl().toString());
    		gecici = gecici.AtaDugum();
    	} while (gecici != null);
    }
    
    private static void PatikaDosyayaYazdir(Dugum d)
    {
    	try
    	{
    		BufferedWriter yaz = new BufferedWriter(new FileWriter(ontolojiDokumaniDizini1 + onlolojiDokumaniAdi + "_sonuc.txt", true));
			Dugum gecici;
	    	gecici = d;
	    	yaz.write("SONUC");
	    	do
	    	{
	        	yaz.write(gecici.Kenar().ifadeAl().toString());
	        	yaz.newLine();
	    		gecici = gecici.AtaDugum();
	    	} while (gecici != null);
	    	yaz.close();
    	}
    	catch(Exception e)
    	{
            e.printStackTrace();
        }
    }
    
    private static void PatikalariDosyayaYazdir()
    {
    	LinkedList<LinkedList<Kenar>> PatikaBaslangiclari;
    	LinkedList<Integer> PatikaUzunluklari;
    	
    	PatikaBaslangiclari = new LinkedList<LinkedList<Kenar>>();
    	PatikaUzunluklari = new LinkedList<Integer>();
    	
		Iterator<Dugum> i = hedefDugumler.iterator();
		Dugum geciciDugum;
		
        if(i.hasNext()) {
	        while (i.hasNext()) 
	        {
	        	LinkedList<Kenar> Patika = new LinkedList<Kenar>();
	        	int PatikaUzunlugu = 0;
	        	geciciDugum = (Dugum)(i.next());
		    	do
		    	{
		        	Patika.add(Patika.size(), geciciDugum.Kenar());
		        	PatikaUzunlugu++;
		    		geciciDugum = geciciDugum.AtaDugum();
		    	} while (geciciDugum != null);
		    	boolean eklendimi = false;
		    	for (int j = 0; j < PatikaUzunluklari.size(); j++)
		    	{
		    		if (PatikaUzunluklari.get(j) > PatikaUzunlugu)
		    		{
				    	PatikaBaslangiclari.add(j, Patika);
				    	PatikaUzunluklari.add(j, PatikaUzunlugu);
				    	eklendimi = true;
		    			break;
		    		}
		    	}
		    	if (!eklendimi)
		    	{
			    	PatikaBaslangiclari.add(Patika);
			    	PatikaUzunluklari.add(PatikaUzunlugu);
		    	}
	        }
        } 
        
        BufferedWriter yaz;
    	try
    	{
    		/*BufferedWriter yaz = new BufferedWriter(new FileWriter("I:\\Kod\\Workspace\\Deney1\\sonuc.txt", true));
			Dugum gecici;
	    	gecici = d;
	    	yaz.write("SONUC");*/

    		LinkedList<Kenar> geciciBagliListe;
    		yaz = new BufferedWriter(new FileWriter(ontolojiDokumaniDizini1 + onlolojiDokumaniAdi + "_sonuc.txt", true));
    		
	    	for (int m = 0; m < PatikaBaslangiclari.size(); m++)
	    	{
	    		System.out.println("Uzunluk: " + PatikaUzunluklari.get(m).toString());
	        	//yaz.write("Uzunluk: " + PatikaUzunluklari.get(m).toString());
	        	//yaz.newLine();
	    		geciciBagliListe = PatikaBaslangiclari.get(m);
	    		for (int n = 0; n < geciciBagliListe.size(); n++)
	    		{
	    			System.out.println(geciciBagliListe.get(n).ifadeAl().toString() + " - " + geciciBagliListe.get(n).yonAl());
		        	//yaz.write(geciciBagliListe.get(n).ifadeAl().toString() + " - " + geciciBagliListe.get(n).yonAl());
		        	//yaz.newLine();
	    		}
	    	}
    		if (yaz != null)
    			yaz.close();
	    	
	    	/*do
	    	{
	        	yaz.write(gecici.Kenar().ifadeAl().toString());
	        	yaz.newLine();
	    		gecici = gecici.AtaDugum();
	    	} while (gecici != null);
	    	
	    	
	    	yaz.close();*/
    	}
    	catch(Exception e)
    	{
            e.printStackTrace();
        }
    }

    private static double PatikaDegeriHesapla()
    {
		Iterator<Dugum> i = hedefDugumler.iterator();
		Dugum geciciDugum;
    	double patika_degeri = 1;
        double minPatikaDeger = 0;
		Hashtable<String, Integer> htmin = null;
		Hashtable<String, Integer> httmp = new Hashtable<String, Integer>();
		int tmp_int = 0;
		//int cnt = 0;
		//int cntmin = 0;
        if(i.hasNext()) {
	        while (i.hasNext()) 
	        {
	        	geciciDugum = (Dugum)(i.next());
	        	patika_degeri = 1;
	        	httmp.clear();
    			//System.out.println("-------------------------------------------");
        		//cnt++;
	        	//if (cnt == 105)
	        	//	System.out.println("105");
        		do
		    	{
		    		//////////////////////////////
	        		tmp_int = 0;
		        	if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#subClassOf") == 0 ||
		        			geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2002/07/owl#subClassOf") == 0)
		        	{
		        		if (geciciDugum.Kenar().yonAl())
		        		{
		        			patika_degeri = patika_degeri * degerSubClassOf_ozelden_genele;
		        			//System.out.println("SubClassOf_ozelden_genele");
		        			//System.out.println("patika_degeri: " + patika_degeri);
		        			if(httmp.containsKey("SubClassOf_ozelden_genele"))
							{
								tmp_int = (Integer) httmp.get("SubClassOf_ozelden_genele");
								tmp_int++;
								httmp.remove("SubClassOf_ozelden_genele");
								httmp.put("SubClassOf_ozelden_genele", new Integer(tmp_int));
							}
							else
							{
								httmp.put("SubClassOf_ozelden_genele", new Integer(1));
							}
		        		}
		        		else
		        		{
		        			patika_degeri = patika_degeri * degerSubClassOf_genelden_ozele;
		        			//System.out.println("SubClassOf_genelden_ozele");
		        			//System.out.println("patika_degeri: " + patika_degeri);
							if(httmp.containsKey("SubClassOf_genelden_ozele"))
							{
								tmp_int = (Integer) httmp.get("SubClassOf_genelden_ozele");
								tmp_int++;
								httmp.remove("SubClassOf_genelden_ozele");
								httmp.put("SubClassOf_genelden_ozele", new Integer(tmp_int));
							}
							else
							{
								httmp.put("SubClassOf_genelden_ozele", new Integer(1));
							}
		        		}
		        	}
		        	else if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#subPropertyOf") == 0 ||
		        			geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2002/07/owl#subPropertyOf") == 0)
		        	{
		        		if (geciciDugum.Kenar().yonAl())
		        		{
		        			patika_degeri = patika_degeri * degerSubPropertyOf_ozelden_genele;
							if(httmp.containsKey("SubPropertyOf_ozelden_genele"))
							{
								tmp_int = (Integer) httmp.get("SubPropertyOf_ozelden_genele");
								tmp_int++;
								httmp.remove("SubPropertyOf_ozelden_genele");
								httmp.put("SubPropertyOf_ozelden_genele", new Integer(tmp_int));
							}
							else
							{
								httmp.put("SubPropertyOf_ozelden_genele", new Integer(1));
							}
		        		}
		        		else
		        		{
		        			patika_degeri = patika_degeri * degerSubPropertyOf_genelden_ozele;
							if(httmp.containsKey("SubPropertyOf_genelden_ozele"))
							{
								tmp_int = (Integer) httmp.get("SubPropertyOf_genelden_ozele");
								tmp_int++;
								httmp.remove("SubPropertyOf_genelden_ozele");
								httmp.put("SubPropertyOf_genelden_ozele", new Integer(tmp_int));
							}
							else
							{
								httmp.put("SubPropertyOf_genelden_ozele", new Integer(1));
							}
		        		}
		        	}
		        	else if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#domain") == 0 ||
		        			geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2002/07/owl#domain") == 0)
		        	{
		        		if (geciciDugum.Kenar().yonAl())
		        		{
		        			patika_degeri = patika_degeri * degerDomain_konuAlanindan_ozellige;
							if(httmp.containsKey("Domain_konuAlanindan_ozellige"))
							{
								tmp_int = (Integer) httmp.get("Domain_konuAlanindan_ozellige");
								tmp_int++;
								httmp.remove("Domain_konuAlanindan_ozellige");
								httmp.put("Domain_konuAlanindan_ozellige", new Integer(tmp_int));
							}
							else
							{
								httmp.put("Domain_konuAlanindan_ozellige", new Integer(1));
							}
		        		}
		        		else
		        		{
		        			patika_degeri = patika_degeri * degerDomain_ozellikten_konuAlanina;
							if(httmp.containsKey("Domain_ozellikten_konuAlanina"))
							{
								tmp_int = (Integer) httmp.get("Domain_ozellikten_konuAlanina");
								tmp_int++;
								httmp.remove("Domain_ozellikten_konuAlanina");
								httmp.put("Domain_ozellikten_konuAlanina", new Integer(tmp_int));
							}
							else
							{
								httmp.put("Domain_ozellikten_konuAlanina", new Integer(1));
							}
		        		}
		        	}
		        	else if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#range") == 0 ||
		        			geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2002/07/owl#range") == 0)
		        	{
		        		if (geciciDugum.Kenar().yonAl())
		        		{
		        			patika_degeri = patika_degeri * degerRange_ozellikten_menzile;
							if(httmp.containsKey("Range_ozellikten_menzile"))
							{
								tmp_int = (Integer) httmp.get("Range_ozellikten_menzile");
								tmp_int++;
								httmp.remove("Range_ozellikten_menzile");
								httmp.put("Range_ozellikten_menzile", new Integer(tmp_int));
							}
							else
							{
								httmp.put("Range_ozellikten_menzile", new Integer(1));
							}
		        		}
		        		else
		        		{
		        			patika_degeri = patika_degeri * degerRange_menzilden_ozellige;
							if(httmp.containsKey("Range_menzilden_ozellige"))
							{
								tmp_int = (Integer) httmp.get("Range_menzilden_ozellige");
								tmp_int++;
								httmp.remove("Range_menzilden_ozellige");
								httmp.put("Range_menzilden_ozellige", new Integer(tmp_int));
							}
							else
							{
								httmp.put("Range_menzilden_ozellige", new Integer(1));
							}
		        		}
		        	}
		        	else if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#inverseOf") == 0 ||
		        			geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2002/07/owl#inverseOf") == 0)
		        	{
		        		patika_degeri = patika_degeri * degerInverseOf;
						if(httmp.containsKey("InverseOf"))
						{
							tmp_int = (Integer) httmp.get("InverseOf");
							tmp_int++;
							httmp.remove("InverseOf");
							httmp.put("InverseOf", new Integer(tmp_int));
						}
						else
						{
							httmp.put("InverseOf", new Integer(1));
						}
		        	}
		        	else if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#equivalentClass") == 0 ||
		        			geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2002/07/owl#equivalentClass") == 0)
		        	{
		        		patika_degeri = patika_degeri * degerEquivalentClass;
						if(httmp.containsKey("EquivalentClass"))
						{
							tmp_int = (Integer) httmp.get("EquivalentClass");
							tmp_int++;
							httmp.remove("EquivalentClass");
							httmp.put("EquivalentClass", new Integer(tmp_int));
						}
						else
						{
							httmp.put("EquivalentClass", new Integer(1));
						}
		        	}
		        	else if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#equivalentProperty") == 0 ||
		        			geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2002/07/owl#equivalentProperty") == 0)
		        	{
		        		patika_degeri = patika_degeri * degerEquivalentProperty;
						if(httmp.containsKey("EquivalentProperty"))
						{
							tmp_int = (Integer) httmp.get("EquivalentProperty");
							tmp_int++;
							httmp.remove("EquivalentProperty");
							httmp.put("EquivalentProperty", new Integer(tmp_int));
						}
						else
						{
							httmp.put("EquivalentProperty", new Integer(1));
						}
		        	}
		        	else if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#disjointWith") == 0 || 
		        			geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2002/07/owl#disjointWith") == 0)
		        	{
		        		patika_degeri = patika_degeri * degerDisjointWith;
						if(httmp.containsKey("DisjointWith"))
						{
							tmp_int = (Integer) httmp.get("DisjointWith");
							tmp_int++;
							httmp.remove("DisjointWith");
							httmp.put("DisjointWith", new Integer(tmp_int));
						}
						else
						{
							httmp.put("DisjointWith", new Integer(1));
						}
		        	}
		        	else if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#differentFrom") == 0 ||
		        			geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2002/07/owl#differentFrom") == 0)
		        	{
		        		patika_degeri = patika_degeri * degerDifferentFrom;
						if(httmp.containsKey("DifferentFrom"))
						{
							tmp_int = (Integer) httmp.get("DifferentFrom");
							tmp_int++;
							httmp.remove("DifferentFrom");
							httmp.put("DifferentFrom", new Integer(tmp_int));
						}
						else
						{
							httmp.put("DifferentFrom", new Integer(1));
						}
		        	}
		        	else
		        	{
		        		patika_degeri = patika_degeri * 0;
		        	}
		    		//System.out.println("ifade: " + geciciDugum.Kenar().ifadeAl().toString());
		    		geciciDugum = geciciDugum.AtaDugum();
		    	} while (geciciDugum != null);

		    	if ((minPatikaDeger < patika_degeri) && (patika_degeri != 1))
		    	{
		    		minPatikaDeger = patika_degeri;
		    		htmin = (Hashtable<String, Integer>) httmp.clone();
		    		//cntmin = cnt;
		    	}

		    	
		    	//System.out.println("patika_degeri: " + patika_degeri);
		    	//System.out.println("cntmin: " + cntmin);
		    	
	        }
        } 
		String key;
		Integer value;
		if (htmin != null)
		{
			Enumeration<String> e = htmin.keys();
	        while (e.hasMoreElements())
	        { 
	            key = (String) e.nextElement(); 
	            value = (Integer) htmin.get (key);
	            System.out.println ("Dil ögesi: { " + key + ", " + value + " }"); 
	        }
		}
		return minPatikaDeger;
    }

    /*private static double PatikaDegeriHesapla()
    {
    	LinkedList<LinkedList<Kenar>> PatikaBaslangiclari;
    	LinkedList<Integer> PatikaUzunluklari;
    	
    	PatikaBaslangiclari = new LinkedList<LinkedList<Kenar>>();
    	PatikaUzunluklari = new LinkedList<Integer>();
    	
		Iterator<Dugum> i = hedefDugumler.iterator();
		Dugum geciciDugum;
    	double patika_degeri_2 = 1;
		
        if(i.hasNext()) {
	        while (i.hasNext()) 
	        {
	        	LinkedList<Kenar> Patika = new LinkedList<Kenar>();
	        	int PatikaUzunlugu = 0;
	        	geciciDugum = (Dugum)(i.next());
	        	patika_degeri_2 = 1;
		    	do
		    	{
		    		//////////////////////////////
		        	if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#subClassOf") == 0)
		        	{
		        		if (geciciDugum.Kenar().yonAl())
		        		{
		        			patika_degeri_2 = patika_degeri_2 * degerSubClassOf_ozelden_genele;
		        		}
		        		else
		        		{
		        			patika_degeri_2 = patika_degeri_2 * degerSubClassOf_genelden_ozele;
		        		}
		        	}
		        	else if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#subPropertyOf") == 0)
		        	{
		        		if (geciciDugum.Kenar().yonAl())
		        		{
		        			patika_degeri_2 = patika_degeri_2 * degerSubPropertyOf_ozelden_genele;
		        		}
		        		else
		        		{
		        			patika_degeri_2 = patika_degeri_2 * degerSubPropertyOf_genelden_ozele;
		        		}
		        	}
		        	else if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#domain") == 0)
		        	{
		        		if (geciciDugum.Kenar().yonAl())
		        		{
		        			patika_degeri_2 = patika_degeri_2 * degerDomain_konuAlanindan__ozellige;
		        		}
		        		else
		        		{
		        			patika_degeri_2 = patika_degeri_2 * degerDomain_ozellikten_konuAlanina;
		        		}
		        	}
		        	else if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#range") == 0)
		        	{
		        		if (geciciDugum.Kenar().yonAl())
		        		{
		        			patika_degeri_2 = patika_degeri_2 * degerRange_ozellikten_menzile;
		        		}
		        		else
		        		{
		        			patika_degeri_2 = patika_degeri_2 * degerRange_menzilden_ozellige;
		        		}
		        	}
		        	else if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#inverseOf") == 0)
		        	{
		        		patika_degeri_2 = patika_degeri_2 * degerInverseOf;
		        	}
		        	else if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#equivalentClass") == 0)
		        	{
		        		patika_degeri_2 = patika_degeri_2 * degerEquivalentClass;
		        	}
		        	else if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#equivalentProperty") == 0)
		        	{
		        		patika_degeri_2 = patika_degeri_2 * degerEquivalentProperty;
		        	}
		        	else if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#disjointWith") == 0)
		        	{
		        		patika_degeri_2 = patika_degeri_2 * degerDisjointWith;
		        	}
		        	else if (geciciDugum.Kenar().ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#differentFrom") == 0)
		        	{
		        		patika_degeri_2 = patika_degeri_2 * degerDifferentFrom;
		        	}
		        	//else if (geciciBagliListe.get(n).ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/1999/02/22-rdf-syntax-ns#type") == 0)
		        	//{
	        		//	patika_degeri = patika_degeri * 1;
		        	//}
		        	else
		        	{
		        		patika_degeri_2 = patika_degeri_2 * 0;
		        	}

		        	System.out.println("Üssssssssst: " + geciciDugum.Kenar().ifadeAl().toString());
		    		/////////////////////////////
		        	Patika.add(Patika.size(), geciciDugum.Kenar());
		        	PatikaUzunlugu++;
		    		geciciDugum = geciciDugum.AtaDugum();
		    	} while (geciciDugum != null);
		    	
		    	System.out.println("patika_degeri_2: " + patika_degeri_2);
		    	
		    	boolean eklendimi = false;
		    	for (int j = 0; j < PatikaUzunluklari.size(); j++)
		    	{
		    		if (PatikaUzunluklari.get(j) > PatikaUzunlugu)
		    		{
				    	PatikaBaslangiclari.add(j, Patika);
				    	PatikaUzunluklari.add(j, PatikaUzunlugu);
				    	eklendimi = true;
		    			break;
		    		}
		    	}
		    	if (!eklendimi)
		    	{
			    	PatikaBaslangiclari.add(Patika);
			    	PatikaUzunluklari.add(PatikaUzunlugu);
		    	}
	        }
        } 
        
        BufferedWriter yaz;
    	double patika_degeri = 1;
        double minPatikaDeger = 0;
        int minPatikaIndex = -1;
        int tmpIndex = -1;
    	try
    	{
    		//BufferedWriter yaz = new BufferedWriter(new FileWriter("I:\\Kod\\Workspace\\Deney1\\sonuc.txt", true));
			//Dugum gecici;
	    	//gecici = d;
	    	//yaz.write("SONUC");

    		LinkedList<Kenar> geciciBagliListe;
    		yaz = new BufferedWriter(new FileWriter(ontolojiDokumaniDizini1 + onlolojiDokumaniAdi + "_sonuc.txt", true));
    		
	    	for (int m = 0; m < PatikaBaslangiclari.size(); m++)
	    	{
    			tmpIndex = m;
	    		//System.out.println("Uzunluk: " + PatikaUzunluklari.get(m).toString());
	        	yaz.write("Uzunluk: " + PatikaUzunluklari.get(m).toString());
	        	yaz.newLine();
	    		geciciBagliListe = PatikaBaslangiclari.get(m);
	    		patika_degeri = 1;
	    		for (int n = 0; n < geciciBagliListe.size(); n++)
	    		{
	    			//System.out.println(geciciBagliListe.get(n).ifadeAl().toString() + " - " + geciciBagliListe.get(n).yonAl());
		        	if (geciciBagliListe.get(n).ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#subClassOf") == 0)
		        	{
		        		if (geciciBagliListe.get(n).yonAl())
		        		{
		        			patika_degeri = patika_degeri * degerSubClassOf_ozelden_genele;
		        		}
		        		else
		        		{
		        			patika_degeri = patika_degeri * degerSubClassOf_genelden_ozele;
		        		}
		        	}
		        	else if (geciciBagliListe.get(n).ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#subPropertyOf") == 0)
		        	{
		        		if (geciciBagliListe.get(n).yonAl())
		        		{
		        			patika_degeri = patika_degeri * degerSubPropertyOf_ozelden_genele;
		        		}
		        		else
		        		{
		        			patika_degeri = patika_degeri * degerSubPropertyOf_genelden_ozele;
		        		}
		        	}
		        	else if (geciciBagliListe.get(n).ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#domain") == 0)
		        	{
		        		if (geciciBagliListe.get(n).yonAl())
		        		{
		        			patika_degeri = patika_degeri * degerDomain_konuAlanindan__ozellige;
		        		}
		        		else
		        		{
		        			patika_degeri = patika_degeri * degerDomain_ozellikten_konuAlanina;
		        		}
		        	}
		        	else if (geciciBagliListe.get(n).ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#range") == 0)
		        	{
		        		if (geciciBagliListe.get(n).yonAl())
		        		{
		        			patika_degeri = patika_degeri * degerRange_ozellikten_menzile;
		        		}
		        		else
		        		{
		        			patika_degeri = patika_degeri * degerRange_menzilden_ozellige;
		        		}
		        	}
		        	else if (geciciBagliListe.get(n).ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#inverseOf") == 0)
		        	{
	        			patika_degeri = patika_degeri * degerInverseOf;
		        	}
		        	else if (geciciBagliListe.get(n).ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#equivalentClass") == 0)
		        	{
	        			patika_degeri = patika_degeri * degerEquivalentClass;
		        	}
		        	else if (geciciBagliListe.get(n).ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#equivalentProperty") == 0)
		        	{
	        			patika_degeri = patika_degeri * degerEquivalentProperty;
		        	}
		        	else if (geciciBagliListe.get(n).ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#disjointWith") == 0)
		        	{
	        			patika_degeri = patika_degeri * degerDisjointWith;
		        	}
		        	else if (geciciBagliListe.get(n).ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/2000/01/rdf-schema#differentFrom") == 0)
		        	{
	        			patika_degeri = patika_degeri * degerDifferentFrom;
		        	}
		        	//else if (geciciBagliListe.get(n).ifadeAl().getPredicate().toString().compareTo("http://www.w3.org/1999/02/22-rdf-syntax-ns#type") == 0)
		        	//{
	        		//	patika_degeri = patika_degeri * 1;
		        	//}
		        	else
		        	{
		        		patika_degeri = patika_degeri * 0;
		        	}
		        	//System.out.println("Patika degeri: " + patika_degeri);
		        	if (patika_degeri < minPatikaDeger)
		        	{
		        		break;
		        	}
	    			yaz.write(geciciBagliListe.get(n).ifadeAl().toString() + " - " + geciciBagliListe.get(n).yonAl());
		        	yaz.newLine();
	    		}
		    	if ((minPatikaDeger < patika_degeri) && (patika_degeri != 1))
		    	{
		    		minPatikaDeger = patika_degeri;
		    		minPatikaIndex = tmpIndex;
		    	}
	    	}
	    	System.out.println("Patika degeri: " + minPatikaDeger);
	    	if ((minPatikaIndex >= 0) && (minPatikaIndex < PatikaBaslangiclari.size()))
	    	{
		    	geciciBagliListe = PatikaBaslangiclari.get(minPatikaIndex);
		    	for (int l = 0; l < geciciBagliListe.size(); l++)
	    		{
	    			System.out.println(geciciBagliListe.get(l).ifadeAl().toString() + " - " + geciciBagliListe.get(l).yonAl());
	    		}
	    	}
    		if (yaz != null)
    			yaz.close();
	    	
    	}
    	catch(Exception e)
    	{
            e.printStackTrace();
            return 0;
        }
		return minPatikaDeger;
    }*/


    public static void fileIterator(Iterator<?> i, String header) {
    	try
    	{
    		BufferedWriter yaz = new BufferedWriter(new FileWriter(ontolojiDokumaniDizini1  + onlolojiDokumaniAdi + "_ifadeler.txt"));
	        if(i.hasNext()) {
		        while (i.hasNext()) 
		        {
		        	yaz.write(i.next().toString());
		        	yaz.newLine();
		        }
	        }       
	        else
	            System.out.println("<EMPTY>");
	    	yaz.close();
	    	System.out.println("yazma islemi bitti");
    	} 
    	catch(Exception e)
    	{
            e.printStackTrace();
        }
    }

    public static void ontolojidekiIfadeleriListele()
    {
    	 //model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
         model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
         //model.getDocumentManager().addAltEntry( ontolojiDokumaniURI, ontolojiDokumaniYeri );
         model.read( "http://localhost/Deney3/D307.M.250.B.D/project.owl" );
         
     	Statement ifade;
    	Iterator<Statement> gecici = model.listStatements();

    	if(gecici.hasNext()) 
    	{
	        while (gecici.hasNext()) 
	        {
	        	ifade = (Statement)gecici.next();
	        	System.out.println(ifade.toString());
	        }
    	}
    	
		Resource kaynak = model.getResource("http://ebiquity.umbc.edu/ontology/project.owl#Project");
    	
        if (kaynak != null)
        	System.out.println("res: " + kaynak.toString());
		
        kaynak = model.getResource("http://ebiquity.umbc.edu/ontology/project.owl#Proposal");
    	
        if (kaynak != null)
        	System.out.println("res: " + kaynak.toString());

    }
}
