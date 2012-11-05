package booking;

import assist.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.sql.*;

public class Client extends JFrame implements ActionListener
{
	private JLabel name=new JLabel("** 真实姓名:");
	private JLabel id=new JLabel("** 证件号码:");
	private JLabel start=new JLabel("    出发地点:");
	private JLabel end=new JLabel("    到达地点:");
	private JLabel starttime=new JLabel("    出发时间:");
	private JLabel returntime=new JLabel("    返回时间:");
	private JLabel flight=new JLabel("    航 班 号 :");
	private JLabel telephone=new JLabel("** 联系电话:");
	private JLabel email=new JLabel("       E-Mail:");
	private JLabel ps=new JLabel("     简短附言:  ");
	private JLabel returnflight=new JLabel(" 返回航班号:");
	private JLabel adultticketnumber=new JLabel("    成人票数:");
	private JLabel childticketnumber=new JLabel("    儿童票数:");
	private JLabel airfirm=new JLabel("    航空公司:");
	private JLabel style=new JLabel("    机票类型:");
		
	static JTextField jbtname=new JTextField(" ",12);
	static JTextField jbtid=new JTextField(" ",20);
	private JTextField jbtstart=new JTextField(" ",12);
	private JTextField jbtend=new JTextField(" ",12);
	private JTextField jbtstarttime=new JTextField(" ",12);
	private JTextField jbtreturntime=new JTextField(" ",12);
	static JTextField jbtadultticketnumber=new JTextField("1",12);
	static JTextField jbtchildticketnumber=new JTextField("1",12);
	private JTextField jbtstyle=new JTextField(12);
	private JTextField jbtreturnflight=new JTextField(12);
	private JTextField jbtairfirm=new JTextField(12);
	static JTextField jbttelephone=new JTextField(12);
	static JTextField jbtemail=new JTextField(12);
	static JTextField jbtqq=new JTextField(12);
	private JTextField jbtflight=new JTextField(12);
	
	
	static JTextArea jbtps=new JTextArea(10,3);

	private JButton handin=new JButton("完成并提交");
	private JButton rewrite=new JButton("重 新 输 入");
	private JButton return1=new JButton("返回实时订票");
	private JPanel p10=new JPanel();

	private String[] string=new String[22];
	
	private SeatInfo seatinformation=new SeatInfo();
   	public Client()
	{	
		try
		{
			Class.forName("com.jdbc.mysql.Driver");
		}
		catch(Exception ex)
		{
		}
		
		jbtstarttime.setEditable(false);
		jbtreturntime.setEditable(false);
		jbtstart.setEditable(false);
		jbtend.setEditable(false);
		jbtflight.setEditable(false);
		jbtairfirm.setEditable(false);
		jbtreturnflight.setEditable(false);
		jbtstyle.setEditable(false);

		JPanel p1=new JPanel();
	//	p1.setBackground(color);
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.add(name);
		p1.add(jbtname);
		
		JPanel p2=new JPanel();
	//	p2.setBackground(color);
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		p2.add(id);
		p2.add(jbtid);
		
		JPanel p3=new JPanel();
	//	p3.setBackground(color);
		p3.setLayout(new FlowLayout(FlowLayout.LEFT));
		p3.add(start);
		p3.add(jbtstart);
		p3.add(style);
		p3.add(jbtstyle);
		
		JPanel p4=new JPanel();
	//	p4.setBackground(color);
		p4.setLayout(new FlowLayout(FlowLayout.LEFT));
		p4.add(end);
		p4.add(jbtend);
		p4.add(airfirm);
		p4.add(jbtairfirm);
		
		JPanel p5=new JPanel();
	//	p5.setBackground(color);
		p5.setLayout(new FlowLayout(FlowLayout.LEFT));
		p5.add(starttime);
		p5.add(jbtstarttime);
		p5.add(returntime);
		p5.add(jbtreturntime);
		
		JPanel p6=new JPanel();
		//p6.setBackground(color);
		p6.setLayout(new FlowLayout(FlowLayout.LEFT));
		p6.add(adultticketnumber);
		p6.add(jbtadultticketnumber);
		p6.add(childticketnumber);
		p6.add(jbtchildticketnumber);
	
		
		JPanel p7=new JPanel();
		//p7.setBackground(color);
		p7.setLayout(new FlowLayout(FlowLayout.LEFT));
		p7.add(telephone);
		p7.add(jbttelephone);
		p7.add(email);
		p7.add(jbtemail);
	
		JPanel p9=new JPanel();
		//p9.setBackground(color);
		jbtps.setLineWrap(true);
		jbtps.setBorder(new LineBorder(new Color(220,220,255),2));
		p9.setLayout(new BorderLayout());
		p9.add(ps,BorderLayout.WEST);
		JScrollPane scrollPane=new JScrollPane(jbtps);
		p9.add(scrollPane,BorderLayout.CENTER);
		
		JPanel p11=new JPanel();
		//p11.setBackground(color);
		p11.setLayout(new FlowLayout(FlowLayout.CENTER));
		p11.add(handin);
		p11.add(rewrite);
		p11.add(return1);
		JPanel p12=new JPanel();
		//p12.setBackground(color);
		p12.setLayout(new FlowLayout(FlowLayout.LEFT));
		p12.add(flight);
		p12.add(jbtflight);
		p12.add(returnflight);
		p12.add(jbtreturnflight);
	
//		p10.setBackground(color);
		p10.setBorder(new MatteBorder(new ImageIcon("./images/f.gif")));
		p10.setLayout(null);
		JLabel title=new JLabel("(带**的必须填写)",JLabel.LEFT);
		p10.add(title);
		p10.add(p1);
		p10.add(p2);
		p10.add(p3);
		p10.add(p4);
		p10.add(p5);
		p10.add(p6);
		p10.add(p7);		
		p10.add(p9);
		p10.add(p11);
		p10.add(p12);
		
		title.reshape(60,20,350,20);
		p1.reshape(40,40,350,40);
		p2.reshape(40,80,350,40);
		p3.reshape(40,120,500,40);
		p4.reshape(40,160,500,40);
		p5.reshape(40,200,500,40);
		p12.reshape(40,240,500,40);
		p6.reshape(40,280,500,40);
		p7.reshape(40,320,500,40);		
		p9.reshape(40,360,345,80);
		p11.reshape(72,460,400,30);
		rewrite.addActionListener(this);
		handin.addActionListener(this);
		return1.addActionListener(this);
	}
	
