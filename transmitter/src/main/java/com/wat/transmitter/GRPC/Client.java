package com.wat.transmitter.GRPC;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.PreDestroy;

@Component
public class Client {
	private ManagedChannel channel;
	private GreeterGrpc.GreeterStub asyncStub;	

	public Client() {
		this.channel = ManagedChannelBuilder.forAddress("localhost", 13000).usePlaintext().build();
		this.asyncStub = GreeterGrpc.newStub(channel);
	}

	public void send(DataList list) throws Exception {
		CountDownLatch latch = new CountDownLatch(1);

		try {
			StreamObserver<DataList> requestObserver = asyncStub.sendStreamData(new StreamObserver<Empty>() {
				@Override
				public void onNext(Empty value) {
					// TODO Auto-generated method stub
				}

				@Override
				public void onError(Throwable t) {
					// TODO Auto-generated method stub
					latch.countDown();
				}

				@Override
				public void onCompleted() {
					// TODO Auto-generated method stub
					System.out.println("데이터 송신 완료!!!");
					latch.countDown();
				}
			});
			requestObserver.onNext(list);
			requestObserver.onCompleted();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@PreDestroy
	private void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(100, TimeUnit.MILLISECONDS);
	}
}