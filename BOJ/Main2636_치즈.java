import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * 공기부터 시작해서 테두리 찾는다
 */

public class Main2636_치즈 {
	
	//입력 처리
	static int M,N;
	static int[][] map;
	
	static final int AIR = 0;
	static final int CHEESE = 1;
	static final int MELT = -1;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static Queue<Edge> queue;
	static boolean[][] visited;
	
	static int time, cheese, temp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) cheese++;
			}
		}
		
		time = 1; temp =0;
		while(true) {
			findBorder();
			melt();
			
			temp = cheeseCount();
			
			if(temp==0) {
				System.out.println(time);
				System.out.println(cheese);
				break;
			}else {
				time++;
				cheese = temp;
			}
		}
	}

	
	//테두리 치즈 찾기
	private static void findBorder() {
		queue = new LinkedList<Edge>();
		queue.offer(new Edge(0,0));;
		visited = new boolean[M][N];
		
		while(!queue.isEmpty()) {
			Edge cur = queue.poll();
			int cr = cur.r; int cc = cur.c;
			
			for(int d=0; d<4; d++) {
				int nr = cr+dr[d];
				int nc = cc+dc[d];
				
				//바깥공기라면
				if(nr>=0 && nr<M && nc>=0 && nc<N) {
					if(map[nr][nc]==AIR && visited[nr][nc]==false) {
						queue.offer(new Edge(nr,nc));
						visited[nr][nc] = true;
					}
					else if(map[nr][nc]==1) {
						visited[nr][nc]=true;
						map[nr][nc]=-1;
					}
				}
			}
		}
	}
	
	
	//치즈 녹이기
	private static void melt() {
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==-1) map[i][j]=0;
			}
		}
	}

	
	private static int cheeseCount() {
		int cnt = 0;
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1) cnt++;
			}
		}
		return cnt;
	}
	
	static class Edge {
		int r;
		int c;
		
		public Edge(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

}
