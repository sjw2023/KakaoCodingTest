class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        //Basic design
        //linear
        //while isAbleToCross
            //if stoneIndex == stones.length
                //-1 all stone
                //stoneIndex = 0;
                //count the number of friends crossed
            //else if encounter 0
                //while
                    //if stones[stoneIndex++] == 0
                        //count++
                    //else if count == k
                        //unableToCross
                    //else
                        //stoneIndex++
                        //count = 0
                        //break
            //else
                //stoneIndex++;
        // System.out.println(findMax(stones, k, 1, stones.length, stones.length/2 ));
        int MAX = 0;
        int MIN = 200_000_000;
        for(int e : stones){
            if(e > MAX)
                MAX = e;
            if(e < MIN)
                MIN = e;
        }
        answer = findMax(stones,k,MIN,MAX,(MIN+MAX)/2);
        //   for(int i =0; i< stones.length; i++){
        //     stones[i]-= 4;
        // }
        // System.out.println(isCrossable(stones,k));
        return answer;
    }

    public int findMax(int [] stones, int k, int minValue, int maxValue, int midValue){
        int MAX = midValue;
        if(stones.length == k)
            return maxValue;
        if(k == 1)
            return minValue;
        if(minValue <= 0 || maxValue >200_000_000 || stones.length >200_000 || k <=0)
            return 0;
        if( minValue >= midValue || maxValue <= midValue){
            if(isCrossable(stones,k,maxValue))
                return maxValue;
            return MAX;
        }
        if(isCrossable(stones, k, MAX)){
            MAX = findMax(stones, k, midValue, maxValue, (maxValue+midValue)/2);
        }
        else{
            // System.out.println("MidValue is "+ midValue + " " +minValue + " " +maxValue);
            MAX = findMax(stones, k, minValue, midValue, (midValue+minValue)/2);
        }
        return MAX;
    }

    public boolean isCrossable( int[] stones, int k, int n ){
        int si = 0;
        int count = 0;
        if(k >= stones.length)
            return true;
        while (true){
            if( si >= stones.length ){
                // System.out.println(si);
                return true;
            }
            if( stones[si++]-(n-1) <= 0){
                count++;
                while(true){
                    if( si == stones.length ){
                        return true;
                    }
                    if(stones[si++]-(n-1) <= 0){
                        count++;
                    }
                    else{
                        count = 0;
                        break;
                    }
                    if( count >= k ){
                        return false;
                    }
                }
            }
        }
    }
}
