package jdk.system;

public class SystemMain {

    public static void main(String[] args) {
        String javaVersion = System.getProperty("java.version");
        System.out.println("java.version:" + javaVersion);
        String javaVendor = System.getProperty("java.vendor");
        System.out.println("java.vendor:" + javaVendor);
        String javaVendorUrl = System.getProperty("java.vendor.url");
        System.out.println("java.vendor.url:" + javaVendorUrl);
        String javaHome = System.getProperty("java.home");
        System.out.println("java.home:" + javaHome);
        String javaVmSpecificationVersion = System.getProperty("java.vm.specification.version");
        System.out.println("java.vm.specification.version:" + javaVmSpecificationVersion);
        String javaVmSpecificationVendor = System.getProperty("java.vm.specification.vendor");
        System.out.println("java.vm.specification.vendor:" + javaVmSpecificationVendor);
        String javaVmSpecificationName = System.getProperty("java.vm.specification.name");
        System.out.println("java.vm.specification.name:" + javaVmSpecificationName);
        String javaVmVersion = System.getProperty("java.vm.version");
        System.out.println("java.vm.version:" + javaVmVersion);
        String javaVmName = System.getProperty("java.vm.name");
        System.out.println("java.vm.name:" + javaVmName);
        String javaSpecificationVersion = System.getProperty("java.specification.version");
        System.out.println("java.specification.version:" + javaSpecificationVersion);
        String javaSpecificationVendor = System.getProperty("java.specification.vendor");
        System.out.println("java.specification.vendor:" + javaSpecificationVendor);
        String javaSpecificationName = System.getProperty("java.specification.name");
        System.out.println("java.specification.name:" + javaSpecificationName);
        String javaClassVersion = System.getProperty("java.class.version");
        System.out.println("java.class.version:" + javaClassVersion);
        String javaClassPath = System.getProperty("java.class.path");
        System.out.println("java.class.path:" + javaClassPath);
        String javaLibraryPath = System.getProperty("java.library.path");
        System.out.println("java.library.path:" + javaLibraryPath);
        String javaIoTmpdir = System.getProperty("java.io.tmpdir");
        System.out.println("java.io.tmpdir:" + javaIoTmpdir);
        String javaCompiler = System.getProperty("java.compiler");
        System.out.println("java.compiler:" + javaCompiler);
        String javaExtDirs = System.getProperty("java.ext.dirs");
        System.out.println("java.ext.dirs:" + javaExtDirs);
        String osName = System.getProperty("os.name");
        System.out.println("os.name:" + osName);
        String osArch = System.getProperty("os.arch");
        System.out.println("os.arch:" + osArch);
        String osVersion = System.getProperty("os.version");
        System.out.println("os.version:" + osVersion);
        String fileSeparator = System.getProperty("file.separator");
        System.out.println("file.separator:" + fileSeparator);
        String pathSeparator = System.getProperty("path.separator");
        System.out.println("path.separator:" + pathSeparator);
        String lineSeparator = System.getProperty("line.separator");
        System.out.println("line.separator:" + lineSeparator);
        String userName = System.getProperty("user.name");
        System.out.println("user.name:" + userName);
        String userHome = System.getProperty("user.home");
        System.out.println("user.home:" + userHome);
        String userDir = System.getProperty("user.dir");
        System.out.println("user.dir:" + userDir);
    }
}
