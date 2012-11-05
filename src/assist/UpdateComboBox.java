package assist;

import manage.*;
import assist.*;
import query.*;

import java.util.*;
import java.sql.*;


 

public class UpdateComboBox extends Thread
{
	private SqlConnection sqlConnection = new SqlConnection();
	private HashSet flight = new HashSet();
	private HashSet airFirm = new HashSet();
	private HashSet place = new HashSet();
	public final static int INSERT_INFO = 1;
	public final static int DELETE_INFO = 2;
	
	public UpdateComboBox()
	{
	   
	    this.start();
	}
	
	public void run()
	{
		try
		{
			while (true)
			{
				updateInsert();
				updateDelete();
				
				this.sleep(2000);		
			}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}	
	
	public void updateInsert()
	{
		String sqlString = "select flight,airfirm,start from flight";
		String newFlightNum = "";
		String newAirFirm = "";
		String newPlace = "";		
	
		try
		{
			ResultSet rs = sqlConnection.executeQuery(sqlString);
			
			while (rs.next())
			{
				newFlightNum = rs.getString("flight");
				newAirFirm = rs.getString("airfirm");
				newPlace = rs.getString("start");
				
				if ( !flight.contains(newFlightNum))
                {
                	flight.add(newFlightNum);
                	updateFlightComboBox(newFlightNum,this.INSERT_INFO);
                }					
				   
				   
				if ( !airFirm.contains(newAirFirm))
				{
					airFirm.add(newAirFirm);
					updateAirFirmComboBox(newAirFirm,this.INSERT_INFO);
				}
				   
				
				if ( !place.contains(newPlace))	
				{
					place.add(newPlace);
					updatePlaceComboBox(newPlace,this.INSERT_INFO);	
				}					   				
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	public void updateDelete()
	{
		try
		{
		    String sqlString = "select flight,airfirm,start from flight";
		    ResultSet rs = sqlConnection.executeQuery(sqlString);
		    
		    HashSet fli = new HashSet();
	        HashSet firm = new HashSet();
	        HashSet pla = new HashSet();
		    
		    while (rs.next())
		    {	    
		    	fli.add(rs.getString("flight"));
		    	
		    	firm.add(rs.getString("airfirm"));
		    	
		    	pla.add(rs.getString("start")); 
		    }								
		    
		    Iterator flightIt = flight.iterator(); 
		    Iterator airFirmIt = airFirm.iterator();
		    Iterator placeIt = place.iterator();		   
		    
		    String flightNum = "";
		   
		    while (flightIt.hasNext())
		    {
		       flightNum = (String)flightIt.next();	
		       if ( !fli.contains(flightNum))
		       {		           	       	  
		       	   updateFlightComboBox(flightNum,this.DELETE_INFO);	
		       }		          
		    }
		    flight = (HashSet)fli.clone();
		    
		    String air = "";
		    while (airFirmIt.hasNext())
		    {
		       air = (String)airFirmIt.next();	
		       if ( !firm.contains(air))
		       {		       	   
		       	   updateAirFirmComboBox(air,this.DELETE_INFO);	
		       }		          
		    }
		    airFirm = (HashSet)firm.clone();
		    
		    String location = "";
		    while (placeIt.hasNext())
		    {
		       location = (String)placeIt.next();	
		       if ( !pla.contains(location))
		       {		       	  
		       	   updatePlaceComboBox(location,this.DELETE_INFO);
		       }		          	
		    }
		    place = (HashSet)pla.clone();
		    		    	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void updateFlightComboBox(String newFlightNum,int insertOrDelete )
	{
		CommonQuery.updateFlightComboBox(newFlightNum,insertOrDelete);
		
	//	FlightQuery.updateFlightList(newFlightNum,insertOrDelete);	
		
		DeletePanel.updateFlightComboBox(newFlightNum,insertOrDelete);	
	}
	
	private void updateAirFirmComboBox(String newAirFirm,int insertOrDelete)
	{
		CommonQuery.updateAirFirmComboBox(newAirFirm,insertOrDelete);
		
	//	AirFirmQuery.updateAirFirmComboBox(newAirFirm,insertOrDelete);
		ComprehenQuery.updateAirFirmComboBox(newAirFirm,insertOrDelete);		
	}
	
	private void updatePlaceComboBox(String newPlace,int insertOrDelete)
	{
		CommonQuery.updatePlaceComboBox(newPlace,insertOrDelete);
		
	//	DestinQuery.updatePlaceComboBox(newPlace,insertOrDelete);
		ComprehenQuery.updatePlaceComboBox(newPlace,insertOrDelete);
		
		DeletePanel.updateCityComboBox(newPlace,insertOrDelete);
	}
}