package manage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class UpdatePanel extends JPanel implements ItemListener,ActionListener
{
	private JTextField[] jtf=new JTextField[12];
	private JCheckBox[] radio=new JCheckBox[12];
	private JButton button=new JButton("确定");
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private int flag=1;
	private boolean tag=false;
	
	public UpdatePanel()
	{
		try
		{
			Class.forName("com.jdbc.mysql.Driver");
		}
		catch(Exception ex)
		{
		}
		
		JPanel p1=new JPanel();
		p1.setBorder(new TitledBorder("基本信息"));
		p1.setLayout(new GridLayout(6,4,5,5));
		p1.add(radio[0]=new JCheckBox("航班号    "));
		p1.add(jtf[0]=new JTextField(10));
		p1.add(radio[1]=new JCheckBox("星期      "));
		p1.add(jtf[1]=new JTextField(10));
		p1.add(radio[2]=new JCheckBox("公司      "));
		p1.add(jtf[2]=new JTextField(10));
		p1.add(radio[3]=new JCheckBox("座位      "));
		p1.add(jtf[3]=new JTextField(10));
		p1.add(radio[4]=new JCheckBox("起飞地    "));
		p1.add(jtf[4]=new JTextField(10));
		p1.add(radio[5]=new JCheckBox("抵达地    "));
		p1.add(jtf[5]=new JTextField(10));
		p1.add(radio[6]=new JCheckBox("起飞时间  "));
		p1.add(jtf[6]=new JTextField(10));
		p1.add(radio[7]=new JCheckBox("抵达时间  "));
		p1.add(jtf[7]=new JTextField(10));
		p1.add(radio[8]=new JCheckBox("儿童票价  "));
		p1.add(jtf[8]=new JTextField(10));
		p1.add(radio[9]=new JCheckBox("成人票价  "));
		p1.add(jtf[9]=new JTextField(10));
		p1.add(radio[10]=new JCheckBox("提前折扣"));
		p1.add(jtf[10]=new JTextField(10));
		p1.add(radio[11]=new JCheckBox("退票率"));
		p1.add(jtf[11]=new JTextField(10));
		
		JPanel p2=new JPanel();
		p2.setBorder(new TitledBorder("操作"));
		p2.add(button);
		
		this.setLayout(new BorderLayout());
		this.add(p1,BorderLayout.CENTER);
		this.add(p2,BorderLayout.SOUTH);
		
		for(int i=0;i<=11;i++)
		jtf[i].setEditable(false);
		
		for(int i=0;i<=11;i++)
		radio[i].addItemListener(this);
		
		button.addActionListener(this);
		
		
	}
	public void itemStateChanged(ItemEvent e)
	{
		if(e.getSource() instanceof JCheckBox)
		{
			
			if(radio[1].isSelected()&&flag==1)
			{JOptionPane.showMessageDialog(null,"输入格式如135","暗示",JOptionPane.WARNING_MESSAGE);flag=0;}
			for(int i=0;i<=11;i++)
			if(radio[i].isSelected()) jtf[i].setEditable(true);
			for(int i=0;i<=11;i++)
			if(!radio[i].isSelected()) jtf[i].setEditable(false);
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		try
		{
		
		String sql="";
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flight","root","");
	    stmt = con.createStatement();
		if(e.getSource() instanceof JButton)
		{
			
			
			
			if(!radio[0].isSelected()||(radio[0].isSelected()&&jtf[0].getText().length()==0)) 
			JOptionPane.showMessageDialog(null,"关键字不能为空","错误",JOptionPane.ERROR_MESSAGE);
			
			else
			{
				for(int i=2;i<=11;i++)
					if(radio[i].isSelected()&&jtf[i].getText().length()==0)
					{
						tag=true;break;
					}  
				if(tag)
				{
					JOptionPane.showMessageDialog(null,"信息不能为空","错误",JOptionPane.ERROR_MESSAGE);
					tag = false;
				}
				   
				else
				{
					String sqlString = "select flight from flight where flight='" + jtf[0].getText().trim() + "'";
					ResultSet rs = stmt.executeQuery(sqlString);
					
					int flag1 = 0;
					while(rs.next())
					{
						flag1 = 1;
					}
					
					if (flag1 == 0)
					{
						JOptionPane.showMessageDialog(null,"对不起!航班号不存在!",
						                              "错误信息",JOptionPane.ERROR_MESSAGE);
						return;
					}
					System.out.println(flag1);
					
					if(radio[2].isSelected())
					{					
						sql="update flight set airFirm='"+jtf[2].getText()+"' where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);   
					}
				
			
				
					if(radio[3].isSelected())
					{
						sql="update flight set seat="+Integer.parseInt(jtf[3].getText().trim())+" where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);				   
					}
			
					if(radio[4].isSelected())
					{
						sql="update flight set start='"+jtf[4].getText()+"' where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);
					}
					if(radio[5].isSelected())
					{
						sql="update flight set destination='"+jtf[5].getText()+"' where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);
					}
					if(radio[6].isSelected())
					{
						sql="update flight set leaveTime='"+jtf[6].getText()+"' where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);
					}
					if(radio[7].isSelected())
					{
						sql="update flight set arriveTime='"+jtf[7].getText()+"' where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);
					}
					if(radio[8].isSelected())
					{
						sql="update flight set childFare="+Float.parseFloat(jtf[8].getText().trim())+" where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);
					}
					if(radio[9].isSelected())
					{
						sql="update flight set adultFare="+Float.parseFloat(jtf[9].getText().trim())+" where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);
					}
					if(radio[10].isSelected())
					{
						sql="update flight set discount1="+Float.parseFloat(jtf[10].getText().trim())+" where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);
					}
					if(radio[11].isSelected())
					{
						sql="update flight set discount2="+Float.parseFloat(jtf[11].getText().trim())+" where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);
					}
					
					JOptionPane.showMessageDialog(null,"航班信息已经更新成功!",
					                              "成功信息",JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			
			
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
		frame.getContentPane().add(new UpdatePanel());
		frame.setSize(400,350);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}