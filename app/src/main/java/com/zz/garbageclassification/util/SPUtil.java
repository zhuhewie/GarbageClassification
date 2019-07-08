package com.zz.garbageclassification.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import com.zz.garbageclassification.app.App;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/11 <p>
 * <p> 更改时间 : 2019/3/11 <p>
 * <p> 版本号 : 1 <p>
 */
public class SPUtil {
    private SPUtil() {
        init(App.instance);
    }

    private static final String KEY_NAME_DEFULT = "yijian";
    static volatile SPUtil instance;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor spEditor;
    public static SPUtil getInstance() {
        if (instance == null) {
            synchronized (SPUtil.class) {
                if (instance == null) {
                    instance = new SPUtil();
                }
            }
        }
        return instance;
    }

    @SuppressLint("CommitPrefEdits")
    public SPUtil init(Context context){
        init(context,KEY_NAME_DEFULT);
        return this;
    }

    @SuppressLint("CommitPrefEdits")
    public SPUtil init(Context context, String name){
        sp = context.getSharedPreferences(name,Context.MODE_PRIVATE);
        spEditor = sp.edit();
        return this;
    }



    public void putInt(String key,int value){
        spEditor.putInt(key,value);
        spEditor.apply();

    }
    public void putString(String key,String value){
        spEditor.putString(key,value);
        spEditor.apply();

    }
    public void putBoolean(String key,Boolean value){
        spEditor.putBoolean(key,value);
        spEditor.apply();
    }

    public void putFloat(String key,float value){
        spEditor.putFloat(key,value);
        spEditor.apply();
    }
    public void putLong(String key,long value){
        spEditor.putLong(key,value);
        spEditor.apply();
    }

    public String getString(String key) {
        return sp.getString(key,"");
    }
    public int getInt(String key) {
        return sp.getInt(key,-1);
    }
    public Boolean getBoolean(String key) {
        return sp.getBoolean(key,false);
    }
    public long getLong(String key) {
        return getLong(key, 0L);
    }

    public long getLong(String key,Long defultLong) {
        return sp.getLong(key, defultLong);

    }
    public float getFloat(String key) {
        return sp.getFloat(key,0f);
    }

    public String getStringFromSp(Context context,String spFileName,String key,String encryptPsw) throws Exception{
        init(context,spFileName);
        String encryptValue=sp.getString(key,null);
        //        Log.d("lqsw","cache is :\n"+encryptValue);
        if(encryptValue==null){
            return null;
        }
        byte[] buff=decrypt(Base64.decode(encryptValue,Base64.DEFAULT),encryptPsw);
        return new String(buff);
    }

    public void saveStringToSp(Context context, String spFileName, String key,String value,
                                      String encryptPsw) throws Exception{
        //        Log.d("lqsw-cache","save name:"+spFileName+",key:"+key);
        byte[] buff=encrypt(value,encryptPsw);
        String encryptValue= Base64.encodeToString(buff,Base64.DEFAULT);
        init(context,spFileName);
        //SharedPreferences.Editor editor = context.getSharedPreferences(spFileName, Context.MODE_PRIVATE).edit();
        spEditor.putString(key,encryptValue);
        spEditor.commit();
        //        Log.d("lqsw-cache","save name:"+spFileName+",value:"+);
    }

    private static byte[] encrypt(String content, String password) throws Exception {
        // 创建AES秘钥
        SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES/CBC/PKCS5PADDING");
        // 创建密码器
        Cipher cipher = Cipher.getInstance("AES");
        // 初始化加密器
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // 加密
        return cipher.doFinal(content.getBytes("UTF-8"));
    }

    private static byte[] decrypt(byte[] content, String password) throws Exception {
        // 创建AES秘钥
        SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES/CBC/PKCS5PADDING");
        // 创建密码器
        Cipher cipher = Cipher.getInstance("AES");
        // 初始化解密器
        cipher.init(Cipher.DECRYPT_MODE, key);
        // 解密
        return cipher.doFinal(content);

    }

}
