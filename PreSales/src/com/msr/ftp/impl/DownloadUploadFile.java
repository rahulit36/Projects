package com.msr.ftp.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.msr.ftp.bean.FTPConfiguration;
import com.msr.util.SingletonClass;
import java.util.List;

/**
 * DownloadUploadFile class contains the code for connecting FTP server and
 * download files
 *
 * @author Pradeep Date Sep 30, 2012 version PA Integration of soccer 1.0
 */
public class DownloadUploadFile {

    String fileName = null;

    public DownloadUploadFile() {
//		ipAddress = "180.151.100.54";
//		userName = "client";
//		passWord = "client123";
//		port="2304";
//		ipAddress = "192.168.10.251";
//		userName = "ftp_user";
//		passWord = "ftp2013";
//		port="21";
    }

    public static void main(String[] args){
        boolean flag = new DownloadUploadFile().makeDir("/home/client/clientDocs", "test_folder");
        System.out.println("flag status is "+flag);
    }
    public boolean makeDir(String path, String DirName){
        boolean success = false;
        FTPConfiguration ftpConf = null;
        FTPClient client = null;
        try{
        ftpConf = new FTPConfiguration();
            ftpConf.setHost(SingletonClass.ipAddress);
            ftpConf.setPort(Integer.parseInt(SingletonClass.port));
            ftpConf.setUserName(SingletonClass.ftpUserName);
            ftpConf.setPassword(SingletonClass.ftpPassWord);
            ftpConf.setTimeout(1000);
            client = new FTPClient();
//			client = new FTPClient();
            System.out.println("going to connect with server");
            client.connect(ftpConf);
            client.changeWorkingDirectory(path);
            System.out.println(client.printWorkingDirectory());
                success = client.makeDirectory(DirName);
               
    }catch(Exception e){
        success = false;
        e.printStackTrace();
    }
        
        return success;
    }
    
    public boolean getFTPFile(String path, String pattern, String dwnldingFileName, String destination) {
        InputStream in = null;
        FTPConfiguration ftpConf = null;
        FTPClient client = null;
        OutputStream out = null;
        boolean isSuccess = false;
        System.out.println("file path is " + path);
        try {
            ftpConf = new FTPConfiguration();
            ftpConf.setHost(SingletonClass.ipAddress);
            ftpConf.setPort(Integer.parseInt(SingletonClass.port));
            ftpConf.setUserName(SingletonClass.ftpUserName);
            ftpConf.setPassword(SingletonClass.ftpPassWord);
            ftpConf.setTimeout(1000);


            client = new FTPClient();
//			client = new FTPClient();
            System.out.println("going to connect with server");
            client.connect(ftpConf);
            client.changeWorkingDirectory(path);
            System.out.println(client.printWorkingDirectory());
//            System.out.println("total number of files are ==>  " + client.listFiles().size());
            System.out.println("path is " + path);
            System.out.println("tx id is " + pattern);
//			if(client.changeWorkingDirectory(path+pattern)){
            if (client.changeWorkingDirectory(path)) {
                System.out.println("path does exist");
                System.out.println("file  exists........" + dwnldingFileName);
                in = client.retrieveFileStream(dwnldingFileName);
                System.out.println(destination);
                setFTPFile(in, dwnldingFileName, destination);
                isSuccess = true;

                System.out.println("file  does not exists........" + dwnldingFileName);


            } else {
                System.out.println("path does not exist");
                isSuccess = false;
            }






            client.disconnect();

        } catch (Exception ae) {
            ae.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }
    
    
    
    
    
    
//    for downloading all type of files i.e doc excel pdf etc
      public boolean getFTPFile(String path, String pattern, String destination) {
        InputStream in = null;
        FTPConfiguration ftpConf = null;
        FTPClient client = null;
        OutputStream out = null;
        boolean isSuccess = false;
        System.out.println("file path is " + path);
         String dwnldingFileName = path.substring(path.lastIndexOf("/")+1);
         String path1 = path.substring(0, path.lastIndexOf("/"));
         path = path1;
        try {
            ftpConf = new FTPConfiguration();
            ftpConf.setHost(SingletonClass.ipAddress);
            ftpConf.setPort(Integer.parseInt(SingletonClass.port));
            ftpConf.setUserName(SingletonClass.ftpUserName);
            ftpConf.setPassword(SingletonClass.ftpPassWord);
            ftpConf.setTimeout(1000);


            client = new FTPClient();
//			client = new FTPClient();
            System.out.println("going to connect with server");
            client.connect(ftpConf);
            client.changeWorkingDirectory(path);
            System.out.println(client.printWorkingDirectory());
//            System.out.println("total number of files are ==>  " + client.listFiles().size());
            System.out.println("path is " + path);
            System.out.println("tx id is " + pattern);
//			if(client.changeWorkingDirectory(path+pattern)){
            if (client.changeWorkingDirectory(path)) {
                System.out.println("file  exists........" + dwnldingFileName);
                in = client.retrieveFileStream(dwnldingFileName);
                System.out.println(destination);
                setFTPFile(in, dwnldingFileName, destination);
                isSuccess = true;
                System.out.println("file  does not exists........" + dwnldingFileName);
            } else {
                System.out.println("path does not exist");
                isSuccess = false;
            }






            client.disconnect();

        } catch (Exception ae) {
            ae.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }
    
    
    

    ////changed by pradeep
    public void setFtpFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFtpFileName() {
        return fileName;
    }

    public void setFTPFile(InputStream in, String dwnFileName, String destination) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(destination + "/" + dwnFileName);
//			out=new FileOutputStream(destination+"\\"+dwnFileName);
            int read = 0;
            byte[] bytes = new byte[1024];
        System.out.println("************InputStream: "+in);
            
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            //System.out.println("Written to a file");
            in.close();
            out.flush();
            out.close();

        } catch (Exception ae) {
            ae.printStackTrace();
        }




    }

