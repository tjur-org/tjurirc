package org.tjur.tjurirc

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

data class Message(val target: Target, val message: String)

data class Target(val user: User?, val channel: String?)

private class SendBuffer(private val irc: InputStream): Runnable {
  private var running = false
  private val messageQueue: BlockingQueue<Message> = LinkedBlockingQueue()

  fun run() {
    running = true
    while (running) {
      val message = messageQueue.poll()
      if (message.target.user != null) {
        
      } else if (message.target.channel != null) {
        irc.send(message.message, message.target.channel)
      }
    }
  }

  fun stop() {
    messageQueue.clear()
    // TODO: put something empty in the queue to pop it in the loop
    running = false
  }

  fun send(message: Message) {
    messageQueue.put(message)
  }
}

class TjurIrc(val host: String, val port: Int) {

  val callbacks: MutableList<IrcCallbacks> = ArrayList()

  fun start() {
    val socket = Socket(host, port)
    val sendBuffer = SendBuffer()
    val senderThread = Thread(sendBuffer)
    senderThread.start()
  }

  fun registerCallback(callback: IrcCallbacks) {
    callbacks.add(callback)
  }

  fun send(message: Message, target: Target) {
    sendBuffer.send(message, target)
  }
}
