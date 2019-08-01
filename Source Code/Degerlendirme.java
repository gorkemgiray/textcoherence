import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.AnonId;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RDFVisitor;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.ontology.OntModelSpec;

public class Degerlendirme {

	static OntModel model;
    static String rdfns = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    static String rdfsns = "http://www.w3.org/2000/01/rdf-schema#";
    static String owlns = "http://www.w3.org/2002/07/owl#";
    static String xsdns = "http://www.w3.org/2001/XMLSchema#";

    //static String ontolojiDokumaniAdi = "Camera.owl";
    static String ontolojiDokumaniAdi = "Camera.owl";
    static String ontolojiDokumaniURI = "http://www.w3.org/2001/sw/WebOnt/guide-src/wine";
    static String ontolojiDokumaniDizini = "file:///I:/Kod/Workspace/Deney/";
    static String ontolojiDokumaniDizini1 = "I:\\Kod\\Workspace\\Deney\\";
    static String ontolojiDokumaniYeri = ontolojiDokumaniDizini + ontolojiDokumaniAdi;
    
    static float SinifSayisi;
    static float SinifBasinaOrtalamaOrnekSayisi;
    static float SinifBasinaOrtalamaOzellikSayisi;
    static ArrayList<DatatypeProperty> dpListesi = new ArrayList<DatatypeProperty>();
    static ArrayList<ArrayList<OntResource>> dpKonuAlaniListesi = new ArrayList<ArrayList<OntResource>>();
    static ArrayList<ObjectProperty> opListesi = new ArrayList<ObjectProperty>();
    static ArrayList<ArrayList<OntResource>> opKonuAlaniListesi = new ArrayList<ArrayList<OntResource>>();
    static ArrayList<ArrayList<OntResource>> opMenzilListesi = new ArrayList<ArrayList<OntResource>>();

    static ArrayList<String> arrontolojiDokumaniAdi;
    static ArrayList<String> arrkaynak1URI;
    static ArrayList<String> arrkaynak2URI;
    static ArrayList<OntResource> arrkaynak1;
    static ArrayList<OntResource> arrkaynak2;
    static ArrayList<Float> arrOOS; //Ortalama Ornek Sayisi
    static ArrayList<Float> arrOOO; //Ortak Ornek Orani
    static ArrayList<Float> arrODTOS; //Ortalama DataType Özellik Sayisi
    static ArrayList<Float> arrODTOO; //Ortak DataType Özellik Orani
    static ArrayList<Float> arrOOOS; //Ortalama Object Özellik Sayisi
    static ArrayList<Float> arrOOOO; //Ortak Object Özellik Orani
    static ArrayList<String> arrkaynak1Tip;
    static ArrayList<String> arrkaynak2Tip;
    
