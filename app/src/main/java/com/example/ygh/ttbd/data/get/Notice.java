package com.example.ygh.ttbd.data.get;

/**
 * Created by ygh on 2016/12/21.
 */

public class Notice
{

    /**
     * id : 1
     * title : 重庆一中：中学生爱心义卖拼创意 全程英文交流大秀口语
     * brief : 【摘要】 香气四溢的手工饼干、门类众多的课外书籍、精致唯美的陶艺作品……12月21
     * 日，重庆一中初中英语组在渝北校区举办了大型圣诞主题英文爱心义卖活动，学生们以班级为单位，在各自的“岗位”上分工协作，积极筹集善款。
     * image_url : http://i4.cqnews
     * .net/education/attachement/jpg/site82/20161222/00e04c08d28019c5665a01.JPG
     * create_at : 2016-12-22 11:54:40
     * author :
     * publish_at : 2016-12-22 11:54:40
     * approval : 0
     */

    private int id;
    private String title;
    private String brief;
    private String image_url;
    private String create_at;
    private String author;
    private String publish_at;
    private int    approval;

    public void setId(int id)
    {
        this.id = id;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setBrief(String brief)
    {
        this.brief = brief;
    }

    public void setImage_url(String image_url)
    {
        this.image_url = image_url;
    }

    public void setCreate_at(String create_at)
    {
        this.create_at = create_at;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public void setPublish_at(String publish_at)
    {
        this.publish_at = publish_at;
    }

    public void setApproval(int approval)
    {
        this.approval = approval;
    }

    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getBrief()
    {
        return brief;
    }

    public String getImage_url()
    {
        return image_url;
    }

    public String getCreate_at()
    {
        return create_at;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getPublish_at()
    {
        return publish_at;
    }

    public int getApproval()
    {
        return approval;
    }
}
