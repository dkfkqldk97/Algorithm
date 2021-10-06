import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 굳이 미세먼지를 ArrayList에 넣어서 관리할 필요는 없다
 */
public class Main17144_미세먼지안녕_배열회전_무한루프_수정 {

	static int R,C,T;
	static int[][] map, machine;
	static int[][] spread;
	static boolean[][]  visited;
	static int cnt, time;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine().trim(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		machine = new int[2][2];
		
		int m=0;
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			for(int j=0; j<C; j++) {
				int temp = Integer.parseInt(st.nextToken());
				
				//공기청정기인 경우
				if(temp==-1) { 
					machine[m][0] = i;
					machine[m][1] = j;
					m++;
				}
				map[i][j] = temp;
			}
		}
		
		for(int t=0; t<T; t++) 	spread();
		
		int result = total();
		System.out.println(result);
		
	}

	private static void spread() {
		
		//확산되는 미세먼지의 양을 누적하기 위한 배열
		spread = new int[R][C]; 

		int r,c,nr,nc;
		
		//확산되는 방향을 저장하기 위한 변수
		int dcnt;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				
				//미세먼지라면
				if(map[i][j]>0) {
					r = i;
					c = j;
					dcnt = 0;
					
					for(int d=0; d<4; d++) {
						nr=r+dr[d];
						nc=c+dc[d];
						
						//미세먼지를 확산시킨다
						if(nr>=0&&nr<R&&nc>=0&&nc<C&&map[nr][nc]!=-1) {
							spread[nr][nc]+=map[r][c]/5;
							dcnt++;
						}
					}
					//4방을 모두 확인했다면 확산된만큼 빼준다
					map[r][c]-=(map[r][c]/5)*dcnt;
				}
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j]+=spread[i][j];
			}
		}
		
		

//		print();
		cleanAir();
		
	}
	
	
	private static void cleanAir() {
		upCleaner(machine[0][0], machine[0][1]);
		downCleaner(machine[1][0], machine[1][1]);
	}
	
	
	//반시계 방향
	private static void upCleaner(int r, int c) {
		
		// v 
		for(int i=r-1; i>=1; i--) map[i][0] = map[i-1][0]; 
		// <==
		for(int i=0; i<C-1; i++) map[0][i] = map[0][i+1];
		// ^
		for(int i=0; i<r; i++) map[i][C-1] = map[i+1][C-1];
		// ==>
		for(int i=C-1; i>1; i--) map[r][i] = map[r][i-1];
		map[r][c+1] = 0;
	}
	
	
	//시계 방향
	private static void downCleaner(int r, int c) {
	
		// ^
		for(int i=r+1; i<R-1; i++) map[i][c] = map[i+1][c];
		// <==
		for(int i=0; i<C-1; i++) map[R-1][i] = map[R-1][i+1];
		// v
		for(int i=R-1; i>r; i--) map[i][C-1] = map[i-1][C-1];
		// ==>
		for(int i=C-1; i>0; i--) map[r][i] = map[r][i-1];
		map[r][c+1] = 0;
	}
		
	
	
	//미세먼지의 양 계산
	private static int total() {
		int sum = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]>0)sum+=map[i][j]; //미세먼지인 경우에 다 더해준다
			}
		}
		return sum;
	}
	
	
	
	private static void print() {
		for(int i=0; i<R; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}


}