    public boolean setUploadFTPFileNew(String outputPath, String filePath, String fileName) {

        InputStream input = null;
        FTPConfiguration ftpConf = null;
        FTPClient client = null;
        OutputStream out = null;
        boolean isUploaded = false;
        try {

            ftpConf = new FTPConfiguration();
            ftpConf.setHost(SingletonClass.ipAddress);
            ftpConf.setPort(Integer.parseInt(SingletonClass.port));
            ftpConf.setUserName(SingletonClass.ftpUserName);
            ftpConf.setPassword(SingletonClass.ftpPassWord);
            ftpConf.setTimeout(20);

            client = new FTPClient();
            client.connect(ftpConf);
            System.out.println("pwd : " + client.printWorkingDirectory());

            System.out.println("pwd after changing path:  " + client.makeDirectory(outputPath));
            client.changeWorkingDirectory(outputPath);
            System.out.println("new PWD: " + client.printWorkingDirectory());
            
            File filee = new File(filePath);
            input = new FileInputStream(filee);
            isUploaded = client.storeFile(fileName, input);
            System.out.println("isupload " + isUploaded);
            client.disconnect();
            input.close();
        } catch (Exception ae) {
            ae.printStackTrace();
            isUploaded = false;
        }
        System.out.println(isUploaded);
        return isUploaded;
    }
    
    public boolean setUploadFTPFile(String outputPath, String pattern, String filePath, String fileName) {

        InputStream input = null;
        FTPConfiguration ftpConf = null;
        FTPClient client = null;
        OutputStream out = null;
        boolean isUploaded = false;
        try {

            ftpConf = new FTPConfiguration();
            ftpConf.setHost(SingletonClass.ipAddress);
            ftpConf.setPort(Integer.parseInt(SingletonClass.port));
            ftpConf.setUserName(SingletonClass.ftpUserName);
            ftpConf.setPassword(SingletonClass.ftpPassWord);
            ftpConf.setTimeout(20);

            client = new FTPClient();
            client.connect(ftpConf);
            System.out.println("pwd : " + client.printWorkingDirectory());

            System.out.println("pwd after changing path:  " + client.makeDirectory(outputPath));
            client.changeWorkingDirectory(outputPath);
            System.out.println("new PWD: " + client.printWorkingDirectory());
            boolean isCreate = client.makeDirectory(pattern);
            System.out.println("file  ........" + isCreate);
            outputPath = outputPath + pattern;
            client.changeWorkingDirectory(outputPath);
            System.out.println("new 2 PWD: " + client.printWorkingDirectory());

            File filee = new File(filePath);
            input = new FileInputStream(filee);
            isUploaded = client.storeFile(fileName, input);
            System.out.println("isupload " + isUploaded);
            client.disconnect();
            input.close();
        } catch (Exception ae) {
            ae.printStackTrace();
            isUploaded = false;
        }
        System.out.println(isUploaded);
        return isUploaded;
    }

