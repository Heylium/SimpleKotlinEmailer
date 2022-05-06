import kotlin.test.Test

data class Email(
    val smtpServer:String = "<your target smtp server>",
    val smtpHost:Int = <your smtp server port>,
    val username:String = "<your email or your defined name>",
    val password:String = "<your email password>",
    val toEmail: String = username,
)
class EmailTest {
    @Test
    fun testEmail(){
        val email = Email()
        val ttl = SimpleEmailSender(
            email.smtpServer,
            email.smtpHost,
            email.username,
            email.password,
            email.toEmail,
            )
        ttl.setFrom("1711968378@qq.com")
            .setTo("1711968378@qq.com")
            .setSubject("subject Hello from kotlin!")
            .setContent("contetn from kotlin!")
            .send()
        ttl.closeSocket()
    }

}
