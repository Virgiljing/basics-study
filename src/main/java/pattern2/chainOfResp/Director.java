package pattern2.chainOfResp;

/**
 * @author 张航铭
 *	主任
 */
public class Director extends Leader {

	public Director(String name) {
		super(name);
	}

	@Override
	public void handlerRequest(LeaveRequest request) {
		if (request.getLeaveDays()<3) {
			System.out.println("员工："+request.getEmpName()+"请假，天数："+request.getLeaveDays()+"原因："+request.getReason());
			System.out.println("主任："+this.name+",审批通过");
		} else {
			if (this.nextLeader!=null) {
				this.nextLeader.handlerRequest(request);
			}
		}
	}


}
