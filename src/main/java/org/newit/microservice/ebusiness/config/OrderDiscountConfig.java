package org.newit.microservice.ebusiness.config;

import javax.annotation.PostConstruct;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderDiscountConfig {

    public static double DISCOUNT;

    @PostConstruct
    public void init() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("52.82.122.82:2181", retryPolicy);
        client.start();

        NodeCache nodeCache = new NodeCache(client, "/newit/order-discount");
        nodeCache.getListenable().addListener(
                () -> {
                    DISCOUNT = Double.valueOf(new String(nodeCache.getCurrentData().getData(), "utf-8"));
                    System.out.println("discount change to:" + DISCOUNT);
                });
        nodeCache.start(false);
    }
}
