import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PRIMPrac {

	static int V,E;
	static int[][] adjmatrix;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[][] adjMatrix = new int[N][N]; //인접 리스트로 구현
		boolean[] visited = new boolean[N];
		int[] minEdge = new int[N];
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
			minEdge[i] = Integer.MAX_VALUE;
		}
		
		
		minEdge[0] = 0; //임의의 시작은 0으로 초기화해놓고 시작한다
		int result = 0;
		
		for(int i=0; i<N; i++) {
			int min = Integer.MAX_VALUE;
			int minVertex = -1;
			
			for(int j=0; j<N; j++) {
				if(!visited[j]&&min>minEdge[j]) {
					min=minEdge[j];
					minVertex=j;
				}
			}
			
			visited[minVertex] = true;
			result+=min;
			
			for(int j=0; j<N; j++) {
				if(!visited[j]&&adjMatrix[minVertex][j]!=0&&minEdge[j]>adjmatrix[minVertex][j]) {
					minEdge[j] = adjMatrix[minVertex][j];
				}
			}
			
		}
		System.out.println(result);
		
	}

}
