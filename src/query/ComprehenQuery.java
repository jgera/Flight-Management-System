package query;

import assist.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;
//综合查询
public class ComprehenQuery extends JPanel implements ActionListener,ItemListener
{
	   //A bean used to connect to the database and execute SQL operation
	static SqlConnection sqlConnection = new SqlConnection();
	
	   //*********************************************************************
	   //Model for the place combobox
    private static DefaultComboBoxModel modelPlace = new DefaultComboBoxModel();   
       //Model for the airfirm combobox
    private static DefaultComboBoxModel modelAirFirm = new DefaultComboBoxModel();
       //Items for the year combobox
    private static Object[] year = {"2012","2013"};
       //Items for the month combobox
 	private static Object[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
 	   //Items for the day combobox
 	private static Object[] day = {"1","2","3","4","5","6","7","8","9","10","11","12",
 	                               "13","14","15","16","17","18","19","20","21","22",
 	                               "23","24","25","26","27","28","29","30","31"}; 	                        
 	                         	
 	private  static JComboBox jcbStart = new JComboBox(),jcbFirstArrive = new JComboBox(),
 	                          jcbArrive = new JComboBox(),jcbAirFirm = new JComboBox(modelAirFirm),
 	                          jcbYear1 = new JComboBox(year),jcbYear2 = new JComboBox(year),
 	                          jcbMonth2 = new JComboBox(month),jcbMonth1 = new JComboBox(month),
 	                          jcbDay1 = new JComboBox(day),jcbDay2 = new JComboBox(day);
 	   //*********************************************************************
 	                   
 	private JRadioButton jrbSingle = new JRadioButton("单程",true),
 	                     jrbDouble = new JRadioButton("往返",false),
 	                     jrbMutiple = new JRadioButton("联程",false);
 	   
 	   //The program should change the content in the label dynamically,
 	   //so we should put the JLabels as the menber varibles 
 	   //so that we can change their contents in every method of this class
 	private JLabel jlStart,jlFirstArrive,jlArrive,
 	               jlTime1,jlTime2,jlAirFirm,
 	               jlReplaceArrive,jlReplaceTime;
 	                   
       //The program should remove and add components into the framework dynamically
       //according to the query mode you have selected!
       //So we should put the JPanels as the menber varibles 
 	   //so that we can remove and add components in the panels in every method of this class
    private JPanel jpFirstArriveBox,jpTime2Box,jpReplaceArrive,jpArrive1,jpReplaceTime,jpTime2; 
       
    private JButton jbQuery = new JButton("查询");
    
       //Used to the items choosed from each combobox
    private String start,firstArrive,arrive,leaveWeek,leaveWeek2,backWeek,airFirm,
                   leaveYear,leaveMonth,leaveDay,backYear,backMonth,backDay,
                   leaveYear2,leaveMonth2,leaveDay2;
      
       //Setup GUI in the Constructor method
    public ComprehenQuery()
    {   
        jcbAirFirm.addItem("所有");
        jcbAirFirm.setSelectedItem("所有");
    
           //Make the time combobox show the present time when you first the program 	
    	setDisplayPresentTime();
    	
    	   //*********************************************************************
    	
    	JPanel jp1 = new JPanel();
    	jp1.add(jrbSingle);
    	JPanel jp2 = new JPanel();
    	jp2.add(jrbDouble);
    	JPanel jp3 = new JPanel();
    	jp3.add(jrbMutiple);    	
    	
    	JPanel jpRadioButton = new JPanel();
    	jpRadioButton.setLayout(new GridLayout(5,1)); 
    	jpRadioButton.add(new JLabel("       "));   	
    	jpRadioButton.add(jp1);
    	jpRadioButton.add(jp2);
    	jpRadioButton.add(jp3);
    	jpRadioButton.add(new JLabel("       "));
    	
    	ButtonGroup bg = new ButtonGroup();
    	bg.add(jrbSingle);
    	bg.add(jrbDouble);
    	bg.add(jrbMutiple);
    	
    	   //*********************************************************************
    	
    	JPanel jpStart = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	jpStart.add(jlStart = new JLabel("        出发城市:"));
    	
    	JPanel jpFirstArrive = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	jpFirstArrive.add(jlFirstArrive = new JLabel("                "));
    	
    	JPanel jpArrive = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	jpArrive.add(jlArrive = new JLabel("        到达城市:"));
    	
    	JPanel jpTime1Tip = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	jpTime1Tip.add(jlTime1 = new JLabel("        出发日期:"));
    	
    	JPanel jpTime2Tip = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	jpTime2Tip.add(jlTime2 = new JLabel("              "));
    	
    	JPanel jpAirFirm = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	jpAirFirm.add(jlAirFirm = new JLabel("       航空公司:"));
    	
    	JPanel jpLabels = new JPanel();
    	jpLabels.setLayout(new GridLayout(7,1));    	
    	jpLabels.add(jpStart);
    	jpLabels.add(jpFirstArrive);
    	jpLabels.add(jpArrive);
    	jpLabels.add(jpTime1Tip);
    	jpLabels.add(jpTime2Tip);
    	jpLabels.add(jpAirFirm);
    	jpLabels.add(new JLabel("            "));
    	
    	   //*********************************************************************
    	            
        JPanel jpStartBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpStartBox.add(jcbStart);
        
           //***********************
           //The combobox "jcbFirstArrive" should be removed from and added into the 
           //jpFirstArriveBox dynamically,
           //so create a panel contains an empty label that used to replace 
           //the panel that contains the combobox "jcbFirstArrive" when we don't need it
        jpReplaceArrive = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	jpReplaceArrive.add(jlReplaceArrive = new JLabel("            "));
    	
    	jpArrive1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	jpArrive1.add(jcbFirstArrive);
    	
        jpFirstArriveBox = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
    	jpFirstArriveBox.add(jpReplaceArrive);
    	   //***********************    	   
    	  
    	JPanel jpArriveBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	jpArriveBox.add(jcbArrive);
    	
    	JPanel jpTime1Box = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	jpTime1Box.add(jcbYear1);
    	jpTime1Box.add(jcbMonth1);
    	jpTime1Box.add(jcbDay1);
    	
    	   //***********************
    	   //The comboboxes "jcbYear2,jcbMonth2,jcbDay2" should be removed from 
    	   //and added into the jpTime2Box dynamically,
    	   //so create a panel contains an empty label that used to replace
           //the panel that contains the comboboxes "jcbYear2,jcbMonth2,jcbDay2"
    	jpReplaceTime = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	jpReplaceTime.add(jlReplaceTime = new JLabel("            ")); 
    	
    	jpTime2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	jpTime2.add(jcbYear2);
    	jpTime2.add(jcbMonth2);
    	jpTime2.add(jcbDay2);
    	
    	jpTime2Box = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
    	jpTime2Box.add(jpReplaceTime); 
    	  	//***********************    	  	 	
    	    
    	JPanel jpAirFirmBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	jpAirFirmBox.add(jcbAirFirm);
    	
    	JPanel jpButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	jpButton.add(jbQuery);
    	
    	JPanel jpComboBox = new JPanel();
    	jpComboBox.setLayout(new GridLayout(7,1));    
    	jpComboBox.add(jpStartBox);
    	jpComboBox.add(jpFirstArriveBox);
    	jpComboBox.add(jpArriveBox);
    	jpComboBox.add(jpTime1Box);
    	jpComboBox.add(jpTime2Box);
    	jpComboBox.add(jpAirFirmBox);
    	jpComboBox.add(jpButton);
    	
    	    //*********************************************************************
        
        JPanel jpQuery = new JPanel();
        jpQuery.setLayout(new BorderLayout());
        jpQuery.add(jpLabels,BorderLayout.WEST);
        jpQuery.add(jpComboBox,BorderLayout.CENTER);
        
        JPanel jpDown = new JPanel();
        jpDown.setLayout(new BorderLayout());
        jpDown.add(jpRadioButton,BorderLayout.WEST);
        jpDown.add(jpQuery,BorderLayout.CENTER);
        
        JLabel jlTitle = new JLabel();
        /*jlTitle.setHorizontalAlignment(JLabel.CENTER);
        jlTitle.setFont(new Font("Tims",Font.BOLD,23));*/
        this.setLayout(new BorderLayout());
        this.add(jlTitle,BorderLayout.NORTH);
        this.add(jpDown,BorderLayout.CENTER);;
        
           //Add listener to the radio buttons 
           //RadioListener is an inner class which define below
        jrbSingle.addActionListener(new RadioListener());
        jrbDouble.addActionListener(new RadioListener());
        jrbMutiple.addActionListener(new RadioListener());
        
           //Add listener to the time comboboxes
        jcbYear1.addItemListener(this);
        jcbYear2.addItemListener(this);
        jcbMonth1.addItemListener(this);
        jcbMonth2.addItemListener(this);
        
           //Add listener to the query button
        jbQuery.addActionListener(this);    	
    }
    
    public static void updatePlaceComboBox(String newPlace,int insertOrDelete)
    {
    	if (insertOrDelete == 1)
    	{
    		if (modelPlace.getIndexOf(newPlace) == -1)
    		{
	    		modelPlace.addElement(newPlace);
	    		jcbStart.addItem(newPlace);	
	    		jcbFirstArrive.addItem(newPlace);	
	    		jcbArrive.addItem(newPlace);	
    		}
	    	
    	}
    	else if (insertOrDelete == 2)
    	{
    		if (modelPlace.getIndexOf(newPlace) != -1)
    		{
	    		modelPlace.removeElement(newPlace);
	    		jcbStart.removeItem(newPlace);
	    		jcbFirstArrive.removeItem(newPlace);
	    		jcbArrive.removeItem(newPlace);	
    		}    		
    	}   	
    }
    
    public static void updateAirFirmComboBox(String newAirFirm,int insertOrDelete)
    {
    	if (insertOrDelete == 1)
    	{
    		if (modelAirFirm.getIndexOf(newAirFirm) == -1)
    		   jcbAirFirm.addItem(newAirFirm);
    	}
    	   
    	else if (insertOrDelete == 2)
    	{
    		if (modelAirFirm.getIndexOf(newAirFirm) != -1)
	    	   jcbAirFirm.removeItem(newAirFirm);
    	}
    	   
    }
    
       //The method that makes the time combobox show the present time
       //when you first the program 
    public void setDisplayPresentTime()
    {
    	   //Get the instance for the class Calendar which used to get the present time
    	Calendar cal = Calendar.getInstance();
    	
    	   //Because there are two Date classes(java.util.Date--java.sql.Date)
    	   //So we should designate the full name for the java.util.Date class 
    	cal.setTime(new java.util.Date());
    	
    	   //Get the present year,month,day
    	String year = String.valueOf(cal.get(Calendar.YEAR));
    	String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
    	String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    	
    	   //Make the time combobox show the present time
    	jcbYear1.setSelectedItem(year);
    	jcbYear2.setSelectedItem(year);
    	jcbMonth1.setSelectedItem(month);
    	jcbMonth2.setSelectedItem(month);
    	
    	   //We should change the items in the day combobox dynamically
    	   //according to the year and month
    	updateDay(year,month,jcbDay1);
    	updateDay(year,month,jcbDay2);    	
    	
    	jcbDay1.setSelectedItem(day);
    	jcbDay2.setSelectedItem(day);    	
    }
    
       //The method which used to change the items in the day combobox dynamically
       //according to the year and month
    private void updateDay(String year,String month,JComboBox jcb)
    {
    	   //There are 30 days in the months 4,6,9,11
    	if (month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11"))    	   
    	{
    		   //jcb.getItemCount() == 31 means that there are 31 days in the day combobox,
    		   //but exactly it is 30 days,so we should remove the 31st day from the day combobox
    		if (jcb.getItemCount() == 31)
    		   jcb.removeItem("31");	
    		else if(jcb.getItemCount() == 29)    		
    		   jcb.addItem("30");    	
    		else if (jcb.getItemCount() == 28)
    		{
    			jcb.addItem("29");
    			jcb.addItem("30");
    		}    	
    	}
    	   //There are 28 or 29 days in the month 2
    	else if (month.equals("2"))
    	{
    		int years = Integer.parseInt(year);
    		
    		   //The year is leap year
    		if ( (years % 400 == 0) || (years %4 == 0 && years % 100 != 0))
    		{
    			if (jcb.getItemCount() == 31)
    			{
    				jcb.removeItem("30");
    			    jcb.removeItem("31");    		    			
    			}
    			else if (jcb.getItemCount() == 30)
    			{
    				jcb.removeItem("30");
    			}
    			else if (jcb.getItemCount() == 28)
    			{
    				jcb.addItem("29");
    			}
    		}
    		   //The year is not leap year
    		else 
    		{
    			if (jcb.getItemCount() == 29)
    			{
    				jcb.removeItem("29");
    			}
    			else if (jcb.getItemCount() == 30)
    			{
    				jcb.removeItem("29");
    				jcb.removeItem("30");
    			}
    			else if (jcb.getItemCount() == 31)
    			{
    				jcb.removeItem("29");
    				jcb.removeItem("30");
    				jcb.removeItem("31");
    			}
    		}
    	}
    	   //There are 31 days in the left months    	
    	else 
    	{
    		if (jcb.getItemCount() == 28)
    		{
    		    jcb.addItem("29");
    		    jcb.addItem("30");
    		    jcb.addItem("31");	
    		}
    		else if (jcb.getItemCount() == 29)
    		{
    			jcb.addItem("30");
    			jcb.addItem("31");    			
    		}
    		else if (jcb.getItemCount() == 30)
    		{
    			jcb.addItem("31");
    		}    		
    	}
    }
    
       //The monitor method for the time combobox
    public void itemStateChanged(ItemEvent e)
    {
    	   //Change the items in the day combobox dynamically
           //according to the year and month that you choose
    	if (e.getSource() == jcbYear1 || e.getSource() == jcbMonth1)
    	{
    		String year = (String)jcbYear1.getSelectedItem();
    		String month = (String)jcbMonth1.getSelectedItem();    		
    		   
    		updateDay(year,month,jcbDay1);
    	}
    	   //The same reason as the above one
    	if (e.getSource() == jcbYear2 || e.getSource() == jcbMonth2)
    	{
    		String year = (String)jcbYear2.getSelectedItem();
    		String month = (String)jcbMonth2.getSelectedItem();
    		
    		updateDay(year,month,jcbDay2);
    	}
    }
    
       //An inner class for the JRadioButton listener
    class RadioListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		   //Remove and add components into the framework dynamically
               //according to the query mode you have selected! 
               
               //If you choose the one way query mode   		  
    		if (jrbSingle.isSelected())
    		{
    			jlFirstArrive.setText("            ");
    			   //Remove the present components in the jpFirstArriveBox panel
    			jpFirstArriveBox.removeAll(); 
    			   //Add the designate component into the panel in the designate query mode
    			jpFirstArriveBox.add(jpReplaceArrive);
    			   //Use the method repaint() so that the component you just add into the panel
    			   //can be showed immediately
    			jpFirstArriveBox.repaint();
    			
    			jlArrive.setText("    到达城市:");
    			jlTime1.setText("    出发日期:");
    			jlTime2.setText("            ");
    			
    			jpTime2Box.removeAll();
    			jpTime2Box.add(jpReplaceTime); 
    			jpTime2Box.repaint();  			
    		}
    		   //If you choose the out and home query mode
    		else if(jrbDouble.isSelected())
    		{
    			jlFirstArrive.setText("            ");
    			jpFirstArriveBox.removeAll();
    			jpFirstArriveBox.add(jpReplaceArrive);
    			jpFirstArriveBox.repaint();
    			
    			jlArrive.setText("    到达城市:");    			
    			jlTime1.setText("    出发日期:");
    			jlTime2.setText("    返程日期:");
    			
    			jpTime2Box.removeAll();
    			jpTime2Box.add(jpTime2);
    			jpTime2Box.repaint();
    		}
    		   //If you choose the mutiple way query mode
    		else if (jrbMutiple.isSelected())
    		{
    			jlFirstArrive.setText("第一到达城市:");
    			jpFirstArriveBox.removeAll();
    			jpFirstArriveBox.add(jpArrive1);
    			jpFirstArriveBox.repaint();
    			
    			jlArrive.setText("第二到达城市:");
    			jlTime1.setText("第一出发日期:");
    			jlTime2.setText("第二出发日期:");
    			
    			jpTime2Box.removeAll();
    			jpTime2Box.add(jpTime2);
    			jpTime2Box.repaint();
    		}    		
    	}
    }
    