    public static void main(String[] args) {

    	KavramCiftleriniBelirle();
        OntResource or1;
		OntResource or2;
		OntClass oc1;
		OntClass oc2;
		Property prop1;
		Property prop2;
		DatatypeProperty dtprop1;
		DatatypeProperty dtprop2;
		ObjectProperty oprop1;
		ObjectProperty oprop2;
		Individual ind1;
		Individual ind2;

    	for (int i = 0; i < arrkaynak1URI.size(); i++)
    	{
	        model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
	        //model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
	        model.getDocumentManager().addAltEntry( "", ontolojiDokumaniDizini +  arrontolojiDokumaniAdi.get(i));
	        model.read( "" );

	    	arrOOS = new ArrayList<Float>();
	    	arrOOO = new ArrayList<Float>();
	    	arrODTOS = new ArrayList<Float>();
	    	arrODTOO = new ArrayList<Float>();
	    	arrOOOS = new ArrayList<Float>();
	    	arrOOOO = new ArrayList<Float>();
	    	arrkaynak1Tip = new ArrayList<String>();
	    	arrkaynak2Tip = new ArrayList<String>();
	    	arrkaynak1 = new ArrayList<OntResource>();
	    	arrkaynak2 = new ArrayList<OntResource>();
	    	
	        SinifSayisi = SinifSayisi();
	        arrODTOS.add(OrtalamaDataTypeOzellikOraniniHesapla());
	        arrOOOS.add(OrtalamaObjectOzellikOraniniHesapla());
	        
	        OntolojiCozumle();
	        or1 = model.getOntResource(arrkaynak1URI.get(i));
			or2 = model.getOntResource(arrkaynak2URI.get(i));

			arrkaynak1.add(or1);
			arrkaynak2.add(or2);
	
			oc1 = null;
			oc2 = null;
			prop1 = null;
			prop2 = null;
			dtprop1 = null;
			dtprop2 = null;
			oprop1 = null;
			oprop2 = null;
			ind1 = null;
			ind2 = null;
			
			if (or1.isIndividual())
			{
				arrkaynak1Tip.add("Individual");
				ind1 = model.getIndividual(arrkaynak1URI.get(i));
			}
			else if (or1.getRDFType().toString().compareTo(owlns + "Class") == 0)
			{
				arrkaynak1Tip.add("Class");
				oc1 = model.getOntClass(arrkaynak1URI.get(i));
			}
			else if (or1.getRDFType().toString().compareTo(owlns + "DatatypeProperty") == 0) 
			{
				arrkaynak1Tip.add("DatatypeProperty");
				dtprop1 = (DatatypeProperty) model.getDatatypeProperty(arrkaynak1URI.get(i));
			}
			else if (or1.getRDFType().toString().compareTo(owlns + "ObjectProperty") == 0)
			{
				arrkaynak1Tip.add("ObjectProperty");
				oprop1 = (ObjectProperty) model.getObjectProperty(arrkaynak1URI.get(i));
			}
			else 
			{
				arrkaynak1Tip.add(or1.getRDFType().toString());
			}

			if (or2.isIndividual())
			{
				arrkaynak2Tip.add("Individual");
				ind2 = model.getIndividual(arrkaynak2URI.get(i));
			}
			else if (or2.getRDFType().toString().compareTo(owlns + "Class") == 0)
			{
				arrkaynak2Tip.add("Class");
				oc2 = model.getOntClass(arrkaynak2URI.get(i));
			}
			else if (or2.getRDFType().toString().compareTo(owlns + "DatatypeProperty") == 0) 
			{
				arrkaynak2Tip.add("DatatypeProperty");
				dtprop2 = (DatatypeProperty) model.getDatatypeProperty(arrkaynak2URI.get(i));
			}
			else if (or2.getRDFType().toString().compareTo(owlns + "ObjectProperty") == 0)
			{
				arrkaynak2Tip.add("ObjectProperty");
				oprop2 = (ObjectProperty) model.getObjectProperty(arrkaynak2URI.get(i));
			}
			else 
			{
				arrkaynak2Tip.add(or2.getRDFType().toString());
			}

			if ((arrkaynak1Tip.get(i).compareTo("Class") == 0) && (arrkaynak2Tip.get(i).compareTo("Class") == 0))
			{
				if ((oc1 != null) && (oc2 != null))
				{
					arrOOO.add(OrtakOrnekOraniniHesapla(oc1, oc2));
					arrODTOO.add(OrtakDataTypeOzellikOraniniHesapla(or1, or2));
					arrOOOO.add(OrtakObjectOzellikOraniniHesapla(or1, or2));
				}
			}
			
    	}
    	
    	for (int j = 0; j < arrkaynak1.size(); j++)
    	{
    		System.out.println("Kavram1" + "," + "Kavram2" + "," + "Kavram1Tip" + "," + "Kavram2Tip" + "," + "OrtalamaOrnekSayisi" + "," + "OrtakOrnekSayisi" + "," + "OrtalamaDataTypeOzellikSayisi" + "," + "OrtakDataTypeOzellikOrani" + "," + "OrtalamaObjectOzellikSayisi" + "," + "OrtakObjectOzellikOrani");
    		System.out.println(arrkaynak1.get(j).getLocalName() + "," + arrkaynak2.get(j).getLocalName() + "," + arrkaynak1Tip.get(j) + "," + arrkaynak2Tip.get(j) + "," + arrOOS.get(j) + "," + arrOOO.get(j) + "," + arrODTOS.get(j) + "," + arrODTOO.get(j) + "," + arrOOOS.get(j) + "," + arrOOOO.get(j));
    	}    	
    }
    