	public JPanel panel(String[] string)
	{
		this.string=string;	
		jbtstart.setText(string[0]);
		jbtend.setText(string[1]);
		jbtstarttime.setText(string[2]);
		
		
		jbtairfirm.setText(string[6]);
		jbtflight.setText(string[4]);
		jbtstyle.setText(string[8]);
		
		//single
		if(string[5].equals("单程"))
		{
			jbtreturnflight.setVisible(false);
			returnflight.setVisible(false);
			returntime.setVisible(false);
			jbtreturntime.setVisible(false);
		}
		//double
		else if(string[5].equals("往返"))
		{
			jbtreturnflight.setVisible(true);
			returnflight.setVisible(true);
			returntime.setVisible(true);
			jbtreturntime.setVisible(true);
		}
		//multiple
		else if(string[5].equals("联程"))
		{
			string[9]=string[18];
			string[10]=string[17];
			string[11]=string[16];
		}
		jbtreturnflight.setText(string[11]);
		jbtreturntime.setText(string[9]);
		return p10;
	}
	
	int adultnumber=0;
	int childnumber=0;
	int ticketnumber=0;
	
	public void actionPerformed(ActionEvent e)
	{
		
		int len1=jbtname.getText().trim().length();
		int len2=jbtid.getText().trim().length();
		int len3=jbtadultticketnumber.getText().trim().length();
		int len4=jbtchildticketnumber.getText().trim().length();
		int len5=jbttelephone.getText().trim().length();
		
		if(e.getSource()==handin)
		{			
			string[12]=jbtname.getText().trim();
			string[13]=jbtid.getText().trim();			
		
			int i=0;// leftticket for single
			int j=0;// leftticket for double and multiple
			if(len1==0||len2==0||len3==0||len4==0||len5==0)
			{
				String str=getstring(len1,len2,len3,len4,len5);
				JOptionPane.showMessageDialog(this,str,"错误信息！",JOptionPane.ERROR_MESSAGE);
				
			}   
			else
			{
			adultnumber=Integer.parseInt(jbtadultticketnumber.getText().trim());
			childnumber=Integer.parseInt(jbtchildticketnumber.getText().trim());
			
			string[14]=String.valueOf(adultnumber);
			string[15]=String.valueOf(childnumber);
			
			ticketnumber=adultnumber+childnumber;
				////single
				if(string[5].toString().trim().equals("单程"))
				{
					i=seatinformation.dingPiao(string[4],string[3],ticketnumber);         
				
					if(i!=-2)
					{
						if(i==-1)
						{
							float adultfare=0;
							float childfare=0;
							float piaojia=0;
							try
							{
								Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","");
								Statement stmt = con.createStatement();
								ResultSet rs = stmt.executeQuery("select adultFare,childFare from flight where flight='"+string[4]+"'");
								while(rs.next())
								{
									adultfare=rs.getFloat(1);
									childfare=rs.getFloat(2);
								}
								
							}
							catch(Exception ex)
							{
							}
							piaojia=adultnumber*adultfare+childnumber*childfare;
							String dingdan=string[3]+string[4]+String.valueOf((int)(100*Math.random()));
							string[16]=dingdan;
							string[21]=""+piaojia;
							JOptionPane.showMessageDialog(this,"   恭喜！提交成功\n你的定单号是"+dingdan+"\n"+"你应付价钱为"+piaojia,
							                              "客户信息",JOptionPane.INFORMATION_MESSAGE);
							Booking.clientFrame.setVisible(false);
							Booking.clientFrame.dispose();
							Booking.frame.setVisible(true);
							writeToFile writetofile=new writeToFile(string);
						}
						else
						{
							JOptionPane.showMessageDialog(this,"非常抱歉！只有"+i+"张航班票剩余\n请您重新选择票数",
							                              "客户信息",JOptionPane.INFORMATION_MESSAGE);
							jbtadultticketnumber.setText(" ");
							jbtchildticketnumber.setText(" ");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null,"对不起，今天没有这个航班",
						                              "客户信息",JOptionPane.ERROR_MESSAGE);
					} 		
				}
				//double
				else //if(string[5].toString().trim().equals("往返")||string[5].toString().trim().equals("往返"))
				{
					i=seatinformation.dingPiao(string[4],string[3],ticketnumber);
					j=seatinformation.dingPiao(string[11],string[10],ticketnumber);
					
					if(i==-2)
					   JOptionPane.showMessageDialog(null,"对不起，今天没有第一个航班",
					                                 "客户信息",JOptionPane.ERROR_MESSAGE);
					else if(j==-2)
					   JOptionPane.showMessageDialog(null,"对不起，今天没有第二个航班",
					                                 "客户信息",JOptionPane.ERROR_MESSAGE);
					else
					{
						if(i==-1&&j==-1)
						{
						float adultfare=0;
						float childfare=0;
						float piaojia=0;
						try
						{
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","");
							Statement stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery("select adultFare,childFare from flight where flight='"+string[4]+"'");
							while(rs.next())
							{
								adultfare=rs.getFloat(1);
								childfare=rs.getFloat(2);
							}
							
						}
						catch(Exception ex)
						{
						}
						piaojia=adultnumber*adultfare+childnumber*childfare;
						try
						{
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","");
							Statement stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery("select adultFare,childFare from flight where flight='"+string[11]+"'");
							while(rs.next())
							{
								adultfare=rs.getFloat(1);
								childfare=rs.getFloat(2);
							}
							
						}
						catch(Exception ex)
						{
						}
						piaojia=piaojia+adultnumber*adultfare+childnumber*childfare;
						String dingdan=string[3]+string[4]+String.valueOf((int)(100*Math.random()));
						string[16]=dingdan;
						string[21]=""+piaojia;
						JOptionPane.showMessageDialog(this,"   恭喜！提交成功\n你的定单号是"+dingdan+"\n"+"你应付价钱为"+piaojia,
						                              "客户信息",JOptionPane.INFORMATION_MESSAGE);
						Booking.clientFrame.setVisible(false);
						Booking.clientFrame.dispose();
						Booking.frame.setVisible(true);
						
						writeToFile writetofile=new writeToFile(string);
						
						}
					
						else
						{
							if(i!=-1)
							{
							JOptionPane.showMessageDialog(this,"非常抱歉！只有"+i+"张第一航班票剩余\n请您重新选择票数",
							                              "客户信息",JOptionPane.INFORMATION_MESSAGE);
							jbtadultticketnumber.setText(" ");
							jbtchildticketnumber.setText(" ");
							}
							else
							{
							if(j!=-1)
							{
								JOptionPane.showMessageDialog(this,"非常抱歉！只有"+j+"张返回航班票剩余\n请您重新选择票数",
								                              "客户信息",JOptionPane.INFORMATION_MESSAGE);
								jbtadultticketnumber.setText(" ");
								jbtchildticketnumber.setText(" ");
							}
							}
						}
					}
					
				}
				//multiple 
			
				}
		}
		if(e.getSource()==rewrite)
		{
			jbtname.setText("");
			jbtid.setText("");
			jbtadultticketnumber.setText("");
			jbtchildticketnumber.setText("");
			jbttelephone.setText("");
			jbtqq.setText("");
			jbtemail.setText("");
			jbtps.setText("");
		}
		if(e.getSource()==return1)
		{
			Booking.clientFrame.setVisible(false);
			Booking.clientFrame.dispose();
			Booking.frame.setVisible(true);
		}
	
	}
	public String getstring(int i,int j,int r,int s,int l)
	{
		if(i==0)
		   return "姓名不能为空！";
		else if(j==0)
		   return "证件号不能为空！";
		else if(r==0)
		   return "成人票数不能为空！";
		else if(s==0)
		   return "儿童票数不能为空";
		else if(l==0)
		   return "电话号码不能为空";
		   
		return "ERROR!";
	}
}