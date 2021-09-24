import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main1786_찾기 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] text = in.readLine().toCharArray();
		char[] pattern = in.readLine().toCharArray();
		
		int tlength = text.length, plength = pattern.length;
		
		//부분일치 테이블 만들기 
		int[] pi = new int[plength];
		for(int i=1, j=0; i<plength; i++) {
			while(j>0 && pattern[i] != pattern[j]) {
				j=pi[j-1];
			}
			
			if(pattern[i]==pattern[j]) pi[i]=++j; 
			//pi[j]=0;
		}
		
		//패턴 매칭 찾기
		int cnt = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0, j=0; i<tlength; ++i) {
			
			while(j>0&&text[i]!=pattern[j]) j=pi[j-1];
			
			if(text[i]==pattern[j]) { //일치한다면
				if(j==plength-1) {
					cnt++;
					list.add(i+2-plength);
					j=pi[j];
				}else {
					j++;
				}
				
			}
		}
		
		System.out.println(cnt);
		if(cnt>0) {
			for(int i=0; i<list.size(); i++) {
				sb.append(list.get(i));
				sb.append(" ");
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb);
		}
	}

}
