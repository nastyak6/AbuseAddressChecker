package bitcoinabuse;

public interface IBitcoinAbuse {
	public String CheckAddress(String address);
	public String getNumberOfAbuses(String address);
	public String getLink(String address);
}
