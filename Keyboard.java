package StockMarktApp;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Keyboard extends JPanel implements ActionListener {
	/*
	 * create a virtual keyboard with 4 row of characters
	 */

	// buttons to be added to the rows
	private JButton[] jb = new JButton[40];
	// panel to hold all buttons per row
	private JPanel jp;

	StockMarket stockMarket;

	// constructor method, creates a GridLayout managers to main panel
	// and add each row with FlowLayout manager layout
	public Keyboard(StockMarket stockMarket){
		super(new GridLayout(5,11));
		add(firstRow(), new FlowLayout());
		add(secondRow(), new FlowLayout());
		add(thirdRow(), new FlowLayout());
		add(fourthRow(), new FlowLayout());
		add(fifthRow(), new FlowLayout());
		this.stockMarket = stockMarket;
	}

	// return letter of the keyboard accordingly to value received
	private String getLetter(int num){
		switch(num){
		case 10:
			return "Q";
		case 11:
			return "W";
		case 12:
			return "E";
		case 13:
			return "R";
		case 14:
			return "T";
		case 15:
			return "Y";
		case 16:
			return "U";
		case 17:
			return "I";
		case 18:
			return "O";
		case 19:
			return "P";
		case 21:
			return "A";
		case 22:
			return "S";
		case 23:
			return "D";
		case 24:
			return "F";
		case 25:
			return "G";
		case 26:
			return "H";
		case 27:
			return "J";
		case 28:
			return "K";
		case 29:
			return "L";
		case 31:
			return "Z";
		case 32:
			return "X";
		case 33:
			return "C";
		case 34:
			return "V";
		case 35:
			return "B";
		case 36:
			return "N";
		case 37:
			return "M";
		case 38:
			return ".";

		default:
			return "";
		}
	}

	private String removeLast(String str){
		if(str.length() <= 0){
			return "";
		}else{
			return str.substring(0,str.length()-1);			
		}
	}

	// first row of characters
	private JPanel firstRow(){
		jp = new JPanel();

		for(int i=0; i<10; i++){
			jb[i] = new JButton("" + i);
			jb[i].addActionListener(this);
			jp.add(jb[i]);
		}

		return jp;
	}

	// second row of characters
	private JPanel secondRow(){
		jp = new JPanel();

		for(int i=10; i<20; i++){
			jb[i] = new JButton("" + getLetter(i));
			jb[i].addActionListener(this);
			jp.add(jb[i]);
		}

		jb[20] = new JButton("DEL");

		jb[20].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				String str = stockMarket.getJTextFieldText();

				if(!(str.equals(""))){
					stockMarket.setJTextFieldText(removeLast(str));
				}	
			}
		});

		jp.add(jb[20]);

		return jp;
	}

	// third row of characters
	private JPanel thirdRow(){
		jp = new JPanel();

		for(int i=21; i<30; i++){
			jb[i] = new JButton("" + getLetter(i));
			jb[i].addActionListener(this);
			jp.add(jb[i]);
		}

		jb[30] = new JButton("RETURN");

		jb[30].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				String str = stockMarket.getJTextFieldText();
				SymbolCheck sc = new SymbolCheck(str);
				
				// check if symbols are not greater than 8 and there's not repeated symbols
				if(!sc.getGreaterThanEight()){
					str = URLReader.readURL("http://finance.yahoo.com/d/quotes.csv?s=" + str + "&f=nl1pdj1xe1");
					Tabs tabObj = stockMarket.getTabObject();
					tabObj.setTabs(str);
				}else{
					JOptionPane.showMessageDialog(null, 
							"Stock symbols greater than eight characters are not allowed!");					
				}
			}
		});

		jp.add(jb[30]);

		return jp;
	}

	// fourth row of characters
	private JPanel fourthRow(){
		jp = new JPanel();

		for(int i=31; i<39; i++){
			jb[i] = new JButton("" + getLetter(i));
			jb[i].addActionListener(this);
			jp.add(jb[i]);
		}

		return jp;
	}

	// fifth row of characters
	private JPanel fifthRow(){
		jp = new JPanel();

		jb[39] = new JButton("Add Another Symbol");
		jb[39].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				String str = stockMarket.getJTextFieldText();
				int len = str.length();
				if( len > 0 && !(str.substring( len-1 , len ).equals("+")) ){
					stockMarket.sendButton("+");
				}
			}
		});

		jp.add(jb[39]);

		return jp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<jb.length; i++){
			if(e.getSource() == jb[i]){
				stockMarket.sendButton(jb[i].getText());
			}
		}
	}
}
