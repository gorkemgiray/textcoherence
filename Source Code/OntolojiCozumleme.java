import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;


public class OntolojiCozumleme {
	static OntModel model;
    static String rdfns = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    static String rdfsns = "http://www.w3.org/2000/01/rdf-schema#";
    static String owlns = "http://www.w3.org/2002/07/owl#";
    static String xsdns = "http://www.w3.org/2001/XMLSchema#";

    static String onlolojiDokumaniAdi = "wine.rdf";
    static String ontolojiDokumaniURI = "http://www.w3.org/2001/sw/WebOnt/guide-src/wine";
    static String ontolojiDokumaniDizini = "file:///I:/Kod/Workspace/WineOnt/";
    static String ontolojiDokumaniDizini1 = "I:\\Kod\\Workspace\\Deney\\";
    static String ontolojiDokumaniDizini2 = "I:\\Kod\\Workspace\\WineOnt\\";
    static String ontolojiDokumaniYeri = ontolojiDokumaniDizini + onlolojiDokumaniAdi;

    //static String ontolojiAdresi = "http://sweet.jpl.nasa.gov/2.0/";
    static String ontolojiAdresi = "file:///I:/Kod/Workspace/TonesSayfasindakiOntolojiler/";
    static String onlolojiDokumaniAdi1 = "astroBody.owl";
    static String ontolojiDokumaniYeri1 = ontolojiAdresi + onlolojiDokumaniAdi1;
    static ArrayList<String> ontolojiDosyaAdlari;
    
