package com.zz.garbageclassification.util;

import android.content.Context;
import android.util.Base64;
import com.facebook.android.crypto.keychain.AndroidConceal;
import com.facebook.android.crypto.keychain.SharedPrefsBackedKeyChain;
import com.facebook.crypto.Crypto;
import com.facebook.crypto.CryptoConfig;
import com.facebook.crypto.Entity;
import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;
import com.facebook.crypto.keychain.KeyChain;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/11 <p>
 * <p> 更改时间 : 2019/3/11 <p>
 * <p> 版本号 : 1 <p>
 */
public class ConcealUtil {
    /**
     * 文件加密方法
     * @param crypto
     * @param srcFile  需要加密的源文件
     * @param destFile 加密后的文件
     */
    public static void encrypting(Crypto crypto, File srcFile, File destFile) {
        try {
            FileInputStream fis = new FileInputStream(srcFile);
            OutputStream fileStream = new BufferedOutputStream(new FileOutputStream(destFile));

            OutputStream outputStream = crypto.getCipherOutputStream(
                    fileStream,
                    Entity.create("test_entrypt"));
            int writer = 0;
            byte[] buffer = new byte[1024];
            while ((writer = fis.read(buffer)) > 0) {
                outputStream.write(buffer,0,writer);
                outputStream.flush();
            }
            fis.close();
            outputStream.close();
            fileStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (KeyChainException e) {
            e.printStackTrace();
        } catch (CryptoInitializationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 文件解密方法
     * @param crypto
     * @param destFile
     * @return
     */
    public static String decrypt(Crypto crypto,File srcFile,File destFile){
        String result = "";
        try {
            FileOutputStream out = new FileOutputStream(destFile);
            FileInputStream fileStream = new FileInputStream(destFile);
            BufferedOutputStream bos = new BufferedOutputStream(out);

            InputStream inputStream = crypto.getCipherInputStream(
                    fileStream,
                    Entity.create("test_entrypt"));
            int read;

            byte[] buffer = new byte[1024];

            while ((read = inputStream.read(buffer)) != -1) {
                bos.write(buffer,0,read);
                bos.flush();
            }
            out.close();
            inputStream.close();
            fileStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (KeyChainException e) {
            e.printStackTrace();
        } catch (CryptoInitializationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 加密
     * @param context
     * @param key
     * @param plainText 要加密的文本
     */
    public static void enypto(Context context,String key,String plainText,String spName){
        KeyChain keyChain = new SharedPrefsBackedKeyChain(context, CryptoConfig.KEY_256);
        Crypto crypto = AndroidConceal.get().createDefaultCrypto(keyChain);
        try {
            byte[] cipherText = crypto.encrypt(plainText.getBytes(), Entity.create(key));
            SPUtil.getInstance().putString(spName,encodeForPrefs(cipherText));

        } catch (KeyChainException e) {
            e.printStackTrace();
        } catch (CryptoInitializationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解密
     *
     * @param context
     * @param key
     * @param text 密文
     */
    public static String decrypt(Context context, String key,String text){
        KeyChain keyChain = new SharedPrefsBackedKeyChain(context, CryptoConfig.KEY_256);
        Crypto crypto = AndroidConceal.get().createDefaultCrypto(keyChain);
        try {
            byte[] plainText = crypto.decrypt(decodeFromPrefs(text), Entity.create(key));
            String result = new String(plainText);
            return result;
        } catch (KeyChainException e) {
            e.printStackTrace();
        } catch (CryptoInitializationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * String[] 转 byte
     * @param keyString
     * @return
     */
    static byte[] decodeFromPrefs(String keyString) {
        return keyString == null ? null : Base64.decode(keyString, 0);
    }

    /**
     * byte[] 转 String
     * @param key
     * @return
     */
    static String encodeForPrefs(byte[] key) {
        return key == null ? null : Base64.encodeToString(key, 0);
    }

}
