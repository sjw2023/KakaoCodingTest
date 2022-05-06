import java.util.*;

class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        //___ArrayList<String> candidates = new ArrayList<> ();
        //---Set<Map<String, String>> removeDup = new HashSet<> ();
        //---Map<String, String> dict = new HashMap<> ();
        //---Dictionary<String,String> tableDict = new Dictionary<> ();
        //---Set < Pair<String, String> > removeDup = new HashSet<> ();
        //basic n^2 complexiti algorithm
        //finding substring
        // for(int i = 0; i < user_id.length; i++){
        //     for(int j = 0; j < banned_id.length; j++)
        //     if( compare( user_id[i], banned_id[j]) > 0  ){

        //         //candidates.add(user_id[i]);
        //         //except the dup id
        //         //---removeDup.add(user_id[i]);
        //         //need to connect banned and real
        //         //--removeDup( dict.put(banned_id[j], user_id[i]) ); map also storing unique key
                // //--dict.put(banned_id[j], user_id[i]); it removes same key elements while value is diff
                // //--tableDict.put(banned_id[j], user_id[i]); cannot use Dictionary
                // //--removeDup.add( new Pair<String, String> (banned_id[j], user_id[i] ));need to change strategy
//

//             }
        //---- Second strategy -- need to iterate banned_id first.
        //if find the match for the first banned_id save it and move on to next banned_id,
        //in case of dup banned_id check while processing second id compare with the first one.
        //once find ond set of restrained_id move on to next loop
        //from the first banned_id check again while compare the set of restrained_id.
        //once find
//         ArrayList< Map<String, String>> restrained_id = new ArrayList< Map< String, String > >();

//         for(int i = 0; i < banned_id.length; i++){
//             // Map< String, String > restrained_id_set = new HashMap< String, String > ();
//             for(int j = 0; j < user_id.length; j++){
//                 if( compare(user_id[j], banned_id[i]) > 0 ){//if you find match
//                     //check if fore element has same pair.
//                     if( restrained_id.size() == 0){
//                         restrained_id_set.put(banned_id[i], user_id[j]);//store it into set
//                         System.out.println("array empty" + banned_id[i]);
//                         break;
//                     }
//                     else{
//                         for( Map<String, String> e : restrained_id ){
//                             if(e.containsKey( banned_id[i] ) != true){
//                                 restrained_id_set.put(banned_id[i], user_id[j]);//store it into set
//                                 System.out.println("array not empty no dup" + banned_id[i]);
//                             }
//                             else{

//                                 System.out.println("array not empty dup" + banned_id[i]);
//                                 continue;
//                             }
//                         }
//                     }

//                 }
//             }
//             if(restrained_id_set.size() == banned_id.length){
//                 restrained_id.add(temp);//add set into ArrayList.

//             }
//                 //System.out.println(restrained_id_set.toString());
//         }
        //third strategy mark names that has been traveled.
        //inner for loopo have to be processed as many as banned_id length,

//         Set< Map<String, String>> restrained_id = new HashSet< Map< String, String > >();
//        Map<String, String> temp = new HashMap<>();
//         boolean[] mark = new boolean[ user_id.length ] ;
//         Arrays.fill(mark, false);
//         for(int l = 0; l < banned_id.length; l++){

//             for(int i = 0; i < banned_id.length; i++){
//                 for(int j = 0; j < user_id.length; j++){
//                     if(compare(user_id[j], banned_id[i]) == 1 && mark[j] != true){
//                         mark[j] = true;
//                         temp.put(banned_id[i],user_id[j]);
//                         System.out.println(temp);
//                         break;
//                     }
//                     else{

//                     }
//                 }
//             }
//             System.out.println("adding to set"+temp);
//             restrained_id.add( new HashMap<String, String>( temp ) );
//         }

        //Fourth Strategy
        //Find all possible user_id respect to banned_id
        Map <String, ArrayList<String> > possible_id = new HashMap<> ();
        for( String banned : banned_id){
            ArrayList<String> t = new ArrayList<>();
            for( String user : user_id){
                if( compare(user, banned) == 1 ){
                    t.add(user);
                }
            }
            possible_id.put(banned, t);
        }
        //Make restained_id table.
        // ArrayList< Map<String, String> > restained_id = new ArrayList<>();
//         String [][] possible_table = new String[banned_id.length][];
//         for(int i =0; i< banned_id.length; i++){
//             for( var e : possible_id.get( banned_id[i])){
//                 System.out.println(e);

//             }
//         }
        //bring out the arrays
        int level = banned_id.length;

        Set<Set<String>> result = getAnswer( possible_id, banned_id, level, 0 ,new HashSet<>(), new HashSet<>());
        System.out.println(result);
        answer = result.size();
        return answer;
    }
    public Set<Set<String>> getAnswer( Map<String,
                         ArrayList<String>> table,
                         String[] keys, int level, int depth,
                         Set<String> check,
                                     Set<Set<String>> removeDup){

        if(depth == level){
            if (check.size() == level){
                Set<String> temp = new HashSet<String> ();
                Iterator<String> it = check.iterator();
                while(it.hasNext())
                {
                    temp.add(it.next());
                    // System.out.println(it.next().toString());
                }
                removeDup.add(temp);
            }
            return removeDup;
        }
        var key = keys[depth];
        for( var value : table.get(key) ){
            if(check.contains(value) == true)
                continue;
            check.add(value);
            // System.out.println(depth + check.toString());
            // System.out.println(depth + removeDup.toString());
            removeDup = getAnswer(table, keys, level, depth+1, check, removeDup);
            // System.out.println(depth + removeDup.toString());
            check.remove(value);
        }
        return removeDup;
    }

    public int compare( String s1, String s2 ){
        //when length of both strings are diff
        if(s1.length() != s2.length() ){
            return -1;
        }
        //whent the length() of both strings are same
        else{
            for(int i = 0; i < s2.length(); i++){
                //* means every letter
                if(s2.charAt(i) == '*'){
                    continue;
                }
                //when same letter
                else if(s1.charAt(i) == s2.charAt(i)){
                    continue;
                }
                //when different letter
                else{
                    return -1;
                }
            }
            return 1;
        }
    }
}
