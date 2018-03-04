import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

// Definition for binary tree with next pointer.
class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;

    TreeLinkNode(int x) {
        val = x;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Main {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        if (prices.length == 1) return prices[0];
        int curProfit = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                curProfit += prices[i + 1] - prices[i];
            }
        }
        return curProfit + prices[0];
    }

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int max = 0;
        int min = prices[0];
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            //保证最小值是之前算出来的
            max = Math.max(max, prices[i] - min);
        }
        return max;
    }

    public int reverse(int x) {
        int reverse = 0;
        while (x / 10 != 0) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        reverse = reverse * 10 + x % 10;

        return reverse;
    }

    public int maxArea(int[] height) {
        int s, maxArea = 0;
        int l = 0, r = height.length - 1;
        while (l < r) {
            s = (r - l) * min(height[l], height[r]);
            if (s > maxArea)
                maxArea = s;
            if (height[l] > height[r])
                r--;
            else
                l++;
        }
        return maxArea;
    }

    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num - values[i] > 0)
                num -= values[i];
            roman.append(strs[i]);
        }
        return roman.toString();
    }

    public void rotate(int[][] matrix) {
        if (matrix == null) return;
        int n = matrix.length;
        int[][] matrixTemp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrixTemp[i][j] = matrix[n - j - 1][i];
            }
        }
        matrix = matrixTemp;
    }

    public int maxSubArray(int[] A) {
//        int curMax = Integer.MIN_VALUE;;
//        for(int i = 0; i < A.length; i ++) {
//            int sum = A[i];
//            curMax = curMax > sum ? curMax : sum;
//            for(int j = i + 1;j < A.length; j++) {
//                sum += A[j];
//                curMax = curMax > sum ? curMax : sum;
//            }
//        }
//        return curMax
        int result = Integer.MIN_VALUE, f = 0;
        for (int i = 0; i < A.length; ++i) {
            f = max(f + A[i], A[i]);
            result = max(result, f);
        }
        return result;
    }

    public int climbStairs(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
//        return climbStairs(n - 1) + climbStairs(n - 2);
        int[] s = new int[n + 1];
        s[0] = 0;
        s[1] = 1;
        s[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            s[i] = s[i - 1] + s[i - 2];
        }
        return s[n];
    }

    public void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> qs = new LinkedList<>();
        TreeLinkNode tail = root;
        if (root != null) {
            qs.offer(root);
        } else {
            return;
        }

        while (!qs.isEmpty()) {
            TreeLinkNode c = qs.poll();
            if (c.left != null) {
                qs.offer(c.left);
            }
            if (c.right != null) {
                qs.offer(c.right);
            }
            if (c == tail || qs.isEmpty()) {
                c.next = null;
                tail = c.right;
                System.out.println(c.val + "->null");
            } else {
                c.next = qs.element();
                System.out.println(c.val + "->" + c.next.val);
            }
        }
    }

    public boolean isPalindrome(int x) {
        if (x < 10) return x >= 0;
        int base = 1;
        for (int i = x; i > 9; base *= 10, i /= 10) ;
        for (; x > 0 && base > 0; base /= 100) {
            int head = x / base, tail = x % 10;
            if (head != tail) return false;
            x %= base;//掐头
            x /= 10;//去尾
        }
        return true;
    }

    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        ArrayList<Integer> values = new ArrayList<>();
        while (root != null || !s.isEmpty()) {
            while (root != null) {
                s.push(root);//先访问再入栈
                root = root.left;
            }
            root = s.pop();
            values.add(root.val);
            root = root.right;//如果是null，出栈并处理右子树
        }
        return values;
    }
    public int searchInsert(int[] A, int target) {

        int l = 0, r = A.length - 1;
        if(r == -1) {
            return 0;
        }
        int mid;
        while(r - l > 0) {
            mid = (l + r) / 2;
            if(A[mid] > target) {
                r = mid - 1;
            } else if(A[mid] == target) {
                return mid;
            } else {
                l = mid + 1;
            }

        }
        if(r != 0) {
            return r + 1;
        } else {
            return 0;
        }

    }

    public static void main(String[] args) {
        // write your code here
//        TreeLinkNode root = new TreeLinkNode(1);
//        root.left = new TreeLinkNode(2);
//        root.right = new TreeLinkNode(3);
//        root.left.left = new TreeLinkNode(4);
//        root.left.right = null;
//        root.left.left.left = null;
//        root.left.left.right = null;
//        root.right.left = new TreeLinkNode(5);
//        root.right.right = null;
//        root.right.left.left = null;
//        root.right.left.right = null;
//        new Main().connect(root);
        int[] a = new int[]{2, 3, 5, 6};
        System.out.println(new Main().searchInsert(a,1 ));
    }
}
