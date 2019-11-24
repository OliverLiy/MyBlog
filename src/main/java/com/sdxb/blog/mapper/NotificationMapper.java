package com.sdxb.blog.mapper;

import com.sdxb.blog.entity.Notification;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotificationMapper {
    @Insert("insert into notification(notifier,receiver,outerid,type,createtime,status) values (#{notifier},#{receiver},#{outerid},#{type},#{createtime},#{status})" )
    void insert(Notification notification);

    @Select("select count(1) from notification where receiver=#{id}")
    int count(int id);

    @Select("select * from notification where receiver=#{id} order by createtime desc limit #{offset},#{size}")
    List<Notification> list(@Param("id") int id, @Param("offset") int offset, @Param("size") int size);

    @Select("select count(1) from notification where receiver=#{id} and status=0")
    int getunreadcount(int id);

    @Update("update notification set status=1 where id=#{id} and status=0")
    void updatestatus(int id);

    @Select("select type from notification where id=#{id}")
    int gettypebyid(int id);

    @Select("select outerid from notification where id=#{id}")
    int getouteridbyid(int id);

}
