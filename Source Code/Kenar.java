import java.util.LinkedList;

import com.hp.hpl.jena.rdf.model.Statement;


public class Kenar {
	private Statement m_ifade;
	//private Node parent;
	//private LinkedList<Node> children;
	//private boolean target;
	private boolean m_yon; //true: left to right; false: right to left

	public Kenar(Statement ifade, boolean yon)
	{
		m_ifade = ifade;
		m_yon = yon;
	}

	public Statement ifadeAl()
	{
		return m_ifade;
	}
	
	public boolean yonAl()
	{
		return m_yon;
	}

}
