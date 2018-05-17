package com.zzy.elf_template;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.View;

import com.zzy.core.ElfConstact;
import com.zzy.core.model.Page;
import com.zzy.elf_template.template.engine.Engine;
import com.zzy.elf_template.template.engine.EngineHelper;

/**
 * @author zzy
 * @date 2018/3/19
 */

public class ElfTemplateProxy {
    private ElfTemplateConstact.Binder mBinder;
    /*******************************************************************************************************/
    public static ElfTemplateProxy getInstance(){
        return ElfTemplateProxy.LazyHolder.ourInstance;
    }
    private static class LazyHolder {
        private static final ElfTemplateProxy ourInstance = new ElfTemplateProxy();
    }
    private ElfTemplateProxy() {}
    public ElfTemplateConstact.Binder getBinder() {
        return mBinder;
    }

    public void init(ElfTemplateConstact.Binder binder) throws RuntimeException{
        if(binder==null){
            throw new RuntimeException("You must set binder first!");
        }
        this.mBinder = binder;
        com.zzy.core.ElfProxy.getInstance().setBinder(new ElfConstact.Binder() {
            @Override
            public SparseArray<ElfConstact.TemplateRender> getTemplateRenderList(Context context, Page page) {
                try {
                    return EngineHelper.getEngineList(context,page);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public Engine getTemplateRender(Context context, int templateId) throws Exception{
                return EngineHelper.getEngin(context,templateId);
            }

            @Override
            public int getTemplateLayoutId(int templateId) {
                return EngineHelper.getLayoutId(templateId);
            }

            @Override
            public void onShowImage(Context context, Uri imageUri, View view) {
                mBinder.onShowImage(context,imageUri,view);
            }

            @Override
            public Fragment onPageGroupGetFragmentEvent(Context context, String pageCode) {
                return mBinder.onPageGroupGetFragmentEvent(context,pageCode);
            }
        });
    }
}
