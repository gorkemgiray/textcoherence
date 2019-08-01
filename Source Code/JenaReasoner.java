// Portions Copyright (c) 2006 - 2008, Clark & Parsia, LLC. <http://www.clarkparsia.com>
// Clark & Parsia, LLC parts of this source code are available under the terms of the Affero General Public License v3.
//
// Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
// Questions, comments, or requests for clarification: licensing@clarkparsia.com

//package org.mindswap.pellet.examples;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
//import com.hp.hpl.jena.rdf.model.InfModel;
//import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
//import com.hp.hpl.jena.rdf.model.Property;
//import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
//import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.Statement;
//import com.hp.hpl.jena.reasoner.Reasoner;
//import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
//import com.hp.hpl.jena.vocabulary.RDFS;
//import com.hp.hpl.jena.vocabulary.XSD;
//import com.hp.hpl.jena.rdf.model.SimpleSelector;
//import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import java.io.*;

/**
 * An example to show how to use PelletReasoner as a Jena reasoner. The reasoner can
 * be directly attached to a plain RDF <code>Model</code> or attached to an <code>OntModel</code>.
 * This program shows how to do both of these operations and achieve the exact same results. 
 * 
 * @author Evren Sirin
 */
public class JenaReasoner {
    
    static String rdfns = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    static String rdfsns = "http://www.w3.org/2000/01/rdf-schema#";
    static String owlns = "http://www.w3.org/2002/07/owl#";
    static String xsdns = "http://www.w3.org/2001/XMLSchema#";

	public static void main(String[] args) {
        //usageWithDefaultModel();
        
        usageWithOntModel2();
    }
    
    
    public static void usageWithOntModel2() {    
        OntModel m = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
        OntDocumentManager dm = m.getDocumentManager();
        /*dm.addAltEntry( "http://www.w3.org/2001/sw/WebOnt/guide-src/wine#",
        "file:///I:/Kod/Workspace/Deney/Camera.owl"    );
		m.read( "http://www.w3.org/2001/sw/WebOnt/guide-src/wine#" );*/
		dm.addAltEntry( "",
		"file:///I:/Kod/Workspace/Deney/Camera.owl"    );
		m.read( "" );

        fileIterator(m.listStatements(), null);
        //printIterator(m.listStatements(), null);
        //System.out.println();
    }
    
    public static void usageWithOntModel3() {   
        OntModel model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
        
        //model.getDocumentManager().addAltEntry( "http://www.w3.org/2001/sw/WebOnt/guide-src/wine",
        //"file:///C:/Documents and Settings/Görkem Giray/My Documents/Downloads/software.owl" );
        model.getDocumentManager().addAltEntry( "http://www.w3.org/2001/sw/WebOnt/guide-src/wine",
        "file:///I:/Kod/Workspace/Deney/nasdaq-ont.owl" );

        // read the file
        //model.read( ont );
        model.read( "http://www.w3.org/2001/sw/WebOnt/guide-src/wine" );
        
        String startNode;
        String endNode;
        //startNode = "http://www.w3.org/2001/sw/WebOnt/guide-src/wine#US_President";
        //endNode = "http://www.w3.org/2001/sw/WebOnt/guide-src/wine#Obama2";
        //startNode = "http://www.w3.org/2001/sw/WebOnt/guide-src/wine#Obama2";
        //endNode = "http://www.w3.org/2001/sw/WebOnt/guide-src/wine#US_President";
        //startNode = "http://www.w3.org/2001/sw/WebOnt/guide-src/wine#Otobus";
        //endNode = "http://www.w3.org/2001/sw/WebOnt/guide-src/wine#SuperWinery";
        //startNode = "http://www.w3.org/2001/sw/WebOnt/guide-src/wine#SuperWinery";
        //endNode = "http://www.w3.org/2001/sw/WebOnt/guide-src/wine#Otobus";
        //startNode = "http://keg.cs.tsinghua.edu.cn/ontology/software#Project_Admin";
        //endNode = "http://keg.cs.tsinghua.edu.cn/ontology/software#LastestNew";
        startNode = "http://www.w3.org/2001/sw/WebOnt/guide-src/wine#stock";
        endNode = "http://www.daml.org/2002/08/nasdaq/nasdaq-ont#Market";
        
        List<Statement> stmts = getStatements(model, startNode);
        List<Node> paths = new LinkedList<Node>();
        List<Node> TargetNodes = new LinkedList<Node>();
        
        Statement stmt;
        boolean direction = false;
        for (int m = 0; m < stmts.size(); m++)
        {
        	stmt = (Statement)stmts.get(m);
        	if ((stmt.getSubject().toString().compareTo(startNode) == 0) && (stmt.getObject().toString().compareTo(endNode) == 0))
        	{
        		//baslangic ile bitis dugumler arasinda bir ifadeden olusan iliski var
        		Node n = new Node(stmt, (Node)null, true, true);
            	paths.add(n);
            	TargetNodes.add(n);
        	}
        	else if ((stmt.getObject().toString().compareTo(startNode) == 0) && (stmt.getSubject().toString().compareTo(endNode) == 0))
        	{
        		//baslangic ile bitis dugumler arasinda bir ifadeden olusan iliski var
        		Node n = new Node(stmt, (Node)null, true, false);
            	paths.add(n);
            	TargetNodes.add(n);
        	}
        	else
        	{
        		if ((stmt.getPredicate().toString().compareTo(rdfsns + "domain") == 0) || (stmt.getPredicate().toString().compareTo(rdfsns + "range") == 0) || (stmt.getPredicate().toString().compareTo(rdfsns + "subClassOf") == 0))
        		{
        			if (stmt.getSubject().getURI().compareTo(startNode) == 0) //
        			{
        				direction = true;
        			}
        			else
        			{
        				direction = false;
        			}
        		}
        		else
        		{
        			direction = true;
        		}
        		Node n = new Node(stmt, (Node)null, false, direction);
            	paths.add(n);
            	findChildren(model, n, startNode, endNode, TargetNodes, 0);
        	}
        }
        System.out.println("bitti");
        printPaths(TargetNodes);
    }
    
