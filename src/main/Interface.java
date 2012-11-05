package main;

import query.*;
import manage.*;
import booking.*;
import returning.*;
import assist.*;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.net.URL;

class Interface extends JPanel implements ActionListener
{
	private MyQuery query;
	private TestDB manager;
	private Returning tuiPiao;
	private Booking dingPiao;
	
	private JButton jbQuery = new JButton("查询");
	private JButton jbManager = new JButton("管理");
	private JButton jbDingPiao = new JButton("订票");
	private JButton jbTuiPiao = new JButton("退票");
	private JButton jbAbout = new JButton("关于");
	
	public Interface()
	{
		this.setLayout(null);
		this.add(jbQuery);	
		this.add(jbDingPiao);
		this.add(jbTuiPiao);
		this.add(jbManager);
		this.add(jbAbout);
		
		jbQuery.setBounds(95,280,80,30);		
		jbDingPiao.setBounds(205,280,80,30);
		jbTuiPiao.setBounds(315,280,80,30);
		jbManager.setBounds(425,280,80,30);
		jbAbout.setBounds(425,350,80,30);
		
		jbQuery.addActionListener(this);
		jbManager.addActionListener(this);
		jbDingPiao.addActionListener(this);
		jbTuiPiao.addActionListener(this);
		jbAbout.addActionListener(this);
	}
	
	public void paintComponent(Graphics g)
	{	    	
		ImageIcon imageIcon = new ImageIcon("./images/ui.png");
		Image image = imageIcon.getImage();
		
		g.drawImage(image,0,0,this);		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == jbQuery)
		{
			query = new MyQuery();
			
			query.setSize(557,387);
		//	query.setResizable(false);
		    query.setTitle("航班查询系统");
		    query.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		    query.setVisible(true);
		}
		   
		else if (e.getSource() == jbManager )
		{
			String zhangHao = JOptionPane.showInputDialog(null,"请输入你的帐号:",
			                                              "帐号验证",JOptionPane.PLAIN_MESSAGE);
			if (zhangHao == null)
			   return;
			if (!zhangHao.equals("city") )
			{
				JOptionPane.showMessageDialog(null,"对不起!你的帐号不正确!",
				                              "帐号错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String password = JOptionPane.showInputDialog(null,"请输入你的密码:",
			                                              "密码验证",JOptionPane.PLAIN_MESSAGE);
			if (password == null )
			    return;
			if (!password.equals("city"))
			{
				JOptionPane.showMessageDialog(null,"对不起!你的密码不正确!",
				                              "帐号错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			manager = new TestDB();
			
			manager.setSize(765,373);
			manager.setResizable(false);
		    manager.setTitle("航班管理系统");
		    manager.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			manager.setVisible(true);
		}
		
		else if (e.getSource() == jbDingPiao)
		{
			dingPiao = new Booking();
			
			dingPiao.setSize(542,300);
			//dingPiao.setResizable(false);
			dingPiao.setTitle("航班订票系统");
			dingPiao.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);			
			dingPiao.setVisible(true);
		}
		   
		else if (e.getSource() == jbTuiPiao)
		{
			tuiPiao = new Returning();
			
			tuiPiao.setSize(660,390);
			//tuiPiao.setResizable(false);
	    	tuiPiao.setTitle("航班退票系统");
	    	tuiPiao.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	    	
	    	tuiPiao.setVisible(true);
		}
		
		else if (e.getSource() == jbAbout)
		{
			String information = "关于我们" + "\n" +
           	  	                 "组长: " + "练楷鑫" + "\n" + 
           	  	                 "组员: " + "郭双双 李慕紫 黄自强" + "\n" +
           	  	                 "学校: " + "大连理工大学城市学院";
           	  	                 
			JOptionPane.showMessageDialog(null,information,"关于",JOptionPane.INFORMATION_MESSAGE);
		}		   
	}
}