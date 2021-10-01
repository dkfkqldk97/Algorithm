import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main4485_초록색옷입은애가젤다지 {

	static int[][] map, minLose;
	static boolean[][] visited;
	static int TC,N;
	static int[][] dir = { {-1,0}, {0,-1}, {1,0}, {0,1} };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		TC=1;
		while(true) {
			
			N = Integer.parseInt(br.readLine());
			if(N==0) break;
			map = new int[N][N];
			minLose = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) Arrays.fill(minLose[i],Integer.MAX_VALUE);
			minLose[0][0] = map[0][0];
			
			
			System.out.println("Problem "+TC+": "+bfs(0,0));
			TC++;
		}
		
	}

	private static int bfs(int startR, int startC) {

		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2];
			}
		});
		
		pq.offer(new int[] {startR, startC, minLose[startR][startC]});
		
		while(!pq.isEmpty()) {
			
			int[] cur = pq.poll();
			int r = cur[0];
			int c = cur[1];
			int lose = cur[2];
			
			if(r==N-1&&c==N-1) return lose;
			
			if(visited[r][c]) continue;
			
			visited[r][c] = true;
			
			for(int d=0; d<4; d++) {
				
				int nr = r+dir[d][0];
				int nc = c+dir[d][1];
				
				if(nr>=0&&nr<N&&nc>=0&&nc<N&&!visited[nr][nc]&&minLose[nr][nc]>minLose[r][c]+map[nr][nc]) {
					minLose[nr][nc]=minLose[r][c]+map[nr][nc];
					pq.offer(new int[] { nr,nc,minLose[nr][nc]});
				}
			}
		}
		return minLose[N-1][N-1];
	}

}
