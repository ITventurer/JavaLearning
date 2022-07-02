package sort;
import java.io.*;
public class InsertSort
{
    int data[] =new int[6];
    int size = 6;
    public static void main(String Args[])
    {
        InsertSort test = new InsertSort();
        test.inputarr();
        System.out.print("您输入的原始数组为：");
        test.showData();
        test.insert();
    }
    public void inputarr()
    {
        int i ;
        for(i=0;i<size;i++)
        {
            try
            {
                System.out.print("请您输"+(i+1)+"个数字：");
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                data[i] = Integer.parseInt(br.readLine());
            }
            catch (Exception e){}
        }
    }
    public void showData()
    {
        int i;
        for (i=0;i<size;i++)
        {
            System.out.print(data[i]+" ");
        }
        System.out.print("\n");

    }

    public void insert()
    {
        int i ,j,temp;
        for (i=1;i<size;i++)
        {
            temp = data[i];
            j=i-1;
            while (j>=0 && temp <data[j])
            {
                data[j+1] = data[j];
                j--;
            }
            data[j+1] =temp;
            System.out.print("第"+i+"次扫面:");
            showData();
        }

    }
}