    private static void printPaths(List<Node> TargetNodes)
    {
    	Node tmp;
    	for (int m = 0; m < TargetNodes.size(); m++)
    	{
    		System.out.println(" - " + m + " - ");
   			tmp = TargetNodes.get(m);
   			do
   			{
   				System.out.println(tmp.getStatement().toString());
   				tmp = tmp.getParent();
   			} while (tmp != null);
    	}
    	
    }
    //static int sayac;
    private static void findChildren(OntModel model, Node n, String startNode, String endNode, List<Node> TargetNodes, int depth)
    {
    	//System.out.print(sayac++);
    	String m_startNode;
    	//if ((n.getStatement().getPredicate().toString().compareTo(rdfsns + "domain") == 0) || (n.getStatement().getPredicate().toString().compareTo(rdfsns + "range") == 0))    	
       	/*if (n.getStatement().getPredicate().toString().compareTo(rdfsns + "domain") == 0)    	
    	{
    		m_startNode = n.getStatement().getSubject().toString();
    	}
    	else
    	{
    		m_startNode = n.getStatement().getObject().toString();
    	}*/
       	if (n.getDirection())    	
    	{
    		m_startNode = n.getStatement().getObject().toString();
    	}
    	else
    	{
    		m_startNode = n.getStatement().getSubject().toString();
    	}
    	
    	List<Statement> stmts;
    	stmts = getStatements(model, m_startNode);
    	
    	if (stmts == null)
    	{
    		return;
    	}
    	else
    	{
    		boolean flag;
    		for (int m = 0; m < stmts.size(); m++)
    		{
    			
    			System.out.println(depth + ". " + stmts.get(m).toString());
    	    	//Agacin bir dalinda bir statement birden fazla kere olmamali. Aksi takdirde sonsuz dongu oluyor.
    			flag = true;
    			Node tmp = n;
    			while (tmp != null)
       			{
       				if ((stmts.get(m).getSubject().toString().compareTo(tmp.getStatement().getSubject().toString()) == 0) && (stmts.get(m).getPredicate().toString().compareTo(tmp.getStatement().getPredicate().toString()) == 0) && (stmts.get(m).getObject().toString().compareTo(tmp.getStatement().getObject().toString()) == 0))
       				{
       					flag = false;
       					break; //while
       				}
       				tmp = tmp.getParent();
       			} 
       			
    			if (flag)
    			{
    				Node nn;
	    			if (stmts.get(m).getSubject().toString().compareTo(m_startNode) == 0)
	    			{
	    				nn = new Node(stmts.get(m), n, false, true);
	    			}
	    			else
	    			{
	    				nn = new Node(stmts.get(m), n, false, false);
	    			}
	    			n.addChild(nn);
	    			
	    			if (stmts.get(m).getObject().toString().compareTo(endNode) == 0)
	    			{
	    				nn.setTarget(true);
	    				nn.setDirection(true);
	    				TargetNodes.add(nn);
	    				return;
	    			}
	    			else if (stmts.get(m).getSubject().toString().compareTo(endNode) == 0)
	    			{
	    				nn.setTarget(true);
	    				nn.setDirection(false);
	    				TargetNodes.add(nn);
	    				return;
	    			}
	    			else if ((stmts.get(m).getObject().toString().compareTo(startNode) == 0) || (stmts.get(m).getSubject().toString().compareTo(startNode) == 0)) //??? KONTROL ET
	    			{
	    				//Object baslangic dugumune esitse sonsuz donguyu engelle
	    				//return;
	    			}
	    			//else if ((!(((Node)stmts.get(m)).getDirection())) && (stmts.get(m).getSubject().toString().compareTo(startNode) == 0)) //??? KONTROL ET
	    			//{
	    				//Object baslangic dugumune esitse sonsuz donguyu engelle
	    				//return;
	    			//}
	    			else
	    			{
	    				findChildren(model, nn, startNode, endNode, TargetNodes, ++depth);
	    			}
    			}
    		}
    	}
    }
    
