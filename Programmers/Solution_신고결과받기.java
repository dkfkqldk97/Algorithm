import java.util.*;

class Solution_신고결과받기 {
    public int[] solution(String[] id_list, String[] report, int k) {
        StringTokenizer st;
        
        int memberCnt = id_list.length;
        int reportCnt = report.length;
        int[] answer = new int[memberCnt];
        
        Map<String, Integer> index = new HashMap<>();
        Map<String, List<Integer>> list = new HashMap<>();
        
        for(int i=0; i<memberCnt; i++) {
            index.put(id_list[i],i);
        }
        
        for(String rep : report) {
            String[] users = rep.split(" ");
            String fromUser = users[0];
            String toUser = users[1];
            if(!list.containsKey(toUser)) list.put(toUser, new ArrayList<>());
            List<Integer> temp = list.get(toUser);
            if(!temp.contains(index.get(fromUser))) temp.add(index.get(fromUser));
        }
        
        for(int i=0; i<memberCnt; i++) {
            String id = id_list[i];
            if(list.containsKey(id) && list.get(id).size()>=k) {
                for(int idx : list.get(id)) {
                    answer[idx]++;
                }
            }
        }
        
        
        return answer;
    }
}