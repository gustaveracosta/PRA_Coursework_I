package StockMarktApp;

public class SymbolCheck {
	
	private String symbol;
	private String[] array;
	private boolean greaterThanEight = false;
	
	public SymbolCheck(String symbol){
		this.symbol = changePlusSign(symbol);
		array = this.symbol.split(" ");
		checkSize();
	}
	
	public boolean getGreaterThanEight(){
		return greaterThanEight;
	}

	public boolean isReady(){
		return true;
	}
	
	private void checkSize(){
		for(int i=0; i<array.length; i++){
			if(array[i].length() > 8){
				greaterThanEight = true;
				break;
			}
		}
	}
	
	private String changePlusSign(String str){
		
		int i = 0;
		String word = "";
		
		while(i < str.length()){
			if(str.charAt(i) == '+'){
				word += " ";
				i++;
			}else{
				word += str.charAt(i);
				i++;
			}
		}
		return word;
	}
}