    private static List<Statement> getStatements(OntModel model, String startNode)
    {
        
    	Resource res = model.getResource(startNode);
    	
        if (res == null)
        	return null;
        
        //printIterator(model.listStatements(res, null, (RDFNode)null), null);
        
        //Iterator<?> i = model.listStatements(res, null, (RDFNode)null);
        Iterator<?> i = ((LinkedList<Statement>)listStatements(model, res)).iterator();
        List<Statement> stmts = new LinkedList<Statement>();
        Statement stmt = null;
        
        if(i.hasNext()) {
	        while (i.hasNext()) 
	        {
	            stmt = (Statement)i.next();
	            //System.out.println(" * " + stmt.getPredicate().getLocalName());
	            if (stmt.getObject().isURIResource())
	            {
	            	if ((((Resource)stmt.getObject()).getNameSpace().compareTo(xsdns)) != 0)
	            	{
			            if (((stmt.getPredicate().toString().compareTo(rdfns + "type") == 0) & (stmt.getObject().toString().compareTo(owlns + "Thing") == 0)) ||
			            		((stmt.getPredicate().toString().compareTo(rdfsns + "subClassOf") == 0) & (stmt.getObject().toString().compareTo(owlns + "Thing") == 0)) ||
			            		((stmt.getPredicate().toString().compareTo(rdfns + "type") == 0) & (stmt.getObject().toString().compareTo(owlns + "Class") == 0)) ||
			            		((stmt.getPredicate().toString().compareTo(rdfns + "type") == 0) & (stmt.getObject().toString().compareTo(owlns + "ObjectProperty") == 0)) ||
			            		((stmt.getPredicate().toString().compareTo(rdfns + "type") == 0) & (stmt.getObject().toString().compareTo(owlns + "DatatypeProperty") == 0)) ||
			            		((stmt.getPredicate().toString().compareTo(rdfns + "type") == 0) & (stmt.getObject().toString().compareTo(owlns + "FunctionalProperty") == 0)) ||
			            		((stmt.getPredicate().toString().compareTo(rdfns + "type") == 0) & (stmt.getObject().toString().compareTo(owlns + "InverseFunctionalProperty") == 0)) ||
			            		((stmt.getPredicate().toString().compareTo(rdfns + "type") == 0) & (stmt.getObject().toString().compareTo(owlns + "TransitiveProperty") == 0)) ||
			            		((stmt.getPredicate().toString().compareTo(rdfns + "type") == 0) & (stmt.getObject().toString().compareTo(owlns + "SymmetricProperty") == 0)) ||
			            		((stmt.getPredicate().toString().compareTo(rdfns + "type") == 0) & (stmt.getObject().toString().compareTo(owlns + "Restriction") == 0)) ||
			            		((stmt.getPredicate().toString().compareTo(rdfns + "type") == 0) & (stmt.getObject().toString().compareTo(owlns + "DataRange") == 0)) ||
			            		((stmt.getPredicate().toString().compareTo(rdfns + "type") == 0) & (stmt.getObject().toString().compareTo(owlns + "Ontology") == 0)) ||
			            		((stmt.getSubject().toString().compareTo(stmt.getObject().toString())) == 0) || //equivalentClass, equivalentProperty, sameAs, subPropertyOf  
			    	        	//(((stmt.getPredicate().getURI().compareTo(owlns + "equivalentClass") == 0) & (stmt.getSubject().getURI().compareTo(stmt.getObject().toString())) == 0)) ||
			    	        	//(((stmt.getPredicate().getURI().compareTo(owlns + "equivalentProperty") == 0) & (stmt.getSubject().getURI().compareTo(stmt.getObject().toString())) == 0)) ||
			    	        	//(((stmt.getPredicate().getURI().compareTo(owlns + "sameAs") == 0) & (stmt.getSubject().getURI().compareTo(stmt.getObject().toString())) == 0)) ||
			            		//stmt.getObject().isLiteral() ||
			            		//?????????(stmt.getObject().isURIResource() & (((Resource)stmt.getObject()).getNameSpace().compareTo(xsdns)) ==0) ||
			            		(stmt.getObject().toString().compareTo(owlns + "topDataProperty") == 0) ||
			    	        	(stmt.getObject().toString().compareTo(owlns + "bottomDataProperty") == 0) ||
			            		(stmt.getObject().toString().compareTo(owlns + "topObjectProperty") == 0) ||
			    	        	(stmt.getObject().toString().compareTo(owlns + "bottomObjectProperty") == 0) ||
			    	        	(stmt.getObject().toString().compareTo(owlns + "Nothing") == 0) ||
			    	        	(stmt.getSubject().toString().compareTo(owlns + "Nothing") == 0) ||
			    	        	(stmt.getPredicate().toString().compareTo(owlns + "propertyDisjointWith") == 0) ||
			    	        	(stmt.getObject().toString().compareTo(rdfns + "nil") == 0))
			            {
			            	//Bazi cikarsama ifadelerini ele. Bunlar patika cikarmak icin gerekli degil.
			            } 
			            else if (stmt.getPredicate().toString().compareTo(rdfsns + "subClassOf") == 0)
			            {
			            	if (stmt.getObject().isAnon())
			            	{
		    	        		stmts.add(stmt);
			            	}
			            	else
			            	{
				            	//Subject'in sadece direk superclass'larini al. Diger turlu hiyerarsideki genelligi hesaplayamayiz.
				            	OntClass oc = model.getOntClass(stmt.getSubject().getURI());
				            	ExtendedIterator<OntClass> j = oc.listSuperClasses(true);
				                if(j.hasNext()) {
				        	        while (j.hasNext()) 
				        	        {
				        	        	if (stmt.getObject().toString().compareTo(((OntClass)j.next()).getURI()) == 0)
				        	        	{
				        	        		stmts.add(stmt);
				        	        		//System.out.println(" - " + stmt.toString());
				        	        		break;
				        	        	}
				        	        }
				                }
			            	}
			            	//printIterator(oc.listSuperClasses(true), null);
			            }
			            else
			            {
			        		stmts.add(stmt);
			        		//System.out.println(" - " + stmt.toString());
			        	}
	            	}
	            }
	        }
        }
    	return stmts;
    }
    
