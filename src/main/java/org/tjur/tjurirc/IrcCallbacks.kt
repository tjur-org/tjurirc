package org.tjur.tjurirc

interface IrcCallbacks {
    fun onMessage(channel: String, user: User, message: String)
    fun onPrivateMessage(user: User, message: String)
    fun onJoin(channel: String, user: User)
    fun onPart(channel: String, user: User)
    fun onQuit(channel: String, user: User, message: String)
    fun onNewNick(oldNick: String, user: User)
    fun onKick(channel: String, kicker: User, kicked: User, reason: String)
    fun onTopicChange(channel: String, setBy: User, topic: String)
    fun onUserPing(user: User)
    fun onServerPing(ping: String)
}