    static ArrayList<Statement> stmtssubClassOf;
    static ArrayList<Statement> stmtssubPropertyOf;
    static ArrayList<Statement> stmtsdomain;
    static ArrayList<Statement> stmtsrange;
    static ArrayList<Statement> stmtsequivalentClass;
    static ArrayList<Statement> stmtsequivalentProperty;
    static ArrayList<Statement> stmtssameAs;
    static ArrayList<Statement> stmtsdifferentFrom;
    static ArrayList<Statement> stmtsdistinctMembers;
    static ArrayList<Statement> stmtsinverseOf;
    static ArrayList<Statement> stmtsonProperty;
    static ArrayList<Statement> stmtsallValuesFrom;
    static ArrayList<Statement> stmtssomeValuesFrom;
    static ArrayList<Statement> stmtsminCardinality;
    static ArrayList<Statement> stmtsmaxCardinality;
    static ArrayList<Statement> stmtscardinality;
    static ArrayList<Statement> stmtsintersectionOf;
    static ArrayList<Statement> stmtsunionOf;
    static ArrayList<Statement> stmtscomplementOf;
    static ArrayList<Statement> stmtsoneOf;
    static ArrayList<Statement> stmtsdisjointWith;
    static ArrayList<Statement> stmtshasValue;
    static ArrayList<Statement> stmtsClass;
    static ArrayList<Statement> stmtsDatatypeProperty;
    static ArrayList<Statement> stmtsObjectProperty;
    static ArrayList<Individual> individuals;
    static ArrayList<Statement> stmtsFunctionalProperty;
    static ArrayList<Statement> stmtsTransitiveProperty;
    static ArrayList<Statement> stmtsSymmetricProperty;
    static ArrayList<Statement> stmtsInverseFunctionalProperty;
    static ArrayList<Statement> stmtsRestriction;
    static ArrayList<Statement> stmtsAllStatements;
    public static void main(String[] args) 
    {
        //model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
        //model.getDocumentManager().addAltEntry( ontolojiDokumaniURI, ontolojiDokumaniYeri );
        //model.read( ontolojiDokumaniURI );
        //printIterator(model.listStatements(null, (Property)RDFS.subClassOf, (RDFNode)null)); //(RDFNode)null
        /*model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM ); //cikarsamasiz
        model.getDocumentManager().addAltEntry( onlolojiDokumaniAdi1, ontolojiDokumaniYeri1 );
        model.read(onlolojiDokumaniAdi1);
        fileIterator(model.listStatements(null, (Property)RDFS.subClassOf, (RDFNode)null), "subClassOf"); */ //(RDFNode)null
        
        ontolojiDosyaAdlari = new ArrayList<String>();
        //SweetOntolojiDosyalariniHazirla();
        //ProtegeSayfasindakiOntolojiDosyalariniHazirla();
        TonesSayfasindakiOntolojiDosyalariniHazirla();
        
        stmtssubClassOf = new ArrayList<Statement>();
        stmtssubPropertyOf = new ArrayList<Statement>();
        stmtsdomain = new ArrayList<Statement>();
        stmtsrange = new ArrayList<Statement>();
        stmtsequivalentClass = new ArrayList<Statement>();
        stmtsequivalentProperty = new ArrayList<Statement>();
        stmtssameAs = new ArrayList<Statement>();
        stmtsdifferentFrom = new ArrayList<Statement>();
        stmtsdistinctMembers = new ArrayList<Statement>();
        stmtsinverseOf = new ArrayList<Statement>();
        stmtsonProperty = new ArrayList<Statement>();
        stmtsallValuesFrom = new ArrayList<Statement>();
        stmtssomeValuesFrom = new ArrayList<Statement>();
        stmtsminCardinality = new ArrayList<Statement>();
        stmtsmaxCardinality = new ArrayList<Statement>();
        stmtscardinality = new ArrayList<Statement>();
        stmtsintersectionOf = new ArrayList<Statement>();
        stmtsunionOf = new ArrayList<Statement>();
        stmtscomplementOf = new ArrayList<Statement>();
        stmtsoneOf = new ArrayList<Statement>();
        stmtsdisjointWith = new ArrayList<Statement>();
        stmtshasValue = new ArrayList<Statement>();
        stmtsClass = new ArrayList<Statement>();
        stmtsDatatypeProperty = new ArrayList<Statement>();
        stmtsObjectProperty = new ArrayList<Statement>();
        individuals = new ArrayList<Individual>();
        stmtsFunctionalProperty = new ArrayList<Statement>();
        stmtsTransitiveProperty = new ArrayList<Statement>();
        stmtsSymmetricProperty = new ArrayList<Statement>();
        stmtsInverseFunctionalProperty = new ArrayList<Statement>();
        stmtsRestriction = new ArrayList<Statement>();
        stmtsAllStatements = new ArrayList<Statement>();
        //for (int i = 0; i < ontolojiDosyaAdlari.size(); i++)
        //{
    	/*onlolojiDokumaniAdi1 = ontolojiDosyaAdlari.get(i);
    	ontolojiDokumaniYeri1 = ontolojiAdresi + ontolojiDosyaAdlari.get(i);
    	model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM ); //cikarsamasiz
        model.getDocumentManager().addAltEntry( ontolojiDosyaAdlari.get(i), ontolojiDokumaniYeri1 );
        model.read(ontolojiDosyaAdlari.get(i));*/
    	onlolojiDokumaniAdi1 = "wine.rdf";
    	ontolojiDokumaniYeri1 = "I:\\Kod\\Workspace\\WineOnt\\" + onlolojiDokumaniAdi1;
    	model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM ); //cikarsamasiz
        //model.getDocumentManager().addAltEntry( onlolojiDokumaniAdi1, ontolojiDokumaniYeri1 );
        //model.read(onlolojiDokumaniAdi1);
        model.getDocumentManager().addAltEntry( "http://www.w3.org/TR/2003/PR-owl-guide-20031209/wine#", "file:///I:/Kod/Workspace/WineOnt/wine.rdf" );
        model.read("http://www.w3.org/TR/2003/PR-owl-guide-20031209/wine#");
            fileIterator2(model.listStatements(null, (Property)RDFS.subClassOf, (RDFNode)null), "subClassOf", stmtssubClassOf); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)RDFS.subPropertyOf, (RDFNode)null), "subPropertyOf", stmtssubPropertyOf); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)RDFS.domain, (RDFNode)null), "domain", stmtsdomain); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)RDFS.range, (RDFNode)null), "range", stmtsrange); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.equivalentClass, (RDFNode)null), "equivalentClass", stmtsequivalentClass); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.equivalentProperty, (RDFNode)null), "equivalentProperty", stmtsequivalentProperty); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.sameAs, (RDFNode)null), "sameAs", stmtssameAs); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.differentFrom, (RDFNode)null), "differentFrom", stmtsdifferentFrom); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.distinctMembers, (RDFNode)null), "distinctMembers", stmtsdistinctMembers); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.inverseOf, (RDFNode)null), "inverseOf", stmtsinverseOf); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.onProperty, (RDFNode)null), "onProperty", stmtsonProperty); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.allValuesFrom, (RDFNode)null), "allValuesFrom", stmtsallValuesFrom); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.someValuesFrom, (RDFNode)null), "someValuesFrom", stmtssomeValuesFrom); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.minCardinality, (RDFNode)null), "minCardinality", stmtsminCardinality); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.maxCardinality, (RDFNode)null), "maxCardinality", stmtsmaxCardinality); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.cardinality, (RDFNode)null), "cardinality", stmtscardinality); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.intersectionOf, (RDFNode)null), "intersectionOf", stmtsintersectionOf); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.unionOf, (RDFNode)null), "unionOf", stmtsunionOf); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.complementOf, (RDFNode)null), "complementOf", stmtscomplementOf); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.oneOf, (RDFNode)null), "oneOf", stmtsoneOf); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.disjointWith, (RDFNode)null), "disjointWith", stmtsdisjointWith); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)OWL.hasValue, (RDFNode)null), "hasValue", stmtshasValue); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)RDF.type, (RDFNode)OWL.Class), "Class", stmtsClass); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)RDF.type, (RDFNode)OWL.DatatypeProperty), "DatatypeProperty", stmtsDatatypeProperty); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)RDF.type, (RDFNode)OWL.ObjectProperty), "ObjectProperty", stmtsObjectProperty); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)RDF.type, (RDFNode)OWL.FunctionalProperty), "FunctionalProperty", stmtsFunctionalProperty); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)RDF.type, (RDFNode)OWL.TransitiveProperty), "TransitiveProperty", stmtsTransitiveProperty); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)RDF.type, (RDFNode)OWL.SymmetricProperty), "SymmetricProperty", stmtsSymmetricProperty); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)RDF.type, (RDFNode)OWL.InverseFunctionalProperty), "InverseFunctionalProperty", stmtsInverseFunctionalProperty); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)RDF.type, (RDFNode)OWL.Restriction), "Restriction", stmtsRestriction); //(RDFNode)null
            fileIterator2(model.listStatements(null, (Property)null, (RDFNode)null), "AllStatements", stmtsAllStatements); //(RDFNode)null
            
            fileIterator2(model.listIndividuals(), "Individual", individuals); //(RDFNode)null
        //}
	}
    
    public static void printIterator(Iterator<?> i) 
    {
        
        if(i.hasNext()) {
	        while (i.hasNext()) 
	            System.out.println( i.next() );
        }       
        else
            System.out.println("<EMPTY>");
    }

    public static void printIterator2(Iterator<?> i) 
    {
        
    	Statement stmt;
        if(i.hasNext()) {
	        while (i.hasNext()) 
	        {
	        	stmt = (Statement)i.next();
	            System.out.println( stmt );
	            printIterator(model.listStatements(stmt.getSubject(),RDFS.domain, (RDFNode)null));
	        }
        }       
        else
            System.out.println("<EMPTY>");
    }

    public static void fileIterator(Iterator<?> i, String fileName) 
    {
    	Statement stmt;
    	try
    	{
    		BufferedWriter yaz = new BufferedWriter(new FileWriter(ontolojiDokumaniDizini2  + fileName + ".txt", true));
	        if(i.hasNext()) {
		        while (i.hasNext()) 
		        {
		        	stmt = (Statement)i.next();
		        	yaz.write(onlolojiDokumaniAdi1 + "," + stmt.getSubject().getURI() + "," + stmt.getPredicate().getLocalName() + "," + stmt.getObject().toString());
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

    public static void fileIterator2(Iterator<?> i, String fileName, ArrayList<Statement> stmts) 
    {
    	Statement stmt;
    	boolean listedeVar;
    	try
    	{
    		BufferedWriter yaz = new BufferedWriter(new FileWriter(ontolojiDokumaniDizini2  + fileName + ".txt", true));
	        if(i.hasNext()) {
		        while (i.hasNext()) 
		        {
		        	stmt = (Statement)i.next();
		        	listedeVar = false;
		        	for (int j = 0; j < stmts.size(); j++)
		        	{
		        		if ((stmts.get(j).getSubject().toString().compareTo(stmt.getSubject().toString()) == 0) && 
		        				(stmts.get(j).getPredicate().toString().compareTo(stmt.getPredicate().toString()) == 0 ) && 
		        				(stmts.get(j).getObject().toString().compareTo(stmt.getObject().toString()) == 0))
		        		{
		        			listedeVar = true;
		        			break;
		        		}
		        		/*if (stmts.get(j).getSubject().toString().compareTo(stmt.getSubject().toString()) == 0)
		        		{
		        			System.out.println("listedeVar1");
		        				if (stmts.get(j).getPredicate().toString().compareTo(stmt.getPredicate().toString()) == 0 )
		        				{
				        			System.out.println("listedeVar2");
				        			System.out.println(stmts.get(j).getObject().toString() + " - " + stmt.getObject().toString());
		        					if (stmts.get(j).getObject().toString().compareTo(stmt.getObject().toString()) == 0)
					        		{
					        			listedeVar = true;
					        			System.out.println("listedeVar3333333333333333333333");
					        			break;
					        		}
		        				}
		        		}*/
		        	}
		        	if (!listedeVar)
		        	{
		        		stmts.add(stmt);
			        	yaz.write(onlolojiDokumaniAdi1 + "," + stmt.getSubject().toString() + "," + stmt.getPredicate().getLocalName() + "," + stmt.getObject().toString());
			        	yaz.newLine();
			        	//yaz.write(onlolojiDokumaniAdi1 + "," + stmt.getSubject().toString() + "," + stmt.getPredicate().toString() + "," + stmt.getObject().toString());
		        	}
		        }
	        }       
	        else
	            System.out.println("<EMPTY>");
	    	yaz.close();
	    	System.out.println("yazma islemi bitti2");
    	} 
    	catch(Exception e)
    	{
            e.printStackTrace();
        }
    }

    public static void fileIterator2(ExtendedIterator<Individual> i, String fileName, ArrayList<Individual> inds) 
    {
    	Individual ind;
    	boolean listedeVar;
    	try
    	{
    		BufferedWriter yaz = new BufferedWriter(new FileWriter(ontolojiDokumaniDizini2  + fileName + ".txt", true));
	        if(i.hasNext()) {
		        while (i.hasNext()) 
		        {
		        	ind = (Individual)i.next();
		        	listedeVar = false;
		        	for (int j = 0; j < inds.size(); j++)
		        	{
		        		if (inds.get(j).toString().compareTo(ind.toString()) == 0)
		        		{
		        			listedeVar = true;
		        			break;
		        		}
		        	}
		        	if (!listedeVar)
		        	{
		        		inds.add(ind);
			        	yaz.write(onlolojiDokumaniAdi1 + "," + ind.getLocalName());
			        	yaz.newLine();
			        	//yaz.write(onlolojiDokumaniAdi1 + "," + stmt.getSubject().toString() + "," + stmt.getPredicate().toString() + "," + stmt.getObject().toString());
		        	}
		        }
	        }       
	        else
	            System.out.println("<EMPTY>");
	    	yaz.close();
	    	System.out.println("yazma islemi bitti2");
    	} 
    	catch(Exception e)
    	{
            e.printStackTrace();
        }
    }

    public static void SweetOntolojiDosyalariniHazirla()
    {
        ontolojiDosyaAdlari.add("astroBody.owl");
        ontolojiDosyaAdlari.add("astroGeodesy.owl");
        ontolojiDosyaAdlari.add("astroHelio.owl");
        ontolojiDosyaAdlari.add("astroPlanet.owl");
        ontolojiDosyaAdlari.add("astroStar.owl");
        ontolojiDosyaAdlari.add("astroSun.owl");
        ontolojiDosyaAdlari.add("atmo.owl");
        ontolojiDosyaAdlari.add("atmoBoundary.owl");
        ontolojiDosyaAdlari.add("atmoCloud.owl");
        ontolojiDosyaAdlari.add("atmoEarthReference.owl");
        ontolojiDosyaAdlari.add("atmoFog.owl");
        ontolojiDosyaAdlari.add("atmoFront.owl");
        ontolojiDosyaAdlari.add("atmoLightning.owl");
        ontolojiDosyaAdlari.add("atmoPrecip.owl");
        ontolojiDosyaAdlari.add("atmoPressure.owl");
        ontolojiDosyaAdlari.add("atmoSky.owl");
        ontolojiDosyaAdlari.add("atmoStability.owl");
        ontolojiDosyaAdlari.add("atmoThermo.owl");
        ontolojiDosyaAdlari.add("atmoWave.owl");
        ontolojiDosyaAdlari.add("atmoWind.owl");
        ontolojiDosyaAdlari.add("atmoWindGlobal.owl");
        ontolojiDosyaAdlari.add("atmoWindMesoscale.owl");
        ontolojiDosyaAdlari.add("atmoWindScale.owl");
        ontolojiDosyaAdlari.add("biol.owl");
        ontolojiDosyaAdlari.add("biolAnimal.owl");
        ontolojiDosyaAdlari.add("biolBiome.owl");
        ontolojiDosyaAdlari.add("biolEcology.owl");
        ontolojiDosyaAdlari.add("biolHealth.owl");
        ontolojiDosyaAdlari.add("biolMicrobiota.owl");
        ontolojiDosyaAdlari.add("biolPlant.owl");
        ontolojiDosyaAdlari.add("biolProcess.owl");
        ontolojiDosyaAdlari.add("chem.owl");
        ontolojiDosyaAdlari.add("chemAcidity.owl");
        ontolojiDosyaAdlari.add("chemCharge.owl");
        ontolojiDosyaAdlari.add("chemCompound.owl");
        ontolojiDosyaAdlari.add("chemCompoundAcid.owl");
        ontolojiDosyaAdlari.add("chemCompoundCFC.owl");
        ontolojiDosyaAdlari.add("chemCompoundHalon.owl");
        ontolojiDosyaAdlari.add("chemCompoundHydrocarbon.owl");
        ontolojiDosyaAdlari.add("chemCompoundIon.owl");
        ontolojiDosyaAdlari.add("chemCompoundOrganic.owl");
        ontolojiDosyaAdlari.add("chemConcentration.owl");
        ontolojiDosyaAdlari.add("chemElement.owl");
        ontolojiDosyaAdlari.add("chemElementalMolecule.owl");
        ontolojiDosyaAdlari.add("chemIsotope.owl");
        ontolojiDosyaAdlari.add("chemProcess.owl");
        ontolojiDosyaAdlari.add("chemState.owl");
        ontolojiDosyaAdlari.add("chemStateChange.owl");
        ontolojiDosyaAdlari.add("chemWater.owl");
        ontolojiDosyaAdlari.add("clim.owl");
        ontolojiDosyaAdlari.add("climIndicator.owl");
        ontolojiDosyaAdlari.add("climOscillation.owl");
        ontolojiDosyaAdlari.add("climZone.owl");
        ontolojiDosyaAdlari.add("cryo.owl");
        ontolojiDosyaAdlari.add("envirAssessment.owl");
        ontolojiDosyaAdlari.add("envirControl.owl");
        ontolojiDosyaAdlari.add("envirEmissionSource.owl");
        ontolojiDosyaAdlari.add("envirImpact.owl");
        ontolojiDosyaAdlari.add("envirIndicator.owl");
        ontolojiDosyaAdlari.add("envirPollutant.owl");
        ontolojiDosyaAdlari.add("envirProtection.owl");
        ontolojiDosyaAdlari.add("envirStandards.owl");
        ontolojiDosyaAdlari.add("envirSustainability.owl");
        ontolojiDosyaAdlari.add("envirTransport.owl");
        ontolojiDosyaAdlari.add("geol.owl");
        ontolojiDosyaAdlari.add("geolBasin.owl");
        ontolojiDosyaAdlari.add("geolConstituent.owl");
        ontolojiDosyaAdlari.add("geolContinental.owl");
        ontolojiDosyaAdlari.add("geolEarthReference.owl");
        ontolojiDosyaAdlari.add("geolEarthquake.owl");
        ontolojiDosyaAdlari.add("geolFault.owl");
        ontolojiDosyaAdlari.add("geolMineral.owl");
        ontolojiDosyaAdlari.add("geolOceanic.owl");
        ontolojiDosyaAdlari.add("geolOrogen.owl");
        ontolojiDosyaAdlari.add("geolPetrology.owl");
        ontolojiDosyaAdlari.add("geolPetrologyIgneous.owl");
        ontolojiDosyaAdlari.add("geolResource.owl");
        ontolojiDosyaAdlari.add("geolTectonics.owl");
        ontolojiDosyaAdlari.add("geolVolcano.owl");
        ontolojiDosyaAdlari.add("human.owl");
        ontolojiDosyaAdlari.add("humanAgriculture.owl");
        ontolojiDosyaAdlari.add("humanCommerce.owl");
        ontolojiDosyaAdlari.add("humanCommunications.owl");
        ontolojiDosyaAdlari.add("humanDecision.owl");
        ontolojiDosyaAdlari.add("humanEnergy.owl");
        ontolojiDosyaAdlari.add("humanExtraction.owl");
        ontolojiDosyaAdlari.add("humanJurisdiction.owl");
        ontolojiDosyaAdlari.add("humanStructure.owl");
        ontolojiDosyaAdlari.add("humanTransport.owl");
        ontolojiDosyaAdlari.add("humanTransportAir.owl");
        ontolojiDosyaAdlari.add("humanTransportSpace.owl");
        ontolojiDosyaAdlari.add("hydro.owl");
        ontolojiDosyaAdlari.add("hydroBodyOfWater.owl");
        ontolojiDosyaAdlari.add("hydroGroundwater.owl");
        ontolojiDosyaAdlari.add("hydroSurface.owl");
        ontolojiDosyaAdlari.add("info.owl");
        ontolojiDosyaAdlari.add("infoFile.owl");
        ontolojiDosyaAdlari.add("infoReduction.owl");
        ontolojiDosyaAdlari.add("infoService.owl");
        ontolojiDosyaAdlari.add("infoTechnology.owl");
        ontolojiDosyaAdlari.add("landCoastal.owl");
        ontolojiDosyaAdlari.add("landFluvial.owl");
        ontolojiDosyaAdlari.add("landGeomorphology.owl");
        ontolojiDosyaAdlari.add("landGlacial.owl");
        ontolojiDosyaAdlari.add("landLandform.owl");
        ontolojiDosyaAdlari.add("landOrographic.owl");
        ontolojiDosyaAdlari.add("landSediment.owl");
        ontolojiDosyaAdlari.add("landSoil.owl");
        ontolojiDosyaAdlari.add("landTectonic.owl");
        ontolojiDosyaAdlari.add("landVolcanic.owl");
        ontolojiDosyaAdlari.add("math.owl");
        ontolojiDosyaAdlari.add("mathCalculus.owl");
        ontolojiDosyaAdlari.add("mathFunction.owl");
        ontolojiDosyaAdlari.add("mathOperation.owl");
        ontolojiDosyaAdlari.add("mathRelation.owl");
        ontolojiDosyaAdlari.add("mathSolution.owl");
        ontolojiDosyaAdlari.add("mathStatistics.owl");
        ontolojiDosyaAdlari.add("mathVector.owl");
        ontolojiDosyaAdlari.add("ocean.owl");
        ontolojiDosyaAdlari.add("oceanCirculation.owl");
        ontolojiDosyaAdlari.add("oceanEarthReference.owl");
        ontolojiDosyaAdlari.add("oceanFloor.owl");
        ontolojiDosyaAdlari.add("oceanIce.owl");
        ontolojiDosyaAdlari.add("phys.owl");
        ontolojiDosyaAdlari.add("physContinuumMechanics.owl");
        ontolojiDosyaAdlari.add("physDynamics.owl");
        ontolojiDosyaAdlari.add("physDynamicsRotational.owl");
        ontolojiDosyaAdlari.add("physElecMag.owl");
        ontolojiDosyaAdlari.add("physEnergy.owl");
        ontolojiDosyaAdlari.add("physFluid.owl");
        ontolojiDosyaAdlari.add("physFluidDynamics.owl");
        ontolojiDosyaAdlari.add("physFluidInstability.owl");
        ontolojiDosyaAdlari.add("physFluidWave.owl");
        ontolojiDosyaAdlari.add("physGravity.owl");
        ontolojiDosyaAdlari.add("physParticle.owl");
        ontolojiDosyaAdlari.add("physPlasma.owl");
        ontolojiDosyaAdlari.add("physPressure.owl");
        ontolojiDosyaAdlari.add("physRadiation.owl");
        ontolojiDosyaAdlari.add("physRadiationMedium.owl");
        ontolojiDosyaAdlari.add("physSolid.owl");
        ontolojiDosyaAdlari.add("physSolidConsistence.owl");
        ontolojiDosyaAdlari.add("physSolidDeformation.owl");
        ontolojiDosyaAdlari.add("physSolidFailure.owl");
        ontolojiDosyaAdlari.add("physSound.owl");
        ontolojiDosyaAdlari.add("physSpectrum.owl");
        ontolojiDosyaAdlari.add("physSpectrumUV.owl");
        ontolojiDosyaAdlari.add("physSpectrumVisible.owl");
        ontolojiDosyaAdlari.add("physThermo.owl");
        ontolojiDosyaAdlari.add("physTransport.owl");
        ontolojiDosyaAdlari.add("physWaves.owl");
        ontolojiDosyaAdlari.add("sciCategorical.owl");
        ontolojiDosyaAdlari.add("sciInstrument.owl");
        ontolojiDosyaAdlari.add("sciModel.owl");
        ontolojiDosyaAdlari.add("sciOrdinal.owl");
        ontolojiDosyaAdlari.add("sciProvenance.owl");
        ontolojiDosyaAdlari.add("sciResearch.owl");
        ontolojiDosyaAdlari.add("sciRole.owl");
        ontolojiDosyaAdlari.add("sciSystem.owl");
        ontolojiDosyaAdlari.add("sciSystemDynamics.owl");
        ontolojiDosyaAdlari.add("sciSystemState.owl");
        ontolojiDosyaAdlari.add("sciUncertainty.owl");
        ontolojiDosyaAdlari.add("sciUnits.owl");
        ontolojiDosyaAdlari.add("space.owl");
        ontolojiDosyaAdlari.add("spaceCategory.owl");
        ontolojiDosyaAdlari.add("spaceCoordinates.owl");
        ontolojiDosyaAdlari.add("spaceDirection.owl");
        ontolojiDosyaAdlari.add("spaceDistribution.owl");
        ontolojiDosyaAdlari.add("spaceExtent.owl");
        ontolojiDosyaAdlari.add("spaceObject.owl");
        ontolojiDosyaAdlari.add("spaceRelation.owl");
        ontolojiDosyaAdlari.add("spaceScale.owl");
        ontolojiDosyaAdlari.add("sweetAll.owl");
        ontolojiDosyaAdlari.add("time.owl");
        ontolojiDosyaAdlari.add("timeDiurnal.owl");
        ontolojiDosyaAdlari.add("timeExtent.owl");
        ontolojiDosyaAdlari.add("timeGeologic.owl");
        ontolojiDosyaAdlari.add("timeScale.owl");
        ontolojiDosyaAdlari.add("timeSeasonal.owl");
        ontolojiDosyaAdlari.add("top.owl");
    }
    
    public static void ProtegeSayfasindakiOntolojiDosyalariniHazirla()
    {
    	ontolojiDosyaAdlari.add("camera2.owl");
    	ontolojiDosyaAdlari.add("cinemaChain.owl");
    	ontolojiDosyaAdlari.add("Countries.xml");
    	ontolojiDosyaAdlari.add("family.swrl.owl");
    	ontolojiDosyaAdlari.add("Finance.owl");
    	ontolojiDosyaAdlari.add("index.rdf");
    	ontolojiDosyaAdlari.add("generations.owl");
    	ontolojiDosyaAdlari.add("IPDFull.owl");
    	ontolojiDosyaAdlari.add("koala.owl");
    	ontolojiDosyaAdlari.add("Monetary_ontology.rdf-xml.owl");
    	//ontolojiDosyaAdlari.add("performance.owl");
    	ontolojiDosyaAdlari.add("tourism.owl");
    	ontolojiDosyaAdlari.add("travel.owl");
    	ontolojiDosyaAdlari.add("wine.owl");
    }
    
    public static void TonesSayfasindakiOntolojiDosyalariniHazirla()
    {
    	ontolojiDosyaAdlari.add("photography.owl");
    	ontolojiDosyaAdlari.add("movie.owl");
    	ontolojiDosyaAdlari.add("Hydrology.owl");
    	ontolojiDosyaAdlari.add("foodswap.owl");
    	ontolojiDosyaAdlari.add("expression.owl");
    	ontolojiDosyaAdlari.add("people1.owl");
    	ontolojiDosyaAdlari.add("dbpedia-ontology.owl");
    	ontolojiDosyaAdlari.add("laptops.owl");
    	ontolojiDosyaAdlari.add("BuildingsAndPlaces.owl");
    }
}
