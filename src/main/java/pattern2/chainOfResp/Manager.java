package pattern2.chainOfResp;

/**
 * @author 张航铭
 *	经理
 */
public class Manager extends Leader {

	public Manager(String name) {
		super(name);
	}

	@Override
	public void handlerRequest(LeaveRequest request) {
		if (request.getLeaveDays()<10) {
			System.out.println("员工："+request.getEmpName()+"请假，天数："+request.getLeaveDays()+"原因："+request.getReason());
			System.out.println("总经理："+this.name+",审批通过");
		} else {
			if (this.nextLeader!=null) {
				this.nextLeader.handlerRequest(request);
			}
		}
	}


}
