import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2805_농작물수확하기 {

	static int TC, N;
	static int[][] farm;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			N = Integer.parseInt(br.readLine());
			farm = new int[N][N];
			
			//입력처리
			for(int i=0; i<N; i++) {
				char[] input = br.readLine().toCharArray();
				for(int j=0; j<N; j++) {
					farm[i][j] = input[j]-'0';
				}
			}
			
			System.out.println("#"+tc+" "+calcProfit());
		}
	}

	private static int calcProfit() {
		int profit = 0;
		int center = N/2;
		int begin, end, gap = 0;
		for(int i=0; i<N; i++) {
			begin = center-gap;
			end = center+gap;
			
			for(int j=begin; j<=end; j++) {
				profit+=farm[i][j];
			}
			
			if(i<center) gap++;
			else gap--;
		}
		return profit;
	}

}
