# SimpleKotlinEmailer
A simple email sender &amp;&amp; test function in kotlin
# Usage manual
After imported, you can use this liborary like example in src/jvmTest/kotlin/EmailTest.kt

```kotlin
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
```