       //The monitor method for the button "jbQuery"
    public void actionPerformed(ActionEvent e)
    {
    	   //According to the query mode you choose,the operation is different
    	   
    	   //If you choose the one way query mode  
        if (jrbSingle.isSelected())
        {
        	   //Get the start place
        	start = (String)jcbStart.getSelectedItem();
        	   //Trim the space at the side of the string
        	start = start.trim();
        	
        	   //Get the destination
        	arrive = (String)jcbArrive.getSelectedItem();
        	arrive = arrive.trim();
        	
        	   //Get the leave time
        	leaveYear = (String)jcbYear1.getSelectedItem();
        	leaveMonth = (String)jcbMonth1.getSelectedItem();
        	leaveDay = (String)jcbDay1.getSelectedItem();
        	
        	   //Judge whether the time you choose is valid or not
        	if (!isTimeValid(leaveYear,leaveMonth,leaveDay))
        	{
        		   //If the time is not valid,show error message to the user
        		JOptionPane.showMessageDialog(null,"已经过了出发时间,请重新设定并查询",
        		                              "错误信息",JOptionPane.ERROR_MESSAGE);
        		return;
        	}
        	
        	   //The method timeToWeek is used to turn the designate day into the weekday
        	   //Like turn "2004,12,25" into 6(Saturday)
        	leaveWeek = timeToWeek(leaveYear,leaveMonth,leaveDay);
        	
        	   //Get which air firm you want to take
        	airFirm = (String)jcbAirFirm.getSelectedItem();
        	airFirm = airFirm.trim();
        	
        	   //Do the query work
        	executeSingleQuery();
        }
           //If you choose the out and home way query mode
        else if(jrbDouble.isSelected())
        {
        	start = (String)jcbStart.getSelectedItem();
        	start = start.trim();        	   
        	
        	arrive = (String)jcbArrive.getSelectedItem();
        	arrive = arrive.trim();
        	
        	   //Get the leave time
        	leaveYear = (String)jcbYear1.getSelectedItem();
        	leaveMonth = (String)jcbMonth1.getSelectedItem();
        	leaveDay = (String)jcbDay1.getSelectedItem();
        	   //Get the back time
        	backYear = (String)jcbYear2.getSelectedItem();
        	backMonth = (String)jcbMonth2.getSelectedItem();
            backDay = (String)jcbDay2.getSelectedItem();
        	
        	   //Judge whether the time you choose is valid or not
        	if (!isTimeValid(leaveYear,leaveMonth,leaveDay))
        	{
        		JOptionPane.showMessageDialog(null,"已经过了出发时间,请重新设定并查询",
        		                              "错误信息",JOptionPane.ERROR_MESSAGE);
        		return;
        	}
        	
        	if (!isTimeValid(leaveYear,leaveMonth,leaveDay,backYear,backMonth,backDay))
        	{
        		JOptionPane.showMessageDialog(null,"返程日期不能比出发日期早,请重新设定并查询",
        		                              "错误信息",JOptionPane.ERROR_MESSAGE);
        		return;
        	}
        	
        	leaveWeek = timeToWeek(leaveYear,leaveMonth,leaveDay);
        	backWeek = timeToWeek(backYear,backMonth,backDay);
        	
        	airFirm = (String)jcbAirFirm.getSelectedItem();
        	airFirm = airFirm.trim();
        	
        	executeDoubleQuery();
        }
           //If you choose the mutiple way query mode
        else if (jrbMutiple.isSelected())
        {
        	start = (String)jcbStart.getSelectedItem();
        	start = start.trim();
        	   //Get the midway destination 
        	firstArrive = (String)jcbFirstArrive.getSelectedItem();
        	firstArrive = firstArrive.trim();
        	   //Get the final destination
        	arrive = (String)jcbArrive.getSelectedItem();
        	arrive = arrive.trim();
        	   
        	   //Get the leave time for the start city
        	leaveYear = (String)jcbYear1.getSelectedItem();
        	leaveMonth = (String)jcbMonth1.getSelectedItem();
        	leaveDay = (String)jcbDay1.getSelectedItem();
        	   //Get the leave time for the midway city
        	leaveYear2 = (String)jcbYear2.getSelectedItem();
        	leaveMonth2 = (String)jcbMonth2.getSelectedItem();
        	leaveDay2 = (String)jcbDay2.getSelectedItem();
        	
        	   //Judge whether the time you choose is valid or not
        	if (!isTimeValid(leaveYear,leaveMonth,leaveDay))
        	{
        		JOptionPane.showMessageDialog(null,"已经过了出发时间,请重新设定并查询",
        		                              "错误信息",JOptionPane.ERROR_MESSAGE);
        		return;
        	}
        	
        	if (!isTimeValid(leaveYear,leaveMonth,leaveDay,leaveYear2,leaveMonth2,leaveDay2))
        	{
        		JOptionPane.showMessageDialog(null,"返程日期不能比出发日期早,请重新设定并查询",
        		                              "错误信息",JOptionPane.ERROR_MESSAGE);
        		return;
        	}
        	
        	leaveWeek = timeToWeek(leaveYear,leaveMonth,leaveDay);
        	leaveWeek2 = timeToWeek(leaveYear2,leaveMonth2,leaveDay2);
        	
        	airFirm = (String)jcbAirFirm.getSelectedItem();
        	airFirm = airFirm.trim();
        	
        	executeMutipleQuery();
        }        	
    }  
    
