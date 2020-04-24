package cn.maaa.test.algorithm;

import cn.maaa.test.Node;

import java.util.Arrays;

/**
 * @author :  mazh
 * @date :  2020/4/22 9:44
 */
public class Leetcode {

    public static void main(String[] args) {
        //System.out.println((int)Math.sqrt(35));
        //System.out.println(test(new int[]{1,1,2,1,1},3));

       // System.out.println(test("Let's take LeetCode contest"));

        //char[] chars = {'h', 'e', 'l', 'l', 'o'};
       // reverseString(chars);
        //Node node = initNode("12345");
        //print(reverseList(node));
       // print(reverseList1(node));

       // System.out.println(singleNumber(new int[]{4,1,2,1,2}));

       // System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
        System.out.println(maxProfit2(new int[]{7,1,5,3,6,4}));

       /* Node common = initNode("24");
        Node headA = new Node(3);
        headA.next = common;
        Node headB = new Node(0);
        headB.next = new Node(9);
        headB.next.next = new Node(1);
        headB.next.next.next = common;
        print(getIntersectionNode(headA,headB));*/
    }


    public static Node getIntersectionNode(Node headA, Node headB) {
        if(headA == null || headB == null)
            return null;
         Node temp1 = headA,temp3 = headA;
         Node temp2 = headB,temp4 = headB;
          while ( true){
              if(temp1 == null && temp2 == null)
                  return null;
              if(temp1 == null ){
                  temp1 = headA;
                  temp2 = temp4 = temp4.next;
              }
              if(temp2 == null ){
                  temp2 = headB;
                  temp1 = temp3 = temp3.next;
              }

              if(temp1 == temp2)
                  return temp1;
              temp1 = temp1.next;
              temp2 = temp2.next;
          }
    }

    public static int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
                int temp = prices[i+1] - prices[i];
                if( temp > 0){
                    profit += temp;
                }
        }
        return profit;
    }

    public static int maxProfit2(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length ; j++) {
                int temp = prices[j] - prices[i];
                 profit = temp > profit ? temp : profit;
            }
        }
        return profit;
    }



    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * 说明：
     *
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     *
     * 输入: [2,2,1]
     * 输出: 1
     * 示例 2:
     *
     * 输入: [4,1,2,1,2]
     * 输出: 4
     *
     * */

    public static int singleNumber(int[] nums) {
        /*for (int i = 0; i < nums.length; i++) {
             for (int j = 0;j < nums.length ;j++){
                 if(nums[i] == nums[j] && i != j)
                     break;
                 if(j == nums.length - 1)
                      return nums[i];
             }
        }
        return 0;*/

        /**
         * a^a = 0
         * 0^a = a
         * a^b^a = (a^a)^b = 0^b = b
         *
         * */
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return  result;
    }





    /**
     *  反转一个单链表。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * 进阶:
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     *
     * */

    /** 遍历法*/
    public static Node reverseList(Node head) {
        Node pre = null;
        Node temp ;

        while (head != null){
            temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }

       return pre;
    }

    /** 递归法 */
    public static Node reverseList1(Node head) {
        if(head == null || head.next == null)
            return head;

        Node temp =  head.next;

        Node newHead = reverseList1(temp);

        temp.next = head;
        head.next = null;

        return newHead;
    }



    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     *
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     *
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     *
     *
     * */
    public static void reverseString(char[] s) {

        for (int i = 0; i < s.length/2; i++) {
             char temp = s[i];
             s[i] = s[s.length - 1 - i];
             s[s.length - 1 - i] = temp;
        }

    }


   /**
    * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
    *
    * 示例 1:
    *
    * 输入: "Let's take LeetCode contest"
    * 输出: "s'teL ekat edoCteeL tsetnoc" 
    * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
    *
    * 来源：力扣（LeetCode）
    * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii
    * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    * */
    public static String test(String str){
        String[] split = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            char[] chars = split[i].toCharArray();
            for (int j = chars.length -1; j >=0 ; j--) {
                sb.append(chars[j]);
            }
            sb.append(" ");
        }
        return sb.substring(0,sb.length() -1 );
    }



    /**
     * 给你一个整数数组 nums 和一个整数 k。
     *
     * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
     *
     * 请返回这个数组中「优美子数组」的数目。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [1,1,2,1,1], k = 3
     * 输出：2
     * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
     * 示例 2：
     *
     * 输入：nums = [2,4,6], k = 1
     * 输出：0
     * 解释：数列中不包含任何奇数，所以不存在优美子数组。
     * 示例 3：
     *
     * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
     * 输出：16
     *
     * */
    public static int test(int[] numbers,int k){

        int left = 0 ,right = 0 , oddNum = 0,result = 0;

        while (right < numbers.length){

            //如果是奇数
            if((numbers[right++] & 1) != 0){
                oddNum ++;

                //满足k则计算优美子数组数
                if(oddNum == k){
                    //左，右边偶数的个数
                    int leftNum = 0,rightNum =0;
                    //左边偶数个数
                    while ((numbers[left++] & 1) == 0)
                        leftNum++;


                    //右边偶数个数
                    int temp = right ;  //此时的right由于++已指向第k个基数的下一位
                    while (temp < numbers.length  && (numbers[temp++] & 1) == 0  )
                        rightNum++;

                    result += (leftNum + 1)  *  (rightNum + 1) ;
                    //计算完移动窗口
                    oddNum --;
                    //left++; //此时的left由于++ 已指向第一个基数的下一位

                }
            }

        }


         return result;
    }

    public int[] numMovesStones(int a, int b, int c) {
        int[] arr = new int[]{a, b, c};
        Arrays.sort(arr);
        int max = arr[2] - arr[0] - 2;
        int min = 2;
        if(arr[2] - arr[1] <= 2 || arr[1] - arr[0] <= 2)
            min = 1;
        if(arr[2]  - arr[0] == 2)
            min = 0;
        return new int[]{min,max};
    }

    /**
     *  字符串转链表
     *  234   2 -> 3 -> 4
     * */
    public static Node initNode(String number){
        char[] chars = number.toCharArray();
        Node temp = new Node(0);
        Node data = temp;

        for (char aChar : chars) {
            // char ‘3’ 转为 int 3, 不能直接转化，那样得到是‘3’的Ascii
            Node node = new Node(aChar - '0');
            temp.setNext(node);
            temp = node;
        }
        return data.next;
    }

    /**
     * 打印
     */
    public static void print(Node head){
        Node h = head;
        while (h != null){
            System.out.print(h.data);
            if (h.next == null)
                System.out.println();
            else
                System.out.print("->");
            h = h.next;
        }
    }
}
