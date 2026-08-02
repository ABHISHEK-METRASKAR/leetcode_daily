class Solution {
    public boolean judgeCircle(String moves) {
       int x = 0;
       int y = 0;

       for(int i=0; i<moves.length(); i++){
            char ch = moves.charAt(i);

            switch(ch){
                case 'R' : y++; break;
                case 'L' : y--; break;
                case 'U' : x++; break;
                case 'D' : x--; break;
            }
       }
       return x==0 && y==0; 
    }
}