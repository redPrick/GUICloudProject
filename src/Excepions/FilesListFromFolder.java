package Excepions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import Excepions.MyPathTooLongException;
 
public class FilesListFromFolder {
     
    public static void main(String[] args){
    	
    	listFilesInFolderRecursively("C:/MyFolder/", 0);
    	//printContentFromFile("C:\\MyFolder\\test.txt");
    	//File f = new File("C:\\MyFolder\\aaaa");
    	//getPath(f);
    }
    public static void listFilesInFolderRecursively(String folder, int level){
    	File file = new File(folder);
        File[] files = file.listFiles();
        for(File f: files){
        	for(int i = 0; i < level; i ++)
        		System.out.print("\t");
        	if(f.canRead()){
        		long millisec = f.lastModified();
        		Date dt = new Date(millisec);
        		System.out.println(f.getName() + "  ----  " +  new Date(f.lastModified()));
        	}
        	if(f.isDirectory())
        		listFilesInFolderRecursively(f.getAbsolutePath(), level + 1);
        }
    }
    public static  <T extends String> void printContentFromFile(T file){
    	FileReader stream;
		BufferedReader br = null;
		try {
			stream = new FileReader(file);
			br = new BufferedReader(stream);
			String sCurrentLine;
			
			while (( sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
    }
    public static void getPath(File folder){
    	String path = "";
    	try{
    		path += folder.getAbsolutePath();
    		if(path.length() > 255)
    			throw new MyPathTooLongException();
    	} catch (MyPathTooLongException e){
    		e.printStackTrace();
    	}
    }
}
