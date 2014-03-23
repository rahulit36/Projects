package com.msr.ftp.bean;



/**
 * Class representing an FTP configuration.
 * 
 * @author Manoj Kumar
 * @version 1.0.0.0
 * @since Apr 2, 2009
 */

public class FTPConfiguration {

	private String	host;
	private int		port;
	private String	userName;
	private String	password;
	private String	initialDirectory	= "/";
	private String	backupDirectory		= "/";
	private int		timeout				= 30;
	private boolean	passiveMode			= true;

	/**
	 * @return Returns the host.
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            The host to set.
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return Returns the initialDirectory.
	 */
	public String getInitialDirectory() {
		return initialDirectory;
	}

	/**
	 * @param initialDirectory
	 *            The initialDirectory to set.
	 */
	public void setInitialDirectory(String initialDirectory) {
		this.initialDirectory = initialDirectory;
	}

	/**
	 * @return Returns the passiveMode.
	 */
	public boolean isPassiveMode() {
		return passiveMode;
	}

	/**
	 * @param passiveMode
	 *            The passiveMode to set.
	 */
	public void setPassiveMode(boolean passiveMode) {
		this.passiveMode = passiveMode;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Returns the port.
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            The port to set.
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return Returns the timeout.
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout
	 *            The timeout to set.
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return Returns the backupDirectory.
	 */
	public String getBackupDirectory() {
		return backupDirectory;
	}

	/**
	 * @param backupDirectory
	 *            The backupDirectory to set.
	 */
	public void setBackupDirectory(String backupDirectory) {
		this.backupDirectory = backupDirectory;
	}
}
