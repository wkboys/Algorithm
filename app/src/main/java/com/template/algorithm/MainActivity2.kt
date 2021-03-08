package com.template.algorithm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //二分查找
        twoSelete()
        //排序
        maopao()
        charu()
        guibing()
        kuaisu()
       
    }

    private fun twoSelete() {
        // 需要查找的数字
        val targetNumb = 8
        // 目标有序数组
        val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        var middle = 0
        var low = 0
        var high = arr.size - 1
        var isfind = 0

        while (low <= high) {
            middle = (high + low) / 2
            if (arr[middle] == targetNumb) {
                println("$targetNumb 在数组中,下标值为: $middle")
                isfind = 1
                break
            } else if (arr[middle] > targetNumb) {
                // 说明该数在low~middle之间
                high = middle - 1
            } else {
                // 说明该数在middle~high之间
                low = middle + 1
            }
        }
        if (isfind == 0) {
            println("数组不含 $targetNumb")
        }
    }

    private fun maopao() {
        val arr = intArrayOf(1, 0, 3, 4, 5, -6, 7, 8, 9, 10)
        System.out.println("原始数据: " + Arrays.toString(arr))
        for (i in 1 until arr.size) {
            for (j in 0 until arr.size - i) {
                if (arr[j] > arr[j + 1]) {
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                }
            }
        }
        System.out.println("冒泡排序: " + Arrays.toString(arr))
    }

    private fun charu() {
        val arr = intArrayOf(2, 3, 5, 1, 23, 6, 78, 34)
        System.out.println("原始数据: " + Arrays.toString(arr))
        for (i in 1 until arr.size) {
            val temp = arr[i]
            var j = i - 1
            while (j >= 0) {
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j]
                } else {
                    break
                }
                j--
            }
            arr[j + 1] = temp
        }
        System.out.println("插入排序: " + Arrays.toString(arr))
    }

    private fun guibing() {
        val arr = intArrayOf(49, 38, 65, 97, 76, 13, 27, 50)
        val tmp = IntArray(arr.size)
        System.out.println("原始数据: " + Arrays.toString(arr))
        customMergeSort(arr, tmp, 0, arr.size - 1)
        System.out.println("归并排序: " + Arrays.toString(arr))
    }

    fun customMergeSort(a: IntArray?, tmp: IntArray?, start: Int, end: Int) {
        if (start < end) {
            val mid = (start + end) / 2
            // 对左侧子序列进行递归排序
            customMergeSort(a, tmp, start, mid)
            // 对右侧子序列进行递归排序
            customMergeSort(a, tmp, mid + 1, end)
            // 合并
            customDoubleMerge(a, tmp, start, mid, end)
        }
    }

    fun customDoubleMerge(a: IntArray?, tmp: IntArray?, left: Int,mid: Int, right: Int)
    {
        var p1 = left ;
        var p2 = mid+1;
        var k = left;
        while (p1 <= mid && p2 <= right) {
            if (a!![p1] <= a[p2])
                tmp!![k++] = a[p1++];
            else
                tmp!![k++] = a[p2++];
        }
        while (p1 <= mid){
            tmp!![k++] = a!![p1++];
        }

        while (p2 <= right){
            tmp!![k++] = a!![p2++];
        }
        // 复制回原素组
        for (i in left until right+1){
            a!![i] = tmp!![i];
       }
    }

    private fun kuaisu() {
        val arr = intArrayOf(6, 1, 2, 7, 9, 11, 4, 5, 10, 8)
        System.out.println("原始数据: " + Arrays.toString(arr))
        customQuickSort(arr, 0, arr.size - 1)
        System.out.println("快速排序: " + Arrays.toString(arr))
    }

    fun customQuickSort(arr: IntArray, low: Int, high: Int) {
        var i: Int
        var j: Int
        val temp: Int
        var t: Int
        if (low >= high) {
            return
        }
        i = low
        j = high
        temp = arr[low]
        while (i < j) {
            // 先看右边，依次往左递减
            while (temp <= arr[j] && i < j) {
                j--
            }
            // 再看左边，依次往右递增
            while (temp >= arr[i] && i < j) {
                i++
            }
            t = arr[j]
            arr[j] = arr[i]
            arr[i] = t
        }
        arr[low] = arr[i]
        arr[i] = temp
        // 递归调用左半数组
        customQuickSort(arr, low, j - 1)
        // 递归调用右半数组
        customQuickSort(arr, j + 1, high)
    }
}