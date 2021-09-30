import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * bfs
 */
public class Solution1953_탕주범검거_bfs {

	static int TC,N,M,r,c,time, ans;
	static int[][] map;
	static boolean[][] visited;
	static int[][] pipe;
	static int[][] dir = { {-1,0}, {0,-1}, {1,0}, {0,1} } ; //상 좌 하 우
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine());
		
		makePipe();
		
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new boolean[N][M];
			
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			
			bfs(r,c);
			
			System.out.println("#"+tc+" "+ans);
			
		
		}
			
	}


	private static void bfs(int a, int b) {
		
		if(time==1) {
			ans=1;
			return;
		}

		Queue<int[]> queue = new LinkedList<int[]>();
		
		queue.offer(new int[] {a,b});
		visited[a][b] = true;
		
		int timeCnt = 1;
		
		while(true) {
			
			int qsize = queue.size();
			while(qsize>0) {
				
				int[] cur = queue.poll();
				qsize--;
				int r = cur[0];
				int c = cur[1];
				
				int[] curPipe = pipe[map[r][c]]; //어떤방향으로 갈 수 있는지
				
				
				
				for(int d=0; d<4; d++) {
					if(curPipe[d]==1) { //해당방향으로 가는경우
						
						int nr = r+dir[d][0];
						int nc = c+dir[d][1];
						
						if(nr>=0&&nr<N&&nc>=0&&nc<M&&!visited[nr][nc]&&map[nr][nc]!=0&&pipe[map[nr][nc]][(d+2)%4]==1) {
							
							visited[nr][nc] = true;
							queue.offer(new int[] {nr, nc} );
							
						}
						
					}
				}
			}
			
			timeCnt++;
			if(timeCnt==time) {
				ans = countSpace();
				return;
			}
		}
		
	}

	
	
	private static void makePipe() {
		
		pipe = new int[8][];
		pipe[1] = new int[] { 1,1,1,1 };
		pipe[2] = new int[] { 1,0,1,0 };
		pipe[3] = new int[] { 0,1,0,1 };
		pipe[4] = new int[] { 1,0,0,1 };
		pipe[5] = new int[] { 0,0,1,1 };
		pipe[6] = new int[] { 0,1,1,0 };
		pipe[7] = new int[] { 1,1,0,0 };
		
		
		
	}
	
	private static int countSpace() {
		
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j]) cnt++;
			}
		}
		return cnt;
	}

}
