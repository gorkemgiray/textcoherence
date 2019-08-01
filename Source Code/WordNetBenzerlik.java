import edu.sussex.nlp.jws.HirstAndStOnge;
import edu.sussex.nlp.jws.JWS;
import edu.sussex.nlp.jws.JiangAndConrath;
import edu.sussex.nlp.jws.LeacockAndChodorow;
import edu.sussex.nlp.jws.Lin;
import edu.sussex.nlp.jws.WuAndPalmer;


public class WordNetBenzerlik 
{

	edu.sussex.nlp.jws.Resnik res;
	Lin lin;
	JiangAndConrath jc;
	WuAndPalmer wp;
	LeacockAndChodorow lc;
	HirstAndStOnge hso;
	
	public WordNetBenzerlik()
	{
		String dir = "I:/Kod/Workspace/WordNet"; 
		//JWS	ws = new JWS(dir, "3.0"); //semcor.dat icin
		//JWS	ws = new JWS(dir, "3.0", "ic-bnc-resnik.dat"); 
		//JWS	ws = new JWS(dir, "3.0", "ic-brown-resnik.dat");
		JWS	ws = new JWS(dir, "3.0", "ic-treebank-resnik.dat");
		//JWS	ws = new JWS(dir, "3.0", "ic-shaks-resnik.dat");
		
		res = ws.getResnik();
		lin = ws.getLin();
		jc = ws.getJiangAndConrath();
		wp = ws.getWuAndPalmer();
		lc = ws.getLeacockAndChodorow();
		hso = ws.getHirstAndStOnge();
	}
	
	private double getMax(double d1, double d2, double d3)
	{
		if ((d1 >= d2) && (d1 >= d3))
			return d1;
		else if ((d2 >= d1) && (d2 >= d3))
			return d2;
		else
			return d3;
	}
	
	public double compareStringsResnik(String String1, String String2)
	{
		double noun_sim = 0;
		double verb_sim = 0;
		double adjective_sim = 0;
		//if (String1.compareTo(String2) == 0)
			//System.out.println(String1 + " - " + String2 + " res: " + res.max(String1, String2, "n"));
		/*{
			return 1;
		}
		else
		{*/
			try
			{
				noun_sim = res.max(String1, String2, "n");
			}
			catch (Exception e)
			{
				noun_sim = 0;
			}
			try
			{
				verb_sim = res.max(String1, String2, "v");
				//verb_sim = 0;
			}
			catch (Exception e)
			{
				verb_sim = 0;
			}
			try
			{
				adjective_sim = res.max(String1, String2, "a");
			}
			catch (Exception e)
			{
				adjective_sim = 0;
			}
			return getMax(noun_sim, verb_sim, adjective_sim);
		//}
	}
	
	public double compareStringsLin(String String1, String String2)
	{
		double noun_sim = 0;
		double verb_sim = 0;
		double adjective_sim = 0;
		//if (String1.compareTo(String2) == 0)
			//System.out.println(String1 + " - " + String2 + " lin: " + lin.max(String1, String2, "n"));
		/*{
			return 1;
		}
		else
		{*/
			try
			{
				noun_sim = lin.max(String1, String2, "n");
			}
			catch (Exception e)
			{
				noun_sim = 0;
			}
			try
			{
				verb_sim = lin.max(String1, String2, "v");
			}
			catch (Exception e)
			{
				verb_sim = 0;
			}
			try
			{
				adjective_sim = lin.max(String1, String2, "a");
			}
			catch (Exception e)
			{
				adjective_sim = 0;
			}
			return getMax(noun_sim, verb_sim, adjective_sim);
		//}
	}

	public double compareStringsJC(String String1, String String2)
	{
		double noun_sim = 0;
		double verb_sim = 0;
		double adjective_sim = 0;
		double esanlamli = 1.2876699500047589E7;
		//if (String1.compareTo(String2) == 0)
		//{
			//System.out.println(String1 + " - " + String2 + " jc: " + jc.max(String1, String2, "n"));
		/*	return 1;
		}
		else
		{*/
			try
			{
				noun_sim = jc.max(String1, String2, "n");
				/*if (noun_sim == esanlamli)
					noun_sim = 1;
				else if (noun_sim > 100)
					noun_sim = 0;*/
			}
			catch (Exception e)
			{
				noun_sim = 0;
			}
			try
			{
				verb_sim = jc.max(String1, String2, "v");
				/*if (verb_sim == esanlamli)
					verb_sim = 1;
				else if (verb_sim > 100)
					verb_sim = 0;*/
			}
			catch (Exception e)
			{
				verb_sim = 0;
			}
			try
			{
				adjective_sim = jc.max(String1, String2, "a");
				/*if (adjective_sim == esanlamli)
					adjective_sim = 1;
				else if (adjective_sim > 100)
					adjective_sim = 0;*/
			}
			catch (Exception e)
			{
				adjective_sim = 0;
			}
			return getMax(noun_sim, verb_sim, adjective_sim);
		//}
	}

	public double compareStringsWP(String String1, String String2)
	{
		double noun_sim = 0;
		double verb_sim = 0;
		double adjective_sim = 0;
		/*if (String1.compareTo(String2) == 0)
		{
			return 1;
		}
		else
		{*/
			try
			{
				noun_sim = wp.max(String1, String2, "n");
			}
			catch (Exception e)
			{
				noun_sim = 0;
			}
			try
			{
				verb_sim = wp.max(String1, String2, "v");
				//verb_sim = 0;
			}
			catch (Exception e)
			{
				verb_sim = 0;
			}
			try
			{
				adjective_sim = wp.max(String1, String2, "a");
			}
			catch (Exception e)
			{
				adjective_sim = 0;
			}
			return getMax(noun_sim, verb_sim, adjective_sim);
		//}
	}

	public double compareStringsLC(String String1, String String2)
	{
		double noun_sim = 0;
		double verb_sim = 0;
		double adjective_sim = 0;
		/*if (String1.compareTo(String2) == 0)
		{
			return 1;
		}
		else
		{*/
			try
			{
				noun_sim = lc.max(String1, String2, "n");
			}
			catch (Exception e)
			{
				noun_sim = 0;
			}
			try
			{
				verb_sim = lc.max(String1, String2, "v");
				//verb_sim = 0;
			}
			catch (Exception e)
			{
				verb_sim = 0;
			}
			try
			{
				adjective_sim = lc.max(String1, String2, "a");
			}
			catch (Exception e)
			{
				adjective_sim = 0;
			}
			return getMax(noun_sim, verb_sim, adjective_sim);
		//}
	}

	public double compareStringsHSO(String String1, String String2)
	{
		double noun_sim = 0;
		double verb_sim = 0;
		double adjective_sim = 0;
		if (String1.compareTo(String2) == 0)
		{
			return 1;
		}
		else
		{
			try
			{
				noun_sim = hso.max(String1, String2, "n");
			}
			catch (Exception e)
			{
				noun_sim = 0;
			}
			try
			{
				verb_sim = hso.max(String1, String2, "v");
			}
			catch (Exception e)
			{
				verb_sim = 0;
			}
			try
			{
				adjective_sim = hso.max(String1, String2, "a");
			}
			catch (Exception e)
			{
				adjective_sim = 0;
			}
			return getMax(noun_sim, verb_sim, adjective_sim);
		}
	}
}