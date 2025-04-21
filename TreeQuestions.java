public class TreeQuestions {

    //  * Definition for a binary tree node.
 public static class TreeNode {
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


// 1. Invert Binary Tree
  public static TreeNode invertTree(TreeNode root) { 

      if(root == null)
          return null;

      TreeNode temp = root.left;
      root.left = root.right;
      root.right = temp;

      invertTree(root.left);
      invertTree(root.right);    
      
      return root;
  }

// 2. Maximum Depth of Binary Tree
  public static int maxDepth(TreeNode root) {

    // See the max left and the max right and take the max of them + 1.

    if (root == null)
        return 0;
    
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    
}

// 3. Diameter of Binary Tree

int diameter = 0;

public int diameterOfBinaryTree(TreeNode root) {
    height(root);
    return diameter - 1 ;
}

int height(TreeNode node) {
    if(node == null) {
        return 0;
    }

    int leftHeight = height(node.left);
    int rightHeight = height(node.right);

    int dia = leftHeight + rightHeight + 1;
    diameter = Math.max(diameter, dia);

    return Math.max(leftHeight, rightHeight) + 1;

}



// 4. Balanced Binary Tree

    public static boolean isBalanced(TreeNode node) {
        if (node == null)
            return true;

        int lh = heightBalanced(node.left);
        int rh = heightBalanced(node.right);

        return Math.abs(lh - rh) <= 1 && isBalanced(node.left) && isBalanced(node.right);
    }

    public static int heightBalanced(TreeNode node) {
    if (node == null)
    return 0;
    return Math.max(heightBalanced(node.left), heightBalanced(node.right)) + 1;
    }

    // 5. Same Tree
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if(p == null && q == null)
            return true;
            
        if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
        
    }

// ------------------------------------------------------------


    // Helper method to print tree in level order
    public static void printTree(TreeNode root) {
        if (root == null) return;
        
        System.out.println("Tree in level order:");
        System.out.println(root.val);
        if (root.left != null) System.out.println("Left: " + root.left.val);
        if (root.right != null) System.out.println("Right: " + root.right.val);
        
        if (root.left != null) {
            System.out.println("\nLeft subtree:");
            printTree(root.left);
        }
        if (root.right != null) {
            System.out.println("\nRight subtree:");
            printTree(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        root.left.left.left = new TreeNode(10);

        // System.out.println("Original tree:");
        // printTree(root);
        
        // TreeNode inverted = invertTree(root);
        
        // System.out.println("\nInverted tree:");
        // printTree(inverted);

        System.out.println(maxDepth(root));
    
    }
}