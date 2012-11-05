package manage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class InsertPanel extends JPanel
{
	private JTextField[] jtf=new JTextField[12];
	private JLabel[] label=new JLabel[12];
	private JButton[] button=new JButton[3];
	private JComboBox week,hour1,hour2,min1,min2;
	private String[] s={"1","2","3","4","5","6","7"};
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	public InsertPanel()
	{
		try
		{
			Class.forName("com.jdbc.mysql.Driver");
		}
		catch(Exception ex)
		{
		}
		week=new JComboBox();
		for(int i=0;i<s.length;i++)
		week.addItem(s[i]);
		
		hour1=new JComboBox();
		hour2=new JComboBox();
		min1=new JComboBox();
		min2=new JComboBox();
		for(int i=1;i<=24;i++)
		{
			if(i<10)
			{
				hour1.addItem(""+0+i);
				hour2.addItem(""+0+i);
		    }
		    else
		    {
		    	hour1.addItem(""+i);
				hour2.addItem(""+i);
		    }
		}
		for(int i=0;i<=59;i++)
		{
			if(i<10)
			{
				min1.addItem(""+0+i);
				min2.addItem(""+0+i);
		    }
		    else
		    {
		    	min1.addItem(""+i);
				min2.addItem(""+i);
		    }
		}
		
		JPanel p3=new JPanel();
		p3.add(hour1);
		p3.add(new JLabel("时"));
		p3.add(min1);
		p3.add(new JLabel("分"));
		
		JPanel p4=new JPanel();
		p4.add(hour2);
		p4.add(new JLabel("时"));
		p4.add(min2);
		p4.add(new JLabel("分"));
		
		JPanel p1=new JPanel();
		p1.setBorder(new TitledBorder("基本信息"));
		//p1.setLayout(new GridLayout(6,4,5,5));
		p1.setLayout(new GridLayout(6,4));
		p1.add(label[0]=new JLabel("  航班号:", SwingConstants.CENTER));
		p1.add(jtf[0]=new JTextField(10));
		p1.add(label[1]=new JLabel("    班期:", SwingConstants.CENTER));
		p1.add(jtf[1]=new JTextField(10));
		p1.add(label[0]=new JLabel("    公司:", SwingConstants.CENTER));
		p1.add(jtf[2]=new JTextField(10));
		p1.add(label[0]=new JLabel("    座位:", SwingConstants.CENTER));
		p1.add(jtf[3]=new JTextField(10));
		p1.add(label[0]=new JLabel("  起飞地:", SwingConstants.CENTER));
		
		p1.add(jtf[4]=new JTextField(10));
		p1.add(label[0]=new JLabel("  抵达地:", SwingConstants.CENTER));
		p1.add(jtf[5]=new JTextField(10));
		p1.add(label[0]=new JLabel("起飞时间:", SwingConstants.CENTER));
		p1.add(p3);
//		p1.add(jtf[6]=new JTextField(10));
		p1.add(label[0]=new JLabel("抵达时间:", SwingConstants.CENTER));
		p1.add(p4);
//		p1.add(jtf[7]=new JTextField(10));
		p1.add(label[0]=new JLabel("儿童票价:", SwingConstants.CENTER));
		p1.add(jtf[8]=new JTextField(10));
		p1.add(label[0]=new JLabel("成人票价:", SwingConstants.CENTER));
		p1.add(jtf[9]=new JTextField(10));
		p1.add(label[0]=new JLabel("提前折扣:", SwingConstants.CENTER));
		p1.add(jtf[10]=new JTextField(10));
		p1.add(label[0]=new JLabel("  退票率:", SwingConstants.CENTER));
		p1.add(jtf[11]=new JTextField(10));
		
		JPanel p2=new JPanel();
		p2.setBorder(new TitledBorder("操作"));
		p2.add(button[0]=new JButton("插入"));
//		p2.add(button[1]=new JButton("删除"));
//		p2.add(button[2]=new JButton("更新"));
		
		this.setLayout(new BorderLayout());
		this.add(p1,BorderLayout.CENTER);
		this.add(p2,BorderLayout.SOUTH);
		
		
		button[0].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","");
			        stmt = con.createStatement();
				if(jtf[0].getText().length()==0)
				{
					JOptionPane.showMessageDialog(null,"关键字不能为空","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}				
				
				 if(jtf[2].getText().length()==0)
				 {
				 	JOptionPane.showMessageDialog(null,"公司为空","错误",JOptionPane.ERROR_MESSAGE);
				 	return;
				 }
				
				
				 if(jtf[3].getText().length()==0)
				 {
				 	JOptionPane.showMessageDialog(null,"座位为空","错误",JOptionPane.ERROR_MESSAGE);
				 	return;
				 }
				
				
				 if(jtf[4].getText().length()==0)
				 {
				 	JOptionPane.showMessageDialog(null,"城市为空","错误",JOptionPane.ERROR_MESSAGE);
				 	return;
				 }
				
				
				 if(jtf[5].getText().length()==0)
				 {
				 	JOptionPane.showMessageDialog(null,"城市为空","错误",JOptionPane.ERROR_MESSAGE);
				 	return;
				 }				 
				
				 if(jtf[8].getText().length()==0)
				 {
				 	JOptionPane.showMessageDialog(null,"信息不能为空","错误",JOptionPane.ERROR_MESSAGE);
				 	return;
				 }
				
				
				 if(jtf[9].getText().length()==0)
				 {
			 		JOptionPane.showMessageDialog(null,"信息不能为空","错误",JOptionPane.ERROR_MESSAGE);
			 		return;
				 }
			
				
				 if(jtf[10].getText().length()==0)
				 {
				 	JOptionPane.showMessageDialog(null,"信息不能为空","错误",JOptionPane.ERROR_MESSAGE);
				 	return;
				 }
				
				
				 if(jtf[11].getText().length()==0)
				 {
			 		JOptionPane.showMessageDialog(null,"信息不能为空","错误",JOptionPane.ERROR_MESSAGE);
			 		return;
				 }			
				
				else
				{
					int maxremark=1;
					System.out.print("hello2");
				    rs=stmt.executeQuery("select remark from flight");System.out.print("hello1");
				    int remark=0;
					while(rs.next())
					{
						System.out.print(maxremark);
						remark=rs.getInt(1);
						if(remark>maxremark) maxremark=remark;
					}
					System.out.print(maxremark);
				    String time1=(String)hour1.getSelectedItem()+(String)min1.getSelectedItem();
				    String time2=(String)hour2.getSelectedItem()+(String)min2.getSelectedItem();
                    maxremark=maxremark+1;
			    	String sql="insert into flight values('"+jtf[0].getText()+"','"+jtf[2].getText()+"','"+
				                                  jtf[4].getText()+"','"+jtf[5].getText()+"','"+time1+"','"+ 
			                                    time2+"',"+Float.parseFloat(jtf[8].getText().trim())+","+Float.parseFloat(jtf[9].getText().trim())+","
			                                    +Float.parseFloat(jtf[10].getText().trim())+","+Float.parseFloat(jtf[11].getText().trim())+","+Integer.parseInt(jtf[3].getText().trim())
			                                    +",'"+jtf[1].getText()+"',"+maxremark+")";
			                                    
			        stmt.executeUpdate(sql);

			        
			        
			        System.out.print("he");
 
			        stmt.close();
			        con.close();
			     }}
			    catch(Exception ex)
				{
					ex.printStackTrace();
				}
			 }                                    
		});
	}
	
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception ex)
		{
			
		}
		JFrame frame=new JFrame();
		frame.getContentPane().add(new InsertPanel());
		frame.setSize(714,304);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}