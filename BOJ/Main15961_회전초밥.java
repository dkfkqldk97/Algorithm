import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 조합으로 풀면 시간초과로 터진다
 */

public class Main15961_회전초밥 {

	static int N,d,k,c;
	static int[] sushi, eat;
	static boolean[] selected;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		sushi = new int[N];
		for(int i=0; i<N; i++) sushi[i] = Integer.parseInt(br.readLine());
		
		eat = new int[d+1];
		slide();
		
		System.out.println(max);
		
		
	}
	
	

	private static void slide() {

		int cnt = 0;
		for(int i=0; i<k; i++) {
			if(eat[sushi[i]]==0) {
				cnt++;
			}
			eat[sushi[i]]++;
		}
		
		for(int i=1; i<N; i++) {
			
			//이벤트 초밥을 아직 먹지 않았다면
			if(eat[c]==0) {
				eat[c]++;
				cnt++;
			}
			
			//현재 이벤트 초밥까지 더해서 먹은 초밥과 비교
			max = max < cnt? cnt : max;
			
			//슬라이드 이동한다
			
			//슬라이드에서 빠지는 스시 안먹는다
			eat[sushi[i-1]]--;
			if(eat[sushi[i-1]]==0) cnt--;
			
			//슬라이드에 포함되는 스시 먹는다
			if(eat[sushi[(i+k-1)%N]]==0) cnt++;
			eat[sushi[(i+k-1)%N]]++;
		}
		
		
		
	}



	

}
