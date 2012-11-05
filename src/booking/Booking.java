package booking;

import assist.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;

public class Booking extends JFrame implements ActionListener
{
	private String[] string=new String[22];
	private JComboBox boxyear=new JComboBox();
	private JComboBox boxyear1=new JComboBox();
	private JComboBox boxyear2=new JComboBox();
	private JComboBox boxmonth=new JComboBox();
	private JComboBox boxmonth1=new JComboBox();
	private JComboBox boxmonth2=new JComboBox();
	private JComboBox boxday1=new JComboBox();
	private JComboBox boxday2=new JComboBox();
	private JComboBox boxday=new JComboBox();
	
	//private JComboBox boxadult=new JComboBox();
	private String[] year={" 2012"," 2013"};
	private String[] adult={" 成人 "," 儿童 "," 学生 "};
	private String[] month={"01","02","03","04","05","06","07","08","09","10","11","12"	};
	private String[] day={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private JTextField jbtflight=new JTextField(10);
	private JTextField jbtflight1=new JTextField(10);
	private JTextField jbtflight2=new JTextField(10);
	private JRadioButton jbrsingle,jbrdouble,jbrmultiple,jbradult,jbrchild;
	private ButtonGroup jbr=new ButtonGroup();
	private ButtonGroup jbr1=new ButtonGroup();
	private JButton jbtbutton=new JButton("机 票 预 定");
	private JButton jbtreturn=new JButton("返回主页面");
	Color color=new Color(230,230,255);
	static JFrame clientFrame=new JFrame();
	static Booking frame;
	private Client client=new Client();
	public static boolean open=false;
	//equals to p0
	private JPanel s1,s2;
	private JLabel jblsecondflight=new JLabel("    第二航班号");
	private JLabel jblreturnflight=new JLabel("       返回航班");
	private SeatInfo seatinformation=new SeatInfo();
	int flag=0;
	int pflag=0;
	public Booking()
	{
		frame = this;
		
		string[5]="单程";
	//	string[12]="single";
	   	for(int i=0;i<year.length;i++)
			boxyear.addItem(year[i]);	
		for(int i=0;i<year.length;i++)
			boxyear1.addItem(year[i]);
		for(int i=0;i<year.length;i++)
			boxyear2.addItem(year[i]);
		for(int i=0;i<month.length;i++)
			boxmonth.addItem(month[i]);
		for(int i=0;i<month.length;i++)
			boxmonth1.addItem(month[i]);
		for(int i=0;i<month.length;i++)
			boxmonth2.addItem(month[i]);
		for(int i=0;i<day.length;i++)
			boxday.addItem(day[i]);
		for(int i=0;i<day.length;i++)
			boxday1.addItem(day[i]);
		for(int i=0;i<day.length;i++)
			boxday2.addItem(day[i]);
	//	for(int i=0;i<adult.length;i++)
//			boxadult.addItem(adult[i]);
		//Panel for the return time
		JPanel p0=new JPanel();	
		p0.setVisible(false);
		display(p0);		
		//p0.setBackground(color);
		p0.setLayout(new FlowLayout(FlowLayout.LEFT));
		p0.add(new JLabel("        返回日期"));
		p0.add(boxyear1);
		//boxyear1.setVisible(false);
		p0.add(new JLabel("年"));
	//	jbtmonth1.setHorizontalAlignment(JTextField.RIGHT);
		p0.add(boxmonth1);
	//	jbtday1.setHorizontalAlignment(JTextField.RIGHT);
		p0.add(new JLabel("月"));
		p0.add(boxday1);
		p0.add(new JLabel("日"));
		
		//Panel for multiple
		JPanel p1=new JPanel();	
		p1.setVisible(false);
		display1(p1);
		//p1.setBackground(color);
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.add(new JLabel("第二出发日期:"));
		p1.add(boxyear2);
		p1.add(new JLabel("年"));
	//	jbtmonth2.setHorizontalAlignment(JTextField.RIGHT);
		p1.add(boxmonth2);
	//	jbtday2.setHorizontalAlignment(JTextField.RIGHT);
		p1.add(new JLabel("月"));
		p1.add(boxday2);
		p1.add(new JLabel("日"));
		//Panel starttime
		
		
		JPanel p3=new JPanel();
		//p3.setBackground(color);
		p3.setLayout(new FlowLayout(FlowLayout.LEFT));
		//JLabel starttime
		p3.add(new JLabel("第一出发日期:"));
		p3.add(boxyear);
		p3.add(new JLabel("年"));
	//	jbtmonth.setHorizontalAlignment(JTextField.RIGHT);
	//	jbtmonth.setText("12");
		p3.add(boxmonth);
	//	jbtday.setHorizontalAlignment(JTextField.RIGHT);	
		p3.add(new JLabel("月"));
		p3.add(boxday);
		p3.add(new JLabel("日"));
		//p3.updateUI();
		//Panel style
		JPanel p5=new JPanel();
		//p5.setBackground(color);
		p5.setLayout(new FlowLayout(FlowLayout.LEFT));
		//style
		p5.add(new JLabel("机票类型:                 "));
		p5.add(jbrsingle=new JRadioButton("单程",true));
		p5.add(jbrdouble=new JRadioButton("往返",false));
		p5.add(jbrmultiple=new JRadioButton("联票   ",false));
	//	p5.add(boxadult);
		//jbrsingle.setBackground(color);
		//jbrdouble.setBackground(color);
		//jbrmultiple.setBackground(color);
		jbr.add(jbrsingle);
		jbr.add(jbrdouble);
		jbr.add(jbrmultiple);
	
		JPanel p6=new JPanel();
		p6.setLayout(new FlowLayout(FlowLayout.LEFT));
		//p6.setBackground(color);
		p6.add(jbtbutton);
		p6.add(jbtreturn);
		JPanel p8=new JPanel();
		//p8.setBackground(color);
		p8.setLayout(new FlowLayout(FlowLayout.LEFT));
		//the firstflight
		p8.add(new JLabel("第一航班号:   "));
		p8.add(jbtflight);
		p8.add(jblreturnflight);
		p8.add(jbtflight1);
		p8.add(jblsecondflight);
		p8.add(jbtflight2);
		jblsecondflight.setVisible(false);
		jbtflight1.setVisible(false);
		jbtflight2.setVisible(false);
		jblreturnflight.setVisible(false);
		JPanel p7=new JPanel();
		//p7.setBackground(color);
		p7.setBorder(new MatteBorder(new ImageIcon("./images/f.gif")));
		p7.setLayout(null);
		p7.add(p0);
		p7.add(p1);
		p7.add(p3);
		p7.add(p5);
		p7.add(p6);
		p7.add(p8);
		p3.reshape(50,30,400,30);
		p0.reshape(50,70,400,30);
		p1.reshape(50,70,400,30);
		p8.reshape(50,110,450,30);
		p5.reshape(50,150,400,40);
		p6.reshape(120,200,400,30);
		getContentPane().add(p7,BorderLayout.CENTER);
		//getContentPane().setBackground(new Color(255,255,230));	
		jbtbutton.addActionListener(this);	
		jbtreturn.addActionListener(this);
		jbrsingle.addActionListener(this);
		jbrdouble.addActionListener(this);
		jbrmultiple.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter()
	                          {
	                          	public void windowClosing(WindowEvent e)
	                          	{
	                          		Booking.this.setVisible(false);
	                          		Booking.this.dispose();
	                          	}
	                          }
	                      );	
	}
	//set panel//s1 for return
	public void display(JPanel p)
	{
		s1=p;
		
	}
	//set label//s2 for multiple
	public void display1(JPanel p)
	{
		s2=p;
	}
	
