class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        
       

        for(int i=0; i<asteroids.length; i++){
            int astr = asteroids[i];
            boolean destroyed = false;
            while(!stack.isEmpty() && stack.peek()>0 && astr<0){
                if(stack.peek()<Math.abs(astr)){
                    stack.pop();
                    continue;
                }
                else if(stack.peek() == Math.abs(astr)){
                    stack.pop();
                }

                destroyed = true;
                break;
            }
            if(!destroyed){
                stack.push(astr);
            }
            
        }
        int[] array = new int[stack.size()];
        for (int i=array.length-1; i>=0; i--) {
        array[i] = stack.pop(); 
        }
        return array;
    }
}