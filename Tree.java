package _2025;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Deque;
import java.util.Stack;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;



public class Tree {

    static class Node {
        int data;
        Node left;
        Node right;
        
        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    public Node addNode(int data, Node head) {
        Node tempHead = head;
        Node n = new Node(data);
        if (head == null) {
            head = n;
            return head;
        }
        Node prev = null;
        while (head != null) {
            prev = head;
            if (head.data < data) {
                head = head.right;
            } else {
                head = head.left;
            }
        }
        if (prev.data < data) {
            prev.right = n;
        } else {
            prev.left = n;
        }
        return tempHead;
    }

    public void levelOrder(Node root){
        if(root == null){
            System.out.println("Please enter a valid tree!");
            return;
        }
        Queue<Node> queue = new LinkedList<Node>();
    
        queue.offer(root);
    
        System.out.println();
    
        while(queue.size() > 0){
    
            root = queue.poll();
    
            System.out.print(root.data + " ");
    
            if(root.left != null){
                queue.add(root.left);
            }
    
            if(root.right != null){
                queue.add(root.right);
            }
    
        }
    }

    public void postOrderItr(Node root){
        Deque<Node> stack1 = new LinkedList<Node>();
        Deque<Node> stack2 = new LinkedList<Node>();

        stack1.addFirst(root);


        while(!stack1.isEmpty()){

            root = stack1.pollFirst();
            if(root.left != null){
                stack1.addFirst(root.left);
            }
            if(root.right != null){
                stack1.addFirst(root.right);
            }
            stack2.addFirst(root);
        }

        while(!stack2.isEmpty()){

            System.out.print(stack2.pollFirst().data + " ");

        }
    }

    public void inorderItr(Node root){

        Deque<Node> stack = new LinkedList<Node>();

        Node node = root;

        while(true){

            if(node != null){

                stack.addFirst(node);

                node = node.left;
            }

            else{
                if(stack.isEmpty()){
                    break;
                }

                node = stack.pollFirst();

                System.out.print(node.data + "  ");

                node = node.right;
            }
        }
    }

    public void preOrderItr(Node root){

        Deque<Node> stack = new LinkedList<Node>();

        stack.addFirst(root);

        while(!stack.isEmpty()){

            root = stack.pollFirst();

            System.out.print(root.data + " ");

            if(root.right != null){
                stack.addFirst(root.right);
            }

            if(root.left!= null){
                stack.addFirst(root.left);
            }
        }
    }
    
        public void reverseLevelOrderTraversal(Node root){
            if(root == null){
                return;
            }
            Queue<Node> q = new LinkedList<>();
            Stack<Node> s = new Stack<>();

            q.offer(root);

            while(!q.isEmpty()){
                root = q.poll();
                if(root.right != null){
                    q.offer(root.right);
                }
                if(root.left != null){
                    q.offer(root.left);
                }
                s.push(root);
            }

            // 2. While-loop

            // His solution for printing:
       while(!s.isEmpty()){
            // His code without -->
           System.out.print(s.pop().data + " ");
            //    Mine with -->
//            System.out.print(s.pop().data + " --> ");
       }

        }

        public Node lca(Node root, Node n1, Node n2){
            if(root == null){
                return null;
            }
    
            // 1.Here is Base Case --> If the Root is one of the nodes
            // To find Ancestor --> Then it's the Ancestor.
            if(root == n1 || root == n2){
                return root;
            }
    
            // Get the Number if it's in the Left of Right of the Node.
            Node left = lca(root.left, n1, n2);
            Node right = lca(root.right, n1, n2);
    
            // Return the Root if Left of Right of Node is Not NULL.
            if(left != null && right != null){
                return root;
            }
    
            // Otherwise Return Left or Right of this Node.
            return left != null ? left : right;
        }

        public Node lowestCommonAncestor(Node root, int p, int q) {
            if (root.data > Math.max(p, q)) {
                return lowestCommonAncestor(root.left, p, q);
            } else if (root.data < Math.min(p, q)) {
                return lowestCommonAncestor(root.right, p, q);
            } else {
                return root;
            }
        }

        public static boolean findTarget(Node root, int k) {
            HashSet<Integer> set = new HashSet<>();
            return helper(root, k, set);
        }
    
