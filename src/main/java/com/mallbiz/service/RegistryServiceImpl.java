package com.mallbiz.service;

import org.springframework.stereotype.Service;

import com.mallbiz.api.TestRegistryService;

@Service("testRegistryServiceImpl")
public class RegistryServiceImpl implements TestRegistryService {
	public String hello(String root,String parent,String child)  {
		/*
		//服务端
        Transaction t = Cat.newTransaction("PigeonService", "cross-server-Transaction");
		 
		Cat.Context context2 = new CatContext();

		context2.addProperty(Context.ROOT, root);
		context2.addProperty(Context.PARENT, parent);
		context2.addProperty(Context.CHILD, child);
		 
		Event crossAppEvent = Cat.newEvent("PigeonService.app", "cat-dubbo-web");
        Event crossServerEvent = Cat.newEvent("PigeonService.client", "127.0.0.1");
        crossAppEvent.setStatus(Event.SUCCESS);
        crossServerEvent.setStatus(Event.SUCCESS);
        crossAppEvent.complete();
        crossServerEvent.complete();
        t.addChild(crossAppEvent);
        t.addChild(crossServerEvent);
	     
	     Cat.logRemoteCallServer(context2);
		 System.err.println("logRemoteCallServer:"+context2);

		 
	     t.setStatus(Transaction.SUCCESS);
	     t.complete();
		
		*/
		
		return "hello return ";
	}
}

