package pattern.proxy.jdk;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public void save() {
		System.out.println("保存客户...");
	}

	@Override
	public void find() {
		System.out.println("查询客户...");
	}

	@Override
	public void update() {
		System.out.println("修改客户...");
	}

	@Override
	public void delete() {
		System.out.println("删除客户...");
	}

}