    public static void main1(String[] args) {
        model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
        //model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
        model.getDocumentManager().addAltEntry( ontolojiDokumaniURI, ontolojiDokumaniYeri );
        model.read( ontolojiDokumaniURI );
        
        
        OzellikleriListele();
        //SiniflariListele();
        //OrnekleriListele();
		//Resource kaynak = model.getResource("http://www.owl-ontologies.com/generations.owl#Man");
        //String kaynak1URI = "http://www.xfront.com/owl/ontologies/camera/#aperture";
        //String kaynak1URI = "http://www.xfront.com/owl/ontologies/camera/#Camera";
        //String kaynak2URI = "http://www.xfront.com/owl/ontologies/camera/#f-stop";
        String kaynak1URI = "http://www.xfront.com/owl/ontologies/camera/#Range";
        String kaynak2URI = "http://www.xfront.com/owl/ontologies/camera/#Lens";
        
        OntResource or1 = model.getOntResource(kaynak1URI);
		OntResource or2 = model.getOntResource(kaynak2URI);
		OntClass oc1;
		OntClass oc2;
		Property prop1;
		Property prop2;
		DatatypeProperty dtprop1;
		DatatypeProperty dtprop2;
		ObjectProperty oprop1;
		ObjectProperty oprop2;
		Individual ind1;
		Individual ind2;
		System.out.println(or1.getRDFType().toString());
		System.out.println(or2.getRDFType().toString());
		if (or1.isIndividual() && or2.isIndividual())//(or1.isClass() && or2.isClass())
		{
			System.out.println("Individual - Individual");
			ind1 = model.getIndividual(kaynak1URI);
			ind2 = model.getIndividual(kaynak2URI);
		}
		else if ((or1.getRDFType().toString().compareTo(owlns + "Class") == 0) && 
				(or2.getRDFType().toString().compareTo(owlns + "Class") == 0))
		{
			oc1 = model.getOntClass(kaynak1URI);
			oc2 = model.getOntClass(kaynak2URI);
			System.out.println("Class - Class");
		}
		else if ((or1.getRDFType().toString().compareTo(owlns + "DatatypeProperty") == 0) && 
				(or2.getRDFType().toString().compareTo(owlns + "DatatypeProperty") == 0))
		{
			
			dtprop1 = (DatatypeProperty) model.getDatatypeProperty(kaynak1URI);
			dtprop2 = (DatatypeProperty) model.getDatatypeProperty(kaynak2URI);
			System.out.println("DatatypeProperty - DatatypeProperty");
		}
		else if ((or1.getRDFType().toString().compareTo(owlns + "ObjectProperty") == 0) && 
				(or2.getRDFType().toString().compareTo(owlns + "ObjectProperty") == 0))
		{
			
			oprop1 = (ObjectProperty) model.getObjectProperty(kaynak1URI);
			oprop2 = (ObjectProperty) model.getObjectProperty(kaynak2URI);
			System.out.println("ObjectProperty - ObjectProperty");
		}
		else 
			System.out.println(or1.getRDFType().toString() + " - " + or2.getRDFType().toString());
        //OntClass oc = model.getOntClass("http://www.owl-ontologies.com/generations.owl#Man");
		//System.out.println(Node.getType("http://www.owl-ontologies.com/generations.owl#Man").toString());
    
        
        //OntClass oc1 = model.getOntClass("http://www.owl-ontologies.com/generations.owl#Man");
        //OntClass oc2 = model.getOntClass("http://www.owl-ontologies.com/generations.owl#Son");
        //SinifOrnekListelerininKesisiminiHesapla(SinifOrnekleriniListele(oc1), SinifOrnekleriniListele(oc2));
        //SinifAtaSiniflariniListele(oc1);
        //SinifAtaSinifListelerininKesisiminiHesapla(SinifAtaSiniflariniListele(oc1), SinifAtaSiniflariniListele(oc2));
        //OrnekleriListele();
        //System.out.println(OrnekSayisi());
        //OntolojiCozumle();
		
		OzellikKesisiminiHesapla1(or1, or2);
		
	}
	
    public static float OrtalamaDataTypeOzellikOraniniHesapla()
    {
    	/*List<DatatypeProperty> dtprop = model.listDatatypeProperties().toList();
    	for (int i = 0; i < dtprop.size(); i++)
    	{
    		System.out.println(dtprop.size());
    		System.out.println(dtprop.toString());
    	}*/
    	if (SinifSayisi == 0)
    		return 0;
    	else
    		//http://www.w3.org/2002/07/owl#bottomDataProperty, http://www.w3.org/2002/07/owl#topDataProperty ozelliklerini sayma
    		return (model.listDatatypeProperties().toList().size() - 2) / SinifSayisi;
    }

    public static float OrtalamaObjectOzellikOraniniHesapla()
    {
    	/*List<ObjectProperty> oprop = model.listObjectProperties().toList();
    	for (int i = 0; i < oprop.size(); i++)
    	{
    		System.out.println(oprop.size());
    		System.out.println(oprop.toString());
    	}*/
    	if (SinifSayisi == 0)
    		return 0;
    	else
    		//http://www.w3.org/2002/07/owl#bottomObjectProperty, http://www.w3.org/2002/07/owl#topObjectProperty ozelliklerini sayma
    		return (model.listObjectProperties().toList().size() - 2) / SinifSayisi;
    }

