package design_pattern.chain_of_responsibility;

/**
 * @author: zhaixt
 * @date: 2019/6/26 9:43
 */
public class ResposibilityChainTest {
    public static void main(String[] args) {
        Leader a = new Director("张三");
        Leader b = new Manager("李四");
        Leader b2 = new ViceGeneralManager("李小四");
        Leader c = new GeneralManager("王五");


        //组织责任链对象关系
        a.setNextLeader(b);
        b.setNextLeader(b2);
        b2.setNextLeader(c);

        //开始请假操作
        LeaveRequest req1 = new LeaveRequest("Tom", 10, "回英国老家");
        a.handleRequest(req1);
    }

}
