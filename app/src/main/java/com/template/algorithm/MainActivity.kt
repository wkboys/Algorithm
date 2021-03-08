package com.template.algorithm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Node
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //出现最多的数字
        s1_4()
        //出现最多的数字 提高空间复杂度为O(n) 降低时间复杂度为O(n)
        s2_4()
        //栈的案例
        val s = "{[()()]}"
        System.out.println(isLegal(s))
        //循环队列解决了约瑟夫环问题
//        ring(10, 5)
        // 5 个评委对 1 个运动员的打分 去掉一个最高分和一个最低分
        getScore()
        //字符串匹配
        s1()
        //最大公共子串
        val maxSubString = getMaxSubString("abcdef", "defg")
        println(maxSubString)
        //遍历树 16、13、20、10、15、22、21、26。
        //      20
        //   16     18
        // 10  12
        //前序 中序 后序
        var TreeNode=TreeNode()
        var leftNode=TreeNode()
        var rightNode=TreeNode()
        var left2Node=TreeNode()
        var left3Node=TreeNode()
        TreeNode.value=20
        TreeNode.left=leftNode
        TreeNode.Right=rightNode
        leftNode.value=16
        rightNode.value=18
        leftNode.left=left2Node
        leftNode.Right=left3Node
        left2Node.value=10
        left3Node.value=12
