# Java中的ThreadLocal详解
## 一、ThreadLocal简介
多线程访问同一个共享变量的时候容易出现并发问题，特别是多个线程对一个变量进行写入的时候，为了保证线程安全，一般使用者在访问共享变量的时候需要进行额外的同步措施才能保证线程安全性。ThreadLocal是除了加锁这种同步方式之外的一种保证一种规避多线程访问出现线程不安全的方法，当我们在创建一个变量后，如果每个线程对其进行访问的时候访问的都是线程自己的变量这样就不会存在线程不安全问题。

ThreadLocal是JDK包提供的，它提供线程本地变量，如果创建一乐ThreadLocal变量，那么访问这个变量的每个线程都会有这个变量的一个副本，在实际多线程操作的时候，操作的是自己本地内存中的变量，从而规避了线程安全问题
##二、ThreadLocal简单使用
下面的例子中，开启两个线程，在每个线程内部设置了本地变量的值，然后调用print方法打印当前本地变量的值。如果在打印之后调用本地变量的remove方法会删除本地内存中的变量，代码如下所示
```java
package com.virgilin.basic.threadlocal;

public class ThreadLocalTest {
    static ThreadLocal<String> localVar = new ThreadLocal<>();
    static void print(String str){
        /**
         * 打印当前线程中本地内存中本地变量的值
         */
        System.out.println(str + ":" + localVar.get());
        /**
         * 清除本地内存中的本地变量
         */
        localVar.remove();
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            localVar.set("localVar1");
            print("thread1");
            System.out.println("after remove: " + localVar.get());
        });

        Thread t2 = new Thread(() -> {
            localVar.set("localVar2");
            print("thread2");
            System.out.println("after remove: " + localVar.get());
        });
        t1.start();
        t2.start();
    }
}
```
运行结果：
```
thread1:localVar1
thread2:localVar2
after remove: null
after remove: null
```
##三、ThreadLocal的实现原理
下面是ThreadLocal的类图结构，从图中可知：Thread类中有两个变量threadLocals和inheritableThreadLocals，二者都是ThreadLocal内部类ThreadLocalMap类型的变量，我们通过查看内部内ThreadLocalMap可以发现实际上它类似于一个HashMap。在默认情况下，每个线程中的这两个变量都为null，只有当线程第一次调用ThreadLocal的set或者get方法的时候才会创建他们（后面我们会查看这两个方法的源码）。除此之外，和我所想的不同的是，每个线程的本地变量不是存放在ThreadLocal实例中，而是放在调用线程的ThreadLocals变量里面（前面也说过，该变量是Thread类的变量）。也就是说，ThreadLocal类型的本地变量是存放在具体的线程空间上，其本身相当于一个装载本地变量的工具壳，通过set方法将value添加到调用线程的threadLocals中，当调用线程调用get方法时候能够从它的threadLocals中取出变量。如果调用线程一直不终止，那么这个本地变量将会一直存放在他的threadLocals中，所以不使用本地变量的时候需要调用remove方法将threadLocals中删除不用的本地变量。下面我们通过查看ThreadLocal的set、get以及remove方法来查看ThreadLocal具体实怎样工作的
![](../image/1368768-20190614000329689-872917045.png)
###　1、set方法源码
```java
public class ThreadLocal<T> {
    public void set(T value) {
         //(1)获取当前线程（调用者线程）
        Thread t = Thread.currentThread();
        //(2)以当前线程作为key值，去查找对应的线程变量，找到对应的map
        ThreadLocalMap map = getMap(t);
        //(3)如果map不为null，就直接添加本地变量，key为当前线程，值为添加的本地变量值
        if (map != null)
            map.set(this, value);
        //(4)如果map为null，说明首次添加，需要首先创建出对应的map
        else
            createMap(t, value);
    }
}
```
在上面的代码中，(2)处调用getMap方法获得当前线程对应的threadLocals(参照上面的图示和文字说明)，该方法代码如下
```java
public class ThreadLocal<T> {
    ThreadLocalMap getMap(Thread t) {
        //获取线程自己的变量threadLocals，并绑定到当前调用线程的成员变量threadLocals上
        return t.threadLocals;
    }
}
```
如果调用getMap方法返回值不为null，就直接将value值设置到threadLocals中（key为当前线程引用，值为本地变量）；如果getMap方法返回null说明是第一次调用set方法（前面说到过，threadLocals默认值为null，只有调用set方法的时候才会创建map），这个时候就需要调用createMap方法创建threadLocals，该方法如下所示
```java
public class ThreadLocal<T> {
    void createMap(Thread t, T firstValue) {
        t.threadLocals = new ThreadLocalMap(this, firstValue);
    }
}
```
　createMap方法不仅创建了threadLocals，同时也将要添加的本地变量值添加到了threadLocals中。
###　2、get方法源码

