package pattern2.iterator;

import java.util.ArrayList;
import java.util.List;

public class ConcreteMyAggregate {
	private List<Object> list = new ArrayList<>();

	public ConcreteMyAggregate() {
		super();
	}
	public void addObject(Object obj) {
		this.list.add(obj);
	}
	public void removeObject(Object obj) {
		this.list.remove(obj);
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public MyIterator createIterator() {
		return new ConcreteIterator();
	}
	private class ConcreteIterator implements MyIterator{
		private int cursor;//游标
		@Override
		public void first() {
			cursor = 0;
		}

		@Override
		public void next() {
			if (cursor < list.size()) {
				cursor ++;
			}
		}

		@Override
		public boolean hasNext() {
			if (cursor<list.size()) {
				return true;
			}
			return false;
		}

		@Override
		public boolean isFirst() {
			return cursor == 0;
		}

		@Override
		public boolean isLast() {
			return cursor == list.size()-1;
		}

		@Override
		public Object getCurrentObj() {
			return list.get(cursor);
		}
		
	}
	
}