    public static float OrtakDataTypeOzellikOraniniHesapla(OntResource or1, OntResource or2)
    {
    	boolean or1bulundu;
    	boolean or2bulundu;
    	float or1DatatypeProperty = 0;
    	float or2DatatypeProperty = 0;
    	float ortakDatatypeProperty = 0;
    
    	List<DatatypeProperty> dpListesi1 = model.listDatatypeProperties().toList();
    	
    	List<? extends OntResource> dpKonuAlaniListesi1;

    	if (dpListesi1 == null)
    		return 0;
    	
    	for (int i = 0; i < dpListesi1.size(); i++)
    	{
    		or1bulundu = false;
    		or2bulundu = false;
    		dpKonuAlaniListesi1 = dpListesi1.get(i).listDomain().toList();
    		if (dpKonuAlaniListesi1 != null)
    		{
	        	for (int j = 0; j < dpKonuAlaniListesi1.size(); j++)
	        	{
	        		if (dpKonuAlaniListesi1.get(j).toString().compareTo(or1.toString()) == 0)
	        		{
	        			or1DatatypeProperty ++;
	        			or1bulundu = true;
	        		}
	        		if (dpKonuAlaniListesi1.get(j).toString().compareTo(or2.toString()) == 0)
	        		{
	        			or2DatatypeProperty ++;
	        			or2bulundu = true;
	        		}
	        		if ((or1bulundu) && (or2bulundu))
	        		{
	        			ortakDatatypeProperty ++;
	        			break;
	        		}
	        	}
    		}
    	}
    	//System.out.println(or1.toString() + " datatype sayý: " + or1DatatypeProperty);
    	//System.out.println(or2.toString() + " datatype sayý: " + or2DatatypeProperty);
    	//System.out.println("ortak datatype sayý: " + ortakDatatypeProperty);
    	if ((or1DatatypeProperty + or2DatatypeProperty - ortakDatatypeProperty) == 0)
    		return 0;
    	else
    		return (ortakDatatypeProperty / (or1DatatypeProperty + or2DatatypeProperty - ortakDatatypeProperty));
    }
    
    public static float OrtakObjectOzellikOraniniHesapla(OntResource or1, OntResource or2)
    {
    	boolean or1bulundu;
    	boolean or2bulundu;
    	float or1ObjectProperty = 0;
    	float or2ObjectProperty = 0;
    	float ortakObjectProperty = 0;
    
    	List<ObjectProperty> opListesi1 = model.listObjectProperties().toList();
    	
    	List<? extends OntResource> opKonuAlaniListesi1;

    	if (opListesi1 == null)
    		return 0;
    	
    	for (int i = 0; i < opListesi1.size(); i++)
    	{
    		or1bulundu = false;
    		or2bulundu = false;
    		opKonuAlaniListesi1 = opListesi1.get(i).listDomain().toList();
    		if (opKonuAlaniListesi1 != null)
    		{
	        	for (int j = 0; j < opKonuAlaniListesi1.size(); j++)
	        	{
	        		if (opKonuAlaniListesi1.get(j).toString().compareTo(or1.toString()) == 0)
	        		{
	        			or1ObjectProperty ++;
	        			or1bulundu = true;
	        		}
	        		if (opKonuAlaniListesi1.get(j).toString().compareTo(or2.toString()) == 0)
	        		{
	        			or2ObjectProperty ++;
	        			or2bulundu = true;
	        		}
	        		if ((or1bulundu) && (or2bulundu))
	        		{
	        			ortakObjectProperty ++;
	        			break;
	        		}
	        	}
    		}
    	}
    	//System.out.println(or1.toString() + " datatype sayý: " + or1DatatypeProperty);
    	//System.out.println(or2.toString() + " datatype sayý: " + or2DatatypeProperty);
    	//System.out.println("ortak datatype sayý: " + ortakDatatypeProperty);
    	if ((or1ObjectProperty + or2ObjectProperty - ortakObjectProperty) == 0)
    		return 0;
    	else
    		return (ortakObjectProperty / (or1ObjectProperty + or2ObjectProperty - ortakObjectProperty));
    }
    
    public static void OzellikKesisiminiHesapla1(OntResource or1, OntResource or2)
    {
    	boolean or1bulundu;
    	boolean or2bulundu;
    	float or1DatatypeProperty = 0;
    	float or2DatatypeProperty = 0;
    	float ortakDatatypeProperty = 0;
    	float ortakDatatypeOrani = 0;
    	
    	if ((dpListesi == null) || (dpKonuAlaniListesi == null))
    		return;
    	for (int i = 0; i < dpListesi.size(); i++)
    	{
    		or1bulundu = false;
    		or2bulundu = false;
    		if (dpKonuAlaniListesi.get(i) != null)
    		{
	        	for (int j = 0; j < dpKonuAlaniListesi.get(i).size(); j++)
	        	{
	        		if (dpKonuAlaniListesi.get(i).get(j).toString().compareTo(or1.toString()) == 0)
	        		{
	        			or1DatatypeProperty ++;
	        			or1bulundu = true;
	        		}
	        		if (dpKonuAlaniListesi.get(i).get(j).toString().compareTo(or2.toString()) == 0)
	        		{
	        			or2DatatypeProperty ++;
	        			or2bulundu = true;
	        		}
	        		if ((or1bulundu) && (or2bulundu))
	        		{
	        			ortakDatatypeProperty ++;
	        			break;
	        		}
	        	}
    		}
    	}
    	System.out.println(or1.toString() + " datatype sayý: " + or1DatatypeProperty);
    	System.out.println(or2.toString() + " datatype sayý: " + or2DatatypeProperty);
    	System.out.println("ortak datatype sayý: " + ortakDatatypeProperty);
    	ortakDatatypeOrani = ortakDatatypeProperty / (or1DatatypeProperty + or2DatatypeProperty - ortakDatatypeProperty);
    	
    }
    
