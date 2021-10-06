import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4014_활주로건설 {

	static int TC, N, X;
	static int[][] map;
	static boolean[][] visited;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			
			st = new StringTokenizer(br.readLine().trim(), " ");

			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			ans = 0;
		
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//가로방향 탐색
			searchRow();
			
			//세로방향 탐색
			searchCol();
			
			System.out.println("#"+tc+" "+ans);
		}

		
	}

	
	
	private static void searchRow() {
		
		visited = new boolean[N][N];
		
		//가로방향으로 탐색
		for(int i=0; i<N; i++) {
			for(int j=1; j<N; j++) {
				
				
				//높이의 차이가 있다면
				if(map[i][j-1]!=map[i][j]) {
					
					
					//왼쪽이 높은경우
					if(map[i][j-1]==map[i][j]+1) {
						
						
						//아직 활주로가 건설되지 않았다면
						if(!visited[i][j]) {
							
							//낮은 쪽의 높이, 길이
							int temp = map[i][j];
							int length = 0;
							
							//낮은쪽의 길이가 x이상
							for(int k=j; k<j+X; k++) if(k<N&&map[i][k]==temp&&!visited[i][k]) length++;
							
							//활주로 건설이 가능하다면
							if(length==X) {
								
								//활주로 건설 체크
								for(int k=j; k<j+X; k++) visited[i][k] = true;
								
								//경사 있지만 경사로 설치 불가능 다음 행 확인
							}else {
								j=N;
								break;
							}
						}else {
							j=N;
							break; 
						}
						
						//오른쪽이 높은경우
					}else if(map[i][j-1]+1==map[i][j]){
						
						//아직 활주로가 건설되지 않았다면
						if(!visited[i][j-1]) {
							
							//낮은 쪽의 높이, 길이
							int temp = map[i][j-1];
							int length = 0;
							
							//낮은쪽의 길이가 x이상
							for(int k=j-1; k>=j-X; k--) if(k>=0&&map[i][k]==temp&&!visited[i][k]) length++;
							
							//활주로 건설이 가능하다면
							if(length==X) {
								
								//활주로 건설 체크
								for(int k=j-1; k>=j-X; k--) visited[i][k] = true;
								
								
								//경사 있지만 경사로 설치 불가능 다음 행 확인
							} else {
								j=N;
								break; 
							}
						}else {
							j=N;
							break; 
						}
						
						//높이 차이가 2이상인 경우
					}else if(Math.abs(map[i][j]-map[i][j-1])>1) {
						j=N;
						break;
					}
				}
				if(j==N-1) ans++;
				
			}
		}
	}

	
	
	
	private static void searchCol() {
		
		visited = new boolean[N][N];
		
		//세로방향으로 탐색
		for(int j=0; j<N; j++) {
			for(int i=1; i<N; i++) {
				
				
				//높이의 차이가 있다면
				if(map[i-1][j]!=map[i][j]) {
					
					
					//위쪽이 높은경우
					if(map[i-1][j]==map[i][j]+1) {
						
						
						//아직 활주로가 건설되지 않았다면
						if(!visited[i][j]) {
							
							//낮은 쪽의 높이, 길이
							int temp = map[i][j];
							int length = 0;
							
							//낮은쪽의 길이가 x이상
							for(int k=i; k<i+X; k++) if(k<N&&map[k][j]==temp&&!visited[k][j]) length++;
							
							//활주로 건설이 가능하다면
							if(length==X) {
								
								//활주로 건설 체크
								for(int k=i; k<i+X; k++) visited[k][j] = true;
								
								//경사 있지만 경사로 설치 불가능 다음 열 확인
							}else {
								i=N;
								break;
							}
						}else {
							i=N;
							break; 
						}
						
						//아레쪽이 높은경우
					}else if(map[i-1][j]+1==map[i][j]){
						
						//아직 활주로가 건설되지 않았다면
						if(!visited[i-1][j]) {
							
							//낮은 쪽의 높이, 길이
							int temp = map[i-1][j];
							int length = 0;
							
							//낮은쪽의 길이가 x이상
							for(int k=i-1; k>=i-X; k--) if(k>=0&&map[k][j]==temp&&!visited[k][j]) length++;
							
							//활주로 건설이 가능하다면
							if(length==X) {
								
								//활주로 건설 체크
								for(int k=i-1; k>=i-X; k--) visited[k][j] = true;
								
								
								//경사 있지만 경사로 설치 불가능 다음 열 확인
							} else {
								i=N;
								break; 
							}
						}else {
							i=N;
							break; 
						}
						
						//높이 차이가 2이상인 경우
					}else if(Math.abs(map[i][j]-map[i-1][j])>1) {
						i=N;
						break;
					}
				}
				if(i==N-1) ans++;
			}
		}
	}
	
	
	
	
}


