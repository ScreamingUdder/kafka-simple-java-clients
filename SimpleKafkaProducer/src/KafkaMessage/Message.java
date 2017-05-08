// automatically generated, do not modify

package KafkaMessage;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Message extends Table {
  public static Message getRootAsMessage(ByteBuffer _bb) { return getRootAsMessage(_bb, new Message()); }
  public static Message getRootAsMessage(ByteBuffer _bb, Message obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Message __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public String contents() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer contentsAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }

  public static int createMessage(FlatBufferBuilder builder,
      int contentsOffset) {
    builder.startObject(1);
    Message.addContents(builder, contentsOffset);
    return Message.endMessage(builder);
  }

  public static void startMessage(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addContents(FlatBufferBuilder builder, int contentsOffset) { builder.addOffset(0, contentsOffset, 0); }
  public static int endMessage(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishMessageBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

