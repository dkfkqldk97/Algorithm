import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1249_보급로_bfs {

	static int TC, N;
	static int map[][];
	static int min;
	static int[][] dir = { {0,1}, {-1,0}, {0,-1}, {1,0} };
	static int memo[][];
	static boolean[][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			min = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				String s = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(s.substring(i, i+1));
				}
			}
			
			memo = new int[N][N];
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) Arrays.fill(memo[i],Integer.MAX_VALUE);
			memo[0][0] = 0;
			
			
			bfs(0,0);
			System.out.println("#"+tc+" "+min);

			
		}
		
	}

	
	private static void bfs(int a, int b) {
		
		Queue<int[]> queue = new LinkedList<int[]>();
		
		queue.offer(new int[] {a,b});
		visited[a][b] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			if(r==N-1&&c==N-1) {
				min = min > memo[r][c]? memo[r][c] : min;
			}
			
			for(int d=0; d<4; d++) {
				int nr = r+dir[d][0];
				int nc = c+dir[d][1];
				
				if(nr>=0&&nr<N&&nc>=0&&nc<N) {
					
					
					if(!visited[nr][nc]||memo[nr][nc] > memo[r][c] + map[nr][nc]) {
						visited[nr][nc] = true;
						memo[nr][nc] = memo[r][c] + map[nr][nc];
						queue.offer(new int[] {nr,nc});
					}
				}
			}
			
			
			
		}
		
		
		
		
	}

}