    private static List<Statement> listStatements(OntModel model, Resource res)
    {
    	List<Statement> stmts;
    	Iterator<Statement> tmp = model.listStatements();
    	stmts = new LinkedList<Statement>();
    	Statement stmt;
    	
    	if(tmp.hasNext()) {
	        while (tmp.hasNext()) 
	        {
	        	stmt = (Statement)tmp.next();
	        	
	        	//if (stmt.getSubject().getURI().compareTo(res.getURI()) == 0)
		        if (stmt.getSubject().toString().compareTo(res.toString()) == 0)
	        	{
	        		stmts.add(stmt);
	        	}
		        else if ((stmt.getPredicate().toString().compareTo(rdfsns + "domain") == 0) || (stmt.getPredicate().toString().compareTo(rdfsns + "range") == 0) || (stmt.getPredicate().toString().compareTo(rdfsns + "subClassOf") == 0))
		        //else if (stmt.getPredicate().toString().compareTo(rdfsns + "domain") == 0)
		        {
		        	if ((stmt.getObject().isResource()) || (stmt.getObject().isAnon()))
		        	{
		        		if (stmt.getObject().toString().compareTo(res.toString()) == 0)
		        			stmts.add(stmt);
		        	}
		        	else if ((stmt.getSubject().isResource()) || (stmt.getSubject().isAnon()))
		        	{
		        		if (stmt.getSubject().toString().compareTo(res.toString()) == 0)
		        			stmts.add(stmt);
		        	}
		        }
	        }
        }       
    	
    	return stmts;
    }
    
    public static void printIterator(Iterator<?> i, String header) {
        /*System.out.println(header);
        for(int c = 0; c < header.length(); c++)
            System.out.print("=");
        System.out.println();*/
        
        if(i.hasNext()) {
	        while (i.hasNext()) 
	            System.out.println( i.next() );
        }       
        else
            System.out.println("<EMPTY>");
        
        //System.out.println();
    }

    public static void fileIterator(Iterator<?> i, String header) {
        /*System.out.println(header);
        for(int c = 0; c < header.length(); c++)
            System.out.print("=");
        System.out.println();*/
    	char c = '\n';
    	try
    	{

    	BufferedWriter yaz = new BufferedWriter(new FileWriter("I:\\Kod\\Workspace\\Deney\\yenidosya.txt"));
    	
        if(i.hasNext()) {
	        while (i.hasNext()) 
	        {
	        	//yaz.write(c);
	        	yaz.write(i.next().toString());
	        	yaz.newLine();
	        }
        }       
        else
            System.out.println("<EMPTY>");
    	yaz.close();
    	System.out.println("yazma islemi bitti");
    	} catch(Exception e)
    	{
            e.printStackTrace();
        }

        //System.out.println();
    }
}
