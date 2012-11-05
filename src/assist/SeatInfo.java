package assist;

import assist.*;

import javax.swing.*;
import java.io.*;
import java.sql.*;

public class SeatInfo 
{
	private SqlConnection sqlConnection = new SqlConnection();
	private RandomAccessFile raf;
	private final int FLIGHT_PER_DAY = 10;

	public SeatInfo()
	{
            File file=new File(".","data");
        	file.mkdir();
        	File f = new File(file,"SeatInfo.txt");           
		
		try
		{
			raf = new RandomAccessFile(f,"rw");
			
			if (raf.length() == 0)
			{
				raf.setLength(31 * 4 * FLIGHT_PER_DAY);	
				for (int i = 0 ; i < 31 * FLIGHT_PER_DAY;i++)
			       raf.writeInt(0);
			}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		
	}
	
	public boolean isFull(String flightNum,String day)
	{
	    try
	    {
	     	long index = cacuIndex(day);
		    long address = cacuAddr(flightNum);
		    long absoluteAddress = index + address;
	    
		    raf.seek(absoluteAddress);
		    int bookedSeats = raf.readInt();
	       	String sqlString = "select seat from flight where flight='" + flightNum + "'";
			ResultSet rs = sqlConnection.executeQuery(sqlString);
			
			int totalSeats = 0;	
				
			while (rs.next())
				totalSeats = rs.getInt(1);	
			if (totalSeats == bookedSeats)
			   return true;
			else
			   return false;
	    
	    }
	    catch(Exception e)
	    {
	       	return false;
	    }		
	}
	
	public int dingPiao(String flightNum,String day,int seats)
	{
		int leftSeats = 0;
		try
	    {
	    	long index = cacuIndex(day);
		    long address = cacuAddr(flightNum);
		    long absoluteAddress = index + address;
		    
		    raf.seek(absoluteAddress);
		    int bookedSeats = raf.readInt();
	    
	    	String sqlString = "select seat,week from flight where flight='" + flightNum + "' ";
			ResultSet rs = sqlConnection.executeQuery(sqlString);
			
			int totalSeats = 0;	
			String week="";	
			while (rs.next())			   
		   	{
		   		totalSeats = rs.getInt(1);
		   		week=rs.getString(2);
		   	}			   	
			    
			String c=isAbsence(day);
		    int flag=0;
			for(int i=0;i<week.length();i++)
			{
				String w=week.substring(i,i+1);
				if(c.equals(w)) 
				{
					flag=1;
					break; 
				}
			}
			
			if(flag==1)
			{
				leftSeats = totalSeats - bookedSeats;
			
				if (leftSeats >= seats)
				{
					raf.seek(absoluteAddress);
					raf.writeInt(bookedSeats + seats);
					return -1;
				}
				else
				    return leftSeats;
			}
			else
			     return -2;
						   
			
	    }
	    catch(Exception e)
	    {
	       e.printStackTrace();
	    }
	    
	    return leftSeats;
	}
	
	public void tuiPiao(String flightNum,String day,int seats)
	{
         try
         {
         	long index = cacuIndex(day);
		    long address = cacuAddr(flightNum);
		    long absoluteAddress = index + address;
		    
		    raf.seek(absoluteAddress);
		    int bookedSeats = raf.readInt();
		   
		    if (bookedSeats < seats)
		       JOptionPane.showMessageDialog(null,"退票数大于已定票数!",
		                                     "错误信息",JOptionPane.ERROR_MESSAGE);
		    else
		    {
		    	raf.seek(absoluteAddress);
		    	raf.writeInt(bookedSeats - seats);
		    }
         }  
         catch(Exception e)
         {
         }            
	}
	
	public long cacuIndex(String day)
	{
		String d = day.substring(6,8);
		long index = Long.parseLong(d);
		
		return (index - 1) * 4 * FLIGHT_PER_DAY;
	}
	
	public long cacuAddr(String flightNum)
	{
		long remark = 0;
		try
		{
			String sqlString = "select remark from flight where flight='" + flightNum + "'";
			ResultSet rs = sqlConnection.executeQuery(sqlString);
					
			while (rs.next())
			   remark = rs.getInt(1);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return (remark - 1) * 4;
	}
	private String timeToWeek(String year,String month,String day)
    {
       int sum=0;
       int y = Integer.parseInt(year);
       int m = Integer.parseInt(month);
       int d = Integer.parseInt(day);
              
       int[] dayOfMonth = {0,31,28,31,30,31,30,31,31,30,31,30,31};  
      
          //Caculate the first the day of the designate year is "Xing Qi Ji"
       int firstDayOfYear = firstDay(y);
            
       for(int i = 1;i < m;i++)
        {
           sum=sum+dayOfMonth[i];
        }
      
       sum = sum+(d-1)+firstDayOfYear;

          //If month is over February and the designate year is leap year,
          //the total days should be add one 
       if( (m >= 2) && ((y%4 == 0 && y%100 != 0) || (y%400 == 0)))
          sum ++;
          
       int week = 0;  
          //The weekday for the designate day is: 
       int x = sum % 7;       
       switch(x)
         {
          case 1:
             week = 1;
             break;            
          case 2:
             week = 2;
             break;
          case 3:
             week = 3;
             break;
          case 4:
             week = 4;
             break;
          case 5:
             week = 5;
             break;
          case 6:
             week = 6;
             break;
          case 0:
             week = 7;
             break;
         } 
         
       return String.valueOf(week);                  	    
    }
    
       //The method used to caculate the first the day of the designate year is "Xing Qi Ji"
    private int firstDay(int year)
    {
    	int a,b;
    	
	    if(year <= 2000)
	    {
	        a=2000-year;
	        b=6-(a+a/4-a/100+a/400)%7;
	        return b;
	    }
	    else 
	    {
	        a=year-2000;
	        b=(a+1+(a-1)/4-(a-1)/100+(a-1)/400)%7+6;
	        return b%7;
	    }
    }
    
    private String isAbsence(String date)
    {
    	String year=date.substring(0,4);
    	String month=date.substring(4,6);
    	String day=date.substring(6,8);
    	
    	String week=timeToWeek(year,month,day);
    	
    	return week;
    	
    }
}