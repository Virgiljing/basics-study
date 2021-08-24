package mycollection;

/**
 * @author 张航铭
 *	有序数组
 */
public class OrderArray{
	private long[] a;
	private int size;
	public OrderArray(int max) {
		a = new long[max];
		size = 0;
	}
	
	
	//普通的线性插入
	public void insert(long value) {
		int j;
		for(j=0;j<size;j++) {
			if (a[j]>value) {
				break;
			}
		}
		for(int k=size;k>j;k--) {
			a[k] = a[k-1];
		}
		a[j] = value;
		size++;
	}
	
	//二分查找
	public int find(long searchKey) {
		int lowerBound = 0;
		int upperBound = size-1;
		int curIn;
		while(true) {
			curIn = (lowerBound + upperBound)/2;
			if (a[curIn] == searchKey) {
				return curIn;
			}else if (lowerBound > upperBound) {
				return size;
			} else {
				if (a[curIn]<searchKey) {
					lowerBound = curIn +1;
				}else {
					upperBound = curIn -1;
				}
			}
		}
	}
	
	//删除操作
	public boolean delete(Long value) {
		int j = find(value);
		if (j == size) {
			return false;
		}else {
			for(int k=j;k<size;k++) {
				a[k] = a[k+1];
			}
			size -- ;
			return true;
		}
	}
	
	public void display() {
		for(int j=1;j<size;j++) {
			System.out.print(a[j] + " ");
		}
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		int maxSize = 100;
		OrderArray arr;
		arr = new OrderArray(maxSize);
		arr.insert(77);
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(00);
		arr.insert(66);
		arr.insert(33);
		int searchKey = 55;
		if (arr.find(searchKey) != arr.size) {
			System.out.println("Found "+searchKey);
		}else {
			System.out.println("Can't find "+searchKey);
			
		}
		
		arr.display();
		arr.delete(88L);
		arr.delete(55L);
		arr.delete(99L);
		arr.display();
	}
}
