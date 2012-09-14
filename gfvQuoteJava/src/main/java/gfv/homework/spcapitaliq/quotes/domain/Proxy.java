package gfv.homework.spcapitaliq.quotes.domain;


/**
 * Class used to manage proxy access
 * 
 * @author GFV
 *
 */
public class Proxy {
	public boolean active;
	public String host;
	public String port;
	public String user;
	public String password;

	public void setActive(boolean proxy) {
		this.active = proxy;
	}

	public void setHost(String proxyHost) {
		this.host = proxyHost;
	}

	public void setPort(String proxyPort) {
		this.port = proxyPort;
	}

	public void setUser(String proxyUser) {
		this.user = proxyUser;
	}

	public void setPassword(String proxyPassword) {
		this.password = proxyPassword;
	}
	
	public void use(){
		if (!active) {
			deactivate();
			return;
		}
		activate();
				
	}
	private void activate(){
		System.setProperty("http.proxyHost", host);
		System.setProperty("http.proxyPort", port);
		System.setProperty("http.proxyUser", user);
		System.setProperty("http.proxyPassword", password);
	}

	private void deactivate() {
		System.setProperty("http.proxyHost", "");
		System.setProperty("http.proxyPort", "");
		System.setProperty("http.proxyUser", "");
		System.setProperty("http.proxyPassword", "");
		
	}

}