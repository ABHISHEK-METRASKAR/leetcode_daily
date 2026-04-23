class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for(int i=1; i<=n; i++){
            list.add(i);
        }

        k--;
        int fact = 1;
        for(int i=1; i<n; i++){
            fact *= i;
        }
        StringBuilder result = new StringBuilder();
        for(int i=n; i>0; i--){
            int index = k/fact;
            result.append(list.get(index));
            list.remove(index);

            if(i>1){
               k %= fact;
               fact /= (i-1);            
            }
        }

        
        return result.toString();
    }
}