    public static void OntolojiCozumle()
    {
    	float SinifSayisi = SinifSayisi();
    	float OrnekSayisi = OrnekSayisi();
    	//System.out.println("SinifSayisi =" + SinifSayisi);
    	//System.out.println("OrnekSayisi =" + OrnekSayisi);
    	
    	if (SinifSayisi != 0)
    		SinifBasinaOrtalamaOrnekSayisi = OrnekSayisi / SinifSayisi;
    	else
    		SinifBasinaOrtalamaOrnekSayisi = 0;
    	
    	//System.out.println("SinifBasinaOrtalamaOrnekSayisi: " + SinifBasinaOrtalamaOrnekSayisi);
    	arrOOS.add(SinifBasinaOrtalamaOrnekSayisi);
    }

    private static float OrtakOrnekOraniniHesapla(OntClass oc1, OntClass oc2)
    {
    	ArrayList<OntResource> oc1Ornekler;
    	ArrayList<OntResource> oc2Ornekler;
    	int OrtakOrnekSayisi = 0;

    	oc1Ornekler = SinifOrnekleriniListele(oc1);
		oc2Ornekler = SinifOrnekleriniListele(oc2);
		OrtakOrnekSayisi = SinifOrnekListelerininKesisiminiHesapla(oc1Ornekler, oc2Ornekler);
		if ((oc1Ornekler.size() + oc2Ornekler.size() - OrtakOrnekSayisi) == 0)
			return 0;
		else
			return (OrtakOrnekSayisi / (oc1Ornekler.size() + oc2Ornekler.size() - OrtakOrnekSayisi));
    	
    }

    private static float OrtakAtaSinifOraniniHesapla(OntClass oc1, OntClass oc2)
    {
    	ArrayList<OntClass> oc1Atasiniflar;
    	ArrayList<OntClass> oc2Atasiniflar;
    	int OrtakAtasinifSayisi = 0;

		oc1Atasiniflar = SinifAtaSiniflariniListele(oc1);
		oc2Atasiniflar = SinifAtaSiniflariniListele(oc2);
		OrtakAtasinifSayisi = SinifAtaSinifListelerininKesisiminiHesapla(oc1Atasiniflar, oc2Atasiniflar);
		if ((oc1Atasiniflar.size() + oc2Atasiniflar.size() - OrtakAtasinifSayisi) == 0)
			return 0;
		else
			return (OrtakAtasinifSayisi / (oc1Atasiniflar.size() + oc2Atasiniflar.size() - OrtakAtasinifSayisi));

    }
    
    public static float SiniflarinBenzerliginiHesapla(OntClass oc1, OntClass oc2)
    {
    	ArrayList<OntResource> oc1Ornekler;
    	ArrayList<OntResource> oc2Ornekler;
    	int OrtakOrnekSayisi = 0;
    	float OrtakOrnekOrani = 0;
    	ArrayList<OntClass> oc1Atasiniflar;
    	ArrayList<OntClass> oc2Atasiniflar;
    	int OrtakAtasinifSayisi = 0;
    	float OrtakAtasinifOrani = 0;
    	
    	if (SiniflarEsitmi(oc1, oc2)) //Siniflar arasinda equivalentClass iliskisi varsa
    		return 1;
    	else
    	{
    		//Ortak ornekler
    		oc1Ornekler = SinifOrnekleriniListele(oc1);
    		oc2Ornekler = SinifOrnekleriniListele(oc2);
    		OrtakOrnekSayisi = SinifOrnekListelerininKesisiminiHesapla(oc1Ornekler, oc2Ornekler);
    		OrtakOrnekOrani = OrtakOrnekSayisi / (oc1Ornekler.size() + oc2Ornekler.size() - OrtakOrnekSayisi);
    		//Ata siniflar
    		oc1Atasiniflar = SinifAtaSiniflariniListele(oc1);
    		oc2Atasiniflar = SinifAtaSiniflariniListele(oc2);
    		OrtakAtasinifSayisi = SinifAtaSinifListelerininKesisiminiHesapla(oc1Atasiniflar, oc2Atasiniflar);
    		OrtakAtasinifOrani = OrtakAtasinifSayisi / (oc1Atasiniflar.size() + oc2Atasiniflar.size() - OrtakAtasinifSayisi);
    		
    	}
    	return 0;
    }
    
