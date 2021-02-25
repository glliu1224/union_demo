#@Transiactional失效场景  
###(1)---@Transiactional应用在非public修饰的方法上  
:::之所以失效是因为在SpringAOP代理时，TransiactionInterceptor在目标方法执行前后进行拦截，Spring代理工厂在启动时，会扫描所有的类和方法，并且会检查目标方法的修饰符是否为public，不是public则不会获取@Transiactional的属性配置信息  
protected、private、修饰的方法上使用@Transiaction注解，虽然是无无效，但是不会报错  
###(2)---@Transiactional注解属性propagation设置错误  
:::这种失效是由于配置错误，若是错误的配置一下三种propagation，事务将不会法还是能回滚  
@TransactionDefinition.PROPAGATION_SUPPORTS:如果当前存在事务，则加入该事务，如果当前没有事务，则以非事务的方式继续运行  
@TransactionDefinition.PROPAGATION_NOT_SUPPORTED:以非事务方式运行，如果当前存在事务，则把当前事务挂起  
@TransactionDefinition.PROPAGATION_NEVER:以非事务方式运行，如果当前存在事务，抛出异常  
###(3)@Transactional注解属性rollbackFor设置错误  
rollbackFor可以指定能够触发事务回滚的异常类型，Spring默认抛出了未检查unchecked异常(继承自RuntimeException的异常)或者Error才回滚事务，其它异常不会触发回滚事务，如果在事务中抛出其它类型的异常，但却期望Spring能够回滚事务，就需要指出rollbackFor属性  
###(4)同一个类中方法调用，导致@Transactional失效  
开发中避免不了会对同一个类里面的方法调用，比如一个类Test，他的一个方法A，A再调用本类的方法B（不论B是用public还是private修饰），但方法A没有声明注解事务，而B方法有。则外部调用方法A之后，方法B的事务是不会起作用的。  
#Spring中七种传播机制  
###PROPAGATION_REQUIRED  
Spring默认的传播机制能满足绝大部分的业务需求，如果外层有事务，则当前事务加入到外层事务，一块提交，一块回滚。如果外层没有事务，新建一个事务执行  
Class A{  
    @Transaction(prepagation=propagation.REQUIRED)  
    public void method{  
        B b = new B();  
        b.method();  
    }  
}  
Class B{  
    @Transiaction(propagation=propagation.REQUIRED)  
    public void bMethod(){  
    //something  
    }  
}
上图是默认传播方法A调用方法B，那么B会加入到A事务，出现异常两个方法都会回滚  
###PROPAGATION_REQUIRED_NEW  
该事务传播机制每次都会新开启一个事务，同时把外层事务挂起，当前事务执行完毕，恢复上层事务执行，如果外层没有事务，执行当前新开启的事务即可。  
仍然以上面的场景方法A调用方法B作为一个例子，意味着方法A 和方法A是两个独立的事务，当执行方法B时方法A的事务会被挂起等待方法B执行完才执行，若是方法B发生异常回滚，在方法A没有补货异常的情况下方法A也会回滚，但是如果方法A捕获异常后则不会随着B回滚  
###PROPAGATION_SUPPORTS  
如果外层有事务则加入外层事务，如果外层没有事务则使用非事务方式执行，完全依赖外层事务  
###PROPAGATION_NOT_SUPPORTED  
该传播机制不支持事务，如果外层存在事务则挂起，执行完当前代码则回复外层事务，无论是否异常都不会回滚当前代码  
###PROPAGATION_NEVER  
该传播机制不支持外层事务，如果外层有事务则抛出异常  
###PROPAGATION_MANDATORY  
与NEVER相反，如果外层没有事务则抛出异常  
###PROPAGATION_NESTED  
该传播机制的特点是可以保存状态保存点，当事务回滚到某一个点，从而避免所有的嵌套事务都回滚，即各自回滚各自的，如果子事务没有把异常处理掉基本还是会全部回滚的  
