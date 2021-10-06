import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2239_스도쿠 {

	static int[][] map;
	static int cntZero;
	static ArrayList<int[]> zeroList;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[9][9];
		zeroList = new ArrayList<int[]>();
		for(int i=0; i<9; i++) {
			char[] ch = br.readLine().toCharArray();
			for(int j=0; j<9; j++) {
				map[i][j] = ch[j]-'0'; 
				
				//0인경우
				if(map[i][j]==0) zeroList.add(new int[] {i,j});
			}
		}
		
		//채워야 하는 0의 개수
		cntZero = zeroList.size();
		
		fill(0);
		
		
		
		
		
	}

	private static void fill(int cnt) {
		
		//모든 0을 다 채운경우
		if(cnt==cntZero) {
			print();
			System.exit(0);
		}
		
		//사용된 숫자를 확인하기 위한 배열
		boolean[] used = new boolean[10];
		
		//현재 채워야 하는 칸
		int[] cur = zeroList.get(cnt);
		int r = cur[0];
		int c = cur[1];
		
		
		//가로줄 확인
		for(int i=0; i<9; i++) {
			if(map[r][i]!=0) used[map[r][i]] = true;
		}
		
		
		//세로줄 확인
		for(int i=0; i<9; i++) {
			if(map[i][c]!=0) used[map[i][c]] = true;
		}
		
		
		//3*3 확인
		int cr = (r/3)*3;
		int cc = (c/3)*3;
		for(int i=cr; i<cr+3; i++) {
			for(int j=cc; j<cc+3; j++) {
				if(map[i][j]!=0) used[map[i][j]] = true;
			}
		}

		
		for(int i=1; i<10; i++) {
			
			//사용하지 않은 숫자인경우
			if(!used[i]) {
				map[r][c] = i;
				fill(cnt+1);
				map[r][c] = 0;
			}
		}
	
		
		
	}

	
	private static void print() {

		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	

}
