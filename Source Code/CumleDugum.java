import java.util.ArrayList;


public class CumleDugum 
{
	private int m_CumleNo;
	private long m_CumleBas;
	private long m_CumleBit;
	private double m_GGBagdasiklik;
	private double m_ResnikBagdasiklik;
	private double m_LinBagdasiklik;
	private double m_JCBagdasiklik;
	private double m_WPBagdasiklik;
	private double m_LCBagdasiklik;
	private double m_ResnikWordnetBagdasiklik;
	private double m_LinWordnetBagdasiklik;
	private double m_JCWordnetBagdasiklik;
	private double m_WPWordnetBagdasiklik;
	private double m_LCWordnetBagdasiklik;
	private double m_HSOWordnetBagdasiklik;
	private ArrayList<KelimeDugum> m_Kelimeler;
	
	public CumleDugum(int cumleNo, long cumleBas, long cumleBit)
	{
		m_CumleNo = cumleNo;
		m_CumleBas = cumleBas;
		m_CumleBit = cumleBit;
		m_Kelimeler = null;
	}
	
	public int CumleNo()
	{
		return m_CumleNo;
	}

	public long CumleBaslangic()
	{
		return m_CumleBas;
	}

	public long CumleBitis()
	{
		return m_CumleBit;
	}
	
	public void setGGBagdasiklik(double ggRagdasiklik)
	{
		m_GGBagdasiklik = ggRagdasiklik;
	}
	
	public double GGBagdasiklik()
	{
		return m_GGBagdasiklik;
	}
	
	public void setResnikBagdasiklik(double resnikRagdasiklik)
	{
		m_ResnikBagdasiklik = resnikRagdasiklik;
	}
	
	public double ResnikBagdasiklik()
	{
		return m_ResnikBagdasiklik;
	}
	
	public void setLinBagdasiklik(double linRagdasiklik)
	{
		m_LinBagdasiklik = linRagdasiklik;
	}
	
	public double LinBagdasiklik()
	{
		return m_LinBagdasiklik;
	}
	
	public void setJiangConrathBagdasiklik(double jcRagdasiklik)
	{
		m_JCBagdasiklik = jcRagdasiklik;
	}
	
	public double JiangConrathBagdasiklik()
	{
		return m_JCBagdasiklik;
	}
	
	public void setWuPalmerBagdasiklik(double wpRagdasiklik)
	{
		m_WPBagdasiklik = wpRagdasiklik;
	}
	
	public double WuPalmerBagdasiklik()
	{
		return m_WPBagdasiklik;
	}
	
	public void setLeacockChodorowBagdasiklik(double lcRagdasiklik)
	{
		m_LCBagdasiklik = lcRagdasiklik;
	}
	
	public double LeacockChodorowBagdasiklik()
	{
		return m_LCBagdasiklik;
	}

	public void setResnikWordnetBagdasiklik(double resnikWordnetRagdasiklik)
	{
		m_ResnikWordnetBagdasiklik = resnikWordnetRagdasiklik;
	}
	
	public double ResnikWordnetBagdasiklik()
	{
		return m_ResnikWordnetBagdasiklik;
	}
	
	public void setLinWordnetBagdasiklik(double linWordnetRagdasiklik)
	{
		m_LinWordnetBagdasiklik = linWordnetRagdasiklik;
	}
	
	public double LinWordnetBagdasiklik()
	{
		return m_LinWordnetBagdasiklik;
	}
	
	public void setJCWordnetBagdasiklik(double jcWordnetRagdasiklik)
	{
		m_JCWordnetBagdasiklik = jcWordnetRagdasiklik;
	}
	
	public double JCWordnetBagdasiklik()
	{
		return m_JCWordnetBagdasiklik;
	}
	
	public void setWPWordnetBagdasiklik(double wpWordnetRagdasiklik)
	{
		m_WPWordnetBagdasiklik = wpWordnetRagdasiklik;
	}
	
	public double WPWordnetBagdasiklik()
	{
		return m_WPWordnetBagdasiklik;
	}
	
	public void setLCWordnetBagdasiklik(double lcWordnetRagdasiklik)
	{
		m_LCWordnetBagdasiklik = lcWordnetRagdasiklik;
	}
	
	public double LCWordnetBagdasiklik()
	{
		return m_LCWordnetBagdasiklik;
	}
	
	public void setHSOWordnetBagdasiklik(double hsoWordnetRagdasiklik)
	{
		m_HSOWordnetBagdasiklik = hsoWordnetRagdasiklik;
	}
	
	public double HSOWordnetBagdasiklik()
	{
		return m_HSOWordnetBagdasiklik;
	}
	
	public void ekleKelimeDugum(KelimeDugum kelime)
	{
		if (m_Kelimeler == null)
		{
			m_Kelimeler = new ArrayList<KelimeDugum>();
		}
		m_Kelimeler.add(kelime);
	}
	
	public ArrayList<KelimeDugum> Kelimeler()
	{
		return m_Kelimeler;
	}
}