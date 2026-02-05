package CSCI3200;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Infix_Postfix_Conversion {

	private void allotted_characters() {
		System.out.println("\n" + "Operators: + - * / %");
		System.out.println("Variables: A-Z & a-z");
		System.out.println("Works with all integers <=0");
	}
	
	//Method converts the user string into characters in reverse so it can be implemented in the stack correctly
	public static String[] Character_Conversion(String exp) {
		List<String> out = new ArrayList<>();
	    StringBuilder number = new StringBuilder();
		
	    for (int i = 0; i < exp.length(); i++) {
	    	char c = exp.charAt(i);
	        if (Character.isDigit(c)) {
	            number.append(c);                        
	        } else {
				if (number.length() >0) {
					out.add(number.toString());
					number.setLength(0);
				}
				out.add(String.valueOf(c));
	        }
		}
	    if (number.length() > 0) {out.add(number.toString());}
		return out.toArray(new String[0]);		
	}
	
	//Method declares that *, /, and % have a precedence of 2 and + and - have a precedence of 1
	public static int precedence(char c) {
	    switch (c) {
	        case '+': case '-': return 1;
	        case '*': case '/': case '%': return 2;
	        default: return -1;
	    }
	}
	
	
	
	public static Stack<String> Postfix_Notation(String[] exparr) throws Exception {
		Stack<String> post_exp = new Stack<String>();
		Stack<Character> oper = new Stack<Character>();
		for (int i = 0; i<exparr.length; i++) {
			//if the character on top of the stack is a variable it is pushed to the post_exp stack
			if (exparr[i].charAt(0) >= 65 &&  exparr[i].charAt(0) < 91 || exparr[i].charAt(0) >= 97 && exparr[i].charAt(0) < 123 || 
					exparr[i].charAt(0) >= 48 && exparr[i].charAt(0) <= 57) {
				post_exp.push(exparr[i]);
			}
			else {
				//push "(" onto oper stack
				if (exparr[i].charAt(0) == '(') {
					oper.push(exparr[i].charAt(0));
				}
				//if character is "+", "-", "/", '%', or "*"
				else if (exparr[i].charAt(0) == '*' || exparr[i].charAt(0) == '+' || exparr[i].charAt(0) == '-' || exparr[i].charAt(0) == '/' || exparr[i].charAt(0) == '%') {
					//checks the oper stack to see if not empty, not a "(", and the top of the oper stack has a greater precedence than the top of the exp_stack
					while (!oper.isEmpty() && oper.peek() != '(' && precedence(oper.peek()) >= precedence(exparr[i].charAt(0))) {						
						Character elem = oper.pop();
						post_exp.push(elem.toString());
					}
					oper.push(exparr[i].charAt(0));
				}
				//if character is ")" it pops the stack until it reaches the "("
				else if (exparr[i].charAt(0) == ')') {
					while (!oper.isEmpty() && oper.peek() != 40) {
						Character elem = oper.pop();
						post_exp.push(elem.toString());
					}
					//removes the "("
					oper.pop();
				}
			}
		}
		//pushes the remaining operators in the oper stack onto post_exp
		while (!oper.isEmpty()) {
			Character elem = oper.pop();
			post_exp.push(elem.toString());
		}
		return post_exp;
	}	
	

	
	public static String String_Conversion(Stack<String> c) {
		return String.join(" ", c);  // Stack iterates from bottom to top (in insertion order)
    }
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Type \"list\" to see allotted characters");
		System.out.println("Type \"end\" to terminate the program" + "\n");
		Infix_Postfix_Conversion ICP = new Infix_Postfix_Conversion();
		String expression = "";
		
		//Main Loop
		while (!expression.equals("end")) {
			System.out.print("Enter in equation in Infix form to convert into Postfix form: ");
			expression = scan.nextLine();
			if (expression.equals("list")) {
				ICP.allotted_characters();
			} else if (expression.equals("end")) {
				scan.close();
			} else {
				expression = expression.replaceAll("\\s+", "");

				try {
					Error_Cases.Error_Check(expression);
					String[] exparr = Character_Conversion(expression);
					System.out.println("This equation in Postfix Notation is: " + String_Conversion(Postfix_Notation(exparr)));
				} catch (Exception e){
					System.out.println(e);
				}
			}
			System.out.println();
		}
	}
}
