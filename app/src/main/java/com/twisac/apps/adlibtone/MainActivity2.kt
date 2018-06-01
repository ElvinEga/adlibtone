package com.twisac.apps.adlibtone

import android.Manifest
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.AssetFileDescriptor
import android.media.AudioManager
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class MainActivity2 : AppCompatActivity() {

   // private var txtRingtone: TextView? = null
    //  private String fNmae = "kiss_me.mp3";
    private val fNmae = "wewe_nani.mp3"
    //  private String fPAth = "android.resource://com.egafic.test.ringtontest/raw/kiss_me";
    private val fPAth = "android.resource://com.egafic.test.ringtontest/raw/wewe_nani"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*  txtRingtone = findViewById(R.id.txtRingtone) as TextView
        txtRingtone!!.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 23) {
                if (checkPermission()) {
                    if (Settings.System.canWrite(this@MainActivity2)) {
                        setRingtone()
                    } else {
                        val intent = Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS)
                                .setData(Uri.parse("package:" + packageName))
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }


                    Log.e("value", "Permission already Granted, Now you can save image.")
                } else {
                    requestPermission()
                }
            } else {
                setRingtone()
                Log.e("value", "Not required for requesting runtime permission")
            }
        }
    }


    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(this@MainActivity2, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity2, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this@MainActivity2, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show()
        } else {
            ActivityCompat.requestPermissions(this@MainActivity2, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e("value", "Permission Granted, Now you can save image .")
                if (Build.VERSION.SDK_INT >= 23) {
                    if (Settings.System.canWrite(this@MainActivity2)) {
                        setRingtone()
                    } else {
                        val intent = Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS)
                                .setData(Uri.parse("package:" + packageName))
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }
            } else {
                Log.e("value", "Permission Denied, You cannot save image.")
            }
        }
    }


    private fun setRingtone() {
        var openAssetFileDescriptor: AssetFileDescriptor?
        (getSystemService(Context.AUDIO_SERVICE) as AudioManager).ringerMode = 2
        val file = File(Environment.getExternalStorageDirectory().toString() + "/appkeeda", this.fNmae)
        if (!file.parentFile.exists()) {
            file.parentFile.mkdirs()
        }
        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        val parse = Uri.parse(this.fPAth)
        val contentResolver = contentResolver
        try {
            openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(parse, "r")
        } catch (e2: FileNotFoundException) {
            openAssetFileDescriptor = null
        }

        try {
            val bArr = ByteArray(1024)
            val createInputStream = openAssetFileDescriptor!!.createInputStream()
            val fileOutputStream = FileOutputStream(file)
            var read = createInputStream.read(bArr)
            while (read != -1) {
                fileOutputStream.write(bArr, 0, read)
                read = createInputStream.read(bArr)
            }
            fileOutputStream.close()
        } catch (e3: IOException) {
            e3.printStackTrace()
        }

        val contentValues = ContentValues()
        contentValues.put("_data", file.absolutePath)
        contentValues.put("title", "Wewe nani ringtone")
        contentValues.put("mime_type", "audio/mp3")
        contentValues.put("_size", java.lang.Long.valueOf(file.length()))
        contentValues.put("artist", Integer.valueOf(R.string.app_name))
        contentValues.put("is_ringtone", java.lang.Boolean.valueOf(true))
        contentValues.put("is_notification", java.lang.Boolean.valueOf(false))
        contentValues.put("is_alarm", java.lang.Boolean.valueOf(false))
        contentValues.put("is_music", java.lang.Boolean.valueOf(false))
        try {
            Toast.makeText(this, StringBuilder().append("Ringtone set successfully"), Toast.LENGTH_LONG).show()
            RingtoneManager.setActualDefaultRingtoneUri(baseContext, 1, contentResolver.insert(MediaStore.Audio.Media.getContentUriForPath(file.absolutePath), contentValues))
        } catch (th: Throwable) {
            Toast.makeText(this, StringBuilder().append("Ringtone feature is not working"), Toast.LENGTH_LONG).show()
        }

    }

    companion object {
        private val PERMISSION_REQUEST_CODE = 1
    }*/
    }
}
