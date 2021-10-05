import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5656_벽돌깨기 {

	static int TC,N,W,H;
	static ArrayList<int[]> brickIndexes;
	static int[][] dir = { {-1,0}, {0,-1}, {1,0}, {0,1} };
	static int min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			
			st = new StringTokenizer(br.readLine().trim(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[H][W];
			
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			
			go(0, map);
			
			System.out.println("#"+tc+" "+min);
			
		}
		
	}

	
	private static void go(int ball, int[][] map) {
		
		//모든 구슬을 모두 사용
		if (ball==N) {
			//남아있는 벽돌의개수를 세서 최소값을 갱신해야한다
			int result = getRemain(map);
			min = Math.min(min,result);
			return;
		}
		
		int[][] breakMap = new int[H][W];
		//중복조합
		for(int c=0; c<W; c++) {
			
			//c열에 구슬 던졌을때 만나는 벽돌 찾기
			int r = 0;
			
			//인덱스 배열안에 속하고 벽돌이 없다면 내려가면서 계속 찾아야 한다
			while(r<H && map[r][c]==0) r++;
			
			//구슬에 맞는 벽돌이 없는 경우, 해당 열이 모두 빈칸인경우
			if(r==H) {
				go(ball+1,map);
				
				//구슬이 벽돌에 맞은경우
			}else {
				
				//이전까지의  map을 복사하여 사용하여야 한다
				copyMap(map, breakMap);
				
				//맞은 벽돌과 주변 벽돌 함께 제거해야한다
				breakBrick(r, c, breakMap);
				
				//빈곳이 있으면 벽돌을 당겨 채워준다
				fillBllank(breakMap);
				
				//다음구술을 던진다
				go(ball+1,breakMap);
			}
			
		}
		
	}
	
	


	private static int getRemain(int[][] map) {
		int cnt = 0;
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(map[i][j]>0) cnt++;
			}
		}
		return cnt;
	}


	//빈칸이 생기면 벽돌을 당겨서 채워준다
	private static void fillBllank(int[][] map) {
		
		for(int c=0; c<W; c++) {

			//밑에서부터 확인해줘야한다
			int r=H-1;
			
			while(r>0) {
				
				//빈칸이라면 벽돌을 내려야 한다
				if(map[r][c]==0) {
					
					//자신의 바로 위부터 탐색해야한다
					int nr = r-1;
					
					while(nr>0&&map[nr][c]==0) nr--;
					
					//벽돌을 찾으면 밑으로 내려줘야한다
					map[r][c] = map[nr][c];
					
					//내려준 벽돌은 없어졌다
					map[nr][c] = 0;
				}
				
				//빈칸이 아니라면 위에 탐색
				r--;
			}
		}
	}


	private static void breakBrick(int a, int b, int[][] map) {

		//범위에 포함되어 파괴되는 벽돌의 위치 저장
		Queue<int[]> newBreakBrick = new LinkedList<int[]>();
		
		//벽돌의 위치와 파괴가능한 범위
		if(map[a][b]>1)	newBreakBrick.offer(new int[] {a,b,map[a][b]});
		
		//벽돌은 파괴되었다
		map[a][b] = 0;
		
		
		while(!newBreakBrick.isEmpty()) {
			
			int[] cur = newBreakBrick.poll();
			int r = cur[0];
			int c = cur[1];
			int power = cur[2];
			
			//해당위치에 있는 벽돌은 파괴된다
			map[r][c] = 0;
			
			
			for(int d=0; d<4; d++) {
				int nr=r, nc=c;
				
				//파괴가능한 범위만큼 파괴한다
				for(int p=0; p<power-1; p++) {
					
					nr += dir[d][0];
					nc += dir[d][1];
					
					//인덱스 범위에 속하고 벽돌이 있다면
					if(nr>=0&&nr<H&&nc>=0&&nc<W&&map[nr][nc]>0) {
						//벽돌의 power가 1보다 크다면
						if(map[nr][nc]>1) newBreakBrick.offer(new int[] {nr,nc,map[nr][nc]});
						
						//벽돌을 파괴한다
						map[nr][nc] = 0;
					}
					
					
					
				}
			}
			
			
			
			
			
		}
		
		
		
		
		
		
	}


	
	
	
	//이차원 베열을 복사
	private static void copyMap(int[][] origin, int[][] copied) {
		for(int i=0; i<H; i++) {
			copied[i] = Arrays.copyOf(origin[i], W);
		}
	}

}