	String otherchoice=new String(" ");
	String dotherchoice=new String(" ");
	String motherchoice=new String(" ");
	boolean isFull=false;
	public void actionPerformed(ActionEvent e)
	{
		String year1=new String(boxyear.getSelectedItem().toString().trim());
		String month1=new String(boxmonth.getSelectedItem().toString().trim());
		String day1=new String(boxday.getSelectedItem().toString().trim());
		String year2=new String(boxyear1.getSelectedItem().toString().trim());
		String month2=new String(boxmonth1.getSelectedItem().toString().trim());
		String day2=new String(boxday1.getSelectedItem().toString().trim());
		string[2]=year1+"年"+month1+"月"+day1+"日";
		string[3]=year1+month1+day1;
		string[4]=jbtflight.getText().trim();
		string[9]=year2+"年"+month2+"月"+day2+"日";
		string[10]=year2+month2+day2;
		string[11]=jbtflight1.getText().trim();
		string[16]=jbtflight2.getText().trim();
		String year3=new String(boxyear2.getSelectedItem().toString().trim());
		String month3=new String(boxmonth2.getSelectedItem().toString().trim());
		String day3=new String(boxday2.getSelectedItem().toString().trim());
		string[17]=year3+month3+day3;
		string[18]=year3+"年"+month3+"月"+day3+"日";
		
		//book the ticket	
		if(e.getSource()==jbtbutton)
		{
			otherchoice=" ";
			dotherchoice=" ";
				isFull=seatinformation.isFull(string[4],string[3]);
			int sflight=singleflight();
			int dflight=0;
			int mflight=0;
		
			String year=string[3].substring(0,4);
			String month=string[3].substring(4,6);
			String day=string[3].substring(6,8);
			boolean before=isTimeValid(year,month,day);
			
			if(before)
			{if(jbrsingle.isSelected())
			{
				if(sflight==1)
				{
					JOptionPane.showMessageDialog(this,"航班号不能为空!",
					                              "错误信息",JOptionPane.ERROR_MESSAGE);
					jbtflight.setText("");
				}
				else if(sflight==2)
				{
					JOptionPane.showMessageDialog(this,"你要预定的航班已经满座!\n您可以改定同一天的航班:\n"+otherchoice,
					                              "客户信息",JOptionPane.INFORMATION_MESSAGE);
					jbtflight.setText("");
				}
				else if(sflight==3)
				{
					JOptionPane.showMessageDialog(this,"您要预定的航班已经满座了!\n而且当天其他航班也已无票售!\n建议您重新选择航班日期",
					                              "错误信息",JOptionPane.ERROR_MESSAGE);
					jbtflight.setText("");
							
				}
				else if(sflight==4)
				{
					JOptionPane.showMessageDialog(this,"没有此次航班，请您重新查阅！",
					                              "错误信息",JOptionPane.ERROR_MESSAGE);
					jbtflight.setText("");
				}
				else
				{
					clientFrame.getContentPane().add(client.panel(string));
					clientFrame.setTitle("客户资料");
					clientFrame.setSize(560,540);					
					clientFrame.setVisible(true);
					this.setVisible(false);
					
					client.jbtname.setText("");
					client.jbtid.setText("");
					client.jbtqq.setText("");
					client.jbtps.setText("");
					client.jbttelephone.setText("");
					client.jbtadultticketnumber.setText("1");
					client.jbtchildticketnumber.setText("1");
				}
			}
			else if(jbrdouble.isSelected())
			{
				isFull=seatinformation.isFull(string[11],string[10]);
				dflight=doubleflight();
				if(sflight==1)
				{
					JOptionPane.showMessageDialog(this,"第一航班号不能为空!","错误信息",JOptionPane.ERROR_MESSAGE);
				}
				else if(sflight==2)
				{
					JOptionPane.showMessageDialog(this,"你要预定的第一航班号已经满座!\n您可以改定同一天的航班:\n"+otherchoice,"客户信息",JOptionPane.INFORMATION_MESSAGE);
					jbtflight.setText("");
				}
				else if(sflight==3)
				{
					JOptionPane.showMessageDialog(this,"您要预定的第一航班已经满座了!\n而且当天其他航班也已无票售!\n建议您重新选择第一航班日期","错误信息",JOptionPane.ERROR_MESSAGE);
					jbtflight.setText("");
							
				}
				else if(sflight==4)
				{
					JOptionPane.showMessageDialog(this,"没有第一航班号，请您重新查阅！","错误信息",JOptionPane.ERROR_MESSAGE);
					jbtflight.setText("");
				}
				else
				{
					if(dflight==1)
					{
						JOptionPane.showMessageDialog(this,"返回航班号不能为空!","错误信息",JOptionPane.ERROR_MESSAGE);
						
					}
					else if(dflight==2)
					{
						JOptionPane.showMessageDialog(this,"你要预定的返回航班号已经满座!\n您可以改定同一天的航班:\n"+otherchoice,"客户信息",JOptionPane.INFORMATION_MESSAGE);
						jbtflight1.setText("");
					}
					else if(dflight==3)
					{
						JOptionPane.showMessageDialog(this,"您要预定的返回航班号已经满座了!\n而且当天其他航班也已无票售!\n建议您重新选择返回日期","错误信息",JOptionPane.ERROR_MESSAGE);
						jbtflight1.setText("");
					}
					else if(dflight==4)
					{
						JOptionPane.showMessageDialog(this,"不存在您要预定的返回班号，请您重新查阅！","错误信息",JOptionPane.ERROR_MESSAGE);
						jbtflight1.setText("");
					}
					else if(dflight==5)
					{
						JOptionPane.showMessageDialog(this,"返回航班号与第一航班号不匹配\n请重新输入返回航班号！","错误信息",JOptionPane.ERROR_MESSAGE);
						jbtflight1.setText("");
					}
					else
					{
						clientFrame.getContentPane().add(client.panel(string));
						clientFrame.setSize(560,540);
						clientFrame.setTitle("客户资料");
						clientFrame.setVisible(true);
						this.setVisible(false);
					}
				}
			}
			else if(jbrmultiple.isSelected())
			{
				isFull=seatinformation.isFull(string[11],string[10]);
				mflight=multipleflight();
				if(sflight==1)
				{
					JOptionPane.showMessageDialog(this,"第一航班号不能为空!","错误信息",JOptionPane.ERROR_MESSAGE);
				}
				else if(sflight==2)
				{
					JOptionPane.showMessageDialog(this,"你要预定的第一航班号已经满座!\n您可以改定同一天的航班:\n"+otherchoice,"客户信息",JOptionPane.INFORMATION_MESSAGE);
					jbtflight.setText("");
				}
				else if(sflight==3)
				{
					JOptionPane.showMessageDialog(this,"您要预定的第一航班已经满座了!\n而且当天其他航班也已无票售!\n建议您重新选择第一航班日期","错误信息",JOptionPane.ERROR_MESSAGE);
					jbtflight.setText("");
							
				}
				else if(sflight==4)
				{
					JOptionPane.showMessageDialog(this,"没有第一航班号，请您重新查阅！",
					                              "错误信息",JOptionPane.ERROR_MESSAGE);
					jbtflight.setText("");
				}
				else
				{
					if(mflight==1)
					{
						JOptionPane.showMessageDialog(this,"第二航班号不能为空!",
						                              "错误信息",JOptionPane.ERROR_MESSAGE);
						
					}
					else if(mflight==2)
					{
						JOptionPane.showMessageDialog(this,"你要预定的第二航班号已经满座!\n您可以改定同一天的航班:\n"+otherchoice,
						                              "客户信息",JOptionPane.INFORMATION_MESSAGE);
						jbtflight1.setText("");
					}
					else if(mflight==3)
					{
						JOptionPane.showMessageDialog(this,"您要预定的第二航班号已经满座了!\n而且当天其他航班也已无票售!\n建议您重新选择第二航班日期",
						                              "错误信息",JOptionPane.ERROR_MESSAGE);
						jbtflight1.setText("");
					}
					else if(mflight==4)
					{
						JOptionPane.showMessageDialog(this,"不存在您要预定的第二航班号，请您重新查阅！",
						                              "错误信息",JOptionPane.ERROR_MESSAGE);
						jbtflight1.setText("");
					}
					else if(mflight==5)
					{
						JOptionPane.showMessageDialog(this,"第二航班号与第一航班号不匹配\n请重新输入返回航班号！",
						                              "错误信息",JOptionPane.ERROR_MESSAGE);
						jbtflight1.setText("");
					}
					else
					{
						clientFrame.getContentPane().add(client.panel(string));
						clientFrame.setTitle("客户资料");
						clientFrame.setSize(560,540);						
						clientFrame.setVisible(true);
						this.setVisible(false);
					}
				}
			}}
			else
			{
				JOptionPane.showMessageDialog(null,"对不起，不能预定以前的票了",
				                              "客户信息",JOptionPane.ERROR_MESSAGE);
			}
		}	
		if(e.getSource()==jbtreturn)
		{
			this.setVisible(false);
			this.dispose();
		}
		if(e.getSource()==jbrsingle)
		{
			string[11]=jbtflight1.getText().trim();
		//	string[12]="single";
			string[5]="单程";
			s1.setVisible(false);
			s2.setVisible(false);
			jblsecondflight.setVisible(false);
			jbtflight1.setVisible(false);
			jblreturnflight.setVisible(false);
			jbtflight2.setVisible(false);
		}
		if(e.getSource()==jbrdouble)
		{
		//	string[12]="double";
			string[5]="往返";
			s2.setVisible(false);
			s1.setVisible(true);
			jblreturnflight.setVisible(true);
			jbtflight1.setVisible(true);
			jblsecondflight.setVisible(false);
			jbtflight2.setVisible(false);
		}
		if(e.getSource()==jbrmultiple)
		{
		//	string[12]="multiple";
			string[5]="联程";
			s1.setVisible(false);
			jblreturnflight.setVisible(false);
			jbtflight1.setVisible(false);
			s2.setVisible(true);
			jblsecondflight.setVisible(true);
			jbtflight2.setVisible(true);
		}
	}
	String startplace=new String();
	String endplace=new String();
	public int singleflight()
	{
		if(jbtflight.getText().trim().length()==0)	return 1;
		else
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from flight");
				while(rs.next())				
					if(rs.getString(1).trim().equals(string[4]))
					flag=1;
				if(flag==1)
					{	
						
						String airfirm1=new String();
						String sql="select start,destination,airFirm from flight where flight='"+string[4]+"'";
						ResultSet rs1=stmt.executeQuery(sql);
						while(rs1.next())
						{
							startplace = rs1.getString(1).trim();
							endplace=rs1.getString(2).trim();
							airfirm1=rs1.getString(3).trim();
						}
					
						if(!isFull)
						{
							string[0]=startplace;
							string[1]=endplace;
							string[6]=airfirm1;
					//		string[7]=boxadult.getSelectedItem().toString().trim();
							string[8]=string[5]+"票";
						}
						else
						{
							String[] flightcode=new String[50];
							int i=0;
							for(i=0;i<50;i++)
							flightcode[i]=new String("null");
							i=0;
							
								sql="select flight from flight where start='"+startplace+"' AND destination='"+endplace+"'";
							ResultSet rs2=stmt.executeQuery(sql);
							while(rs2.next())
							{
								 flightcode[i]=rs2.getString(1);
								 i++;
							}
							stmt.close();
							con.close();
							i=0;
						
							while(!(flightcode[i].equals("null")))
							{
								if(!(flightcode[i].equals(string[4])));
								{
									isFull=seatinformation.isFull(flightcode[i],string[3]);
									if(!isFull)
									{
										otherchoice=otherchoice+flightcode[i];
									}
								}
								i++;
							}
							
							if(!(otherchoice.equals(" ")))	return 2;
							
							else	return 3;
						
						}
					}
					if(flag==0)	return 4;
				
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
					
			}
			return 0;
	}
	
	//********************************************************************************************
	
	public int doubleflight()
	{
		if(jbtflight1.getText().trim().length()==0)	return 1;
		else
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","");
				Statement stmt = con.createStatement();
				//ResultSet rs = stmt.executeQuery("select * from flight");
				ResultSet drs = stmt.executeQuery("select * from flight");
				//flag=1 exists the flight 
				while(drs.next())
				{
					if(drs.getString(1).trim().equals(string[11]))
					{
						flag=1;
					}
				}
				if(flag==1)
				{	
					String dstartplace=new String();
					String dendplace=new String();
					String sql="select start,destination from flight where flight='"+string[11]+"'";
					ResultSet drs1=stmt.executeQuery(sql);
					while(drs1.next())
					{
						dstartplace = drs1.getString(1).trim();
						dendplace=drs1.getString(2).trim();
					}
					if(dstartplace.equals(endplace)&&dendplace.equals(startplace))
					{
						if(isFull)
						{
						String[] dflightcode=new String[50];
						int i=0;
						for(i=0;i<50;i++)
						dflightcode[i]=new String("null");
						i=0;
						sql="select flight from flight where start='"+startplace+"' AND destination='"+endplace+"'";
						ResultSet drs2=stmt.executeQuery(sql);
						while(drs2.next())
						{
							 dflightcode[i]=drs2.getString(1);
							 i++;
						}
						stmt.close();
						con.close();
						i=0;
						while(!(dflightcode[i].equals("null")))
						{
							if(!(dflightcode[i].equals(string[11])));
							{
								isFull=seatinformation.isFull(dflightcode[i],string[3]);
								if(!isFull)
								{
									dotherchoice=dotherchoice+dflightcode[i];
								}
							}
							i++;
						}
						if(!(dotherchoice.equals(" ")))	return 2;
						else return 3;
					}
					}
					else	return 5;
					}
					if(flag==0)	return 4;
					}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				}
				return 0;
		}
		//***************************************************************************
	public int multipleflight()
	{
		if(jbtflight2.getText().trim().length()==0)	return 1;
		else
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","");
				Statement stmt = con.createStatement();
				//ResultSet rs = stmt.executeQuery("select * from flight");
				ResultSet mrs = stmt.executeQuery("select * from flight");
				//flag=1 exists the flight 
				while(mrs.next())
				{
					if(mrs.getString(1).trim().equals(string[16]))
					{
						flag=1;
					}
				}
				string[11]=string[16];
				if(flag==1)
				{	
					String mstartplace=new String();
					String mendplace=new String();
					String sql="select start,destination from flight where flight='"+string[16]+"'";
					ResultSet mrs1=stmt.executeQuery(sql);
					while(mrs1.next())
					{
						mstartplace = mrs1.getString(1).trim();
						mendplace=mrs1.getString(2).trim();
					}
					if(mstartplace.equals(endplace))
					{
						if(isFull)
						{
						String[] mflightcode=new String[50];
						int i=0;
						for(i=0;i<50;i++)
						mflightcode[i]=new String("null");
						i=0;
						sql="select flight from flight where start='"+startplace+"' AND destination='"+endplace+"'";
						ResultSet mrs2=stmt.executeQuery(sql);
						while(mrs2.next())
						{
							 mflightcode[i]=mrs2.getString(1);
							 i++;
						}
						stmt.close();
						con.close();
						i=0;
						while(!(mflightcode[i].equals("null")))
						{
							if(!(mflightcode[i].equals(string[16])));
							{
								isFull=seatinformation.isFull(mflightcode[i],string[17]);
								if(!isFull)
								{
									motherchoice=motherchoice+mflightcode[i];
								}
							}
							i++;
						}
						if(!(motherchoice.equals(" ")))	return 2;
						else return 3;
					}
					}
					else	return 5;
					}
					if(flag==0)	return 4;
					}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				}
				return 0;
		}
		//*************************************************************
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
}