    public boolean setUploadFTPFile(String outputPath, String pattern, List<String> filePathList) {

        InputStream input = null;
        FTPConfiguration ftpConf = null;
        FTPClient client = null;
        OutputStream out = null;
        boolean isUploaded = false;
        try {

            ftpConf = new FTPConfiguration();
            ftpConf.setHost(SingletonClass.ipAddress);
            ftpConf.setPort(Integer.parseInt(SingletonClass.port));
            ftpConf.setUserName(SingletonClass.ftpUserName);
            ftpConf.setPassword(SingletonClass.ftpPassWord);
            ftpConf.setTimeout(20);

            client = new FTPClient();
            client.connect(ftpConf);


            
            System.out.println("pwd : " + client.printWorkingDirectory());

            System.out.println("pwd after changing path:  " + client.makeDirectory(outputPath));
            client.changeWorkingDirectory(outputPath);
            System.out.println("new PWD: " + client.printWorkingDirectory());
            boolean isCreate = client.makeDirectory(pattern);
            System.out.println("file  ........" + isCreate);
            outputPath = outputPath + pattern;
            client.changeWorkingDirectory(outputPath);
                       System.out.println("new 2 PWD: " + client.printWorkingDirectory());
            for (int i = 0; i < filePathList.size(); i++) {
                File filee = new File(filePathList.get(i));
                input = new FileInputStream(filee);
                isUploaded = client.storeFile(filee.getName(), input);
                System.out.println("isupload " + isUploaded);

            }

            client.disconnect();
            input.close();
        } catch (Exception ae) {
            ae.printStackTrace();
            isUploaded = false;
        }
        System.out.println(isUploaded);
        return isUploaded;
    }
    
    
      public boolean setUploadFTPFile(String outputPath, String pattern, String pattern1, List<String> filePathList) {

        InputStream input = null;
        FTPConfiguration ftpConf = null;
        FTPClient client = null;
        OutputStream out = null;
        boolean isUploaded = false;
        try {

            ftpConf = new FTPConfiguration();
            ftpConf.setHost(SingletonClass.ipAddress);
            ftpConf.setPort(Integer.parseInt(SingletonClass.port));
            ftpConf.setUserName(SingletonClass.ftpUserName);
            ftpConf.setPassword(SingletonClass.ftpPassWord);
            ftpConf.setTimeout(20);

            client = new FTPClient();
            client.connect(ftpConf);


            
            System.out.println("pwd : " + client.printWorkingDirectory());

            System.out.println("pwd after changing path:  " + client.makeDirectory(outputPath));
            client.changeWorkingDirectory(outputPath);
            System.out.println("new PWD: " + client.printWorkingDirectory());
            boolean isCreate = client.makeDirectory(pattern);
            System.out.println("file  ........" + isCreate);
            outputPath = outputPath + pattern;
            client.changeWorkingDirectory(outputPath);
                       System.out.println("new 2 PWD: " + client.printWorkingDirectory());
            boolean isCreateanother = client.makeDirectory(pattern1);
            System.out.println("file  ........" + isCreateanother);
            outputPath = outputPath +"/"+ pattern1;
            client.changeWorkingDirectory(outputPath);
                       System.out.println("new 2 PWD: " + client.printWorkingDirectory());
            for (int i = 0; i < filePathList.size(); i++) {
                File filee = new File(filePathList.get(i));
                input = new FileInputStream(filee);
                isUploaded = client.storeFile(filee.getName(), input);
                System.out.println("isupload " + isUploaded);

            }

            client.disconnect();
            input.close();
        } catch (Exception ae) {
            ae.printStackTrace();
            isUploaded = false;
        }
        System.out.println(isUploaded);
        return isUploaded;
    }
}
