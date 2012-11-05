package booking;

import java.io.*;
public class writeToFile
{
	private RandomAccessFile raf;
	private String[] string=new String[20];
	public writeToFile(String[] string)
	{
	
		this.string=string;
		try
		{
			File file=new File(".","data");
        	file.mkdir();
        	File f = new File(file,"ClientInfo.txt");
			raf=new RandomAccessFile(f,"rw");
			raf.writeUTF(string[16]);
			raf.writeUTF(string[12]);
			raf.writeUTF(string[13]);
			raf.writeUTF(string[4]);
			raf.writeUTF(string[11]);
			raf.writeUTF(string[5]);
			raf.writeUTF(string[3]);
			raf.writeUTF(string[10]);
			raf.writeUTF(string[15]);
			raf.writeUTF(string[14]);
			raf.writeUTF(string[21]);
		}
		catch(IOException ex)
		{
		}
	}

}