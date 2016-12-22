package com.example.ygh.ttbd.data.get;

/**
 * Created by ygh on 2016/12/20.
 */

public class Banner
{
    /**
     * id : 1
     * text : 第一张图
     * action_uri :
     * image_url : http://img2.3lian.com/2014/f2/37/d/40.jpg
     * rank : 0
     */

    private int id;
    private String text;
    private String action_uri;
    private String image_url;
    private int    rank;

    public void setId(int id)
    {
        this.id = id;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public void setAction_uri(String action_uri)
    {
        this.action_uri = action_uri;
    }

    public void setImage_url(String image_url)
    {
        this.image_url = image_url;
    }

    public void setRank(int rank)
    {
        this.rank = rank;
    }

    public int getId()
    {
        return id;
    }

    public String getText()
    {
        return text;
    }

    public String getAction_uri()
    {
        return action_uri;
    }

    public String getImage_url()
    {
        return image_url;
    }

    public int getRank()
    {
        return rank;
    }
}
