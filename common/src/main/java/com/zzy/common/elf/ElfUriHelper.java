package com.zzy.common.elf;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.zzy.common.R;
import com.zzy.common.utils.ImageLoaderUtils;

import java.util.HashMap;

/**
 * @author zzy
 * @date 2018/5/15
 */

public class ElfUriHelper {
    public static final String HTTP_IMAGE_URI_PREFIX = "http://";
    public static final String LOCAL_IMAGE_URI_PREFIX = "local://";
    public static final String COLOR_URI_PREFIX = "color://";
    private HashMap<String,Uri> localImgUriMap;
    private HashMap<String,Integer> localColorMap;
    /*******************************************************************************************************/
    public static ElfUriHelper getInstance(){
        return ElfUriHelper.LazyHolder.ourInstance;
    }
    private static class LazyHolder {
        private static final ElfUriHelper ourInstance = new ElfUriHelper();
    }
    private ElfUriHelper() {}
    public void init(Context context){
        initImgMap(context);
        initColorMap(context);
    }

    private void initColorMap(Context context) {
        localColorMap = new HashMap<>();
        localColorMap.put(COLOR_URI_PREFIX +"colorPrimary", R.color.colorPrimary);
        localColorMap.put(COLOR_URI_PREFIX +"grey", R.color.grey);
        localColorMap.put(COLOR_URI_PREFIX +"red", R.color.red);
        localColorMap.put(COLOR_URI_PREFIX +"blue", R.color.blue);
    }
    private void initImgMap(Context context){
        localImgUriMap = new HashMap<>();
        addImage(context,"title_icon1",R.mipmap.title_icon1);
        addImage(context,"title_icon2",R.mipmap.title_icon2);
        addImage(context,"title_icon3",R.mipmap.title_icon3);
        addImage(context,"grid_icon1",R.mipmap.grid_icon1);
        addImage(context,"grid_icon2",R.mipmap.grid_icon2);
        addImage(context,"grid_icon3",R.mipmap.grid_icon3);
        addImage(context,"grid_icon4",R.mipmap.grid_icon4);
        addImage(context,"special_project",R.mipmap.special_project);
        addImage(context,"normal",R.mipmap.normal);
        addImage(context,"finished",R.mipmap.finished);
        addImage(context,"right_arrow",R.mipmap.right_arrow);
        addImage(context,"mine_icon1",R.mipmap.mine_icon1);
        addImage(context,"mine_icon2",R.mipmap.mine_icon2);
        addImage(context,"mine_icon3",R.mipmap.mine_icon3);
        addImage(context,"mine_icon4",R.mipmap.mine_icon4);
        addImage(context,"mine_icon5",R.mipmap.mine_icon5);
        addImage(context,"mine_icon6",R.mipmap.mine_icon6);
        addImage(context,"mine_icon7",R.mipmap.mine_icon7);
        addImage(context,"mine_icon8",R.mipmap.mine_icon8);
        addImage(context,"mine_icon9",R.mipmap.mine_icon9);
        addImage(context,"mine_icon10",R.mipmap.mine_icon10);
    }

    private void addImage(Context context,String name,int resId) {
        localImgUriMap.put(LOCAL_IMAGE_URI_PREFIX +name, Uri.parse("android.resource://" + context.getPackageName() + "/" +resId));
    }

    private int getColor(String key){
        if(localColorMap.containsKey(key)){
            return localColorMap.get(key);
        }
        return R.color.elf_white;
    }
    private Uri getImgUri(String key){
        if(localImgUriMap.containsKey(key)){
            return localImgUriMap.get(key);
        }
        return Uri.parse("");
    }

    public void setResource(Context context,Uri uri,View view){
        if(uri.toString().startsWith(ElfUriHelper.HTTP_IMAGE_URI_PREFIX)){
            ImageLoaderUtils.getInstance().showImg(context,uri,view);
        }else if(uri.toString().startsWith(ElfUriHelper.LOCAL_IMAGE_URI_PREFIX)){
            ImageLoaderUtils.getInstance().showImg(context, getImgUri(uri.toString()),view);
        }else if(uri.toString().startsWith(ElfUriHelper.COLOR_URI_PREFIX)){
            view.setBackgroundResource(getColor(uri.toString()));
        }
    }
}
