import java.util.Scanner;

public class Main11729_하노이탑이동순서 {

	
	static int N;
	static int count;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		count = 0;
		hanoi(N,1,2,3);
		
		System.out.println(count);
		System.out.println(sb);
		
	}
	
	public static void hanoi(int n, int start, int temp, int dest) {
		if(n==1) {
			sb.append(start+" "+dest+"\n");
			count++;
			return;
		}
		
		hanoi(n-1, start, dest, temp);
		sb.append(start+" "+dest+"\n");
		hanoi(n-1, temp, start, dest);
		count++;
	}

}