       //The method used to judge whether the time the you choose is valid or nor
       //If the time you choose is earlier than the present time ,it isn;t valid
    private boolean isTimeValid(String year,String month,String day)
    {
    	int y = Integer.parseInt(year);
    	int m = Integer.parseInt(month);
    	int d = Integer.parseInt(day);
    	
    	   //Get the present time
    	Calendar cal = Calendar.getInstance();
    	 
    	cal.setTime(new java.util.Date());
    	
    	int py = cal.get(Calendar.YEAR);
    	int pm = cal.get(Calendar.MONTH) + 1;
    	int pd = cal.get(Calendar.DAY_OF_MONTH);
    	
    	if (y == py)
    	{
    		if (m < pm)
    		   return false;
    		else if(d < pd)
    		   return false;
    	}
    	
    	return true;
    }  
    
       //The method used to judge whether the time the you choose is valid or nor
       //But it is used to determine whether the leave time for the start city is later
       //than the leave time for the midway city! If so ,it isn't valid
    private boolean isTimeValid(String year1,String month1,String day1,
                                String year2,String month2,String day2)
    {
    	int y1 = Integer.parseInt(year1);
    	int m1 = Integer.parseInt(month1);
    	int d1 = Integer.parseInt(day1);
    	
    	int y2 = Integer.parseInt(year2);
    	int m2 = Integer.parseInt(month2);
    	int d2 = Integer.parseInt(day2);
    	
    	if (y1 < y2)
    	   return true;
    	else if (y1 == y2)
    	{
    		if (m1 < m2)
    		   return true;
    		else if (m1 == m2)
    		{
    			if (d1 < d2)
    			   return true;
    			else if (d1 == d2)
    			   return true;
    			else 
    			   return false;
    		}
    		else 
    		   return false;
    	}
    	else 
    	   return false;
    }    
    
