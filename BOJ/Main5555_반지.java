import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main5555_반지 {
	
	static char[] find;
	static int N;
	static char[][] input;
	
	static Queue<Character> queue;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		find = br.readLine().toCharArray();
		N = Integer.parseInt(br.readLine());
		input = new char[N][10];
		queue = new LinkedList<Character>();
		result = 0;
		
		for(int i=0; i<N; i++) {
			input[i] = br.readLine().toCharArray();
			find(input[i]);
		}
		
//		System.out.println(Arrays.toString(find));
		
	}

	private static void find(char[] arr) {
		int index = 0;
		
		for(int i=0; i<10; i++) {
			if(find[index]==arr[i]) {
				index++;
				
				
			}else {
				queue.offer(arr[i]);
			}
		}
	}

}
