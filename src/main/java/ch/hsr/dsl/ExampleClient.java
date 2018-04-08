package ch.hsr.dsl;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import static ch.hsr.dsl.Protocol.*;

public class ExampleClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5001).usePlaintext().build();
        MyProtocolGrpc.MyProtocolBlockingStub blockingStub = MyProtocolGrpc.newBlockingStub(channel);
        AMessage aMessage = AMessage.newBuilder().setRequest("world").build();
        System.out.println("client sends: "+aMessage.getRequest());
        BMessage bMessage = blockingStub.getMessage(aMessage);
        System.out.println("client received: "+bMessage.getReply());
    }
}