    public static float DatatypeOzelliklerininBenzerliginiHesapla(DatatypeProperty dtprop1, DatatypeProperty dtprop2)
    {

    	return 0;
    }
    
    public static float ObjecttypeOzelliklerininBenzerliginiHesapla(ObjectProperty oprop1, ObjectProperty oprop2)
    {

    	return 0;
    }
    
    public static float OrneklerinBenzerliginiHesapla(Individual ind1, Individual ind2)
    {
    	if (OrneklerEsitmi(ind1, ind2)) //Ornekler arasinda sameAs iliskisi varsa
    		return 1;

    	return 0;
    }
    
    public static boolean SiniflarEsitmi(OntClass oc1, OntClass oc2)
    {
        Iterator<OntClass> j = oc1.listEquivalentClasses();
        OntClass oc;
        while (j.hasNext())
		{
			oc = (OntClass)j.next();
			if (oc.toString().compareTo(oc2.toString()) == 0)
				return true;
		}
    	return false;
    }
    
    /*public static boolean OzelliklerEsitmi(Property prop1, Property prop2)
    {
        
    	Iterator<Property> j = prop1.listProperties(arg0);
        Property prop;
        while (j.hasNext())
		{
			prop = (Property)j.next();
			if (prop.toString().compareTo(prop2.toString()) == 0)
				return true;
		}
    	return false;
    }*/

    public static boolean OrneklerEsitmi(Individual ind1, Individual ind2)
    {
        return ind1.isSameAs(ind2);
    }

    public static void SiniflariListele()
    {
        Iterator<?> j = model.listClasses();
        OntClass oc;
        int sayi = 0;
        while (j.hasNext())
		{
			oc = (OntClass)j.next();
			sayi++;
			System.out.println(oc.toString());
			//SinifOrnekleriniListele(oc);
			//SinifOzellikleriniListele(oc);
		}
		System.out.println(sayi);
		System.out.println(model.listClasses().toList().size());
    }
    
    public static float SinifSayisi()
    {
    	//http://www.w3.org/2002/07/owl#Nothing ve 
    	//http://www.w3.org/2002/07/owl#Thing siniflarini dikkate alma
    	return model.listClasses().toList().size() - 2;
    }

    public static ArrayList<OntResource> SinifOrnekleriniListele(OntClass oc)
    {
    	ArrayList<OntResource> sinifOrnekListesi = new ArrayList<OntResource>(); 
    	Iterator<OntResource> j =  (Iterator<OntResource>) oc.listInstances(false);
        OntResource or;
    	while (j.hasNext())
		{
			or = (OntResource)j.next();
			sinifOrnekListesi.add(or);
			System.out.println("Örnek: " + or.toString());
		}
    	return sinifOrnekListesi;
    }
    
    public static int SinifOrnekListelerininKesisiminiHesapla(ArrayList<OntResource> liste1, ArrayList<OntResource> liste2)
    {
    	int ortak = 0;
    	for (int i = 0; i < liste1.size(); i++)
    	{
        	for (int j = 0; j < liste2.size(); j++)
        	{
        		if (liste1.get(i).toString().compareTo(liste2.get(j).toString()) == 0)
        		{
        			System.out.println("Ortak Örnek: " + liste1.get(i).toString());
        			ortak++;
        			break;
        		}
        	}
    	}
    	return ortak;
    }
    
    public static ArrayList<OntClass> SinifAtaSiniflariniListele(OntClass oc)
    {
    	ArrayList<OntClass> sinifAtaSinifListesi = new ArrayList<OntClass>(); 
    	Iterator<OntClass> j =  (Iterator<OntClass>) oc.listSuperClasses(false);
        OntClass oc1;
    	while (j.hasNext())
		{
			oc1 = (OntClass)j.next();
			if (oc1.toString().compareTo(owlns + "Thing") != 0)
			{
				sinifAtaSinifListesi.add(oc1);
				System.out.println("Ata Sinif: " + oc1.toString());
			}
		}
    	return sinifAtaSinifListesi;
    }

    public static int SinifAtaSinifListelerininKesisiminiHesapla(ArrayList<OntClass> liste1, ArrayList<OntClass> liste2)
    {
    	int ortak = 0;
    	for (int i = 0; i < liste1.size(); i++)
    	{
        	for (int j = 0; j < liste2.size(); j++)
        	{
        		if (liste1.get(i).toString().compareTo(liste2.get(j).toString()) == 0)
        		{
        			System.out.println("Ortak Ata: " + liste1.get(i).toString());
        			ortak++;
        			break;
        		}
        	}
    	}
    	return ortak;
    }

