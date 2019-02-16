JDK 1.8 lib:
access-bridge-64.jar
charsets.jar
cldrdata.jar
deploy.jar
dnsns.jar
jaccess.jar
javawa.jar
jce.jar
jfr.jar
jfxrt.jar
jfxswt.jar
jsse.jar
localedata.jar
management-agent.jar
nashorn.jar
plugin.jar
resources.jar
rt.jar
sunec.jar
sunjce_provider.jar
sunmscapi.jar
sunpkcs11.jar
zipfs.jar

// 1 
access-bridge-64.jar
Java Access Bridge is a technology that exposes the Java Accessibility API in a Microsoft Windows DLL, enabling Java applications and applets that implement the Java Accessibility API to be visible to assistive technologies on Microsoft Windows systems. Java Accessibility API is part of Java Accessibility Utilities, which is a set of utility classes that help assistive technologies provide access to GUI toolkits that implement the Java Accessibility API.
一个允许 WindowsDLL调用java api 技术。

// 2
charsets.jar
Java 字符集，这个类库中包含 Java 所有支持字符集的字符

// 3
cldrdata.jar
The Unicode CLDR provides key building blocks for software to support the world's languages, with the largest and most extensive standard repository of locale data available. This data is used by a wide spectrum of companies for their software internationalization and localization, adapting software to the conventions of different languages for such common software tasks.
Unicode CLDR为支持世界语言的软件提供了关键的构建块，提供了最大和最广泛的语言环境数据标准存储库。

// 4
deploy.jar
Java安装目录的常见部分 - 该文件运行某些产品的安装。 正确设置Java路径后，用户可以执行此文件（只需双击它或按文件上的Enter键），要部署的应用程序将运行其安装程序。 例如。 诺基亚OVI套件通常使用这种部署形式。 作为彼此的JAVA包，如果您将其重命名为ZIP并打开内容，则可以检查包中的类。

// 5
dnsns.jar
名字是DNS naming service ,只有2个方法 getHostByAddr lookupAllHostAddr

// 6
jaccess.jar
Defines JDK utility classes used by implementors of Assistive Technologies.AWT(Abstract Window Toolkit)，中文译为抽象窗口工具包。
@Exported() 当前Activity是否可以被另一个Application的组件启动：true允许被启动；false不允许被启动。

// 7
javaws.jar
JNLP（Java Network Launching Protocol ）是java提供的一种可以通过浏览器直接执行java应用程序的途径
里面同时有com.sun 和 javax.jnlp的路径
包括
-exceptions 异常类
-jnl 各种 XXXDesc, 是这个包的POJO
-net 只有一个Handler.openConnection()
-progress 进度条相关
-security 权限校验
-ui  CacheViewer这个类很大，而且是很像是一个固定的使用页面
-util JavaFX
里面还有Main方法，怎么看都和别的JDK jar有很大的差异

// 8
jce.jar
路径是javax.crypto 
（javax的x是extension的意思，也就是扩展包。java类库是java发布之初就确定了的基础库，
而javax类库则是在上面增加的一层东西，就是为了保持版本兼容要保存原来的，但有些东西有了更好的解决方案，
所以，就加上些，典型的就是awt(Abstract Windowing ToolKit) 和swing。）
这个包都是加密相关的。

// 9
jfr.jar
和 jdk\bin\jmc.exe有关系。Java Mission Control 包括 JMX 控制台和 Java 飞行记录器。
Java 飞行记录器 (JFR) 是一个用于收集有关正在运行的 Java 应用程序的诊断数据和概要分析数据的工具。它集成到 Java 虚拟机 (JVM) 中，
几乎不会带来性能开销，因此甚至可以在高负载生产环境中使用。使用默认设置时，内部测试和客户反馈表明性能影响低于 1%。
对于一些应用程序，这一数字会大幅降低。但是，对于短时间运行的应用程序 (不是在生产环境中运行的应用程序类型)，
相对的启动和预热时间可能会较长，这对性能的影响可能会超过 1%。JFR 收集有关 JVM 及其上运行的 Java 应用程序的数据。

// 10
jfxrt.jar
JDK有个 rt.jar ，是JAVA的。这个就是JavaFX的rt.jar. JavaFX>Swing>AWT.
JavaFX is a set of graphics and media packages that enables developers to design, create, test, debug, and deploy rich 
client applications that operate consistently across diverse platforms.
里面的FXConsole类有针对键盘输入的代码。应该可以用来借鉴

// 11
jfxswt.jar
同为JavaFx相关，对JavaFx和Swing做兼容性操作。

// 12
jsse.jar
SSL连接，验证，X509文件验证，

// 13
localedata.jar
日期显示国际化的包，里面各地区的日期文字，没有复杂逻辑。

// 14 
management-agent.jar
空的？网上资料说的和 jmx 监控有关，没研究为什么是空的。//TODO

// 15
nashorn.jar
包括1.动态链接.包含用于链接调用的动态调用站点的接口和类。       
dynalink与java.lang.invoke包密切相关，并且依赖于该包。
虽然java.lang.invoke为invoke dynamic调用站点的动态链接提供了一个低级别的API，但它不提供一种方法来表示对象的更高级别操作，也不提供实现这些操作的方法。
如果一种语言是静态类型的，并且它的类型系统与JVM的类型系统匹配，那么它可以使用通常的调用、字段访问等指令（例如invokevirtual、getfield）来实现这一点。
但是，如果语言是动态的（因此，某些表达式的类型直到在运行时进行计算时才知道），或者其对象模型或类型系统与JVM的对象模型或类型系统不匹配，
那么它应该使用invokedynamic调用站点，并让dynalink管理它们。
2.Javascript引擎
从 JDK 8 开始，Nashorn取代 Rhino 成为 Java 的嵌入式 JavaScript 引擎。Nashorn 完全支持ECMAScript 5.1 
规范以及一些扩展。该特性允许开发人员将 JavaScript 代码嵌入到 Java 中，甚至从嵌入的 JavaScript 中调用 Java。此外，
它还提供了使用jrunscript从命令行运行 JavaScript 的能力。

// 16
plugin.jar
这个包相当杂。什么东西都有，不好概括。

// 17
resources.jar
提示信息显示国际化的包，里面各地区的文字。只有图片和properties，没有java代码

//18 
rt.jar
JDK的灵魂

//19
sunec.jar
// 20
sunjce_provider.jar
// 21
sunmscapi.jar
// 22
sunpkcs11.jar
以上4个包都是加密相关的。

// 23
zipfs.jar
java 对zip文件操作的支持。因为文件系统不一致， ZipInfo里有各种属性的转换。