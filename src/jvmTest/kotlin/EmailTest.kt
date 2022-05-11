import kotlin.test.Test

class EmailTest {
    @Test
    fun test_email(){
        SimpleEmailSender()
            .setServer(<your smtp server>)
            .setHost(<your smtp port>)
            .setFrom(<set the send email address>)
            .setTo(<set the email address to receive>)
            .setUsername(<set your username on smtp server>)
            .setPassword(<set your password on smtp server>)
            .setSubject(<set your email subject>)
            .setContent(<set your email content>)
            .send()

    }
}
