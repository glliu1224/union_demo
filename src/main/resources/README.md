#JVM知识点
##1、JVM内存参数设置
*-XX:MaxMetaspaceSize：* 设置原空间最大值，默认是-1，即不限制，或者说只受限于本地内存大小  
*-XX:MetaspaceSize:* 指定元空间出发GC的初始阈值（元空间无固定初始大小），以字节为单位，默认是21M，达到该值就会出发fullGC，进行类型卸载，同事收集器会对该值进行调整，如果释放了大量的空间，就是当降低该值，如果释放了很少的空间，那么在不超过-XX:MaxMetaspaceSize的情况下，适当提高该值，这个跟早起JDK版本的-XX:PermSize参数意思不一样  
*XX:PermSize:* 代表永久代的初始容量  
由于调整元空间大小需要fullGC，这是非常昂贵的操作，如果应用在启动的时候大量FullGC，通常是由于永久代或者元空间发生了大小调整，基于这种情况，一般建议在JVM参数中将MetaspaceSize和MaxMetaspaceSize设置一样的值，并设置的比初始值大，一般8G的物理内存的机器都是设置为256M  
##2、类加载
####1、类加载检查  
虚拟机遇到一条new指令的时候，首先去检查这个指令的参数是否能够在常量池中定位到一个类的符号引用，并且检查这个符号引用代表的类是否被加载，解析和初始化过，如果没有，那么必须执行相应的类加载过程  
####2、分配内存  
在类加载检查通过后，接下来虚拟机将为新生对象分配内存，对象所需内存的大小在类加载完之后便可以完全确定，为对象分配空间的任务相同于一块确定大小的内存在Java堆中划分出来  
这个步骤有两个问题：1、如何划分内存  2、在并发情况下，可能出现正在给对象A分配内存，指针还没来得及修改，对象B又同事使用了原来的指针来分配内存的情况  
####划分内存的方法
####解决并发问题  
使用CAS的方法保证每次操作都是原子操作
#### 3、初始化  
内存分配完成之后，虚拟机需要将分配到的内存空间都初始化为零值，如果使用TLAB，这一工作过程也可以提前至TLAB分配时进行，这一步操作保证了对象的实例字段在JAVA代码中可以不赋初始值可以直接使用  
#### 4、设置对象头  
初始化零值之后，虚拟机要对对象进行赋值，例如这个对象是哪个类的实例，如果才能找到类的类源数据信息，对象的哈希吗、对象的GC分代年龄、这些东西都放置在对象头ObjectHeader中  
对象在内存中的存储布局可以分为3块区域：对象头、实例数据、对其填充位，HotSpot虚拟机的对象头包括两部分信息，第一部分用于存储对象自身运行的数据，如哈希吗、GC分代年龄、锁状态标志、线程持有的锁、偏向线程ID、偏向时间戳等、对象的另一部分是类型指针，即对象指向它的类元数据指针，虚拟机通过指针来确定这个对象是哪个类的实例  
####5、执行<init>方法  
执行<init>方法，即对象按照开发人员的意思进行初始化，对应到语言层面上讲。就是为属性赋值，执行构造方法  
####6、查看对象大小 
引入<dependency>
       <groupId>org.openjdk.jol</groupId>
         <artifactId>jol‐core</artifactId>
           <version>0.9</version>
    </dependency>
###对象在Eden区分配  
大多数情况下，对象在Eden区进行分配，当Eden区没有足够的空间进行分配时，虚拟机将发起一次MinitorGC  
*Eden与Survivor默认大小8:1:1*  
大量的对象被分配在Eden区，Eden区满了之后会出发miniorGC，可能会有99%的对象会被当成垃圾回收掉，剩余存货的对象会被挪到为空的那块survivor区，下一次Eden区满了又会触发miniorGC，把Eden区和survivor区垃圾对象回收，把剩余存货的对象一次性挪到为空的survivor区，因为新生代的对象都是朝生夕死的，存活时间很短，所以比例是8:1:1，一般配置都是Eden尽量大。survivor够用即可  
###大对象直接进入老年代  
大对象就是需要大量连续空间存储的对象，比如字符串、数组。  JVM参数-XX:PretenureSizeThreshold可以设置大对象的大小，如果对象超过设置的大小，这个对象不会进入年轻代，这个参数只在Serial和ParNew两个收集器下有小  
*为什么要这样呢？* 这样是为了避免大对象分配内存是的赋值操作而降低效率  
###长期存活的对象进入老年代  
既然虚拟机采用了分代收集的思想管理内存，那么内存回收是就必须能够识别哪些对象应该放在新生代，哪些对象应该放在老年代，为了做到这一点，虚拟机给每一个对象记录一个对象年龄的计数器  
### 对象动态年龄判断机制  
当前放对象的Survivor区域中，一批对象的总大小大于这块survivor的50%，那么此时大于等于这批对象年龄最大值的对象，就可以直接进入老年代了  
例如survivor区域中有一批对象，年龄1+年龄2—+年龄n的多个年龄综合超过survivor区域的50%，此时就会把年龄n都放入老年代，这个规则其实是希望那些可能是长期存活的对象，尽早进入老年代，对象动态年龄判断机制一般是在minor gc之后触发的。  
###老年代空间分配担保机制
年轻代每次minorGC之前JVM都会计算下老年代剩余可用空间  
如果这个可用空间小于年轻力现有的所有对象之和，包括垃圾对象，就会在miniorGC之前进行一次FullGC  