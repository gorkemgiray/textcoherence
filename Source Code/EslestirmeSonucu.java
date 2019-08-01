public class EslestirmeSonucu {
	private String m_String1;
	private String m_String2;
	private double m_GG_Ilintililik;
	private double m_Resnik_Benzerlik;
	private double m_Lin_Benzerlik;
	private double m_JC_Benzerlik;
	private double m_LC_Benzerlik;
	private double m_WP_Benzerlik;
	//private double m_ResnikWordnet_Benzerlik;
	
//	public EslestirmeSonucu(String String1, String String2, double GG_Ilintililik, double Resnik_Benzerlik,
//			double Lin_Benzerlik, double JC_Benzerlik, double LC_Benzerlik, double WP_Benzerlik,
//			double ResnikWordnet_Benzerlik)
	public EslestirmeSonucu(String String1, String String2, double GG_Ilintililik, double Resnik_Benzerlik,
			double Lin_Benzerlik, double JC_Benzerlik, double LC_Benzerlik, double WP_Benzerlik)
	{
		m_String1 = String1;
		m_String2 = String2;
		m_GG_Ilintililik = GG_Ilintililik;
		m_Resnik_Benzerlik = Resnik_Benzerlik;
		m_Lin_Benzerlik = Lin_Benzerlik;
		m_JC_Benzerlik = JC_Benzerlik;
		m_LC_Benzerlik = LC_Benzerlik;
		m_WP_Benzerlik = WP_Benzerlik;
		//m_ResnikWordnet_Benzerlik = ResnikWordnet_Benzerlik;
	}
	
	public String getString1()
	{
		return m_String1;
	}

	public String getString2()
	{
		return m_String2;
	}
	
	public double get_GG_Ilintililik()
	{
		return m_GG_Ilintililik;
	}

	public double get_Resnik_Benzerlik()
	{
		return m_Resnik_Benzerlik;
	}
	
	public double get_Lin_Benzerlik()
	{
		return m_Lin_Benzerlik;
	}
	
	public double get_JC_Benzerlik()
	{
		return m_JC_Benzerlik;
	}
	
	public double get_LC_Benzerlik()
	{
		return m_LC_Benzerlik;
	}
	
	public double get_WP_Benzerlik()
	{
		return m_WP_Benzerlik;
	}
	
	/*public double get_ResnikWordnet_Benzerlik()
	{
		return m_Resnik_Benzerlik;
	}*/
}
