package bitcoinabuse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import logger.LoggerToFile;

public class BitcoinAbuseConnection implements IBitcoinAbuse {

	private final String BASE_URL = "https://www.bitcoinabuse.com/api/reports/check?address={ADDRESS}&api_token="
			+ Constants.API_KEY;
	private final String BASE_WALLET_URL = "https://www.bitcoinabuse.com/reports/";

	@Override
	public String CheckAddress(String address) {
		String getRequestUrl = BASE_URL.replace("{ADDRESS}", address);
		try {
			String response = sendGET(getRequestUrl);
			return response;
		} catch (IOException e) {
			LoggerToFile.getLogger().log("[CheckAddress] - Could not check the address. Error msg: " + e.getMessage());
			return null;
		}
	}
	
	public String getNumberOfAbuses(String address) {
		String result = CheckAddress(address);
		String arr[] = result.split(",");
		String res = arr[1].substring(8);
		return res;
	}

	public String getLink(String address) {
		return BASE_WALLET_URL + address;
	}
	
	private String sendGET(String getUrl) throws IOException {
		URL url = new URL(getUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		if (con.getResponseCode() == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			con.disconnect();
			return content.toString();
		} else
			return null;
	}

}
