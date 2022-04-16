import okio.IOException
import java.io.InputStreamReader
import java.net.Socket
import java.util.Base64

fun main() {
    println("Hello World!")
    val sc: Socket = Socket("smtp.qq.com", 25)
    if (sc != null) {
        println("connected")
        println("============")
        println(sc)
        println("============")
    } else {
        println("connect failed")
        return
    }
    7
    val retMsg = receiver(sc)
    val send = send("helo a", sc)
    receiver(sc)

    println("============")
    println("login")
    send("auth login", sc)
    receiver(sc)


    val username = "1711968378@qq.com"
    val keyword = "nmfmvithpuyghdfc"

    println("============")
    println("send username")
    send(Base64.getEncoder().encodeToString(username.toByteArray()), sc)
    receiver(sc)

    println("============")
    println("send password")
    send(Base64.getEncoder().encodeToString(keyword.toByteArray()), sc)
    receiver(sc)

    mailer(sc)

    sc.close()

}

fun send(msg: String?, sc: Socket?) {
    var message = msg
    val stout = sc!!.getOutputStream()
    try {
        if (stout != null && msg != null) {
            message = "$message\r\n"
            val btmsg = message.toByteArray()
            stout.write(btmsg)
            stout.flush()
        } else {
            println("empty message or socket.")
        }
        println("message sent: $message")
    } catch (err: IOException) {
        println("message sent failed:")
        err.printStackTrace()
    }
}

fun receiver(sc: Socket?): String? {
    var message: String? = ""
    val stin = InputStreamReader(sc!!.getInputStream())
    try {
        val rcvMessage = CharArray(1024)
        val a = stin!!.read(rcvMessage)
        if (a <= -1) {
            return null
        }
        message = String(rcvMessage, 0, a)
        println("received:$message")
    } catch (err: IOException) {
        println("receive message failed:")
        err.printStackTrace()
    }
    return message
}

fun mailer(sc: Socket) {
    println(">>sending email:")
    val mailFrom = "mail from:<1711968378@qq.com>"
    send(mailFrom,sc)
    receiver(sc)

    val mailTo = "rcpt to:<1711968378@qq.com>"
    send(mailTo,sc)
    receiver(sc)

    val data = "data"
    send(data,sc)
    receiver(sc)

    val subject = "From:1711968378@qq.com\r\n" +
            "To:17119683378@qq.com" +
            "Subject:mail from kotlin\r\n\r\n" +
            "This is the email form kotlin.\r\n."
    send(subject,sc)
    receiver(sc)

}