        private static boolean helper(Node node, int k, HashSet<Integer> set) {
            if(node == null) {
                return false;
            }
            if(set.contains(k - node.data)) {
                return true;
            }
    
            set.add(node.data);
            return helper(node.left, k, set) || helper(node.right, k , set);
        }

        // ------------------------------------------------------------------------------------------------

        // 9. Kth Smallest Element in a BST:

        public int kthSmallest(Node root, int k) {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            helperKthSmallest(root, minHeap, k);
    
            // remove k elements
            int ans = 0;
            for(int i=0; i<k; i++) {
                ans = minHeap.poll();
            }
            return ans;
        }
    
        private void helperKthSmallest(Node node, PriorityQueue<Integer> minHeap, int k) {
            if (node == null) {
                return;
            }
    
            helperKthSmallest(node.left, minHeap, k);
    
            minHeap.offer(node.data);
    
            helperKthSmallest(node.right, minHeap, k);
        }

        private int k;
        private int ans;

        public int kthSmallestWithoutHeap(Node root, int k) {
            this.k = k;
            helper(root);
            return ans;
        }
    
        private void helper(Node node) {
            if (node == null) {
                return;
            }
    
            helper(node.left);
    
            k--;
            if(k==0) {
                ans = node.data;
                return;
            }
    
            helper(node.right);
        }

        // ------------------------------------------------------------------------------------------------

        // 10. Kth Largest Element in a BST:
        
        public int kthLargest(Node root, int k) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            helperKthLargest(root, maxHeap, k);

            // remove k elements
            int ans = 0;
            for(int i=0; i<k; i++) {
                ans = maxHeap.poll();
            }
            return ans;
        }

        private void helperKthLargest(Node node, PriorityQueue<Integer> maxHeap, int k) {
            if (node == null) {
                return;
            }

            helperKthLargest(node.left, maxHeap, k);
            maxHeap.offer(node.data);
            helperKthLargest(node.right, maxHeap, k);
        }

        // ------------------------------------------------------------------------------------------------
        // 11.Find Minimum and Maximum of BST --> [[  it's a Skewed Tree  ]]:

        public int findMin(Node root) {
            if (root == null) {
                return Integer.MAX_VALUE;
            }
            while (root.left != null) {
                root = root.left;
            }
            return root.data;
        }
    
        public int findMax(Node root) {
            if (root == null) {
                return Integer.MIN_VALUE;
            }
            while (root.right != null) {
                root = root.right;
            }
            return root.data;
        }

        // ------------------------------------------------------------------------------------------------

        // 12.Largest BST in BT:

        public int largestBST(Node root) {
            if(root == null){
                return 0;
            }
            if(isBST(root)){
                return size(root);
            }
            return Math.max(largestBST(root.left), largestBST(root.right));
        }   

