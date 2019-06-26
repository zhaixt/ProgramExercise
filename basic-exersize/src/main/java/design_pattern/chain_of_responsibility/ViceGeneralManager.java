package design_pattern.chain_of_responsibility;

/**
 * 副总经理
 * @author: zhaixt
 * @date: 2019/6/26 9:41
 */
public class ViceGeneralManager extends Leader {
    public ViceGeneralManager(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        if(request.getLeaveDays() < 20){
            System.out.println("员工"+request.getEmpName()+"请假 天数"+request.getLeaveDays()+"理由："+request.getReason());
            System.out.println("副总经理:"+this.name+"审批通过");
        }else{
            this.nextLeader.handleRequest(request);
        }
    }
}
