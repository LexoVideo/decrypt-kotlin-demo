package video.lexo.decrypt

import android.app.Activity
import android.os.Bundle
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.channels.FileChannel

class DecryptActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decrypt)
        //encrypted TS file path
        val filePath = "/encrypted/test.ts"
        val file = File(filePath)
        var fis: FileInputStream? = null
        var fileChannel: FileChannel? = null
        try {
            fis = FileInputStream(file)
            fileChannel = fis.channel
            val mbb = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length())
            val data = ByteArray(file.length().toInt())
            mbb[data]
            //Pass in the IV related to encryption and decryption
            val decryptor = PlayerDecryptor(this@DecryptActivity, "IV")
            //Pass in the encrypted data and get the decrypted data
            val decryptedResult = decryptor.process(ByteBuffer.wrap(data))
            val decryptedData = decryptedResult.data
            //After decryption, release it
            decryptor.release()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                fileChannel?.close()
                fis?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}