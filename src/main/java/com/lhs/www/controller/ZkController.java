package com.lhs.www.controller;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 后台启动zookeeper
 * Watcher
 * new ZooKeeper(connect,timeout,)
 * @author pact
 *https://blog.csdn.net/zhenghhgz/article/details/80581232
 */
@RestController
@RequestMapping("/zk")
public class ZkController {

	@RequestMapping("/getNode")
	public String zkGet() {
		 Watcher watcher= new Watcher(){            
	            public void process(WatchedEvent event) {
	                System.out.println("receive event："+event);
	            }
	        };
	        String value = null;        
	        try {            
	            final ZooKeeper zookeeper = new ZooKeeper("119.23.202.18:2181", 999999, watcher);            
	            final byte[] data = zookeeper.getData("/node_1", watcher, null);
	            value = new String(data);
	            zookeeper.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }        
	        return "获取 node_1 节点值为 [" + value + "]";
	    }
	
}