        private boolean isBST(Node root){
            return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private boolean isBSTUtil(Node node, int min, int max){
            if(node == null){
                return true;
            }
            if(node.data <= min || node.data >= max){
                return false;
            }
            return isBSTUtil(node.left, min, node.data) && isBSTUtil(node.right, node.data, max);
            }

        private int size(Node root){
            if(root == null){
                return 0;
            }
            return size(root.left) + size(root.right) + 1;
        }
        
        
        //  ------------------

        // 13.Convert Binary Tree to Doubly Linked List:

        public Node convertToDLL(Node root) {
            if (root == null) return null;
            
            Node head = convertToDLL(root.left);
            
            if (head == null) {
                head = root;
            } else {
                Node temp = head;
                while (temp.right != null) {
                    temp = temp.right;
                }
                temp.right = root;
                root.left = temp;
            }
            
            convertToDLL(root.right);
            return head;
        }

        // ------------------------------------------------------------------------------------------------

        // 14. Morris Inorder Tree Traversal:

        public void morrisInorder(Node root){
            Node current = root;
            while(current != null){
                if(current.left == null){
                    System.out.print(current.data + " ");
                    current = current.right;
                }
                else{
                    Node predecessor = current.left;    
                    while(predecessor.right != null && predecessor.right != current){
                        predecessor = predecessor.right;
                    }
                    if(predecessor.right == null){
                        predecessor.right = current;
                        current = current.left;
                    }
                    else{
                        predecessor.right = null;
                        System.out.print(current.data + " ");
                        current = current.right;
                    }
                        }
            }   
        }

        // ------------------------------------------------------------------------------------------------

        // 15. Correct Binary Tree That Has Two Nodes Swapped:

        Node first;
        Node second;
        Node prev;

      public void helperIOT(Node root) {
          iot(root);

          // swap
          int temp = first.data;
          first.data = second.data;
          second.data = temp;
      }

      private void iot(Node node) {
          if (node == null) {
              return;
          }

          iot(node.left);

          if (prev != null && prev.data > node.data) {
              if (first == null) {
                  first = prev;
              }
              second = node;
          }

          prev = node;

          iot(node.right);
      }

      public void printIOT(Node node) {
        if (node == null) {
            return;
        }
        printIOT(node.left);
        if (node == first || node == second) {
                System.out.print("[" + node.data + "] ");
            } else {
                System.out.print(node.data + " ");
            }
            printIOT(node.right);
        }

        // ------------------------------------------------------------------------------------------------

        // 16. Convert Sorted Array to BST:

        public Node sortedArrayToBST(int[] nums) {
            return helperOfSortedArrayToBST(nums, 0, nums.length - 1);
        }
    
        private Node helperOfSortedArrayToBST(int[] nums, int left, int right) {
            if (left > right) return null;
            
            int mid = left + (right - left) / 2;
            Node root = new Node(nums[mid]);
            
            root.left = helperOfSortedArrayToBST(nums, left, mid - 1);
            root.right = helperOfSortedArrayToBST(nums, mid + 1, right);
            
            return root;
        }
    
        // ------------------------------------------------------------------------------------------------

        // 17. Find Successor of a Node in Level Order:

        public Node findSuccessor(Node root, int key){
            if (root == null) {
                return null;
            }
    
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
    
            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                Node currentNode = queue.poll();
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
    
                if (currentNode.data == key) {
                    break;
                }
    
            }
            return queue.poll();
        }

        // ------------------------------------------------------------------------------------------------

        // 18. Find Successor of a Node in BST:

        public Node findSuccessorBST(Node root, int key) {
                Node successor = null;
                while (root != null) {
                    if (root.data > key) {
                        successor = root;
                        root = root.left;
                    } else {
                        root = root.right;
                    }
                }
                return successor;
            }

        // ------------------------------------------------------------------------------------------------

        // 19. Zigzag Level Order Traversal:

        public List<List<Integer>> zigzagLevelOrder(Node root) {
            List<List<Integer>> result = new ArrayList<>();
    
            if (root == null) {
                return result;
            }
    
            Deque<Node> queue = new LinkedList<>();
            queue.add(root);
    
            boolean reverse = false;
    
            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                List<Integer> currentLevel = new ArrayList<>(levelSize);
                for (int i=0; i < levelSize; i++) {
                    if (!reverse) {
                        Node currentNode = queue.pollFirst();
                        currentLevel.add(currentNode.data);
                        if (currentNode.left != null) {
                            queue.addLast(currentNode.left);
                        }
                        if (currentNode.right != null) {
                            queue.addLast(currentNode.right);
                        }
                    } else {
                        Node currentNode = queue.pollLast();
                        currentLevel.add(currentNode.data);
                        if (currentNode.right != null) {
                            queue.addFirst(currentNode.right);
                        }
                        if (currentNode.left != null) {
                            queue.addFirst(currentNode.left);
                        }
                    } // Ends of Else-Statement
    
                } // Ends of For-Loop
    
                reverse = !reverse;
                result.add(currentLevel);
    
            } // Ends of -While-Loop
    
