package leetcode;
//二叉树最大深度
public class Maxdeepth {
    public static void main(String[] args) {

    }
    //深度优先，二叉树的最大深度=max(左子树，右子树)
    public int maxDepth(TreeNode root) {
        if (root==null){
            return 0;
        }
        else {
            int left= maxDepth(root.left);
            int right= maxDepth(root.right);
            return Math.max(left,right)+1;
        }
    }


    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
     }
  }
}