//        printPreoderTree(TreeNode)  //20 16 10 12 18
//        printInoderTree(TreeNode)     //10 16 12 20 18
        printAfterTree(TreeNode)      //10 12 16 18 20
    }

    fun s1_4() {
        val a = intArrayOf(1, 3, 4, 3, 4, 1, 3)
        var val_max = -1
        var time_max = 0    //全局最大次数变量 time_max
        var time_tmp = 0    //当前元素次数 time_tmp
        for (i in a.indices) {
            time_tmp = 0
            for (j in a.indices) {
                if (a[i] == a[j]) {
                    time_tmp += 1
                }
                if (time_tmp > time_max) {
                    time_max = time_tmp
                    val_max = a[i]
                }
            }
        }
        println(val_max)
    }
    fun s2_4() {
        val a = intArrayOf(1, 2, 3, 4, 5, 5, 6)
        val d: MutableMap<Int, Int> = HashMap()
        for (i in a.indices) {
            if (d.containsKey(a[i])) {
                d[a[i]] = d[a[i]]!! + 1
            } else {
                d[a[i]] = 1
            }
        }
        var val_max = -1
        var time_max = 0
        for (key in d.keys) {
            if (d[key]!! > time_max) {
                time_max = d[key]!!
                val_max = key
            }
        }
        println(val_max)
    }

    private fun isLegal(s: String): String? {
        val stack = Stack<Char>()
        for (i in 0 until s.length) {
            val curr = s[i]
            if (isLeft(curr) == 1) {
                stack.push(curr)
            } else {
                if (stack.empty()) {
                    return "非法"
                }
                val p = stack.pop() as Char
                if (isPair(p, curr) == 0) {
                    return "非法"
                }
            }
        }
        return if (stack.empty()) {
            "合法"
        } else {
            "非法"
        }
    }
    private fun isLeft(c: Char): Int {
        return if (c == '{' || c == '(' || c == '[') {
            1
        } else {
            2
        }
    }

    private fun isPair(p: Char, curr: Char): Int {
        return if (p == '{' && curr == '}' || p == '[' && curr == ']' || p == '(' && curr == ')') {
            1
        } else {
            0
        }
    }

    fun ring(n: Int, m: Int) {
        val q = LinkedList<Int>()
        for (i in 1..n) {
            q.add(i)
        }
        val k = 2
        var element = 0
        var i = 0
        while (i < k) {
            element = q.poll()
            q.add(element)
            i++
        }
        i = 1
        while (q.size > 0) {
            element = q.poll()
            if (i < m) {
                q.add(element)
                i++
            } else {
                i = 1
                println(element)
            }
        }
    }

    fun getScore() {
        val a = intArrayOf(2, 1, 4, 5, 3)
        var max_inx = -1
        var max_val = -1
        var min_inx = -1
        var min_val = 99
        for (i in a.indices) {
            if (a[i] > max_val) {
                max_val = a[i]
                max_inx = i
            }
            if (a[i] < min_val) {
                min_val = a[i]
                min_inx = i
            }
        }
        var inx1 = max_inx
        var inx2 = min_inx
        if (max_inx < min_inx) {
            inx1 = min_inx
            inx2 = max_inx
        }
        for (i in inx1 until a.size - 1) {
            a[i] = a[i + 1]
        }
        for (i in inx2 until a.size - 1) {
            a[i] = a[i + 1]
        }
        var sumscore = 0
        for (i in 0 until a.size - 2) {
            sumscore += a[i]
        }
        var avg = sumscore / 3.0
        System.out.println(avg)
    }

    fun s1() {
        val s = "goodgoogle"
        val t = "google"
        var isfind = 0
        for (i in 0 until s.length - t.length + 1) {
            if (s[i] == t[0]) {
                var jc = 0
                for (j in 0 until t.length) {
                    if (s[i + j] != t[j]) {
                        break
                    }
                    jc = j
                }
                if (jc == t.length - 1) {
                    isfind = 1
                }
            }
        }
        println(isfind)
    }

    // 如，传递的参数为 "abcdef" 和"defg"
    fun getMaxSubString(maxString: String, minString: String): String? {
        var max: String? = null //并不知道哪个字符串长，哪个字符串短。
        var min: String? = null
        //1。 先找到最大的字符串和最小的字符串。 根据长度进行比较
        max = if (maxString.length > minString.length) maxString else minString
        min = if (maxString == max) minString else maxString

        //2. 求出最小的那个的长度。 根据这个长度，进行相应的循环。
        val minLength = min.length
        //3.如果整个包含的话，那个就不用循环判断了。
        if (max.contains(min)) {
            return min
        }
        //3.开始进行相关的循环操作了。
        for (i in 0 until minLength) { //从最小的开始循环。
            // 从开头处开始,最后的位置是minLength; 因为subString 截取时不到后面的那个参数，所以这里是<=minLength;
            var start = 0
            var end = minLength - i
            while (end <= minLength) {
                /**
                 * 第一次循环时， 先看整个是否进行了包含，去掉0位，即defg 是否在abcdef 里面。
                 * 第二次循环时，要把defg 去掉一位，看是否在abcdef 里面。 截取时，有def 和efg 两种。
                 * 第三次循环时，把defg 去掉两位。 截取有: de ef fg 三种方式。
                 * 第四次循环时， 把defg 去掉三位，有 d e f g 四种方式。 如果还不存在，则说明没有相同的子串。
                 * 外层循环 为最小的字符串的长度。 0~ length_1
                 * 内层循环为: 从0 开始，结束位置为 length-i, 判断是否在，如果不再，则进行整体移动， 为1 ~length-i+1,2~ length-i+2
                 * 直到 end 结束位置为 length , 因为substring 时不会取出最后的索引值。
                 *
                 */
                val temp = min.substring(start, end) //截取的那个。
                if (max.contains(temp)) {
                    return temp
                }
                start++
                end++
            }
        }
        return null
    }

    //中序
    fun printInoderTree(root: TreeNode?) {
        //base case
        if (root == null) {
            return
        }
        //递归调用printTree
        printInoderTree(root.left)
        System.out.println(root.value)
        printInoderTree(root.Right)
    }

    //前序
    fun printPreoderTree(root: TreeNode?) {
        //base case
        if (root == null) {
            return
        }
        //递归调用printTree
        System.out.println(root.value)
        printPreoderTree(root.left)
        printPreoderTree(root.Right)
    }
    //后续
    fun printAfterTree(root: TreeNode?) {
        //base case
        if (root == null) {
            return
        }
        //递归调用printTree
        printAfterTree(root.left)
        printAfterTree(root.Right)
        System.out.println(root.value)
    }

}