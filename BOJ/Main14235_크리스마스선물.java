import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main14235_크리스마스선물 {
	
	static int N;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>(Collections.reverseOrder());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			//아이인경우
			if(a==0) {
				if(!pq.isEmpty()) sb.append(pq.poll()+"\n");
				else sb.append("-1\n");
				
			//거점인경우
			}else {
				for(int j=0; j<a; j++) {
					pq.offer(Integer.parseInt(st.nextToken()));
				}
			}
			
		}
		
		System.out.println(sb);

	}

}
