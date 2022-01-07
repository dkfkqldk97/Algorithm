import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1861_정사각형방 {

	static int TC,N;
	static int[][] map;
	static boolean[][] visited;
	
	//상하좌우
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	//출력 좌표, 방 이동 카운드
	static int index, max;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			
			N = Integer.parseInt(br.readLine());
			//경계체크를 위해 0으로 패딩
			map = new int[N+2][N+2];
			visited = new boolean[N+2][N+2];
			max = Integer.MIN_VALUE;
			index = N;
			
			//배열 입력
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					countRoom(i,j,map[i][j],1);
				}
			}
			System.out.println("#"+tc+" "+index+" "+max);
		}
		
		
		
	}
	private static void countRoom(int r, int c, int start, int count) {
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			//다음칸이 0이 아니고 현재칸보다 1이 크다면
			if(map[nr][nc]!=0 &&(map[nr][nc] == map[r][c]+1)) {
				countRoom(nr,nc,start,count+1);
				
			//다음칸으로 갈 수 없다면
			}else {
				if(max<count) {
					max = count;
					index=start;
				}
				else if(max==count) {
					if(start<index) index=start;
				}
			}
		}
	}
}
