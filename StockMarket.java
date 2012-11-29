


package StockMarktApp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StockMarket extends JPanel {

	private JPanel jp;
	private JLabel jl;
	private Font jtfFont;
	private JTextField jtf; 
	private Tabs tab;


	public StockMarket(){
		super(new BorderLayout());
		
		tab = new Tabs();

		add(optLabel(), BorderLayout.WEST);
		add(tab, BorderLayout.CENTER);
		add(input(), BorderLayout.SOUTH);	
	}
	
	public Tabs getTabObject(){
		return tab;
	}

	public String getJTextFieldText(){
		return jtf.getText();
	}

	public void setJTextFieldText(String str){
		jtf.setText(str);
		jp.updateUI();
	}

	public void sendButton(String x){		
		jtf.setText(jtf.getText() + x);
		jp.updateUI();			
	}

	private JPanel optLabel(){
		jp = new JPanel(new FlowLayout());

		jl = new JLabel("<html><font size=5>Output:</font></html>");
		jp.add(jl);

		return jp;
	}

	private JPanel input(){
		jp = new JPanel(new FlowLayout(30));

		jl = new JLabel("<html><font size=5>Input:</font></html>");
		jp.add(jl);
		
		jtf = new JTextField();
		jtf.setBackground(getBackground()); jtf.setBorder(getBorder()); jtf.setEditable(false);

		jtfFont =new Font(jtf.getFont().getName(), jtf.getFont().getStyle(), 20);

		jtf.setFont(jtfFont);

		jp.add(jtf);

		return jp;
	}

}
