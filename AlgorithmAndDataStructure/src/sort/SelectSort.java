package sort;
//选择排序
public class SelectSort {
    int data[] = {9,7,5,3,4,6};
    public static void main(String Args[])
    {
    System.out.print("原始数据为：");
    SelectSort test = new SelectSort();
    test.showData();
    test.select();
    }
    void showData()
    {
        for(int i = 0;i< data.length;i++)
        {
            System.out.print(data[i]+" ");
        }
        System.out.print("\n");
    }

    void select()
    {
        int smallest,temp,j,k,index;//选择一个最小的值插入排序
        for (int i =0;i< data.length;i++ )
        {
            smallest = data[i];
            index = i;
            for (j =i+1;j< data.length;j++)
            {
                if (smallest>data[j])
                {
                    smallest = data[j];
                    index = j;
                }
            }
            temp = data[index];//swap操作
            data[index] = data[i];
            data[i] = temp;
            System.out.print("第"+(i+1)+"次排序结果：");
            for(k=0;k<6;k++)
            {
                System.out.print(data[k]+" ");
            }
            System.out.print("\n");

        }
        System.out.print("\n");
    }
}
