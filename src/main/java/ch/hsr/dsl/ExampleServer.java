package ch.hsr.dsl;

import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import static ch.hsr.dsl.Protocol.*;

import java.io.IOException;

public class ExampleServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("server starting");
        ServerBuilder.forPort(5001).addService(new MyService()).build().start().awaitTermination();
    }

    private static class MyService extends MyProtocolGrpc.MyProtocolImplBase {
        @Override
        public void getMessage(AMessage request, StreamObserver<BMessage> responseObserver) {
            System.out.println("server received: " + request.getRequest()+ ","+request.getCode());
            BMessage bMessage = BMessage.newBuilder().setReply("hallo").build();
            responseObserver.onNext(bMessage);
            System.out.println("server replied: " + bMessage.getReply());
            responseObserver.onCompleted();
        }
    }
}