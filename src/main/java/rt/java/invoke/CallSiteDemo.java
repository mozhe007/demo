package rt.java.invoke;


/*
* CallSite是变量MethodHandle的持有者 ，其名称为target 。 链接到CallSite的invokedynamic指令委托对站点当前目标的所有调用。
* CallSite可以与若干invokedynamic指令相关联，或者它可以是“自由浮动”，与无关联。 在任何情况下，都可以通过名为dynamic invoker的关联方法句柄调用它。
CallSite是一个抽象类，不允许用户直接子类化。 它有三个直接的，具体的子类，可以实例化或子类化。

如果不需要一个可变的靶， invokedynamic指令可永久地用的手段结合constant call site 。
如果需要具有易失性变量语义的可变目标，因为必须立即且可靠地见证对目标的更新，可以使用volatile call site 。
否则，如果需要可变目标，则可以使用mutable call site 。*/
public class CallSiteDemo {

}
