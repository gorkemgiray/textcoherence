public class Dugum {
	private Kenar m_Kenar;
	private Dugum m_AtaDugum;

	public Dugum(Kenar kenar, Dugum ataDugum)
	{
		m_Kenar = kenar;
		m_AtaDugum = ataDugum;
	}

	public Kenar Kenar()
	{
		return m_Kenar;
	}
	
	public Dugum AtaDugum()
	{
		return m_AtaDugum;
	}
}