       //The method used to turn the designate day into the weekday
       //Like turn "2004,12,25" into 6(Saturday)
    private String timeToWeek(String year,String month,String day)
    {
       int sum=0;
       int y = Integer.parseInt(year);
       int m = Integer.parseInt(month);
       int d = Integer.parseInt(day);
              
       int[] dayOfMonth = {0,31,28,31,30,31,30,31,31,30,31,30,31};  
      
          //Calculate the first the day of the designate year is "Xing Qi Ji"
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
    
       //The method used to calculate the first the day of the designate year is "Xing Qi Ji"
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
    
       //The query method for the one way query mode
    public void executeSingleQuery()
    {
    	String sqlString = formSQLString(start,arrive);    	   
    	         
        ResultSet rs = sqlConnection.executeQuery(sqlString);
        
        if (rs != null)
	    {
		       //Form result string
	        String result = "                                                    " + 
			                "综合查询"; 
			   //Form the specific result string according the message you give       
	        result += formResult(rs,leaveYear,leaveMonth,leaveDay,leaveWeek,start,arrive);    	
	        
	           //Display result in a dialog
	        showResult(result);
	    }	       
	    else 
	       JOptionPane.showMessageDialog(null,"没有连接上数据库!",
	                                    "错误信息",JOptionPane.ERROR_MESSAGE);           
    }
    
       //The query method for the out and home way query mode
    public void executeDoubleQuery()
    {
    	   //The out and home way has to query the database two times to find the 
    	   //flight information of from start city to destination and from destination to start city
    	String sqlString1 = formSQLString(start,arrive);        
        ResultSet rs1 = sqlConnection.executeQuery(sqlString1);
        
        String sqlString2 = formSQLString(arrive,start);    	         
        ResultSet rs2 = sqlConnection.executeQuery(sqlString2);
        
        if ( (rs1 != null) || (rs2 != null))
	    {
	    	String result = "                                                  " + 
		                    "综合查询 ";
			   //Form the result string for the out and home way query mode              
	        result += formDoubleResult(rs1,rs2);    	
	        
	        showResult(result);
	    }
	    else 
	       JOptionPane.showMessageDialog(null,"没有连接上数据库!",
	                                    "错误信息",JOptionPane.ERROR_MESSAGE);       
    }
    
       //The query method for the multiple way query mode
    public void executeMutipleQuery()
    {
    	   //The out and home way has to query the database two times to find the 
    	   //flight information of from start city to midway destination 
    	   //and from midway destination to final destination
    	String sqlString1 = formSQLString(start,firstArrive);  	         
        ResultSet rs1 = sqlConnection.executeQuery(sqlString1);
        
        String sqlString2 = formSQLString(firstArrive,arrive); 	         
        ResultSet rs2 = sqlConnection.executeQuery(sqlString2);
        
        if ((rs1 != null) || (rs2 != null))
	    {
	    	String result = "                                                               " + 
		                    "综合查询                                                 ";
			   //Form the result string for the multiple way query mode
	        result += formMutipleResult(rs1,rs2);    	
	        
	        showResult(result);
	    }
	    else 
	       JOptionPane.showMessageDialog(null,"没有连接上数据库!",
	                                    "错误信息",JOptionPane.ERROR_MESSAGE);        
    }
    
    public String formSQLString(String begin,String end)
    {
    	String sqlString = "SELECT DISTINCT * FROM " + "flight " +    	
						   "WHERE start=" + "\'" + begin + "\'" + " AND " +
			               "destination=" + "\'" + end + "\'";			           	
    	                
    	if (!airFirm.equals("所有"))
    	   sqlString += " AND " + "airFirm=" + "\'" + airFirm + "\'";
    	   
    	return sqlString;
    }
    
       //Get the result string from the resultset
    public String formResult(ResultSet rs,String year,String month,String day,
                                          String week,String begin,String end)
    {		
		String result = "";
		   //Change the English weekday into the chinese weekday
		String weekDay = dayOfWeek(week);
		
		result += "\n" + "航程:" + year + "年" + month + "月" + day + "日" +	
		          "(星期" + weekDay + ")  " + begin + " -> " + end + "\n"; 
		                  
		result += "航班号       航空公司       起飞地点       抵达地点       起飞时间  抵达时间  " + 
		          "儿童票价   成人票价   折扣   班期 " + "\n";
		     
		   //Used to determine whether there are no records found          
		int originLength = result.length();
		
		String time1,time2;
		String childFare,adultFare,discount1,discount2,seat;
		String flight,airfirm,start,destination;
		
		try
		{	
		    String tempResult = "";
		    String tempWeek;
			while(rs.next())
			{	
				flight = String.valueOf(rs.getString("flight"));
				airfirm = String.valueOf(rs.getString("airfirm"));
				start = String.valueOf(rs.getString("start"));
				destination = String.valueOf(rs.getString("destination"));
				while(flight.length() != 11)
				flight += " ";
				while(airfirm.length() != 15)
				airfirm += " ";
				while(start.length() != 18)
				start += " ";
				while(destination.length() != 11)
				destination += " ";
				tempResult = flight + airfirm + start + destination;
				/*tempResult = rs.getString("flight") + rs.getString("airfirm") + rs.getString("start") + 
				             rs.getString("destination");*/
				             
				   //When you get the time from the resultset,it is like "1200".
				   //So we should change it into the form "12:00".
				time1 = rs.getString("leaveTime");
				time2 = rs.getString("arriveTime");
				   //getTime(String time) is used to change the time form into standard one
				time1 = getTime(time1);
				time2 = getTime(time2);
				
				tempResult += time1 + "     " + time2  + "     ";
				
				   //Make sure that the following items have the exactly bits,
				   //so that they can be display in a neat format	
				childFare = String.valueOf(rs.getFloat("childFare"));
				adultFare = String.valueOf(rs.getFloat("adultFare"));
				discount1 = String.valueOf(rs.getFloat("discount1"));
				discount2 = String.valueOf(rs.getFloat("discount2"));
				seat = String.valueOf(rs.getInt("seat"));
				
				   //Make every item in a neat format
				while(childFare.length() != 11)
				   childFare += " ";
				while(adultFare.length() != 11)
				   adultFare += " ";
				while(discount1.length() != 8)
				   discount1 += " ";						
				   
				tempWeek = rs.getString("week");
		        tempResult += childFare + adultFare + discount1 +
				              tempWeek;
				tempResult += "\n";
				
				   //If the flight schedule contains the day that the user designate,
				   //the record is the just one we find!So put the tempResult to the result!
				   //If not,it is not the result!So can't put the tempResult to the result!
				if (tempWeek.indexOf(week) != -1)
				   result += tempResult;							
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		   //Means there are no records found
		   //So give user message that couldn't find correlate infomation
		if (result.length() == originLength)
		{
			result += "                                                    " +
			          "对不起,找不到你想要的航班信息!" + "\n";
		}	
		
		return result;
    } 
    
       //The method used to change the time form 
    private String getTime(String time)
	{
		String time1,time2;
		time1 = time.substring(0,2);
		time2 = time.substring(2,4);
		
		time1 = time1.concat(":");
		time1 = time1.concat(time2);
		
		return time1;
	}
	 
       //The method used to change the English weekday into the chinese weekday
    private String dayOfWeek(String weekNum)
    {
    	String week = "";
    	int num = Integer.parseInt(weekNum);
    	
		switch(num)
		{
			case 1:
             week = "一";
             break;            
          case 2:
             week = "二";
             break;
          case 3:
             week = "三";
             break;
          case 4:
             week = "四";
             break;
          case 5:
             week = "五";
             break;
          case 6:
             week = "六";
             break;
          case 7:
             week = "日";
             break;
		}
		
		return week;
    }   
    
       //Form the result string for the out and home way query mode 
    public String formDoubleResult(ResultSet rs1,ResultSet rs2)
    {
    	String result1 = formResult(rs1,leaveYear,leaveMonth,leaveDay,leaveWeek,start,arrive);
    	String result2 = formResult(rs2,backYear,backMonth,backDay,backWeek,arrive,start);
    	
    	String result = result1 + result2;
    	return result;
    }
    
       //Form the result string for the mutiple way query mode
    public String formMutipleResult(ResultSet rs1,ResultSet rs2)
    {
    	String result1 = formResult(rs1,leaveYear,leaveMonth,leaveDay,leaveWeek,start,firstArrive);
    	String result2 = formResult(rs2,leaveYear2,leaveMonth2,leaveDay2,leaveWeek2,firstArrive,arrive);
    	
    	String result = result1 + result2;
    	return result;
    }
    
       //Show the result in a dialog
    public void showResult(String result)
    {
    	JOptionPane.showMessageDialog(null,result,"查询结果",JOptionPane.PLAIN_MESSAGE);
    }         
}///:~