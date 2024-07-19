package com.wat.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: sec.proto")
public final class GreeterGrpc {

  private GreeterGrpc() {}

  public static final String SERVICE_NAME = "sec.Greeter";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.wat.grpc.DataList,
      com.wat.grpc.Empty> getSendDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendData",
      requestType = com.wat.grpc.DataList.class,
      responseType = com.wat.grpc.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.wat.grpc.DataList,
      com.wat.grpc.Empty> getSendDataMethod() {
    io.grpc.MethodDescriptor<com.wat.grpc.DataList, com.wat.grpc.Empty> getSendDataMethod;
    if ((getSendDataMethod = GreeterGrpc.getSendDataMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getSendDataMethod = GreeterGrpc.getSendDataMethod) == null) {
          GreeterGrpc.getSendDataMethod = getSendDataMethod = 
              io.grpc.MethodDescriptor.<com.wat.grpc.DataList, com.wat.grpc.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "sec.Greeter", "SendData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.wat.grpc.DataList.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.wat.grpc.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("SendData"))
                  .build();
          }
        }
     }
     return getSendDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.wat.grpc.DataList,
      com.wat.grpc.Empty> getSendStreamDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendStreamData",
      requestType = com.wat.grpc.DataList.class,
      responseType = com.wat.grpc.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.wat.grpc.DataList,
      com.wat.grpc.Empty> getSendStreamDataMethod() {
    io.grpc.MethodDescriptor<com.wat.grpc.DataList, com.wat.grpc.Empty> getSendStreamDataMethod;
    if ((getSendStreamDataMethod = GreeterGrpc.getSendStreamDataMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getSendStreamDataMethod = GreeterGrpc.getSendStreamDataMethod) == null) {
          GreeterGrpc.getSendStreamDataMethod = getSendStreamDataMethod = 
              io.grpc.MethodDescriptor.<com.wat.grpc.DataList, com.wat.grpc.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "sec.Greeter", "SendStreamData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.wat.grpc.DataList.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.wat.grpc.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("SendStreamData"))
                  .build();
          }
        }
     }
     return getSendStreamDataMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreeterStub newStub(io.grpc.Channel channel) {
    return new GreeterStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreeterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GreeterBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GreeterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GreeterFutureStub(channel);
  }

  /**
   */
  public static abstract class GreeterImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendData(com.wat.grpc.DataList request,
        io.grpc.stub.StreamObserver<com.wat.grpc.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getSendDataMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.wat.grpc.DataList> sendStreamData(
        io.grpc.stub.StreamObserver<com.wat.grpc.Empty> responseObserver) {
      return asyncUnimplementedStreamingCall(getSendStreamDataMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendDataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.wat.grpc.DataList,
                com.wat.grpc.Empty>(
                  this, METHODID_SEND_DATA)))
          .addMethod(
            getSendStreamDataMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.wat.grpc.DataList,
                com.wat.grpc.Empty>(
                  this, METHODID_SEND_STREAM_DATA)))
          .build();
    }
  }

  /**
   */
  public static final class GreeterStub extends io.grpc.stub.AbstractStub<GreeterStub> {
    private GreeterStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterStub(channel, callOptions);
    }

    /**
     */
    public void sendData(com.wat.grpc.DataList request,
        io.grpc.stub.StreamObserver<com.wat.grpc.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.wat.grpc.DataList> sendStreamData(
        io.grpc.stub.StreamObserver<com.wat.grpc.Empty> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getSendStreamDataMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class GreeterBlockingStub extends io.grpc.stub.AbstractStub<GreeterBlockingStub> {
    private GreeterBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.wat.grpc.Empty sendData(com.wat.grpc.DataList request) {
      return blockingUnaryCall(
          getChannel(), getSendDataMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GreeterFutureStub extends io.grpc.stub.AbstractStub<GreeterFutureStub> {
    private GreeterFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.wat.grpc.Empty> sendData(
        com.wat.grpc.DataList request) {
      return futureUnaryCall(
          getChannel().newCall(getSendDataMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_DATA = 0;
  private static final int METHODID_SEND_STREAM_DATA = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreeterImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GreeterImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_DATA:
          serviceImpl.sendData((com.wat.grpc.DataList) request,
              (io.grpc.stub.StreamObserver<com.wat.grpc.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_STREAM_DATA:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sendStreamData(
              (io.grpc.stub.StreamObserver<com.wat.grpc.Empty>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GreeterBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.wat.grpc.SecProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Greeter");
    }
  }

  private static final class GreeterFileDescriptorSupplier
      extends GreeterBaseDescriptorSupplier {
    GreeterFileDescriptorSupplier() {}
  }

  private static final class GreeterMethodDescriptorSupplier
      extends GreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GreeterMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GreeterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GreeterFileDescriptorSupplier())
              .addMethod(getSendDataMethod())
              .addMethod(getSendStreamDataMethod())
              .build();
        }
      }
    }
    return result;
  }
}
