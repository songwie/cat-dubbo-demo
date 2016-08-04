package com.mallbiz.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.mallbiz.api.TestRegistryService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
public class CatDubboTest {

	@Autowired
	private TestRegistryService testRegistryService; 

	//@Autowired
	//private NotifyImpl notify;

	@Test
	public void indexServer() throws Exception{
	    Thread.currentThread().sleep(10000000);
	    ProtocolConfig.destroyAll();
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testCat() throws Exception{
		/*//客户端
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
	    */
		
		String name = testRegistryService.hello("1","1","2");
	    System.err.println(System.currentTimeMillis()   + ","+ Thread.currentThread() + ","+ name);
		
	    Thread.currentThread().sleep(10000000);
	}
	
	@Test
	public void index() throws Exception{
		String requestKey = "1mogo";
	    String name = testRegistryService.hello("1","1","1");
	    System.err.println(System.currentTimeMillis()  
	    		+ ","+ Thread.currentThread() + ","+ name);
	    /*
	    Future<String> pFuture = RpcContext.getContext().getFuture();
	    
	    String p = pFuture.get(); 
	    System.err.println(System.currentTimeMillis()  
	    		+ ","+ Thread.currentThread() + ","+ p);*/
	    
	    
	    Thread.currentThread().sleep(100000);
	    
	    ProtocolConfig.destroyAll();

		/*while (true) {
		    if (!notify.ret.containsKey(requestKey)) {
		        Thread.sleep(200);
		    } else {
		    	System.err.println(notify.ret.get(requestKey));
		        break;
		    }

		}*/
		/*final AtomicInteger count = new AtomicInteger(1);
		final long starttime = System.currentTimeMillis();
        final int threadCount = 8;
    	for(int i=0;i<threadCount;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true){
						try {

							String requestKey = "1111111111111111111111111111111111"
							                   +"1111111111111111111111111111111111"
							                   +"1111111111111111111111111111111111"
							                   +"1111111111111111111111111111111111"
							                   +"1111111111111111111111111111111111"
							                   +"1111111111111111111111111111111111"
							                   +"1111111111111111111111111111111111"
							                   +"1111111111111111111111111111111111" ;

						    String name = testRegistryService.hello(requestKey);
						    //System.err.println(name);

						    if(count.get()%100000==0){
						    	long endtime = System.currentTimeMillis();
	                            System.err.println("thread counts: " + threadCount +","
	                            		+" call count: "+ count.get()+","
	                            		+" tps : "+count.get()/((endtime-starttime)/1000)+"/s ,"
	                            		+" times " + (endtime-starttime)/1000 +"s");

						    }
						    count.incrementAndGet();
						} catch (Exception e) {
							e.printStackTrace();
						}finally{

						}

					}

				}
			}).start();

		}*/

	}

}
