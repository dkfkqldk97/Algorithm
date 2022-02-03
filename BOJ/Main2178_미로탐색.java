import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 가지치기
 */
public class Main2178_미로탐색 {

	static int N,M;
	static int[][] map;
	static int min;
	static boolean[][] visited;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		min = Integer.MAX_VALUE;
		visited = new boolean[N][M];
		visited[0][0] = true;
		bfs(0,0);
		
		System.out.println(map[N-1][M-1]);
	}

	private static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {r,c});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			
			for(int d=0; d<4; d++) {
				int nr = cr+dr[d];
				int nc = cc+dc[d];
				
				if(nr>=0&&nr<N&&nc>=0&&nc<M) {
					if(!visited[nr][nc]&&map[nr][nc]==1) {
						queue.offer(new int[] {nr, nc});
						map[nr][nc]=map[cr][cc]+1;
						visited[nr][nc]=true;
					}
				}
			}
		}
		
		
	}
}
