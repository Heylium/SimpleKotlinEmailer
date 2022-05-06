import kotlin.test.Test

data class Email(
    val smtpServer:String = "smtp.qq.com",
    val smtpHost:Int = 25,
    val username:String = "1711968378@qq.com",
    val password:String = "nmfmvithpuyghdfc",
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