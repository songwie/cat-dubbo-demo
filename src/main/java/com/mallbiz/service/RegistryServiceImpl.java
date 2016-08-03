package com.mallbiz.service;

import org.springframework.stereotype.Service;

import com.dianping.cat.Cat;
import com.dianping.cat.Cat.Context;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import com.mallbiz.api.TestRegistryService;
import com.mallbiz.controller.CatContext;

@Service("testRegistryServiceImpl")
public class RegistryServiceImpl implements TestRegistryService {
	public String hello(String root,String parent,String child)  {
		
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
		
		
		
		return "hello return ";
	}
}

