package mycollection.demo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张航铭
 *	Map集合：该集合存储键值对。要保证键的唯一性
 *  1.添加
 *      put(K key,V value)
 *      putAll(Map<? extends K,? extends V> m)
 *  2.删除
 *      clear();
 *      remove(Object key)
 *  3.判断
 *      containsValue(Object value)
 *      containsKey(Object key)
 *      isEmpty()
 *      
 *  4.获取
 *      get(Object key)
 *      size()
 *      values()
 *      
 *      entrySet()
 *      keySet()
 *      
 * Map
 *     |--Hashtable:底层是哈希表数据结构，不可以存入null键null值。该集合是线程同步的。jdk1.0效率低
 *     |--HashMap:底层是哈希表数据结构，允许使用null键null值，该集合是不同步的。jdk1.2效率高
 *     |--TreeMap:底层是二叉树数据结构。线程不同步，可以用于给Map集合中的键进行排序
 * 和Set很像
 * Set底层就是使用Map集合。
 */
public class MapDemo {
	public static void main(String[] args) {
		Map<String , String> map = new HashMap<>();
		//添加元素，如果出现添加时，相同的键，那么后添加的值会覆盖原有键对应的值。并put()方法返回被覆盖的值
		System.out.println(map.put("01", "zhangsan1"));
		System.out.println(map.put("01", "lisi"));
		map.put("02", "zhangsan2");
		map.put("03", "zhangsan3");
		System.out.println("containsKey:"+map.containsKey("01"));
		System.out.println("remove:"+map.remove("02"));
		System.out.println("get:"+map.get("03"));
		map.put(null, "zhang");
		System.out.println(map);
		System.out.println("-------------");
		//获取Map集合中的所有的值。
		Collection<String> values = map.values();
		System.out.println(values);
	}
}
