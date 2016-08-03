package com.mallbiz.controller;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.dianping.cat.Cat;
import com.dianping.cat.Cat.Context;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import com.mallbiz.api.TestRegistryService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
public class IndexTest {

	@Autowired
	private TestRegistryService testRegistryService; 

	//@Autowired
	//private NotifyImpl notify;

	@Test
	public void indexServer() throws Exception{
	    Thread.currentThread().sleep(100000);
	    ProtocolConfig.destroyAll();
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testCat() throws Exception{
		//客户端
        Transaction t2 = Cat.newTransaction("cross", "cross-server-Transaction");
		 
		Cat.Context context2 = new CatContext();
		context2.addProperty("testKey", "server");
 
        Event crossAppEvent2 = Cat.newEvent("PigeonService.app", "cross-myApp");	//clientName
        Event crossServerEvent2 = Cat.newEvent("PigeonService.client","cross-myClient");	//clientIp
        crossAppEvent2.setStatus(Event.SUCCESS);
        crossServerEvent2.setStatus(Event.SUCCESS);
        crossAppEvent2.complete();
        crossServerEvent2.complete();
        t2.addChild(crossAppEvent2);
        t2.addChild(crossServerEvent2);
	     
        
        String name = testRegistryService.hello(context2.getProperty(Context.ROOT),context2.getProperty(Context.PARENT),context2.getProperty(Context.CHILD));
	    System.err.println(System.currentTimeMillis()   + ","+ Thread.currentThread() + ","+ name);
	  
	    
	    Cat.logRemoteCallServer(context2);
		System.err.println("logRemoteCallServer:"+context2);

		 
	    t2.setStatus(Transaction.SUCCESS);
	    t2.complete();
	    
	    Thread.currentThread().sleep(100000);
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
