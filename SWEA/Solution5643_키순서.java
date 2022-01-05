import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 부모와 자식에 나를 제외한 모든 노드가 연결되어 있으면 된다
 * ->
 */
public class Solution5643_키순서 {

	static int TC,N,M;
	static boolean[] visited;
	static Queue<Integer> queue;
	static int result;
	static ArrayList<Integer>[] parent;
	static ArrayList<Integer>[] child;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			result = 0;

			parent = new ArrayList[N+1];
			child = new ArrayList[N+1];
			
			for(int i=0; i<N+1; i++) {
				parent[i] = new ArrayList<Integer>();
				child[i] = new ArrayList<Integer>();
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				parent[start].add(end);
				child[end].add(start);
			}
			
			
			find();
			
			System.out.println("#"+tc+" "+result);
		}
		
		
		
		
		
		
		
	}

	private static void find() {
		
		for(int i=1; i<=N; i++) { //1번 노드부터 N번노드까지 탐색한다
			visited = new boolean[N+1];
			queue = new LinkedList<Integer>();
			
			queue.offer(i);
			visited[i]=true;;
			
			int cnt = 0;
			
			//현재노드의 부모노드에 해당하는 모든 노드를 찾는다
			//부모노드는 계속 부모노드만 찾는다
			while(!queue.isEmpty()) {
			
				int cur = queue.poll();
				
				for(int p : parent[cur]) {
					if(visited[p]) continue;
					
					visited[p] = true;
					queue.offer(p);
					cnt++;
				}
				
			}
			
			

			//현재노드의 자식모드에 해당하는 노드를 찾는다
			//자식노드는 계속 자식노드만 찾는다
			
			queue.offer(i);
			
			while(!queue.isEmpty()) {
				
				int cur = queue.poll();
				
				for(int c : child[cur]) {
					if(visited[c]) continue;
					
					visited[c] = true;
					queue.offer(c);
					cnt++;
				}
			}
			
			if(cnt==N-1) result++;
		}
	}
	
}
