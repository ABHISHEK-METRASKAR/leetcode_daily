/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){  //base case 
            return 0;
        }

        int leftDepth = maxDepth(root.left); // recursice call
        int rightDepth = maxDepth(root.right);

        return 1+Math.max(leftDepth,rightDepth);
    }
}