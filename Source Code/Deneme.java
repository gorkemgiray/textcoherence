import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.BoxLayout;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

import java.awt.Rectangle;

public class Deneme extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JComboBox jComboBox = null;
	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.setName("jComboBox");
			jComboBox.setBounds(new Rectangle(32, 52, 401, 22));
		}
		return jComboBox;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Deneme thisClass = new Deneme();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
				
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public Deneme() {
		super();
		initialize();
		doldur();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(541, 315);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJComboBox(), null);
		}
		return jContentPane;
	}

	private void doldur()
	{
		ExtendedIterator<OntClass> ei = Patika.SiniflariAl();
		while (ei.hasNext())
		{
			//if (((OntClass)ei.next()).getLocalName().compareTo("") != 0)
				jComboBox.addItem(((OntClass)ei.next()).getURI());
		}
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
