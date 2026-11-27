class Solution {
    public boolean canAliceWin(int n) {
        int remove = 10;
        boolean alice = true;

        while(n >= remove){
            n -= remove;
            remove--;

            if(alice){
                alice = false;
            }else{
                alice = true;
            }
        }

        return !alice;
    }
}