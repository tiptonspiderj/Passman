package tiptonspiderj1.com;

import java.io.Serializable;

public class Profile implements Serializable {	
	
	/***************************************************************************
	*                                 Variables                                *
	***************************************************************************/
	
	private static final long serialVersionUID = 1L;
	
	String username;
	String password;
	String pin;
	String webAddress;
	String alias;	
	
	/****************************************************************************
	*                            Setters and Getters                            *
	****************************************************************************/
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getWebAddress() {
		return webAddress;
	}
	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}
	
	/****************************************************************************
	*                             Methods/Functions                             *
	****************************************************************************/
	
	public Profile(String username, String password, String pin, String webAddress, String alias) {
		this.username = username;
		this.password = password;
		this.pin = pin;
		this.alias= alias;
		this.webAddress = webAddress;
	}
	
	@Override
	public String toString() {
		return "Profile [username=" + username + ", password=" + password + ", pin=" + pin + ", webAddress="
				+ webAddress + ", alias=" + alias + "]";
	}	
	
}
