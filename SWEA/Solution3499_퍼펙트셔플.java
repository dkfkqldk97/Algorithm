import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution3499_퍼펙트셔플 {
	static int TC,N;
	static String[] input;
	static Queue<String> deck1;
	static Queue<String> deck2;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			deck1 = new LinkedList<String>();
			deck2 = new LinkedList<String>();
			
			input = new String[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) input[i] = st.nextToken();
			
			//카드 반으로 나누기
			divideCard();
			
			//카드 섞기
			perfectShuffle();
			
			for(int i=0; i<N; i++) {
				sb.append(input[i]);
				sb.append(" ");
			}
			
			sb.setLength(sb.length()-1);
			System.out.println("#"+tc+" "+sb);
			
			
		}
	}
	
	private static void perfectShuffle() {
		for(int i=0; i<N; i++) {
			if(i%2==0) input[i] = deck1.poll();
			if(i%2!=0) input[i] = deck2.poll();
		}
	}

	public static void divideCard() {
		for(int i=0; i<(N/2)+1; i++) deck1.offer(input[i]);
		if(N%2==0) {
			for(int i=N/2; i<N; i++) deck2.offer(input[i]);
		}else {
			for(int i=(N/2)+1; i<N; i++) deck2.offer(input[i]);
		}
	}

}
