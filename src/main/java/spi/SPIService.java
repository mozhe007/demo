package spi;


/**
 *  1. 定义一个接口
 *  2.  实现类
 *  3.  在resources/META_INF/services 目录下，编写 {全路径}的文件名，如： spi.SPIservice
 *  4。 使用。 见测试类 SPIServiceTest
 *
 */
public interface SPIService {
    void execute();
}