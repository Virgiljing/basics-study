package jvm;

import java.util.HashSet;
import java.util.Set;

/**
 * VM Args：-XX:PermSize=6M -XX:MaxPermSize=6M
 *
 * @author zzm
 * 无论是在JDK 7中继续使
 * 用-XX：MaxPermSize参数或者在JDK 8及以上版本使用-XX：MaxMeta-spaceSize参数把方法区容量同
 * 样限制在6MB，也都不会重现JDK 6中的溢出异常
 * 这时候使用-Xmx参数限制最大堆到6MB就能
 * 够看到以下两种运行结果之一，
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
// 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<String>();
// 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}