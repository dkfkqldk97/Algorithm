import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main1021_회전하는큐 {
	
	static int N,M;
	static LinkedList<Integer> deque = new LinkedList<>();
	static int[] num;
	static int min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		num = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		min = 0;
		for(int i=1; i<=N; i++) {
			deque.offer(i);
		}
		
		for(int i=0; i<M; i++) {
			find(num[i]);
//			System.out.println();
		}
		
		System.out.println(min);
		
	}

	private static void find(int a) {
		int index = deque.indexOf(a);
//		System.out.println("index:"+index);
		int size = deque.size();
//		System.out.println("size:"+size);
		
		int half = 0;
		if(size%2==0) {
			half = size/2-1;
		}else {
			half = size/2;
		}
//		System.out.println("half:"+half);
		
		
		//앞쪽에 있다면 2번연산
		if(index<=half) {
			for(int i=0; i<index; i++) {
				int temp = deque.poll();
//				System.out.println("2temp:"+temp);
				deque.offerLast(temp);
				min++;
			}
		//뒤쪽에 있다면 3번연산
		}else {
			for(int i=0; i<size-index; i++) {
				int temp = deque.pollLast();
//				System.out.println("3temp:"+temp);
				deque.offerFirst(temp);
				min++;
			}
		}
		
		deque.pollFirst();
//		for(int i=0; i<deque.size(); i++) {
//			System.out.print(deque.get(i));
//		}
//		System.out.println();
		
	}
	
	
}