在get方法的实现中，首先获取当前调用者线程，如果当前线程的threadLocals不为null，就直接返回当前线程绑定的本地变量值，否则执行setInitialValue方法初始化threadLocals变量。在setInitialValue方法中，类似于set方法的实现，都是判断当前线程的threadLocals变量是否为null，是则添加本地变量（这个时候由于是初始化，所以添加的值为null），否则创建threadLocals变量，同样添加的值为null。
```java
public class ThreadLocal<T> {
    public T get() {
        //(1)获取当前线程
        Thread t = Thread.currentThread();
        //(2)获取当前线程的threadLocals变量
        ThreadLocalMap map = getMap(t);
        //(3)如果threadLocals变量不为null，就可以在map中查找到本地变量的值
        if (map != null) {
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                @SuppressWarnings("unchecked")
                T result = (T)e.value;
                return result;
            }
        }
        //(4)执行到此处，threadLocals为null，调用该更改初始化当前线程的threadLocals变量
        return setInitialValue();
    }

    private T setInitialValue() {
        //protected T initialValue() {return null;}
        T value = initialValue();
         //获取当前线程
        Thread t = Thread.currentThread();
        //以当前线程作为key值，去查找对应的线程变量，找到对应的map
        ThreadLocalMap map = getMap(t);
        //如果map不为null，就直接添加本地变量，key为当前线程，值为添加的本地变量值
        if (map != null)
            map.set(this, value);
        //如果map为null，说明首次添加，需要首先创建出对应的map
        else
            createMap(t, value);
        return value;
    }
}
```
###　3、remove方法的实现

　　remove方法判断该当前线程对应的threadLocals变量是否为null，不为null就直接删除当前线程中指定的threadLocals变量
```java
public class ThreadLocal<T> {
     public void remove() {
        //获取当前线程绑定的threadLocals
         ThreadLocalMap m = getMap(Thread.currentThread());
        //如果map不为null，就移除当前线程中指定ThreadLocal实例的本地变量
         if (m != null)
             m.remove(this);
     }
}
```
4、如下图所示：每个线程内部有一个名为threadLocals的成员变量，该变量的类型为ThreadLocal.ThreadLocalMap类型（类似于一个HashMap），其中的key为当前定义的ThreadLocal变量的this引用，value为我们使用set方法设置的值。每个线程的本地变量存放在自己的本地内存变量threadLocals中，如果当前线程一直不消亡，那么这些本地变量就会一直存在（所以可能会导致内存溢出），因此使用完毕需要将其remove掉。
![](../image/1368768-20190614011044060-2111473950.png)

## 四、ThreadLocal不支持继承性
同一个ThreadLocal变量在父线程中被设置值后，在子线程中是获取不到的。（threadLocals中为当前调用线程对应的本地变量，所以二者自然是不能共享的）
```java
package com.virgilin.basic.threadlocal;

public class ThreadLocalTest2 {
    //(1)创建ThreadLocal变量
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        //在main线程中添加main线程的本地变量
        threadLocal.set("mainVar");
        Thread thread = new Thread(() -> {
            System.out.println("子线程中的本地变量值：" + threadLocal.get());
        });

        thread.start();
        System.out.println("main线程中的本地变量：" + threadLocal.get());
    }
}

```
运行结果：
```
main线程中的本地变量：mainVar
子线程中的本地变量值：null
```
## 五、InheritableThreadLocal类
　从上面代码可以看出，InheritableThreadLocal类继承了ThreadLocal类，并重写了childValue、getMap、createMap三个方法。其中createMap方法在被调用（当前线程调用set方法时得到的map为null的时候需要调用该方法）的时候，创建的是inheritableThreadLocal而不是threadLocals。同理，getMap方法在当前调用者线程调用get方法的时候返回的也不是threadLocals而是inheritableThreadLocal。

