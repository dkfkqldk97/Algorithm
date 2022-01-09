import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution2001_파리퇴치 {

	static int TC, N, M;
	static int[][] map;
	static int max;        
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N+1];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
		
			makeHap();
//			for(int i=0; i<N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			
			max = Integer.MIN_VALUE;
			findMax();
			
			System.out.println("#"+tc+" "+max);
			
		}
		
	}


	private static void findMax() {
		int sum = 0;
		for(int i=0; i<N-M+1; i++) {
			for(int j=M; j<=N; j++) {
				sum = 0;
				for(int k=0; k<M; k++) {
					sum+=map[i+k][j]-map[i+k][j-M];
				}
//				System.out.println("i:"+i+" "+"j:"+j+" "+"max:"+max);
				max = Integer.max(max,sum);
			}
		}
	}


	private static void makeHap() {
		for(int i=0; i<N; i++) {
			for(int j=1; j<=N; j++) {
				map[i][j]+=map[i][j-1];
			}
		}
	}

}
