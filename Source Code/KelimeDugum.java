
public class KelimeDugum 
{
	private int m_CumleNo;
	private String m_Kelime;
	private long m_KelimeBas;
	private long m_KelimeBit;
	private String m_KelimeKok;
	private String m_KelimeKategori;
	private String m_EtiketOntolojiURI;
	private String m_EtiketURI;
	private String m_EtiketTip;
	
	public KelimeDugum(int cumleNo, String kelime, long kelimeBaslangic, long kelimeBitis, String kelimeKok, String kelimeKategori)
	{
		m_CumleNo = cumleNo;
		m_Kelime = kelime;
		m_KelimeBas = kelimeBaslangic;
		m_KelimeBit = kelimeBitis;
		m_KelimeKok = kelimeKok;
		m_KelimeKategori = kelimeKategori;
		m_EtiketOntolojiURI = "";
		m_EtiketURI = "";
		m_EtiketTip = "";
	}
	
	public KelimeDugum(int cumleNo, String kelime, long kelimeBaslangic, long kelimeBitis, String kelimeKok, 
			String kelimeKategori, String etiketURI, String etiketTip)
	{
		m_CumleNo = cumleNo;
		m_Kelime = kelime;
		m_KelimeBas = kelimeBaslangic;
		m_KelimeBit = kelimeBitis;
		m_KelimeKok = kelimeKok;
		m_KelimeKategori = kelimeKategori;
		setEtiketBilgisi(etiketURI, etiketTip);
	}
	
	public void setEtiketBilgisi(String etiketURI, String etiketTip)
	{
		int index = etiketURI.lastIndexOf("#");
		m_EtiketOntolojiURI = etiketURI.substring(0, index);
		m_EtiketURI = etiketURI;
		m_EtiketTip = etiketTip;
	}
	
	public int CumleNo()
	{
		return m_CumleNo;
	}
	
	public String Kelime()
	{
		return m_Kelime;
	}
	
	public long KelimeBaslangic()
	{
		return m_KelimeBas;
	}
	
	public long KelimeBitis()
	{
		return m_KelimeBit;
	}
	
	public String KelimeKok()
	{
		return m_KelimeKok;
	}
	
	public String KelimeKategori()
	{
		return m_KelimeKategori;
	}
	
	public String EtiketOntolojiURI()
	{
		return m_EtiketOntolojiURI;
	}
	
	public String EtiketURI()
	{
		return m_EtiketURI;
	}
	
	public String EtiketTip()
	{
		return m_EtiketTip;
	}
}
