import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 100*100 ������ �迭 
 * ����� x�� ��ȯ
 * 
 * 
 */

public class Solution1210_Ladder {
	

	
	static final int LADDER = 1;
	static final int END = 2;
	
	static int[][] map;
	static int TC,N,M;
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//�¿� ��� üũ�� ���� ���� 102��
		TC=10; N = 100;
		
		for(int tc=1; tc<=TC; tc++) {
			
			map = new int[N][N+2];
			br.readLine();
			int col = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == END) col = j;
				}
			}
			System.out.println("#"+tc+" "+findStartCol(col));
		}
		
	}
	
	
	private static int findStartCol(int col) {
		for(int row=N-2; row>0; row--) {
			//���ʿ� ��ٸ��� �ִ� ���
			if(map[row][col-1]==LADDER) {
				//��ٸ��϶����� �� �������� ����
				do {
					col--;
				}while(map[row][col-1]==LADDER);
			//�����ʿ� ��ٸ��� �ִ� ���
			}else if(map[row][col+1]==LADDER) {
				do {
					col++;
				}while(map[row][col+1]==LADDER);
			}
		}
		return col-1;
	}

}
