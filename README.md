# SimpleKotlinEmailer
A simple email sender &amp;&amp; test function in kotlin
# Usage manual
After imported, you can use this liborary like example in src/jvmTest/kotlin/EmailTest.kt
notice the "<" and ">" in the example is not required when using.

```kotlin
SimpleEmailSender()
            .setServer(<your smtp server>)
            .setHost(<your smtp port>)
            .setFrom(<the send email address>)
            .setTo(<set the email address to receive>)
            .setUsername(<your username on smtp server>)
            .setPassword(<your password on smtp server>)
            .setSubject(<your email subject>)
            .setContent(<your email content>)
            .send()
```
