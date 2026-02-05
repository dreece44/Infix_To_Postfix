package CSCI3200;
public class Error_Cases {
	public static final String Invalid_Operand = "Error: Invalid Operator is Used";
	public static final String Invalid_Formula = "Error: Expression is not valid";
	public static final String Invalid_Parenthiesis = "Error: Parenthiesis were not properly closed";
	
	public static void Error_Check(String exp) throws Exception {
		int lcount = 0;
		int parCount = 0;
		for (int i = 0;  i < exp.length(); i++) {
			char c = exp.charAt(i);
			if (Character.isDigit(c)) {
			} else if (Character.isLetter(c)) {
				lcount += 1;
				if (lcount == 2) {
					throw new Exception(Invalid_Formula);
				}
			} else if (exp.charAt(i) == '(') {
				parCount += 1;
			} else if (exp.charAt(i) == ')') {
				parCount -= 1;
			} else if (Infix_Postfix_Conversion.precedence(c) == -1) {
				throw new Exception(Invalid_Operand);
			} else {
				lcount = 0;
			}
			
		}
		if (parCount != 0) {
			throw new Exception(Invalid_Parenthiesis); 
		}
	}
	
	
}
