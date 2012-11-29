package StockMarktApp;

import java.util.ArrayList;

public class EachTab {

	private String[] tabArray;
	private boolean symbolValidity;
	private ArrayList<String> arrayTxt;

	public EachTab(String str){
		tabArray = str.split("\n");
		divideInput(str);
	}

	public ArrayList<String> getTabsArray(){
		return arrayTxt;
	}

	public boolean isValidSymbol(){
		return symbolValidity;
	}

	private void divideInput(String ipt){

		arrayTxt = new ArrayList<String>();

		for(int i=0; i < tabArray.length; i++){
			symbolValidity = isStockSymbol(tabArray[i]);

			if(!symbolValidity){
				break;
			}

			if(hasCommaCompany(tabArray[i])){
				String[] component = tabArray[i].split(",");
				component = fixComma(component);
				organizeLine(component);
			}else{
				String[] component = tabArray[i].split(",");
				organizeLine(component);
			}
		}
	}
	
	private String[] fixComma(String[] array){
		
		for(int i=0; i<array.length-1; i++){
			if(i == 0){
				array[0] = array[0] +","+ array[1];
			}else{
				array[i] = array[i+1];
			}
		}
		return array;
	}
	
	private void organizeLine(String[] strArray){
		
		for(int i=0; i<6; i++){
			if(i == 0){
				arrayTxt.add(removeQuotes(strArray[i]));
			}else if(i == 5){
				arrayTxt.add(removeQuotes(strArray[i]));
			}else{
				arrayTxt.add(strArray[i]);
			}
		}
	}

	private String removeQuotes(String str){
		return str.substring(1, str.length()-1);
	}

	private boolean isStockSymbol(String str) {
		return str.substring(str.length()-4, str.length()-1).equals("N/A");
	}

	private boolean hasCommaCompany(String str){
		int i = 1;

		while( i < str.length() ){

			if(str.charAt(i) == '"'){
				return false;
			}else if(str.charAt(i) == ','){
				return true;
			}else{
				i++;
			}
		}	

		return false;
	}
}
