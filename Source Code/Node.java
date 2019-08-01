import java.util.LinkedList;
import com.hp.hpl.jena.rdf.model.Statement;

public class Node {
	private Statement statement;
	private Node parent;
	private LinkedList<Node> children;
	private boolean target;
	private boolean direction; //true: left to right; false: right to left

	public Node(Statement stmt, Node par, boolean tar, boolean dir)
	{
		statement = stmt;
		parent = par;
		children = new LinkedList<Node>();
		target = tar;
		direction = dir;
	}

	public void setStatement(Statement s)
	{
		statement = s;
	}

	public Statement getStatement()
	{
		return statement;
	}
	
	public void addChild(Node n)
	{
		children.add(n);
	}
	
	public void setParent(Node n)
	{
		parent = n;
	}

	public Node getParent()
	{
		return parent;
	}

	public void setTarget(boolean t)
	{
		target = t;
	}

	public boolean getTarget()
	{
		return target;
	}

	public void setDirection(boolean dir)
	{
		direction = dir;
	}

	public boolean getDirection()
	{
		return direction;
	}

}