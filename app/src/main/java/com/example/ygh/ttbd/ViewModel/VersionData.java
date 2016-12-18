package com.example.ygh.ttbd.ViewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.ygh.ttbd.BR;

/**
 * Created by ygh on 2016/12/12.
 */

public class VersionData extends BaseObservable
{
    /**
     * id : 1
     * ver_code : 1
     * ver_name : 1
     * url : http://localhost/test.apk
     */
    private int    id;
    private int    ver_code;
    private String ver_name;
    private String url;
    private int    add;

    public void setId(int id)
    {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    public void setVer_code(int ver_code)
    {
        this.ver_code = ver_code;
        notifyPropertyChanged(BR.ver_code);
    }

    public void setVer_name(String ver_name)
    {
        this.ver_name = ver_name;
        notifyPropertyChanged(BR.ver_name);
    }

    public void setUrl(String url)
    {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }

    @Bindable
    public int getId()
    {
        return id;
    }

    @Bindable
    public int getVer_code()
    {
        return ver_code;
    }

    @Bindable
    public String getVer_name()
    {
        return ver_name;
    }

    @Bindable
    public String getUrl()
    {
        return url;
    }

    public int getAdd()
    {
        return add;
    }

    public void setAdd(int add)
    {
        this.add = add;
    }
}
