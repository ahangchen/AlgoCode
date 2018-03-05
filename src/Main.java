import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import static java.util.Arrays.sort;

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

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
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
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] strs = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        StringBuilder roman = new StringBuilder();
        for(int i=0;i<values.length;i++) {
            while(num - values[i] >= 0) {
                num -= values[i];
                roman.append(strs[i]);
            }
        }
        return roman.toString();
    }

    public int romanToInt(String s) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int result = 0;
        String[] keys = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        char[] cs = s.toCharArray();
        int ci = 0;
        for(int i = 0; i < keys.length; i ++) {
            // for each key
            char[] ck = keys[i].toCharArray();

            // check key on the start of string, till string exhaust or key string exhaust
            while(ci < cs.length) {
                boolean exhaust = false, match=false;
                // match chars
                if(ck.length > cs.length - ci) {
                    break;
                }
                boolean partMatch = false;
                for(char k : ck) {
                    if(cs[ci] == k) {
                        match = true;
                        partMatch = true;
                        ci ++;
                    } else {
                        if(partMatch) {
                            ci --;
                        }
                        match = false;
                        exhaust = true;
                        break;
                    }
                }
                if(match) {
                    result += values[i];

                }
                if(exhaust) {
                    break;
                }

            }
        }
        return result;
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
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        ArrayList<Integer> values = new ArrayList<>();
        while (root != null || !s.isEmpty()) {
            while (root != null) {
                values.add(root.val);
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
        while(r >= l) {
            mid = (l + r) / 2;
            if(A[mid] > target) {
                r = mid - 1;
            } else if(A[mid] == target) {
                return mid;
            } else {
                l = mid + 1;
            }

        }
        return l;

    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        return Math.abs(leftDepth - rightDepth) < 2 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int uniquePaths(int m, int n) {
//        if(m == 1 || n == 1) return 1;
//        if(m == 0 || n == 0) return 0;
//        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
        int[][] ps = new int[m + 1][n + 1];
        ps[0][0] = 0;
        ps[0][1] = 0;
        ps[1][0] = 0;
        for (int i = 1; i <= m; i++) {
            ps[i][1] = 1;
        }
        for (int j = 1; j <= n; j++) {
            ps[1][j] = 1;
        }
        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                ps[i][j] = ps[i - 1][j] + ps[i][j - 1];
            }
        }
        return ps[m][n];
    }

    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = A[i];
    }

    public void sortColors(int[] A) {
//        sort(A);
        int begin = 0;
        int end = A.length - 1;
        int cur = 0;
        while (cur <= end) {
            // 所有的0放到数组前边
            if (A[cur] == 0) {
                A[cur] = A[begin] ^ A[cur] ^ (A[begin] = A[cur]);
                begin++;
                cur++;
                // 所有2放到数组后边
            } else if (A[cur] == 2) {
                A[cur] = A[end] ^ A[cur] ^ (A[end] = A[cur]);
                end--;
            } else cur++;
        }

    }


    public void pHelp(int n, int x, int y, String s, ArrayList<String> list) {
        // 终止条件
        if (y == n) {
            list.add(s);
        }
        if (x < n) {
            pHelp(n, x + 1, y, s + "(", list);
        }
        // 递归过程中 左括号x的个数必须大于等于右括号个数
        if (x > y) {
            pHelp(n, x, y + 1, s + ")", list);
        }
    }

    public ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> list = new ArrayList<String>();
        pHelp(n, 0, 0, "", list);
        return list;
    }


    public int singleNumber(int[] A) {
        int ones = 0;//记录只出现过1次的bits
        int twos = 0;//记录只出现过2次的bits
        int threes;
        for (int i = 0; i < A.length; i++) {
            int t = A[i];
            twos |= ones & t;//要在更新ones前面更新twos
            ones ^= t;
            threes = ones & twos;//ones和twos中都为1即出现了3次
            ones &= ~threes;//抹去出现了3次的bits
            twos &= ~threes;
        }
        return ones;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode m = new ListNode(0);
        ListNode n = m;
        while (l1 != null) {
            while (l2 != null && l2.val < l1.val) {
                n.next = new ListNode(l2.val);
                n = n.next;
                l2 = l2.next;
            }
            n.next = new ListNode(l1.val);
            n = n.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            n.next = new ListNode(l2.val);
            n = n.next;
            l2 = l2.next;
        }
        return m.next;
    }

    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int j = 1, old = A[0];
        for (int i = 1; i < A.length; i++) {
            if (old != A[i]) {
                A[j] = A[i];
                j++;
                old = A[i];
            }
        }
        return j;
    }



    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int up = 0, down = m - 1, mid = (up + down) / 2;

        while(up <= down) {
            if(matrix[mid][0] > target) {
                // search up
                down = mid - 1;
            } else if(matrix[mid][n-1] >= target) {
                // search in line
                break;
            } else {
                //search down
                up = mid + 1;
            }
            mid = (up + down) / 2;
        }

        if(up <= down) {
            //search in line
            int l = 0, r = n - 1;
            int [] line = matrix[mid];
            mid = (l + r) / 2;
            while(l <= r) {
                if(line[mid] > target) {
                    //search left
                    r = mid - 1;
                } else if(line[mid] == target) {
                    return true;
                } else {
                    //search right
                    l = mid + 1;
                }
                mid = (l + r) / 2;
            }
            return false;
        } else {
            return false;
        }
    }
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n ][ n];
        for(int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                matrix[i][j] = 0;
            }
        }
        int pos_r = 0, pos_c = 0, l = 0,r = 1,u = 0,d = 1, move = 0;
        for(int i = 0; i< n * n; i++) {
            matrix[pos_r][pos_c] = i + 1;
            // cur environment
            if(pos_c == 0 || matrix[pos_r][pos_c - 1] != 0) l = 0; else l = 1;
            if(pos_r == 0 || matrix[pos_r - 1][pos_c] != 0) u = 0; else u = 1;
            if(pos_c == n - 1 || matrix[pos_r][pos_c + 1] != 0) r = 0; else r = 1;
            if(pos_r == n - 1 || matrix[pos_r + 1][pos_c] != 0) d = 0; else d = 1;
            switch(move) {
                case 0 :{
                    if(r == 1) {
                        pos_c ++;
                    } else {
                        pos_r ++;
                        move = 1;
                    }
                    break;
                }
                case 1:{
                    if(d == 1) {
                        pos_r ++;
                    } else {
                        pos_c --;
                        move = 2;
                    }
                    break;
                }
                case 2:{
                    if(l == 1) {
                        pos_c --;
                    } else {
                        pos_r --;
                        move = 3;
                    }
                    break;
                }
                case 3:{
                    if(u == 1) {
                        pos_r --;
                    } else {
                        pos_c ++;
                        move = 0;
                    }
                    break;
                }
            }

        }
        return matrix;
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
//        int[] a = new int[]{2, 3, 5, 6};
//        System.out.println(new Main().searchInsert(a,1 ));
//        System.out.println(new Main().uniquePaths(1,2));
//        int[] A = new int[]{0, 1, 0, 2, 1};
//        new Main().sortColors(A);
//        for (int a : A) {
//            System.out.print(a);
//        }
//        ArrayList<String> ps = new Main().generateParenthesis(4);
//        for (String p : ps) {
//            System.out.println(p);
//        }
//        int[] a = new int[]{};
//        int l = new Main().removeDuplicates(a);
//        for (int i = 0; i < l; i++) {
//            System.out.print(a[i]);
//        }
        int n = 4;
        int[][] m = new Main().generateMatrix(n);
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j ++)
                System.out.print(m[i][j]);
            System.out.println();
        }
    }
}
