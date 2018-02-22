package com.zzw;

/**
 * @author zzw
 * @see
 * @since 2018/2/14
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);
        System.out.println(contextClassLoader.getParent());
        try {
            Class<?> classLoader = ClassLoader.getSystemClassLoader().loadClass(contextClassLoader.getClass().getName());
            Class<?>[] interfaces = classLoader.getInterfaces();
            System.out.println(interfaces);
            while (classLoader.getSuperclass() != null){
                System.out.println(classLoader.getSuperclass());
                classLoader = classLoader.getSuperclass();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
