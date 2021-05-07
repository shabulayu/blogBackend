package com.siyuanblog.blog.service;

import com.siyuanblog.blog.po.Tag;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    Page<Tag> listTag(Pageable pageable);

    Tag updateTag(Long id, Tag tag) throws NotFoundException;

    void deleteTag(Long id);

}