    public static void SinifOzellikleriniListele(OntClass oc)
    {
    	
    	StmtIterator j =  oc.listProperties();
        Statement stmt;
    	while (j.hasNext())
		{
			stmt = (Statement)j.next();
			System.out.println("Özellik: " + stmt.toString());
		} 	
    }
    
    public static void OrnekleriListele()
    {
    	OntClass oc;
    	
    	Iterator<?> j = model.listIndividuals();
        Individual ind;
        int sayi = 0;
        while (j.hasNext())
		{
			ind = (Individual)j.next();
			sayi++;
			System.out.println(ind.toString() + " - " + ind.getOntClass().toString());
			Iterator<?> i = ind.listOntClasses(false);
	        while (i.hasNext())
			{
				oc = (OntClass)i.next();
				System.out.println(" - " + oc.toString());
			}
		}
		System.out.println(sayi);
    }
    
    public static int OrnekSayisi()
    {
    	return model.listIndividuals().toList().size();
    }
    
    public static Iterator<OntResource> ObjectOzellikMenziliniListele(ObjectProperty op)
    {
		OntResource or;

		Iterator<OntResource> i = (Iterator<OntResource>) op.listRange();
        while (i.hasNext())
		{
			or = (OntResource)i.next();
			if (or.toString().compareTo(owlns + "Thing") == 0)
				i.remove();
			else
				System.out.println("Range: " + or.toString());
		}
        return i;
    }
    
    public static ArrayList<OntResource> ObjectOzellikMenziliniAl(ObjectProperty op)
    {
		OntResource or;
		ArrayList<OntResource> opMenzili = new ArrayList<OntResource>();
		Iterator<OntResource> i = (Iterator<OntResource>) op.listRange();
        while (i.hasNext())
		{
			or = (OntResource)i.next();
			if (or.toString().compareTo(owlns + "Thing") == 0)
				i.remove();
			else
			{
				opMenzili.add(or);
				//System.out.println("Range: " + or.toString());
			}
		}
        return opMenzili;
    }
    
    public static Iterator<OntResource> ObjectOzellikKonuAlaniniListele(ObjectProperty op)
    {
		OntResource or;

		Iterator<OntResource> i = (Iterator<OntResource>) op.listDomain();
        while (i.hasNext())
		{
			or = (OntResource)i.next();
			if (or.toString().compareTo(owlns + "Thing") == 0)
				i.remove();
			else
				System.out.println("Domain: " + or.toString());
		}
        return i;
    }
    
    public static Iterator<OntResource> DatatypeOzellikKonuAlaniniListele(DatatypeProperty dp)
    {
		OntResource or;

		Iterator<OntResource> i = (Iterator<OntResource>) dp.listDomain();
        while (i.hasNext())
		{
			or = (OntResource)i.next();
			if (or.toString().compareTo(owlns + "Thing") == 0)
				i.remove();
			else
				System.out.println("Domain (Datatype): " + or.toString());
		}
        return i;
    }

    public static ArrayList<OntResource> ObjectOzellikKonuAlaniniAl(ObjectProperty op)
    {
		OntResource or;
		ArrayList<OntResource> opKonuAlani = new ArrayList<OntResource>();
		Iterator<OntResource> i = (Iterator<OntResource>) op.listDomain();
        while (i.hasNext())
		{
			or = (OntResource)i.next();
			if (or.toString().compareTo(owlns + "Thing") == 0)
				i.remove();
			else
			{
				opKonuAlani.add(or);
				//System.out.println("Domain (Datatype): " + or.toString());
			}
		}
        return opKonuAlani;
    }
    
    public static ArrayList<OntResource> DatatypeOzellikKonuAlaniniAl(DatatypeProperty dp)
    {
		OntResource or;
		ArrayList<OntResource> dpKonuAlani = new ArrayList<OntResource>();
		Iterator<OntResource> i = (Iterator<OntResource>) dp.listDomain();
        while (i.hasNext())
		{
			or = (OntResource)i.next();
			if (or.toString().compareTo(owlns + "Thing") == 0)
				i.remove();
			else
			{
				dpKonuAlani.add(or);
				//System.out.println("Domain (Datatype): " + or.toString());
			}
		}
        return dpKonuAlani;
    }
    
