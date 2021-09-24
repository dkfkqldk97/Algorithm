import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * <진행순서>
 * 1.배열을 입력 받으면서 익은 토마토의 개수 cnt변수에 저장, 안익은 토마토의 개수 rest변수에 저장, 익은 토마토의 인덱스 
 * 	 stack에 int[] 저장
 * 		->rest==0 이라면 0출력하고 끝낸다(이미 모든 토마토가 익은 상태라면)
 * 		->rest>0 이라면 토마토를 익히러 간다
 * 2.(토마토 익히는 함수)
 * 	 cnt만큼 stack에서 pop하면서 사방탐색을 진행해서 익은 토마토 주변 토마토를 익힌다
 *	  새로 익은 토마토의 개수는  newTomato변수에 저장, time++, map에 1표시해주고 인덱스를 stack에 push해준다
 *		-> rest>0 && newTomato==0 이면 
 *		      남은 토마토는 있지만 더이상 새로운 토마토를 익히지 못하는 상황이므로 -1출력하고 끝낸다
 *		-> rest==newTomato 이면 
 *		      남은 토마토를 모두 익힌거니까 time출력하고 끝낸다
 *		-> rest>newTomato 이면 아직 익힐 토맡토가 남아있으니까 다시 토마토 익히러 간다(2번반복)
 */
public class Main7576_토마토 {
	
	static int N, M;
	static int[][] map;
	static int cnt, rest, newTomato, time;
	static int[][] dir = { {-1,0}, {1,0}, {0,-1}, {0,1} };
	static Queue<int[]> tomato;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine().trim(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		tomato = new LinkedList<int[]>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			for(int j=0; j<M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				
				if(temp==1) {//익은 토마토라면
					cnt++; //익은 토마토의 개수 저장
					tomato.add(new int[] {i,j}); //익은 토마토의 인덱스 저장
				}else if(temp==0) { //안익은 토마토라면
					rest++;
				}
				
				map[i][j] = temp;
			}
		}
		
		if(rest==0) { //저장될때 부터 모든 토마도가 익어있는 상태
			System.out.println(0);
		}else if(rest>0) {
			go();
		}
	}
	
	private static void go() {
		newTomato=0;
		
		
		for(int i=0; i<cnt; i++) {//익은 토마토의 개수만큼 사방탐색을 진행한다
			int[] tomatoIndex = tomato.poll();
			int r = tomatoIndex[0];
			int c = tomatoIndex[1];
			
			for(int d=0; d<4; d++) {
				int nr = r+dir[d][0];
				int nc = c+dir[d][1];
				
				if(nr>=0&&nr<N&&nc>=0&&nc<M&&map[nr][nc]==0) { //인덱스에 속하고 안익은 토마토라면
					newTomato++;
					map[nr][nc]=1;//익은 토마토 표시
					tomato.add(new int[] {nr,nc}); //새로 익은 토마토 stack에 push해준다
				}
			}
		}
		
		//이미 익어있던 토마토에 대한 사방탐색이 끝났다
		time++;
		if(rest>0&&newTomato==0) { //안익은 토마토는 남아있는데 새로 익은 토마토가 없는 경우 토마토가 다 익을 수 없는 상황이다
			System.out.println(-1);
			return;
		}else if(rest==newTomato) { //안익은 토마토를 모두 익혀버린 상황
			System.out.println(time);
			return;
		}else if(rest>newTomato) { //아직 익힐 토마토가 남아있는 상황
			cnt=newTomato; //새로 익은 토마토의 개수
			rest-=newTomato; // 남아있는 토마토에서 새로 익은 토마토의 개수를 빼서 아직 익지못한 토마토의 개수를 구한다
			go();
		}
	}

}
