package com.shushant.common.compose.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import java.util.*

interface FileSystemManager {

    val config: LiveData<FileSystemConfig?>

    fun launch(fileSystemConfig: FileSystemConfig, callback: (Uri?) -> Unit)

    fun setFileUri(uri: Uri?)

    fun getFileInfoEntity(uri: Uri, fileSizeUnit: FileSizeUnit, context: Context): FileInfoEntity?

    fun createImage(context: Context): Uri?

    fun getCreatedImage(): Uri?
}

enum class FileOperation {
    CAMERA, GALLERY, FILE_LIBRARY
}

enum class FileSizeUnit {
    B, KB, MB
}

data class FileSystemConfig(
    val id: String = UUID.randomUUID().toString(),
    val fileOperation: FileOperation
)

data class FileInfoEntity(
    val id: String = UUID.randomUUID().toString(),
    val fileName: String,
    val fileFullName: String,
    val fileMimeType: String,
    val ext: String,
    val fileSize: Long,
    val calcFileSize: Double,
    val fileSizeUnit: FileSizeUnit,
    val uri: Uri,
    val thumbnail: Bitmap?,
    var documentId: String = ""
)
