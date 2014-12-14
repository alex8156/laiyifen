/**
 * 
 */
package com.user.servlet;

import java.io.File;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

import junit.framework.TestCase;

/**
 * @author pzp
 *
 */
public class PathTool extends TestCase {
	
	/**
	 * 使用当前线程的类加载器得到项目下的classpath路径
	 * @param resource
	 * @return
	 */
	public static String getAbsolutePath(String resource) {
		URL url = Thread.currentThread().getContextClassLoader().getResource(resource);
		String path = url.getPath();
		return path;
//		return url.getPath();
	}
	
	/**
	 * 使用类来定位classpath路径
	 * @param c 用来定位的类
	 * @return classpath绝对路径
	 */
	public static String getClassPath(Class<?> c) {
		ProtectionDomain pd = c.getProtectionDomain();
		if (pd == null) {
			return null;
		}
		CodeSource codeSource = pd.getCodeSource();
		if (codeSource == null) {
			return null;
		}
		URL url = codeSource.getLocation();
		if (url == null) {
			return null;
		}
		return url.getPath();
	}
	
	/**
     * 方法描述：获取web应用下的class路径绝对路径
     * 例如 D:\work\testPE\WebRoot\WEB-INF\classes\
     * 
     * @return ClassPath 绝对路径
     */
    public static String getClassPath()
    {
        String classPath = getClassPath(PathTool.class);
        String classPathUrl = null;
        try {
            classPathUrl = classPath.substring(0, classPath.indexOf("classes"));
        }
        catch (Exception e) {
            File file = new File("");
            classPathUrl = file.getAbsolutePath() + "/WebRoot/WEB-INF/";
//            classPathUrl = Thread.currentThread().getContextClassLoader()
//            .getResource("").getPath();
        }
        classPathUrl += "classes/";
        return classPathUrl;
    } 
	
	
    /**
     * 方法描述：根据class获取web应用的绝对路径
     * 例如 D:\work\testPE\WebRoot\
     * 
     * @return String 绝对路径
     */
    public static String getWebRootPath()
    {
        String classPath = getClassPath();
        String webInfUrl = classPath.substring(0, classPath.indexOf("classes"));
        String webrootUrl = webInfUrl.substring(0, classPath.indexOf("WEB-INF"));
        return webrootUrl;
    }

	
	
	// 测试：使用类来定位classpath路径
	// 输出路径例如：C:/workspace/MyEclipse_8.x/StudyGeneric/bin/
	public void testGetClasspath() {
		
		String classPath = PathTool.getClassPath(PathTool.class);
		System.out.println("testGetClasspath(): " + classPath);
	}
	
	
	// 测试：使用类加载器
	// 输出路径例如：C:/workspace/MyEclipse_8.x/StudyGeneric/bin/
	public void testPath() {
		String classPath = PathTool.getAbsolutePath("");
		System.out.println("getAbsolutePath(): " + classPath);
	}
	
	//---------------------------------------------------------------------
    /**
     * 测试得到webroot路径，即例如：
     * D:\work\testPE\WebRoot\
     * @param args
     */
    public void testGetWebRootPath()
    {
        String webrootUrl = PathTool.getWebRootPath();
        System.out.println("getWebRootPath() -- webrootUrl:  "+webrootUrl +"\n"); 
    }
	
}
