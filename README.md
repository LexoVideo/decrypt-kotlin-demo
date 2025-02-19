# decrypt-kotlin-demo

Maven repository
```kotlin   
repositories {
    maven { url 'https://maven.lexo.video/repository/maven-releases/' }
}
```

```kotlin
implementation 'video.lexo.decrypt:player:2.1.6'
```

Decrypt Data
```kotlin
//Pass in the IV related to encryption and decryption
val decryptor = PlayerDecryptor(this@DecryptActivity, "IV")
//Pass in the encrypted data and get the decrypted data
val decryptedResult = decryptor.process(ByteBuffer.wrap(data))
val decryptedData = decryptedResult.data
//After decryption, release it
decryptor.release()
```