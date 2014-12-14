/*
 * 文件名：MyPathTool.java
 * 版权：Copyright 2013-2018 panzhipeng All Rights Reserved. 
 * 描述： IoTest.java
 * 修改人：panzhipeng
 * 修改时间：2009-2-13
 * 修改内容：新增
 */

package servlet;

import java.io.File;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

import junit.framework.TestCase;


/**
 * TODO 添加类描述
 * 
 * @author panzhipeng
 * @version 1.0
 * @since 2009-2-13
 */
public class PathTool extends TestCase {
    
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
     * 方法描述：根据class获取web应用的路径绝对路径
     * 
     * @param c 用来定位的类
     * @return ClassPath 绝对路径
     */
    @SuppressWarnings("unchecked")
	private static String getClassPath(Class c)
    {
        ProtectionDomain pd = c.getProtectionDomain();

        if (pd == null)
        {
            return null;
        }
        CodeSource cs = pd.getCodeSource();

        if (cs == null)
        {
            return null;
        }

        URL url = cs.getLocation();
        if (url == null)
        {
            return null;
        }
        String classPath = url.getPath();
 
        return classPath;
    }
    
    
    /**
     * 获取 String绝对路径
     */ 
    public static String getAbsolutePath(String resource) {
        return getUrl(resource).getPath();
    }
    
    /**
     * 获取 URL 绝对路径,此方法可能会不适合在那种自己写类装载器的环境中使用
     */
    public static URL getUrl(String resource) {
        return Thread.currentThread().getContextClassLoader().getResource(resource);
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
    
    
    /**
     * 测试得到webroot下的classes路径，即例如：
     * D:\work\testPE\WebRoot\WEB-INF\classes\
     * @param args
     */
    public void testGetClassPath() {

        String classPathUrl = PathTool.getClassPath();
        System.out.println("getClassPath() -- classPathUrl:  " + classPathUrl + "\n");
    }
    
}
