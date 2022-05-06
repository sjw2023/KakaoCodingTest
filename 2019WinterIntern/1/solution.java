class Solution {
    public int solution(int[][] board, int[] moves) {
        //get N of board.
        //var size = board.length();
        var size = board.length;
        //board size check.
        if ( size > 30 || size < 5 ){
            return 0;
        }
        //---- int[] dollStack = new int [size]; basket must big enough for all dolls
        //--- maximum nnumber of dolls are size*size not size*2
        int[] dollStack = new int [size*size];
        int index = 0;
        int answer = 0;
        //for each moves
        //---- wrong foreach syntaxfor(m:moves)
        for( int m : moves ){
            //i means column, for each row of ith column find the first doll
            //if found then pick the doll and mark as empty the doll's location and skip to the next move
            for( int row = 0; row < size; row++ ){
                if(board[row][m-1] != 0){
                    //if doll stack is empty just put
                    if(index == 0){
                        dollStack[index++] = board[row][m-1];
                        System.out.println(dollStack[index-1]);
                        board[row][m-1] = 0;
                        break;
                    }
                    //if doll stack is not empty comapare with a doll at the last position of stack and doll
                    //that we just pick
                    //check if a both are same type
                    else{
                        //if doll in last position is same type of doll that we picked from board
                        //raise answer by 1 and remove doll from stack
                        if(dollStack[index-1] == board[row][m-1]){
                            System.out.println(dollStack[index-1]);
                            //----I wasn't marking the board with 0 after picking the doll.
                            board[row][m-1] = 0;
                            //1)----index--; I didn't think that I need to set 0, big mistake
                            //2)----dollStack[index--] = 0; index should point the last element of stack,
                            //----I need decremennt the index before using it
                            dollStack[--index] = 0;
                            //----answer++ should increased by 2 sinnce 2 dolls are removed when they are same.
                            answer+=2;
                            break;
                        }
                        //if dolls are not same type just put doll into stack
                        else{
                            dollStack[index++] = board[row][m-1];
                            System.out.println(dollStack[index-1]);
                            //----I wasn't marking the board with 0 after picking the doll.
                            board[row][m-1]= 0;
                            break;
                        }
                    }
                }
                //Doll index check.
                else if(board[row][m-1] > 100)
                {
                    return 0;
                }

            }

        }

        return answer;
    }
}
