package com.enjoy.cluster;

import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.Cluster;
import com.alibaba.dubbo.rpc.cluster.Directory;
import com.alibaba.dubbo.rpc.cluster.support.FailfastClusterInvoker;
import com.alibaba.dubbo.rpc.cluster.support.FailoverClusterInvoker;

public class FailSmsCluster implements Cluster {

    @Override
    public <T> Invoker<T> join(Directory<T> directory) throws RpcException {
        //添加发送消息的业务。
        sendSms();
        //一般这里业务要一个单独的一个FailfastClusterInvoker。来实现容错重试的机制。
        return new FailfastClusterInvoker<>(directory);
    }

    private void sendSms(){
        System.out.println("send sms notify！");
    }
}
