package manage;

import assist.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class DeletePanel extends JPanel  implements ActionListener
{
	private JLabel label=new JLabel("请选择下列某种情况删除");
	private JRadioButton radio1,radio2,radio3;
	public   static JComboBox city1=new JComboBox(),city2=new JComboBox(),
	                          flight1=new JComboBox(),week=new JComboBox(),flight2=new JComboBox();

    private static DefaultComboBoxModel modelFlight = new DefaultComboBoxModel();
    private static DefaultComboBoxModel modelCity = new DefaultComboBoxModel();
    
	private String[] weeks={"1","2","3","4","5","6","7"};
	
	private JLabel start=new JLabel("起飞城市");
	private JLabel end=new JLabel("抵达城市");
	private JButton ok=new JButton("确定");
	private ButtonGroup group=new ButtonGroup();
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String w="";
	private int k=0;
	private boolean bool=true;
	
	public DeletePanel()
	{
	    try
		{
			Class.forName("com.jdbc.mysql.Driver");
		}
		catch(Exception ex)
		{
		}
			    
	    radio1=new JRadioButton("按航班号进行");
	    radio2=new JRadioButton("按星期进行  ");
	    radio3=new JRadioButton("按起止城市  ");
	    
	    group.add(radio1);
	    group.add(radio2);
	    group.add(radio3);
	    
	    JPanel p9=new JPanel(new FlowLayout(FlowLayout.LEFT));
	    p9.add(flight1);
	    JPanel jp1= new JPanel(new BorderLayout());
	    jp1.add(new JLabel("                "),BorderLayout.WEST);
	    jp1.add(radio1,BorderLayout.CENTER);
	    jp1.add(new JLabel("                            "),BorderLayout.EAST);
	    
		JPanel p1=new JPanel();
		p1.setBorder(new TitledBorder("航班号"));
		p1.setLayout(new BorderLayout());
		p1.add(jp1,BorderLayout.WEST);
		p1.add(p9,BorderLayout.CENTER);
		
		JPanel p6=new JPanel(new FlowLayout(FlowLayout.LEFT));
		p6.add(flight2);
		p6.add(new JLabel("  "));
		p6.add(week);
		JPanel jp2 = new JPanel(new BorderLayout());
		jp2.add(new JLabel("                "),BorderLayout.WEST);
		jp2.add(radio2,BorderLayout.CENTER);
		jp2.add(new JLabel("                            "),BorderLayout.EAST);
		
		
		JPanel p2=new JPanel();
		p2.setBorder(new TitledBorder("星期"));
		p2.setLayout(new BorderLayout());
		p2.add(jp2,BorderLayout.WEST);
		p2.add(p6,BorderLayout.CENTER);


        JPanel p11=new JPanel(new FlowLayout(FlowLayout.LEFT));
        p11.add(start);
        p11.add(new JLabel("    "));
        p11.add(city1);
        JPanel p12=new JPanel(new FlowLayout(FlowLayout.LEFT));
        p12.add(end);
        p12.add(new JLabel("    "));
        p12.add(city2);
		JPanel p3=new JPanel();
		p3.setLayout(new GridLayout(2,1));		
		p3.add(p11);	
		p3.add(p12);
		
		JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jp3.add(new JLabel("             "),BorderLayout.WEST);
		jp3.add(radio3,BorderLayout.CENTER);
		jp3.add(new JLabel("                         "),BorderLayout.EAST);
		
		JPanel p4=new JPanel();
		p4.setBorder(new TitledBorder("航线"));
		p4.setLayout(new BorderLayout());
		p4.add(jp3,BorderLayout.WEST);
		p4.add(p3,BorderLayout.CENTER);
		
		
		JPanel p5=new JPanel();
		p5.setLayout(new BorderLayout());
		p5.add(p1,BorderLayout.NORTH);
		p5.add(p4,BorderLayout.CENTER);
		//p5.add(p2,BorderLayout.SOUTH);
		
		JPanel p7=new JPanel();
		p7.setBorder(new TitledBorder("操作"));
		p7.add(ok);		
		
		this.setLayout(new BorderLayout());
		this.add(label,BorderLayout.NORTH);
		this.add(p5,BorderLayout.CENTER);
		this.add(p7,BorderLayout.SOUTH);
		

		flight2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
		    {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","");
	        stmt = con.createStatement();	
		    	
		    	
				String flight=(String)flight2.getSelectedItem();
				rs=stmt.executeQuery("select week from flight where flight='"+flight+"'");
				while(rs.next())
				{
					w=rs.getString(1).trim();
				}
				for(int i=0;i<=w.length();i++)
				{
					char c=w.charAt(i);
					String s=""+c;
					week.addItem(s);	
				}
			    
			
		    
		     }
		     catch(Exception ex)
		     {
		     	
		     }
			}
		

          });
		
		ok.addActionListener(this);
		
	}
	
	public static void updateCityComboBox(String newCity,int flag)
	{
		if(flag==1)
		{
			if (modelCity.getIndexOf(newCity) == -1)
			{
				modelCity.addElement(newCity);
				city1.addItem(newCity);
				city2.addItem(newCity);
			}
		}
		if(flag==2)
		{
			if (modelCity.getIndexOf(newCity) != -1)
			{
				modelCity.removeElement(newCity);
				city1.removeItem(newCity);
				city1.removeItem(newCity);
			}			   			
		}		
	}
	
	public static void updateFlightComboBox(String newFlight,int flag)
	{
		if(flag==1)
		{
			if (modelFlight.getIndexOf(newFlight)  == -1)
			{
				modelFlight.addElement(newFlight);
				flight1.addItem(newFlight);
				flight2.addItem(newFlight);
			}
		
		}
		if(flag==2)
		{
			if (modelFlight.getIndexOf(newFlight) != -1)
			{				
				modelFlight.removeElement(newFlight);
				flight1.removeItem(newFlight);
				flight2.removeItem(newFlight);
			}
		}
	}
	
	//implements ActionListener and make this method
    public void actionPerformed(ActionEvent e)
	{
		try
		{
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","");
	        stmt = con.createStatement();
	        
			if(e.getSource() instanceof JButton)
			{
				if(radio1.isSelected())
				{
					String flight=(String)flight1.getSelectedItem();
					String sql="delete from flight where flight='"+flight+"'";
					stmt.executeUpdate(sql);
					
				}
				else if(radio3.isSelected())
				{
					String start=(String)city1.getSelectedItem();
					String destination=(String)city2.getSelectedItem();
                    String sql="delete from flight where start='"+start+"' and destination='"+destination+"'";
                    stmt.executeUpdate(sql);
                    					
				}
				else if(radio2.isSelected())
				{
					
					String flight=(String)flight2.getSelectedItem();
					rs=stmt.executeQuery("select week from flight where flight='"+flight+"'");
					while(rs.next())
					{
						w=rs.getString(1).trim();
					}
					for(int i=0;i<=w.length();i++)
					{
						char c=w.charAt(i);
						String s=""+c;
						if(week.getSelectedItem().equals(s))
						{
							k=i;break;
						}
					}
					String week=w.substring(0,k)+w.substring(k+1,w.length());
					System.out.println(week.length());
					if(week.length()==0) 
					stmt.executeUpdate("delete from flight where flight='"+flight+"'");
					else stmt.executeUpdate("update flight set week='"+week+"' where flight='"+flight+"'");
				}
				else JOptionPane.showMessageDialog(this,"你没有选择三种情况之一","选择",JOptionPane.ERROR_MESSAGE);
			}
			 
			
		}
		catch(Exception ex)
		{
		}
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
		frame.getContentPane().add(new DeletePanel());
		frame.setSize(300,300);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}