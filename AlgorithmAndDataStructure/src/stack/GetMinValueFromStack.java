package stack;
import java.util.Stack;

public class GetMinValueFromStack {
    private Stack<Integer> stackdata;
    private Stack<Integer> stackmin;
    public GetMinValueFromStack(){
        this.stackdata = new Stack<Integer>();
        this.stackmin = new Stack<Integer>();
    }
    public void push(int newnum){
        if (this.stackmin.isEmpty()){
            this.stackmin.push(newnum);
        }
        else if(newnum<this.getMinData()){
            this.stackmin.push(newnum);
        }
        else {
            int min =this.stackmin.peek();
            this.stackmin.push(min);
        }
        this.stackdata.push(newnum);
    }
    public int pop(){

        if (this.stackdata.isEmpty()){
            throw new RuntimeException("你的栈已经空了");
        }
        int data = this.stackdata.pop();
        this.stackmin.pop();
        return data;
    }
    public int getMinData(){
        if (stackmin.isEmpty()){
            throw  new RuntimeException("你的栈为空");
        }
         return  this.stackmin.peek();
    }
}
/*寻找栈内最小数的思想是建立一个与栈同步存储数据的最小栈，在每一次压入数据时，数据栈压入数据，数据与最小栈栈顶‘
* 做比较，若小，则压入最小栈栈顶，若大，则将最小栈栈顶值再次压入栈顶。弹出时，数据栈和最小栈保持同步弹出，不过
* 数据栈弹出值返回到用户。保持两个栈数据量的同步。取最小值时，返还给用户最小栈栈顶值即可*/
