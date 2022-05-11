import kotlin.test.Test

class EmailTest {
    @Test
    fun test_email(){
        val params1 = arrayListOf(
            "smtp.qq.com",
            "1711968378@qq.com",
            "nmfmvithpuyghdfc",
            "subject Hello from kotlin!",
            "contetn from kotlin!"

        )
        SimpleEmailSender()
            .setServer(params1[0])
            .setHost(25)
            .setFrom(params1[1])
            .setTo(params1[1])
            .setUsername(params1[1])
            .setPassword(params1[2])
            .setSubject(params1[3])
            .setContent(params1[4])
            .send()

    }
}