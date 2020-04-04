package velocity;

import commonbean.Bean;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class VelocityeDemo {

    public static Bean getBean(){
        Bean bean = new Bean();
        bean.setId("<21$ &quot; 3\"df");
        return bean;
    }

    public static void test() {
        VelocityEngine ve = new VelocityEngine();
        //模板文件所在的路径
        String path = "d:/workspace/demo/src/main/java/velocity";
        //设置参数
        ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, path);
//处理中文问题
        ve.setProperty(Velocity.INPUT_ENCODING, "GBK");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "GBK");
        try {
            //初始化模板
            ve.init();
            //获取模板(hello.html)
            //Velocity 模板的名称
            Template template = ve.getTemplate("hello.html");
            //获取上下文
            VelocityContext root = new VelocityContext();
            //把数据填入上下文   （注意：与上面的对应）
            root.put("pageView", getBean());
            root.put("jsonUtils", JsonUtils.getInstance());
//输出路径
            String outpath = "d:/workspace/demo/src/main/java/velocity/helloworld.html";
            //输出
            Writer mywriter = new PrintWriter(new FileOutputStream(new File(outpath)));
            template.merge(root, mywriter);
            mywriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VelocityeDemo.test();
    }
}
