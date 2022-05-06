import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.Socket
import java.net.UnknownHostException
import java.util.Base64


class SimpleEmailSender(
    private val smtpServer: String?,
    private val smtpHost: Int?,
    private val username:String?,
    private val password:String?,
    private val toMail:String?,
    val encryption: String? = null
) {

    private var socket: Socket? = null
    private var streamIn: InputStream? = null
    private var streamOut: OutputStream? = null
    private var isConnected: Boolean = false
    private var emailBody:EmailBody = EmailBody()

    init {
        if (smtpServer == null) {
            throw Exception(">>Email can not send, no smtp server provided!\r\n")
        }
        if (smtpHost == null) {
            throw Exception(">>Email can not send, no smtp server host provided!\r\n")
        }
        if (username == null) {
            throw Exception(">>Cannot login smtp server, no username provided!\r\n")
        }
        if (password == null){
            throw Exception(">>Cannot login smtp server, no password provided!\r\n")
        }

        try {
            socket = Socket(smtpServer, smtpHost)
            socket!!.soTimeout = 60000
            streamIn = socket!!.getInputStream()
            streamOut = socket!!.getOutputStream()

            if (socket == null || streamIn == null || streamOut == null) {
                println(">>Failed to build socket connection with provided server.\r\n")
            } else {
                isConnected = true
                println(">>Socket connection with smtp server succeed at server: $smtpServer, server host: $smtpHost.\r\n")
                this.authorization()
//                println(">>Socket closed!!!")
            }

        } catch (ioException: IOException) {
            throw Exception(">>Connection could not be established for io exception.\r\n")
        } catch (unKnowHost: UnknownHostException) {
            throw Exception(">>Connection could not be established for unknown host exception.\r\n")
        }

    }

    private fun authorization() {
        if (!isConnected) {
            throw Exception(">>Socket connection did not established!\r\n")
        }
        this.receiveMessage()
        val hello = this.sendMessage("helo a").receiveMessage()

        val auth = this.sendMessage("auth login").receiveMessage()

        this.sendMessage(encodeFn(username!!)).receiveMessage()
        this.sendMessage(encodeFn(password!!)).receiveMessage()

    }

    private fun sendMessage(outMessage: String?): SimpleEmailSender {
        if (socket == null || streamOut == null) {
            throw Exception(">>Message sending failed for no socket established.\r\n")
        }
        var outMsg = outMessage!!
        outMsg = String.format("%s\r\n", outMsg)
        try {
            streamOut!!.write(outMsg.toByteArray())
            streamOut!!.flush()
            println(">>Message has sent:$outMessage")
        } catch (IOExp: IOException) {
            throw IOException(">>Message sent failed for IO exception.\r\n")
        }
        return this
    }

    private fun receiveMessage(): SimpleEmailSender? {
        if (socket == null || streamIn == null) {
            throw Exception(">>Message receive failed for no socket established.\r\n")
        }
        val message: String
        val inStream = InputStreamReader(streamIn!!)
        try {
            val rev = CharArray(1024)
            val a = inStream.read(rev)
            if (a <= -1) {
                return null
            }
            message = String(rev, 0, a)
            println(">>Received message: $message")
        } catch (IOExp: IOException) {
            throw Exception(">>Message received failed for IO exception.\r\n")
        }
        return this
    }

    private fun encodeFn(str: String): String {
        return Base64.getEncoder().encodeToString(str.toByteArray())
    }

    fun closeSocket() {
        this.socket?.close()
        println(">>Socket closed!!!")
    }


    fun send(): Boolean {
        this.sendMessage("mail from:<${username}>").receiveMessage()
        this.sendMessage("rcpt to:<${toMail}>").receiveMessage()
        this.sendMessage("data").receiveMessage()
        this.sendMessage(emailBody.buildEmailBody()).receiveMessage()
        return true
    }

    data class EmailBody(
        var from: String = "",
        var to: String = "",
        var subject: String = "",
        var content: String = ""
    ){
        fun buildEmailBody():String = "From:${this.from}\r\nTo:${this.to}\r\nSubject:${this.subject}\r\n\r\n${this.content}\r\n\r\n."
    }

    fun setFrom(from: String):SimpleEmailSender {
        emailBody.from = from
        return this
    }
    fun setTo(to: String):SimpleEmailSender  {
        emailBody.to = to
        return this
    }
    fun setSubject(subject: String):SimpleEmailSender  {
        emailBody.subject = subject
        return this
    }
    fun setContent(content: String):SimpleEmailSender {
        emailBody.content = content
    return this
    }


}