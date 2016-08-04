# 点评cat-dubbo-demo

点评cat监控远程调用链与dubbo集成，可以通过点评CAT监控dubbo服务调用关系。
详细说明地址：http://songwie.com/articlelist/171


点评远程PigeonCall 原理为通过Cat.Context上下文，来传递客户端与服务端的调用关系。
如下: CatContext 是实现的Cat.Context 类， 然后客户端在调用Cat.logRemoteCallClient(completeEvent);方法的时候会自动生成客户端的
root，parent，child3个调用id，然后自己发送到服务端 比如通过rpc调用上下文或者http方式，然后服务端获取到3个参数然后生成调用链。

    Cat.Context context2 = new CatContext();

		context2.addProperty(Context.ROOT, root);
		context2.addProperty(Context.PARENT, parent);
		context2.addProperty(Context.CHILD, child);

注意客户端与服务端的PigeonCall.app，PigeonCall.server，PigeonCall.port，PigeonService.app等几个参数必须是这样写，负责无法在cross报表及
dependen 报表看到数据。



客户端：
Transaction t = Cat.newTransaction("PigeonCall", "cross-client-Transaction");
		 
		Cat.Context completeEvent = new CatContext();
 
		Event crossAppEvent = Cat.newEvent("PigeonCall.app", "cat-dubbo-web");
	    Event crossServerEvent = Cat.newEvent("PigeonCall.server", "127.0.0.1");
	    Event crossPortEvent = Cat.newEvent("PigeonCall.port", "8081");
	    crossAppEvent.setStatus(Event.SUCCESS);
	    crossServerEvent.setStatus(Event.SUCCESS);
	    crossPortEvent.setStatus(Event.SUCCESS);
	    crossPortEvent.complete();
	    crossServerEvent.complete();
	    crossPortEvent.complete();
	    t.addChild(crossAppEvent);
	    t.addChild(crossPortEvent);
	    t.addChild(crossServerEvent);
	     
        Cat.logRemoteCallClient(completeEvent);
		System.err.println("logRemoteCallServer:"+completeEvent);
        
        String name = testRegistryService.hello(completeEvent.getProperty(Context.ROOT),
        		completeEvent.getProperty(Context.PARENT),
        		completeEvent.getProperty(Context.CHILD));
	    System.err.println(System.currentTimeMillis()   + ","+ Thread.currentThread() + ","+ name);
	  
		 
	    t.setStatus(Transaction.SUCCESS);
	    t.complete();     
	     
	服务端：
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



