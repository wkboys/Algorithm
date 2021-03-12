package com.template.algorithm

fun main(){
    //判断整数是否为2的n次方
    var x =5

   //var isTwo=getNum(x)
   //一直除以2
    var isTwo= nCF(x)
    println("istwo=$isTwo")
}

fun nCF(n: Int): Boolean {
    var n = n
    var b = false
    while (true) {
        val j = n % 2
        n = n / 2
        if (j == 1) {
            b = false
            break
        }
        if (n == 2) {
            b = true
            break
        }
    }
    return b
}


fun getNum(i: Int): Boolean {
    return i > 0 && i and i - 1 == 0
}

internal class Solution {
    var nums = intArrayOf(2, 7, 11, 15)
    var target = 9
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val res = IntArray(2)
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i
                    res[1] = j
                    break
                }
            }
        }
        return res
    }
}
