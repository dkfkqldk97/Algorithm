import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main1058_친구 {

	static int N, max;
	static char[][] input;
	static boolean[] visited;
	static Queue<Integer> queue;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		input= new char[N][N];
		
		for(int i=0; i<N; i++) {
			input[i] = br.readLine().toCharArray();
		}
		
		max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			max = Integer.max(max,count(i));
		}
		
		System.out.println(max);
	}
		
	private static int count(int a) {
		queue = new LinkedList<Integer>();
		visited = new boolean[N];
		visited[a] = true;
		int sum = 0; 
		for(int i=0; i<N; i++) {
			if(input[i][a]=='Y'||input[a][i]=='Y') {
				if(!visited[i]) {
					queue.offer(i);
					visited[i] = true;
					sum++;
				}
			}
		}
		
		while(!queue.isEmpty()) {
			int q = queue.poll();
			for(int i=0; i<N; i++) {
				if(input[q][i]=='Y'&&!visited[i]) {
					sum++;
					visited[i] = true;
				}
			}
		}
		
		return sum;
		
	}
		

}