    public static void OzellikleriListele()
    {
    	Iterator<?> j = model.listDatatypeProperties();
        DatatypeProperty dp;
        int sayi = 0;
        while (j.hasNext())
		{
			dp = (DatatypeProperty)j.next();
			if ((dp.toString().compareTo(owlns + "topDataProperty") == 0) || (dp.toString().compareTo(owlns + "bottomDataProperty") == 0))
			{
				
			}
			else
			{
				dpListesi.add(dp);
				dpKonuAlaniListesi.add(DatatypeOzellikKonuAlaniniAl(dp));
				sayi++;
				System.out.println(dp.toString());
				//DatatypeOzellikKonuAlaniniListele(dp);
			}
		}
		System.out.println("Datatype Property: " + sayi);

		OntResource or;
		
		j = model.listObjectProperties();
        ObjectProperty op;
        sayi = 0;
        while (j.hasNext())
		{
			op = (ObjectProperty)j.next();
			if ((op.toString().compareTo(owlns + "topObjectProperty") == 0) || (op.toString().compareTo(owlns + "bottomObjectProperty") == 0))
			{
				
			}
			else
			{
				opListesi.add(op);
				opMenzilListesi.add(ObjectOzellikMenziliniAl(op));
				opKonuAlaniListesi.add(ObjectOzellikKonuAlaniniAl(op));
				sayi++;
				System.out.println(op.toString());
				ObjectOzellikKonuAlaniniListele(op);
				ObjectOzellikMenziliniListele(op);
			}
		}
		System.out.println("Object Property: " + sayi);
    }
    
    public static void OrnekleriListele(OntClass oc)
    {
    	Iterator<?> j = oc.listProperties();
        Individual ind;
        Property p;
        int sayi = 0;
        while (j.hasNext())
		{
			//ind = (Individual)j.next();
			p = (Property)j.next();
			sayi++;
			//System.out.println(ind.getURI() + '-' + ind.getLocalName() + '-' + ind.toString());
			System.out.println(p.getURI() + '-' + p.getLocalName() + '-' + p.toString());
		}
		System.out.println(sayi);
    }

    private static void KavramCiftleriniBelirle()
    {
        arrontolojiDokumaniAdi = new ArrayList<String>();
        arrkaynak1URI = new ArrayList<String>();
        arrkaynak2URI = new ArrayList<String>();
        
        arrontolojiDokumaniAdi.add("finance_th_web.owl");
        arrkaynak1URI.add("http://www.larflast.bas.bg/ontology#money");
        arrkaynak2URI.add("http://www.larflast.bas.bg/ontology#cash");
        arrontolojiDokumaniAdi.add("finance_th_web.owl");
        arrkaynak1URI.add("http://www.larflast.bas.bg/ontology#currency");
        arrkaynak2URI.add("http://www.larflast.bas.bg/ontology#money");
        arrontolojiDokumaniAdi.add("premises001.rdf");
        arrkaynak1URI.add("http://www.w3.org/2002/03owlt/equivalentClass/premises001#car");
        arrkaynak2URI.add("http://www.w3.org/2002/03owlt/equivalentClass/premises001#Automobile");
        arrontolojiDokumaniAdi.add("address.owl");
        arrkaynak1URI.add("http://gaia.fdi.ucm.es/ontologies/address.owl#Street");
        arrkaynak2URI.add("http://gaia.fdi.ucm.es/ontologies/address.owl#Avenue");
        arrontolojiDokumaniAdi.add("finance_th_web.owl");
        arrkaynak1URI.add("http://www.larflast.bas.bg/ontology#money");
        arrkaynak2URI.add("http://www.larflast.bas.bg/ontology#bank");
        arrontolojiDokumaniAdi.add("vsto_realms.owl");
        arrkaynak1URI.add("file:///Users/luca/java/vsto/vsto.owl#Planet");
        arrkaynak2URI.add("file:///Users/luca/java/vsto/vsto.owl#Star");
        arrontolojiDokumaniAdi.add("people.owl");
        arrkaynak1URI.add("http://owl.cs.manchester.ac.uk/2007/07/sssw/people#man");
        arrkaynak2URI.add("http://owl.cs.manchester.ac.uk/2007/07/sssw/people#woman");
        arrontolojiDokumaniAdi.add("space.owl");
        arrkaynak1URI.add("http://www.r4isstatic.com/linkeddata/ontologies/ontomedia/core/space#Wood");
        arrkaynak2URI.add("http://www.r4isstatic.com/linkeddata/ontologies/ontomedia/core/space#Forest");
        arrontolojiDokumaniAdi.add("laptops.owl");
        arrkaynak1URI.add("http://owl.protege.stanford.edu#Computer");
        arrkaynak2URI.add("http://owl.protege.stanford.edu#Keyboard");
        arrontolojiDokumaniAdi.add("space.owl");
        arrkaynak1URI.add("http://www.r4isstatic.com/linkeddata/ontologies/ontomedia/core/space#River");
        arrkaynak2URI.add("http://www.r4isstatic.com/linkeddata/ontologies/ontomedia/core/space#Lake");
        arrontolojiDokumaniAdi.add("space.owl");
        arrkaynak1URI.add("http://www.r4isstatic.com/linkeddata/ontologies/ontomedia/core/space#City");
        arrkaynak2URI.add("http://www.r4isstatic.com/linkeddata/ontologies/ontomedia/core/space#River");
    }
}
