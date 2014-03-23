package com.msr.ftp.bean;

import java.util.Calendar;


/**
 * @author Sumit Singhal
 * @version 1.0.0.0
 * @since Apr 2, 2009
 */
public interface RemoteFile {
	public String getName();

	public boolean isDirectory();

	public boolean isFile();

	public long getSize();
	
	public Calendar getTimeStamp();
}
