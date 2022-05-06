import java.util.*;
import java.lang.*;

class Solution {
    class node{
        private long parent;
        private long child;

        // private boolean empty = false;
        public long getParent(){return parent;}
        public void setParent(long p){parent = p;}
        public long getChild(){return child;}
        public void setChild(long c){child = c;}
        // public boolean isEmpty(){return empty;}
        // public void occupied(){empty = true;}

        public node(long n){parent = n;}
    }
    public long[] solution(long k, long[] room_number) {
        long[] answer = {};
        Map<Long,node> occupied = new HashMap<>();
        ArrayList<Long> ans = new ArrayList<>();
        for( var e : room_number ){
            ans.add( addNode(occupied,e ) );
        }
        // Long[] a = ans.toArray(a);
        answer = new long [room_number.length];
        for(int i = 0; i < room_number.length; i++)
            answer[i] = ans.get(i).longValue();
        return answer;
    }
    public long addNode(Map<Long, node> m, long rn){
        // long node_numb;
        if(m.size() == 0){
                m.put(rn, new node (rn+1) );
                return rn;
        }
        else{
            if(m.containsKey(rn) == true){
                long node_numb = addNode( m, m.get(rn).getParent() );
                m.put(rn, new node( m.get(node_numb).getParent() ));
                return node_numb;
            }
            else{
                m.put(rn, new node( rn+1 ));
                return rn;
            }
        }

    }
}
