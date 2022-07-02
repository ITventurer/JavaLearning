package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//二分查找
public class BinarySearch {
    public static void main(String args[]) throws IOException
    {
       int[]arr={1,2,3,6,5,6,6,8,9,5,4};
       int val = 4;
       System.out.println(binarySearch(arr,val));
    }

    public static int binarySearch(int [] arr,int val){
        int low = 0;
        int high = arr.length-1;
        Arrays.sort(arr);
        while(low<=high)
        {   int mid = -1;
            if (low==high){
                mid =low;//为了防止最右边界找不到
            }
            else {
                mid = low+((high-low)>>1);
            }
            if (val<arr[mid])
            {
                high = mid-1;
            }
            else if(val>arr[mid])
            {
                low = mid + 1;
            }
            else
            {
                return mid;
            }
        }
        return -1;
        }
    }