            return result;
        }

        // -------------

        // 20. Sum Root to Leaf Numbers
           static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            
            public TreeNode(int val) {
                this.val = val;
                this.left = null;
                this.right = null;
            }
        }

        public int sumNumbers(TreeNode root) {
            return helperSumRootToLeaf(root, 0);
        }

        private int helperSumRootToLeaf(TreeNode node, int sum) {
            if(node == null) {
                return 0;
            }
            sum = sum * 10 + node.val;
            if (node.left == null && node.right == null) {
                return sum;
            }
            return helperSumRootToLeaf(node.left, sum) + helperSumRootToLeaf(node.right, sum);
        }

        // ------------------------------------

          // 21. Maximum Path Sum
          private int maxPathSumAns = Integer.MIN_VALUE;
        
          public int maxPathSum(TreeNode root) {
              helperMaxPathSum(root);
              return maxPathSumAns;
          }
          
          private int helperMaxPathSum(TreeNode node) {
              if(node == null) {
                  return 0;
              }
  
              int left = helperMaxPathSum(node.left);
              int right = helperMaxPathSum(node.right);
  
              left = Math.max(0, left);
              right = Math.max(0, right);
  
              int pathSum = left + right + node.val;
  
              maxPathSumAns = Math.max(maxPathSumAns, pathSum);
  
              return Math.max(left, right) + node.val;
          }

        //   ------------------------------------------------------------------------------------------------

          // 22. Find Path in Tree
          public boolean findPath(TreeNode node, int[] arr) {
            if (node == null) {
                return arr.length == 0;
            }
            return helperFindPath(node, arr, 0);
        }

        private boolean helperFindPath(TreeNode node, int[] arr, int index) {
            if(node == null) {
                return false;
            }
            if(index >= arr.length || node.val != arr[index]) {
                return false;
            }
            if(node.left == null && node.right == null && index == arr.length - 1) {
                return true;
            } 
            return helperFindPath(node.left, arr, index + 1) || 
                   helperFindPath(node.right, arr, index + 1);
        }

        // ------------------------------------------------------------------------------------------------
        
        public Node sortedListToBST(Node head) {
            if (head == null) return null;
            
            // Convert tree to sorted list
            List<Integer> list = new ArrayList<>();
            inorderToList(head, list);
            
            // Convert sorted list to balanced BST
            return buildBalancedBST(list, 0, list.size() - 1);
        }
        
        private void inorderToList(Node node, List<Integer> list) {
            if (node == null) return;
            inorderToList(node.left, list);
            list.add(node.data);
            inorderToList(node.right, list);
        }
        
        private Node buildBalancedBST(List<Integer> list, int start, int end) {
            if (start > end) return null;
            
            int mid = start + (end - start) / 2;
            Node root = new Node(list.get(mid));
            
            root.left = buildBalancedBST(list, start, mid - 1);
            root.right = buildBalancedBST(list, mid + 1, end);
            
            return root;
        }

        // ------------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------------
        // Main Function:
        // ------------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------------

    public static void main(String args[]){
        Tree levelOrder = new Tree();
        // levelOrder.levelOrder(null);
        // Node head = null;
        // head = levelOrder.addNode(10, head);
        // head = levelOrder.addNode(15, head);
        // head = levelOrder.addNode(5, head);
        // head = levelOrder.addNode(7, head);
        // head = levelOrder.addNode(19, head);
        // head = levelOrder.addNode(20, head);
        // head = levelOrder.addNode(-1, head);

//        1. ( Level )  Traversing:
//        System.out.print("Level: ");
        // levelOrder.levelOrder(head);
        // output: 10 5 -1 7 15 19 20 
//        2. ( Post ) Order Traversing [ Left - Right - Root ]:
//        levelOrder.postOrderItr(head);
//         output: 7 5 -1 20 19 15 10 
//        3. ( In ) Order Traversing [ Left - Root - Right ]:
//        levelOrder.inorderItr(head);
//         output: -1 5 7 10 15 19 20 
//        4. ( Pre ) Order Traversing [ Root - Left - Right ]:
//        System.out.println("\nPre:");
//        levelOrder.preOrderItr(head);
//         output: 10 5 -1 7 15 19 20 
//        5. ( Reverse Level ) Order Traversing [ Root - Right - Left ]:
        // levelOrder.reverseLevelOrderTraversal(head);
        // output: 10 5 -1 7 15 19 20 

// ------------------------------------------------------------------------------------------------
        // 6. Lowest Common Ancestor for Binary Tree:
        // Node root = new Node(3);
        // root.left = new Node(6);
        // root.right = new Node(8);
        // root.left.left = new Node(2);
        // root.left.right = new Node(11);
        // root.left.right.left = new Node(9);
        // root.left.right.right = new Node(5);

        // Node result = levelOrder.lca(root, root.left.left , root.left.right.right);
        // System.out.println("Lowest Common Ancestor of " + "2" +" and " + "5" + " is: " + result.data);

        // output: Lowest Common Ancestor of 2 and 5 is: 6

// ------------------------------------------------------------------------------------------------
        // 7. Check if the Binary Tree is a Binary Search Tree:

        // Node root = null;
        // root = levelOrder.addNode(10, root);
        // root = levelOrder.addNode(30, root);
        // root = levelOrder.addNode(25, root);
        // root = levelOrder.addNode(35, root);
        // root = levelOrder.addNode(-10, root);
        // root = levelOrder.addNode(0, root);
        // root = levelOrder.addNode(-20, root);    
        // root = levelOrder.addNode(-15, root);
        // root = levelOrder.addNode(45, root);

        // int p = 35;
        // int q = -15;

        // Node result = levelOrder.lowestCommonAncestor(root, p, q);

        // System.out.println("Lowest Common Ancestor of " + p + " and " + q + " is: " + result.data);
        
        // output: Lowest Common Ancestor of 35 and -15 is: 10
// ------------------------------------------------------------------------------------------------
        // 8. Find Target in Binary Tree:

        // Node root = new Node(5);
        // root.left=new Node(3);
        // root.left.left=new Node(2);
        // root.left.right=new Node(4);

        // root.right = new Node(6);
        // root.right.right=new Node(7);

        // System.out.println(findTarget(root, 9));
        
        // output: true

        // ------------------------------------------------------------------------------------------------

        // 9. Kth Smallest Element in a BST:

        // Node root = new Node(5);
        // root.left=new Node(3);
        // root.left.left=new Node(2);
        // root.left.right=new Node(4); 
        // root.right = new Node(6);
        // root.right.right=new Node(7);

        // System.out.println(levelOrder.kthSmallest(root, 3));

        // output: 4

        // System.out.println(levelOrder.kthSmallestWithoutHeap(root, 3));

        // output: 4

        // ------------------------------------------------------------------------------------------------

        // 10. Kth Largest Element in a BST:

        // System.out.println(levelOrder.kthLargest(root, 3));  // Should print 5

        // output: 5

        // ------------------------------------------------------------------------------------------------


        // 11.Find Minimum and Maximum of BST --> [[  it's a Skewed Tree  ]]:

        // System.out.println(levelOrder.findMin(head));

        // output: -1

        // System.out.println(levelOrder.findMax(head));

        // output: 20

        // ------------------------------------------------------------------------------------------------

        // 12.Largest BST in BT:

        // System.out.println(levelOrder.largestBST(head));

        // output: 5

        // ------------------------------------------------------------------------------------------------

        // 13.Convert Binary Tree to Doubly Linked List:

        // Node result = levelOrder.convertToDLL(head);
        // while(result != null){
        //     if(result.right == null){
        //         System.out.print(result.data);
        //     }
        //     else{
        //         System.out.print(result.data + " || ");
        //     }
        //     result = result.right;
        // }

        // output: -1 || 5 || 7 || 10 || 15 || 19 || 20
        
        // ------------------------------------------------------------------------------------------------

        // 14. Morris Inorder Tree Traversal:

        // levelOrder.morrisInorder(head);

        // output: -1 5 7 10 15 19 20

        // ------------------------------------------------------------------------------------------------

        // 15. Correct Binary Tree That Has Two Nodes Swapped:

        // levelOrder.printIOT(head);

        // output: -1 5 7 10 15 19 20
        // ------------------------------------------------------------------------------------------------

        // 16. Convert Sorted Array to BST:

        // int[] arr = {-10, -3, 0, 5, 9};
        // Node result = levelOrder.sortedArrayToBST(arr);
        // levelOrder.levelOrder(result);

        // output: 0 -3 9 -10 5

        // ------------------------------------------------------------------------------------------------

        // 17. Find Successor of a Node in Level Order:

        // Node result = levelOrder.findSuccessor(head, 10);
        // System.out.println(result.data);

        // output: 5

        // ------------------------------------------------------------------------------------------------

        // 18. Find Successor of a Node in BST:

        // Node result = levelOrder.findSuccessorBST(head, 10);
        // System.out.println(result.data);

        // output: 15

        // ------------------------------------------------------------------------------------------------


        // 19. Zigzag Level Order Traversal:

        // List<List<Integer>> result = levelOrder.zigzagLevelOrder(head);
        // System.out.println(result);

        // System.out.println(levelOrder.zigzagLevelOrder(head)); 

        // output: [[10], [15, 5], [20, 7, -1]]

        // ------------------------------------------------------------------------------------------------

        // 20. Sum Root to Leaf Numbers Example


        // TreeNode root = new TreeNode(5);
        // root.left = new TreeNode(4);
        // root.right = new TreeNode(8);
        // root.left.left = new TreeNode(11);
        // root.right.left = new TreeNode(13);
        // root.right.right = new TreeNode(4);
        // root.left.left.left = new TreeNode(7);
        // root.left.left.right = new TreeNode(2);
        // root.right.right.right = new TreeNode(1);
        
        // System.out.println("Sum of all root-to-leaf numbers: " + levelOrder.sumNumbers(root));
        // Output: 17463

        // ------------------------------------------------------------------------------------------------

      

        // 21. Maximum Path Sum Example
        // TreeNode root2 = new TreeNode(5);
        // root2.left = new TreeNode(4);
        // root2.right = new TreeNode(8);
        // root2.left.left = new TreeNode(11);
        // root2.right.left = new TreeNode(13);
        // root2.right.right = new TreeNode(4);
        // root2.left.left.left = new TreeNode(7);
        // root2.left.left.right = new TreeNode(2);
        // root2.right.right.right = new TreeNode(1);
        
        // System.out.println("Maximum path sum: " + levelOrder.maxPathSum(root2));
        // Output: 48 (Path: 11 -> 4 -> 5 -> 8 -> 13)

        // ------------------------------------------------------------------------------------------------

      

        // 22. Find Path in Tree Example
        // TreeNode root = new TreeNode(5);
        // root.left = new TreeNode(4);
        // root.right = new TreeNode(8);
        // root.left.left = new TreeNode(11);
        // root.right.left = new TreeNode(13);
        // root.right.right = new TreeNode(4);
        // root.left.left.left = new TreeNode(7);
        // root.left.left.right = new TreeNode(2);
        // root.right.right.right = new TreeNode(1);

        // int[] path1 = {5, 4, 11, 7};  // Valid path
        // int[] path2 = {5, 8, 4, 1};   // Valid path
        // int[] path3 = {5, 4, 11, 2};  // Valid path
        // int[] path4 = {5, 8, 13};     // Valid path
        // int[] path5 = {5, 4, 11, 3};  // Invalid path

        // System.out.println("Path [5,4,11,7] exists: " + levelOrder.findPath(root, path1));
        // System.out.println("Path [5,8,4,1] exists: " + levelOrder.findPath(root, path2));
        // System.out.println("Path [5,4,11,2] exists: " + levelOrder.findPath(root, path3));
        // System.out.println("Path [5,8,13] exists: " + levelOrder.findPath(root, path4));
        // System.out.println("Path [5,4,11,3] exists: " + levelOrder.findPath(root, path5));

        // output:
        // Path [5,4,11,7] exists: true
        // Path [5,8,4,1] exists: true
        // Path [5,4,11,2] exists: true
        // Path [5,8,13] exists: true
        // Path [5,4,11,3] exists: false

        // ------------------------------------------------------------------------------------------------

        // 23. Convert Sorted List to BST

        Node head = null;
        head = levelOrder.addNode(10, head);
        head = levelOrder.addNode(15, head);
        head = levelOrder.addNode(5, head);
        head = levelOrder.addNode(7, head);
        head = levelOrder.addNode(19, head);
        head = levelOrder.addNode(20, head);
        head = levelOrder.addNode(-1, head);

        Node result = levelOrder.sortedListToBST(head);
        levelOrder.levelOrder(result);

        // output: 10 5 -1 7 15 19 20
        
        

    }

  

}   

