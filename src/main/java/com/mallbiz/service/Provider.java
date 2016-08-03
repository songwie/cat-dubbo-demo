package com.sw.dubbo.regist.service;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        		new String[] {"http://10.20.160.198/wiki/display/dubbo/provider.xml"});
        context.start();

        System.in.read(); // 按任意键退出
    }

}
