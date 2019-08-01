import java.util.Iterator;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.ontology.OntDocumentManager;

import org.mindswap.pellet.jena.PelletReasonerFactory;

public class JenaIntroduction {

	static OntModel m;
	static String rdf_ns = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	static String ns = "http://www.owl-ontologies.com/travel.owl";
	public static void main(String[] args)
	{
		//OntModel m = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM ); //cikarsamasiz
        OntDocumentManager dm = m.getDocumentManager();
		dm.addAltEntry(ns, "file:///C:/travel.owl");
		m.read(ns);
		//fileIterator(m);
		System.out.println(findTypeOfResource(ns + "#BondiBeach"));
	}
	
    public static void fileIterator(OntModel m) {
    	try
    	{
    		Iterator<Statement> i = m.listStatements();
	        if(i.hasNext()) {
		        while (i.hasNext()) 
		        {
		        	System.out.println(i.next().toString());
		        }
	        }       
	        else
	            System.out.println("<EMPTY>");
    	}
    	catch(Exception e)
    	{
            e.printStackTrace();
        }
    }
    
    public static String findTypeOfResource(String resURI)
    {
		Resource res = null;
		String result = "";
    	try
		{
			res = m.getOntResource(resURI);
			if (res == null) 
				return "Resource cannot be found in the ontology";

			Iterator<Statement> i = m.listStatements(res, null, (RDFNode)null);
	        Statement stmt;
			if(i.hasNext()) {
		        while (i.hasNext()) 
		        {
		        	stmt = i.next();
		        	if (stmt.getPredicate().toString().compareTo(rdf_ns + "type") == 0)
		        	{
		        		if (result.compareTo("") == 0)
			        		result = stmt.getObject().toString();
		        		else
		        			result = result + " , " + stmt.getObject().toString();
		        	}
		        }
	        } 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
    }
}