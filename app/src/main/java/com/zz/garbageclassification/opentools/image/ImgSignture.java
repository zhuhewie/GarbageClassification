package com.zz.garbageclassification.opentools.image;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.Key;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class ImgSignture implements Key {
    private int currentVersion;

    public ImgSignture(int currentVersion) {
        this.currentVersion = currentVersion;
    }

    @Override
    public int hashCode() {
        return currentVersion;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ImgSignture) {
            ImgSignture other = (ImgSignture) obj;
            return this.currentVersion == other.currentVersion;
        }
        return false;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ByteBuffer.allocate(Integer.SIZE).putInt(currentVersion).array());
    }
}
