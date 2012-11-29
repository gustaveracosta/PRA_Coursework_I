package StockMarktApp;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Tabs extends JPanel {
	
	private JPanel jp;
	private JLabel jl;
	private JTabbedPane jtp;
	private ArrayList<String> arrayAll;
	
	private String compName;
	private String change;
	private String price;
	private String dividend;
	private String capit;
	private String stockMarket;
	private String color;
	
	double absPrice;
	double absPercentile;
	double percentilePrice;
	
	public Tabs(){
		super(new FlowLayout());
		jtp = new JTabbedPane(JTabbedPane.LEFT);
		jtp.setPreferredSize(new Dimension(400,250));
		jp = new JPanel(new GridLayout(6,1));
		
		compName = "";
		price = "";
		change = "";
		dividend = "";
		capit = "";
		stockMarket = "";		
		
		jp.add(optDisplay());
		jtp.add(jp);
		add(jtp);
	}
	
	public void setTabs(String str){
		jtp.removeAll();
		
		EachTab et = new EachTab(str);
		
		if(et.isValidSymbol()){
			arrayAll = et.getTabsArray();
			sendToBeDisplayed();
		}else{
			JOptionPane.showMessageDialog(null, "Stock symbol not found!");
		}
	}
	
	private String setCurrency(String str){

		if(str.equals("NasdaqNM") || str.equals("NYSE")){
			return "$";
		}else if(str.equals("Brussels") || str.equals("Paris")){
			return "EUR";
		}else if(str.equals("HKSE")){
			return "HK$";
		}else if(str.equals("London")){
			return "p(ence)";
		}else if(str.equals("SES")){
			return "$$";
		}else{
			return "";
		}
	}
	
	private void computePrices(String cur, String prev){
		
		double currentPrice = Double.parseDouble(cur);		
		double previousPrice = Double.parseDouble(prev);
		
		absPrice = currentPrice - previousPrice;
		absPrice *= 100; 
		absPrice = Math.round(absPrice); 
		absPrice /= 100;
		
		percentilePrice = ( (currentPrice - previousPrice) / previousPrice ) * 100;
		percentilePrice *= 100; 
		percentilePrice = Math.round(percentilePrice); 
		percentilePrice /= 100;
	}
	
	private void sendToBeDisplayed(){
		
		int tabCounter = 1;
		
		for(int i=0; i < arrayAll.size(); i++){
			
			compName = arrayAll.get(i++);
			price = arrayAll.get(i) +" "+ setCurrency(arrayAll.get(i+4));
			
			computePrices(arrayAll.get(i++), arrayAll.get(i++));
			
			priceColor(absPrice);
			
			change = absPrice + " (" +percentilePrice+ "%)";
			dividend = arrayAll.get(i++);
			capit = arrayAll.get(i++);
			stockMarket = arrayAll.get(i);		
			
			jtp.addTab("Tab " + tabCounter++ , optDisplay());	
			
		}		
	}
	
	private void priceColor(double number){
		
		if(number == 0){
			color = "black";
		}else if(number > 0){
			color = "green";
		}else{
			color = "red";
		}
	}
	
	private JPanel optDisplay(){
		jp = new JPanel(new GridLayout(6,1));
		
		jl = new JLabel("<html><font size=6><i>" +compName+ "</i></font></html>");
		jp.add(jl);
				
		jl = new JLabel("<html><font size=5 color=blue><i>Price: </i></font>" +
				"<font size=5>" +price+ "</font></html>");
		jp.add(jl);
		
		jl = new JLabel("<html><font size=5 color=blue><i>Change: </i></font>" +
				"<font size=5 color=" +color+ ">" +change+ "</font></html>");
		jp.add(jl);
		
		jl = new JLabel("<html><font size=5 color=blue><i>Dividend: </i></font>" +
				"<font size=5>" +dividend+ "</font></html>");
		jp.add(jl);
		
		jl = new JLabel("<html><font size=5 color=blue><i>Market Cap.: </i></font>" +
				"<font size=5>" +capit+ "</font></html>");
		jp.add(jl);
		
		jl = new JLabel("<html><font size=5 color=blue><i>Stock Exchange: </i></font>" +
				"<font size=5>" +stockMarket+ "</font></html>");
		jp.add(jl);
		
		return jp;
	}

}
