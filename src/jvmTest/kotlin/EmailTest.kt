import kotlin.test.Test

data class Email(
//    val smtpServer:String = "smtp.qq.com",
    val smtpServer:String = "smtp.office365.com",
    val smtpHost:Int = 25,
//    val username:String = "1711968378@qq.com",
    val username:String = "rag.eln@ractigen.com",
//    val password:String = "nmfmvithpuyghdfc",
    val password:String = "aaaaaa.1",
//    val toEmail:String = "1711968378@qq.com",
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