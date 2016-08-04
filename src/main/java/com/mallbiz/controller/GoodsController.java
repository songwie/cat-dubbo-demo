package com.mallbiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.cat.Cat;
import com.dianping.cat.Cat.Context;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import com.mallbiz.catcommon.CatContext;

@Controller("/")
public class GoodsController {
	 @RequestMapping(value="/test")
     @ResponseBody
	 public void test(){
		 for(int i=0;i<1000;i++){
			  Transaction t = Cat.getProducer().newTransaction("newTransaction", "testTransaction");  
			  try {  
	                System.out.println("debug cat ...");  
	                Cat.getProducer().logEvent("newlogEvent", "logEventname", Event.SUCCESS, "keyValuePairs"); 
	                
	                //Thread.sleep(1000);
	                
	                Cat.getProducer().logEvent("newlogEvent", "logEvent1");
	                
	                Cat.logMetricForCount("logMetricForCount");
	                
	                t.setStatus(Transaction.SUCCESS);  
	          } catch (Exception e) {  
	                Cat.getProducer().logError(e);//用log4j记录系统异常，以便在Logview中看到此信息  
	                t.setStatus(e);  
	          } finally {  
	               t.complete();  
	          }  
		 } 
		 
     }
	 
	 @RequestMapping(value="/crossClient")
     @ResponseBody
	 public void crossClient(){
		 //客户端
		 Transaction t = Cat.newTransaction("cross", "cross-client-Transaction");
		 
		 Cat.Context context = new CatContext();
		 context.addProperty("testKey", "client");
		 
		 /*context.addProperty(Context.ROOT, "myroot");
		 context.addProperty(Context.PARENT, "1000");
		 context.addProperty(Context.CHILD, "1000-001");
		 */
		 
	     Event crossAppEvent = Cat.newEvent("PigeonService.app", "cross-myApp");	//clientName
         Event crossServerEvent = Cat.newEvent("PigeonService.client","cross-myClient");	//clientIp
         crossAppEvent.setStatus(Event.SUCCESS);
         crossServerEvent.setStatus(Event.SUCCESS);
         crossAppEvent.complete();
         crossServerEvent.complete();
         t.addChild(crossAppEvent);
         t.addChild(crossServerEvent);
	         
		 Cat.logRemoteCallClient(context);
		 System.err.println("logRemoteCallClient:"+context);
		 t.setStatus(Transaction.SUCCESS);
	     t.complete();
	     
	     crossServer2(context.getProperty(Context.ROOT),context.getProperty(Context.PARENT),context.getProperty(Context.CHILD));
     }
	 
	 private void crossServer2(String root,String parent,String child){
		 //服务端
         Transaction t2 = Cat.newTransaction("cross", "cross-server-Transaction");
		 
		 Cat.Context context2 = new CatContext();
		 context2.addProperty("testKey", "server");

		 context2.addProperty(Context.ROOT, root);
		 context2.addProperty(Context.PARENT, parent);
		 context2.addProperty(Context.CHILD, child);
		 
         Event crossAppEvent2 = Cat.newEvent("PigeonService.app", "cross-myApp");	//clientName
         Event crossServerEvent2 = Cat.newEvent("PigeonService.client","cross-myClient");	//clientIp
         crossAppEvent2.setStatus(Event.SUCCESS);
         crossServerEvent2.setStatus(Event.SUCCESS);
         crossAppEvent2.complete();
         crossServerEvent2.complete();
         t2.addChild(crossAppEvent2);
         t2.addChild(crossServerEvent2);
	     
	     Cat.logRemoteCallServer(context2);
		 System.err.println("logRemoteCallServer:"+context2);

		 
	     t2.setStatus(Transaction.SUCCESS);
	     t2.complete();
     }
	 
	 @RequestMapping(value="/crossServer")
     @ResponseBody
	 private void crossServer(String root,String parent,String child){
		 //服务端
         Transaction t2 = Cat.newTransaction("cross", "cross-server-Transaction");
		 
		 Cat.Context context2 = new CatContext();
		 context2.addProperty("testKey", "server");

		 context2.addProperty(Context.ROOT, root);
		 context2.addProperty(Context.PARENT, parent);
		 context2.addProperty(Context.CHILD, child);
		 
         Event crossAppEvent2 = Cat.newEvent("PigeonService.app", "cross-myApp");	//clientName
         Event crossServerEvent2 = Cat.newEvent("PigeonService.client","cross-myClient");	//clientIp
         crossAppEvent2.setStatus(Event.SUCCESS);
         crossServerEvent2.setStatus(Event.SUCCESS);
         crossAppEvent2.complete();
         crossServerEvent2.complete();
         t2.addChild(crossAppEvent2);
         t2.addChild(crossServerEvent2);
	     
	     Cat.logRemoteCallServer(context2);
		 System.err.println("logRemoteCallServer:"+context2);

		 
	     t2.setStatus(Transaction.SUCCESS);
	     t2.complete();
     }
	 
}
