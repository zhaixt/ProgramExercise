package design_pattern.chain_of_responsibility;

/**
 * @author: zhaixt
 * @date: 2019/6/26 9:38
 */
public abstract class Leader {
    protected String name;
    //责任链上的后继对象
    protected Leader nextLeader;
    public Leader(String name) {
        super();
        this.name = name;
    }
    //设定责任链后继对象
    public void setNextLeader(Leader nextLeader) {
        this.nextLeader = nextLeader;
    }
    /**
     * 处理请求的核心的业务方法
     * @param request
     */
    public abstract void handleRequest(LeaveRequest request);
}
