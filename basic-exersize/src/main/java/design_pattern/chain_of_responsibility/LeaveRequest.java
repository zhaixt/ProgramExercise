package design_pattern.chain_of_responsibility;

import lombok.Data;

/**
 * 封装请假基本信息
 * @author: zhaixt
 * @date: 2019/6/26 9:39
 */
@Data
public class LeaveRequest {
    private String empName;
    private int leaveDays;
    private String reason;
    public LeaveRequest(String empName, int leaveDays, String reason) {
        super();
        this.empName = empName;
        this.leaveDays = leaveDays;
        this.reason = reason;
    }

}
