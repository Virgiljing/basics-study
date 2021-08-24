package mycollection.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author 张航铭
 * Map集合的两种去除方式：
 *    1.  Set<K> keySet():键map中所有的键存入到Set集合中。Set具有迭代器。
 *        所有可以迭代方式取出的键，在根据get方法。获取每一个键对应的值。
 *        Map集合的取出原理，键Map集合转成Set集合
 *        
 *    2.  Set<entrySet():
 *        将Map集合中的映射关系存入到了set集合中，而这个关系的数据类型就是:Map.Entry
 *      
 */
public class MapDemo2 {
	public static void main(String[] args) {
		Map<String , String> map = new HashMap<>();
		map.put("01", "lisi1");
		map.put("02", "lisi2");
		map.put("03", "lisi3");
		map.put("04", "lisi4");
		map.put("05", "lisi5");
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			String value = map.get(key);
			System.out.println(key+"::::"+value);
		}
		
		Set<Entry<String,String>> entrySet = map.entrySet();
		for (Entry<String, String> entry : entrySet) {
			System.out.println(entry.getKey()+":::"+entry.getValue());
		}
	}
}
