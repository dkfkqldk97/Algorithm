import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3307_최장증가부분순열 {

	static int TC;
	static int N;
	static int[] arr;
	static int[] LTS;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			LTS = new int[N];
			st = new StringTokenizer(br.readLine().trim(), " ");
			
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int max = 0;
			for(int i=0; i<N; i++) {
				LTS[i] = 1;
				
				for(int j=0; j<i; j++) {
					if(arr[j]<arr[i] && LTS[i]<LTS[j]+1) {
						LTS[i] = LTS[j]+1;
					}
				}
				if(max<LTS[i]) max = LTS[i];
			}
			System.out.println("#"+tc+" "+max);
		}
		
		
		
	}

}
