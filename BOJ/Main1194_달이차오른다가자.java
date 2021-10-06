import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1194_달이차오른다가자 {

	static int N,M;
	static char[][] map;

	static final char WALL='#';
	static final char SRC='0';
	static final char DEST='1';
	
	static final int INF = Integer.MAX_VALUE;
	
	static int[] dr = { -1,1,0,0 };
	static int[] dc = { 0,0,-1,1 };
	
	static int startR, startC;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N+2][M+2];
		
		//벽으로 맵 패딩
		for(int i=0; i<N+2; i++) Arrays.fill(map[i], WALL);
		
		//맵 초기화
		for(int i=1; i<=N; i++) {
			char[] ch = br.readLine().toCharArray();
			for(int j=1; j<=M; j++) {
				 map[i][j] = ch[j-1];
				 
				 //출발점
				 if(map[i][j]==SRC) {
					 startR = i;
					 startC = j;
				 }
			}
		}
		
		int answer = bfs();
		
		if(answer == INF) System.out.println(-1);
		else System.out.println(answer);
		
		
	}

	private static int bfs() {

		Queue<int[]> queue = new LinkedList<int[]>();
		//키는 A~F 6가지
		boolean[][][] visited = new boolean[N+2][M+2][1<<6];
		
		int result = Integer.MAX_VALUE;
		
		//시작위치, key, length
		queue.offer(new int[] {startR, startC, 0, 0} );
		visited[startR][startC][0] = true;
		
		while(!queue.isEmpty()) {

			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int key = cur[2];
			int length = cur[3];
			
			
			visited[r][c][key] = true;
			
			//도착점이라면
			if(map[r][c]==DEST&&result>length) {
				result = length;
				continue;
			}
		
			
			for(int d=0; d<4; d++) {
				
				int nr = r+dr[d];
				int nc = c+dc[d];
				int nk = key;
				
				//방문한적이 있거나 벽이라면 못간다
				if(visited[nr][nc][nk] || map[nr][nc]==WALL) continue;
			
				//문이라면
				if('A'<=map[nr][nc]&&map[nr][nc]<='F') {
					
					//어떤문인지
					int door = map[nr][nc]-'A';
					
					//열쇠가 없다면
					if((nk&1<<door) ==0) continue;
			
				
				//열쇠라면
				}else if('a'<=map[nr][nc]&&map[nr][nc]<='f') {
					
					//새로운 열쇠 획득
					nk |= ( 1<<( map[nr][nc]-'a'));
				}
				
//				visited[nr][nc][nk] = true;
				queue.offer(new int[] { nr,nc,nk,length+1});
			}
		}
		
		return result;
	}
	
	

}
