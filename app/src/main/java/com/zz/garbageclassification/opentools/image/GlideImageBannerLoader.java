package com.zz.garbageclassification.opentools.image;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import com.zz.garbageclassification.opentools.image.YJImage;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.youth.banner.loader.ImageLoader;

import java.io.File;


public class GlideImageBannerLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        YJImage.Companion.with(context.getApplicationContext())
                .load(path)
//                .apply(new RequestOptions().fitCenter())
                .into(imageView);

    }
//    @Override
//    public ImageView createImageView(Context context) {
//        //圆角
//        return new RoundAngleImageView(context);
//    }
}
