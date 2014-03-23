package com.msr.ftp.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
//import org.apache.log4j.Logger;
//import org.apache.poi.ss.usermodel.ClientAnchor;

import com.msr.ftp.bean.FTPConfiguration;
import com.msr.ftp.bean.RemoteFile;

/**
 * FTPClient class contains the code for connecting FTP server and retreive file
 * as input stream
 *
 * @author Pradeep Date Sep 30, 2012 version PA Integration of soccer 1.0
 */
public class FTPClient {
//	private final static Logger							log				= Logger.getLogger(FTPClient.class.getName());
//	private static Logger logger=Logger.getLogger(FTPClient.class);

    protected static Locale defaultLocale = Locale.getDefault();
    protected static ResourceBundle rb = ResourceBundle.getBundle("MessageResources", defaultLocale);
    private final org.apache.commons.net.ftp.FTPClient ftpClient;

    public FTPClient() {
        //System.out.println("FTP client creating");
        ftpClient = new org.apache.commons.net.ftp.FTPClient();
        //System.out.println(ftpClient);
    }

    public void connect(FTPConfiguration ftpc) throws RemoteException {
        try {
            System.out.println("GOING TO CONNECT");
            int timeout = ftpc.getTimeout() * 500000;

            // before connection - not to force user to put password faster than timeout
            ftpClient.setDefaultTimeout(timeout);

            int reply;
            //System.out.println("host " + ftpc.getHost());
            //System.out.println("port " + ftpc.getPort() + "user name "+ ftpc.getUserName() +"   password " + ftpc.getPassword());
            ftpClient.connect(ftpc.getHost());
            // After connection attempt, you should check the reply code to verify
            // success.
            reply = getReplyCode();

            //System.out.println("Reply is " + getReplyString());

            if (!FTPReply.isPositiveCompletion(reply)) {
                //System.out.println("Disconnecting because of negative reply");
                ftpClient.disconnect();
                throw new RemoteException(rb.getString("MSG_FtpRefusedConnection") + " : " + ftpc.getHost());
            }

            // login
            System.out.println("Login as " + ftpc.getUserName());
            if (!ftpClient.login(ftpc.getUserName(), ftpc.getPassword())) {
                //System.out.println("Login unusuccessful -> logout");
                ftpClient.logout();
                throw new RemoteException(rb.getString("MSG_FtpLoginFailed"));
            }
            //System.out.println("Login successful");

            if (ftpc.isPassiveMode()) {
                //System.out.println("Setting passive mode");
                ftpClient.enterLocalPassiveMode();
            }

            // binary mode as a default
            //System.out.println("Setting file type to BINARY");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            System.out.println("Remote system is " + ftpClient.getSystemName());

        } catch (IOException ex) {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    System.out.println("Exception while disconnecting" + e);
                }
            }
            System.out.println("Exception while connecting" + ex);
            throw new RemoteException(rb.getString("MSG_FtpCannotConnect"));
        }
    }

    public void disconnect() throws RemoteException {
        System.out.println("Remote client trying to disconnect");
        if (ftpClient.isConnected()) {
            System.out.println("Remote client connected -> disconnecting");
            try {
                ftpClient.logout();
            } catch (IOException ex) {
                System.out.println("Error while disconnecting" + ex);
                throw new RemoteException(rb.getString("MSG_FtpCannotLogout"));
            } finally {
                try {
                    ftpClient.disconnect();
                    System.out.println("Remote client disconnected");
                } catch (IOException ex) {
                    System.out.println("Remote client disconnected with exception" + ex);
                }
            }
        }
    }

    public int getReplyCode() {
        return ftpClient.getReplyCode();
    }

    public String getReplyString() {
        String reply = ftpClient.getReplyString();
        if (reply == null) {
            return null;
        }
        return reply.trim();
    }

    public String getNegativeReplyString() {
        int replyCode = ftpClient.getReplyCode();
        if (FTPReply.isNegativePermanent(replyCode) || FTPReply.isNegativeTransient(replyCode)) {
            return getReplyString();
        }
        return null;
    }

    public String printWorkingDirectory() throws RemoteException {
        try {
            return ftpClient.printWorkingDirectory();
        } catch (IOException ex) {
            System.out.println("Error while pwd" + ex);
            throw new RemoteException(rb.getString("MSG_FtpCannotPwd"));
        }
    }

    public boolean storeFile(String remote, InputStream local) throws RemoteException {
        try {
            return ftpClient.storeFile(remote, local);
            ////	return ftpClient.completePendingCommand();
        } catch (IOException ex) {
            System.out.println("Error while storing file " + remote + "  " + ex);
            //throw new RemoteException(rb.getString("MSG_FtpCannotStoreFile"));
            throw new RemoteException(ex.toString());
        }
    }

    public boolean deleteFile(String pathname) throws RemoteException {
        try {
            return ftpClient.deleteFile(pathname);
        } catch (IOException ex) {
            System.out.println("Error while deleting file " + pathname + ex);
            throw new RemoteException(rb.getString("MSG_FtpCannotDeleteFile"));
        }
    }

    public boolean rename(String from, String to) throws RemoteException {
        try {
            return ftpClient.rename(from, to);
        } catch (IOException ex) {
            System.out.println(String.format("Error while renaming file %s -> %s", from, to) + ex);
            throw new RemoteException(rb.getString("MSG_FtpCannotRenameFile"));
        }
    }

    public List listFiles() throws RemoteException {
        List<RemoteFile> result = null;
        try {
            FTPFile[] files = ftpClient.listFiles();
            result = new ArrayList<RemoteFile>(files.length);
            for (FTPFile f : files) {
                // #142682
                if (f == null) {
                    // hmm, really weird...
                    System.out.println("NULL returned for listing");
                    continue;
                }
                result.add(new RemoteFileImpl(f));
            }
        } catch (IOException ex) {
            System.out.println("Error while listing files " + ex);
            throw new RemoteException(rb.getString("MSG_FtpCannotListFiles"));
        }
        return result;
    }

    public boolean retrieveFile(String remote, OutputStream local) throws RemoteException {
        try {
            return ftpClient.retrieveFile(remote, local);
        } catch (IOException ex) {
            System.out.println("Error while retrieving file " + remote + ex);
            throw new RemoteException(rb.getString("MSG_FtpCannotStoreFile"));
        }
    }

    public boolean changeWorkingDirectory(String pathname) throws RemoteException {
        try {
            return ftpClient.changeWorkingDirectory(pathname);
        } catch (IOException ex) {
            System.out.println("Error while changing directory " + ex);
            throw new RemoteException(rb.getString("MSG_FtpCannotChangeDirectory"));
        }
    }

    public boolean makeDirectory(String pathname) throws RemoteException {
        try {
            System.out.println("pathname: "+pathname);
            return ftpClient.makeDirectory(pathname+"/");
        } catch (IOException ex) {
            System.out.println("Error while creating directory " + ex);
            throw new RemoteException(rb.getString("MSG_FtpCannotCreateDirectory"));
        }
    }

    public static boolean lastFileModified(FTPFile[] files, String pattern) {

        boolean isExist = false;


        for (FTPFile file : files) {
            if (file.getName().startsWith(pattern) && file.isDirectory()) {
                //exist
                isExist = true;
                System.out.println(isExist);

            } else {
                isExist = false;
                System.out.println(isExist);
            }

        }
        return isExist;

    }

    public InputStream retrieveFileStream(String name) {
        InputStream in = null;
        try {
            System.out.println("retrieveFileStream ftpClient ftpClient============= " + ftpClient + "  name  is " + name);
            in = ftpClient.retrieveFileStream(name);
            System.out.println("Input Strean at 255 line no in FTP CLient: "+in);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return in;
    }

    public boolean retrieveWriteFileStream(String name, String dirRemote) {
        InputStream in = null;
        boolean isWrite = false;
        try {
//			String tempPath = "/tmp/" + name;
            File dirFile = new File("C:\\Windows\\Temp\\tmp");
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            String tempPath = "C:\\Windows\\Temp\\tmp" + name;
            FileOutputStream fout = new FileOutputStream(tempPath);
            System.out.println("retrieveFileStream ftpClient ftpClient============= " + ftpClient + "  name  is " + name);
            ftpClient.retrieveFile(name, fout);  //;      retrieveFileStream(remote)retrieveFileStream(name,fout);
            FileInputStream fin = new FileInputStream(tempPath);
            isWrite = ftpClient.storeFile(dirRemote + name, fin);
            File tmptrfFile = new File(tempPath);
            if (tmptrfFile.exists()) {
                tmptrfFile.delete();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isWrite;
    }

    public FTPFile[] getListFileArray() {
        FTPFile[] files = null;
        try {
            files = ftpClient.listFiles();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return files;
    }

    public boolean completePendingCommand() throws IOException {
        return FTPReply.isPositiveCompletion(getReplyCode());
    }

    private static final class RemoteFileImpl implements RemoteFile {

        private final FTPFile ftpFile;

        public RemoteFileImpl(FTPFile ftpFile) {
            assert ftpFile != null;
            this.ftpFile = ftpFile;
        }

        public String getName() {
            return ftpFile.getName();
        }

        public boolean isDirectory() {
            return ftpFile.isDirectory();
        }

        public boolean isFile() {
            return ftpFile.isFile();
        }

        public long getSize() {
            return ftpFile.getSize();
        }

        public Calendar getTimeStamp() {
            // TODO Auto-generated method stub
            return ftpFile.getTimestamp();
        }
    }
}
