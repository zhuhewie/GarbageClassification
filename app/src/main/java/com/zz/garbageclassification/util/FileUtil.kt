package com.zz.garbageclassification.util

import android.os.Environment
import java.io.*

class FileUtil {
    companion object {
        fun getRootFilePath() :String {
            val rootDir = File("${Environment.getExternalStorageDirectory()}/仲裁人")
            if (!rootDir.exists()) {
                rootDir.mkdirs()
            }
            return rootDir.absolutePath
        }

        /**
         * 截图路径
         */
        fun getEcertificate() : String{
            val certifitFile = File("${getRootFilePath()}/电子证书")
            if (!certifitFile.exists()) {
                certifitFile.mkdirs()
            }
            return certifitFile.absolutePath
        }

        /**
         * 裁剪图片的缓存目录
         */
        fun getCropPath() :String {
            val cropFile = File("${getRootFilePath()}/crop")
            if (!cropFile.exists()) {
                cropFile.mkdirs()
            }
            return cropFile.absolutePath
        }

        fun getDownloadPath() :String {
            val downloadFile = File("${getRootFilePath()}/download")
            if (!downloadFile.exists()) {
                downloadFile.mkdirs()
            }
            return downloadFile.absolutePath
        }

        fun getUploadPath() :String {
            val downloadFile = File("${getRootFilePath()}/upload")
            if (!downloadFile.exists()) {
                downloadFile.mkdirs()
            }
            return downloadFile.absolutePath
        }

        fun getPath(path :String) :String{
            val file = File("${getRootFilePath()}/${path}")
            if (!file.exists()) {
                file.mkdirs()
            }
            return file.absolutePath
        }

        /**
         * 判断下载目录时候存在
         */
        fun isExistDir(saveDir: String): String {
            val file = File(FileUtil.getDownloadPath(), saveDir)
            if (file.exists()) {
                file.delete()
            }
            file.mkdirs()
            return file.absolutePath
        }

        @Throws(IOException::class)


        /**
         * 保存文件到指定位置
         */
        fun saveFile(inputStream: InputStream, file: File) {
            var fos = FileOutputStream(file)
            var bis = BufferedInputStream(inputStream)
            val buffer = ByteArray(1024)
            var len = 0
            len = bis.read(buffer)
            while (len != -1) {
                fos.write(buffer, 0, len)
                len = bis.read(buffer)
            }
            fos.flush()
            fos.close()
            bis.close()
            inputStream.close()
        }

    }
}