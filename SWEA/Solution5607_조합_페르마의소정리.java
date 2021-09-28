import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution5607 {

	static int T = 1234567891;
	static int TC,N,R;
	static long[] fac;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			
			st = new StringTokenizer(br.readLine().trim(), " ");
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			fac = new long[1000001];
		
			fac[0]=1;
			for(int i=1; i<1000001; i++) {
				fac[i] = (fac[i-1]*i)%T;
			}
			
			long result = comb(N,R);
			System.out.println("#"+tc+" "+result);
		}
		
	}
	
	public static long comb(int n, int r) {
		
			
		long a = fac[N]%T;
		long b = (fac[N-R]*fac[R])%T;
		
		b = pow(b,T-2);
		
		return (a*b)%T;
		
	}
	
	
	public static long pow(long a, long b) {
		if(b==0) return 1;
		
		else if(b==1) return a;
		
		//b가 짝수면 2번곱한다
		if(b%2==0) {
			long temp = pow(a,b/2);
			
			return (temp*temp)%T;
		}
		
		//b가 홀수면 2번
		long temp = pow(a,b-1)%T;
		
		return (temp*a)%T;
	}
	
	
	

}
