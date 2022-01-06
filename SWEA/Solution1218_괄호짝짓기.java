import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution1218_괄호짝짓기 {
	
	static int TC, answer;
	static char[] input;
	static Stack<Character> stack;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		TC=10;
		
		for(int tc=1; tc<=TC; tc++) {
			br.readLine();
			input = br.readLine().toCharArray();
			stack = new Stack<Character>();
			answer = 1;
			
			for(char ch : input) {
				switch(ch) {
				
				case '(' : case '[' : case '{' : case '<' : 
					stack.push(ch);
					break;
				case ')' : 
					if(stack.isEmpty() || stack.pop()!='(') 
						answer = 0;
					break;
				case ']' : 
					if(stack.isEmpty() || stack.pop()!='[') 
						answer = 0;
					break;
				case '}' : 
					if(stack.isEmpty() || stack.pop()!='{') 
						answer = 0;
					break;
				case '>' : 
					if(stack.isEmpty() || stack.pop()!='<') 
						answer = 0;
					break;
				}
			}
			System.out.println("#"+tc+" "+answer);
		}
		

	}

}
