import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Comparator;
class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        //iterate the string.
        //While iterating, if find "{" read number untill find ","
        //put number into answer.
        //---- String[] trim = s.split(","); to use multiple letter in regex you should use group ( )
        //---- String[] trim = s.split("[{},]"); regex of java is using [] for grouping not()
        //---- String[] trim = s.split("[{},]"); I fond replace method of String.
        //----         String[] trim = s.replaceFirst("{", "");//.split("[{},]"); replaceFirst needs
        // regex as input argument not just String.
        //----System.out.println(s.replaceFirst("[{]", "")); replaceFirst only replace the first one
        //System.out.println(s.replaceAll("[{}]", ""));
        //---- String[] trim = s.replaceAll("[{}]", "").split("[,]"); Tuple concerns the order of element this way can't implement tuple.
        //---String[] trim = s.replaceAll("[{{]","{").split("[}+]"); need better replace
        //---   String[] trim = s.replace("{{","{").replace("}}","}").split("[}+,]"); need better split
        //      regex [}][,]* means start with } and end with "," "," can appear 0 and more times.
        String[] trim = s.replace("{{","{").replace("}}","}").split("[}][,]*");


        // ----Arrays.sort( trim, new Comparator<String> () { the length of String is s.length() not s.length
        //     @Override
        //     public int compare(String s1, String s2){
        //         if(s1.length > s2.length)
        //             return 1;
        //         }
        // });
        //Sort the array with its length.
        Arrays.sort( trim, new Comparator<String> () {
            @Override
            public int compare(String s1, String s2){
                if(s1.length() > s2.length())
                    return 1;
                else
                    return -1;
                }
        });
        // for(int i =0; i < trim.length;i++){
        //     for(int j = 1; j< trim.length;j++){
        //         if(trim[i].length() > trim[j].length()){
        //             String temp = trim[i];
        //             trim[i] = trim[j];
        //             trim[j] = temp;
        //         }
        //     }
        // }

        for (String a: trim)
        {
            // System.out.println(a);
            //remove first "{" and split with ","
            String[] temp = a.replace("{", "").split(",");

            for(String b : temp)
            {
                // System.out.println("length is " + answer.length);

                if(answer.length == 0){
                    // System.out.println(b);
                    answer = Arrays.copyOf(answer, answer.length+1);
                    answer[answer.length-1] = Integer.parseInt(b);
                    for(int d : answer){
                    System.out.print("lengthg == 0 "+d);
                    }
                }
                //---else if(Arrays.binarySearch(answer, Integer.parseInt(b)) <= 0){ inary search need sorted array
                else{
                    // System.out.printf("%s",answer.toString());
                    // for(int d : answer){
                    // System.out.print("b"+ d);
                    // }
                    var check = false;
                    for(int c : answer){
                        if(c == Integer.parseInt(b)){
                            check = true;
                            break;
                        }

                    }
                    if(check == false){
                    answer = Arrays.copyOf(answer, answer.length+1);
                    answer[answer.length-1] = Integer.parseInt(b);
                    System.out.print(Integer.parseInt(b) +"Added\n");
                    }

                }


                // answer = Arrays.copyOf(answer, answer.length+1);
                // answer[answer.length-1] = Integer.parseInt(b);
            }
        }


//         for (String a : trim )
//         {

//             //----if(a.isWhiteSpace() != 0), after split the empty array has "" ont white space
//             if( a != null )
//             {
//                 // System.out.println(a);
//                 //----int[] temp = copyOf(answer, answer.length+1); wrong way to use copyOf
//                 //---- int[] temp = Arrays.copyOf(answer, answer.length+1);
//                 //---- answer = Arrays.copyOf(answer, answer.length+1);//new int [answer.length+1];
//                 //----answer.append(a.parseInt()); integer.parseInt(String) is correct way to parse.
//                 //temp[answer.length-1] = Integer.parseInt(a);
//                 //removing dup
//                 //---answer[answer.length-1] = Integer.parseInt(a);
//                 //---removeDup.add(Integer.parseInt(a)); Set is not for this quiz

//             }
//         }

//         //--- for( int a : removeDup ) this is code to copy set to array no need anymore;
//         // {
//         //     System.out.println(a);
//         //     //----answer = new int[answer.length+1]; Need make copy if not the arrays will have ) values except the last element.
//         //     answer = Arrays.copyOf(answer, answer.length+1);
//         //     answer[answer.length-1] = a;
//         // }
        return answer;
    }
}