下面我们看看重写的childValue方法在什么时候执行，怎样让子线程访问父线程的本地变量值。我们首先从Thread类开始说起
```java
public class Thread{
    private void init(ThreadGroup g, Runnable target, String name,
                      long stackSize, AccessControlContext acc,
                      boolean inheritThreadLocals) {
        //判断名字的合法性
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }

        this.name = name;
        //(1)获取当前线程(父线程)
        Thread parent = currentThread();
        //安全校验
        SecurityManager security = System.getSecurityManager();
        if (g == null) {//g:当前线程组
            /* Determine if it's an applet or not */

            /* If there is a security manager, ask the security manager
               what to do. */
            if (security != null) {
                g = security.getThreadGroup();
            }

            /* If the security doesn't have a strong opinion of the matter
               use the parent thread group. */
            if (g == null) {
                g = parent.getThreadGroup();
            }
        }

        /* checkAccess regardless of whether or not threadgroup is
           explicitly passed in. */
        g.checkAccess();

        /*
         * Do we have the required permissions?
         */
        if (security != null) {
            if (isCCLOverridden(getClass())) {
                security.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);
            }
        }

        g.addUnstarted();

        this.group = g;//设置为当前线程组
        this.daemon = parent.isDaemon();;//守护线程与否(同父线程)
        this.priority = parent.getPriority();//优先级同父线程
        if (security == null || isCCLOverridden(parent.getClass()))
            this.contextClassLoader = parent.getContextClassLoader();
        else
            this.contextClassLoader = parent.contextClassLoader;
        this.inheritedAccessControlContext =
                acc != null ? acc : AccessController.getContext();
        this.target = target;
        setPriority(priority);
        //(2)如果父线程的inheritableThreadLocal不为null
        if (inheritThreadLocals && parent.inheritableThreadLocals != null)
            //（3）设置子线程中的inheritableThreadLocals为父线程的inheritableThreadLocals
            this.inheritableThreadLocals =
                ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);
        /* Stash the specified stack size in case the VM cares */
        this.stackSize = stackSize;

        /* Set thread ID */
        tid = nextThreadID();
    }
}
```
在init方法中，首先(1)处获取了当前线程(父线程)，然后（2）处判断当前父线程的inheritableThreadLocals是否为null，然后调用createInheritedMap将父线程的inheritableThreadLocals作为构造函数参数创建了一个新的ThreadLocalMap变量，然后赋值给子线程。下面是createInheritedMap方法和ThreadLocalMap的构造方法
```java
public class Thread{
   static ThreadLocalMap createInheritedMap(ThreadLocalMap parentMap) {
        return new ThreadLocalMap(parentMap);
    }
}
public class ThreadLocal<T> {
    static class ThreadLocalMap {
       private ThreadLocalMap(ThreadLocalMap parentMap) {
            Entry[] parentTable = parentMap.table;
            int len = parentTable.length;
            setThreshold(len);
            table = new Entry[len];

            for (int j = 0; j < len; j++) {
                Entry e = parentTable[j];
                if (e != null) {
                    @SuppressWarnings("unchecked")
                    ThreadLocal<Object> key = (ThreadLocal<Object>) e.get();
                    if (key != null) {
                        Object value = key.childValue(e.value);
                        Entry c = new Entry(key, value);
                        int h = key.threadLocalHashCode & (len - 1);
                        while (table[h] != null)
                            h = nextIndex(h, len);
                        table[h] = c;
                        size++;
                    }
                }
            }
        }
    }
}
```
在构造函数中将父线程的inheritableThreadLocals成员变量的值赋值到新的ThreadLocalMap对象中。返回之后赋值给子线程的inheritableThreadLocals。总之，InheritableThreadLocals类通过重写getMap和createMap两个方法将本地变量保存到了具体线程的inheritableThreadLocals变量中，当线程通过InheritableThreadLocals实例的set或者get方法设置变量的时候，就会创建当前线程的inheritableThreadLocals变量。而父线程创建子线程的时候，ThreadLocalMap中的构造函数会将父线程的inheritableThreadLocals中的变量复制一份到子线程的inheritableThreadLocals变量中。
## 六、从ThreadLocalMap看ThreadLocal使用不当的内存泄漏问题
### 1、基础概念 
首先我们先看看ThreadLocalMap的类图，在前面的介绍中，我们知道ThreadLocal只是一个工具类，他为用户提供get、set、remove接口操作实际存放本地变量的threadLocals（调用线程的成员变量），也知道threadLocals是一个ThreadLocalMap类型的变量，下面我们来看看ThreadLocalMap这个类。在此之前，我们回忆一下Java中的四种引用类型，相关GC只是参考前面系列的文章(JVM相关)

①强引用：Java中默认的引用类型，一个对象如果具有强引用那么只要这种引用还存在就不会被GC。

②软引用：简言之，如果一个对象具有弱引用，在JVM发生OOM之前（即内存充足够使用），是不会GC这个对象的；只有到JVM内存不足的时候才会GC掉这个对象。软引用和一个引用队列联合使用，如果软引用所引用的对象被回收之后，该引用就会加入到与之关联的引用队列中

③弱引用（这里讨论ThreadLocalMap中的Entry类的重点）：如果一个对象只具有弱引用，那么这个对象就会被垃圾回收器GC掉(被弱引用所引用的对象只能生存到下一次GC之前，当发生GC时候，无论当前内存是否足够，弱引用所引用的对象都会被回收掉)。弱引用也是和一个引用队列联合使用，如果弱引用的对象被垃圾回收期回收掉，JVM会将这个引用加入到与之关联的引用队列中。若引用的对象可以通过弱引用的get方法得到，当引用的对象呗回收掉之后，再调用get方法就会返回null

④虚引用：虚引用是所有引用中最弱的一种引用，其存在就是为了将关联虚引用的对象在被GC掉之后收到一个通知。（不能通过get方法获得其指向的对象）
![](../image/1368768-20190614105112553-1657649661.png)
### 2、分析ThreadLocalMap内部实现
上面我们知道ThreadLocalMap内部实际上是一个Entry数组，我们先看看Entry的这个内部类
```java
static class ThreadLocalMap {
      /**
       * 是继承自WeakReference的一个类，该类中实际存放的key是
       * 指向ThreadLocal的弱引用和与之对应的value值(该value值
       * 就是通过ThreadLocal的set方法传递过来的值)
       * 由于是弱引用，当get方法返回null的时候意味着坑能引用
       */
        static class Entry extends WeakReference<ThreadLocal<?>> {
            /** value就是和ThreadLocal绑定的 */
            Object value;
            //k：ThreadLocal的引用，被传递给WeakReference的构造方法
            Entry(ThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }
        }
        
        //WeakReference构造方法(public class WeakReference<T> extends Reference<T> )
         public WeakReference(T referent) {
             super(referent); //referent：ThreadLocal的引用
         }
         
         //Reference构造方法     
         Reference(T referent) {
             this(referent, null);//referent：ThreadLocal的引用
         }
         
         Reference(T referent, ReferenceQueue<? super T> queue) {
             this.referent = referent;
             this.queue = (queue == null) ? ReferenceQueue.NULL : queue;
        }
}
```
　在上面的代码中，我们可以看出，当前ThreadLocal的引用k被传递给WeakReference的构造函数，所以ThreadLocalMap中的key为ThreadLocal的弱引用。当一个线程调用ThreadLocal的set方法设置变量的时候，当前线程的ThreadLocalMap就会存放一个记录，这个记录的key值为ThreadLocal的弱引用，value就是通过set设置的值。如果当前线程一直存在且没有调用该ThreadLocal的remove方法，如果这个时候别的地方还有对ThreadLocal的引用，那么当前线程中的ThreadLocalMap中会存在对ThreadLocal变量的引用和value对象的引用，是不会释放的，就会造成内存泄漏。

　　考虑这个ThreadLocal变量没有其他强依赖，如果当前线程还存在，由于线程的ThreadLocalMap里面的key是弱引用，所以当前线程的ThreadLocalMap里面的ThreadLocal变量的弱引用在gc的时候就被回收，但是对应的value还是存在的这就可能造成内存泄漏(因为这个时候ThreadLocalMap会存在key为null但是value不为null的entry项)。

　　总结：THreadLocalMap中的Entry的key使用的是ThreadLocal对象的弱引用，在没有其他地方对ThreadLoca依赖，ThreadLocalMap中的ThreadLocal对象就会被回收掉，但是对应的不会被回收，这个时候Map中就可能存在key为null但是value不为null的项，这需要实际的时候使用完毕及时调用remove方法避免内存泄漏。